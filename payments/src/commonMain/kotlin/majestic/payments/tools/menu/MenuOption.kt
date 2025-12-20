package majestic.payments.tools.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Popup
import majestic.popup.Inline
import majestic.popup.Items
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_more_horizontal

internal data class OptionMenu<T>(
    val label: String,
    val action: T,
    val icon: DrawableResource? = null
)

@Composable
internal fun <T> MenuOption(
    orientation: ScreenOrientation,
    actions: List<OptionMenu<T>>,
    onAction: (T) -> Unit,
    colors: MenuOptionColors = MenuOptionColors.default,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered || expanded) colors.icon.background else Color.Transparent

    Popup(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource)
            .onClick { expanded = !expanded },
        inline = Inline(modifier = Modifier.fillMaxSize(), alignment = Alignment.Center) {
            val color = if (isHovered) colors.icon.foreground else colors.icon.foreground.copy(.9f)
            Icon(
                modifier = Modifier.size(24.dp).then(if (orientation is Portrait) Modifier.rotate(90f) else Modifier),
                painter = painterResource(Res.drawable.ic_more_horizontal),
                tint = color,
                contentDescription = "Icon"
            )
        },
        items = Items(
            modifier = Modifier.width(IntrinsicSize.Min)
                .background(colors.dropDown)
                .padding(horizontal = 6.dp),
            data = actions,
            shape = RoundedCornerShape(10.dp)
        ) { item ->
            val colorRed = colors.delete
            val colorNormal = colors.item
            NavItem(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 10.dp)
                    .onClick {
                        expanded = !expanded
                        onAction(item.action)
                    },
                leading = {
                    if (item.icon != null) {
                        Icon(imageVector = vectorResource(item.icon), contentDescription = null)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                label = item.label,
                colors = if (item.action.toString().equals("delete", true)) colorRed else colorNormal,
            )
        }
    )
}
