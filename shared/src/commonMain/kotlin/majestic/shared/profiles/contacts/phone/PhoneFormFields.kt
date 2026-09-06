package majestic.shared.profiles.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.profiles.contacts.phone.form.Form
import majestic.shared.users.label.contacts.DedicatedFormLabels

@Composable
fun PhoneFormFields(
    state: PhoneFormState,
    labels: DedicatedFormLabels,
    colors: PhoneFormColors,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    Form(
        field = state.field,
        modifier = Modifier.fillMaxWidth(),
        labels = labels,
        colors = colors,
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 11.dp),
        textSize = 14.sp
    )
    PhoneAvailabilityFields(
        state = state,
        modifier = Modifier.fillMaxWidth(),
        labels = labels,
        colors = colors
    )
}