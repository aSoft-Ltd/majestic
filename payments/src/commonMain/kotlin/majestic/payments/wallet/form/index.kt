package majestic.payments.wallet.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.payments.labels.SectionLabels
import majestic.payments.tools.form.Footer
import majestic.payments.tools.form.Header
import majestic.payments.tools.form.PaymentFormColors
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_02_solid

@Composable
fun CreateWallet(
    colors: PaymentFormColors,
    orientation: ScreenOrientation,
    methods: List<PaymentMethod>,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    Header(
        modifier = Modifier.background(colors.header.background).then(
            if (orientation is Landscape) Modifier.padding(20.dp) else Modifier.padding(12.dp)
        ).fillMaxWidth(),
        colors = colors.header,
        labels = SectionLabels(
            label = "Create Payment",
            description = "Fill in the details to create a new payment"
        ),
        icon = Res.drawable.ic_wallet_02_solid,
    )
    WalletInputs(
        colors = colors,
        methods = methods,
        orientation = orientation,
        modifier = Modifier.weight(1f)
            .verticalScroll(rememberScrollState()).then(
                if (orientation is Landscape) Modifier.padding(20.dp) else Modifier.padding(12.dp)
            )
    )
    Footer(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        colors = colors.footer,
        submit = "Create Payment",
        cancel = "Cancel",
        onSubmit = { onSubmit() },
        onCancel = { onCancel() }
    )
}
