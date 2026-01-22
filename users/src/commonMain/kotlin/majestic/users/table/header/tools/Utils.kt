package majestic.users.table.header.tools

import users.label.table.ColumnLabels
import users.label.table.FilterLabels

fun getHeaderLabels(labels: ColumnLabels) = ColumnLabels(
    checkbox = "checkbox",
    name = labels.name,
    email = labels.email,
    id = labels.id,
    dateJoined = labels.dateJoined,
    lastActive = labels.lastActive,
    roles = labels.roles,
    permission = labels.permission,
    status = labels.status,
    users = labels.users,
    filter = FilterLabels(
        search = labels.filter.search,
        date = labels.filter.date,
        letterRange = labels.filter.letterRange,
        ascend = labels.filter.ascend,
        descend = labels.filter.descend,
        apply = labels.filter.apply,
        to = labels.filter.to,
        from = labels.filter.from,
        cancel = labels.filter.cancel,
        status = labels.filter.status
    )
)