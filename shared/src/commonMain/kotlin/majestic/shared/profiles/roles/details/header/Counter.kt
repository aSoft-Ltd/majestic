package majestic.shared.profiles.roles.details.header

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
import majestic.icons.Res
import majestic.icons.ic_account_setting
import majestic.icons.ic_admission
import org.jetbrains.compose.resources.painterResource

data class CounterColors(
    val icon: Color,
    val title: Color
)

@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    colors: CounterColors,
    count: Int,
    labels: RolesHeaderLabels
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    Icon(
        painter = painterResource(Res.drawable.ic_account_setting),
        contentDescription = null,
        tint = colors.icon,
        modifier = Modifier.size(32.dp)
    )
    Text(
        text = "$count ${labels.roles}",
        color = colors.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
}