package com.kamilkulka.companyanalyzer.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.companyanalyzer.data.DataOrException
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    private val repositoryData: MutableState<DataOrException<ArrayList<CustomerItem>,
            Boolean, Exception>> = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getDataFromRepository()
    }

    private fun getDataFromRepository() {
        viewModelScope.launch {
            repositoryData.value.loading = true
            Timber.d("Getting data from repository...")
            repositoryData.value = repository.getAllCustomers()

            if (repositoryData.value.data.toString().isNotEmpty()) repositoryData.value.loading =
                false

        }
    }
}