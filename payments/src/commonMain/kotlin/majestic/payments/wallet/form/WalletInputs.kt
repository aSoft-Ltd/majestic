package majestic.payments.wallet.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.TextField
import majestic.payments.labels.wallet.form.NewWalletLabels
import majestic.payments.tools.form.PaymentFormColors
import majestic.payments.wallet.tools.PaymentMethod

@Composable
internal fun WalletInputs(
    labels: NewWalletLabels,
    colors: PaymentFormColors,
    orientation: ScreenOrientation,
    methods: List<PaymentMethod>,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    var title by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.padding(bottom = 20.dp),
        value = title,
        hint = labels.nameInput.placeholder,
        colors = colors.textField,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = labels.nameInput.label,
                color = colors.foreground.copy(alpha = 0.5f)
            )
        },
        onChange = { title = it }
    )
    PaymentMethods(
        description = labels.accounts,
        colors = colors.card,
        methods = methods,
        orientation = orientation
    )
}
