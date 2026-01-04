package majestic.users.profile.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import majestic.users.labels.profile.TabLabels
import majestic.users.profile.header.tools.header.Head
import majestic.users.profile.header.tools.header.HeadColors
import majestic.users.profile.header.tools.header.tools.HeadData
import majestic.users.profile.header.tools.toProfileData
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


data class ProfileHeaderLabels(
    val header: HeaderLabels,
    val tabs: TabLabels
)

data class DetailedHeaderColors(
    val icon: Color,
    val head: HeadColors
)

@Composable
fun DetailHeader(
    labels: ProfileHeaderLabels,
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
                tint = colors.icon, //if (theme.mode is Light) theme.dominant.contra.color else theme.surface.contra.color
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
                colors = colors.head,
                orientation = orientation,
            )
        }

        menuOption()
    }
    tabs()
}