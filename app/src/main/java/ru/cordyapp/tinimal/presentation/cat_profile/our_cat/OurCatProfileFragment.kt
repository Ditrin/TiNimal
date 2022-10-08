package ru.cordyapp.tinimal.presentation.cat_profile.our_cat

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
import ru.cordyapp.tinimal.databinding.FragmentCatOurProfileBinding

@AndroidEntryPoint
class OurCatProfileFragment: Fragment(R.layout.fragment_cat_our_profile) {
    private val binding by viewBinding(FragmentCatOurProfileBinding::bind)
    private val viewModel: OurCatProfileViewModel by viewModels()
    private val args: OurCatProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.ourCatId
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
                Glide.with(this@OurCatProfileFragment)
                    .load(catInfo.photo)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(photoCatProfileFragment)
                ourCatToolbar.backPressButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
        }
    }
}