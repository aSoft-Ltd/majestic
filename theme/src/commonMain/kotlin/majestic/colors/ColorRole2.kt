package majestic.colors

import majestic.ColorPair

data class ColorRole2(
    /**
     * The main color
     */
    val main: ColorPair,
    /**
     * The complementary color
     */
    val comp: ColorPair
)
