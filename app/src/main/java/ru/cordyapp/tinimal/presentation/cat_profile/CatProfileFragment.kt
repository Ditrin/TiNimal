package ru.cordyapp.tinimal.presentation.cat_profile

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
import ru.cordyapp.tinimal.utils.setStarByRating

@AndroidEntryPoint
class CatProfileFragment : Fragment(R.layout.fragment_cat_profile) {
    private val binding by viewBinding(FragmentCatProfileBinding::bind)
    private val viewModel: CatProfileViewModel by viewModels()
    private val args: CatProfileFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.cat
        Log.d("ID_TAG", id.toString())
        viewModel.getCat(id)
        viewModel.cat.observe(viewLifecycleOwner) {catInfo ->
            with(binding) {
                nameProfileFragment.text = catInfo.name
                breedProfileFragment.text = catInfo.breed
                ageCatProfileFragment.text = catInfo.age.toString()
                if (catInfo.sex)
                    sexImageView.setImageResource(R.drawable.ic_female)
                else
                    sexImageView.setImageResource(R.drawable.ic_male)
                priceProfileFragment.text = catInfo.price.toString()
                addressProfileFragment.text = catInfo.owner_address
                infoTextView.text = catInfo.info
                Glide.with(this@CatProfileFragment)
                    .load(catInfo.photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(photoCatProfileFragment)
                nameOwnerTextView.text = catInfo.owner_name
                rankingCountTextView.text = catInfo.owner_ranking.toString()
                reviewCountTextView.text = catInfo.count_feedback.toString()
                Glide.with(this@CatProfileFragment)
                    .load("https://cordy-app.herokuapp.com/avatars/" + catInfo.owner_id.toString())
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarCatProfile)
                toolbarCatProfile.backPressButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
                rankingCatProfile.setStarByRating(catInfo.owner_ranking)
                toolbarCatProfile.callButton.setOnClickListener { }
                avatarCatProfile.setOnClickListener {
                    val bundle = Bundle().apply {
                        putLong("id_owner", catInfo.owner_id)
                    }
                    findNavController().navigate(R.id.action_catProfileFragment_to_userProfileFragment, bundle)
                }
                toolbarCatProfile.callButton.setOnClickListener {
                    call(catInfo.owner_phoneNumber.toString())
                }

            }

            binding.reviewCatProfile.setOnClickListener {
                findNavController().navigate(R.id.action_catProfileFragment_to_feedbackOtherFragment)
            }
        }
    }

    private fun call(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),
                0
            )
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }
}