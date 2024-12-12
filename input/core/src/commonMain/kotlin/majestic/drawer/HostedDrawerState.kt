package majestic.drawer

internal sealed interface HostedDrawerState {
    val key: Any
}

internal data class OpenedDrawer(
    override val key: Any,
    val span: DrawerSpan,
    val display: DrawerDisplay
) : HostedDrawerState

internal data class ClosedDrawer(
    override val key: Any
) : HostedDrawerState