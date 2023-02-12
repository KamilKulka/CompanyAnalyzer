package com.kamilkulka.companyanalyzer.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.kamilkulka.companyanalyzer.R
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamilkulka.companyanalyzer.component.CustomerItemCard

@Composable
fun ClientsScreen(viewModel: ClientsViewModel = hiltViewModel()) {
    var isExpandedEarliestCustomer by remember { mutableStateOf(true) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(id = R.string.earliest_check_in_customer),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.clickable {
                    isExpandedEarliestCustomer = !isExpandedEarliestCustomer
                })
            if (isExpandedEarliestCustomer) {
                LazyColumn {
                    items(items = viewModel.getEarliestCustomer()) { customerItem ->
                        CustomerItemCard(customerItem)
                    }
                }
            }
        }
    }
}