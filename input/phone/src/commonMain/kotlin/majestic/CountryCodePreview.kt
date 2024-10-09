package majestic

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nation.Country
import nation.Flag

@Composable
fun CountryCodePreview(
    country: Country,
    modifier: Modifier = Modifier
) = Row(modifier, verticalAlignment = Alignment.CenterVertically) {
    Flag(country)
    Spacer(modifier = Modifier.width(10.dp))
    Text(country.label)
    Spacer(modifier = Modifier.width(10.dp))
    Text("(+${country.dialingCode})")
}
