package majestic.shared.profiles.contacts.email

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
import majestic.shared.profiles.contacts.tools.dialogs.ContactVerificationFormLabels
import majestic.shared.profiles.contacts.tools.dialogs.OtpInput
import majestic.shared.profiles.contacts.tools.dialogs.OtpInputColors

data class EmailVerificationFormColors(
    val button: ColorPair,
    val otp: OtpInputColors,
    val description: Color,
    val email: Color,
    val enterCode: Color
)

@Composable
fun EmailVerificationForm(
    colors: EmailVerificationFormColors,
    labels: ContactVerificationFormLabels,
    orientation: ScreenOrientation,
    onVerify: () -> Unit,
    onChangeEmail: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = if (orientation is Landscape) Arrangement.spacedBy(20.dp) else Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
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
            onClick = {}
        )

        ActionText(
            label = labels.changeContact,
            onClick = onChangeEmail
        )
    }
}