package majestic.graph.arc

data class ArcSpec(
    val colors: ArcColors = ArcColors(),
    val angles: Arc = Arc(),
    val stroke: ArcStroke = ArcStroke(),
    val layout: ArcLayout = ArcLayout(),
    val animation: ArcAnimation = ArcAnimation()
)