package majestic.buttons


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class ButtonAnimateColors(
    val hovered: ColorPair = ColorPair(
        foreground = Color(0xFFAB47BC),
        background = Color(0xFFAB47BC).copy(alpha = 0.2f)
    ),
    val inactive: ColorPair = ColorPair(
        foreground = Color.White.copy(alpha = 0.7f),
        background = Color.White.copy(alpha = 0.05f)
    ),
    val text: Color = Color.White.copy(alpha = 0.7f)
) {
    companion object {
        val default by lazy { ButtonAnimateColors() }
    }
}

@Composable
fun ButtonAnimate(

    label: String,
    icon: DrawableResource,
    expanded: Boolean = true,
    isOpen: Boolean = false,
    isHovered: Boolean = false,
    colors: ButtonAnimateColors = ButtonAnimateColors.default,
    modifier: Modifier
) {
    val iconColor = if (isHovered) colors.hovered.foreground else colors.inactive.foreground

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            tint = iconColor,
            contentDescription = "Icon",
            modifier = Modifier.size(24.dp)
        )

        if ((isHovered || isOpen) && expanded) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = label,
                color = colors.text,
                fontSize = 14.sp
            )
        }
    }
}