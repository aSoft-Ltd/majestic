package majestic.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun graphVector(
    color: Color = Color(0xFF81C784),
) = ImageVector.Builder(
    name = "Graph vector",
    defaultWidth = 90.0.dp,
    defaultHeight = 36.0.dp,
    viewportWidth = 90.0f,
    viewportHeight = 36.0f
).apply {
    path(
        fill = linearGradient(
            0.0f to color.copy(0.3f), 1.0f to color.copy(0.0f),
            start = Offset(44.992f, 1.156f), end = Offset(44.992f, 36.004f)
        ), stroke = null,
        strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
        strokeLineMiter = 4.0f, pathFillType = NonZero
    ) {
        moveTo(17.799f, 17.977f)
        curveTo(11.077f, 21.949f, 4.151f, 22.865f, 0.383f, 23.782f)
        verticalLineTo(36.004f)
        horizontalLineTo(89.602f)
        verticalLineTo(1.172f)
        curveTo(86.424f, 0.927f, 82.88f, 3.615f, 79.824f, 6.672f)
        curveTo(76.769f, 9.728f, 63.63f, 27.142f, 46.214f, 17.977f)
        curveTo(28.798f, 8.811f, 24.521f, 14.005f, 17.799f, 17.977f)
        close()
    }
    path(
        fill = SolidColor(Color(0x00000000)), stroke = SolidColor(color),
        strokeLineWidth = 1.22217f, strokeLineCap = Butt, strokeLineJoin = Miter,
        strokeLineMiter = 4.0f, pathFillType = NonZero
    ) {
        moveTo(0.383f, 23.798f)
        curveTo(4.151f, 22.881f, 11.077f, 21.964f, 17.799f, 17.992f)
        curveTo(24.521f, 14.02f, 28.798f, 8.827f, 46.214f, 17.992f)
        curveTo(63.63f, 27.158f, 76.769f, 9.743f, 79.824f, 6.687f)
        curveTo(82.88f, 3.631f, 86.424f, 0.943f, 89.602f, 1.187f)
    }
}.build()
