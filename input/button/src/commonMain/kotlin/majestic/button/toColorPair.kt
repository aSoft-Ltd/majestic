package majestic.button

import majestic.ColorPair

fun ButtonState<ColorPair>.toColorPair(
    hovered: Boolean,
    pressed: Boolean,
    disabled: Boolean,
): ColorPair {
    if (disabled) return this.disabled
    if (pressed) return this.pressed
    if (hovered) return this.hovered
    return this.default
}