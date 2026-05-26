---
title: majestic.drawer
weight: 1
bookCollapseSection: false
---

# `majestic.drawer`

`majestic.drawer` is the drawer-hosting package in Majestic. It lets you declare one or more drawers, attach them to a host, and then open, close, toggle, add, or remove them through a controller.

A typical usage pattern looks like this:

- create one or more drawers
- register them in a host controller
- wrap the screen in `DrawerHost`
- drive drawer state from the controller

## Public API Overview

The main public entry points are:

- `rememberInlineDrawer(...)`
- `rememberOverlayDrawer(...)`
- `rememberDrawerHostController(...)`
- `rememberSingleDrawerController()`
- `DrawerHost(...)`
- `InlineDrawerHost(...)`
- `OverlayDrawerHost(...)`
- `MultiDrawerController`
- `SingleDrawerController`
- `DrawerContext`

There is also an older `NavigationDrawer(...)` API, but it is deprecated in favor of `DrawerHost(...)`.

## Mental Model

The package is built around four pieces:

1. `Drawer`
2. `MultiDrawerController`
3. `DrawerHost`
4. `HostedDrawerState`

### `Drawer`

A `Drawer` is a remembered description of one drawer:

- size, stored internally as a span
- position: `Left`, `Right`, `Top`, or `Bottom`
- display mode: `Inline` or `Overlay`
- background color for overlay mode
- drawer content

You normally do not construct it directly. Use:

```kotlin
val sideMenu = rememberInlineDrawer(
    span = 400.dp
) {
    // drawer content
}
```

or:

```kotlin
val sideMenu = rememberOverlayDrawer(
    span = 400.dp
) {
    // drawer content
}
```

Important defaults:

- `rememberInlineDrawer(...)` defaults `position` to `DrawerPosition.Left`
- `rememberOverlayDrawer(...)` defaults `position` to `DrawerPosition.Left`
- `rememberOverlayDrawer(...)` defaults `background` to `Color.Transparent`
- `rememberInlineDrawer(...)` does not expose a `background` parameter

If a drawer should open from a non-default side, pass `position` explicitly:

```kotlin
val filters = rememberOverlayDrawer(
    span = 320.dp,
    position = DrawerPosition.Right
) {
    // drawer content
}
```

## Display Modes

### Inline

Inline drawers take space from the content area. In the host implementation, inline drawers are partitioned first and rendered recursively by position. If the drawer is open, its animated width or height is carved out of the host size.

Use inline drawers when the layout should visibly reflow around the drawer.

### Overlay

Overlay drawers sit on top of the content. When open, they animate in from their edge and can tint the background using the drawer's `background` color.

Use overlay drawers when the main content should remain laid out underneath.

## Backgrounds

There are two different background concepts in this package:

1. the drawer body background
2. the overlay backdrop background

### Drawer body background

Both inline and overlay drawers can have a visible panel background by styling the outermost container inside the drawer content:

```kotlin
val sideMenu = rememberInlineDrawer(span = 400.dp) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        // content
    }
}
```

The same pattern works for overlay drawers:

```kotlin
val sideMenu = rememberOverlayDrawer(span = 400.dp) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        // content
    }
}
```

### Overlay backdrop background

Overlay drawers also support a separate `background` parameter:

```kotlin
val sideMenu = rememberOverlayDrawer(
    span = 400.dp,
    background = Color.Black.copy(alpha = 0.35f)
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        // content
    }
}
```

This `background` is the backdrop or overlay tint drawn behind the drawer while it is open. It is not the same thing as the drawer body's own background.

Inline drawers do not expose this backdrop parameter because they participate in layout instead of floating above the content.

## Sizes

There are two public ways to define size:

- fixed size with `span = 400.dp`
- proportional size with `ratio = 0.5f`

Internally these become:

- `DpSpan`
- `RatioSpan`

At render time, the host converts that span into real `Dp` based on the host size.

Practical guidance:

- use `span` when the drawer must keep a predictable fixed size
- use `ratio` when the drawer should adapt to host width or height

## Host And Controller

The standard setup is:

```kotlin
val left = rememberInlineDrawer(
    span = 400.dp
) {
    // content
}

val overlay = rememberOverlayDrawer(
    span = 400.dp
) {
    // content
}

val controller = rememberDrawerHostController(left, overlay)

DrawerHost(controller) {
    // main content
}
```

If one drawer should be visible immediately, open it explicitly:

