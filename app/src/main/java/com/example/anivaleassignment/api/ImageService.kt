package com.example.anivaleassignment.api

import com.example.anivaleassignment.models.ImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("api")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("q") searchTerm: String
    ): Response<ImageList>
}