package majestic.shared.notices.list.noticecard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import majestic.shared.notices.list.NoticeItem

@Composable
fun Content(
    item: NoticeItem,
    colors: NoticeCardColors,
    modifier: Modifier = Modifier
) = Text(
    text = item.description,
    color = colors.description,
    fontSize = 14.sp,
    maxLines = 2,
    overflow = TextOverflow.Ellipsis,
    lineHeight = 20.sp,
    modifier = modifier
)
