package com.example.challengevk.domain.repository

import com.example.challengevk.data.api.ApiService
import com.example.challengevk.data.model.VkServicesList
import com.example.challengevk.data.repository.VkServiceRepository
import javax.inject.Inject

class VkServiceRepositoryImpl @Inject constructor(private val api: ApiService) :
    VkServiceRepository {
    override suspend fun getVkServices(): VkServicesList = api.getVkServicesList()
}