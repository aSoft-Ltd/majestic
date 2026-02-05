package majestic.payments.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.CountryTooltip
import nation.Country

class InfoTag(
    val text: String,
    val color: Color
) {
    companion object
}

@Composable
internal fun InfoTags(
    country: Country? = null,
    colors: ColorPair,
    tags: List<InfoTag>,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    if (country != null) CountryTooltip(
        modifier = Modifier.size(12.dp).clip(CircleShape),
        colors = colors,
        country = country
    )
    tags.forEach { tag ->
        Text(
            modifier = Modifier.clip(RoundedCornerShape(3.dp))
                .background(tag.color.copy(0.1f))
                .padding(horizontal = 4.dp, vertical = 2.dp),
            text = tag.text,
            color = tag.color.copy(0.5f),
            fontSize = 10.sp,
            lineHeight = 1.sp
        )
    }
}
