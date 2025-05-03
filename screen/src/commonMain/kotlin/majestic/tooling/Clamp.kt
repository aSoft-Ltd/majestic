package majestic.tooling

inline fun clamp(min: Float, value: Float, max: Float): Float = when {
    value < min -> min
    value > max -> max
    else -> value
}