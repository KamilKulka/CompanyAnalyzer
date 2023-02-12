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
import java.time.LocalDate
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
            repositoryData.value = repository.getAllCustomers()

            if (repositoryData.value.data.toString().isNotEmpty()) repositoryData.value.loading =
                false

        }
    }

    fun getEarliestCustomer(): List<CustomerItem> {

        var earliestDate: LocalDate? = null
        repositoryData.value.data?.let {
            for (customerItem in it) {
                customerItem.lastCheckInDate?.let { checkInDate ->
                    if (earliestDate == null || checkInDate < earliestDate) {
                        earliestDate = checkInDate
                    }
                }
            }
        } ?: return emptyList()

        if (earliestDate == null) return emptyList()

        return repositoryData.value.data?.let { it ->
            it.filter { customerItem -> customerItem.lastCheckInDate == earliestDate }
        } ?: emptyList()
    }

    fun getLatestCustomer(): List<CustomerItem> {

        var latestDate: LocalDate? = null
        repositoryData.value.data?.let {
            for (customerItem in it) {
                customerItem.lastCheckInDate?.let { checkInDate ->
                    if (latestDate == null || checkInDate > latestDate) {
                        latestDate = checkInDate
                    }
                }
            }
        } ?: return emptyList()

        if (latestDate == null) return emptyList()

        return repositoryData.value.data?.let { it ->
            it.filter { customerItem -> customerItem.lastCheckInDate == latestDate }
        } ?: emptyList()
    }
}