package majestic.users.dashboard.tools

import majestic.users.labels.table.TableLabels
import majestic.users.table.header.tools.FiltersLabels

internal fun TableLabels.toFilterLabels(): FiltersLabels = FiltersLabels(
    searchTitle = head.filters.search,
    dateTime = head.filters.date,
    letterRange = head.filters.letterRange,
    ascend = head.filters.ascend,
    descend = head.filters.descend,
    apply = head.filters.apply,
    to = head.filters.to,
    from = head.filters.from,
    cancel = head.filters.cancel
)
