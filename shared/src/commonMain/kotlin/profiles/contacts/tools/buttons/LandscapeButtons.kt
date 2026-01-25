package profiles.contacts.tools.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import majestic.buttons.FlatButton
import profiles.contacts.tools.ContactsColors

@Composable
internal fun LandscapeButtons(
    buttonBox: Rect,
    colors: ContactsColors,
    onEmailButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit
) = Popup(
    alignment = Alignment.TopEnd,
    offset = IntOffset(x = 0, y = buttonBox.bottom.toInt() + 20)
) {
    val firstInteractionSource = remember { MutableInteractionSource() }
    val firstHovered by firstInteractionSource.collectIsHoveredAsState()
    val secondInteractionSource = remember { MutableInteractionSource() }
    val secondHovered by secondInteractionSource.collectIsHoveredAsState()
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FlatButton(
            modifier = Modifier.flatButton(
                interactionSource = firstInteractionSource,
                bgColor = getBackground(
                    isHovered = firstHovered,
                    colors = colors.flatButton
                )
            ) {
                onEmailButtonClick()
            },
            colors = colors.flatButton,
            label = "Email",
            interactionSource = firstInteractionSource
        )
        FlatButton(
            modifier = Modifier.flatButton(
                interactionSource = firstInteractionSource,
                bgColor = getBackground(
                    isHovered = secondHovered,
                    colors = colors.flatButton
                )
            ) {
                onPhoneButtonClick()
            },
            colors = colors.flatButton,
            label = "Email",
            interactionSource = secondInteractionSource
        )
    }
}