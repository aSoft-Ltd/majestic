package majestic.shared.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.button.appearence.plusButton
import majestic.button.basic.PlusButton
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class EmptyStateColors(
    val foreground: Color,
    val button: ColorPair,
    val iconBlur: Color,
    val gradient: Pair<Color, Color>,
)

@Composable
fun EmptyState(
    icon: DrawableResource,
    title: String,
    description: String,
    colors: EmptyStateColors,
    action: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GradientIcon(icon = icon, colors = colors, modifier = Modifier.size(83.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    color = colors.foreground,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = description,
                    color = colors.foreground.copy(alpha = 0.5f),
                    fontSize = 10.sp,
                    lineHeight = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(224.dp)
                )
            }
        }

        if (action != null) {
            PlusButton(
                modifier = Modifier.plusButton(
                    colors = colors.button,
                    onClick = action,
                )
            )
        }
    }
}

@Composable
private fun GradientIcon(icon: DrawableResource, colors: EmptyStateColors, modifier: Modifier = Modifier) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    val ellipseSize = 56.dp
    val blurRadius = 32.dp
    val blurLayerSize = ellipseSize + blurRadius * 6
    val blurAlpha = 0.3f

    Box(
        modifier = Modifier
            .requiredSize(blurLayerSize)
            .offset(x = 0.dp, y = (-4).dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .blur(
                radius = blurRadius,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(ellipseSize)
                .background(
                    color = colors.iconBlur.copy(alpha = blurAlpha),
                    shape = CircleShape
                )
        )
    }

    Image(
        imageVector = vectorResource(icon),
        contentDescription = null,
        modifier = Modifier
            .size(83.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()

                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colors.gradient.first,
                            colors.gradient.second
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height)
                    ),
                    blendMode = BlendMode.SrcIn
                )
            }
    )
}