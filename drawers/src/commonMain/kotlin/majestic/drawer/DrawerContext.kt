package majestic.drawer

/**
 * Context passed to drawer content while it is rendered by a [DrawerHost].
 *
 * Use [controller] when the drawer content needs to open, close, toggle, add,
 * or remove drawers. Use [drawer] when the content needs to target the drawer
 * instance that is currently being rendered.
 */
data class DrawerContext(
    /** Controller for the host that is rendering this drawer. */
    val controller: MultiDrawerController,
    /** The drawer instance whose content is currently being composed. */
    val drawer: Drawer,
)
