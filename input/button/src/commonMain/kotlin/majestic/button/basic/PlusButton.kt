package majestic.button.basic

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import majestic.button.Button
import majestic.button.appearence.PlusButtonElement
import majestic.icons.Res
import majestic.icons.ic_add
import org.jetbrains.compose.resources.vectorResource

@Composable
fun PlusButton(modifier: Modifier = Modifier) {
    val iconInteraction = modifier.source()
    val iconHovered by iconInteraction.collectIsHoveredAsState()

    Button(modifier = modifier) { colors ->
        BasicButtonContent(
            icon = vectorResource(Res.drawable.ic_add),
            rotation = if (iconHovered) 180f else 0f,
            colors = colors
        )
    }
}

@Composable
private fun Modifier.source(): MutableInteractionSource {
    var source: MutableInteractionSource? = null

    any { element ->
        (element as? PlusButtonElement)?.let { source = it.source } != null
    }

    return source ?: remember { MutableInteractionSource() }
}