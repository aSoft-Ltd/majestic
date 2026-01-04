package majestic.users.profile.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import majestic.ActionButton
import majestic.ActionText
import majestic.ButtonColors
import majestic.users.labels.profile.contact.ContactVerificationFormLabels
import majestic.users.profile.contacts.tools.OtpInput
import majestic.users.profile.contacts.tools.OtpInputColors

data class PhoneVerificationFormColors(
    val surfaceActual: Color,
    val surfaceContra: Color,
    val otp: OtpInputColors
)

@Composable
internal fun PhoneVerificationForm(
    colors: PhoneVerificationFormColors,
    labels: ContactVerificationFormLabels,
    onVerify: () -> Unit,
    onChangePhone: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(40.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = labels.title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colors.surfaceContra
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = labels.sentCode,
            color = colors.surfaceContra
        )
        Text(
            text = "+255 752 988 988",
            fontWeight = FontWeight.Bold,
            color = colors.surfaceContra
        )
        Text(
            text = labels.enterCode,
            color = colors.surfaceContra
        )
    }

    var otp by remember { mutableStateOf("") }
    OtpInput(
        value = otp,
        length = 5,
        colors = colors.otp,
        onValueChange = { otp = it }
    )
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        text = labels.submit,
        colors = ButtonColors(
            contentColor = colors.surfaceActual,
            containerColor = colors.surfaceContra
        ),
        onClick = onVerify
    )
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        ActionText(
            label = labels.resendCode,
            onClick = {}
        )
        ActionText(
            label = labels.changeEmail,
            onClick = onChangePhone
        )
    }
}