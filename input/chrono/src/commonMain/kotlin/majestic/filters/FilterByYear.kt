package majestic.filters

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.SmartSelect
import majestic.icons.Res
import majestic.icons.ic_calendar

@Composable
 fun FilterByYear(
    years: List<String>,
    selected: String,
    defaults: FilterDefault = FilterDefault.Defaults,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit = {}
) {
    var selectedYear by remember { mutableStateOf(selected) }
    val icon = Res.drawable.ic_calendar

    SmartSelect(
        modifier = modifier,
        dropdownModifier = Modifier.padding(8.dp),
        items = years,
        item = { FilterItem(defaults.item, it, it == selectedYear) },
        selected = { SelectedItem(defaults.colors, it, icon) },
        placeholder = { SelectedItem(defaults.colors, selectedYear, icon) },
        value = selectedYear,
        onChange = {
            selectedYear = it ?: selectedYear
            onSelected(it ?: selectedYear)
        },
        drawerContainerColor = defaults.drawerColor,
        shape = defaults.containerShape,
        dropDownShape = defaults.drawerShape
    )
}