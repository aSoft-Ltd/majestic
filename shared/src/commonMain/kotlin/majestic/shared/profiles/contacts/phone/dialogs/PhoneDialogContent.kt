package majestic.shared.profiles.contacts.phone.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.contacts.email.PromptColors
import majestic.shared.profiles.contacts.phone.PhoneForm
import majestic.shared.profiles.contacts.phone.PhoneFormColors
import majestic.shared.profiles.contacts.phone.PhoneVerificationForm
import majestic.shared.profiles.contacts.phone.PhoneVerificationFormColors
import majestic.shared.profiles.contacts.tools.dialogs.Add
import majestic.shared.profiles.contacts.tools.dialogs.Delete
import majestic.shared.profiles.contacts.tools.dialogs.Duplicate
import majestic.shared.profiles.contacts.tools.dialogs.Edit
import majestic.shared.profiles.contacts.tools.dialogs.GeneralPrompt
import majestic.shared.profiles.contacts.tools.dialogs.None
import majestic.shared.profiles.contacts.tools.dialogs.Verify
import majestic.shared.users.label.contacts.ContactLabels

data class PhoneDialogContentColors(
    val form: PhoneFormColors,
    val verification: PhoneVerificationFormColors,
    val prompts: PromptColors
)


@Composable
internal fun PhoneDialogContent(
    labels: ContactLabels,
    colors: PhoneDialogContentColors,
    orientation: ScreenOrientation,
    state: PhoneDialogState
) = when (state.active) {
    is Add -> PhoneForm(
        labels = labels.forms.phone.edit,
        onSubmit = { state.open(Verify) },
        onCancel = { state.dismiss() },
        colors = colors.form,
        orientation = orientation,
    )

    is Verify -> PhoneVerificationForm(
        modifier = Modifier.then(if (orientation == Portrait) Modifier.fillMaxSize(1f) else Modifier).padding(16.dp),
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
        onApproved = { state.dismiss() },
        orientation = orientation
    )

    is Edit -> PhoneForm(
        labels = labels.forms.phone.edit,
        onSubmit = { state.open(Verify) },
        onCancel = { state.dismiss() },
        colors = colors.form,
        orientation = orientation,
    )

    is Delete -> GeneralPrompt(
        labels = labels.toPhoneDeleteLabels(),
        onRejected = { state.dismiss() },
        onApproved = { state.dismiss() },
        colors = colors.prompts.delete,
        orientation = orientation,
        isDestructive = true
    )

    is None -> {}
}