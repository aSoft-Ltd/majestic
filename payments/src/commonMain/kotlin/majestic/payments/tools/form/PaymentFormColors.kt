package majestic.payments.tools.form

import androidx.compose.ui.graphics.Color
import majestic.TextFieldColors
import majestic.dialogs.DialogColors
import majestic.payments.wallet.tools.PaymentCardColors
import majestic.shared.tools.modal.ModalColors
import majestic.shared.tools.modal.ModalFooterColors

data class PaymentFormColors(
    val foreground: Color,
    val card: PaymentCardColors,
    val textField: TextFieldColors,
    val modalFooterColors: ModalFooterColors,
    val modalColors: ModalColors,
)