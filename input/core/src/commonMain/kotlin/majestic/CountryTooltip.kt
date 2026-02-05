package majestic

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nation.Country
import nation.Flag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryTooltip(
    colors: ColorPair,
    country: Country,
    modifier: Modifier = Modifier
) {
    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)

    TooltipBox(
        modifier = modifier,
        positionProvider = tooltipPosition,
        state = tooltipState,
        tooltip = {
            Text(
                country.label,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(colors.background)
                    .padding(8.dp),
                color = colors.foreground,
                fontSize = 10.sp,
                lineHeight = 1.sp,
            )
        },
    ) {
        Flag(
            country = country,
            modifier = modifier,
            scale = ContentScale.Crop
        )
    }
}
