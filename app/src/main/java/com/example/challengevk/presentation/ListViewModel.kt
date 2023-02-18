package com.example.challengevk.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengevk.data.model.VkServicesList
import com.example.challengevk.domain.usecases.GetAllVkServices
import com.example.challengevk.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getVkServicesListUseCase: GetAllVkServices
) : ViewModel() {

    private val _vkServices = MutableLiveData<NetworkResult<VkServicesList>>(NetworkResult.Loading()).apply {
        viewModelScope.launch(Dispatchers.Main) {
            value = loadServices()
        }
    }

    val vkServices: LiveData<NetworkResult<VkServicesList>> = _vkServices

    private suspend fun loadServices() = try {
        val services = getVkServicesListUseCase()
        NetworkResult.Success(services)
    } catch (e: HttpException) {
        NetworkResult.Error("Ошибка запроса")
    } catch (e: IOException) {
        NetworkResult.Error("Отсутствует подключение")
    }

    fun onRetryClicked() = viewModelScope.launch(Dispatchers.Main) {
        _vkServices.value = loadServices()
    }
}