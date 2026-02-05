package majestic.payments.transaction.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.layouts.Flex
import majestic.layouts.FlexCol
import majestic.layouts.FlexRow
import majestic.payments.tools.InfoTag
import majestic.payments.tools.InfoTags
import nation.Country

@Composable
internal fun HeaderInfo(
    title: String,
    colors: ColorPair,
    tags: List<InfoTag>,
    orientation: ScreenOrientation,
    country: Country? = null,
    modifier: Modifier = Modifier
) = Flex(
    modifier = modifier,
    orientation = orientation,
    row = FlexRow(
        arrangement = Arrangement.spacedBy(10.dp),
        alignment = Alignment.CenterVertically
    ),
    col = FlexCol(arrangement = Arrangement.spacedBy(10.dp))
) {
    Text(
        text = title,
        color = colors.background,
        fontSize = if (orientation is Landscape) 18.sp else 14.sp,
        lineHeight = 1.sp
    )
    InfoTags(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        country = country,
        tags = tags,
        colors = colors,
    )
}
