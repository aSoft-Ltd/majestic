package majestic.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

@Composable
fun rememberDrawerHostController(vararg drawers: Drawer): MultiDrawerHostController {
    val them = remember(drawers) {
        mutableStateMapOf<Drawer, HostedDrawerState>()
    }

    LaunchedEffect(drawers) {
        drawers.forEach { them[it] = ClosedDrawer }
    }
    return MultiDrawerHostController(them)
}