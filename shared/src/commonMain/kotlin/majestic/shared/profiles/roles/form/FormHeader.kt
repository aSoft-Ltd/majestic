package majestic.shared.profiles.roles.form

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_vertical_settings
import majestic.shared.profiles.roles.RoleAssignmentColors
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun FormHeader(
    userName: String,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Row(
    modifier = Modifier.fillMaxWidth().padding(24.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(Res.drawable.ic_vertical_settings),
        contentDescription = null,
        tint = colors.titleIcon,
        modifier = Modifier.size(24.dp)
    )
    Spacer(Modifier.width(16.dp))
    Text(
        text = "${labels.title} $userName",
        color = colors.title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}
