package majestic.button

data class ButtonState<out S>(
    val default: S,
    val hovered: S = default,
    val pressed: S = hovered,
    val disabled: S = default,
)