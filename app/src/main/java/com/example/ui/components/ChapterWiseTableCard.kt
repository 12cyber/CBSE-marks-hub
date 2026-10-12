package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Chapter
import com.example.data.model.MarksStatus
import com.example.data.model.Subject
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CrimsonRed
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.EmeraldGreen

@Composable
fun ChapterWiseTableCard(
    subject: Subject,
    completedChapterIds: Set<String>,
    difficultChapterIds: Set<String>,
    chapterNotes: Map<String, String>,
    onToggleCompleted: (String) -> Unit,
    onToggleDifficult: (String) -> Unit,
    onEditNote: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedUnitFilter by remember { mutableStateOf<String?>(null) }
    var activeChapterForNote by remember { mutableStateOf<Chapter?>(null) }
    var noteText by remember { mutableStateOf("") }

    val allChapters = remember(subject) {
        subject.units.flatMap { it.chapters }
    }

    val filteredChapters = remember(allChapters, selectedUnitFilter) {
        if (selectedUnitFilter == null) allChapters
        else allChapters.filter { it.unitId == selectedUnitFilter }
    }

    // Theory total marks check
    val theoryTotal = subject.assessmentStructure.maxTheory
    val internalTotal = subject.assessmentStructure.maxInternal

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with Subject Info & Session
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CHAPTER-WISE MARKS WEIGHTAGE",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${subject.name} (${subject.code})",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "Session 2026–27",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Assessment Structure Breakdown Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column {
                            Text(
                                text = "Board Theory",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "$theoryTotal Marks",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Column {
                            Text(
                                text = "Internal Assessment",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "$internalTotal Marks",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Column {
                            Text(
                                text = "Total Marks",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "100 Marks",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = EmeraldGreen
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Text(
                            text = "${allChapters.size} Chapters",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Official Disclaimer Notice
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = AmberGold.copy(alpha = 0.1f),
                border = androidx.compose.foundation.BorderStroke(1.dp, AmberGold.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Notice",
                        tint = AmberGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "CBSE officially fixes marks at the Unit level. Where exact chapter marks are not isolated by CBSE, verified historical exam trends (2019–2024 Board Papers & SQPs) are displayed. Labels clearly distinguish [OFFICIAL] from [HISTORICAL TREND].",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Unit Filter Chips Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedUnitFilter == null,
                    onClick = { selectedUnitFilter = null },
                    label = { Text("All Units (${allChapters.size})") }
                )
                subject.units.forEach { unit ->
                    FilterChip(
                        selected = selectedUnitFilter == unit.id,
                        onClick = { selectedUnitFilter = if (selectedUnitFilter == unit.id) null else unit.id },
                        label = { Text("${unit.unitNumber} (${unit.officialMarks}M)") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Chapter Table Items
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                filteredChapters.forEach { chapter ->
                    val isDone = completedChapterIds.contains(chapter.id)
                    val isDiff = difficultChapterIds.contains(chapter.id)
                    val hasNote = chapterNotes.containsKey(chapter.id)

                    ChapterRowCard(
                        chapter = chapter,
                        isCompleted = isDone,
                        isDifficult = isDiff,
                        hasNote = hasNote,
                        onToggleCompleted = { onToggleCompleted(chapter.id) },
                        onToggleDifficult = { onToggleDifficult(chapter.id) },
                        onOpenNote = {
                            activeChapterForNote = chapter
                            noteText = chapterNotes[chapter.id] ?: ""
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Official Verification Citation Footer
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Official Source: ${subject.sourceTitle}",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Verified for CBSE Academic Session 2026–27 • Official portal: ${subject.sourceUrl}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    // Note Editing Dialog
    activeChapterForNote?.let { chapter ->
        AlertDialog(
            onDismissRequest = { activeChapterForNote = null },
            title = {
                Text(
                    text = "Personal Study Notes: ${chapter.name}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column {
                    Text(
                        text = "Important Concepts: ${chapter.importantConcepts.take(2).joinToString(", ")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        label = { Text("Write your revision formulas or notes...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEditNote(chapter.id, noteText)
                        activeChapterForNote = null
                    }
                ) {
                    Text("Save Note")
                }
            },
            dismissButton = {
                TextButton(onClick = { activeChapterForNote = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun ChapterRowCard(
    chapter: Chapter,
    isCompleted: Boolean,
    isDifficult: Boolean,
    hasNote: Boolean,
    onToggleCompleted: () -> Unit,
    onToggleDifficult: () -> Unit,
    onOpenNote: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isDifficult) CrimsonRed.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Row 1: Chapter Name & Marks Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "Ch ${chapter.number}",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = chapter.unitName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = chapter.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Marks Display Pill
                Column(horizontalAlignment = Alignment.End) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(chapter.marksStatus.badgeColorHex).copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(chapter.marksStatus.badgeColorHex))
                    ) {
                        Text(
                            text = chapter.estimatedMarksDisplay,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(chapter.marksStatus.badgeColorHex),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = chapter.marksStatus.tag,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
                        color = Color(chapter.marksStatus.badgeColorHex)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 2: PYQ Frequency & Question Formats
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Timeline,
                        contentDescription = "PYQ Trend",
                        tint = ElectricBlue,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = chapter.pyqFrequency,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 3: Important Concepts & Exam Tips
            if (chapter.examTips.isNotBlank()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Exam Tip",
                        tint = AmberGold,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = chapter.examTips,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Row 4: Student Action Buttons (Completed, Difficult, Notes)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Completed Checkbox Button
                    FilledTonalButton(
                        onClick = onToggleCompleted,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (isCompleted) EmeraldGreen.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surface,
                            contentColor = if (isCompleted) EmeraldGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = "Toggle Complete",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isCompleted) "Completed" else "Mark Done",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    // Flag Difficult Button
                    FilledTonalButton(
                        onClick = onToggleDifficult,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (isDifficult) CrimsonRed.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surface,
                            contentColor = if (isDifficult) CrimsonRed else MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(
                            imageVector = if (isDifficult) Icons.Default.Flag else Icons.Default.OutlinedFlag,
                            contentDescription = "Toggle Difficult",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isDifficult) "Difficult" else "Flag Weak",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                // Add Note Button
                IconButton(
                    onClick = onOpenNote,
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(
                        imageVector = if (hasNote) Icons.AutoMirrored.Filled.StickyNote2 else Icons.Default.EditNote,
                        contentDescription = "Study Notes",
                        tint = if (hasNote) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
