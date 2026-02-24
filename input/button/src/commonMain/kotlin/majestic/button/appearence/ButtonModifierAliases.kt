package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import majestic.ColorPair

@Composable
fun Modifier.closeModalIconButton(
    color: Color,
    onClick: () -> Unit = { },
): Modifier = translucentButton(color = color, onClick = onClick, alpha = 0.05f)

@Composable
fun Modifier.cardButton(
    color: Color,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = { },
): Modifier = this
    .translucentButton(
        color = color,
        enabled = enabled,
        shape = shape,
        source = source,
        onClick = onClick,
        alpha = 0.03f
    )
    .widthIn(max = 120.dp)
    .padding(horizontal = 15.dp, vertical = 8.dp)

@Composable
fun Modifier.cardButtonMobile(
    color: Color,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = { },
): Modifier = translucentButton(
    color = color,
    enabled = enabled,
    shape = shape,
    source = source,
    onClick = onClick,
    alpha = 0.03f
)

@Composable
fun Modifier.translucentFormButton(
    color: Color,
    onClick: () -> Unit = { },
): Modifier = this
    .translucentButton(color = color, onClick = onClick, alpha = 0.05f)
    .padding(horizontal = 20.dp, vertical = 7.dp)

@Composable
fun Modifier.translucentFormIconButton(
    color: Color,
    onClick: () -> Unit = { },
): Modifier = translucentButton(color = color, onClick = onClick, alpha = 0.05f)

@Composable
fun Modifier.constructiveFormButton(
    colors: ColorPair,
    onClick: () -> Unit = { },
): Modifier = this
    .solidButton(colors = colors, onClick = onClick)
    .padding(horizontal = 20.dp, vertical = 7.dp)

@Composable
fun Modifier.plusButton(
    colors: ColorPair,
    onClick: () -> Unit = { },
    source: MutableInteractionSource = remember { MutableInteractionSource() },
): Modifier = this
    .then(PlusButtonElement(source))
    .solidButton(colors = colors, onClick = onClick, source = source)

@Composable
fun Modifier.listItemIconButton(
    color: Color,
    alpha: Float = 0f,
    onClick: () -> Unit = { },
): Modifier = translucentButton(color = color, onClick = onClick, alpha = alpha)

@Composable
fun Modifier.dropdownTrigger(
    color: Color,
    onClick: () -> Unit = { },
): Modifier = this
    .translucentButton(color = color, onClick = onClick, alpha = 0.05f)
    .padding(horizontal = 20.dp, vertical = 7.dp)