package majestic.users.labels.dashboard

data class DashboardLabels(
    val insights: InsightsLabels,
    val summary: SummaryCardLabels,
    val tabs: DashboardTabLabels,
    val table:DashboardTableLabel,
    val roles: DashboardRoleTableLabels
)