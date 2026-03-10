package majestic.shared.tools.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Popup
import majestic.button.Button
import majestic.icons.Res
import majestic.icons.ic_more_horizontal
import majestic.popup.Inline
import majestic.popup.Items
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

enum class MenuAction { Edit, Duplicate, Delete }


data class OptionMenu<T>(
    val label: String,
    val action: T,
    val icon: DrawableResource? = null,
    val isDestructive: Boolean = false,
)

@Composable
fun <T> MenuOption(
    colors: MenuOptionColors,
    icon: (@Composable (color: Color) -> Unit)? = null,
    orientation: ScreenOrientation,
    actions: List<OptionMenu<T>>,
    onAction: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Popup(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        inline = Inline(alignment = Alignment.Center) {
            val color = if (isHovered) colors.icon.foreground else colors.icon.foreground.copy(.9f)
            Button(
                modifier = Modifier.listItemIconButton(
                    colors = colors.listIconButton,
                    onClick = { expanded = !expanded }
                )
            ) { colors ->
                if (icon == null) Icon(
                    imageVector = vectorResource(Res.drawable.ic_more_horizontal),
                    contentDescription = null,
                    tint = colors.foreground,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(18.dp)
                        .then(if (orientation is Portrait) Modifier.rotate(90f) else Modifier)

                )
                else icon.invoke(color)
            }
        },
        items = Items(
            modifier = Modifier.width(IntrinsicSize.Min)
                .background(colors.dropDown)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            data = actions,
            shape = RoundedCornerShape(12.dp)
        ) { item ->
            val colorRed = colors.lastItem
            val colorNormal = colors.item
            NavItem(
                leading = {
                    if (item.icon != null) {
                        Icon(imageVector = vectorResource(item.icon), contentDescription = null)
                    }
                },
                modifier = Modifier.navItem().onClick {
                    expanded = !expanded
                    onAction(item.action)
                },
                label = item.label,
                colors = if (item.action.toString()
                        .equals("delete", ignoreCase = true)
                ) colorRed else colorNormal,
            )
        }
    )
}