package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import majestic.ColorPair

@Composable
fun Modifier.expandableButton(
    colors: ColorPair,
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
): Modifier = this
    .then(ExpandableButtonElement(source))
    .solidButton(
        colors = colors,
        source = source,
        expandable = true,
        onClick = onClick
    )