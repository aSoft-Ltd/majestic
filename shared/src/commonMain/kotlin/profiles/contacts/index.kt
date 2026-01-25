package profiles.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import profiles.contacts.email.dialogs.EmailDialogs
import profiles.contacts.email.dialogs.rememberEmailDialogState
import profiles.contacts.phone.dialogs.PhoneDialogs
import profiles.contacts.phone.dialogs.rememberPhoneDialogState
import profiles.contacts.tools.ContactsColors
import profiles.contacts.tools.buttons.FloatingButtons
import profiles.contacts.tools.dialogs.Add
import users.UsersLabels

internal fun contactList(
    orientation: ScreenOrientation,
    colors: ContactsColors
): Modifier = when (orientation) {
    is Landscape -> Modifier
        .wrapContentSize()
        .background(
            color = colors.backgrounds.landscape.copy(.5f),
            shape = RoundedCornerShape(20.dp)
        )

    is Portrait -> Modifier
        .padding(10.dp)
        .fillMaxSize()
}

@Composable
fun Contacts(
    modifier: Modifier,
    colors: ContactsColors,
    labels: UsersLabels,
    orientation: ScreenOrientation
) {
    val emailDialog = rememberEmailDialogState()
    val phoneDialog = rememberPhoneDialogState()
    EmailDialogs(
        state = emailDialog,
        labels = labels.profile.tabs.contacts.content,
        colors = colors.email.dialog,
        orientation = orientation
    )
    PhoneDialogs(
        state = phoneDialog,
        labels = labels.profile.tabs.contacts.content,
        colors = colors.phone.dialog,
        orientation = orientation
    )

    Box(
        contentAlignment = if (orientation is Landscape) Alignment.TopEnd else Alignment.BottomEnd,
        modifier = modifier
    ) {
        var isOpen by remember { mutableStateOf(false) }
        var buttonBox by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }

        ContactList(
            modifier = contactList(orientation, colors),
            orientation = orientation,
            colors = colors,
            labels = labels,
            emailDialog = emailDialog,
            phoneDialog = phoneDialog
        )


        FloatingButtons(
            orientation = orientation,
            buttonBox = buttonBox,
            labels = labels,
            isOpen = isOpen,
            colors = colors,
            onEmailButtonClick = {
                isOpen = false
                emailDialog.open(Add)
            },
            onPhoneButtonClick = {
                isOpen = false
                phoneDialog.open(Add)
            },
            emailDialog = emailDialog,
            phoneDialog = phoneDialog,
            onDismiss = { isOpen = false },
            onOpen = {
                isOpen = true
            },
            onPlaced = {
                buttonBox = it.boundsInParent()
            }
        )
    }
}