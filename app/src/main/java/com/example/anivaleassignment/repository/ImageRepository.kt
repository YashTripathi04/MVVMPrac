package com.example.anivaleassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anivaleassignment.api.ImageService
import com.example.anivaleassignment.models.ImageList

class ImageRepository(private val imageService: ImageService) {

    private val imageLiveData = MutableLiveData<ImageList>()
    val images: LiveData<ImageList>
        get() = imageLiveData

    suspend fun getImages(key: String, searchTerm: String) {
        val result = imageService.getImages(key, searchTerm)
        if (result?.body() != null) {
            imageLiveData.postValue(result.body())
        }
    }
}