package com.kamilkulka.companyanalyzer.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.util.convertToDate
import com.kamilkulka.companyanalyzer.util.separateInt
import java.lang.reflect.Type

class CustomerItemDeserializer : JsonDeserializer<CustomerItem> {
    companion object {
        const val FIRST_NAME = "First Name"
        const val LAST_NAME = "Last Name"
        const val JOB = "Job"
        const val COMPANY = "Company"
        const val CITY = "City"
        const val STREET = "Street"
        const val TYPE = "Type"
        const val ZIP = "Zip"
        const val PHONE = "Phone"
        const val LAST_CHECK_IN_DATE = "Last Check-In Date"
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CustomerItem {
        val jsonObject = json.asJsonObject
        val customerItem = CustomerItem()

        verifyJsonAndPerform(jsonObject[FIRST_NAME]) {
            customerItem.firstName = jsonObject[FIRST_NAME].asString
        }
        verifyJsonAndPerform(jsonObject[LAST_NAME]) {
            customerItem.lastName = jsonObject[LAST_NAME].asString
        }
        verifyJsonAndPerform(jsonObject[JOB]) {
            customerItem.job = jsonObject[JOB].asString
        }
        verifyJsonAndPerform(jsonObject[COMPANY]) {
            customerItem.company = jsonObject[COMPANY].asString
        }
        verifyJsonAndPerform(jsonObject[CITY]) {
            customerItem.city = jsonObject[CITY].asString
        }
        verifyJsonAndPerform(jsonObject[STREET]) {
            customerItem.street = jsonObject[STREET].asString
        }
        verifyJsonAndPerform(jsonObject[TYPE]) {
            customerItem.type = jsonObject[TYPE].asString
        }
        verifyJsonAndPerform(jsonObject[ZIP]) {
            customerItem.zip = jsonObject[ZIP].asString
        }
        verifyJsonAndPerform(jsonObject[PHONE]) {
            customerItem.phone = jsonObject[PHONE].asString.separateInt()
        }
        verifyJsonAndPerform(jsonObject[LAST_CHECK_IN_DATE]) {
            customerItem.lastCheckInDate = jsonObject[LAST_CHECK_IN_DATE].asString.convertToDate()
        }
        return customerItem
    }

    private fun verifyJsonAndPerform(jsonElement: JsonElement, toPerform: () -> Unit) {
        if (!jsonElement.isJsonNull && jsonElement.asString.isNotEmpty()) toPerform()
    }
}