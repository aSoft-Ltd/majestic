package majestic.shared.notices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_close
import majestic.icons.ic_megaphone
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoticeBar(
    theme: ThemeColor,
    onClose: () -> Unit,
    onNotify: () -> Unit,
    notify: String,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {
        Text(
            text = "Notices",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = theme.surface.contra.color
        )
    }
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_close),
            contentDescription = "Close",
            tint = theme.surface.contra.color,
            modifier = Modifier.size(18.dp).onClick(onClose)
        )

        header()
    }

    TextButton(
        onClick = onNotify,
        modifier = Modifier.wrapContentWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_megaphone),
                contentDescription = notify,
                tint = theme.surface.contra.color,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = notify,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = theme.surface.contra.color
            )
        }
    }
}