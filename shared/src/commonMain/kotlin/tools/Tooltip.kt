package tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tooltip(
    text: String,
    colors: ColorPair,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)

    TooltipBox(
        modifier = modifier,
        positionProvider = tooltipPosition,
        state = tooltipState,
        tooltip = {
            Text(
                text,
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(colors.background)
                    .padding(10.dp),
                color = colors.foreground,
                fontSize = 12.sp,
                lineHeight = 0.1.sp,
            )
        },
        content = content
    )
}