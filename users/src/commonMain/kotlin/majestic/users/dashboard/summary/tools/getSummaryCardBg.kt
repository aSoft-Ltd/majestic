package majestic.users.dashboard.summary.tools

import androidx.compose.ui.graphics.compositeOver
import majestic.users.dashboard.summary.SummaryCardListProps

internal fun getSummaryCardBg(
    isHovered: Boolean,
    props: SummaryCardListProps
) = when (isHovered) {
    true -> props.summaryCardProps.colors.foreground.copy(0.02f)
        .compositeOver(props.summaryCardProps.colors.background)

    false -> props.summaryCardProps.colors.background
}
