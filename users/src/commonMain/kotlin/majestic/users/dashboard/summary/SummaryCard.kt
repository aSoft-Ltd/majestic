package majestic.users.dashboard.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.tooling.animateAsState
import majestic.users.dashboard.tools.UserDetailsStatus
import majestic.users.dashboard.tools.graphVector
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_down_01_solid
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_up_02


data class SummaryCardColorProps(
    val colors: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Black
    )
)

enum class SummaryStatus { GOOD, BAD }

@Composable
fun SummaryCard(
    label: String,
    value: Int,
    icon: Painter,
    orientation: ScreenOrientation,
    percentage: Float? = null,
    userDetailsStatus: UserDetailsStatus? = null,
    summaryStatus: SummaryStatus? = null,
    modifier: Modifier,
    props: SummaryCardColorProps,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val background = when (isHovered) {
        true -> props.colors.foreground.copy(0.02f).compositeOver(props.colors.background)
        false -> props.colors.background
    }
    val summaryColor = when (summaryStatus) {
        SummaryStatus.GOOD -> Color(0xFF46C362)
        else -> Color(0xFFF44336)
    }
    val summaryIcon = when (summaryStatus) {
        SummaryStatus.GOOD -> Res.drawable.ic_arrow_up_02
        else -> Res.drawable.ic_arrow_down_01_solid
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(props.colors.foreground.copy(0.05f)),
            ) {
                Image(
                    modifier = if (orientation is Portrait) Modifier.padding(5.dp)
                        .size(20.dp) else Modifier.padding(10.dp),
                    painter = icon,
                    colorFilter = ColorFilter.tint(props.colors.foreground),
                    contentDescription = null,
                )
            }
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            val graphColor = when (userDetailsStatus) {
                UserDetailsStatus.USERS -> UserDetailsStatus.USERS.color
                UserDetailsStatus.ROLES -> UserDetailsStatus.ROLES.color
                UserDetailsStatus.ACTIVE -> UserDetailsStatus.ACTIVE.color
                UserDetailsStatus.PENDING -> UserDetailsStatus.PENDING.color
                else -> props.colors.foreground
            }
            Column {
                val v by animateAsState(to = value, steps = 50)
                Text(
                    text = "${v.toInt()}",
                    color = props.colors.foreground,
                    fontSize = if (orientation is Landscape) 24.sp else 16.sp,
                )
                Text(
                    text = label,
                    fontSize = if (orientation is Landscape) 16.sp else 12.sp,
                    color = props.colors.foreground.copy(0.5f)
                )
            }
            Image(
                modifier = if (orientation is Portrait) Modifier.width(50.dp) else Modifier,
                imageVector = graphVector(graphColor),
                contentDescription = null
            )
        }
    }
}