package ru.cordyapp.tinimal.presentation.filter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.FilterDTO
import ru.cordyapp.tinimal.databinding.FragmentParametersBinding
import ru.cordyapp.tinimal.utils.Filter

@AndroidEntryPoint
class FilterFragment : Fragment(R.layout.fragment_parameters) {
    private val binding by viewBinding(FragmentParametersBinding::bind)
    private val viewModel: FilterViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonShow.setOnClickListener {

                val address = if (addressEditTextParameterFragment.text.toString() != "")
                    addressEditTextParameterFragment.text.toString() else null

                val breed = if (breedEditTextParameterFragment.text.toString() != "")
                    breedEditTextParameterFragment.text.toString() else null

                val age = if (ageEditTextParameterFragment.text.toString() != "")
                    ageEditTextParameterFragment.text.toString().toInt() else null

                val priceFrom = if (priceStartEditText.text.toString() != "")
                    priceStartEditText.text.toString().toInt() else null

                val priceTo = if (priceEndEditText.text.toString() != "")
                    priceEndEditText.text.toString().toInt() else null

                val filter = FilterDTO(
                    address = address,
                    breed = breed,
                    age = age,
                    sex = femaleCheckBox.isChecked,
                    passport = passportCheckBox.isChecked,
                    vaccination = vaccinationCheckBox.isChecked,
                    certificates = certificatesCheckBox.isChecked,
                    priceFrom = priceFrom,
                    priceTo = priceTo
                )
                viewModel.postFilter(filter)
                viewModel.isSuccess.observe(viewLifecycleOwner) {
                    if (it) {
                        viewModel.listCat.observe(viewLifecycleOwner) { list ->
                            Filter.listCat = list
                            findNavController().navigate(R.id.action_filterFragment_to_mainFragment)
                        }
                    } else
                        Toast.makeText(
                            requireContext(),
                            R.string.error_try_again_later,
                            Toast.LENGTH_SHORT
                        ).show()
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
        }
    }
}