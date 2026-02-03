package majestic.button

fun <T> ButtonParams<T>.toResolved(
    hovered: Boolean,
    pressed: Boolean,
    disabled: Boolean,
): T {
    if (disabled) return this.disabled
    if (pressed) return this.pressed
    if (hovered) return this.hovered
    return this.default
}