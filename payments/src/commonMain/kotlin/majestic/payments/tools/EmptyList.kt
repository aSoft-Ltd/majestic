package majestic.payments.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import majestic.payments.labels.SectionLabels
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyList(
    labels: SectionLabels,
    theme: ThemeColor,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Icon(
        modifier = Modifier.size(52.dp),
        painter = painterResource(icon),
        tint = theme.dominant.actual.color,
        contentDescription = null
    )
    Text(
        text = labels.label,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 1.sp,
        color = theme.surface.contra.color
    )
    Text(
        text = labels.description,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center,
        color = theme.surface.contra.color.copy(alpha = 0.5f)
    )
}
