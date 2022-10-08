package ru.cordyapp.tinimal.presentation.feedbacks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentFeedbackOtherBinding

@AndroidEntryPoint
class FeedbackOtherFragment: Fragment(R.layout.fragment_feedback_other) {
    private val viewModel: FeedbackOtherViewModel by viewModels()
    private val binding by viewBinding(FragmentFeedbackOtherBinding::bind)
    private val feedbackAdapter = FeedbackAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.getFeedbacks(id)
    }
}