package majestic

data class ColorPickerLabels(
    val title: String,
    val value: String,
    val cancel: String = "Cancel",
    val confirm: String = "Change"
)