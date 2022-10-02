package ru.cordyapp.tinimal.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.presentation.main.MainAdapter
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels()
    private val profileAdapter = ProfileAdapter()
    private val id: Long = SharedPref.id ?: -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        if (id.toInt() != -1)
            viewModel.getUsersListByLogin(id)
        viewModel.catsList.observe(viewLifecycleOwner) {
            binding.catsByUserListRecycleView.apply {
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
            val list = it.cats?.map { CatMapper().map(it) }

            profileAdapter.setCatsList(list!!)

            with(binding) {
                nameTextViewProfile.text = it.name
                mailTextViewProfile.text = it.mail
                phoneTextViewProfile.text = it.phoneNumber.toString()
                addressTextViewProfile.text = it.address
                reviewsCountTextViewProfile.text = it.ranking.toString()
                starImageButtonProfile.setImageResource(countRating(it.ranking))
                reviewsCountTextViewProfile.text = it.feedbacks?.size.toString()
                starCountTextViewProfile.text = it.ranking.toString()

                Glide.with(this@ProfileFragment)
                    .load(it.avatar)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarImageViewProfile)
            }

            binding.plusImageViewProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_catFormFragment)
            }
        }
        binding.toolbar.logoutButton.setOnClickListener {
            SharedPref.id = null
            SharedPref.authToken = null
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun countRating(raiting: Float?) =
        when (raiting?.toInt()) {
            1 -> R.drawable.ic_star1
            2 -> R.drawable.ic_star2
            3 -> R.drawable.ic_star3
            4 -> R.drawable.ic_star4
            5 -> R.drawable.ic_star5
            else -> R.drawable.ic_star_empty
        }
}