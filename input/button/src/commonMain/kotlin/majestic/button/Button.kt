package majestic.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.tooling.onClick

@Composable
fun Modifier.button(
    button: ButtonController
): Modifier {
    val color = button.toColorPair()
    return this
        .hoverable(button.source)
        .clickable(interactionSource = button.source, indication = null, enabled = button.disabled, onClickLabel = null, role = null, onClick = {})
        .background(color = color.background, shape = button.shape)
}

@Composable
fun rememberButton(
    colors: ButtonState<ColorPair> = ButtonState(
        default = ColorPair(Color.White, Color.Magenta),
        hovered = ColorPair(Color.Black, Color.White),
        pressed = ColorPair(Color.Black, Color.Yellow),
    ),
    shape: Shape = RoundedCornerShape(8.dp),
    disabled: Boolean = false,
    onClick: () -> Unit = {}
) = remember(colors, disabled, shape) {
    ButtonController(colors, disabled, shape, onClick)
}

@Composable
fun Button(
    button: ButtonController,
    label: @Composable ColorPair.() -> Unit,
    modifier: Modifier = Modifier.button(button)
) {
    LaunchedEffect(button) {
        button.source.interactions.collect { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> button.updateHovered(true)
                is HoverInteraction.Exit -> button.updateHovered(false)
                is PressInteraction.Press -> button.updatePressed(true)
                is PressInteraction.Release -> button.updatePressed(false)
            }
        }
    }
    Row(
        modifier = modifier.onClick(button.onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        button.toColorPair().label()
    }
}

@Composable
fun Button2(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val ele = modifier.registered()
    Text("ele = $ele")
}

private fun Modifier.registered(): ButtonElement? {
    var ele: ButtonElement? = null
    any {
        val res = it is ButtonElement
        if (res) ele = it
        res
    }
    return ele
}