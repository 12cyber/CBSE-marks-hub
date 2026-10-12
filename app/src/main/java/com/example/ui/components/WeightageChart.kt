package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Subject
import kotlin.math.atan2

private val ChartColors = listOf(
    Color(0xFF38BDF8), // Electric Blue
    Color(0xFFA78BFA), // Electric Violet
    Color(0xFFFBBF24), // Amber Gold
    Color(0xFF34D399), // Emerald
    Color(0xFFF472B6), // Pink
    Color(0xFF60A5FA), // Blue
    Color(0xFFFB923C)  // Orange
)

@Composable
fun WeightageChart(
    subject: Subject,
    modifier: Modifier = Modifier
) {
    val totalTheoryMarks = subject.assessmentStructure.maxTheory.toFloat()
    var selectedUnitIndex by remember { mutableStateOf<Int?>(null) }

    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(subject) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(1f, animationSpec = tween(durationMillis = 800))
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(subject) {
                        detectTapGestures { offset ->
                            val center = Offset(size.width / 2f, size.height / 2f)
                            val touchVec = offset - center
                            var angle = Math.toDegrees(atan2(touchVec.y.toDouble(), touchVec.x.toDouble())).toFloat()
                            if (angle < 0) angle += 360f
                            // Adjust for start angle of -90 (top)
                            val normalizedAngle = (angle + 90f) % 360f

                            var accumulated = 0f
                            selectedUnitIndex = null
                            for ((i, unit) in subject.units.withIndex()) {
                                val sweep = (unit.officialMarks / totalTheoryMarks) * 360f
                                if (normalizedAngle >= accumulated && normalizedAngle < accumulated + sweep) {
                                    selectedUnitIndex = i
                                    break
                                }
                                accumulated += sweep
                            }
                        }
                    }
            ) {
                val strokeWidth = 32.dp.toPx()
                val diameter = size.minDimension - strokeWidth
                val topLeft = Offset(
                    (size.width - diameter) / 2f,
                    (size.height - diameter) / 2f
                )
                val arcSize = Size(diameter, diameter)

                var startAngle = -90f
                for ((index, unit) in subject.units.withIndex()) {
                    val sweep = (unit.officialMarks / totalTheoryMarks) * 360f * animationProgress.value
                    val isSelected = selectedUnitIndex == index
                    val color = ChartColors[index % ChartColors.size]

                    drawArc(
                        color = if (isSelected) color else color.copy(alpha = 0.85f),
                        startAngle = startAngle,
                        sweepAngle = sweep,
                        useCenter = false,
                        topLeft = topLeft,
                        size = arcSize,
                        style = Stroke(
                            width = if (isSelected) strokeWidth * 1.15f else strokeWidth,
                            cap = StrokeCap.Butt
                        )
                    )
                    startAngle += (unit.officialMarks / totalTheoryMarks) * 360f
                }
            }

            // Center Label
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val selectedUnit = selectedUnitIndex?.let { subject.units.getOrNull(it) }
                if (selectedUnit != null) {
                    Text(
                        text = "${selectedUnit.officialMarks}M",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = selectedUnit.unitNumber,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        text = "${totalTheoryMarks.toInt()}M",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Theory Total",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Legend
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            subject.units.forEachIndexed { index, unit ->
                val color = ChartColors[index % ChartColors.size]
                val isSelected = selectedUnitIndex == index

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                modifier = Modifier.size(10.dp),
                                shape = CircleShape,
                                color = color
                            ) {}
                            Text(
                                text = "${unit.unitNumber}: ${unit.name}",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal),
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1
                            )
                        }
                        Text(
                            text = "${unit.officialMarks} Marks",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
