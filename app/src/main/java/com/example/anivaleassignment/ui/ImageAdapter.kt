package com.example.anivaleassignment.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anivaleassignment.databinding.ImageItemBinding
import com.example.anivaleassignment.models.Hit
import com.example.anivaleassignment.models.ImageList

class ImageAdapter(private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var images: List<Hit> = listOf()

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val imageUrl = images[position].webformatURL
            Glide.with(context)
                .load(imageUrl)
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(context, EnlargedImageActivity::class.java).apply {
                    putExtra("imageUrl", imageUrl)
                }
                context.startActivity(intent)
            }
        }
    }

    fun setImageList(imageList: ImageList) {
        images = imageList.hits
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(position)
    }
}