package majestic.shared.profiles.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.contacts.email.Email
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogState
import majestic.shared.profiles.contacts.phone.Phone
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogState
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.profiles.contacts.tools.ContactsItemBackground
import majestic.shared.profiles.contacts.tools.EmailMenuAction
import majestic.shared.profiles.contacts.tools.PhoneMenuAction
import majestic.shared.profiles.contacts.tools.dialogs.Delete
import majestic.shared.profiles.contacts.tools.dialogs.Duplicate
import majestic.shared.profiles.contacts.tools.dialogs.Edit
import majestic.shared.tools.separator
import majestic.shared.users.UsersLabels

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

data class ContactListData(
    val email: List<String> = listOf("amanihamduni@gmail.com", "amani45@gmail.com"),
    val phone: List<String> = listOf("+255 745 147 852", "+255 755 005 600")
)

@Composable
internal fun ContactList(
    modifier: Modifier,
    orientation: ScreenOrientation,
    colors: ContactsColors,
    labels: UsersLabels,
    data: ContactListData,
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


    data.email.forEachIndexed { index, email ->
        Email(
            modifier = Modifier.contact(orientation, colors.item),
            text = email,
            isPrimary = index == 0,
            labels = labels.profile.tabs.contacts.content,
            orientation = orientation,
            colors = colors.email
        ) {
            when (it) {
                EmailMenuAction.Primary -> {}
                EmailMenuAction.Edit -> emailDialog.open(Edit)
                EmailMenuAction.Duplicate -> emailDialog.open(Duplicate)
                EmailMenuAction.Delete -> emailDialog.open(Delete)
            }
        }
    }

    data.phone.forEachIndexed { index, phone ->
        val isLast = index == data.phone.size - 1
        Phone(
            modifier = Modifier.contact(
                orientation = orientation,
                colors = colors.item,
                lastItem = isLast,
                shape = if (isLast) RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                ) else RoundedCornerShape(0.dp)
            ),
            text = phone,
            colors = colors.phone,
            isPrimary = index == 0,
            isWhatsapp = index == 0,
            isNormal = true,
            labels = labels.profile.tabs.contacts.content,
            orientation = orientation
        ) {
            when (it) {
                PhoneMenuAction.Primary -> {}
                PhoneMenuAction.Edit -> phoneDialog.open(Edit)
                PhoneMenuAction.Duplicate -> phoneDialog.open(Duplicate)
                PhoneMenuAction.Delete -> phoneDialog.open(Delete)
            }
        }
    }
}