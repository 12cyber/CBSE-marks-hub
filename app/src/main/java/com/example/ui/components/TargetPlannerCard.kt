package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumRepository
import com.example.data.model.AssessmentStructure
import com.example.data.model.Subject
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CrimsonRed
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.EmeraldGreen

@Composable
fun TargetPlannerCard(
    difficultChapterIds: Set<String>,
    modifier: Modifier = Modifier
) {
    val allSubjects = remember { CurriculumRepository.getAllSubjects() }
    var selectedSubjectCode by remember { mutableStateOf("041") }
    val currentSubject = remember(selectedSubjectCode) {
        CurriculumRepository.getSubjectByCode(selectedSubjectCode) ?: allSubjects.first()
    }

    val maxTheory = currentSubject.assessmentStructure.maxTheory.toFloat()
    val maxInternal = currentSubject.assessmentStructure.maxInternal.toFloat()

    var targetMarksInput by remember { mutableStateOf("95") }
    var currentInternalInput by remember { mutableStateOf("19") }

    val targetTotal = targetMarksInput.toFloatOrNull() ?: 90f
    val currentInternal = currentInternalInput.toFloatOrNull() ?: 18f

    // Calculate marks needed in remaining theory exam
    val marksNeededInTheory = targetTotal - currentInternal
    val percentageNeededInTheory = if (maxTheory > 0) (marksNeededInTheory / maxTheory) * 100f else 0f

    val isAchievable = marksNeededInTheory in 0f..maxTheory

    val difficultChaptersInSubject = remember(currentSubject, difficultChapterIds) {
        currentSubject.units.flatMap { it.chapters }.filter { difficultChapterIds.contains(it.id) }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("target_planner_card"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.TrackChanges,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(22.dp)
                )
                Text(
                    text = "TARGET MARKS PLANNER",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Inputs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = targetMarksInput,
                    onValueChange = { targetMarksInput = it },
                    label = { Text("Desired Final Marks (/ 100)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = currentInternalInput,
                    onValueChange = { currentInternalInput = it },
                    label = { Text("Internal Marks (${maxInternal.toInt()} Max)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Requirement Output Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "EXAMINATION TARGET REQUIREMENT:",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    if (isAchievable) {
                        Text(
                            text = "You need ${marksNeededInTheory.toInt()} / ${maxTheory.toInt()} marks in the Board Theory Examination (${String.format("%.1f%%", percentageNeededInTheory)}) to achieve your ${targetTotal.toInt()}% target.",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    } else if (marksNeededInTheory > maxTheory) {
                        Text(
                            text = "Target unreachable: Need ${marksNeededInTheory.toInt()} marks, but maximum theory paper is only ${maxTheory.toInt()} marks. Please adjust your internal or target marks.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = CrimsonRed
                        )
                    } else {
                        Text(
                            text = "Target already achieved! Your current internal marks (${currentInternal.toInt()}) already meet or exceed your goal.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = EmeraldGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Strategic Study Guidance
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = AmberGold.copy(alpha = 0.1f)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = AmberGold, modifier = Modifier.size(16.dp))
                        Text(
                            text = "Strategic Recommendation:",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = AmberGold
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    if (difficultChaptersInSubject.isNotEmpty()) {
                        Text(
                            text = "You have flagged ${difficultChaptersInSubject.size} chapter(s) as difficult in this subject (${difficultChaptersInSubject.joinToString(", ") { it.name }}). To secure ${marksNeededInTheory.toInt()} marks in Theory, prioritize clearing these bottlenecks before attempting full length mock papers.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    } else {
                        Text(
                            text = "High-weightage units account for over 50% of the theory paper. Focus on Section D (5-mark long answers) and Section E (4-mark case studies) to protect your margins.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Disclaimer: Calculations provide strategic planning guidance and do not guarantee examination results.",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
