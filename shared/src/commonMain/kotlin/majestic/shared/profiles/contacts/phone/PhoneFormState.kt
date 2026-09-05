package majestic.shared.profiles.contacts.phone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import symphony.PhoneField

class PhoneFormState internal constructor(val field: PhoneField) {
    var whatsapp by mutableStateOf(false)
    var normalCalls by mutableStateOf(false)
}