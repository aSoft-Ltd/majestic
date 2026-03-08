package majestic.shared.profiles.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.PhoneFieldColors
import majestic.shared.profiles.contacts.phone.form.ButtonsRow
import majestic.shared.profiles.contacts.phone.form.Form
import majestic.shared.profiles.contacts.tools.Info
import majestic.shared.profiles.contacts.tools.info
import majestic.shared.tools.modal.ModalFooter
import majestic.shared.tools.modal.ModalFooterColors
import majestic.shared.tools.modal.modalFooterStyle
import majestic.shared.users.label.contacts.DedicatedFormLabels

data class PhoneFormColors(
    val info: Color,
    val label: Color,
    val leadingIcon: ColorPair,
    val search: ColorPair,
    val codePreview: Color,
    val modalFooterColors: ModalFooterColors,
    val button: ColorPair,
    val phoneField: PhoneFieldColors,
)

@Composable
fun PhoneForm(
    colors: PhoneFormColors,
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

        Form(
            modifier = Modifier
                .then(
                    other = when (orientation) {
                        is Portrait -> Modifier.padding(horizontal = 20.dp)
                        is Landscape -> Modifier
                    }
                )
                .fillMaxWidth(),
            labels = labels,
            colors = colors
        )

        ButtonsRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            orientation = orientation,
            labels = labels,
            colors = colors
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