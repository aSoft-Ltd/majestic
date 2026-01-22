package majestic.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import majestic.icons.ic_plus_sign
import majestic.icons.Res

@Composable
internal fun CloseButton(
    modifier: Modifier,
    size: Dp = 35.dp,
    tint: Color = Color(0xFFF),
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    Icon(
        modifier = Modifier
            .size(size)
            .padding(5.dp)
            .graphicsLayer { rotationZ = 45f },
        painter = painterResource(Res.drawable.ic_plus_sign),
        tint = tint,
        contentDescription = "Icon"
    )
}