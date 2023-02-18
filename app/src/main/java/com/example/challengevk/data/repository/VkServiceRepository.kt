package com.example.challengevk.data.repository

import com.example.challengevk.data.model.VkServicesList

interface VkServiceRepository {
    suspend fun getVkServices(): VkServicesList
}