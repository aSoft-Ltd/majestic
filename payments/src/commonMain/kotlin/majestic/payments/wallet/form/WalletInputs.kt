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
import majestic.payments.tools.form.PaymentFormColors

@Composable
internal fun WalletInputs(
    colors: PaymentFormColors,
    orientation: ScreenOrientation,
    methods: List<PaymentMethod>,
    modifier: Modifier = Modifier
) = Column(modifier = modifier) {
    var title by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.padding(bottom = 20.dp),
        value = title,
        hint = "e.g School Fees",
        colors = colors.textField,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = "Wallet Name",
                color = colors.foreground.copy(alpha = 0.5f)
            )
        },
        onChange = { title = it }
    )
    PaymentMethods(
        description = "Select at least 1 and more payment account",
        colors = colors.card,
        methods = methods,
        orientation = orientation
    )
}
