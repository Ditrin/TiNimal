package ru.cordyapp.tinimal.presentation.cat_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentCatProfileBinding
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.presentation.profile.ProfileViewModel
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class CatProfileFragment: Fragment(R.layout.fragment_cat_profile) {
    private val binding by viewBinding(FragmentCatProfileBinding::bind)
    private val viewModel: CatProfileViewModel by viewModels()
    private val id = SharedPref.id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCat(id!!)
        viewModel.cat.observe(viewLifecycleOwner){
            with(binding){
                nameProfileFragment.text = it.name
                breedProfileFragment.text = it.breed
                ageCatProfileFragment.text = it.age.toString()

            }
        }
    }
}