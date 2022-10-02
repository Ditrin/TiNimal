package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentFeedbackProfileBinding
import ru.cordyapp.tinimal.presentation.feedbacks.FeedbackAdapter

@AndroidEntryPoint
class FeedbackProfileFragment: Fragment(R.layout.fragment_feedback_profile) {
    private val viewModel: FeedbackProfileViewModel by viewModels()
    private val binding by viewBinding(FragmentFeedbackProfileBinding::bind)
    private val feedbackAdapter = FeedbackAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}