package majestic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate

@Composable
fun SelectedDate(value: LocalDate? = null) {
    if (value == null) {
        Text("No date selected")
    } else {
        Text("Date: ${value.dayOfMonth} ${value.month.name} ${value.year}")
    }
}