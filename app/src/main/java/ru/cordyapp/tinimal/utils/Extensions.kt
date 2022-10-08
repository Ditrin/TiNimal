package ru.cordyapp.tinimal.utils

import android.widget.ImageButton
import ru.cordyapp.tinimal.R


    fun ImageButton.setStarByRating(value: Float?) {
        val res = when (value?.toInt()) {
            1 -> R.drawable.ic_star1
            2 -> R.drawable.ic_star2
            3 -> R.drawable.ic_star3
            4 -> R.drawable.ic_star4
            5 -> R.drawable.ic_star5
            else -> R.drawable.ic_star_empty
        }
        setImageResource(res)
    }