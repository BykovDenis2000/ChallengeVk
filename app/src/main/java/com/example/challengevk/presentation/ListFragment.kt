package com.example.challengevk.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengevk.R
import com.example.challengevk.data.model.VkService
import com.example.challengevk.databinding.FragmentListBinding
import com.example.challengevk.utils.NetworkResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: Fragment(R.layout.fragment_list), OnItemClickListener {

    private val viewModel: ListViewModel by viewModels()
    private var retrySnackbar: Snackbar? = null

    override fun onItemClick(service: VkService) {
        val direction = ListFragmentDirections.actionListFragmentToDetailFragment(service)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentListBinding.bind(view)
        val serviceAdapter = VkServiceAdapter(this)

        with(binding.serviceRecycler) {
            setHasFixedSize(true)
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.vkServices.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error<*> -> {
                    retrySnackbar?.dismiss()
                    retrySnackbar = Snackbar.make(
                        requireView(),
                        it.message ?: "Ошибка",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Ещё раз") {
                        viewModel.onRetryClicked()
                    }.also { snackbar ->
                        snackbar.show()
                    }
                }
                is NetworkResult.Success<*> -> {
                    serviceAdapter.submitList(it.data.items)
                }
                is NetworkResult.Loading<*> -> Unit
            }
        }
    }
}