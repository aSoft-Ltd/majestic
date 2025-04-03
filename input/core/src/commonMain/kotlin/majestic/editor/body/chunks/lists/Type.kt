package majestic.editor.body.chunks.lists

sealed class Type {
    sealed class Ordered : Type() {
        data object Numbers : Ordered()
        data class Alphabetic(val caps: Boolean) : Ordered()
        data class Roman(val caps: Boolean) : Ordered()
    }
    sealed class UnOrdered : Type() {
        data object Bullet : UnOrdered()
        data object Arrow : UnOrdered()
        data object Diamond : UnOrdered()
    }
}