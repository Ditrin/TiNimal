package ru.cordyapp.tinimal.presentation.profile_edit

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
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

    //    private val args: ProfileEditFragmentArgs by navArgs()
    private val viewModel: ProfileEditViewModel by viewModels()
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var file: File? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = User.user!!

        loadUser(user)

        with(binding) {
            plusImageButtonProfileEdit.setOnClickListener {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                gallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                gallery.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                startActivityForResult(gallery, pickImage)
//                openGalleryForImage()
            }
            removeAccountButtonProfileEdit.setOnClickListener {

            }

            saveButtonProfileEdit.setOnClickListener {
                val editUser = UserEditDTO(
                    nameEditTextProfileEdit.text.toString(),
                    numberEditTextProfileEdit.text.toString().toLong(),
                    mailEditTextProfileEdit.text.toString(),
                    addressEditTextProfileEdit.text.toString()
                )
//                file = File(viewModel.pathImage.value!!.path)
//                Log.d("PATH_TAG", viewModel.pathImage.value!!.path.toString())
//                Log.d("PATH_TAG", file.toString())

                viewModel.update(editUser, user.id)
//                viewModel.postAvatar(user.id, file!!)
                viewModel.isSuccess.observe(viewLifecycleOwner) {
                    if (it) {
                        Toast.makeText(requireActivity(), "User info update", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_profileEditFragment_to_profileFragment)
                    } else
                        Toast.makeText(
                            requireActivity(),
                            "User info not update. Retry later",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
            binding.appBarInfo.setOnClickListener {
                requireActivity().onBackPressed()
            }
            binding.removeAccountButtonProfileEdit.setOnClickListener {
                Log.d("DELETE_TAG", "id = ${User.user!!.id}, token = ${SharedPref.authToken}")
                viewModel.deleteUser(User.user!!.id)
                SharedPref.authToken = ""
                findNavController().navigate(R.id.action_profileEditFragment_to_loginFragment)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageUri?.let { viewModel.saveImagePath(it) }
            binding.avatarImageViewProfileEdit.setImageURI(imageUri)
            file = File(getPath(imageUri))
            Log.d("PATH_TAG", file.toString())
            viewModel.postAvatar(User.user!!.id, file!!)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == pickImage) {
//            binding.avatarImageViewProfileEdit.setImageURI(data?.data)
//            val resolver = requireActivity().contentResolver
//            val uri = data?.data
//            resolver.takePersistableUriPermission(uri!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            viewModel.saveImagePath(uri)
//            Log.d("PATH_TAG", uri.path.toString())
//            file = File(uri.toString())
//            viewModel.postAvatar(User.user!!.id, file!!)
//        }
//    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.type = "image/*"
        startActivityForResult(intent, pickImage)
    }

    private fun loadUser(user: UserDTO) {
        binding.nameEditTextProfileEdit.setText(user.name)
        binding.numberEditTextProfileEdit.setText(user.phoneNumber.toString())
        binding.mailEditTextProfileEdit.setText(user.mail)
        binding.addressEditTextProfileEdit.setText(user.address)
        Glide.with(this@ProfileEditFragment)
            .load(user.avatar)
            .transform(CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.avatarImageViewProfileEdit)
    }

    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor =
            requireActivity().getContentResolver().query(uri!!, projection, null, null, null)
                ?: return null
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s: String = cursor.getString(column_index)
        cursor.close()
        return s
    }

//    fun getRealPathFromURI_API19(uri: Uri?): String? {
//        var filePath = ""
//        val wholeID = DocumentsContract.getDocumentId(uri)
//
//        // Split at colon, use second item in the array
//        val id = wholeID.split(":").toTypedArray()[1]
//        val column = arrayOf(MediaStore.Images.Media.DATA)
//
//        // where id is equal to
//        val sel = MediaStore.Images.Media._ID + "=?"
//        val cursor: Cursor? = requireActivity().contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            column, sel, arrayOf(id), null
//        )
//        val columnIndex = cursor?.getColumnIndex(column[0])
//        if (cursor?.moveToFirst() == true) {
//            filePath = cursor.getString(columnIndex!!)
//        }
//        cursor?.close()
//        return filePath
//    }
}