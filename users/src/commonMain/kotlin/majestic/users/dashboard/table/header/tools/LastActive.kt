package majestic.users.dashboard.table.header.tools

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import majestic.users.table.header.tools.LastActiveCellProperties

@Composable
internal fun LastActive(
    props: LastActiveCellProperties,
    modifier: Modifier = Modifier
) {
    val popColors = props.colors.compPopColors
    Text(
        modifier = modifier,
        text = props.labels.lastActive,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        softWrap = false,
        color = popColors.foreground
    )
}