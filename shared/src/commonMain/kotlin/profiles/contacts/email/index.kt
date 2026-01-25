package profiles.contacts.email

import Flex
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
import majestic.icons.Res
import majestic.icons.ic_mail
import menu.MenuOption
import menu.MenuOptionColors
import menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import profiles.contacts.email.dialogs.EmailDialogsColors
import profiles.contacts.tools.EmailMenuAction
import profiles.contacts.tools.dialogs.GeneralPromptColors
import users.label.contacts.ContactLabels

data class PromptColors(
    val warning: GeneralPromptColors,
    val delete: GeneralPromptColors,
)

data class EmailColors(
    val tint: Color,
    val title: Color,
    val separator: Color,
    val primary: Color,
    val primaryBackground: Color,
    val dialog: EmailDialogsColors,
    val menuOption: MenuOptionColors
)

@Composable
fun Email(
    labels: ContactLabels,
    text: String,
    colors: EmailColors,
    isPrimary: Boolean,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
    onAction: (EmailMenuAction) -> Unit,
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
        onAction(action)
    }
}