package ru.cordyapp.tinimal.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.databinding.FragmentSearchBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.domain.models.CatShort

@AndroidEntryPoint
class MainFragment:Fragment(R.layout.fragment_search) {
    private val viewModel: MainViewModel by viewModels()

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val mainAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.catsList.observe(viewLifecycleOwner){
            binding.adsListRecyclerView.apply {
                adapter = mainAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            val list = it.map { CatMapper().map(it) }
//            val list = mutableListOf<CatShort>()
//            it.forEach {
//                list.add(CatMapper().map(it))
//            }
            mainAdapter.setCatsList(list)

        }
    }
}