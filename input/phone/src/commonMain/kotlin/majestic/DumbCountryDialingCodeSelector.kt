package majestic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nation.Country

@Composable
fun DumbCountryDialingCodeSelector(
    value: Country? = null,
    modifier: Modifier = Modifier,
    onClick: ((Country) -> Unit)? = null,
    placeholder: @Composable () -> Unit = { Text("Select") },
    item: @Composable (Country) -> Unit = { CountryDialingCodePreview(it) },
    selected: @Composable (Country) -> Unit = item
) = DumbSelect(
    items = Country.entries,
    item = item,
    selected = selected,
    placeholder = placeholder,
    value = value,
    onClick = onClick,
    modifier = modifier
)