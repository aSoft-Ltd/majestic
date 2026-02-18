package majestic.shared.profiles.contacts.tools.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.buttons.ExpandDirection
import majestic.buttons.FloatingActionButton
import majestic.shared.button.Button
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogState
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogState
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.users.label.contacts.OptionLabels

fun Modifier.flatButton(
    interactionSource: MutableInteractionSource,
    bgColor: Color
) = this
    .clip(CircleShape)
    .background(bgColor)
    .pointerHoverIcon(PointerIcon.Hand)
    .hoverable(interactionSource = interactionSource)
    .padding(vertical = 10.dp, horizontal = 20.dp)


@Composable
internal fun PortraitButtons(
    modifier: Modifier,
    colors: ContactsColors,
    labels: OptionLabels,
    isOpen: Boolean,
    onDismiss: () -> Unit,
    emailDialog: EmailDialogState,
    phoneDialog: PhoneDialogState
) = FloatingActionButton(
    modifier = modifier,
    direction = ExpandDirection.UP,
    expanded = isOpen,
    color = colors.floatingButton,
    content = {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                theme = colors.theme,
                label = labels.email,
                onClick = {
                    onDismiss()
                    emailDialog.open(Add)
                },
            )
            Button(
                onClick = {
                    onDismiss()
                    phoneDialog.open(Add)
                },
                theme = colors.theme,
                label = labels.phone,
            )
        }
    }
)