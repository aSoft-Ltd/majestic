package majestic.shared.users.label.contacts

import majestic.shared.tools.modal.ModalFooterLabels

data class DedicatedFormLabels(
    val title: String,
    val input: TextInputLabels,
    val availability: AvailabilityLabels,
    val modalFooterLabels: ModalFooterLabels,
    val info: String
)