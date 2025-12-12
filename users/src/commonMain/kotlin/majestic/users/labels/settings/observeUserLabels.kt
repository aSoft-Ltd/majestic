package majestic.users.labels.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.map
import majestic.users.labels.UsersLabels

@Composable
internal fun observeUsersLabels(
    languageController: LanguageController
): State<UsersLabels> {
    return remember(languageController) {
        languageController.language.map { lang ->
            when (lang) {
                Language.ENGLISH -> UsersLabels.english
                Language.SWAHILI -> UsersLabels.swahili
            }
        }
    }.collectAsState(UsersLabels.english)
}