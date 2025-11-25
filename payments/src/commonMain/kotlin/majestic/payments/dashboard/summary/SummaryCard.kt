package majestic.payments.dashboard.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.SummaryLabels
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_arrow_up_02

data class SummaryCardColors(
    val text: Color,
    val icon: Color
)

@Composable
fun SummaryCard(
    labels: SummaryLabels,
    type: SummaryType,
    value: String,
    percentage: Float,
    colors: SummaryCardColors,
    orientation: ScreenOrientation,
    wallet: Int? = null,
    onView: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.icon.copy(0.05f))
                    .padding(8.dp),
                painter = painterResource(type.icon),
                tint = colors.icon,
                contentDescription = null,
            )
            if (orientation is Landscape) Text(
                text = type.getLabel(labels),
                color = colors.text.copy(0.3f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
        }
        Row(
            modifier = Modifier
                .background(Color(0xFF46C362).copy(0.1f), CircleShape)
                .padding(vertical = 5.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "${percentage}%", color = Color(0xFF46C362), fontSize = 12.sp, lineHeight = 0.1.sp)
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(Res.drawable.ic_arrow_up_02),
                tint = Color(0xFF46C362),
                contentDescription = null,
            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "TZS",
                    color = colors.text.copy(0.3f),
                    fontSize = if (type == SummaryType.COLLECTED && orientation is Landscape) 16.sp else 12.sp
                )
                Text(
                    text = value,
                    color = colors.icon,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = if (type == SummaryType.COLLECTED && orientation is Landscape) 32.sp else 20.sp,
                    lineHeight = 1.sp
                )
            }
            if (orientation is Portrait) Text(
                text = type.getLabel(labels),
                color = colors.text.copy(0.3f),
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
        }
        if (type == SummaryType.BALANCE) Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$wallet",
                color = colors.text,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 1.sp
            )
            Text(
                text = labels.wallet,
                color = colors.text.copy(0.3f),
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
        } else if (type != SummaryType.COLLECTED) {
            ViewButton(label = labels.view, color = colors.text, onClick = onView)
        }
    }
}
