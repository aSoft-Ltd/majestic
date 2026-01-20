package majestic.users.labels.dashboard

import majestic.users.labels.SummaryCardLabels

data class DashboardLabels(
    val insights: InsightsLabels,
    val summary: SummaryCardLabels,
    val tabs: DashboardTabLabels,
    val roles: RoleLabels
)