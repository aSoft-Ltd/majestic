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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation

data class SummaryCardColors(
    val text: Color,
    val icon: Color
)

@Composable
fun SummaryCard(
    label: String,
    value: String,
    icon: Painter,
    percentage: Float,
    colors: SummaryCardColors,
    orientation: ScreenOrientation,
    wallet: Int? = null,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null
) = Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Icon(
                modifier = Modifier.size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.icon.copy(0.05f))
                    .padding(8.dp),
                painter = icon,
                tint = colors.icon,
                contentDescription = null,
            )
            if (orientation is Landscape) Text(
                text = label,
                color = colors.text.copy(0.3f),
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
            Text(text = "${percentage.toInt()}%", color = Color(0xFF46C362), fontSize = 12.sp, lineHeight = 0.1.sp)
//            Icon(
//                modifier = Modifier.size(20.dp),
//                painter = painterResource(summaryIcon),
//                tint = Color(0xFF46C362),
//                contentDescription = null,
//            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "TZS",
                    color = colors.text.copy(0.3f),
                    fontSize = if (wallet == null && trailing == null && orientation is Landscape) 16.sp else 12.sp,
                    lineHeight = 1.sp
                )
                Text(
                    text = value,
                    color = colors.icon,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (wallet == null && trailing == null && orientation is Landscape) 24.sp else 18.sp,
                    lineHeight = 1.sp
                )
            }
            if (orientation is Portrait) Text(
                text = label,
                color = colors.text.copy(0.3f),
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
        }
        if (wallet != null) Column(
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
                text = "Wallets",
                color = colors.text.copy(0.3f),
                fontSize = 12.sp,
                lineHeight = 1.sp
            )
        } else trailing?.invoke()
    }
}
