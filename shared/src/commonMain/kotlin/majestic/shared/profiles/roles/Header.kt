package majestic.shared.profiles.roles

import academia.generated.resources.Res
import academia.generated.resources.ic_info_circle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor
import org.jetbrains.compose.resources.painterResource
data class HeaderColors(
    val background:Color,
    val title: Color
)
@Composable
internal fun Header(modifier: Modifier, theme: ThemeColor) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    Text(
        text = "Campuses",
        color = theme.surface.contra.color,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
    Icon(
        painter = painterResource(Res.drawable.ic_info_circle),
        contentDescription = "Info",
        tint = theme.surface.contra.color.copy(alpha = 0.7f),
        modifier = Modifier.size(16.dp)
    )
}