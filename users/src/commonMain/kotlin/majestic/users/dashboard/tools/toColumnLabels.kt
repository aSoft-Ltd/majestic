package majestic.users.dashboard.tools

import majestic.shared.users.label.table.ColumnLabels
import majestic.shared.users.label.table.TableLabels


internal fun TableLabels.toColumnLabels(): ColumnLabels = ColumnLabels(
    name = head.name,
    email = head.email,
    id = head.id,
    dateJoined = head.dateJoined,
    lastActive = head.lastActive,
    checkbox = "checkbox",
    roles = head.roles,
    permission = head.permission,
    status = head.status,
    users = head.users,
    filter = toFilterLabels()
)