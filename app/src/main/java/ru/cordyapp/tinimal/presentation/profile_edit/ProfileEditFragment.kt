package ru.cordyapp.tinimal.presentation.profile_edit

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.databinding.FragmentProfileEditBinding
import ru.cordyapp.tinimal.presentation.profile.ProfileViewModel
import java.io.File

@AndroidEntryPoint
class ProfileEditFragment : Fragment(R.layout.fragment_profile_edit) {
    private val binding by viewBinding(FragmentProfileEditBinding::bind)
    private val args: ProfileEditFragmentArgs by navArgs()
    private val viewModel: ProfileEditViewModel by viewModels()
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var file: File? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.userId

        loadUser(user)

        with(binding) {
            plusImageButtonProfileEdit.setOnClickListener {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
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
                file = File(viewModel.pathImage.toString())
                viewModel.update(nameEditTextProfileEdit.text.toString(), user.id, file!!)

            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageUri?.let { viewModel.saveImagePath(it) }
            binding.avatarImageViewProfileEdit.setImageURI(imageUri)
        }
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
}