```kotlin
val left = rememberInlineDrawer(span = 400.dp) { /* content */ }
val controller = rememberDrawerHostController(left)

LaunchedEffect(Unit) {
    controller.open(left)
}
```

`rememberDrawerHostController(...)` creates a `MultiDrawerHostController` with all supplied drawers registered as closed.

`DrawerHost(...)` then:

- reads the current host size
- splits drawers into inline and overlay groups
- renders inline drawers as part of layout
- renders overlay drawers above the content
- exposes the controller through `LocalDrawerHostControllerContext`

That last part matters because `DrawerHost(...)` has a default controller parameter:

```kotlin
fun DrawerHost(
    controller: MultiDrawerController = LocalDrawerHostControllerContext.current,
    ...
)
```

In practice, most screens should still pass the controller explicitly. The composition local mainly allows nested drawer-aware content to resolve the current host controller without plumbing it everywhere.

## Single Drawer Hosts

If a screen only needs one drawer, the package also provides host wrappers:

- `InlineDrawerHost(...)`
- `OverlayDrawerHost(...)`

These wrappers:

- take a `SingleDrawerController`
- create and register a drawer internally
- wrap the content in a `DrawerHost(...)`
- remove the registered drawer automatically on dispose

Example:

```kotlin
val controller = rememberSingleDrawerController()

OverlayDrawerHost(
    controller = controller,
    span = 320.dp
) {
    // drawer content
} content@{
    // main content
}
```

Use these wrappers when:

- only one drawer is needed
- the screen does not benefit from managing multiple named drawers directly

Use `DrawerHost(...)` with `rememberDrawerHostController(...)` when:

- a screen needs multiple drawers
- drawers need different display modes or positions
- drawers may be added or removed dynamically

## Opening And Toggling

The controller supports:

- `open(drawer)`
- `close(drawer)`
- `toggle(drawer)`
- `open()` / `close()` / `toggle()` for all drawers
- `add(...)` for runtime-created drawers
- `remove(key)` for runtime cleanup

There are two controller shapes:

- `SingleDrawerController`
- `MultiDrawerController`

`SingleDrawerController` supports screen-level drawer state only:

- `open()`
- `open(ratio)`
- `open(span)`
- `close()`
- `toggle()`
- `toggle(ratio)`
- `toggle(span)`

`MultiDrawerController` extends that with per-drawer operations:

- `open(drawer)`
- `close(drawer)`
- `toggle(drawer)`
- `state(drawer)`
- `add(...)`
- `remove(...)`

Example:

```kotlin
val left = rememberInlineDrawer(span = 400.dp) { /* content */ }
val controller = rememberDrawerHostController(left)

LaunchedEffect(Unit) {
    controller.open(left)
}
```

This opens the selected drawer as soon as the screen mounts.

A screen action can toggle drawers individually:

```kotlin
val left = rememberInlineDrawer(span = 400.dp) { /* content */ }
val controller = rememberDrawerHostController(left)

Button(modifier = Modifier.onClick {
    controller.toggle(left)
}) {
    Text("Toggle Left Drawer")
}
```

and:

```kotlin
val overlay = rememberOverlayDrawer(span = 400.dp) { /* content */ }
val controller = rememberDrawerHostController(overlay)

Button(modifier = Modifier.onClick {
    controller.toggle(overlay)
}) {
    Text("Toggle Overlay Drawer")
}
```

The controller also supports key-based operations:

```kotlin
val controller = rememberDrawerHostController()

controller.add(drawer = "filters", ratio = 0.4f) {
    // content
}

controller.toggle(drawer = "filters")
controller.open(drawer = "filters")
controller.close(drawer = "filters")
```

That works because hosted drawers are tracked with keys in the internal state map.

## Drawer Content

Drawer content receives a `DrawerContext`:

```kotlin
data class DrawerContext(
    val controller: MultiDrawerController,
    val drawer: Drawer,
)
```

That means a drawer can close or toggle itself from inside its own content:

```kotlin
Button(modifier = Modifier.onClick {
    it.controller.toggle()
}) {
    Text("Toggle")
}
```

This is useful when drawer content needs to dismiss or reconfigure itself without pushing that logic back into the screen container.

## Runtime Drawer Creation

`MultiDrawerController.add(...)` supports runtime drawer registration.

There are two API styles:

### Immediate creation with an explicit key

```kotlin
val drawer = controller.add(
    key = "filters",
    span = 320.dp
) {
    // content
}
```

