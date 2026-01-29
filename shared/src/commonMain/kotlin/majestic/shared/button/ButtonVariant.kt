package majestic.shared.button

sealed interface ButtonVariant {
    sealed interface Primary : ButtonVariant {
        data object Default : Primary
        data object Destructive : Primary
        data object Success : Primary
    }

    sealed interface Secondary : ButtonVariant {
        sealed interface Opaque : Secondary {
            data object Default : Opaque
            data object Destructive : Opaque
            data object Success : Opaque
        }

        sealed interface Transparent : Secondary {
            data object Default : Transparent
            data object Destructive : Transparent
            data object Success : Transparent
        }

        sealed interface Outlined : Secondary {
            data object Default : Outlined
            data object Destructive : Outlined
            data object Success : Outlined
        }
    }
}