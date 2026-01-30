package majestic.shared.profiles.contacts.phone.form

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.CountryDialingCodeSelector
import majestic.PhoneField
import majestic.shared.profiles.contacts.ContactForm
import majestic.shared.profiles.contacts.phone.PhoneFormColors
import majestic.shared.tools.DialingCodePreview
import majestic.shared.users.label.contacts.DedicatedFormLabels
import nation.Country

@Composable
internal fun Form(
    modifier: Modifier,
    labels: DedicatedFormLabels,
    colors: PhoneFormColors
) {
    val form = ContactForm()
    form.phone.setCountry(Country.TZ)
    PhoneField(
        field = form.phone,
        modifier = modifier,
        hint = labels.input.placeholder,
        colors = colors.phoneField,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = labels.input.label,
                color = colors.label,
            )
        },
        leadingIcon = {
            CountryDialingCodeSelector(
                modifier = Modifier.width(100.dp),
                field = form.phone,
                colors = colors.leadingIcon,
                searchColors = colors.search,
                selected = {
                    DialingCodePreview(
                        modifier = Modifier.padding(start = 10.dp),
                        country = it,
                        color = colors.codePreview
                    )
                }
            )
        },
    )
}