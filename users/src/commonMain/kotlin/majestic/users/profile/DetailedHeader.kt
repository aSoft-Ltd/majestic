package majestic.users.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Light
import majestic.ThemeColor
import majestic.tooling.onClick
import majestic.users.profile.tabs.ProfileTabs
import majestic.users.profile.tools.ProfileDestinationMapper
import majestic.users.profile.tools.header.Head
import majestic.users.profile.tools.header.tools.HeadData
import majestic.users.profile.tools.toProfileData
import majestic.users.tools.colors.background
import majestic.users.tools.data.GenderLabels
import majestic.users.tools.data.UsersData
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class HeaderLabels(
    val joined: String,
    val phone: String,
    val lastActive: String,
    val dob: String,
    val gender: GenderLabels
)

data class TabsLabels(
    val permissions: String,
    val contacts: String,
    val roles: String,
    val security: String
)

data class ProfileHeaderLabels(
    val header: HeaderLabels,
    val tabs: TabsLabels
)

@Composable
fun DetailHeader(
    labels: ProfileHeaderLabels,
    user: UsersData,
    theme: ThemeColor,
    navigator: Navigator,
    back: DrawableResource,
    endpoint: ProfileDestinationMapper,
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
                tint = if (theme.mode is Light) theme.dominant.contra.color else theme.surface.contra.color,
                contentDescription = "Icon"
            )
            Head(
                modifier = Modifier.height(IntrinsicSize.Max),
                data = HeadData(
                    avatar = user.userAvatar,
                    name = user.fullName,
                    gender = user.gender.getLabels(
                        labels.header.gender
                    ),
                    list = user.toProfileData(labels),
                    flag = user.flag
                ),
                theme = theme,
                orientation = orientation,
            )
        }

        menuOption()
    }
    ProfileTabs(
        modifier = Modifier
            .height(if (orientation is Landscape) 50.dp else 40.dp)
            .fillMaxWidth()
            .background(color = theme.background)
            .padding(horizontal = if (orientation is Landscape) 30.dp else 10.dp)
            .horizontalScroll(rememberScrollState()),
        labels = labels.tabs,
        themes = theme,
        navigator = navigator,
        endpoint = endpoint
    )
}