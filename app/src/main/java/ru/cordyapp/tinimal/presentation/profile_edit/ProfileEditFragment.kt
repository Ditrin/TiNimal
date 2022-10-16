package ru.cordyapp.tinimal.presentation.profile_edit

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.databinding.FragmentProfileEditBinding
import ru.cordyapp.tinimal.utils.SharedPref
import ru.cordyapp.tinimal.utils.User
import java.io.File


@AndroidEntryPoint
class ProfileEditFragment : Fragment(R.layout.fragment_profile_edit) {
    private val binding by viewBinding(FragmentProfileEditBinding::bind)
    private val viewModel: ProfileEditViewModel by viewModels()
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var filePart: MultipartBody.Part? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = User.user!!

        loadUser(user)

        with(binding) {
            avatarImageViewProfileEdit.setImageResource(R.drawable.default_avatar)
            appBarInfo.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
            plusImageButtonProfileEdit.setOnClickListener {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                gallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                gallery.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                startActivityForResult(gallery, pickImage)
            }
            removeImageButtonProfileEdit.setOnClickListener {
                Glide.with(this@ProfileEditFragment)
                    .load(R.drawable.default_avatar)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.avatarImageViewProfileEdit)
            }

            viewModel.pathImage.observe(viewLifecycleOwner) {
                Glide.with(this@ProfileEditFragment)
                    .load(it.toString())
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.avatarImageViewProfileEdit)
            }

            saveButtonProfileEdit.setOnClickListener {

                val editUser = UserEditDTO(
                    nameEditTextProfileEdit.text.toString(),
                    numberEditTextProfileEdit.text.toString().toLong(),
                    mailEditTextProfileEdit.text.toString(),
                    addressEditTextProfileEdit.text.toString()
                )

                viewModel.update(editUser, user.id)

                viewModel.isUpdate.observe(viewLifecycleOwner) {
                    if (it) {
                        if (filePart != null) {
                            viewModel.postAvatar(User.user!!.id, filePart!!)
                            viewModel.isAvatar.observe(viewLifecycleOwner) {
                                Toast.makeText(
                                    requireActivity(),
                                    R.string.user_info_update,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                findNavController().navigate(R.id.action_profileEditFragment_to_profileFragment)
                            }
                        } else {

                            Toast.makeText(
                                requireActivity(),
                                R.string.user_info_update,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            findNavController().navigate(R.id.action_profileEditFragment_to_profileFragment)
                        }
                    } else
                        Toast.makeText(
                            requireActivity(),
                            R.string.user_info_not_update,
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }

            removeImageButtonProfileEdit.setOnClickListener {
                viewModel.saveImagePath("".toUri())
            }

            binding.appBarInfo.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageUri?.let { viewModel.saveImagePath(it) }
            Log.d("PATH_TAG", "ImageUri = " + imageUri.toString())

            filePart = getFilePart(imageUri!!)
        }
    }

    private fun getFilePart(uri: Uri): MultipartBody.Part {
        val newUri = getRealPathFromURI(uri)
        val file = File(newUri)
        val fileName: String = File(uri.path).name
        val body = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(
            "file", fileName, body
        )

    }

    private fun loadUser(user: UserDTO) {
        binding.nameEditTextProfileEdit.setText(user.name)
        binding.numberEditTextProfileEdit.setText(user.phoneNumber.toString())
        binding.mailEditTextProfileEdit.setText(user.mail)
        binding.addressEditTextProfileEdit.setText(user.address)
        if (user.avatar == null)
            binding.avatarImageViewProfileEdit.setImageResource(R.drawable.default_avatar)
        else
            Glide.with(this@ProfileEditFragment)
                .load(user.avatar)
                .transform(CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.avatarImageViewProfileEdit)
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val cursor: Cursor =
            requireActivity().contentResolver.query(contentURI, null, null, null, null)!!
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        result = cursor.getString(idx)
        cursor.close()
        return result
    }

}