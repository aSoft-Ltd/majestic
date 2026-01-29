package majestic.shared.profiles.contacts.tools.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import majestic.shared.button.Button
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.users.label.contacts.OptionLabels

@Composable
internal fun LandscapeButtons(
    buttonBox: Rect,
    colors: ContactsColors,
    labels: OptionLabels,
    onEmailButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit
) = Popup(
    alignment = Alignment.TopEnd,
    offset = IntOffset(x = 0, y = buttonBox.bottom.toInt() + 20)
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = {
                onEmailButtonClick()
            },
            theme = colors.theme,
            label = labels.email
        )
        Button(
            onClick = {
                onPhoneButtonClick()
            },
            theme = colors.theme,
            label = labels.phone,
        )
    }
}