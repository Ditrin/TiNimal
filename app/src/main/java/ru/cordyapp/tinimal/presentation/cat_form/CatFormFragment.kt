package ru.cordyapp.tinimal.presentation.cat_form

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentCatFormBinding
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.presentation.profile.ProfileViewModel

@AndroidEntryPoint
class CatFormFragment: Fragment(R.layout.fragment_cat_form) {
    private val binding by viewBinding(FragmentCatFormBinding::bind)
    private val viewModel: CatFormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.isCertificate.observe(viewLifecycleOwner) { isCertificate->
            binding.certificatesCheckBox.setOnClickListener {
                viewModel.setCertificate()
                if (isCertificate)
                    binding.certificatesCheckBox.isChecked = isCertificate
                else
                    binding.certificatesCheckBox.isChecked = !isCertificate
            }
            Log.d("ass", "${viewModel.isCertificate.value}")

        }
//
//        viewModel.isPassport.observe(viewLifecycleOwner) { isPassport->
//            binding.passportCheckBox.setOnClickListener {
//                viewModel.setPassport()
//                if (isPassport)
//                    binding.passportCheckBox.setBackgroundResource(R.drawable.custom_square_fill)
//                else
//                    binding.passportCheckBox.setBackgroundResource(R.drawable.custom_square)
//            }
//        }
//
//        viewModel.isVaccination.observe(viewLifecycleOwner) { isVaccination->
//            binding.vaccinationCheckBox.setOnClickListener {
//                viewModel.setVaccination()
//                if (isVaccination)
//                    binding.vaccinationCheckBox.setBackgroundResource(R.drawable.custom_square_fill)
//                else
//                    binding.vaccinationCheckBox.setBackgroundResource(R.drawable.custom_square)
//            }
//        }
//
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

    }
}


