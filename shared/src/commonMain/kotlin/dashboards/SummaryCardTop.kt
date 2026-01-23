package dashboards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.animateAsState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SummaryCardTop(
    modifier: Modifier,
    props: SummaryCardColorProps,
    orientation: ScreenOrientation,
    icon: Painter,
    percentage: Float?,
    summaryColor: Color,
    summaryIcon: DrawableResource
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(props.colors.foreground.copy(0.05f))
            .then(
                when (orientation) {
                    is Portrait -> Modifier
                        .padding(5.dp)
                        .size(10.dp)

                    is Landscape -> Modifier.padding(10.dp)
                }
            ),
        painter = icon,
        colorFilter = ColorFilter.tint(props.colors.foreground),
        contentDescription = null,
    )

    percentage?.let {
        val pct by animateAsState(to = it * 100)
        Row(
            modifier = Modifier
                .background(summaryColor.copy(0.1f), CircleShape)
                .padding(vertical = 5.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${pct.toInt()}%",
                color = summaryColor,
                fontSize = 12.sp,
                lineHeight = 0.1.sp
            )
            Image(
                modifier = if (orientation is Landscape) Modifier.size(20.dp) else Modifier.size(
                    16.dp
                ),
                painter = painterResource(summaryIcon),
                colorFilter = ColorFilter.tint(summaryColor),
                contentDescription = null,
            )
        }
    }
}