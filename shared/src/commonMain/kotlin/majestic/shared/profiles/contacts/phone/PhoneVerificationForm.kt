package majestic.shared.profiles.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import majestic.ActionText
import majestic.ColorPair
import majestic.button.appearence.constructiveFormButton
import majestic.button.basic.FormButton
import majestic.buttons.ButtonColors
import majestic.shared.profiles.contacts.tools.dialogs.ContactVerificationFormLabels
import majestic.shared.profiles.contacts.tools.dialogs.OtpInput
import majestic.shared.profiles.contacts.tools.dialogs.OtpInputColors

data class PhoneVerificationFormColors(
    val sentCode: Color,
    val otp: OtpInputColors,
    val enterCode: Color,
    val number: Color,
    val sendCode: Color,
    val changeContact: Color,
    val submit: ButtonColors,
    val button: ColorPair,
)

@Composable
internal fun PhoneVerificationForm(
    colors: PhoneVerificationFormColors,
    number: String = "+255 752 988 988",
    labels: ContactVerificationFormLabels,
    onVerify: () -> Unit,
    onChangePhone: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = if (orientation is Landscape) Arrangement.spacedBy(20.dp) else Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = labels.sentCode,
            color = colors.sentCode
        )
        Text(
            text = number,
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

    if (orientation == Portrait) {
        Spacer(modifier = Modifier.height(24.dp))
    }

    FormButton(
        text = labels.submit,
        modifier = Modifier
            .fillMaxWidth()
            .constructiveFormButton(
                colors = colors.button, onClick = onVerify
            )
    )

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        ActionText(
            label = labels.resendCode,
            onClick = {},
        )
        ActionText(
            label = labels.changeContact,
            onClick = onChangePhone,
        )
    }
}