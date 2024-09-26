package majestic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nation.Country

@Composable
fun SmartCountryDialingCodeSelector(
    value: Country? = null,
    modifier: Modifier = Modifier,
    item: @Composable (Country) -> Unit = { CountryDialingCodePreview(it) },
    selected: @Composable (Country) -> Unit = item,
    onClick: ((Country) -> Unit)? = null,
    onChange: ((Country?) -> Unit)? = null
) {
    var country by remember { mutableStateOf(value) }

    DumbCountryDialingCodeSelector(
        value = country,
        item = item,
        selected = selected,
        modifier = modifier,
        onClick = {
            country = if (country == it) null else it
            onClick?.invoke(it)
            onChange?.invoke(country)
        }
    )
}