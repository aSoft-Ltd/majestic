package majestic.users.profile.contacts.tools

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import majestic.ActionButton
import majestic.ButtonColors
import majestic.ThemeColor
import majestic.users.labels.profile.contact.ContactDeleteFormLabels
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_info_circle

data class DeleteFormColors(
    val theme: ThemeColor,
    val cancel: ButtonColors,
    val submit: ButtonColors
)

@Composable
internal fun DeleteForm(
    colors: DeleteFormColors,
    labels: ContactDeleteFormLabels,
    onDismiss: () -> Unit,
    contact: String? = null,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(30.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = labels.title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colors.theme.surface.contra.color
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = labels.description,
            color = colors.theme.surface.contra.color
        )
        Text(
            text = contact ?: "",
            fontWeight = FontWeight.Bold,
            color = colors.theme.surface.contra.color
        )
    }
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFE5A134).copy(0.04f))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_info_circle),
            tint = Color(0xFFE5A134),
            contentDescription = "Icon"
        )
        Text(
            text = labels.info,
            color = Color(0xFFE5A134),
        )
    }
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        ActionButton(
            modifier = Modifier.weight(1f),
            text = labels.submit,
            colors = colors.submit,
            onClick = onDelete
        )
        ActionButton(
            modifier = Modifier.weight(1f),
            text = labels.cancel,
            border = BorderStroke(
                width = 1.dp,
                color = colors.theme.surface.contra.color.copy(alpha = 0.2f)
            ),
            colors = colors.cancel,
            onClick = onDismiss
        )
    }
}