package majestic.users.labels.profile.contact

data class ContactLabels(
    val heading: String,
    val primary: String,
    val addButton: String,
    val availability: Availability,
    val actions: ActionsLabels,
    val forms: ContactActionFormLabels
) {
    data class ContactActionFormLabels(
        val phone: GenericContactFormLabels,
        val email: GenericContactFormLabels
    )

    data class Availability(
        val calls: String,
        val whatsapp: String,
    )

    internal companion object {
        val english by lazy {
            ContactLabels(
                heading = "Emails & Phone",
                primary = "Primary",
                addButton = "Add",
                availability = Availability(
                    calls = "Can receive direct calls",
                    whatsapp = "Available on WhatsApp",
                ),
                actions = ActionsLabels.english,
                forms = ContactActionFormLabels(
                    email = GenericContactFormLabels.english("Email"),
                    phone = GenericContactFormLabels.english("Phone")
                )
            )
        }

        val swahili by lazy {
            ContactLabels(
                heading = "Barua pepe na Simu",
                primary = "Msingi",
                addButton = "Ongeza",
                availability = Availability(
                    calls = "Inaweza kupokea simu moja kwa moja",
                    whatsapp = "Inapatikana WhatsApp",
                ),
                actions = ActionsLabels.swahili,
                forms = ContactActionFormLabels(
                    email = GenericContactFormLabels.swahili("Barua Pepe"),
                    phone = GenericContactFormLabels.swahili("Simu")
                )
            )
        }
    }
}
