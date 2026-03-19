package majestic.payments.wallet.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.dialogs.flexible.FlexibleDialog
import majestic.icons.Res
import majestic.icons.ic_wallet_02_solid
import majestic.payments.labels.wallet.form.NewWalletLabels
import majestic.payments.tools.form.PaymentFormColors
import majestic.payments.wallet.tools.PaymentMethod
import majestic.shared.tools.modal.ModalFooter
import majestic.shared.tools.modal.ModalFooterLabels
import majestic.shared.tools.modal.ModalHeader
import majestic.shared.tools.modal.modalFooterStyle
import majestic.shared.tools.modal.modalHeaderStyle
import org.jetbrains.compose.resources.painterResource

@Composable
fun CreateWallet(
    labels: NewWalletLabels,
    colors: PaymentFormColors,
    orientation: ScreenOrientation,
    methods: List<PaymentMethod>,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        FlexibleDialog(
            onDismiss = onDismiss,
            modifier = modifier,
            bar = {
                ModalHeader(
                    title = labels.header.label,
                    subtitle = labels.header.description,
                    iconPainter = painterResource(Res.drawable.ic_wallet_02_solid),
                    onClose = onDismiss,
                    colors = colors.modalColors,
                    orientation = orientation,
                    modifier = Modifier.modalHeaderStyle(colors = colors.modalColors)
                )
            }
        ) {
            Column {
                WalletInputs(
                    labels = labels,
                    colors = colors,
                    methods = methods,
                    orientation = orientation,
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .then(
                            if (orientation is Landscape) Modifier.padding(20.dp) else Modifier.padding(12.dp)
                        )
                )
                ModalFooter(
                    modifier = Modifier.modalFooterStyle(this),
                    labels = ModalFooterLabels(
                        primary = labels.submitBtn,
                        secondary = labels.cancelBtn
                    ),
                    colors = colors.modalFooterColors,
                    onSubmit = { onSubmit() },
                    onClose = { onCancel() }
                )
            }
        }
    }
}