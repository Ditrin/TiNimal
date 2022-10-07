package ru.cordyapp.tinimal.presentation.cat_profile

import android.os.Bundle
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
import ru.cordyapp.tinimal.databinding.FragmentCatProfileBinding
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.presentation.profile.ProfileViewModel
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class CatProfileFragment : Fragment(R.layout.fragment_cat_profile) {
    private val binding by viewBinding(FragmentCatProfileBinding::bind)
    private val viewModel: CatProfileViewModel by viewModels()
    private val args: CatProfileFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.cat.id
        viewModel.getCat(id)
        viewModel.cat.observe(viewLifecycleOwner) {
            with(binding) {
                nameProfileFragment.text = it.name
                breedProfileFragment.text = it.breed
                ageCatProfileFragment.text = it.age.toString()
                if (it.sex)
                    sexImageView.setImageResource(R.drawable.ic_female)
                else
                    sexImageView.setImageResource(R.drawable.ic_male)
                priceProfileFragment.text = it.price.toString()
                addressProfileFragment.text = it.owner_address
                infoTextView.text = it.info
                nameOwnerTextView.text = it.owner_name
                rankingCountTextView.text = it.owner_ranking.toString()
                reviewCountTextView.text = it.count_feedback.toString()
                Glide.with(this@CatProfileFragment)
                    .load(it.photo)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarCatProfile)
                toolbarCatProfile.backPressButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
                toolbarCatProfile.kudosButton.setOnClickListener {

                }
                toolbarCatProfile.callButton.setOnClickListener { }
            }
        }
    }
}