package majestic.users.profile.contacts.phone.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.profile.contacts.email.PromptColors
import majestic.users.profile.contacts.phone.PhoneForm
import majestic.users.profile.contacts.phone.PhoneFormColors
import majestic.users.profile.contacts.phone.PhoneVerificationForm
import majestic.users.profile.contacts.phone.PhoneVerificationFormColors
import majestic.users.profile.contacts.tools.GeneralPrompt
import majestic.users.profile.contacts.tools.dialogs.Add
import majestic.users.profile.contacts.tools.dialogs.Delete
import majestic.users.profile.contacts.tools.dialogs.Dialog
import majestic.users.profile.contacts.tools.dialogs.Duplicate
import majestic.users.profile.contacts.tools.dialogs.Edit
import majestic.users.profile.contacts.tools.dialogs.Verify

data class PhoneDialogContentColors(
    val form: PhoneFormColors,
    val verification: PhoneVerificationFormColors,
    val prompts: PromptColors
)

private fun Modifier.phoneForms(
    orientation: ScreenOrientation,
    state: PhoneDialogState
) = when (orientation) {
    is Landscape -> this
        .padding(vertical = 30.dp, horizontal = 30.dp)
        .wrapContentSize()

    is Portrait -> when (state.dialogType) {
        is Dialog -> this
            .padding(bottom = 30.dp)
            .fillMaxSize()

        else -> this
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .wrapContentSize()
    }
}

@Composable
internal fun PhoneDialogContent(
    labels: ContactLabels,
    colors: PhoneDialogContentColors,
    orientation: ScreenOrientation,
    state: PhoneDialogState
) = when (state.active) {
    is Add -> PhoneForm(
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        labels = labels.forms.email.edit,
        onSubmit = { state.open(Verify) },
        colors = colors.form,
        orientation = orientation,
    )

    is Verify -> PhoneVerificationForm(
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        colors = colors.verification,
        labels = labels.forms.phone.verify,
        onVerify = { state.dismiss() },
        onChangePhone = { state.open(Edit) },
        orientation = orientation
    )

    is Duplicate -> GeneralPrompt(
        colors = colors.prompts.warning,
        labels = labels.toPhoneDuplicateLabels(),
        onDismiss = { state.dismiss() },
        contact = "",
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        onDelete = { state.dismiss() },
        orientation = orientation
    )

    is Edit -> PhoneForm(
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        labels = labels.forms.email.edit,
        onSubmit = { state.open(Verify) },
        colors = colors.form,
        orientation = orientation,
    )

    is Delete -> GeneralPrompt(
        labels = labels.toPhoneDeleteLabels(),
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        onDismiss = { state.dismiss() },
        onDelete = { state.dismiss() },
        colors = colors.prompts.delete,
        orientation = orientation
    )

    else -> {}
}