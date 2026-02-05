package majestic.payments.tools.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

class InfoEntryColors(
    val icon: Color,
    val text: Color
)

class InfoEntryItem(
    val icon: DrawableResource,
    val title: String,
    val description: String
) {
    companion object
}

@Composable
internal fun InfoEntry(
    title: String,
    description: String,
    colors: InfoEntryColors,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = null,
        tint = colors.icon.copy(alpha = 0.2f),
        modifier = Modifier.size(20.dp)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = title,
            color = colors.text.copy(0.8f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = description,
            color = colors.text.copy(alpha = 0.4f),
            fontSize = 10.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
