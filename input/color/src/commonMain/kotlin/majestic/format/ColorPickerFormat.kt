package majestic.format

sealed interface ColorPickerFormat

data object RGB : ColorPickerFormat

data object HSL : ColorPickerFormat

data object HSV : ColorPickerFormat

data object HEX : ColorPickerFormat