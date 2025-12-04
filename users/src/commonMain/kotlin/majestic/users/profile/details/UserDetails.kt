package majestic.users.profile.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.profile.details.tools.ProfileDestinationMapper
import majestic.users.tools.colors.background
import majestic.users.tools.colors.barColors
import majestic.users.tools.data.UsersData
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun UserDetails(
    orientation: ScreenOrientation,
    user: UsersData,
    theme: ThemeColor,
    endpoint: ProfileDestinationMapper,
    navigator: Navigator,
    labels: ProfileHeaderLabels,
    backIcon: DrawableResource,
    menuOption: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    bars: @Composable () -> Unit = {},
    content: @Composable (() -> Unit) = {},
) = Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

    val barColors = theme.barColors(orientation)
    bars()
    Column(
        modifier = Modifier
            .fillMaxWidth(if (orientation is Landscape) .85f else 1f)
            .then(if (orientation is Landscape) Modifier.padding(20.dp) else Modifier),
        verticalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 10.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailHeader(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(if (orientation is Landscape) 20.dp else 0.dp))
                .background(if (orientation is Landscape) theme.background.copy(.5f) else barColors.background),
            user = user,
            labels = labels,
            theme = theme,
            orientation = orientation,
            navigator = navigator,
            endpoint = endpoint,
            back = backIcon,
            menuOption = menuOption,
        )
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}