package majestic.calendar.month.tools

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import majestic.calendar.month.tools.defaults.YearDefaults
import majestic.calendar.DatePickerState

@Composable
internal fun YearDropdown(
    defaults: YearDefaults,
    state: DatePickerState,
    expanded: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier.heightIn(max = 200.dp)
) {
    DropdownMenu(
        containerColor = defaults.colors.background,
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        state.yearRange.forEach { year ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = "$year",
                        style = TextStyle(
                            fontSize = defaults.fontSize,
                            fontWeight = FontWeight(400),
                            color = defaults.colors.text
                        )
                    )
                },
                onClick = { state.onYearSelected(year) }
            )
        }
    }
}