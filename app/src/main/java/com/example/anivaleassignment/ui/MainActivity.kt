package com.example.anivaleassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.anivaleassignment.api.ImageService
import com.example.anivaleassignment.api.RetrofitHelper
import com.example.anivaleassignment.databinding.ActivityMainBinding
import com.example.anivaleassignment.repository.ImageRepository
import com.example.anivaleassignment.viewmodel.MainViewModel
import com.example.anivaleassignment.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvAdapter = ImageAdapter(this)
        binding.imageRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = rvAdapter
        }

        val imageService = RetrofitHelper.getInstance().create(ImageService::class.java)
        val repository = ImageRepository(imageService)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.images.observe(this) {
            rvAdapter.setImageList(it)
        }

        searchImage()
    }

    private fun searchImage() {
        val searchView = binding.imageSearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(image: String?): Boolean {
                if (!image.isNullOrBlank()) {
                    mainViewModel.setSearchQuery(image)
                    mainViewModel.getImages()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}