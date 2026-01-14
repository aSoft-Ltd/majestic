package majestic.users.profile.contacts.email.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.email.EmailForm
import majestic.users.profile.contacts.email.EmailFormColors
import majestic.users.profile.contacts.email.EmailVerificationForm
import majestic.users.profile.contacts.email.EmailVerificationFormColors
import majestic.users.profile.contacts.email.PromptColors
import majestic.users.profile.contacts.tools.GeneralPrompt
import majestic.users.profile.contacts.tools.dialogs.Add
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Dialog
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.profile.contacts.tools.dialogs.Edit
import majestic.users.profile.contacts.tools.dialogs.Verify

data class EmailDialogContentColors(
    val form: EmailFormColors,
    val verification: EmailVerificationFormColors,
    val prompts: PromptColors
)

private fun Modifier.emailForms(
    orientation: ScreenOrientation,
    state: EmailDialogState
) = when (orientation) {
    is Landscape -> this
        .padding(vertical = 40.dp, horizontal = 30.dp)
        .wrapContentSize()

    is Portrait -> when (state.dialogType) {
        is Dialog -> this
            .padding(vertical = 20.dp, horizontal = 30.dp)
            .fillMaxSize()

        else -> this
            .padding(vertical = 40.dp, horizontal = 30.dp)
            .wrapContentSize()
    }
}

@Composable
internal fun EmailDialogContent(
    labels: ContactLabels,
    colors: EmailDialogContentColors,
    orientation: ScreenOrientation,
    state: EmailDialogState
) = when (state.active) {
    is Add -> EmailForm(
        modifier = Modifier.padding(vertical = 40.dp, horizontal = 30.dp),
        colors = colors.form,
        labels = labels.forms.email.add,
        onSubmit = {
            state.open(Verify)
        },
        orientation = orientation,
    )

    is Verify -> EmailVerificationForm(
        modifier = Modifier.emailForms(orientation = orientation, state = state),
        colors = colors.verification,
        labels = labels.forms.email.verify,
        onVerify = { state.dismiss() },
        onChangeEmail = { state.open(Edit) },
    )

    is Duplicate -> GeneralPrompt(
        colors = colors.prompts.warning,
        labels = labels.toEmailDuplicateLabels(),
        onDismiss = { state.dismiss() },
        contact = "",
        modifier = Modifier.emailForms(orientation = orientation, state = state),
        onDelete = { state.dismiss() },
    )

    is Edit -> EmailForm(
        modifier = Modifier.emailForms(orientation = orientation, state = state),
        labels = labels.forms.email.edit,
        onSubmit = { state.open(Verify) },
        colors = colors.form,
        orientation = orientation,
    )

    is Delete -> GeneralPrompt(
        labels = labels.toEmailDeleteLabels(),
        modifier = Modifier.emailForms(orientation = orientation, state = state),
        onDismiss = { state.dismiss() },
        onDelete = { state.dismiss() },
        colors = colors.prompts.delete
    )

    else -> {}
}
