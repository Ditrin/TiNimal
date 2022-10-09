package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.FragmentFeedbackNewBinding
import ru.cordyapp.tinimal.presentation.cat_profile.CatProfileFragmentArgs
import ru.cordyapp.tinimal.utils.SharedPref

class FeedbackNewFragment: Fragment(R.layout.fragment_feedback_new) {
  //  private val viewModel: FeedbackNewViewModel by viewModels()
    private val binding by viewBinding(FragmentFeedbackNewBinding::bind)
    private val args: FeedbackNewFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.idUser





    }
}