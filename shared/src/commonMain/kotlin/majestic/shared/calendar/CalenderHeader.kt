package majestic.shared.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.DatePickerManager
import majestic.SmartSelect
import majestic.calendar.CalendarPickerColors
import majestic.icons.Res
import majestic.icons.ic_arrow_down
import majestic.icons.ic_arrow_left_01
import majestic.icons.ic_arrow_right_01
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

@Composable
fun CalenderHeader(
    manager: DatePickerManager,
    colors: CalendarPickerColors,
    modifier: Modifier = Modifier,
) = Row(modifier, horizontalArrangement = Arrangement.SpaceBetween) {
    Icon(
        modifier = Modifier.onClick { manager.prevMonth() },
        painter = painterResource(Res.drawable.ic_arrow_left_01),
        contentDescription = "Previous Month",
        tint = colors.surface.foreground.copy(0.8f)
    )
    SmartSelect(
        modifier = Modifier.width(150.dp),
        items = (0..50).toList().map { manager.view.year - 10 + it },
        item = { Text(text = "$it", fontSize = 14.sp, color = colors.surface.foreground) },
        selected = { Placeholder(manager, colors.surface.foreground) },
        placeholder = { Placeholder(manager, colors.surface.foreground) },
        onChange = {
            it?.let {
                manager.select(year = it)
                manager.select(month = manager.view.month)
            }
        },
        drawerContainerColor = colors.surface.background,
        shape = CircleShape,
        dropDownShape = RoundedCornerShape(12.dp),
        dropdownModifier = Modifier.height(250.dp)
    )
    Icon(
        modifier = Modifier.onClick { manager.nextMonth() },
        painter = painterResource(Res.drawable.ic_arrow_right_01),
        contentDescription = "Next Month",
        tint = colors.surface.foreground.copy(0.8f)
    )
}

@Composable
private fun Placeholder(manager: DatePickerManager, color: Color) = Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = "${manager.view.month.name} ${manager.view.year}",
        color = color
    )
    Icon(
        modifier = Modifier.size(12.dp),
        painter = painterResource(Res.drawable.ic_arrow_down),
        contentDescription = "year",
        tint = color.copy(0.5f)
    )
}
