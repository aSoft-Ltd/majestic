package majestic

import androidx.compose.runtime.Composable

data class PaginatorIcons(
    val next: (@Composable () -> Unit)? = null,
    val prev: (@Composable () -> Unit)? = null,
    val first: (@Composable () -> Unit)? = null,
    val last: (@Composable () -> Unit)? = null,
)
