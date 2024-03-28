package com.example.anivaleassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.anivaleassignment.databinding.ActivityEnlargedImageBinding

class EnlargedImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityEnlargedImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnlargedImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra("imageUrl")
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imageView)
    }
}