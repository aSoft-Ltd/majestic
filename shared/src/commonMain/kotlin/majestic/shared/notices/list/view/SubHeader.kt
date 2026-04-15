package majestic.shared.notices.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.notices.list.NoticeItem

@Composable
internal fun SubHeader(
    item: NoticeItem,
    connector: String = "and",
    colors: NoticePopupColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(horizontalArrangement = Arrangement.Start) {
        val limit = if (orientation is Landscape) 5 else 2
        val showMore = item.targets.size > limit
        val display = item.targets.take(limit)
        display.forEachIndexed { index, target ->
            Text(
                text = target,
                color = colors.info,
                fontSize = 15.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium
            )

            if (index < display.size - 1) {
                val separator = when {
                    index == display.size - 2 && !showMore -> if (orientation is Landscape) ", $connector " else " $connector "
                    else -> ", "
                }
                Text(
                    text = separator,
                    color = colors.info,
                    lineHeight = 1.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        if (showMore) {
            val separator = if (orientation is Landscape) ", $connector " else " $connector "
            Text(
                text = separator,
                color = colors.info,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "more",
                color = colors.info,
                lineHeight = 1.sp,
                maxLines = 1,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium
            )
        }
    }
    Text(
        text = item.time,
        lineHeight = 1.sp,
        maxLines = 1,
        color = colors.info,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp
    )
}