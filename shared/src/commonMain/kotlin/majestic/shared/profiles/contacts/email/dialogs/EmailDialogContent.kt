package majestic.shared.profiles.contacts.email.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.contacts.email.EmailForm
import majestic.shared.profiles.contacts.email.EmailFormColors
import majestic.shared.profiles.contacts.email.EmailVerificationForm
import majestic.shared.profiles.contacts.email.EmailVerificationFormColors
import majestic.shared.profiles.contacts.email.PromptColors
import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.profiles.contacts.tools.dialogs.Delete
import majestic.shared.profiles.contacts.tools.dialogs.Duplicate
import majestic.shared.profiles.contacts.tools.dialogs.Edit
import majestic.shared.profiles.contacts.tools.dialogs.GeneralPrompt
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.profiles.contacts.tools.dialogs.Verify
import majestic.shared.users.label.contacts.ContactLabels

data class EmailDialogContentColors(
    val form: EmailFormColors,
    val verification: EmailVerificationFormColors,
    val prompts: PromptColors
)


@Composable
internal fun EmailDialogContent(
    labels: ContactLabels,
    colors: EmailDialogContentColors,
    orientation: ScreenOrientation,
    state: EmailDialogState
) = when (state.active) {
    is Add -> EmailForm(
        colors = colors.form,
        labels = labels.forms.email.add,
        onSubmit = {
            state.open(Verify)
        },
        onCancel = { state.dismiss() },
        orientation = orientation,
    )

    is Verify -> EmailVerificationForm(
        colors = colors.verification,
        labels = labels.forms.email.verify,
        onVerify = { state.dismiss() },
        onChangeEmail = { state.open(Edit) },
        orientation = orientation,
        modifier = Modifier.then(if (orientation == Portrait) Modifier.fillMaxSize(1f) else Modifier).padding(16.dp)
    )

    is Duplicate -> GeneralPrompt(
        colors = colors.prompts.warning,
        labels = labels.toEmailDuplicateLabels(),
        onRejected = { state.dismiss() },
        contact = "",
        onApproved = { state.dismiss() },
        orientation = orientation,
    )

    is Edit -> EmailForm(
        labels = labels.forms.email.edit,
        onSubmit = { state.open(Verify) },
        onCancel = { state.dismiss() },
        colors = colors.form,
        orientation = orientation,
    )

    is Delete -> GeneralPrompt(
        labels = labels.toEmailDeleteLabels(),
        onRejected = { state.dismiss() },
        onApproved = { state.dismiss() },
        colors = colors.prompts.delete,
        orientation = orientation,
        isDestructive = true
    )

    is None -> {}
}