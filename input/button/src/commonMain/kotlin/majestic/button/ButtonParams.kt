package majestic.button

data class ButtonParams<out S>(
    val default: S,
    val hovered: S = default,
    val pressed: S = hovered,
    val disabled: S = default,
)