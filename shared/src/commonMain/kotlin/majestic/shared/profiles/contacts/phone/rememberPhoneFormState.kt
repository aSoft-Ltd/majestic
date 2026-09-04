package majestic.shared.profiles.contacts.phone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import majestic.shared.profiles.contacts.ContactForm
import nation.Country

@Composable
fun rememberPhoneFormState(): PhoneFormState = remember {
    val form = ContactForm().apply { phone.setCountry(Country.TZ) }
    PhoneFormState(form.phone)
}