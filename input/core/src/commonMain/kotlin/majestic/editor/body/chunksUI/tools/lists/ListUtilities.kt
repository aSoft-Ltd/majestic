package majestic.editor.body.chunksUI.tools.lists

import majestic.editor.body.chunks.lists.Type


internal object ListUtilities {
    fun getMarker(type: Type.Ordered, index: Int): String = when (type) {
        is Type.Ordered.Numbers -> "${index + 1}. "
        is Type.Ordered.Alphabetic -> {
            val letter = ('A' + index).toString() + ". "
            if (type.caps) letter else letter.lowercase()
        }

        is Type.Ordered.Roman -> {
            val roman = toRoman(index + 1) + ". "
            if (type.caps) roman else roman.lowercase()
        }
    }


    fun getMarker(type: Type.UnOrdered): String = when (type) {
        is Type.UnOrdered.Bullet -> "● "
        is Type.UnOrdered.Arrow -> "➤ "
        is Type.UnOrdered.Diamond -> "◆ "
    }

    private fun toRoman(number: Int): String {
        if (number <= 0) return ""
        val romanValues = listOf(
            1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
            100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
            10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
        )
        var result = ""
        var remaining = number
        for ((value, symbol) in romanValues) {
            while (remaining >= value) {
                result += symbol
                remaining -= value
            }
        }
        return result
    }
}