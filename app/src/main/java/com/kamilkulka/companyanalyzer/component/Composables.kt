package com.kamilkulka.companyanalyzer.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kamilkulka.companyanalyzer.R
import com.kamilkulka.companyanalyzer.model.CustomerItem
import java.time.LocalDate

@Composable
fun CustomerItemCard(
    customerItem: CustomerItem,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: Dp = 1.dp
) {
    Card(modifier = modifier, elevation = elevation, shape = shape) {
        Column(modifier = Modifier.padding(contentPadding)) {
            NameAndValueRow(name = stringResource(id = R.string.first_name) , customerItem.firstName)
            NameAndValueRow(name = stringResource(id = R.string.last_name), customerItem.lastName)
            NameAndValueRow(name = stringResource(id = R.string.job), customerItem.job)
            NameAndValueRow(name = stringResource(id = R.string.company), customerItem.company)
            NameAndValueRow(name = stringResource(id = R.string.city), customerItem.city)
            NameAndValueRow(name = stringResource(id = R.string.street), customerItem.street)
            NameAndValueRow(name = stringResource(id = R.string.type), customerItem.type)
            NameAndValueRow(name = stringResource(id = R.string.zip), customerItem.zip)
            NameAndValueRow(name = stringResource(id = R.string.phone), customerItem.phone?.toString())
            NameAndValueRow(name = stringResource(id = R.string.last_check_in_date), customerItem.lastCheckInDate?.toString())
        }
    }
}

@Composable
fun NameAndValueRow(name: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "${name}: ")
        Text(text = value ?: "-")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerItemCardPreview() {
    CustomerItemCard(
        customerItem = CustomerItem(
            firstName = "John",
            lastName = "Doe",
            job = null,
            company = null,
            city = "Barcelona",
            street = "Fake street",
            type = "U",
            zip = "37100",
            phone = null,
            lastCheckInDate = LocalDate.now()
        )
    )
} 