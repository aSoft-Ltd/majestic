package kiota.connectivity

data class Online(
    override val medium: Medium,
    /**In per seconds*/
    val speed: Int
) : Connected