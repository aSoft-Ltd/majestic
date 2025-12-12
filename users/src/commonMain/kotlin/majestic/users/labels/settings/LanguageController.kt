package majestic.users.labels.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageController {
    private val current = MutableStateFlow(Language.ENGLISH)
    val language: StateFlow<Language> = current.asStateFlow()

    fun setLanguage(lang: Language) {
        current.value = lang
    }
}

@Composable
fun rememberLanguage() = remember {
    LanguageController()
}