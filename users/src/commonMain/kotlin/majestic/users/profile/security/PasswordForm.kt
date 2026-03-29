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
import majestic.NoRippleInteractionSource
import majestic.TextField
import majestic.button.appearence.constructiveFormButton
import majestic.button.basic.FormButton
import majestic.icons.Res
import majestic.icons.ic_info_circle
import majestic.icons.ic_view
import majestic.icons.ic_view_off
import majestic.shared.users.label.profile.security.PasswordFormLabels
import majestic.shared.users.profile.SecurityColors
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun PasswordForm(
    colors: SecurityColors,
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
        color = colors.foreground
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
        val oldPassVisual =
            if (oldPassShow) VisualTransformation.None else PasswordVisualTransformation()
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
                    color = colors.foreground.copy(alpha = 0.7f)
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
                    tint = colors.foreground
                )
            },
            onChange = { oldPass = it }
        )

        var newPass by remember { mutableStateOf("") }
        var newPassShow by remember { mutableStateOf(false) }
        val newPassVisual =
            if (newPassShow) VisualTransformation.None else PasswordVisualTransformation()
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
                    color = colors.foreground.copy(alpha = 0.7f)
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
                    tint = colors.foreground
                )
            },
            onChange = { newPass = it }
        )
    }

    // TODO: Use modal footer
    FormButton(
        text = labels.submit,
        modifier = Modifier.fillMaxWidth().constructiveFormButton(
            colors = colors.solidConstructive,
            onClick = onSubmit
        )
    )
}