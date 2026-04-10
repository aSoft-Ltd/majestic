package majestic.shared.notices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_presentation
import majestic.shared.notices.labels.EmptyNoticeLabels
import org.jetbrains.compose.resources.painterResource


@Composable
fun EmptyNotice(
    theme: ThemeColor,
    labels: EmptyNoticeLabels,
    modifier: Modifier = Modifier,
    action: @Composable () -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Icon(
        painter = painterResource(Res.drawable.ic_presentation),
        contentDescription = null,
        modifier = Modifier.size(80.dp),
        tint = theme.surface.contra.color.copy(alpha = 0.5f)
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = labels.title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = theme.surface.contra.color
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = labels.subtitle,
        fontSize = 16.sp,
        color = theme.surface.contra.color.copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(max = 450.dp).padding(horizontal = 12.dp)
    )
    Spacer(modifier = Modifier.height(32.dp))
    action()
}