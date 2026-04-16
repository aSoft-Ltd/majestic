package majestic.shared.notices.noticebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_close
import majestic.shared.notices.NoticeBarColors
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun DefaultNoticeBarHeader(colors: NoticeBarColors, onClose: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_close),
            contentDescription = "Close",
            tint = colors.tint,
            modifier = Modifier.size(18.dp).onClick(onClose)
        )
        Text(
            text = "Notices",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.title
        )
    }
}