package ru.cordyapp.tinimal.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.databinding.FragmentProfileBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.presentation.main.MainAdapter

class ProfileFragment: Fragment(R.layout.fragment_profile) {
//    private val binding by viewBinding(FragmentProfileBinding::bind)
//    private val viewModel: ProfileViewModel by viewModels()
//    private val profileAdapter = ProfileAdapter()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.getUsersListByLogin()
//        viewModel.catsList.observe(viewLifecycleOwner){
//            binding.catsByUserListRecycleView.apply {
//                adapter = profileAdapter
//                layoutManager = LinearLayoutManager(requireContext())
//                val dividerItemDecoration = DividerItemDecoration(
//                    context,
//                    (layoutManager as LinearLayoutManager).orientation
//                )
//                addItemDecoration(dividerItemDecoration)
//            }
//            val list = it.cats.map
////            val list = mutableListOf<CatShort>()
////            it.forEach {
////                list.add(CatMapper().map(it))
////            }
//            profileAdapter.setCatsList(list)
//
//        }
//
//        with(binding){
//
//
//
//
//            Glide.with(this@ProfileFragment)
//                .load(Use)
//                .transform(CircleCrop())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageCharacter)
//        }
//
//    }
}