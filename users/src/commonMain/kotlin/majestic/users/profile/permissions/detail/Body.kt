package majestic.users.profile.permissions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
internal fun Body(
    modifier: Modifier,
    props: DetailedProperties
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
                .padding(10.dp),
            props = DetailedItemProperties(
                colors = props.colors.detailedItem,
                item = permission,
                itemState = switch
            ),
            onSwitching = {
                switch = it
            }
        )
        if (index != props.permissions.lastIndex) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(props.colors.separator.copy(.05f))
        )
    }
}