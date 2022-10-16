package ru.cordyapp.tinimal.presentation.cat_form

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAvatarDTO
import ru.cordyapp.tinimal.databinding.FragmentCatFormBinding
import ru.cordyapp.tinimal.utils.SharedPref
import java.io.File

@AndroidEntryPoint
class CatFormFragment : Fragment(R.layout.fragment_cat_form) {
    private val binding by viewBinding(FragmentCatFormBinding::bind)
    private val viewModel: CatFormViewModel by viewModels()
    private val id = SharedPref.id ?: -1
    private var imageUri: Uri? = null
    private var filePart: MultipartBody.Part? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.isEnabled.observe(viewLifecycleOwner) {
                buttonCreate.isEnabled = it
            }
            buttonCreate.setOnClickListener {
                viewModel.verify(
                    nameEditTextCatFormFragment.text.toString(),
                    breedEditTextCatFormFragment.text.toString(),
                    ageEditTextCatFormFragment.text.toString(),
                    priceEditTextCatFormFragment.text.toString(),
                    catStoryEditTextCatFormFragment.text.toString()
                )
                viewModel.isValidate.observe(viewLifecycleOwner) {
                    if (it) {
                        errorTextView.visibility = View.INVISIBLE
                        val cat = CatAddDTO(
                            femaleCheckBox.isChecked,
                            breedEditTextCatFormFragment.text.toString(),
                            nameEditTextCatFormFragment.text.toString(),
                            ageEditTextCatFormFragment.text.toString().toInt(),
                            priceEditTextCatFormFragment.text.toString().toInt(),
                            viewModel.isPassport.value!!,
                            viewModel.isVaccination.value!!,
                            viewModel.isCertificate.value!!,
                            catStoryEditTextCatFormFragment.text.toString(),
                        )
                        viewModel.addCat(cat, id)
                        viewModel.isSuccess.observe(viewLifecycleOwner) {
                            if (it) {
                                viewModel.postAvatar(viewModel.cat.value!!.id, filePart!!)
                                viewModel.isPostAvatar.observe(viewLifecycleOwner) {
                                    if (it) {
                                        Toast.makeText(
                                            activity,
                                            R.string.cat_created,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        findNavController().navigate(R.id.action_catFormFragment_to_profileFragment)
                                    } else
                                        Toast.makeText(
                                            activity,
                                            R.string.cat_no_created,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                }
                            }
                        }
                    } else
                        errorTextView.visibility = View.VISIBLE
                }
            }
        }

        binding.addCatImageView.setOnClickListener {
            val gallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            gallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            gallery.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            startActivityForResult(gallery, REQUEST_CODE)
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
            binding.addCatImageView.setImageURI(imageUri)
        }
    }

    companion object {
        private const val REQUEST_CODE = 200
    }
}


