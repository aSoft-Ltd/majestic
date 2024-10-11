package majestic

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import nation.Country
import symphony.PhoneField

@Composable
fun CountryDialingCodeSelector(
    field: PhoneField,
    modifier: Modifier = Modifier,
    onClick: ((Country) -> Unit)? = null,
    onChange: ((Country?) -> Unit)? = null,
    placeholder: @Composable () -> Unit = { Text("Select") },
    selected: @Composable (Country) -> Unit = {
        CountryDialingCodePreview(it, modifier = Modifier.padding(10.dp))
    },
    item: @Composable (Country) -> Unit = { CountryCodePreview(it) }
) {
    val state = field.state.watchAsState()
    DumbCountryDialingCodeSelector(
        value = state.country,
        countries = state.countries,
        selected = selected,
        placeholder = placeholder,
        modifier = modifier,
        onSearch = { field.searchByFiltering(it.lowercase()) },
        item = item,
        onClick = {
            if (it == state.country) {
                field.unsetCountry()
                onChange?.invoke(null)
            } else {
                field.selectCountryLabel(it.label)
                onChange?.invoke(it)
            }
            onClick?.invoke(it)
        }
    )
}
