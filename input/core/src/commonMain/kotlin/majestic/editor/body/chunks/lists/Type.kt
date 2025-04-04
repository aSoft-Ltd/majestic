package majestic.editor.body.chunks.lists

sealed interface Type {
    sealed interface Ordered : Type {
        data object Numbers : Ordered
        data class Alphabetic(val caps: Boolean) : Ordered
        data class Roman(val caps: Boolean) : Ordered
    }
    sealed interface UnOrdered : Type {
        data object Bullet : UnOrdered
        data object Arrow : UnOrdered
        data object Diamond : UnOrdered
    }
}