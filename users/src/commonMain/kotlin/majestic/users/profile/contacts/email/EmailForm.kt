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
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
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
    val info: Color = Color(0xFFE5A134),
    val label: Color,//theme.surface.contra.color.copy(alpha = 0.7f)
    val field: TextFieldColors,
    val button: ButtonColors //ButtonColors(contentColor = colors.theme.surface.actual.color,containerColor = colors.theme.surface.contra.color)
)

@Composable
internal fun EmailForm(
    colors: EmailFormColors,
    labels: DedicatedFormLabels,
    onSubmit: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = when (orientation) {
        is Landscape -> Arrangement.spacedBy(30.dp)
        is Portrait -> Arrangement.SpaceBetween
    },
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colors.info.copy(0.04f))
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_info_circle),
                tint = colors.info,
                contentDescription = "Icon"
            )
            Text(
                text = labels.info,
                color = colors.info,
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
                    color = colors.label
                )
            },
            onChange = { email = it }
        )
    }
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        text = labels.submit,
        colors = colors.button,
        onClick = onSubmit
    )
}