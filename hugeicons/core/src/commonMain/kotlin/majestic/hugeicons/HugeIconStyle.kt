package majestic.hugeicons

sealed interface HugeIconStyle {
    enum class Stroke : HugeIconStyle { Standard, Rounded, Sharp }
    enum class Solid : HugeIconStyle { Standard, Rounded, Sharp }
    data object Bulk : HugeIconStyle
    data object Duotone : HugeIconStyle
    data object Twotone : HugeIconStyle
}