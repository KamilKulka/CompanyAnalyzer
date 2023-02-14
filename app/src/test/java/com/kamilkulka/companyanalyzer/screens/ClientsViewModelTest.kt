package com.kamilkulka.companyanalyzer.screens

import com.kamilkulka.companyanalyzer.data.DataOrException
import com.kamilkulka.companyanalyzer.model.CustomerItem
import com.kamilkulka.companyanalyzer.repository.CustomerRepository
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class ClientsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var viewModel: ClientsViewModel
    private val dataOrException =
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
        viewModel = ClientsViewModel(repository)
    }

    @Test
    fun initTest() = runTest {
        whenever(repository.getAllCustomers()).thenReturn(dataOrException)

        verify(repository).getAllCustomers()
    }

    @Test
    fun getEarliestCustomerTest() = runTest {
        whenever(repository.getAllCustomers()).thenReturn(dataOrException)
        viewModel = ClientsViewModel(repository)

        val result = viewModel.getEarliestCustomer()

        assertThat(
            result[0], equalTo(
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
                )
            )
        )
    }

    @Test
    fun getLatestCustomerTest() = runTest {
        whenever(repository.getAllCustomers()).thenReturn(dataOrException)
        viewModel = ClientsViewModel(repository)

        val result = viewModel.getLatestCustomer()

        assertThat(
            result[0], equalTo(
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
                )
            )
        )
    }

    @Test
    fun getAllFullNamesAlphabeticallyTest() = runTest {
        whenever(repository.getAllCustomers()).thenReturn(dataOrException)
        viewModel = ClientsViewModel(repository)

        val result = viewModel.getAllFullNamesAlphabetically()

        assertThat(result[0], equalTo("Jane Doe"))
    }

    @Test
    fun getAllJobsAlphabeticallyTest() = runTest {
        whenever(repository.getAllCustomers()).thenReturn(dataOrException)
        viewModel = ClientsViewModel(repository)

        val result = viewModel.getAllJobsAlphabetically()

        assertThat(result[0], equalTo("Actor"))
    }
}