package majestic.users.labels.profile.contact

import majestic.users.labels.general.TextInputLabels

data class DedicatedFormLabels(
    val title: String,
    val input: TextInputLabels,
    val availability: ContactLabels.Availability,
    val submit: String,
    val info: String
) {
    companion object {
        fun english(action: String, context: String) = DedicatedFormLabels(
                title = "$action $context",
                input = TextInputLabels(
                    label = context,
                    placeholder = "Enter ${context.lowercase()}"
                ),
                availability = ContactLabels.Availability(
                    calls = "Normal Calls",
                    whatsapp = "WhatsApp"
                ),
                submit = "Continue",
                info = "We will verify your ${context.lowercase()} before saving it"
            )

        fun swahili(action: String, context: String) = DedicatedFormLabels(
                title = "$action $context",
                input = TextInputLabels(
                    label = context,
                    placeholder = "Weka ${context.lowercase()}"
                ),
                availability = ContactLabels.Availability(
                    calls = "Kawaida",
                    whatsapp = "WhatsApp"
                ),
                submit = "Endelea",
                info = "Tutahakiki ${context.lowercase()} kabla ya kuihifadhi"
            )
    }
}
