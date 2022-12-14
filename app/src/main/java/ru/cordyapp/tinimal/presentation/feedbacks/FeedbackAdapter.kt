package ru.cordyapp.tinimal.presentation.feedbacks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.ItemAdBinding
import ru.cordyapp.tinimal.databinding.ItemReviewBinding
import ru.cordyapp.tinimal.domain.models.CatShort
import ru.cordyapp.tinimal.domain.models.FeedbackShort
import ru.cordyapp.tinimal.presentation.profile.ProfileAdapter
import ru.cordyapp.tinimal.utils.setStarByRating

class FeedbackAdapter : RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {
    private var feedbacks: List<FeedbackShort> = emptyList()



    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feedbackShort: FeedbackShort) {
            binding.nameTextView.text = feedbackShort.name
            binding.textTextView.text = feedbackShort.info
            binding.dataTextView.text = feedbackShort.date
            binding.starCountTextViewProfile2.text = feedbackShort.rating.toString()
            binding.starImageViewReviews.setImageResource(countRating(feedbackShort.rating))
            Glide.with(itemView)
                .load(feedbackShort.photo)
                .transform(CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.avatarImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(feedbacks[position])
    }

    override fun getItemCount() = feedbacks.size

    fun setFeedbackList(feedbacks: List<FeedbackShort>) {
        this.feedbacks = feedbacks
    }
    private fun countRating(raiting: Float?) =
        when (raiting?.toInt()) {
            1 -> R.drawable.ic_star1
            2 -> R.drawable.ic_star2
            3 -> R.drawable.ic_star3
            4 -> R.drawable.ic_star4
            5 -> R.drawable.ic_star5
            else -> R.drawable.ic_star_empty
        }
}