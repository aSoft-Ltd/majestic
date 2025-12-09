package majestic.payments.tools

internal fun Number.formatWithCommas(): String {
    val parts = this.toString().split('.')
    val whole = parts[0]
    val decimal = parts.getOrNull(1)

    val formattedWhole = whole.reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return if (decimal != null) "$formattedWhole.$decimal" else formattedWhole
}
