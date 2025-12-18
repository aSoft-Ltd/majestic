package majestic.users.dashboard.summary

import majestic.users.dashboard.tools.UserDetailsStatus
import majestic.users.labels.SummaryCardLabels
import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_alert
import tz.co.asoft.majestic_users.generated.resources.ic_labor
import tz.co.asoft.majestic_users.generated.resources.ic_patient
import tz.co.asoft.majestic_users.generated.resources.ic_travel_bag
import tz.co.asoft.majestic_users.generated.resources.ic_user_group

data class Summary(
    val label: String,
    val value: Int,
    val percentage: Float,
    val userDetailsStatus: UserDetailsStatus? = null,
    val summaryStatus: SummaryStatus? = null,
    val icon: DrawableResource,
)

internal fun SummaryCardLabels.toSummary() = listOf(
    Summary(
        label = totalUsers,
        value = 120,
        percentage = 0.023f,
        userDetailsStatus = UserDetailsStatus.USERS,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_user_group
    ),
    Summary(
        label = totalRoles,
        value = 50,
        percentage = 0.75f,
        userDetailsStatus = UserDetailsStatus.ACTIVE,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_labor
    ),
    Summary(
        label = activeUsers,
        value = 45,
        percentage = 0.25f,
        userDetailsStatus = UserDetailsStatus.PENDING,
        summaryStatus = SummaryStatus.BAD,
        icon = Res.drawable.ic_travel_bag
    ),
    Summary(
        label = totalPermissions,
        value = 361,
        percentage = 0.25f,
        userDetailsStatus = UserDetailsStatus.PENDING,
        summaryStatus = SummaryStatus.BAD,
        icon = Res.drawable.ic_patient
    ),
    Summary(
        label = pendingInvites,
        value = 47,
        percentage = 0.10f,
        userDetailsStatus = UserDetailsStatus.ROLES,
        summaryStatus = SummaryStatus.GOOD,
        icon = Res.drawable.ic_alert
    )
)
