package com.kamilkulka.companyanalyzer.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kamilkulka.companyanalyzer.R
import com.kamilkulka.companyanalyzer.model.CustomerItem
import java.time.LocalDate

@Composable
fun CustomerCard(
    customerItem: CustomerItem,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: Dp = 1.dp
) {
    var extraDataUnfolded by remember { mutableStateOf(false) }
    Card(modifier = modifier, elevation = elevation, shape = shape) {
        Column(modifier = Modifier
            .padding(contentPadding)
            .animateContentSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                VerticalValueWithTitle(
                    title = stringResource(id = R.string.first_name),
                    value = customerItem.firstName,
                    contentPadding
                )
                VerticalValueWithTitle(
                    title = stringResource(id = R.string.last_name),
                    value = customerItem.lastName,
                    contentPadding
                )
                VerticalValueWithTitle(
                    title = stringResource(id = R.string.last_check_in_date),
                    value = customerItem.lastCheckInDate?.toString(),
                    contentPadding
                )
                IconButton(onClick = { extraDataUnfolded = !extraDataUnfolded }) {
                    if (extraDataUnfolded) Icon(
                        imageVector = Icons.Rounded.ExpandLess,
                        contentDescription = null
                    )
                    else Icon(imageVector = Icons.Rounded.ExpandMore, contentDescription = null)
                }
            }
            if (extraDataUnfolded) {
                Divider(modifier = Modifier.padding(contentPadding.times(2)), thickness = 1.5.dp)

                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.job),
                    value = customerItem.job,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.company),
                    value = customerItem.company,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.city),
                    value = customerItem.city,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.street),
                    value = customerItem.street,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.type),
                    value = customerItem.type,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.zip),
                    value = customerItem.zip,
                    contentPadding
                )
                HorizontalValueWithTitle(
                    title = stringResource(id = R.string.phone),
                    value = customerItem.phone?.toString(),
                    contentPadding
                )
            }
        }
    }
}

@Composable
private fun VerticalValueWithTitle(title: String, value: String?, contentPadding: Dp) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value ?: "-", maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun HorizontalValueWithTitle(title: String, value: String?, contentPadding: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            color = Color.Gray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value ?: "-",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerItemCardPreview() {
    CustomerCard(
        elevation = 6.dp,
        shape = RoundedCornerShape(CornerSize(12.dp)),
        contentPadding = 4.dp,
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