package majestic.button.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.button.Button
import majestic.icons.Res
import majestic.icons.ic_cancel
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CloseModalButton(modifier: Modifier = Modifier) = Button(modifier = modifier) { colors ->
        BasicButtonContent(
            icon = vectorResource(Res.drawable.ic_cancel),
            colors = colors
        )
    }