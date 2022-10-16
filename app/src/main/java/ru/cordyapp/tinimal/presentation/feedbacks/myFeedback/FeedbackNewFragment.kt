package ru.cordyapp.tinimal.presentation.feedbacks.myFeedback

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.FeedbackNewDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserEditDTO
import ru.cordyapp.tinimal.databinding.FragmentFeedbackNewBinding
import ru.cordyapp.tinimal.presentation.cat_profile.CatProfileFragmentArgs
import ru.cordyapp.tinimal.utils.DateForm
import ru.cordyapp.tinimal.utils.SharedPref
import android.util.Log

@AndroidEntryPoint
class FeedbackNewFragment : Fragment(R.layout.fragment_feedback_new) {
    private val viewModel: FeedbackNewViewModel by viewModels()
    private val binding by viewBinding(FragmentFeedbackNewBinding::bind)
    private val args: FeedbackNewFragmentArgs by navArgs()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.idUser
        var count = 1

        with(binding) {
            val list = arrayListOf<ImageView>(
                star1ImageView,
                star2ImageView,
                star3ImageView,
                star4ImageView,
                star5ImageView
            )
            for (i in 0 until list.size) {
                list[i].setOnClickListener {
                    viewModel.setRating(i + 1)
                    fillStar(i + 1, list)
                }
            }

            reviewsButton.setOnClickListener {
                val feedbackNew = FeedbackNewDTO(
                    userId = SharedPref.id!!,
                    date = DateForm.dateParse(System.currentTimeMillis()),
                    text.text.toString(),
                    viewModel.rating.value!!
                )
                viewModel.getFeedbacks(id, feedbackNew)
                viewModel.isSuccess.observe(viewLifecycleOwner){
                    if(it){
                        requireActivity().onBackPressed()
                    }
                }
            }

        }
        binding.appBarInfo.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    fun fillStar(count: Int, list: List<ImageView>) {
        for (i in 0 until count)
            list[i].setImageResource(R.drawable.ic_star5)
        for (i in count..4)
            list[i].setImageResource(R.drawable.ic_star0)
    }
}