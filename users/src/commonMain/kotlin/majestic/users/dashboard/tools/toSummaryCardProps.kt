package majestic.users.dashboard.tools

import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.UserDashboardProps
import majestic.users.dashboard.summary.SummaryCardColorProps
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.summary.toSummary
import majestic.users.labels.UsersLabels


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