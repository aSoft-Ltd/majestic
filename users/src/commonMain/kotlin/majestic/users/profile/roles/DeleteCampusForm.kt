package majestic.users.profile.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.users.labels.profile.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton

@Composable
fun DeleteCampusForm(
    campusName: String,
    labels: RolesLabels.DeleteCampusLabels,
    colors: RolesColors,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = labels.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.theme.surface.contra.color
        )

        Text(
            text = labels.message.replace("{campusName}", campusName),
            fontSize = 16.sp,
            color = colors.theme.surface.contra.color.copy(0.7f),
            lineHeight = 24.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FlatButton(
                modifier = Modifier.weight(1f),
                label = labels.cancelButton,
                colors = colors.flatButton,
                onClick = onDismiss
            )
            FlatButton(
                modifier = Modifier.weight(1f),
                label = labels.confirmButton,
                colors = colors.flatButton,
                onClick = onDelete
            )
        }
    }
}
