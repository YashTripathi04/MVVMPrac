package com.example.anivaleassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anivaleassignment.BuildConfig
import com.example.anivaleassignment.models.ImageList
import com.example.anivaleassignment.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ImageRepository) : ViewModel() {

    private val API_KEY = BuildConfig.imagesApiKey

    init {
        getImages()
    }

    private var imageSearchQuery: String = "City"
    fun setSearchQuery(query: String) {
        imageSearchQuery = query
    }

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getImages(API_KEY, imageSearchQuery)
        }
    }

    val images: LiveData<ImageList>
        get() = repository.images
}