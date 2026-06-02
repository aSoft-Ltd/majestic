package majestic.drawer

/**
 * Open or closed state of a drawer.
 */
enum class DrawerState {
    Open, Closed;

    /**
     * Returns [Closed] when the current state is [Open], and [Open] when the
     * current state is [Closed].
     */
    fun toggled() = when (this) {
        Open -> Closed
        Closed -> Open
    }
}
