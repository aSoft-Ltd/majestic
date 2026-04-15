package majestic.shared.notices.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import majestic.shared.notices.list.NoticeItem

@Composable
internal fun SubHeader(
    item: NoticeItem,
    colors: NoticePopupColors
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = item.targets.firstOrNull() ?: "",
        color = colors.info,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    )

    Text(
        text = item.time,
        color = colors.info,
        fontSize = 14.sp
    )
}