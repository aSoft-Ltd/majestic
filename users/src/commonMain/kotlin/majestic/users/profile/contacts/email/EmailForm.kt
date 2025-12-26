package majestic.users.profile.contacts.email

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ActionButton
import majestic.ButtonColors
import majestic.TextField
import majestic.TextFieldColors
import majestic.ThemeColor
import majestic.users.labels.profile.contact.DedicatedFormLabels
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_info_circle

data class EmailFormColors(
    val theme: ThemeColor,
    val field: TextFieldColors
)

@Composable
internal fun EmailForm(
    colors: EmailFormColors,
    labels: DedicatedFormLabels,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
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

    var email by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.padding(bottom = 20.dp),
        value = email,
        hint = labels.input.placeholder,
        colors = colors.field,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = labels.input.label,
                color = colors.theme.surface.contra.color.copy(alpha = 0.7f)
            )
        },
        onChange = { email = it }
    )
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        text = labels.submit,
        colors = ButtonColors(
            contentColor = colors.theme.surface.actual.color,
            containerColor = colors.theme.surface.contra.color
        ),
        onClick = onSubmit
    )
}