package majestic.button.appearence

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun Modifier.addContactOption(
    color: Color,
    hazeEdgeColor: Color,
    hazeMiddleColor: Color,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    source: MutableInteractionSource = remember { MutableInteractionSource() },
    hazeState: HazeState,
    onClick: () -> Unit = { },
): Modifier {
    return this
        .translucentButton(
            color = color,
            enabled = enabled,
            shape = shape,
            source = source,
            alpha = 0f,
            onClick = onClick,
        )
        .hazeEffect(
            state = hazeState,
            style = HazeMaterials.thin().copy(
                blurRadius = 25.dp,
                noiseFactor = 0.02f,
                backgroundColor = Color.Companion.Transparent,
                tints = listOf(
                    HazeTint(color = hazeEdgeColor),
                    HazeTint(color = hazeMiddleColor)
                )
            )
        )
        .width(120.dp)
        .padding(horizontal = 15.dp, vertical = 8.dp)
}