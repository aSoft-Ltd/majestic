package majestic.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import majestic.calendar.tools.CellContext

fun Modifier.cell(
    ctx: CellContext<*>,
    shape: Shape = RoundedCornerShape(8.dp),
    padding: Dp = 4.dp
) = ctx.modifier.background(
    color = ctx.toColorPair(ctx.colors).background,
    shape = shape
).padding(padding)