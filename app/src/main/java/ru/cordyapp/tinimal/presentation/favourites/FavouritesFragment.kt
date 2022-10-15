package ru.cordyapp.tinimal.presentation.favourites

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
import ru.cordyapp.tinimal.databinding.FragmentFavoritesBinding
import ru.cordyapp.tinimal.databinding.FragmentSearchBinding
import ru.cordyapp.tinimal.domain.mapper.CatMapper
import ru.cordyapp.tinimal.presentation.main.MainAdapter
import ru.cordyapp.tinimal.presentation.main.MainViewModel
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favorites) {
    private val viewModel: FavouritesViewModel by viewModels()

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val mainAdapter = MainAdapter()
    private val id = SharedPref.id ?: -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCats(id)
        viewModel.catsList.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.noPetsTextView.visibility = View.VISIBLE
            else
                binding.noPetsTextView.visibility = View.GONE
            binding.catFavoritesListRecyclerView.apply {
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
                    R.id.action_favouritesFragment_to_catProfileFragment,
                    bundle
                )
            }
        }
        binding.appBarInfo.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}