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