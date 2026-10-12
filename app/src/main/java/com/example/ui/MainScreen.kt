package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.UserPreferencesRepository
import com.example.ui.components.CbseHeader
import com.example.ui.screens.*

enum class AppDestination(val label: String, val icon: ImageVector) {
    DASHBOARD("Home", Icons.Default.Dashboard),
    WEIGHTAGE("Weightage", Icons.Default.TableChart),
    AI_STUDY("AI Study", Icons.Default.AutoAwesome),
    CALCULATOR("Calculator", Icons.Default.Calculate),
    EXPLORER("Explorer", Icons.Default.Explore)
}

sealed class ScreenState {
    data object MainTabs : ScreenState()
    data class SubjectDetail(val subjectCode: String) : ScreenState()
    data class ChapterWeightageDetail(val subjectCode: String) : ScreenState()
    data object Resources : ScreenState()
    data object ProgressTracker : ScreenState()
}

@Composable
fun MainScreen(
    userPrefs: UserPreferencesRepository,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(AppDestination.DASHBOARD) }
    var screenState by remember { mutableStateOf<ScreenState>(ScreenState.MainTabs) }
    var weightageSubjectCode by remember { mutableStateOf("041") }

    val enrolledSubjects by userPrefs.enrolledSubjects.collectAsState()
    val completedChapters by userPrefs.completedChapters.collectAsState()
    val difficultChapters by userPrefs.difficultChapters.collectAsState()
    val bookmarkedTopics by userPrefs.bookmarkedTopics.collectAsState()
    val chapterNotes by userPrefs.chapterNotes.collectAsState()
    val savedScenarios by userPrefs.savedScenarios.collectAsState()
    val targetPercentages by userPrefs.targetPercentages.collectAsState()
    val overallTargetPct by userPrefs.overallTargetPercentage.collectAsState()

    // BackHandler for secondary screens
    if (screenState !is ScreenState.MainTabs) {
        BackHandler {
            screenState = ScreenState.MainTabs
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CbseHeader(
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("bottom_nav_bar"),
                windowInsets = WindowInsets.navigationBars
            ) {
                AppDestination.entries.forEach { destination ->
                    val isSelected = selectedTab == destination && screenState is ScreenState.MainTabs
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            selectedTab = destination
                            screenState = ScreenState.MainTabs
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) },
                        modifier = Modifier.testTag("nav_item_${destination.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = screenState) {
                is ScreenState.SubjectDetail -> {
                    SubjectDetailScreen(
                        subjectCode = state.subjectCode,
                        completedChapters = completedChapters,
                        difficultChapters = difficultChapters,
                        onToggleCompleted = { userPrefs.toggleChapterCompleted(it) },
                        onToggleDifficult = { userPrefs.toggleChapterDifficult(it) },
                        onNavigateToWeightageTable = {
                            weightageSubjectCode = it
                            screenState = ScreenState.ChapterWeightageDetail(it)
                        },
                        onNavigateBack = { screenState = ScreenState.MainTabs }
                    )
                }

                is ScreenState.ChapterWeightageDetail -> {
                    ChapterWeightageScreen(
                        initialSubjectCode = state.subjectCode,
                        completedChapters = completedChapters,
                        difficultChapters = difficultChapters,
                        chapterNotes = chapterNotes,
                        onToggleCompleted = { userPrefs.toggleChapterCompleted(it) },
                        onToggleDifficult = { userPrefs.toggleChapterDifficult(it) },
                        onEditNote = { id, note -> userPrefs.setChapterNote(id, note) },
                        onNavigateBack = { screenState = ScreenState.MainTabs }
                    )
                }

                is ScreenState.Resources -> {
                    ResourcesScreen(onNavigateBack = { screenState = ScreenState.MainTabs })
                }

                is ScreenState.ProgressTracker -> {
                    ProgressTrackerScreen(
                        enrolledSubjects = enrolledSubjects,
                        completedChapters = completedChapters,
                        difficultChapters = difficultChapters,
                        chapterNotes = chapterNotes,
                        overallTargetPct = overallTargetPct,
                        onSetOverallTarget = { userPrefs.setOverallTargetPercentage(it) },
                        onToggleCompleted = { userPrefs.toggleChapterCompleted(it) },
                        onToggleDifficult = { userPrefs.toggleChapterDifficult(it) },
                        onNavigateBack = { screenState = ScreenState.MainTabs }
                    )
                }

                is ScreenState.MainTabs -> {
                    when (selectedTab) {
                        AppDestination.DASHBOARD -> {
                            DashboardScreen(
                                enrolledSubjects = enrolledSubjects,
                                completedChapters = completedChapters,
                                difficultChapters = difficultChapters,
                                overallTargetPct = overallTargetPct,
                                onNavigateToSubjectDetail = {
                                    screenState = ScreenState.SubjectDetail(it)
                                },
                                onNavigateToWeightageTable = {
                                    weightageSubjectCode = it
                                    selectedTab = AppDestination.WEIGHTAGE
                                },
                                onNavigateToAiRecommendations = {
                                    selectedTab = AppDestination.AI_STUDY
                                },
                                onNavigateToCalculator = {
                                    selectedTab = AppDestination.CALCULATOR
                                },
                                onToggleEnrollSubject = { userPrefs.toggleEnrollSubject(it) },
                                onSearchClick = {
                                    selectedTab = AppDestination.EXPLORER
                                }
                            )
                        }

                        AppDestination.WEIGHTAGE -> {
                            ChapterWeightageScreen(
                                initialSubjectCode = weightageSubjectCode,
                                completedChapters = completedChapters,
                                difficultChapters = difficultChapters,
                                chapterNotes = chapterNotes,
                                onToggleCompleted = { userPrefs.toggleChapterCompleted(it) },
                                onToggleDifficult = { userPrefs.toggleChapterDifficult(it) },
                                onEditNote = { id, note -> userPrefs.setChapterNote(id, note) },
                                onNavigateBack = { selectedTab = AppDestination.DASHBOARD }
                            )
                        }

                        AppDestination.AI_STUDY -> {
                            AiRecommendationsScreen(
                                enrolledSubjects = enrolledSubjects,
                                completedChapters = completedChapters,
                                difficultChapters = difficultChapters,
                                targetPercentages = targetPercentages,
                                overallTargetPct = overallTargetPct,
                                onNavigateBack = { selectedTab = AppDestination.DASHBOARD }
                            )
                        }

                        AppDestination.CALCULATOR -> {
                            MarksCalculatorScreen(
                                savedScenarios = savedScenarios,
                                difficultChapters = difficultChapters,
                                onSaveScenario = { userPrefs.saveScenario(it) },
                                onDeleteScenario = { userPrefs.deleteScenario(it) },
                                onNavigateBack = { selectedTab = AppDestination.DASHBOARD }
                            )
                        }

                        AppDestination.EXPLORER -> {
                            CurriculumExplorerScreen(
                                completedChapters = completedChapters,
                                difficultChapters = difficultChapters,
                                bookmarkedTopics = bookmarkedTopics,
                                onToggleCompleted = { userPrefs.toggleChapterCompleted(it) },
                                onToggleDifficult = { userPrefs.toggleChapterDifficult(it) },
                                onToggleBookmark = { userPrefs.toggleBookmarkTopic(it) },
                                onNavigateToSubjectDetail = {
                                    screenState = ScreenState.SubjectDetail(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
