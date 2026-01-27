package majestic.payments.tools.form

import androidx.compose.ui.graphics.Color
import majestic.TextFieldColors
import majestic.payments.wallet.form.PaymentCardColors

data class PaymentFormColors(
    val foreground: Color,
    val background: Color,
    val header: FormHeaderColors,
    val footer: FormFooterColors,
    val card: PaymentCardColors,
    val textField: TextFieldColors
)
