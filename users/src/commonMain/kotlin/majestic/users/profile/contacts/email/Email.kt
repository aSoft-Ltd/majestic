package majestic.users.profile.contacts.email

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import majestic.users.profile.contacts.tools.DeleteForm
import majestic.users.profile.contacts.tools.DeleteFormColors
import majestic.users.profile.contacts.tools.EmailMenuAction
import majestic.users.tools.dialogs.DialogColors
import majestic.users.tools.dialogs.Flex
import majestic.users.tools.dialogs.Modal
import majestic.users.tools.menu.MenuOption
import majestic.users.tools.menu.MenuOptionColors
import majestic.users.tools.menu.OptionMenu
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_mail

data class EmailColors(
    val background: Color,
    val surfaceContra: Color,
    val dialog: DialogColors,
    val emailForm: EmailFormColors,
    val emailVerify: EmailVerificationFormColors,
    val menuOption: MenuOptionColors,
    val deleteForm: DeleteFormColors
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
    var deleteDialogOpened by remember { mutableStateOf(false) }
    if (deleteDialogOpened) Modal(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.background),
        colors = colors.dialog,
        onDismiss = { deleteDialogOpened = false }
    ) {
        DeleteForm(
            labels = labels.forms.email.delete,
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            onDismiss = { deleteDialogOpened = false },
            onDelete = { deleteDialogOpened = false },
            colors = colors.deleteForm
        )
    }

    var verifyDialogOpened by remember { mutableStateOf(false) }
    var editDialogOpened by remember { mutableStateOf(false) }
    if (editDialogOpened) Modal(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.background),
        colors = colors.dialog,
        onDismiss = { editDialogOpened = false }
    ) {
        EmailForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            labels = labels.forms.email.edit,
            onSubmit = {
                editDialogOpened = false
                verifyDialogOpened = true
            },
            colors = colors.emailForm,
        )
    }
    var duplicateDialogOpened by remember { mutableStateOf(false) }
    if (duplicateDialogOpened) Modal(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.background),
        colors = colors.dialog,
        onDismiss = { duplicateDialogOpened = false }
    ) {
        EmailForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            colors = colors.emailForm,
            labels = labels.forms.email.dup,
            onSubmit = {
                duplicateDialogOpened = false
                verifyDialogOpened = true
            },
        )
    }
    if (verifyDialogOpened) Modal(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.background),
        colors = colors.dialog,
        onDismiss = { verifyDialogOpened = false },
    ) {
        EmailVerificationForm(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
            colors = colors.emailVerify,
            labels = labels.forms.email.verify,
            onVerify = { verifyDialogOpened = false },
            onChangeEmail = {
                verifyDialogOpened = false
                editDialogOpened = true
            },
        )
    }

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
                colorFilter = ColorFilter.tint(colors.surfaceContra.copy(0.5f)),
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
                    color = colors.surfaceContra,
                )
                if (isPrimary) Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (orientation is Landscape) Text(
                        text = "•",
                        color = colors.surfaceContra.copy(0.5f),
                    )
                    Text(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.surfaceContra.copy(0.2f))
                            .padding(horizontal = 5.dp),
                        text = labels.primary,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = colors.surfaceContra.copy(0.5f),
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
                EmailMenuAction.Edit -> editDialogOpened = true
                EmailMenuAction.Duplicate -> duplicateDialogOpened = true
                EmailMenuAction.Delete -> deleteDialogOpened = true
            }
        }
    }
}