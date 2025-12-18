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
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ExpandDirection
import majestic.FloatingActionButton
import majestic.ThemeColor
import majestic.floatActionButton
import majestic.tooling.onClick
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.profile.contacts.email.Email
import majestic.users.profile.contacts.email.EmailForm
import majestic.users.profile.contacts.email.EmailVerificationForm
import majestic.users.profile.contacts.phone.Phone
import majestic.users.profile.contacts.phone.PhoneForm
import majestic.users.profile.contacts.phone.PhoneVerificationForm
import majestic.users.profile.contacts.tools.ContactsColors
import majestic.users.tools.ProfilePortraitHeader
import majestic.users.tools.buttons.ButtonAnimate
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.colors.profileBackground
import majestic.users.tools.colors.toBackground
import majestic.users.tools.data.separator
import majestic.users.tools.dialogs.Modal
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

@Composable
private fun Modifier.contact(
    orientation: ScreenOrientation,
    theme: ThemeColor,
    lastItem: Boolean = false,
    shape: Shape = RoundedCornerShape(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    return height(80.dp).fillMaxWidth().then(
        if (orientation is Landscape) Modifier
            .separator(lastItem, theme.surface.contra.color.copy(0.05f))
            .background(
                color = when (isHovered) {
                    true -> theme
                        .toBackground.copy(alpha = .3f)
                        .compositeOver(theme.surface.contra.color.copy(.05f))

                    else -> Color.Transparent
                },
                shape = shape
            )
            .padding(horizontal = 30.dp)
            .hoverable(interactionSource = interactionSource)
        else Modifier.clip(RoundedCornerShape(12.dp))
            .profileBackground(
                theme = theme,
                isHovered = isHovered,
                orientation = orientation
            )
            .padding(horizontal = 20.dp)
    )
}


@Composable
fun Contacts(
    colors: ContactsColors,
    navigator: Navigator,
    language: LanguageController,
    orientation: ScreenOrientation
) {
    val theme = colors.theme
    val labels by observeUsersLabels(language)

    var verifyEmailDialogOpened by remember { mutableStateOf(false) }
    var addEmailDialogOpened by remember { mutableStateOf(false) }
    if (addEmailDialogOpened) Modal(
        theme = theme,
        background = colors.background,
        orientation = orientation,
        onDismiss = { addEmailDialogOpened = false }
    ) {
        EmailForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            colors = colors.emailForm,
            labels = labels.profile.contact.forms.email.add,
            onSubmit = {
                addEmailDialogOpened = false
                verifyEmailDialogOpened = true
            },
        )
    }
    if (verifyEmailDialogOpened) Modal(
        theme = theme,
        background = colors.background,
        orientation = orientation,
        onDismiss = { verifyEmailDialogOpened = false }
    ) {
        EmailVerificationForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            theme = theme,
            labels = labels.profile.contact.forms.email.verify,
            onVerify = { verifyEmailDialogOpened = false },
            onChangeEmail = {
                verifyEmailDialogOpened = false
                addEmailDialogOpened = true
            },
        )
    }

    var verifyPhoneDialogOpened by remember { mutableStateOf(false) }
    var addPhoneDialogOpened by remember { mutableStateOf(false) }
    if (addPhoneDialogOpened) Modal(
        theme = theme,
        background = colors.background,
        orientation = orientation,
        onDismiss = { addPhoneDialogOpened = false }
    ) {
        PhoneForm(
            labels = labels.profile.contact.forms.phone.add,
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            onSubmit = {
                addPhoneDialogOpened = false
                verifyPhoneDialogOpened = true
            },
            colors = colors.phoneForm
        )
    }
    if (verifyPhoneDialogOpened) Modal(
        theme = theme,
        orientation = orientation,
        background = colors.background,
        onDismiss = { verifyPhoneDialogOpened = false }
    ) {
        PhoneVerificationForm(
            theme = theme,
            labels = labels.profile.contact.forms.phone.verify,
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            onVerify = { verifyPhoneDialogOpened = false },
            onChangePhone = {
                verifyPhoneDialogOpened = false
                addPhoneDialogOpened = true
            },
        )
    }

    Box(contentAlignment = if (orientation is Landscape) Alignment.TopEnd else Alignment.BottomEnd) {
        var isOpen by remember { mutableStateOf(false) }
        var buttonBox by remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }


        if (orientation is Landscape) Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp).zIndex(10f)) {
            Box(modifier = Modifier.onPlaced { buttonBox = it.boundsInParent() }) {
                ButtonAnimate(
                    label = labels.profile.contact.addButton,
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
                            addEmailDialogOpened = true
                        }
                    )
                    FlatButton(
                        modifier = Modifier.clip(CircleShape),
                        colors = colors.flatButton,
                        label = "Phone",
                        onClick = {
                            isOpen = false
                            addPhoneDialogOpened = true
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
                                addEmailDialogOpened = true
                            }
                        )
                        FlatButton(
                            modifier = Modifier.clip(CircleShape),
                            colors = colors.flatButton,
                            label = "Phone",
                            onClick = {
                                isOpen = false
                                addPhoneDialogOpened = true
                            }
                        )
                    }
                }
            )
        }

        Column(
            modifier = if (orientation is Portrait) Modifier.fillMaxSize()
            else Modifier.background(
                color = if (orientation is Landscape) colors.background.copy(.5f) else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
        ) {
            if (orientation is Portrait) ProfilePortraitHeader(
                title = labels.profile.contact.heading,
                colors = colors.profileHeader,
                navigator = navigator
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .then(
                        if (orientation is Landscape) Modifier else Modifier.padding(20.dp)
                    ),
                verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(10.dp)
            ) {
                if (orientation is Landscape) Text(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
                    text = labels.profile.contact.heading,
                    color = theme.surface.contra.color.copy(0.5f),
                )
                Email(
                    modifier = Modifier.contact(
                        orientation,
                        theme = theme
                    ),
                    text = "amanihamduni@gmail.com",
                    isPrimary = true,
                    labels = labels.profile.contact,
                    orientation = orientation,
                    colors = colors.email
                )
                Email(
                    modifier = Modifier.contact(
                        orientation,
                        theme = theme
                    ),
                    text = "amani45@gmail.com",
                    isPrimary = false,
                    labels = labels.profile.contact,
                    orientation = orientation,
                    colors = colors.email
                )
                Phone(
                    modifier = Modifier.contact(
                        orientation,
                        theme = theme
                    ),
                    text = "+255 745 147 852",
                    colors = colors.phone,
                    isPrimary = true,
                    isWhatsapp = true,
                    isNormal = true,
                    labels = labels.profile.contact,
                    orientation = orientation,
                )
                Phone(
                    modifier = Modifier.contact(
                        orientation,
                        theme = theme,
                        lastItem = true,
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    ),
                    text = "+255 755 005 600",
                    isPrimary = false,
                    isWhatsapp = false,
                    isNormal = true,
                    labels = labels.profile.contact,
                    orientation = orientation,
                    colors = colors.phone
                )
            }
        }
    }
}