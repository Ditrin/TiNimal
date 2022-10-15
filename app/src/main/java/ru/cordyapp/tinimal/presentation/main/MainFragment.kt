package ru.cordyapp.tinimal.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.CatsDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.SearchDTO
import ru.cordyapp.tinimal.databinding.FragmentSearchBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.utils.Filter

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: MainViewModel by viewModels()

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val mainAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filter = Filter.listCat

        if (filter != null)
            viewModel.setCatsList(filter)
        else
            viewModel.getUsersListByLogin()

        binding.searchImageButton.setOnClickListener {
            val searchText = SearchDTO(binding.searchEditText.text.toString())
            viewModel.postSearch(searchText)
        }

        viewModel.catsList.observe(viewLifecycleOwner) {
            binding.adsListRecyclerView.apply {
                adapter = mainAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                ResourcesCompat.getDrawable(resources, R.drawable.custom_decorator_rec, null)
                    ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
                addItemDecoration(dividerItemDecoration)
            }

            val list = it.map { CatMapper().map(it) }
            mainAdapter.setCatsList(list)
            mainAdapter.setOnClickListener { cat ->
                Log.d("ID_TAG", it.toString())
                val bundle = Bundle().apply {
                    putLong("cat", cat.id)
                }
                findNavController().navigate(
                    R.id.action_mainFragment_to_catProfileFragment,
                    bundle
                )
            }
        }
        binding.filterImageButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
        }
    }
}