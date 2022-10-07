package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentFeedbackProfileBinding
import ru.cordyapp.tinimal.domain.mapper.FeedbackMapper
import ru.cordyapp.tinimal.presentation.feedbacks.FeedbackAdapter
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class FeedbackProfileFragment : Fragment(R.layout.fragment_feedback_profile) {
    private val viewModel: FeedbackProfileViewModel by viewModels()
    private val binding by viewBinding(FragmentFeedbackProfileBinding::bind)
    private val feedbackAdapter = FeedbackAdapter()
    private val id = SharedPref.id ?: -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFeedbacks(id)
        viewModel.feedbackList.observe(viewLifecycleOwner) {
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
    }
}