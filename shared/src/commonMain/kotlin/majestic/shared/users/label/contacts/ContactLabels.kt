package majestic.shared.users.label.contacts

import majestic.shared.tools.modal.ModalFooterLabels

data class ContactLabels(
    val heading: String,
    val primary: String,
    val addButton: String,
    val options: OptionLabels,
    val availability: AvailabilityLabels,
    val actions: ActionsLabels,
    val forms: ContactActionFormLabels,
    val modalFooterLabels: ModalFooterLabels
)

data class ContactActionFormLabels(
    val phone: GenericContactFormLabels,
    val email: GenericContactFormLabels
)