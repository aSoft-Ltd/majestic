package majestic.shared.profiles.contacts.tools.buttons

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.buttons.ButtonAnimateColors
import majestic.buttons.floatActionButton
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.button.Button
import majestic.shared.button.LeadingIcon
import majestic.shared.profiles.contacts.email.dialogs.EmailDialogState
import majestic.shared.profiles.contacts.phone.dialogs.PhoneDialogState
import majestic.shared.profiles.contacts.tools.ContactsColors
import majestic.shared.users.UsersLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource

internal fun Modifier.buttonAnimate(
    colors: ButtonAnimateColors,
    interactionSource: MutableInteractionSource,
    animationSpec: FiniteAnimationSpec<IntSize> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow,
    ),
    isHovered: Boolean,
    onClick: () -> Unit
) = this
    .clip(CircleShape)
    .background(if (isHovered) colors.hovered.background else colors.inactive.background)
    .pointerHoverIcon(PointerIcon.Hand)
    .hoverable(interactionSource = interactionSource)
    .padding(10.dp)
    .animateContentSize(animationSpec)
    .onClick(onClick)

@Composable
internal fun FloatingButtons(
    orientation: ScreenOrientation,
    buttonBox: Rect,
    labels: UsersLabels,
    isOpen: Boolean,
    colors: ContactsColors,
    onEmailButtonClick: () -> Unit,
    onPhoneButtonClick: () -> Unit,
    emailDialog: EmailDialogState,
    phoneDialog: PhoneDialogState,
    onDismiss: () -> Unit = {},
    onOpen: () -> Unit,
    onPlaced: (LayoutCoordinates) -> Unit
) = when (orientation) {
    is Landscape -> Box(modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)) {
        Box(modifier = Modifier.onPlaced { onPlaced(it) }) {
            Button(
                label = labels.profile.tabs.contacts.content.addButton,
                leadingIcon = { LeadingIcon(vectorResource(Res.drawable.ic_add)) },
                forceExpanded = isOpen,
                theme = colors.theme,
                expandable = true,
                orientation = orientation,
                onClick = {
                    onOpen()
                }
            )
        }
        LandscapeButtons(
            buttonBox, colors,
            onEmailButtonClick = onEmailButtonClick,
            onPhoneButtonClick = onPhoneButtonClick,
            labels = labels.profile.tabs.contacts.content.options
        )
    }

    is Portrait -> Box(
        modifier = Modifier.offset(y = (-40).dp, x = (-30).dp)
    ) {
        PortraitButtons(
            modifier = Modifier
                .floatActionButton(colors.floatingButton.background)
                .onClick { onOpen() },
            colors = colors,
            isOpen = isOpen,
            emailDialog = emailDialog,
            phoneDialog = phoneDialog,
            onDismiss = onDismiss,
            labels = labels.profile.tabs.contacts.content.options
        )
    }
}