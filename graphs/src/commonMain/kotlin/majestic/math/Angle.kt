package majestic.math

import kotlin.math.PI

/**
 * Represent angular information
 *
 * e.g.
 * val angle = 180.deg // PI.rad
 */
sealed interface Angle {
    fun inDegrees(): Float = when (this) {
        is Degrees -> value
        is Radians -> ((value * 180) / PI).toFloat()
    }

    fun inRadians(): Float = when (this) {
        is Degrees -> ((value * PI) / 180f).toFloat()
        is Radians -> value
    }
}

data class Radians(val value: Float) : Angle

data class Degrees(val value: Float) : Angle

val Number.deg get(): Angle = Degrees(toFloat())

val Number.rad get(): Angle = Radians(toFloat())