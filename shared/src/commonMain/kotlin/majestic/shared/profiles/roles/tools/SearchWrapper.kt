package majestic.shared.profiles.roles.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.Search
import majestic.icons.Res
import majestic.icons.ic_search
import majestic.shared.profiles.roles.assign.form.SearchColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.details.header.RolesHeaderLabels
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SearchWrapper(
    modifier: Modifier,
    colors: SearchColors,
    controller: AssignmentController,
    labels: RolesHeaderLabels
) = Search(
    modifier = modifier,
    value = controller.searchQuery,
    onChange = { controller.searchQuery = it },
    hint = labels.search,
    colors = colors.default,
    icon = {
        val interaction = remember { MutableInteractionSource() }
        val hovered by interaction.collectIsHoveredAsState()
        Icon(
            painter = painterResource(Res.drawable.ic_search),
            contentDescription = null,
            tint = colors.icon.foreground.unfocused,
            modifier = Modifier
                .padding(2.dp)
                .hoverable(interaction)
                .background(
                    color = if (hovered) colors.icon.background.focused else colors.icon.background.unfocused,
                    shape = CircleShape
                )
                .padding(8.dp)
                .size(24.dp)
        )
    }
)