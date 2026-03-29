package majestic.shared.profiles.contacts.tools.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import dev.chrisbanes.haze.HazeState
import majestic.button.Button
import majestic.button.appearence.addContactOption
import majestic.button.basic.BasicButtonContent
import majestic.button.expandable.ExpandableButton
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.users.UsersLabels
import majestic.shared.users.label.contacts.OptionLabels
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun FloatingButtons(
    orientation: ScreenOrientation,
    buttonBox: Rect,
    labels: UsersLabels,
    colors: ContactsColors,
    hazeState: HazeState,
    onEmailButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit,
    onPlaced: (LayoutCoordinates) -> Unit,
) {
    val isPortrait = orientation is Portrait
    var isOpen by remember { mutableStateOf(false) }

    val popupAlignment = if (isPortrait) Alignment.BottomEnd else Alignment.TopEnd
    val expandDirection = if (isPortrait) Alignment.Bottom else Alignment.Top
    val boxAlignment = if (isPortrait) Alignment.BottomEnd else Alignment.TopStart

    val popupOffset = if (isPortrait) {
        IntOffset(x = 0, y = -40)
    }
    else {
        IntOffset(x = 0, y = buttonBox.bottom.toInt() + 20)
    }

    val animationState = remember { MutableTransitionState(isOpen) }.apply {
        targetState = isOpen
    }

    Box(
        modifier = Modifier.offset(y = (-20).dp, x = (-20).dp),
        contentAlignment = boxAlignment
    ) {
        Box(modifier = Modifier.onPlaced { onPlaced(it) }) {
            ExpandableButton(
                icon = vectorResource(Res.drawable.ic_add),
                label = labels.profile.tabs.contacts.content.addButton,
                colors = colors.button,
                orientation = orientation,
                forceExpanded = isOpen,
                onClick = { isOpen = !isOpen },
            )
        }

        if (animationState.currentState || animationState.targetState) {
            Popup(
                alignment = popupAlignment,
                offset = popupOffset,
                properties = PopupProperties(clippingEnabled = false, focusable = true),
                onDismissRequest = { isOpen = false }
            ) {
                AnimatedVisibility(
                    visibleState = animationState,
                    enter = fadeIn() + expandVertically(expandFrom = expandDirection),
                    exit = fadeOut() + shrinkVertically(shrinkTowards = expandDirection)
                ) {
                    ContactOptionsList(
                        modifier = Modifier.padding(bottom = if (isPortrait) 8.dp else 0.dp),
                        colors = colors,
                        labels = labels.profile.tabs.contacts.content.options,
                        hazeState = hazeState,
                        onEmailButtonClick = {
                            onEmailButtonClick()
                            isOpen = false
                        },
                        onPhoneButtonClick = {
                            onPhoneButtonClick()
                            isOpen = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactOptionsList(
    modifier: Modifier = Modifier,
    colors: ContactsColors,
    labels: OptionLabels,
    hazeState: HazeState,
    onEmailButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            modifier = Modifier.addContactOption(
                color = colors.hazeButton,
                hazeEdgeColor = colors.hazeColors.edges,
                hazeMiddleColor = colors.hazeColors.middle,
                hazeState = hazeState,
                onClick = onEmailButtonClick
            )
        ) { btnColors ->
            BasicButtonContent(
                text = labels.email,
                colors = btnColors
            )
        }

        Button(
            modifier = Modifier.addContactOption(
                color = colors.hazeButton,
                hazeEdgeColor = colors.hazeColors.edges,
                hazeMiddleColor = colors.hazeColors.middle,
                hazeState = hazeState,
                onClick = onPhoneButtonClick
            )
        ) { btnColors ->
            BasicButtonContent(
                text = labels.phone,
                colors = btnColors
            )
        }
    }
}