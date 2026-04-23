package majestic.shared.dashboards

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.layouts.Flex
import majestic.shared.tools.StickyBarColors
import majestic.shared.tools.filters.SingleChoiceBulkAction
import majestic.shared.tools.filters.SingleChoiceBulkActions

@Composable
fun StickyBar(
    orientation: ScreenOrientation,
    colors: StickyBarColors,
    title: String,
    detail: String,
    singleChoiceBulkActions: List<SingleChoiceBulkAction>,
    modifier: Modifier = Modifier,
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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.horizontalScroll(
            state = rememberScrollState(),
            reverseScrolling = true
        ),
    ) {
        SingleChoiceBulkActions(
            colors = colors.dropdown,
            actions = singleChoiceBulkActions
        )
    }
}