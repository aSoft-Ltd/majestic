package majestic.users.dashboard.roles.tools

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
import org.jetbrains.compose.resources.vectorResource

data class CardStatColors(
    val tint: Color,
    val title: Color,
    val value: Color
)

@Composable
fun CardStat(
    stat: Stat,
    colors: CardStatColors,
    modifier: Modifier = Modifier
) = Row(
    horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
) {
    Icon(
        imageVector = vectorResource(stat.icon),
        contentDescription = stat.description,
        tint = colors.tint,
        modifier = Modifier.size(16.dp)
    )

    Text(
        text = stat.title,
        color = colors.title,
        fontSize = 14.sp
    )

    Text(
        text = "${stat.value}",
        color = colors.value,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
}