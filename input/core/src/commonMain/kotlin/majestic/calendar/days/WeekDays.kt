package majestic.calendar.days

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import majestic.calendar.days.tools.WeekDaysDefaults

@Composable
internal fun WeekDays(modifier: Modifier, defaults: WeekDaysDefaults = WeekDaysDefaults.Default) {
    Row {
        defaults.days.forEach { day ->
            Text(
                text = day,
                modifier = modifier,
                textAlign = TextAlign.Center,
                fontSize = defaults.fontSize,
                color = defaults.color,
            )
        }
    }
}