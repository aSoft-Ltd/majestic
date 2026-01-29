package majestic.users.dashboard.summary.tools

import majestic.shared.dashboards.Summary
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.tools.UserDetailsStatus


internal fun getGraphColor(
    item: Summary<UserDetailsStatus>,
    props: SummaryCardListProps
) = when (item.dataStatus) {
    UserDetailsStatus.USERS -> UserDetailsStatus.USERS.color
    UserDetailsStatus.ROLES -> UserDetailsStatus.ROLES.color
    UserDetailsStatus.ACTIVE -> UserDetailsStatus.ACTIVE.color
    UserDetailsStatus.PENDING -> UserDetailsStatus.PENDING.color
    else -> props.summaryCardProps.colors.foreground
}