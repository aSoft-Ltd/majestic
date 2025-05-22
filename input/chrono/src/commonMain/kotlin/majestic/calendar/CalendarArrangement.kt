package majestic.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp

class CalendarArrangement(
    val vertical: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
    val horizontal: Arrangement.Horizontal = Arrangement.spacedBy(24.dp),
) {
    companion object {
        val Default by lazy { CalendarArrangement() }
    }
}