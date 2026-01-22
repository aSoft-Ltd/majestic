package majestic.users.table.header.tools.filters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.SelectColors
import majestic.ThemeColor
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import users.label.table.FilterLabels

data class FilterRangeColors(
    val selector: SelectColors,
    val popCompColors: ColorPair,
    val theme: ThemeColor
)

fun RowScope.inputModifier(theme: ThemeColor) = Modifier
    .weight(1f)
    .clip(RoundedCornerShape(8.dp))
    .border(1.dp, theme.dominant.actual.color, RoundedCornerShape(8.dp))
    .padding(horizontal = 10.dp, vertical = 3.dp)

@Composable
internal fun FilterRange(
    from: String,
    to: String,
    labels: FilterLabels,
    colors: FilterRangeColors,
    caretDown: DrawableResource,
    hint: String = "Range",
    onFrom: (String) -> Unit = {},
    onTo: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
    var expanded by remember { mutableStateOf(false) }
    val icon = vectorResource(caretDown)
    val selectColors = colors.selector
    val popCompColors = colors.popCompColors

    Placeholder(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = if (expanded) selectColors.focused.border else selectColors.blurred.border,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .onClick { expanded = !expanded },
        colors = selectColors,
        isExpanded = expanded,
        icon = icon
    ) {
        if (from.isNotEmpty() && to.isNotEmpty()) Text(
            "$from - $to",
            color = popCompColors.foreground,
            fontSize = 12.sp
        )
        else Text(hint, color = popCompColors.foreground.copy(0.5f), fontSize = 12.sp)
    }

    if (expanded) Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        InlinedTextField(
            modifier = inputModifier(colors.theme),
            value = from,
            hint = labels.from,
            onChange = onFrom,
            cursor = popCompColors.foreground.copy(0.8f),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = popCompColors.foreground.copy(0.8f),
            ),
            hintStyle = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = popCompColors.foreground.copy(0.5f),
            ),
        )
        Text("-", color = popCompColors.foreground)
        InlinedTextField(
            modifier = inputModifier(colors.theme),
            value = to,
            hint = labels.to,
            onChange = onTo,
            cursor = popCompColors.foreground.copy(0.8f),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = popCompColors.foreground.copy(0.8f),
            ),
            hintStyle = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = popCompColors.foreground.copy(0.5f),
            ),
        )
    }
}

@Composable
private fun Placeholder(
    colors: SelectColors,
    isExpanded: Boolean = false,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) { content() }
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(12.dp).wrapContentSize().graphicsLayer { rotationX = animateRotation },
            imageVector = icon,
            contentDescription = "Dropdown Arrow",
            tint = if (isExpanded) colors.focused.text else colors.blurred.text
        )
    }
}