or:

```kotlin
val drawer = controller.add(
    drawer = "filters",
    ratio = 0.4f
) {
    // content
}
```

### Property-delegate creation

```kotlin
val filters by controller.add(
    span = 320.dp,
    position = DrawerPosition.Right
) {
    // content
}
```

The delegate form automatically creates a stable key from the property name and avoids duplicate creation if the property is resolved again.

Important defaults for `add(...)` differ from the drawer factory helpers:

- runtime `add(...)` defaults `position` to `DrawerPosition.Right`
- runtime `add(...)` defaults `display` to `DrawerDisplay.Overlay`
- runtime `add(...)` defaults `background` to `Color.Transparent`

This is different from `rememberInlineDrawer(...)` and `rememberOverlayDrawer(...)`, which default `position` to `DrawerPosition.Left`.

## Runtime State

Internally, the host controller stores each drawer in a state map:

- `ClosedDrawer(key)`
- `OpenedDrawer(key, span, display)`

That state model matters because:

- open state can override span temporarily
- display mode is resolved from current hosted state
- drawers can be looked up and managed by key

This is what allows APIs like:

```kotlin
val controller = rememberDrawerHostController()

controller.add(drawer = "Test", ratio = 0.4f) { ... }
controller.toggle(drawer = "Test")
controller.remove("Test")
```

The key detail is that opening a drawer stores an `OpenedDrawer(key, span, display)` entry, while closing stores `ClosedDrawer(key)`. That allows the controller to:

- preserve identity by key
- override span when opening or toggling
- resolve the current display mode from hosted state

## Positions

Supported positions are:

- `DrawerPosition.Left`
- `DrawerPosition.Right`
- `DrawerPosition.Top`
- `DrawerPosition.Bottom`

Inline and overlay rendering both branch on this enum to decide:

- whether width or height is animated
- whether the drawer enters from the negative edge or from the far edge
- whether content is laid out before or after the drawer

Defaults are not uniform across the package:

- remembered drawer factories default to `Left`
- dynamic `add(...)` defaults to `Right`

Developers should set `position` explicitly when screen layout matters, instead of relying on those defaults.

## Inline vs Overlay Rendering Details

Inline drawers and overlay drawers are hosted differently.

Inline drawers:

- are partitioned first inside `DrawerHost(...)`
- are rendered recursively
- shrink the remaining content space as they open
- animate width for left/right drawers
- animate height for top/bottom drawers

Overlay drawers:

- are rendered on top of the content after inline layout is placed
- animate into view with offset changes
- can tint the host background with the drawer `background`
- do not force the content area to reflow

This distinction is the most important design decision when adopting the package.

## Deprecated API

`NavigationDrawer(...)` still exists, but it is marked deprecated in favor of `DrawerHost(...)`.

It uses the older `DrawerController` model:

- `state`
- `overlap`
- `ratio`
- `direction`
- `animation`

That API is still useful for reading older code, but new code should use:

- `DrawerHost(...)`
- `rememberDrawerHostController(...)`
- `rememberInlineDrawer(...)`
- `rememberOverlayDrawer(...)`

## Choosing An Approach

Use `DrawerHost(...)` with `rememberDrawerHostController(...)` when:

- you need multiple drawers
- you need key-based drawer lookup
- you need runtime `add(...)` and `remove(...)`
- you want inline and overlay drawers on the same screen

Use `InlineDrawerHost(...)` or `OverlayDrawerHost(...)` when:

- you need only one drawer
- you want a simpler screen setup
- you do not need dynamic multi-drawer behavior

## Recommended Usage

Start with this pattern:

1. Declare drawers with `rememberInlineDrawer` or `rememberOverlayDrawer`.
2. Create a controller with `rememberDrawerHostController(...)`.
3. Wrap the screen with `DrawerHost(controller)`.
4. Toggle drawers from screen actions or from inside drawer content via `DrawerContext`.

Use inline mode for structural panels and overlay mode for transient panels.

## Recommended Baseline

For most screens, start with:

1. one or more remembered drawers
2. one remembered host controller
3. one `DrawerHost`
4. explicit open or toggle calls from screen actions

Use fixed `span` when drawer size should remain constant.

Use `ratio` when drawer size should scale with available host space.

Use `Inline` when content should resize around the drawer.

Use `Overlay` when the drawer should slide above existing content.

Set `position` explicitly in production code unless the default is a deliberate design choice.
