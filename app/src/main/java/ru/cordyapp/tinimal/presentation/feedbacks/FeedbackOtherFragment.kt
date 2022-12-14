package ru.cordyapp.tinimal.presentation.feedbacks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewDebug
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentFeedbackOtherBinding
import ru.cordyapp.tinimal.domain.mapper.FeedbackMapper
import ru.cordyapp.tinimal.utils.SharedPref

    @AndroidEntryPoint
    class FeedbackOtherFragment: Fragment(R.layout.fragment_feedback_other) {
        private val viewModel: FeedbackOtherViewModel by viewModels()
        private val binding by viewBinding(FragmentFeedbackOtherBinding::bind)
        private val feedbackAdapter = FeedbackAdapter()
        private val args: FeedbackOtherFragmentArgs by navArgs()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val id = args.idUser
            viewModel.getFeedbacks(id)
            viewModel.feedbackList.observe(viewLifecycleOwner){
                if (it.isEmpty()){
                    binding.noReviews.visibility = View.VISIBLE
                }
                else
                    binding.noReviews.visibility = View.GONE
                binding.reviewsRecycleView.apply {
                    adapter = feedbackAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    val dividerItemDecoration = DividerItemDecoration(
                        context,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                    ResourcesCompat.getDrawable(resources, R.drawable.custom_decorator_feedback, null)
                        ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
                    addItemDecoration(dividerItemDecoration)
                }
                val list = it.map { FeedbackMapper().map(it) }
                feedbackAdapter.setFeedbackList(list)
            }
            binding.appBarInfo.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }

            binding.reviewsButton.setOnClickListener {
                val bundle = Bundle().apply {
                    putLong("idUser", id)
                }
                Log.d("TAG_FEEDBACK", id.toString())

                findNavController().navigate(R.id.action_feedbackOtherFragment_to_feedbackNewFragment, bundle)
            }
        }
    }
