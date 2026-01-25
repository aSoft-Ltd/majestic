package profiles.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import profiles.contacts.email.Email
import profiles.contacts.email.dialogs.EmailDialogState
import profiles.contacts.phone.Phone
import profiles.contacts.phone.dialogs.PhoneDialogState
import profiles.contacts.tools.ContactsColors
import profiles.contacts.tools.ContactsItemBackground
import tools.separator
import users.UsersLabels

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
internal fun ContactList(
    modifier: Modifier,
    orientation: ScreenOrientation,
    colors: ContactsColors,
    labels: UsersLabels,
    emailDialog: EmailDialogState,
    phoneDialog: PhoneDialogState
) = Column(
    modifier = modifier
        .verticalScroll(rememberScrollState()),
    verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(
        10.dp
    )
) {
    if (orientation is Landscape) Text(
        modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
        text = labels.profile.tabs.contacts.content.heading,
        color = colors.item.content.copy(0.5f),
    )
    Email(
        modifier = Modifier.contact(
            orientation,
            colors = colors.item
        ),
        text = "amanihamduni@gmail.com",
        isPrimary = true,
        labels = labels.profile.tabs.contacts.content,
        orientation = orientation,
        colors = colors.email,
        state = emailDialog
    )
    Email(
        labels = labels.profile.tabs.contacts.content,
        text = "amani45@gmail.com",
        colors = colors.email,
        isPrimary = false,
        orientation = orientation,
        modifier = Modifier.contact(
            orientation = orientation,
            colors = colors.item
        ),
        state = emailDialog,
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
        labels = labels.profile.tabs.contacts.content,
        orientation = orientation,
        state = phoneDialog,
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
        labels = labels.profile.tabs.contacts.content,
        orientation = orientation,
        colors = colors.phone,
        state = phoneDialog,
    )
}