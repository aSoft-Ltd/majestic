package majestic.users.dashboard.stickybar.filters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import majestic.SmartSelect
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_calendar

@Composable
internal fun FilterByYear(
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
