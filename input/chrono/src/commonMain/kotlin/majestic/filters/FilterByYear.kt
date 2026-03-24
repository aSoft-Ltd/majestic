package majestic.filters

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

@Composable
fun FilterByYear(
    years: List<String>,
    selected: String,
    onSelected: (String) -> Unit = {},
    colors: DropdownColors,
    modifier: Modifier = Modifier,
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
        placeholder = "Select Year",
        colors = colors,
        popupWidth = matchingWidth,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier.width(matchingWidth)
    )
}