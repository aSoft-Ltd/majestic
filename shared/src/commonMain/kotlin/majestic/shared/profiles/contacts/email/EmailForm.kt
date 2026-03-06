package majestic.shared.profiles.contacts.email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.TextField
import majestic.TextFieldColors
import majestic.shared.profiles.contacts.tools.Info
import majestic.shared.profiles.contacts.tools.info
import majestic.shared.tools.modal.ModalFooter
import majestic.shared.tools.modal.ModalFooterColors
import majestic.shared.tools.modal.modalFooterStyle
import majestic.shared.users.label.contacts.DedicatedFormLabels

data class EmailFormColors(
    val info: Color = Color(0xFFE5A134),
    val label: Color,
    val field: TextFieldColors,
    val modalFooterColors: ModalFooterColors,
)

@Composable
fun EmailForm(
    colors: EmailFormColors,
    labels: DedicatedFormLabels,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = when (orientation) {
        is Landscape -> Arrangement.spacedBy(16.dp)
        is Portrait -> Arrangement.Center
    },
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = when (orientation) {
            is Landscape -> Arrangement.spacedBy(30.dp)
            is Portrait -> Arrangement.Center
        },
        modifier = Modifier.then(if (orientation == Portrait) Modifier.weight(1f) else Modifier).padding(16.dp)
    ) {
        Info(
            modifier = Modifier.info(color = colors.info, orientation = orientation),
            color = colors.info,
            labels = labels.info
        )

        if (orientation == Portrait) {
            Spacer(modifier = Modifier.height(24.dp))
        }

        var email by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.padding(bottom = 20.dp),
            value = email,
            hint = labels.input.placeholder,
            colors = colors.field,
            label = {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = labels.input.label,
                    color = colors.label
                )
            },
            onChange = { email = it }
        )
    }

    ModalFooter(
        modifier = Modifier.modalFooterStyle(this),
        labels = labels.modalFooterLabels,
        colors = colors.modalFooterColors,
        onSubmit = onSubmit,
        onClose = onCancel
    )
}