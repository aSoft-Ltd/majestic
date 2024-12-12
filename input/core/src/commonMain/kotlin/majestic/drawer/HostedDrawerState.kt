package majestic.drawer

internal sealed interface HostedDrawerState {
    val key: Any
    val triggered: Boolean
}

internal data class OpenedDrawer(
    override val key: Any,
    val span: DrawerSpan,
    val display: DrawerDisplay
) : HostedDrawerState {
    override val triggered = true
}

internal data class ClosedDrawer(
    override val key: Any,
    override val triggered: Boolean
) : HostedDrawerState