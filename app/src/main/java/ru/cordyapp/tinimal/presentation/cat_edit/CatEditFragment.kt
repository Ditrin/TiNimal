package ru.cordyapp.tinimal.presentation.cat_edit

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatInfoDTO
import ru.cordyapp.tinimal.databinding.FragmentCatEditBinding
import ru.cordyapp.tinimal.utils.Cat
import ru.cordyapp.tinimal.utils.SharedPref
import java.io.File

@AndroidEntryPoint
class CatEditFragment : Fragment(R.layout.fragment_cat_edit) {
    private val binding by viewBinding(FragmentCatEditBinding::bind)
    private val viewModel: CatEditViewModel by viewModels()
    private var imageUri: Uri? = null
    private var filePart: MultipartBody.Part? = null
    private val myCat = Cat.cat
    private val id = SharedPref.id ?: -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCat(myCat!!)

        with(binding) {
            addPhotoImageButton.setOnClickListener {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                gallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                gallery.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                startActivityForResult(gallery, REQUEST_CODE)
            }

            deleteCatButton.setOnClickListener {
                viewModel.deleteCat(id, myCat.id)
                Toast.makeText(requireActivity(), R.string.cat_was_deleted, Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_catEditFragment_to_profileFragment)

            }

            viewModel.pathImage.observe(viewLifecycleOwner) {
                avatarImageView.setImageURI(it)
            }

            buttonSave.setOnClickListener {
                val catUpdate = CatAddDTO(
                    femaleCheckBox.isChecked,
                    breedEditTextCatFormFragment.text.toString(),
                    nameEditTextCatFormFragment.text.toString(),
                    ageEditTextCatFormFragment.text.toString().toInt(),
                    priceEditTextCatFormFragment.text.toString().toInt(),
                    viewModel.isPassport.value!!,
                    viewModel.isVaccination.value!!,
                    viewModel.isCertificate.value!!,
                    infoEditText.text.toString()
                )

                viewModel.update(catUpdate, id, myCat.id)

                viewModel.isSuccess.observe(viewLifecycleOwner) {
                    if (it) {
                        viewModel.postAvatar(myCat.id, filePart!!)
                        viewModel.isPostAvatar.observe(viewLifecycleOwner) {
                            if (it) {
                                Toast.makeText(
                                    activity,
                                    R.string.cat_was_updated,
                                    Toast.LENGTH_SHORT
                                ).show()
                                requireActivity().onBackPressed()
                            } else
                                Toast.makeText(activity, R.string.cat_was_not_updated, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            }

        }





        viewModel.isCertificate.observe(viewLifecycleOwner) { isCertificate ->
            binding.certificatesCheckBox.setOnClickListener {
                viewModel.setCertificate()
            }
        }

        viewModel.isPassport.observe(viewLifecycleOwner) { isPassport ->
            binding.passportCheckBox.setOnClickListener {
                viewModel.setPassport()
            }
        }

        viewModel.isVaccination.observe(viewLifecycleOwner) { isVaccination ->
            binding.vaccinationCheckBox.setOnClickListener {
                viewModel.setVaccination()

            }
        }

        viewModel.isMale.observe(viewLifecycleOwner) { isMale ->
            binding.maleCheckBox.setOnClickListener {
                viewModel.setSex(false)
                binding.maleCheckBox.isChecked = true
                binding.femaleCheckBox.isChecked = false
            }
            binding.femaleCheckBox.setOnClickListener {
                viewModel.setSex(true)
                binding.maleCheckBox.isChecked = false
                binding.femaleCheckBox.isChecked = true
            }
            Log.d("asd", "${viewModel.isMale.value}")
        }
        binding.appBarInfo.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun loadCat(cat: CatInfoDTO) {
        binding.nameEditTextCatFormFragment.setText(cat.name)
        binding.breedEditTextCatFormFragment.setText(cat.breed)
        binding.ageEditTextCatFormFragment.setText(cat.age.toString())
        binding.priceEditTextCatFormFragment.setText(cat.price.toString())
        binding.infoEditText.setText(cat.info)
        binding.femaleCheckBox.isChecked = cat.sex
        binding.maleCheckBox.isChecked = !cat.sex
        binding.passportCheckBox.isChecked = cat.passport
        binding.vaccinationCheckBox.isChecked = cat.vaccination
        binding.certificatesCheckBox.isChecked = cat.certificates
        Glide.with(this@CatEditFragment)
            .load(cat.photo)
            .transform(CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.avatarImageView)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageUri = data?.data
            imageUri?.let { viewModel.saveImagePath(it) }
            Log.d("PATH_TAG", "ImageUri = " + imageUri.toString())

            filePart = getFilePart(imageUri!!)
        }
    }

    companion object {
        private const val REQUEST_CODE = 200
    }

}