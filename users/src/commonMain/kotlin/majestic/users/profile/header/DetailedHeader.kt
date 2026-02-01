package majestic.users.profile.header

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.users.profile.DetailedHeaderColors
import majestic.shared.users.label.profile.ProfileLabels
import majestic.tooling.onClick
import majestic.users.profile.Head
import majestic.users.profile.header.tools.HeadData
import majestic.users.profile.header.tools.toProfileData
import majestic.shared.users.tools.UsersData
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun DetailHeader(
    labels: ProfileLabels,
    user: UsersData,
    colors: DetailedHeaderColors,
    navigator: Navigator,
    back: DrawableResource,
    tabs: @Composable () -> Unit = {},
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
    menuOption: @Composable () -> Unit = {}
) = Column(
    modifier = modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(if (orientation is Landscape) 20.dp else 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f).wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (orientation is Portrait) Icon(
                modifier = Modifier.size(24.dp).onClick { navigator.go(-1) },
                painter = painterResource(back),
                tint = colors.icon,
                contentDescription = "Icon"
            )
            Head(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .then(
                        when (orientation) {
                            is Landscape -> Modifier.padding(start = 10.dp)
                            is Portrait -> Modifier
                        }
                    ),
                data = HeadData(
                    avatar = user.userAvatar,
                    name = user.fullName,
                    gender = user.gender.getLabels(
                        labels.header.gender
                    ),
                    list = user.toProfileData(labels),
                    flag = user.flag
                ),
                colors = colors.head,
                orientation = orientation,
            )
        }

        menuOption()
    }
    tabs()
}