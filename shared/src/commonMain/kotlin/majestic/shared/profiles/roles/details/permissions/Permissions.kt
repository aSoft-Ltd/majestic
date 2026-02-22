package majestic.shared.profiles.roles.details.permissions

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.toolbar.underline
import majestic.editor.tools.StateColors
import majestic.shared.profiles.Permission

data class ResponsibilityColors(
    val background: StateColors,
    val separator: Color,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color
)

@Composable
internal fun Permissions(
    modifier: Modifier,
    responsibilities: List<Permission>,
    colors: ResponsibilityColors,
    orientation: ScreenOrientation
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top
) {
    responsibilities.forEachIndexed { index, responsibility ->
        val interaction = remember { MutableInteractionSource() }
        val hovered by interaction.collectIsHoveredAsState()
        PermissionItem(
            modifier = Modifier.responsibilityItem(
                interaction = interaction,
                hovered = hovered,
                orientation = orientation,
                colors = colors,
                index = index,
                responsibilities = responsibilities
            ),
            index = index,
            responsibility = responsibility,
            colors = colors,
            orientation = orientation
        )
        if (responsibilities.lastIndex != responsibilities.indexOf(responsibility)) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.separator, 0.5.dp)
        )
    }
}