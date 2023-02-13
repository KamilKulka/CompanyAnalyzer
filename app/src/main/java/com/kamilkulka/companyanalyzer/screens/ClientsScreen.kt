package com.kamilkulka.companyanalyzer.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import com.kamilkulka.companyanalyzer.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamilkulka.companyanalyzer.component.CustomerCard
import com.kamilkulka.companyanalyzer.model.Statistics
import kotlinx.coroutines.launch

@Composable
fun ClientsScreen(viewModel: ClientsViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var statistics by remember { mutableStateOf(Statistics.ALL_CUSTOMERS) }

    ModalDrawer(drawerContent = {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            OutlinedButton(onClick = { statistics = Statistics.ALL_CUSTOMERS }) {
                Text(
                    text = stringResource(id = R.string.all_customers),
                    style = MaterialTheme.typography.button,
                    color = if (statistics == Statistics.ALL_CUSTOMERS) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
            }
            OutlinedButton(onClick = { statistics = Statistics.EARLIEST_CHECK_IN }) {
                Text(
                    text = stringResource(id = R.string.earliest_check_in),
                    style = MaterialTheme.typography.button,
                    color = if (statistics == Statistics.EARLIEST_CHECK_IN) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
            }
            OutlinedButton(onClick = { statistics = Statistics.LATEST_CHECK_IN }) {
                Text(
                    text = stringResource(id = R.string.latest_check_in),
                    style = MaterialTheme.typography.button,
                    color = if (statistics == Statistics.LATEST_CHECK_IN) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
            }
            OutlinedButton(onClick = { statistics = Statistics.SORTED_NAMES }) {
                Text(
                    text = stringResource(id = R.string.sorted_names),
                    style = MaterialTheme.typography.button,
                    color = if (statistics == Statistics.SORTED_NAMES) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
            }
            OutlinedButton(onClick = { statistics = Statistics.SORTED_JOBS }) {
                Text(
                    text = stringResource(id = R.string.sorted_jobs),
                    style = MaterialTheme.typography.button,
                    color = if (statistics == Statistics.SORTED_JOBS) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
                )
            }
        }
    }, drawerState = drawerState) {
        Column {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = "")
                }

                val title = when (statistics) {
                    Statistics.ALL_CUSTOMERS -> stringResource(id = R.string.all_customers)
                    Statistics.EARLIEST_CHECK_IN -> stringResource(id = R.string.earliest_check_in)
                    Statistics.LATEST_CHECK_IN -> stringResource(id = R.string.latest_check_in)
                    Statistics.SORTED_NAMES -> stringResource(id = R.string.sorted_names)
                    Statistics.SORTED_JOBS -> stringResource(id = R.string.sorted_jobs)
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Surface(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    when (statistics) {
                        Statistics.ALL_CUSTOMERS -> {
                            items(items = viewModel.getAllCustomers()) { customerItem ->
                                CustomerCard(
                                    customerItem,
                                    modifier = Modifier.padding(6.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(
                                        CornerSize(16.dp)
                                    ),
                                    contentPadding = 3.dp
                                )
                            }
                        }
                        Statistics.EARLIEST_CHECK_IN -> {
                            items(items = viewModel.getEarliestCustomer()) { customerItem ->
                                CustomerCard(
                                    customerItem,
                                    modifier = Modifier.padding(6.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(
                                        CornerSize(16.dp)
                                    ),
                                    contentPadding = 3.dp
                                )
                            }
                        }
                        Statistics.LATEST_CHECK_IN -> {
                            items(items = viewModel.getLatestCustomer()) { customerItem ->
                                CustomerCard(
                                    customerItem,
                                    modifier = Modifier.padding(6.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(
                                        CornerSize(16.dp)
                                    ),
                                    contentPadding = 3.dp
                                )
                            }
                        }
                        Statistics.SORTED_NAMES -> {
                            items(items = viewModel.getAllFullNamesAlphabetically()) { name ->
                                Card(
                                    modifier = Modifier.padding(6.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(CornerSize(8.dp))
                                ) {
                                    Text(modifier = Modifier.padding(6.dp), text = name)
                                }
                            }
                        }
                        Statistics.SORTED_JOBS -> {
                            items(items = viewModel.getAllJobsAlphabetically()) { job ->
                                Card(
                                    modifier = Modifier.padding(6.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(CornerSize(8.dp))
                                ) {
                                    Text(modifier = Modifier.padding(6.dp), text = job)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}