package com.kamilkulka.companyanalyzer.di

import com.google.gson.GsonBuilder
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.network.CustomerApi
import com.kamilkulka.companyanalyzer.network.CustomerItemDeserializer
import com.kamilkulka.companyanalyzer.repository.CustomerRepository
import com.kamilkulka.companyanalyzer.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCustomerApi(): CustomerApi {
        val customerItemDeserializer =
            GsonBuilder().registerTypeAdapter(CustomerItem::class.java, CustomerItemDeserializer())
                .create()

        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create(customerItemDeserializer))
            .build()
            .create(CustomerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomerRepository(api: CustomerApi) = CustomerRepository(api)
}