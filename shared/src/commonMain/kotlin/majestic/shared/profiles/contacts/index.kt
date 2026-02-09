package majestic.shared.profiles.contacts

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogs
import majestic.shared.profiles.contacts.email.dialogs.rememberEmailDialogState
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogs
import majestic.shared.profiles.contacts.phone.dialogs.rememberPhoneDialogState
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.profiles.contacts.tools.buttons.FloatingButtons
import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.users.UsersLabels

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
    orientation: ScreenOrientation,
    theme: ThemeColor
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
            phoneDialog = phoneDialog,
            theme = theme,
            data = ContactListData()
        )


        FloatingButtons(
            orientation = orientation,
            buttonBox = buttonBox,
            labels = labels,
            isOpen = isOpen,
            colors = colors,
            onEmailButtonClick = {
                emailDialog.open(Add)
                isOpen = false
            },
            onPhoneButtonClick = {
                phoneDialog.open(Add)
                isOpen = false
            },
            emailDialog = emailDialog,
            phoneDialog = phoneDialog,
            onDismiss = { isOpen = false },
            onOpen = {
                isOpen = !isOpen
            },
            onPlaced = {
                buttonBox = it.boundsInParent()
            }
        )
    }
}