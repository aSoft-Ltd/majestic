package majestic.payments.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.SmartSelect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_arrow_down

internal data class ActionDropdownItem(
    val label: String,
    val icon: DrawableResource? = null
)

@Composable
internal fun ActionDropdown(
    colors: ColorPair,
    actions: List<ActionDropdownItem>,
    placeholder: ActionDropdownItem,
    onAction: (ActionDropdownItem) -> Unit = {},
    modifier: Modifier = Modifier,
) = SmartSelect(
    modifier = modifier,
    items = actions,
    item = { Item(color = colors.foreground, action = it) },
    selected = { Placeholder(label = placeholder.label, colors = colors, icon = placeholder.icon) },
    placeholder = { Placeholder(label = placeholder.label, colors = colors, icon = placeholder.icon) },
    onChange = { it?.let(onAction) },
    drawerContainerColor = colors.background,
    shape = CircleShape,
    dropDownShape = RoundedCornerShape(12.dp),
    dropdownModifier = Modifier.width(IntrinsicSize.Max)
)

@Composable
private fun Placeholder(label: String, colors: ColorPair, icon: DrawableResource?) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(CircleShape)
            .background(colors.foreground.copy(.1f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        if (icon != null) Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = colors.foreground,
            contentDescription = null,
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = colors.foreground,
            lineHeight = 0.1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.width(5.dp))
        Icon(
            modifier = Modifier.size(10.dp),
            painter = painterResource(Res.drawable.ic_arrow_down),
            tint = colors.foreground.copy(0.5f),
            contentDescription = null,
        )
    }
}

@Composable
private fun Item(color: Color, action: ActionDropdownItem) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    if (action.icon != null) Icon(
        modifier = Modifier.size(16.dp),
        painter = painterResource(action.icon),
        tint = color,
        contentDescription = null,
    )
    Text(
        text = action.label,
        fontSize = 14.sp,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
