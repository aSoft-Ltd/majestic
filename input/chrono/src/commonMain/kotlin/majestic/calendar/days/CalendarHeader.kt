package majestic.calendar.days

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import majestic.calendar.DatePickerManager

@Composable
fun CalenderHeader(
    manager: DatePickerManager,
    color: Color = Color.Black,
    modifier: Modifier = Modifier,
) = Row(modifier, horizontalArrangement = Arrangement.SpaceBetween) {
    Row {
        Text(
            text = manager.view.month.name.capitalize(Locale.current) + " ${manager.view.year}",
            color = color,
            modifier = Modifier.clickable {
                manager.nextView()
            }
        )
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "<",
            color = color,
            modifier = Modifier.clickable { manager.prevMonth() }
        )
        Text(
            text = ">",
            color = color,
            modifier = Modifier.clickable { manager.nextMonth() }
        )
    }
}