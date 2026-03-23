package majestic.shared.dashboards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.filters.FilterByYear
import majestic.layouts.Flex
import majestic.shared.tools.StickyBarColors

@Composable
fun StickyBar(
    orientation: ScreenOrientation,
    colors: StickyBarColors,
    modifier: Modifier,
    title: String,
    detail: String,
    selected: String = "2025",
    years: List<String> = listOf("2023", "2024", "2025")
) = Flex(
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
            color = colors.title,
        )
        Text(
            text = detail,
            fontSize = 16.sp,
            color = colors.subtitle,
        )
    }
    FilterByYear(
        years = years,
        selected = selected,
        colors = colors.dropdown,
    )
}