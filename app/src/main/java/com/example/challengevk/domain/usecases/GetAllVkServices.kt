package com.example.challengevk.domain.usecases

import com.example.challengevk.data.repository.VkServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllVkServices @Inject constructor(private val vkServiceRepository: VkServiceRepository) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        vkServiceRepository.getVkServices()
    }
}