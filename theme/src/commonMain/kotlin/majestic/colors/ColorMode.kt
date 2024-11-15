package majestic.colors

enum class ColorMode {
    Dark, Light;

    fun toggled() = when (this) {
        Dark -> Light
        Light -> Dark
    }
}