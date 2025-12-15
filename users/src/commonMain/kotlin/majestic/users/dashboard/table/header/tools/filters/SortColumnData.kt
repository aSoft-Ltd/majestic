package majestic.users.dashboard.table.header.tools.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.Popup
import majestic.SelectColors
import majestic.navigation.MenuItemColors
import majestic.popup.Inline
import majestic.popup.Item
import majestic.popup.Items
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class SortColumnDataColors(
    val selector: SelectColors,
    val pop: ColorPair,
    val activeBorder: Color,
    val menuItem: MenuItemColors
)

data class SortColumnDataProps(
    val colors: SortColumnDataColors,
    val caretUpward: DrawableResource,
    val caretDownWard: DrawableResource
)

@Composable
internal fun <T> SortColumnData(
    selected: T,
    items: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    props: SortColumnDataProps
) {
    var expanded by remember { mutableStateOf(false) }
    val popCompColors = props.colors.selector
    Popup(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = { expanded = false },
        inline = Inline(modifier = Modifier.fillMaxHeight(), alignment = Alignment.Center) {
            Box(
                modifier = Modifier.height(25.dp)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .onClick { expanded = !expanded }
            ) {
                Icon(
                    modifier = Modifier.size(16.dp).align(Alignment.TopCenter),
                    painter = painterResource(props.caretUpward),
                    tint = props.colors.pop.foreground.copy(0.8f),
                    contentDescription = "Icon"
                )
                Icon(
                    modifier = Modifier.size(16.dp).align(Alignment.BottomCenter),
                    painter = painterResource(props.caretDownWard),
                    tint = props.colors.pop.foreground.copy(0.2f),
                    contentDescription = "Icon"
                )
            }
        },
        items = Items(
            data = items,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.width(IntrinsicSize.Max).clip(RoundedCornerShape(12.dp))
                .background(props.colors.pop.background),
            item = Item(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                val active = selected == it
                val color = if (active) props.colors.activeBorder else props.colors.pop.foreground

                NavItem(
                    modifier = Modifier.navItem().onClick {
                        onClick(it)
                        expanded = false
                    },
                    label = "$it",
                    shape = RoundedCornerShape(12.dp),
                    style = LocalTextStyle.current.copy(color = color, fontSize = 14.sp, lineHeight = 1.sp),
                    colors = props.colors.menuItem,
                    trailing = {
                        if (active) Box(Modifier.padding(start = 10.dp)) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Filled.Check,
                                tint = color,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        )
    )
}
