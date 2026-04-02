package majestic.shared.profiles.roles.details.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.Search
import majestic.SearchColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController

data class TopHeaderColors(
    val background: Color,
    val counter: CounterColors,
    val separator: Color,
    val search: SearchColors,
)

data class RolesHeaderLabels(val search: String, val roles: String)

@Composable
internal fun TopHeader(
    modifier: Modifier,
    colors: TopHeaderColors,
    count: Int,
    labels: RolesHeaderLabels,
    controller: AssignmentController
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Counter(
        modifier = Modifier.wrapContentSize(),
        colors = colors.counter,
        count = count,
        labels = labels
    )

    Search(
        modifier = Modifier
            .width(250.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Black.copy(0.05f), shape = RoundedCornerShape(20.dp))
            .padding(start = 16.dp),
        value = controller.searchQuery,
        onChange = { controller.searchQuery = it },
        hint = labels.search,
        colors = colors.search,
    )
}