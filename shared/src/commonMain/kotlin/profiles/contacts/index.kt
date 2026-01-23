package profiles.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.buttons.ButtonAnimate
import majestic.buttons.ButtonAnimateColors
import majestic.buttons.FlatButton
import majestic.buttons.FlatButtonColors
import majestic.buttons.floatActionButton
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.tooling.onClick
import profiles.contacts.email.EmailColors
import profiles.contacts.email.dialogs.EmailDialogs
import profiles.contacts.email.dialogs.rememberEmailDialogState
import profiles.contacts.phone.Backgrounds
import profiles.contacts.phone.PhoneColors
import profiles.contacts.phone.dialogs.PhoneDialogs
import profiles.contacts.phone.dialogs.rememberPhoneDialogState
import profiles.contacts.tools.dialogs.Add
import users.UsersLabels

data class ContactBackgroundColors(
    val focused: Color,
    val unfocused: Color
)

data class ProfileBackgroundColors(
    val focused: Color,
    val unfocused: Color
)

data class ContactsItemBackground(
    val content: Color,
    val contact: ContactBackgroundColors,
    val profile: ProfileBackgroundColors
)

data class ContactsColors(
    val item: ContactsItemBackground,
    val buttonAnimate: ButtonAnimateColors,
    val flatButton: FlatButtonColors,
    val floatingButton: ColorPair,
    val backgrounds: Backgrounds,
    val email: EmailColors,
    val phone: PhoneColors
)


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
            modifier = Modifier
                .then(
                    other = when (orientation) {
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
                ),
            orientation = orientation,
            colors = colors,
            labels = labels,
            emailDialog = emailDialog,
            phoneDialog = phoneDialog
        )


        if (orientation is Landscape) Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
            Box(modifier = Modifier.onPlaced { buttonBox = it.boundsInParent() }) {
                ButtonAnimate(
                    label = labels.profile.tabs.contacts.content.addButton,
                    icon = Res.drawable.ic_add,
                    isOpen = isOpen,
                    colors = colors.buttonAnimate,
                    onClick = { isOpen = !isOpen }
                )
            }
            if (isOpen) Popup(
                alignment = Alignment.TopEnd,
                offset = IntOffset(x = 0, y = buttonBox.bottom.toInt() + 20)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FlatButton(
                        modifier = Modifier.clip(CircleShape),
                        colors = colors.flatButton,
                        label = "Email",
                        onClick = {
                            isOpen = false
                            emailDialog.open(Add)
                        }
                    )
                    FlatButton(
                        modifier = Modifier.clip(CircleShape),
                        colors = colors.flatButton,
                        label = "Phone",
                        onClick = {
                            isOpen = false
                            phoneDialog.open(Add)
                        }
                    )
                }
            }
        }

        if (orientation is Portrait) Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {

            PortraitButtons(
                modifier = Modifier.floatActionButton(colors.floatingButton.background)
                    .onClick { isOpen = !isOpen },
                colors = colors,
                isOpen = isOpen,
                emailDialog = emailDialog,
                phoneDialog = phoneDialog,
                onDismiss = {
                    isOpen = false
                }
            )
        }
    }
}
