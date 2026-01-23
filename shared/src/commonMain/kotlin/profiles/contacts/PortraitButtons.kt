package profiles.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.buttons.ExpandDirection
import majestic.buttons.FlatButton
import majestic.buttons.FloatingActionButton
import profiles.contacts.email.dialogs.EmailDialogState
import profiles.contacts.phone.dialogs.PhoneDialogState
import profiles.contacts.tools.dialogs.Add


@Composable
internal fun PortraitButtons(
    modifier: Modifier,
    colors: ContactsColors,
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
            FlatButton(
                modifier = Modifier.clip(CircleShape),
                colors = colors.flatButton,
                label = "Email",
                onClick = {
                    onDismiss()
                    emailDialog.open(Add)
                }
            )
            FlatButton(
                modifier = Modifier.clip(CircleShape),
                colors = colors.flatButton,
                label = "Phone",
                onClick = {
                    onDismiss()
                    phoneDialog.open(Add)
                }
            )
        }
    }
)