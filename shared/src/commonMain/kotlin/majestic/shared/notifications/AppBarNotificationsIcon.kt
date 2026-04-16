package majestic.shared.notifications

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_notification_01_outlined
import org.jetbrains.compose.resources.vectorResource

@Composable
fun AppBarNotificationsIcon(
    count: Int,
    maxCount: Int = 99,
    colors: AppBarNotificationsColors,
    interaction: MutableInteractionSource,
    modifier: Modifier = Modifier
) {
    val isHovered = interaction.collectIsHoveredAsState().value
    val iconTintAlpha by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0.8f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_notification_01_outlined),
            contentDescription = "Notifications",
            tint = colors.foreground.copy(iconTintAlpha),
            modifier = Modifier.size(24.dp).hoverable(interaction)
        )

        if (count > 0) {
            val finalCount = if (count > maxCount) "$maxCount+" else count.toString()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 6.dp)
                    .background(color = Color(0xFFE8423A), shape = CircleShape)
                    .padding(2.dp)
            ) {
                Text(
                    text = finalCount,
                    color = colors.foreground,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 0.sp
                )
            }
        }
    }
}