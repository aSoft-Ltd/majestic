package majestic.users.tools.colors

internal fun ThemeColor.toCheckboxColors() = CheckboxColors(
    selected = CheckboxMicroColors(
        background = Color.Transparent,
        border = Color.Transparent,
        icon = ColorPair(
            background = dominant.actual.color,
            foreground = dominant.contra.color
        )
    ),
    unselected = CheckboxMicroColors(
        background = Color.Transparent,
        border = surface.contra.color.copy(0.3f),
        icon = ColorPair(
            background = dominant.actual.color,
            foreground = dominant.contra.color
        )
    )
)