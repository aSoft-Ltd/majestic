package majestic.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class StartWindow(val width: Int, val height: Int) {
    @SerialName("iphone-xr")
    IphoneXR(414, 896),

    @SerialName("iphone-12-pro")
    Iphone12Pro(390, 844),
}