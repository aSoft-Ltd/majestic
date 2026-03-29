package majestic.users.profile.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.button.appearence.headerIconButton
import majestic.button.basic.HeaderIconButton
import majestic.icons.Res
import majestic.icons.ic_arrow_left
import majestic.shared.tools.CommonProfileColors
import majestic.shared.users.label.profile.ProfileLabels
import majestic.shared.users.tools.UsersData
import majestic.users.profile.Head
import majestic.users.profile.header.tools.HeadData
import majestic.users.profile.header.tools.toProfileData
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DetailHeader(
    labels: ProfileLabels,
    user: UsersData,
    colors: CommonProfileColors,
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
        verticalAlignment = if (orientation == Portrait) Alignment.CenterVertically else Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f).wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (orientation is Portrait) HeaderIconButton(
                icon = vectorResource(Res.drawable.ic_arrow_left),
                modifier = Modifier.headerIconButton(
                    color = colors.iconButton,
                    alpha = 0f,
                    onClick = { navigator.go(-1) },
                )
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
                colors = colors,
                orientation = orientation,
            )
        }

        menuOption()
    }
    tabs()
}