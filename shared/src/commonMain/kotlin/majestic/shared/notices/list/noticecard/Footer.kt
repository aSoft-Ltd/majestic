package majestic.shared.notices.list.noticecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.Tag
import majestic.shared.notices.list.NoticeItem
import majestic.shared.notices.list.NoticeStatus
import majestic.tag

@Composable
fun Footer(
    item: NoticeItem,
    labels: NoticeCardLabels,
    colors: NoticeCardColors,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    HorizontalDivider(
        color = colors.divider,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val maxVisible = 4
            val painters = item.avatars
            val avatarsToShow = painters.take(maxVisible)

            Box(contentAlignment = Alignment.CenterStart) {
                avatarsToShow.forEachIndexed { index, painter ->
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = (index * 12).dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(1.dp, colors.background, CircleShape)
                    )
                }
            }
            Text(
                text = item.progress,
                color = colors.info,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (item.hasAlert) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(Color(0xFFEF4444), CircleShape)
                )
            }
            val statusColor = when (item.status) {
                NoticeStatus.Posting -> colors.status.posting
                NoticeStatus.Posted -> colors.status.posted
                NoticeStatus.Failed -> colors.status.failed
                NoticeStatus.Draft -> colors.status.draft
            }
            Tag(
                label = labels.status.get(item.status),
                color = statusColor,
                modifier = Modifier.tag(color = statusColor.copy(alpha = 0.15f))
            )
        }
    }
}
