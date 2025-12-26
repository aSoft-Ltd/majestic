package majestic.users.profile.security

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ActionButton
import majestic.ButtonColors
import majestic.NoRippleInteractionSource
import majestic.TextField
import majestic.TextFieldColors
import majestic.ThemeColor
import majestic.users.labels.profile.security.PasswordFormLabels
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_info_circle
import tz.co.asoft.majestic_users.generated.resources.ic_view
import tz.co.asoft.majestic_users.generated.resources.ic_view_off

data class PasswordFormColors(
    val theme: ThemeColor,
    val field: TextFieldColors
)

@Composable
internal fun PasswordForm(
    colors: PasswordFormColors,
    labels: PasswordFormLabels,
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

    Column {
        var oldPass by remember { mutableStateOf("") }
        var oldPassShow by remember { mutableStateOf(false) }
        val oldPassVisual = if (oldPassShow) VisualTransformation.None else PasswordVisualTransformation()
        TextField(
            modifier = Modifier.padding(bottom = 20.dp),
            value = oldPass,
            hint = labels.oldPass.placeholder,
            colors = colors.field,
            visualTransformation = oldPassVisual,
            label = {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = labels.oldPass.label,
                    color = colors.theme.surface.contra.color.copy(alpha = 0.7f)
                )
            },
            trailingIcon = {
                val icon = if (oldPassShow) Res.drawable.ic_view_off else Res.drawable.ic_view
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = NoRippleInteractionSource,
                        indication = null,
                        onClick = { oldPassShow = !oldPassShow }
                    ),
                    painter = painterResource(icon),
                    contentDescription = "Icon",
                    tint = colors.theme.surface.contra.color
                )
            },
            onChange = { oldPass = it }
        )

        var newPass by remember { mutableStateOf("") }
        var newPassShow by remember { mutableStateOf(false) }
        val newPassVisual = if (newPassShow) VisualTransformation.None else PasswordVisualTransformation()
        TextField(
            modifier = Modifier.padding(bottom = 20.dp),
            value = newPass,
            hint = labels.newPass.placeholder,
            colors = colors.field,
            visualTransformation = newPassVisual,
            label = {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = labels.newPass.label,
                    color = colors.theme.surface.contra.color.copy(alpha = 0.7f)
                )
            },
            trailingIcon = {
                val icon = if (newPassShow) Res.drawable.ic_view_off else Res.drawable.ic_view
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = NoRippleInteractionSource,
                        indication = null,
                        onClick = { newPassShow = !newPassShow }
                    ),
                    painter = painterResource(icon),
                    contentDescription = "Icon",
                    tint = colors.theme.surface.contra.color
                )
            },
            onChange = { newPass = it }
        )
    }

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