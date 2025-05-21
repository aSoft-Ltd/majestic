package majestic

class MenuItem(
    val label: String,
    val graphic: MenuGraphic? = null,
    val onClick: () -> Unit = {}
)