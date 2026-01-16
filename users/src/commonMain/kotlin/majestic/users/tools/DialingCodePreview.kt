package majestic.users.tools

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import nation.Country
import nation.Flag

@Composable
internal fun DialingCodePreview(
    country: Country,
    color: Color = Color.Black,
    modifier: Modifier = Modifier
) = Row(modifier, verticalAlignment = Alignment.CenterVertically) {
    Flag(
        modifier = Modifier
            .size(26.dp)
            .clip(CircleShape),
        country = country,
        scale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text("+${country.dialingCode}", color = color)
    Icon(
        imageVector = Icons.Filled.ArrowDropDown,
        tint = color,
        contentDescription = null
    )
}