package majestic.users.dashboard.summary

import majestic.icons.Res
import majestic.icons.ic_alert
import majestic.icons.ic_labor
import majestic.icons.ic_patient
import majestic.icons.ic_travel_bag
import majestic.icons.ic_user_group
import majestic.shared.dashboards.Summary
import majestic.shared.dashboards.SummaryStatus
import majestic.shared.users.label.dashboard.SummaryCardLabels
import majestic.users.dashboard.tools.UserDetailsStatus


internal fun SummaryCardLabels.toSummary() = listOf(
    Summary(
        label = totalUsers,
        value = 120,
        percentage = 0.023f,
        dataStatus = UserDetailsStatus.USERS,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_user_group
    ),
    Summary(
        label = totalRoles,
        value = 50,
        percentage = 0.75f,
        dataStatus = UserDetailsStatus.ACTIVE,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_labor
    ),
    Summary(
        label = activeUsers,
        value = 45,
        percentage = 0.25f,
        dataStatus = UserDetailsStatus.PENDING,
        summaryStatus = SummaryStatus.BAD,
        icon = Res.drawable.ic_travel_bag
    ),
    Summary(
        label = totalPermissions,
        value = 361,
        percentage = 0.25f,
        dataStatus = UserDetailsStatus.PENDING,
        summaryStatus = SummaryStatus.BAD,
        icon = Res.drawable.ic_patient
    ),
    Summary(
        label = pendingInvites,
        value = 47,
        percentage = 0.10f,
        dataStatus = UserDetailsStatus.ROLES,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_alert
    )
)
