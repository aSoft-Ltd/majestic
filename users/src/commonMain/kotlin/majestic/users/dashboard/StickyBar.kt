package majestic.users.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.dashboard.stickybar.filters.FilterByYear
import majestic.users.dashboard.stickybar.filters.FilterDefault
import majestic.users.tools.dialogs.Flex

@Composable
internal fun StickyBar(
    orientation: ScreenOrientation,
    theme: ThemeColor,
    filterDefaults: FilterDefault,
    modifier: Modifier,
    title: String,
    detail: String,
    selected: String = "2025",
    years: List<String> = listOf("2023", "2024", "2025")
) {
    Flex(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        alignment = Alignment.CenterVertically,
        orientation = orientation
    ) {
        Column {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = theme.surface.contra.color,
            )
            Text(
                text = detail,
                fontSize = 16.sp,
                color = theme.surface.contra.color.copy(0.3f),
            )
        }
        FilterByYear(
            years = years,
            selected = selected,
            modifier = Modifier.width(120.dp),
            defaults = filterDefaults
        )
    }
}

//fun ThemeColor.toFilterDefault() = FilterDefault(
//    drawerColor = toPopCompColors().background,
//    colors = SelectFilterColors(
//        background = toPopMainColors().background,
//        text = toPopMainColors().foreground,
//        icon = toPopMainColors().foreground,
//    ),
//    containerShape = RoundedCornerShape(8.dp),
//    drawerShape = RoundedCornerShape(8.dp),
//    item = ColorPair(
//        foreground = toPopMainColors().foreground.copy(.8f),
//        background = toPopMainColors().foreground
//    )
//)