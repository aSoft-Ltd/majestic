package majestic.users.profile.contacts

import symphony.Fields
import symphony.PhoneOutput
import symphony.phone
import symphony.text

internal data class PersonContact(
    var email: String? = null,
    var phone: PhoneOutput? = null,
)

internal class ContactForm : Fields<PersonContact>(PersonContact()) {
    val email = text(name = output::email)
    val phone = phone(
        name = output::phone,
        filter = { c, key ->
            c.name.contains(key, ignoreCase = true)
                    || "+${c.dialingCode}".contains(key, ignoreCase = true)
                    || c.label.contains(key, ignoreCase = true)
        }
    )
}