package majestic.users.profile.contacts.email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ActionButton
import majestic.ActionText
import majestic.ButtonColors
import majestic.users.labels.profile.contact.ContactVerificationFormLabels
import majestic.users.profile.contacts.tools.OtpInput
import majestic.users.profile.contacts.tools.OtpInputColors

data class EmailVerificationFormColors(
    val buttonColors: ButtonColors,
    val otp: OtpInputColors,
    val description: Color,
    val email: Color,
    val enterCode: Color
)

@Composable
internal fun EmailVerificationForm(
    colors: EmailVerificationFormColors,
    labels: ContactVerificationFormLabels,
    orientation: ScreenOrientation,
    onVerify: () -> Unit,
    onChangeEmail: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = if (orientation is Landscape) Arrangement.spacedBy(20.dp) else Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Column(
        modifier = Modifier
            .then(if (orientation is Portrait) Modifier.padding(top = 20.dp) else Modifier)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = labels.sentCode,
                color = colors.description
            )
            Text(
                text = "amanihamduni@gmail.com",
                fontWeight = FontWeight.Bold,
                color = colors.email
            )
            Text(
                text = labels.enterCode,
                color = colors.enterCode
            )
        }

        var otp by remember { mutableStateOf("") }
        OtpInput(
            value = otp,
            length = 5,
            colors = colors.otp,
            onValueChange = { otp = it }
        )
        if (orientation is Landscape) ActionButton(
            modifier = Modifier.fillMaxWidth(),
            text = labels.submit,
            colors = colors.buttonColors,
            onClick = onVerify
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionText(
                label = labels.resendCode,
                onClick = {}
            )
            ActionText(
                label = labels.changeContact,
                onClick = onChangeEmail
            )
        }
    }

    if (orientation is Portrait) ActionButton(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        text = labels.submit,
        colors = colors.buttonColors,
        onClick = onVerify
    )
}