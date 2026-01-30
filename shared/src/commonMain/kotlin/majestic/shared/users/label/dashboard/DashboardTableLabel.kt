package majestic.shared.users.label.dashboard

import majestic.shared.users.label.table.TableLabels


data class DashboardTableLabel(
    val title: String,
    val manage: String,
    val add: String,
    val table: TableLabels
)