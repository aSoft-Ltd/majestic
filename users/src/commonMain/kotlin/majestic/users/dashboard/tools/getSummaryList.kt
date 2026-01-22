package majestic.users.dashboard.tools

import dashboards.Summary
import kotlin.random.Random
import dashboards.SummaryStatus
import org.jetbrains.compose.resources.DrawableResource

data class SummaryCardDrawables(
    val totalUsers: DrawableResource,
    val totalRoles: DrawableResource,
    val activeUsers: DrawableResource,
    val totalPermissions: DrawableResource,
    val pendingInvites: DrawableResource
)

fun getSummaryList(labels: UserDetailsStatusLabels, drawables: SummaryCardDrawables) = listOf(
    Summary(
        labels.totalUsers,
        12_145, 0.01f,
        null,
        SummaryStatus.GOOD,
        drawables.totalUsers
    ),
    Summary(
        labels.totalRoles,
        589,
        0.05f,
        UserDetailsStatus.ROLES,
        SummaryStatus.BAD,
        drawables.totalRoles
    ),
    Summary(
        labels.activeUsers,
        361,
        0.04f,
        UserDetailsStatus.ACTIVE,
        SummaryStatus.GOOD,
        drawables.activeUsers
    ),
    Summary(
        labels.totalPermissions,
        47,
        0.03f,
        UserDetailsStatus.PERMISSIONS,
        SummaryStatus.GOOD,
        drawables.totalPermissions
    ),
    Summary(
        labels.pendingInvites,
        169,
        Random.nextFloat(),
        UserDetailsStatus.PENDING,
        null,
        drawables.pendingInvites
    )
)
