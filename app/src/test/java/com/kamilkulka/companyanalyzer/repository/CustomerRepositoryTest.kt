package com.kamilkulka.companyanalyzer.repository

import com.kamilkulka.companyanalyzer.data.DataOrException
import com.kamilkulka.companyanalyzer.model.Customer
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.network.CustomerApi
import com.kamilkulka.companyanalyzer.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class CustomerRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var customerApi: CustomerApi

    private lateinit var repository: CustomerRepository

    private val expectedCustomers = Customer()

    private val dataOrExceptionExpected =
        DataOrException<ArrayList<CustomerItem>, Boolean, Exception>(
            arrayListOf(
                CustomerItem(
                    "John",
                    "Travolta",
                    "Actor",
                    null,
                    "Los Angeles",
                    "Hollywood Bvd 35",
                    "U",
                    null,
                    null,
                    LocalDate.of(2015, 5, 11)
                ), CustomerItem(
                    "Jane",
                    "Doe",
                    "Hairdresser",
                    "Hairless",
                    "Granada",
                    null,
                    "T",
                    "12345",
                    909090111,
                    LocalDate.of(2011, 12, 24)
                ), CustomerItem(
                    "Raul",
                    "Lopez",
                    null,
                    null,
                    "Barcelona",
                    null,
                    null,
                    "A1234",
                    333000333,
                    LocalDate.of(2021, 3, 1)
                )
            ), false, null
        )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        repository = CustomerRepository(customerApi)

        expectedCustomers.add(
            CustomerItem(
                "John",
                "Travolta",
                "Actor",
                null,
                "Los Angeles",
                "Hollywood Bvd 35",
                "U",
                null,
                null,
                LocalDate.of(2015, 5, 11)
            ))
        expectedCustomers.add(
            CustomerItem(
                "Jane",
                "Doe",
                "Hairdresser",
                "Hairless",
                "Granada",
                null,
                "T",
                "12345",
                909090111,
                LocalDate.of(2011, 12, 24)
            ))
        expectedCustomers.add(
            CustomerItem(
                "Raul",
                "Lopez",
                null,
                null,
                "Barcelona",
                null,
                null,
                "A1234",
                333000333,
                LocalDate.of(2021, 3, 1)
            ))
    }

    @Test
    fun getAllCustomersTest() = runTest {
        whenever(customerApi.getAllCustomers()).thenReturn(expectedCustomers)

        val result = repository.getAllCustomers()

        assertThat(result, equalTo(dataOrExceptionExpected))
    }
}