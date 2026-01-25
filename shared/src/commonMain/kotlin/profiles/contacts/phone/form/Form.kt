package profiles.contacts.phone.form

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.CountryDialingCodeSelector
import majestic.PhoneField
import nation.Country
import profiles.contacts.ContactForm
import profiles.contacts.phone.PhoneFormColors
import tools.DialingCodePreview
import users.label.contacts.DedicatedFormLabels

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