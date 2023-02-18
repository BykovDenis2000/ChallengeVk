package com.example.challengevk.data.api

import com.example.challengevk.data.model.VkServicesList
import retrofit2.http.GET

interface ApiService {
    @GET(value = "/semi-final-data.json")
    suspend fun getVkServicesList(): VkServicesList
}