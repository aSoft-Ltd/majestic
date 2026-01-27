package majestic.users.table.header.tools

import majestic.shared.users.label.table.StatusLabels


fun StatusLabels.toStatusLabels() = StatusLabels(
    invited = invited,
    active = active,
    declined = declined,
    revoked = revoked
)