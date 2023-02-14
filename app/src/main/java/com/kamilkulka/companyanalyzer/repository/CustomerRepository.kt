package com.kamilkulka.companyanalyzer.repository

import com.kamilkulka.companyanalyzer.data.DataOrException
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.network.CustomerApi
import timber.log.Timber
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val customerApi: CustomerApi) {

    private val dataOrException = DataOrException<ArrayList<CustomerItem>, Boolean, Exception>()

    suspend fun getAllCustomers(): DataOrException<ArrayList<CustomerItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = customerApi.getAllCustomers()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        } catch (e: Exception) {
            dataOrException.loading = false
            dataOrException.e = e
            Timber.d("Exception: ${dataOrException.e!!.message}")
        }
        return dataOrException
    }
}