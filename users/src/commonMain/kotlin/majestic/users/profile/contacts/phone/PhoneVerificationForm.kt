package majestic.users.profile.contacts.phone

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

data class PhoneVerificationFormColors(
    val sentCode: Color,
    val otp: OtpInputColors,
    val enterCode:Color,
    val number:Color,
    val sendCode: Color,
    val changeContact: Color,
    val submit: ButtonColors
)

@Composable
internal fun PhoneVerificationForm(
    colors: PhoneVerificationFormColors,
    labels: ContactVerificationFormLabels,
    onVerify: () -> Unit,
    onChangePhone: () -> Unit,
    orientation: ScreenOrientation,
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
                color = colors.sentCode
            )
            Text(
                text = "+255 752 988 988",
                fontWeight = FontWeight.Bold,
                color = colors.number
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
            colors = colors.submit,
            onClick = onVerify
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionText(
                label = labels.resendCode,
                onClick = {},
                labelColor = colors.sendCode
            )
            ActionText(
                label = labels.changeContact,
                onClick = onChangePhone,
                labelColor = colors.changeContact
            )
        }
    }
    if (orientation is Portrait) ActionButton(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        text = labels.submit,
        colors = colors.submit,
        onClick = onVerify
    )
}