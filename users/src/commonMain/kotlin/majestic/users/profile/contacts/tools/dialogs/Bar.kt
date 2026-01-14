package majestic.users.profile.contacts.tools.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_plus_sign

data class BarColors(
    val title: Color,
    val tint: Color,
    val iconBackground: Color
)

@Composable
internal fun Bar(
    modifier: Modifier,
    title: String,
    onDismiss: () -> Unit,
    orientation: ScreenOrientation,
    colors: BarColors
) = Box(modifier = modifier) {
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    Text(
        modifier = Modifier.align(Alignment.Center),
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colors.title
    )

    Icon(
        modifier = Modifier
            .padding(top = 12.dp, end = 12.dp, bottom = 24.dp)
            .align(
                when (orientation) {
                    Landscape -> Alignment.TopEnd
                    Portrait -> Alignment.CenterStart
                }
            )
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onDismiss)
            .hoverable(interactionSource = interaction)
            .background(
                color = when (orientation) {
                    is Landscape -> when (hovered) {
                        true -> colors.iconBackground
                        else -> colors.iconBackground.copy(.7f)
                    }

                    is Portrait -> Color.Transparent
                },
                shape = CircleShape
            )
            .graphicsLayer { rotationZ = 45f }
            .padding(5.dp)
            .size(24.dp),
        painter = painterResource(Res.drawable.ic_plus_sign),
        tint = colors.tint,
        contentDescription = "Icon"
    )
}