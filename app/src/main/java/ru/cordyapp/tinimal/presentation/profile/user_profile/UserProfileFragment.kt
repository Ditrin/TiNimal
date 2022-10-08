package ru.cordyapp.tinimal.presentation.profile.user_profile

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentProfileOtherBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.presentation.profile.ProfileAdapter
import ru.cordyapp.tinimal.utils.setStarByRating

@AndroidEntryPoint
class UserProfileFragment : Fragment(R.layout.fragment_profile_other) {
    private val binding by viewBinding(FragmentProfileOtherBinding::bind)
    private val viewModel: UserProfileViewModel by viewModels()
    private val profileAdapter = ProfileAdapter()
    private val args: UserProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.idOwner
        viewModel.getUsersListByLogin(id)

        viewModel.catsList.observe(viewLifecycleOwner) { user ->
            binding.catListRecyclerView.apply {
                adapter = profileAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                ResourcesCompat.getDrawable(resources, R.drawable.custom_decorator_rec, null)
                    ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
                addItemDecoration(dividerItemDecoration)
            }
            val list = user.cats?.map { CatMapper().map(it) }

            profileAdapter.setCatsList(list!!)

            with(binding) {
                nameTextViewProfile.text = user.name
                addressTextViewProfile.text = user.address
                reviewsCountTextViewProfile.text = user.ranking.toString()
                starImageButtonProfile.setStarByRating(user.ranking)
                reviewsCountTextViewProfile.text = user.feedbacks?.size.toString()
                starCountTextViewProfile.text = user.ranking.toString()

                Glide.with(this@UserProfileFragment)
                    .load("https://cordy-app.herokuapp.com/avatars/" + user.id.toString())
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarImageViewProfile)
            }
            binding.userProfileToolbar.callButton.setOnClickListener {
                call(user.phoneNumber.toString())
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