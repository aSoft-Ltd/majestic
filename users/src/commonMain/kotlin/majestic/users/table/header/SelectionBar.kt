package majestic.users.table.header

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.tooling.onClick
import majestic.users.table.header.tools.Tooltip
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class SelectionBarColors(
    val counter: Color,
    val title: Color,
    val icons: ColorPair,
)

sealed interface CallBack
data object Delete : CallBack
data object SendMail : CallBack
data object Export : CallBack
data object Archive : CallBack
data object Cancel : CallBack
data object Assign : CallBack


data class Action(
    val icon: DrawableResource,
    val label: String,
    val callback: CallBack
)

data class SelectionBarProperties(
    val count: Int,
    val label: String,
    val colors: SelectionBarColors,
    val iconBackground: Boolean,
    val actions: List<Action>
)

@Composable
fun SelectionBar(
    modifier: Modifier,
    props: SelectionBarProperties,
    onActionClick: (CallBack) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${props.count}",
            fontSize = 16.sp,
            color = props.colors.counter
        )
        Text(
            text = props.label,
            fontSize = 16.sp,
            color = props.colors.counter
        )
    }
    Row(
        modifier = Modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        props.actions.forEach { item ->
            val interactionSource = remember { MutableInteractionSource() }
            val hovered by interactionSource.collectIsHoveredAsState()
            Tooltip(
                modifier = Modifier
                    .wrapContentSize()
                    .hoverable(interactionSource = interactionSource)
                    .onClick {
                        onActionClick(item.callback)
                    },
                text = item.label,
                colors = ColorPair(
                    foreground = props.colors.icons.foreground,
                    background = props.colors.icons.background
                ),
            ) {
                Icon(
                    imageVector = vectorResource(item.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .then(
                            if (props.iconBackground) Modifier
                                .background(
                                    color = if (hovered) props.colors.icons.foreground.copy(.1f) else props.colors.icons.foreground.copy(
                                        .06f
                                    ),
                                    shape = CircleShape
                                )
                                .padding(10.dp) else Modifier
                        )
                        .size(18.dp),
                    tint = if (hovered) props.colors.icons.foreground else props.colors.icons.foreground.copy(.5f)
                )
            }
        }
    }
}