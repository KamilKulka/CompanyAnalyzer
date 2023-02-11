package com.kamilkulka.companyanalyzer.network

import com.kamilkulka.companyanalyzer.model.Customer
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CustomerApi {
    @GET("data")
    suspend fun getAllCustomers(): Customer
}