package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.users.labels.roles.AssignRoleModalLabels
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RolesColors
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_account_settings_filled

@Composable
fun AssignRoleHeader(
    userName: String,
    labels: AssignRoleModalLabels,
    colors: RolesColors,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color(0xFF202733))
            .padding(20.dp)

    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_account_settings_filled),
            contentDescription = "Account Settings Filled",
            tint = Color(0xFFD18C27),
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF000000).copy(alpha = 0.10f))
                .background(Color(0xFF1D2430))
                .padding(12.dp)
                .size(24.dp)
        )

        Text(
            text = "${labels.title} $userName",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.theme.surface.contra.color
        )
    }
}