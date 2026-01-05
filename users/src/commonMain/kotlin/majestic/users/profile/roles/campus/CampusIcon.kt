package majestic.users.profile.roles.campus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.users.profile.roles.CampusColors
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_school

@Composable
fun CampusIcon(colors: CampusColors, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(colors.iconBackground)
            .background(colors.theme.surface.contra.color.copy(alpha = 0.10f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(Res.drawable.ic_school),
            contentDescription = null,
            tint = colors.iconTint
        )
    }
}