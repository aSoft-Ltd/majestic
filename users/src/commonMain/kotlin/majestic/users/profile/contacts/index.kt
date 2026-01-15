package majestic.users.profile.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ExpandDirection
import majestic.FloatingActionButton
import majestic.floatActionButton
import majestic.tooling.onClick
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.profile.contacts.email.Email
import majestic.users.profile.contacts.email.EmailColors
import majestic.users.profile.contacts.email.dialogs.EmailDialogs
import majestic.users.profile.contacts.email.dialogs.rememberEmailDialogState
import majestic.users.profile.contacts.phone.Backgrounds
import majestic.users.profile.contacts.phone.Phone
import majestic.users.profile.contacts.phone.PhoneColors
import majestic.users.profile.contacts.phone.dialogs.PhoneDialogs
import majestic.users.profile.contacts.phone.dialogs.rememberPhoneDialogState
import majestic.users.profile.contacts.tools.dialogs.Add
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.tools.buttons.ButtonAnimate
import majestic.users.tools.buttons.ButtonAnimateColors
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors
import majestic.users.tools.data.separator
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

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
private fun Modifier.contact(
    orientation: ScreenOrientation,
    colors: ContactsItemBackground,
    lastItem: Boolean = false,
    shape: Shape = RoundedCornerShape(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    return height(80.dp).fillMaxWidth().then(
        if (orientation is Landscape) Modifier
            .separator(lastItem, colors.content.copy(0.05f))
            .background(
                color = when (isHovered) {
                    true -> colors.contact.focused
                    else -> colors.contact.unfocused
                },
                shape = shape
            )
            .padding(horizontal = 30.dp)
            .hoverable(interactionSource = interactionSource)
        else Modifier.clip(RoundedCornerShape(12.dp))
            .background(
                color = when (isHovered) {
                    true -> colors.profile.focused
                    else -> colors.profile.unfocused
                },
                shape = shape
            )
            .padding(horizontal = 20.dp)
    )
}

@Composable
fun Contacts(
    colors: ContactsColors,
    language: LanguageController,
    orientation: ScreenOrientation
) {
    val language by observeUsersLabels(language)
    val labels = language.profile.tabs.contacts.content
    val emailDialog = rememberEmailDialogState()
    val phoneDialog = rememberPhoneDialogState()
    EmailDialogs(
        state = emailDialog,
        labels = labels,
        colors = colors.email.dialog,
        orientation = orientation
    )
    PhoneDialogs(
        state = phoneDialog,
        labels = labels,
        colors = colors.phone.dialog,
        orientation = orientation
    )

    Box(
        contentAlignment = if (orientation is Landscape) Alignment.TopEnd else Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        var isOpen by remember { mutableStateOf(false) }
        var buttonBox by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }

        Column(
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
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(
                10.dp
            )
        ) {
            if (orientation is Landscape) Text(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
                text = labels.heading,
                color = colors.item.content.copy(0.5f),
            )
            Email(
                modifier = Modifier.contact(
                    orientation,
                    colors = colors.item
                ),
                text = "amanihamduni@gmail.com",
                isPrimary = true,
                labels = labels,
                orientation = orientation,
                colors = colors.email
            )
            Email(
                modifier = Modifier.contact(
                    orientation = orientation,
                    colors = colors.item
                ),
                text = "amani45@gmail.com",
                isPrimary = false,
                labels = labels,
                orientation = orientation,
                colors = colors.email
            )
            Phone(
                modifier = Modifier.contact(
                    orientation = orientation,
                    colors = colors.item
                ),
                text = "+255 745 147 852",
                colors = colors.phone,
                isPrimary = true,
                isWhatsapp = true,
                isNormal = true,
                labels = labels,
                orientation = orientation,
            )
            Phone(
                modifier = Modifier.contact(
                    orientation = orientation,
                    colors = colors.item,
                    lastItem = true,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                ),
                text = "+255 755 005 600",
                isPrimary = false,
                isWhatsapp = false,
                isNormal = true,
                labels = labels,
                orientation = orientation,
                colors = colors.phone
            )
        }


        if (orientation is Landscape) Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
            Box(modifier = Modifier.onPlaced { buttonBox = it.boundsInParent() }) {
                ButtonAnimate(
                    label = labels.addButton,
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
            FloatingActionButton(
                modifier = Modifier.floatActionButton(colors.floatingButton.background)
                    .onClick { isOpen = !isOpen },
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
            )
        }
    }
}