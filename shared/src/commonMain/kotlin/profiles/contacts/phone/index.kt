package profiles.contacts.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.dialogs.Flex
import majestic.icons.Res
import majestic.icons.ic_call
import majestic.icons.ic_calling_solid
import majestic.icons.ic_whatsapp_solid
import menu.MenuOption
import menu.MenuOptionColors
import menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import profiles.contacts.phone.dialogs.PhoneDialogState
import profiles.contacts.phone.dialogs.PhoneDialogsColors
import profiles.contacts.tools.PhoneMenuAction
import profiles.contacts.tools.dialogs.Delete
import profiles.contacts.tools.dialogs.Duplicate
import profiles.contacts.tools.dialogs.Edit
import tools.Tooltip
import users.label.contacts.ContactLabels

data class Backgrounds(
    val portrait: Color,
    val landscape: Color
)

data class PhoneColors(
    val tint: Color,
    val title: Color,
    val separator: Color,
    val primary: Color,
    val primaryBackground: Color,
    val tooltip: ColorPair,
    val menuOption: MenuOptionColors,
    val dialog: PhoneDialogsColors,
)

@Composable
internal fun Phone(
    labels: ContactLabels,
    text: String,
    colors: PhoneColors,
    isPrimary: Boolean,
    isWhatsapp: Boolean,
    state: PhoneDialogState,
    isNormal: Boolean,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(Res.drawable.ic_call),
            colorFilter = ColorFilter.tint(colors.tint),
            contentDescription = null,
        )
        Flex(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            alignment = Alignment.CenterVertically,
            orientation = orientation
        ) {
            Text(
                text = text,
                color = colors.title
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (orientation is Landscape) Text(
                    text = "•",
                    color = colors.separator,
                )
                if (isPrimary) Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(colors.primaryBackground)
                        .padding(horizontal = 5.dp),
                    text = labels.primary,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = colors.primary,
                )
                if (isNormal) Tooltip(
                    text = labels.availability.calls,
                    colors = colors.tooltip,
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(Res.drawable.ic_calling_solid),
                        colorFilter = ColorFilter.tint(Color(0xFF30C0F9)),
                        contentDescription = null,
                    )
                }
                if (isWhatsapp) Tooltip(
                    text = labels.availability.whatsapp,
                    colors = colors.tooltip,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(Res.drawable.ic_whatsapp_solid),
                        colorFilter = ColorFilter.tint(Color(0xFF25D366)),
                        contentDescription = null,
                    )
                }
            }
        }
    }
    MenuOption(
        colors = colors.menuOption,
        orientation = orientation,
        actions = listOf(
            OptionMenu(labels.actions.primary, PhoneMenuAction.Primary),
            OptionMenu(labels.actions.phone.edit, PhoneMenuAction.Edit),
            OptionMenu(labels.actions.phone.duplicate, PhoneMenuAction.Duplicate),
            OptionMenu(labels.actions.phone.delete, PhoneMenuAction.Delete)
        ),
    ) { action ->
        when (action) {
            PhoneMenuAction.Primary -> {}
            PhoneMenuAction.Edit -> state.open(Edit)
            PhoneMenuAction.Duplicate -> state.open(Duplicate)
            PhoneMenuAction.Delete -> state.open(Delete)
        }
    }
}