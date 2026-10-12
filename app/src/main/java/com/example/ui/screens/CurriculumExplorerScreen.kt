package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumRepository
import com.example.data.SearchResultItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.EmeraldGreen

@Composable
fun CurriculumExplorerScreen(
    completedChapters: Set<String>,
    difficultChapters: Set<String>,
    bookmarkedTopics: Set<String>,
    onToggleCompleted: (String) -> Unit,
    onToggleDifficult: (String) -> Unit,
    onToggleBookmark: (String) -> Unit,
    onNavigateToSubjectDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedSubjectCodeFilter by remember { mutableStateOf<String?>(null) }
    var onlyDifficultFilter by remember { mutableStateOf(false) }

    val allSubjects = remember { CurriculumRepository.getAllSubjects() }

    val searchResults = remember(searchQuery, selectedSubjectCodeFilter) {
        if (searchQuery.isNotBlank()) {
            CurriculumRepository.searchCurriculum(
                query = searchQuery,
                selectedSubjectCodes = selectedSubjectCodeFilter?.let { setOf(it) }
            )
        } else {
            emptyList()
        }
    }

    val allChaptersList = remember(selectedSubjectCodeFilter, onlyDifficultFilter, difficultChapters) {
        val list = CurriculumRepository.getAllChaptersFlat(selectedSubjectCodeFilter)
        if (onlyDifficultFilter) {
            list.filter { difficultChapters.contains(it.third.id) }
        } else {
            list
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("curriculum_explorer_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Title
        item {
            Column {
                Text(
                    text = "Curriculum Explorer",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Searchable syllabus across all Class 10 CBSE subjects (Session 2026–27)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Search Input Field
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search any topic, chapter, formula or unit...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("curriculum_search_input"),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }

        // Filter Chips Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedSubjectCodeFilter == null,
                    onClick = { selectedSubjectCodeFilter = null },
                    label = { Text("All Subjects") }
                )

                FilterChip(
                    selected = onlyDifficultFilter,
                    onClick = { onlyDifficultFilter = !onlyDifficultFilter },
                    label = { Text("Flagged Difficult Only") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Flag, contentDescription = null, modifier = Modifier.size(14.dp))
                    }
                )

                allSubjects.forEach { sub ->
                    FilterChip(
                        selected = selectedSubjectCodeFilter == sub.code,
                        onClick = {
                            selectedSubjectCodeFilter = if (selectedSubjectCodeFilter == sub.code) null else sub.code
                        },
                        label = { Text("${sub.name} (${sub.code})") }
                    )
                }
            }
        }

        // Search Results Section (if searching)
        if (searchQuery.isNotBlank()) {
            item {
                Text(
                    text = "Search Results (${searchResults.size})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (searchResults.isEmpty()) {
                item {
                    Text(
                        text = "No topics matched '$searchQuery'. Try checking spelling or browsing subjects below.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                items(searchResults) { result ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToSubjectDetail(result.subjectCode) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = result.title,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = result.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        } else {
            // Browsing All Chapters
            item {
                Text(
                    text = "Browsing Chapters (${allChaptersList.size})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(allChaptersList) { (subject, unit, chapter) ->
                val isDone = completedChapters.contains(chapter.id)
                val isDiff = difficultChapters.contains(chapter.id)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${subject.name} • ${unit.unitNumber}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Ch ${chapter.number}: ${chapter.name}",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(chapter.marksStatus.badgeColorHex).copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = chapter.estimatedMarksDisplay,
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color(chapter.marksStatus.badgeColorHex),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Core Topics: ${chapter.topics.take(2).joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilterChip(
                                selected = isDone,
                                onClick = { onToggleCompleted(chapter.id) },
                                label = { Text(if (isDone) "Completed" else "Mark Done") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            )

                            FilterChip(
                                selected = isDiff,
                                onClick = { onToggleDifficult(chapter.id) },
                                label = { Text(if (isDiff) "Difficult" else "Flag Weak") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Flag,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
