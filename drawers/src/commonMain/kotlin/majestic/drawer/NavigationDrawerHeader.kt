package majestic.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Simple header helper for legacy navigation drawer content.
 *
 * Renders a close affordance aligned to the end of the header, then composes
 * [content] below it.
 *
 * @param modifier modifier applied to the outer column.
 * @param onClose invoked when the close affordance is clicked.
 * @param content header content below the close affordance.
 */
@Deprecated(
    message = "The NavigationDrawer API is deprecated. Build drawer headers inside DrawerHost drawer content instead.",
    replaceWith = ReplaceWith(
        imports = arrayOf(
            "androidx.compose.foundation.layout.Column"
        ),
        expression = "Column(modifier) { content() }",
    )
)
@Composable
fun NavigationDrawerHeader(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) = Column(modifier) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClose),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(Icons.Filled.Close, "close")
    }
    content()
}
