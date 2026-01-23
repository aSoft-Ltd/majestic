package profiles.contacts.phone.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import profiles.contacts.email.PromptColors
import profiles.contacts.phone.PhoneForm
import profiles.contacts.phone.PhoneFormColors
import profiles.contacts.phone.PhoneVerificationForm
import profiles.contacts.phone.PhoneVerificationFormColors
import profiles.contacts.tools.GeneralPrompt
import profiles.contacts.tools.dialogs.Add
import profiles.contacts.tools.dialogs.Delete
import profiles.contacts.tools.dialogs.Dialog
import profiles.contacts.tools.dialogs.Duplicate
import profiles.contacts.tools.dialogs.Edit
import profiles.contacts.tools.dialogs.None
import profiles.contacts.tools.dialogs.Verify
import users.label.contacts.ContactLabels

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
        onRejected = { state.dismiss() },
        contact = "",
        modifier = Modifier.phoneForms(orientation = orientation, state = state),
        onApproved = { state.dismiss() },
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
        onRejected = { state.dismiss() },
        onApproved = { state.dismiss() },
        colors = colors.prompts.delete,
        orientation = orientation
    )

    is None -> {}
}