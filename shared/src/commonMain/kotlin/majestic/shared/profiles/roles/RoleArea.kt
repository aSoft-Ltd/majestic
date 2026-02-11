package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import tz.co.asoft.academia.overview.insights.website.utils.underline
import tz.co.asoft.academia.playground.ronald.CampusData
import tz.co.asoft.academia.tools.colors.toBackground
data class RoleColors(
    header
)
@Composable
fun RoleArea(
    modifier: Modifier = Modifier,
    theme: ThemeColor,
    orientation: ScreenOrientation,
    campuses: List<CampusData>
) = Column(modifier = modifier) {
    Header(
        modifier = when (orientation) {
            is Landscape -> Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp, top = 16.dp)
            is Portrait -> Modifier.fillMaxWidth().background(theme.toBackground).padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
        },
        theme = theme
    )
    Column(
        modifier = when (orientation) {
            is Portrait -> Modifier.fillMaxWidth()
            is Landscape -> Modifier.wrapContentSize()
        },
        verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.spacedBy(4.dp)
    ) {
        campuses.forEach { campus ->
            val interaction = remember { MutableInteractionSource() }
            val hovered by interaction.collectIsHoveredAsState()
            CampusItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .hoverable(interaction)
                    .background(
                        color = when (orientation) {
                            is Landscape -> if (hovered) theme.surface.contra.color.copy(.05f) else Color.Transparent
                            is Portrait -> theme.toBackground.copy(.5f)
                        },
                        shape = when (orientation) {
                            is Landscape -> when {
                                campuses.indexOf(campus) == campuses.lastIndex -> RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )

                                else -> RoundedCornerShape(0.dp)
                            }

                            is Portrait -> RoundedCornerShape(8.dp)
                        }
                    )

                    .padding(if (orientation is Landscape) 20.dp else 10.dp),
                campus = campus,
                theme = theme,
                orientation = orientation,
                onAdd = { },
                onMore = { }
            )
            if (campuses.lastIndex != campuses.indexOf(campus)) Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .underline(theme.surface.contra.color.copy(0.05f), 0.5.dp)
            )
        }
    }
}
