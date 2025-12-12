package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import majestic.users.labels.general.SectionLabels

@Composable
internal fun SectionHeading(
    labels: SectionLabels,
    color: Color,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    Text(
        text = labels.label,
        fontWeight = FontWeight.Bold,
        color = color
    )
    Text(
        text = labels.description,
        color = color.copy(alpha = 0.5f)
    )
}