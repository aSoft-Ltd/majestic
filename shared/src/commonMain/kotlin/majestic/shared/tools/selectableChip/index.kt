package majestic.shared.tools.selectableChip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_checkmark_circle_01
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectableChip(
    onSelectedChange: (Boolean) -> Unit,
    label: String,
    selected: Boolean,
    colors: SelectableChipColors,
    contentPadding: PaddingValues = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
    textSize: TextUnit = 14.sp,
    modifier: Modifier = Modifier
) = Box(modifier = modifier, propagateMinConstraints = true) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val stateColors = when {
        selected -> colors.selected
        hovered -> colors.hovered
        else -> colors.default
    }
    val background by animateColorAsState(
        targetValue = stateColors.background,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "SelectableChipBackground"
    )
    val text by animateColorAsState(
        targetValue = stateColors.text,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "SelectableChipText"
    )
    val indicator by animateColorAsState(
        targetValue = stateColors.indicator,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "SelectableChipIndicator"
    )

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .toggleable(
                value = selected,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Checkbox,
                onValueChange = onSelectedChange
            )
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selected) Icon(
            painter = painterResource(Res.drawable.ic_checkmark_circle_01),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = indicator
        )
        else Box(
            modifier = Modifier
                .size(20.dp)
                .border(width = 2.dp, color = indicator, shape = CircleShape)
        )
        Text(
            text = label,
            color = text,
            fontSize = textSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}