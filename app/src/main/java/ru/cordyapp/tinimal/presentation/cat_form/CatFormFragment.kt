package ru.cordyapp.tinimal.presentation.cat_form

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAddDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatAvatarDTO
import ru.cordyapp.tinimal.databinding.FragmentCatFormBinding

@AndroidEntryPoint
class CatFormFragment : Fragment(R.layout.fragment_cat_form) {
    private val binding by viewBinding(FragmentCatFormBinding::bind)
    private val viewModel: CatFormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
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
                        val file = CatAvatarDTO(
                            imageView.toString()

                        )
                        val cat = CatAddDTO(
                            viewModel.isMale.toString().toBoolean(),
                            breedEditTextCatFormFragment.text.toString(),
                            nameEditTextCatFormFragment.text.toString(),
                            ageEditTextCatFormFragment.text.toString().toInt(),
                            priceEditTextCatFormFragment.text.toString().toInt(),
                            viewModel.isPassport.toString().toBoolean(),
                            viewModel.isVaccination.toString().toBoolean(),
                            viewModel.isCertificate.toString().toBoolean(),
                            catStoryEditTextCatFormFragment.text.toString(),
                            file.toString()
                        )
                        viewModel.addCat(cat, id.toLong(), file)
                    } else
                        errorTextView.visibility = View.VISIBLE
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

    }
}


