package com.example.challengevk.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VkService(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon_url")
    @Expose
    val iconUrl: String,
    @SerializedName("service_url")
    @Expose
    val serviceUrl: String
)

//fun VkService.toDomainObject() = VkService(
//    description = description,
//    iconUrl = iconUrl,
//    name = name,
//    serviceUrl = serviceUrl
//)