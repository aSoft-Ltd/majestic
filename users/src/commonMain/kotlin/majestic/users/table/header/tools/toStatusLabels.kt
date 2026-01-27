package majestic.users.table.header.tools

import majestic.shared.users.label.table.TableStatusLabels
import majestic.shared.users.label.table.UsersStatusLabels


fun TableStatusLabels.toStatusLabels() = UsersStatusLabels(
    invited = invited,
    active = active,
    declined = declined,
    revoked = revoked
)