package majestic.users.profile.contacts.email

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
import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.email.dialogs.EmailDialogs
import majestic.users.profile.contacts.email.dialogs.EmailDialogsColors
import majestic.users.profile.contacts.email.dialogs.rememberEmailDialogState
import majestic.users.profile.contacts.tools.EmailMenuAction
import majestic.users.profile.contacts.tools.GeneralPromptColors
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.profile.contacts.tools.dialogs.Edit
import majestic.users.tools.dialogs.Flex
import majestic.users.tools.menu.MenuOption
import majestic.users.tools.menu.MenuOptionColors
import majestic.users.tools.menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_mail

data class PromptColors(
    val warning: GeneralPromptColors,
    val delete: GeneralPromptColors,
)

data class EmailColors(
    val tint: Color,//colors.surfaceContra.copy(0.5f)
    val title: Color,//colors.surfaceContra
    val separator: Color,//colors.surfaceContra.copy(0.5f)
    val primary: Color,//colors.surfaceContra.copy(0.5f)
    val primaryBackground: Color,//colors.surfaceContra.copy(0.2f)
    val dialog: EmailDialogsColors,
    val menuOption: MenuOptionColors
)

@Composable
internal fun Email(
    labels: ContactLabels,
    text: String,
    colors: EmailColors,
    isPrimary: Boolean,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    val activeDialog = rememberEmailDialogState()
    EmailDialogs(
        state = activeDialog,
        labels = labels,
        colors = colors.dialog,
        orientation = orientation
    )

    Row(
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
                painter = painterResource(Res.drawable.ic_mail),
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
                    color = colors.title,
                )
                if (isPrimary) Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (orientation is Landscape) Text(
                        text = "•",
                        color = colors.separator,
                    )
                    Text(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.primaryBackground)
                            .padding(horizontal = 5.dp),
                        text = labels.primary,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = colors.primary,
                    )
                }
            }
        }

        MenuOption(
            colors = colors.menuOption,
            orientation = orientation,
            actions = listOf(
                OptionMenu(labels.actions.primary, EmailMenuAction.Primary),
                OptionMenu(labels.actions.email.edit, EmailMenuAction.Edit),
                OptionMenu(labels.actions.email.duplicate, EmailMenuAction.Duplicate),
                OptionMenu(labels.actions.email.delete, EmailMenuAction.Delete),
            ),
        ) { action ->
            when (action) {
                EmailMenuAction.Primary -> {}
                EmailMenuAction.Edit -> activeDialog.open(Edit)
                EmailMenuAction.Duplicate -> activeDialog.open(Duplicate)
                EmailMenuAction.Delete -> activeDialog.open(Delete)
            }
        }
    }
}