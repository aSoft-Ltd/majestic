package majestic.shared.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StatusBeacon(color: Color = Color(0xFF4CAF50), size: Dp = 5.dp, modifier: Modifier = Modifier) =
    Box(modifier = modifier.size(size).clip(CircleShape).background(color))
