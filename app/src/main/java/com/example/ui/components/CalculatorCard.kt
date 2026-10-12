package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
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
import com.example.data.model.SavedScenario
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CrimsonRed
import com.example.ui.theme.EmeraldGreen
import java.util.UUID

@Composable
fun CalculatorCard(
    onSaveScenario: (SavedScenario) -> Unit,
    modifier: Modifier = Modifier
) {
    val allSubjects = remember { CurriculumRepository.getAllSubjects() }
    var selectedSubjectCode by remember { mutableStateOf("041") }
    val currentSubject = remember(selectedSubjectCode) {
        CurriculumRepository.getSubjectByCode(selectedSubjectCode) ?: allSubjects.first()
    }

    var is80_20 by remember { mutableStateOf(currentSubject.assessmentStructure == AssessmentStructure.STANDARD_80_20) }

    LaunchedEffect(currentSubject) {
        is80_20 = currentSubject.assessmentStructure == AssessmentStructure.STANDARD_80_20
    }

    val maxTheory = if (is80_20) 80f else 50f
    val maxInternal = if (is80_20) 20f else 50f

    var theoryInput by remember { mutableStateOf("65") }
    var internalInput by remember { mutableStateOf("19") }
    var targetInput by remember { mutableStateOf("92") }
    var scenarioTitle by remember { mutableStateOf("Pre-Board Estimate") }
    var showSavedMessage by remember { mutableStateOf(false) }

    val theoryObtained = theoryInput.toFloatOrNull() ?: 0f
    val internalObtained = internalInput.toFloatOrNull() ?: 0f
    val targetPct = targetInput.toFloatOrNull() ?: 90f

    // Validations
    val theoryValid = theoryObtained in 0f..maxTheory
    val internalValid = internalObtained in 0f..maxInternal

    val totalObtained = if (theoryValid && internalValid) theoryObtained + internalObtained else 0f
    val percentage = if (theoryValid && internalValid) (totalObtained / 100f) * 100f else 0f
    val remainingNeeded = if (targetPct > percentage) (targetPct - percentage) else 0f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("marks_calculator_card"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "CBSE MARKS CALCULATOR",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Preset Toggle Buttons
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    FilterChip(
                        selected = is80_20,
                        onClick = { is80_20 = true },
                        label = { Text("80+20", style = MaterialTheme.typography.labelSmall) }
                    )
                    FilterChip(
                        selected = !is80_20,
                        onClick = { is80_20 = false },
                        label = { Text("50+50", style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Inputs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = theoryInput,
                    onValueChange = { theoryInput = it },
                    label = { Text("Theory (/ ${maxTheory.toInt()})") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = !theoryValid,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("calc_theory_input")
                )

                OutlinedTextField(
                    value = internalInput,
                    onValueChange = { internalInput = it },
                    label = { Text("Internal (/ ${maxInternal.toInt()})") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = !internalValid,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("calc_internal_input")
                )
            }

            if (!theoryValid || !internalValid) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Please enter valid marks: Theory (0–${maxTheory.toInt()}), Internal (0–${maxInternal.toInt()})",
                    style = MaterialTheme.typography.bodySmall,
                    color = CrimsonRed
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Real-Time Result Display
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "TOTAL OBTAINED",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${totalObtained.toInt()} / 100",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "PERCENTAGE",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = String.format("%.1f%%", percentage),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = if (percentage >= 90f) EmeraldGreen else AmberGold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Step-by-step arithmetic steps
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Calculation Steps:",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "1. Theory Component: ${theoryObtained.toInt()} / ${maxTheory.toInt()} marks",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "2. Internal Assessment: ${internalObtained.toInt()} / ${maxInternal.toInt()} marks",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "3. Aggregation: ${theoryObtained.toInt()} + ${internalObtained.toInt()} = ${totalObtained.toInt()} marks out of 100 (${String.format("%.1f", percentage)}%)",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Target Deficit row & Save
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = scenarioTitle,
                    onValueChange = { scenarioTitle = it },
                    label = { Text("Scenario Name") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = {
                        val sc = SavedScenario(
                            id = UUID.randomUUID().toString(),
                            title = scenarioTitle.ifBlank { "Scenario ${System.currentTimeMillis() % 1000}" },
                            subjectCode = currentSubject.code,
                            subjectName = currentSubject.name,
                            maxTheory = maxTheory,
                            maxInternal = maxInternal,
                            obtainedTheory = theoryObtained,
                            obtainedInternal = internalObtained,
                            totalMarks = totalObtained,
                            percentage = percentage,
                            targetMarks = targetPct,
                            remainingNeeded = remainingNeeded
                        )
                        onSaveScenario(sc)
                        showSavedMessage = true
                    },
                    modifier = Modifier.testTag("save_calc_scenario_button")
                ) {
                    Icon(imageVector = Icons.Default.Bookmark, contentDescription = "Save", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Save")
                }
            }

            if (showSavedMessage) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                    Text(
                        text = "Scenario saved to device!",
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldGreen
                    )
                }
            }
        }
    }
}
