package majestic.users.dashboard.tools

import composex.screen.orientation.ScreenOrientation
import majestic.shared.dashboards.SummaryCardColorProps
import majestic.shared.users.UsersLabels
import majestic.users.dashboard.UserDashboardProps
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.summary.toSummary


internal fun UsersLabels.toSummaryCardProps(
    props: UserDashboardProps,
    orientation: ScreenOrientation
): SummaryCardListProps = SummaryCardListProps(
    summaryCardProps = SummaryCardColorProps(
        colors = props.summaryCard
    ),
    summaryList = dashboard.summary.toSummary(),
    orientation = orientation
)