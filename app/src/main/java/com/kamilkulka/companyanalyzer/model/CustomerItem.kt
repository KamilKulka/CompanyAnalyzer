package com.kamilkulka.companyanalyzer.model

import java.time.LocalDate

data class CustomerItem(
    var firstName: String? = null,
    var lastName: String? = null,
    var job: String? = null,
    var company: String? = null,
    var city: String? = null,
    var street: String? = null,
    var type: String? = null,
    var zip: String? = null,
    var phone: Int? = null,
    var lastCheckInDate: LocalDate? = null,
)