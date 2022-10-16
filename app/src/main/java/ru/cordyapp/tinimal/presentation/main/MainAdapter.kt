package ru.cordyapp.tinimal.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.ItemAdBinding
import ru.cordyapp.tinimal.databinding.ItemCatsBinding
import ru.cordyapp.tinimal.domain.models.CatShort

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var cats: List<CatShort> = emptyList()

    inner class ViewHolder(private val binding: ItemAdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatShort) {
            with(binding) {
                priceTextView.text = cat.price.toString()
                if (cat.sex) sexImageView.setImageResource(R.drawable.ic_female)
                else sexImageView.setImageResource(R.drawable.ic_male)
                nameTextView.text = cat.name
                breedTextView.text = cat.breed
                Glide.with(itemView)
                    .load(cat.photo)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAdBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(cats[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(cats[position]) }
        }
    }

    private var onItemClickListener: ((CatShort) -> Unit)? = null

    override fun getItemCount(): Int = cats.size

    fun setOnClickListener(listener: (CatShort) -> Unit) {
        onItemClickListener = listener
    }

    fun setCatsList(cats: List<CatShort>) {
        this.cats = cats
    }

}