package majestic.payments.tools.filters

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.dropdown.DropdownItem
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_calendar_04

@Composable
internal fun FilterByYear(
    colors: DropdownColors,
    years: List<String>,
    selected: String,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit = {}
) {
    val matchingWidth = 120.dp
    var selectedYear by remember { mutableStateOf(selected) }

    Dropdown(
        items = years.map { year -> DropdownItem(value = year, label = year) },
        selected = selectedYear,
        onSelection = {
            selectedYear = it
            onSelected(it)
        },
        leadingIcon = vectorResource(Res.drawable.ic_calendar_04),
        placeholder = "Select Year",
        colors = colors,
        popupWidth = matchingWidth,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier.width(matchingWidth)
    )
}