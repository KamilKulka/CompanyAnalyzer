package com.kamilkulka.companyanalyzer.data

data class DataOrException<T,Boolean,E>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null)
