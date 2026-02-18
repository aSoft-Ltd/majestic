package majestic.shared.profiles.permissions.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.editor.toolbar.underline


@Composable
internal fun Content(
    modifier: Modifier,
    props: DetailedProperties,
    orientation: ScreenOrientation,
    colors: DetailColors
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
) {
    props.permissions.forEachIndexed { index, permission ->
        var switch by remember { mutableStateOf(permission.active) }
        DetailedItem(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            props = DetailedItemProperties(
                colors = colors.body,
                item = permission,
                itemState = switch
            ),
            onSwitching = {
                switch = it
            },
            orientation = orientation
        )
        if (index != props.permissions.lastIndex) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .underline(colors.separator, 1.dp)
        )
    }
}