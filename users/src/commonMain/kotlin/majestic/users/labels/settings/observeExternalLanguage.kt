package majestic.users.labels.settings


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Observes an external language state and syncs it to the internal LanguageController
 *
 * @param T The external language type (e.g., academia's LanguageSetting)
 * @param externalLanguage StateFlow from external module
 * @param mapper Function to convert external enum to internal Language enum
 * @param controller Your module's language controller
 * @param scope CoroutineScope to collect the flow
 */
internal fun <T> observeExternalLanguage(
    externalLanguage: StateFlow<T>,
    mapper: (T) -> Language,
    controller: LanguageController,
    scope: CoroutineScope
) {
    scope.launch {
        externalLanguage.collect { externalValue ->
            controller.setLanguage(mapper(externalValue))
        }
    }
}