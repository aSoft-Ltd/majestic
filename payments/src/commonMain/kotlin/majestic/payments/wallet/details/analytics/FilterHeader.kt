package majestic.payments.wallet.details.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import majestic.Popup
import majestic.button.Button
import majestic.payments.wallet.details.analytics.tools.AnalyticColors
import majestic.payments.wallet.details.analytics.tools.AnalyticsFilter
import majestic.payments.wallet.details.analytics.tools.AnalyticsFilters
import majestic.popup.Inline
import majestic.popup.Overlay
import majestic.rememberDateRangePickerManager
import majestic.shared.calendar.CalendarRangePicker
import majestic.tooling.onClick

@Composable
internal fun FilterHeader(
    colors: AnalyticColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    var analyticsFilter by remember { mutableStateOf(AnalyticsFilter.ACCOUNT) }
    AnalyticsFilters(
        filter = analyticsFilter,
        color = colors.header.foreground,
        orientation = orientation,
        onChange = { analyticsFilter = it },
    )

    var expanded by remember { mutableStateOf(false) }
    val today = LocalDate.fromEpochDays(kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays())
    val nextMonth = today + DatePeriod(months = 1)
    val manager = rememberDateRangePickerManager(
        start = today,
        end = LocalDate(nextMonth.year, nextMonth.month.number, nextMonth.day),
        onPicked = { _, _ -> expanded = false }
    )
    Popup(
        onDismissRequest = { expanded = false },
        expanded = expanded,
        inline = Inline {
            Button(
                modifier = Modifier.clip(CircleShape)
                    .background(colors.header.foreground.copy(0.05f))
                    .onClick { expanded = true }
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "Start Date", fontSize = 14.sp, lineHeight = 1.sp, color = colors.header.foreground.copy(0.7f))
                Spacer(Modifier.width(10.dp))
                Text(text = "-", fontSize = 30.sp, lineHeight = 1.sp, color = colors.header.foreground.copy(0.7f))
                Spacer(Modifier.width(10.dp))
                Text(text = "End Date", fontSize = 14.sp, lineHeight = 1.sp, color = colors.header.foreground.copy(0.7f))
            }
        },
        overlay = Overlay(
            modifier = Modifier.width(IntrinsicSize.Max)
                .background(colors.calendar.surface.background)
                .padding(if (orientation is Landscape) 20.dp else 8.dp),
            shape = RoundedCornerShape(if (orientation is Landscape) 40.dp else 8.dp)
        ) {
            CalendarRangePicker(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max),
                arrangement = Arrangement.spacedBy(20.dp),
                manager = manager,
                from = "From",
                to = "To",
                colors = colors.calendar,
                orientation = orientation
            )
        }
    )
}
