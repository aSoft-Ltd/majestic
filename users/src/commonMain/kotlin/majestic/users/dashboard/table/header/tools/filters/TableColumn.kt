package majestic.users.dashboard.table.header.tools.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ChoiceColors
import majestic.ColorPair
import majestic.IconCheckCircle
import majestic.IconCheckColors
import majestic.Popup
import majestic.SelectColors
import majestic.navigation.MenuItemColors
import majestic.popup.Inline
import majestic.popup.Item
import majestic.popup.Items
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class TableColumnColors(
    val pop: ColorPair,
    val selector: SelectColors,
    val choice: ChoiceColors,
    val check: IconCheckColors,
    val menuItem: MenuItemColors
)

@Composable
internal fun TableColumn(
    modifier: Modifier,
    expanded: Boolean,
    colors: TableColumnColors,
    selected: List<String>,
    icon: DrawableResource,
    columns: List<String>,
    onClick: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val popColors = colors.pop
    Popup(
        modifier = modifier,
        inline = Inline(modifier = Modifier.fillMaxSize(), alignment = Alignment.Center) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(icon),
                tint = popColors.foreground,
                contentDescription = "Icon"
            )
        },
        items = Items(
            data = columns,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.width(IntrinsicSize.Max)
                .clip(RoundedCornerShape(12.dp))
                .background(popColors.background),
            item = Item(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                val active = selected.contains(it)
                val c = colors.choice
                val color = if (active) c.selected else c.unselected

                NavItem(
                    modifier = Modifier.navItem().onClick { onClick(it) },
                    label = it,
                    shape = RoundedCornerShape(20.dp),
                    leading = {
                        Box(Modifier.padding(end = 10.dp)) {
                            IconCheckCircle(
                                size = 16.dp,
                                selected = active,
                                colors = IconCheckColors(
                                    background = color.icon.background,
                                    border = color.border,
                                    icon = color.icon
                                )
                            )
                        }
                    },
                    style = LocalTextStyle.current.copy(
                        color = colors.pop.foreground,
                        fontSize = 14.sp,
                        lineHeight = 1.sp
                    ),
                    colors = colors.menuItem
                )
            }
        ),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    )
}