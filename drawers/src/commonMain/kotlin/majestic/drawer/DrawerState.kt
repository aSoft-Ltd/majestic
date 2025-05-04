package majestic.drawer


enum class DrawerState {
    Open, Closed;

    fun toggled() = when (this) {
        Open -> Closed
        Closed -> Open
    }
}