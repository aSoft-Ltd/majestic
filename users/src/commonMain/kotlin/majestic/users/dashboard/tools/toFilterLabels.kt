package majestic.users.dashboard.tools

import majestic.shared.users.label.table.FilterLabels
import majestic.shared.users.label.table.TableLabels

internal fun TableLabels.toFilterLabels() = FilterLabels(
    search = head.filters.search,
    date = head.filters.date,
    letterRange = head.filters.letterRange,
    ascend = head.filters.ascend,
    descend = head.filters.descend,
    apply = head.filters.apply,
    to = head.filters.to,
    from = head.filters.from,
    cancel = head.filters.cancel,
    status = head.status
)
