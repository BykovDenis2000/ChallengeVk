package com.example.challengevk.data.model

import com.google.gson.annotations.SerializedName

data class VkServicesList(
    @SerializedName("items")
    val items: List<VkService>
)
