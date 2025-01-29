package majestic

import androidx.compose.runtime.Composable

data class PaginatorIcons(
    val nextPage: (@Composable () -> Unit)? = null,
    val previousPage: (@Composable () -> Unit)? = null,
    val firstPage: (@Composable () -> Unit)? = null,
    val lastPage: (@Composable () -> Unit)? = null,
)
