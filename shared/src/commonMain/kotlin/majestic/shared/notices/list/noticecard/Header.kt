package majestic.shared.notices.list.noticecard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Tag
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownItem
import majestic.icons.Res
import majestic.icons.ic_more_horizontal
import majestic.icons.ic_presentation
import majestic.shared.notices.list.NoticeItem
import majestic.shared.notices.list.NoticeStatus
import majestic.tag
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun <T> Header(
    item: NoticeItem,
    labels: NoticeCardLabels,
    colors: NoticeCardColors,
    orientation: ScreenOrientation,
    actions: List<DropdownItem<T>>,
    onAction: (T) -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = when (orientation) {
        is Landscape -> Alignment.Top
        is Portrait -> Alignment.CenterVertically
    }
) {
    Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(Res.drawable.ic_presentation),
            contentDescription = null,
            tint = colors.icon.foreground,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.icon.background)
                .padding(12.dp)
        )


        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.title,
                    color = colors.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 1.sp
                )
                item.info.forEach {
                    Text(text = "•", color = colors.info, fontSize = 14.sp)
                    Text(
                        text = it,
                        color = colors.info,
                        fontSize = 14.sp,
                        maxLines = 1,
                        lineHeight = 1.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (orientation is Landscape) Text(
                    text = labels.target,
                    color = colors.info,
                    fontSize = 13.sp,
                    maxLines = 1,
                    lineHeight = 1.sp,
                    overflow = TextOverflow.Ellipsis
                )
                item.targets.forEach { target ->
                    Tag(
                        label = target,
                        color = colors.tag.foreground,
                        modifier = Modifier.tag(color = colors.tag.background)
                    )
                }
                if (orientation is Landscape) Text(
                    text = item.time,
                    color = colors.info,
                    fontSize = 13.sp,
                    maxLines = 1,
                    lineHeight = 1.sp,
                    overflow = TextOverflow.Ellipsis
                )
                val statusColor = when (item.status) {
                    NoticeStatus.Posting -> colors.status.posting
                    NoticeStatus.Posted -> colors.status.posted
                    NoticeStatus.Failed -> colors.status.failed
                    NoticeStatus.Draft -> colors.status.draft
                }
                if (orientation is Portrait) Tag(
                    label = labels.status.get(item.status),
                    color = statusColor,
                    modifier = Modifier.tag(color = statusColor.copy(alpha = 0.15f))
                )
            }
        }
    }

    Dropdown(
        items = actions,
        onAction = onAction,
        colors = colors.dropdown,
        customTrigger = { _, onToggle ->
            Icon(
                imageVector = vectorResource(Res.drawable.ic_more_horizontal),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .rotate(if (orientation is Portrait) 90f else 0f)
                    .onClick {
                        onToggle()
                    },
                tint = colors.dropdown.trigger
            )
        },
        popupWidth = 150.dp,
        modifier = Modifier.border(1.dp, Color.Red)
    )
}
