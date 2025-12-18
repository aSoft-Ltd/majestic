package majestic.users.labels.dashboard

import majestic.users.labels.SummaryCardLabels

data class DashboardLabels(
    val insights: InsightsLabels,
    val summary: SummaryCardLabels,
    val roles: RoleLabels
) {
    companion object {
        val english by lazy {
            DashboardLabels(
                insights = InsightsLabels.english,
                summary = SummaryCardLabels.english,
                roles = RoleLabels.english
            )
        }
        val swahili by lazy {
            DashboardLabels(
                insights = InsightsLabels.swahili,
                summary = SummaryCardLabels.swahili,
                roles = RoleLabels.swahili
            )
        }
    }
}
