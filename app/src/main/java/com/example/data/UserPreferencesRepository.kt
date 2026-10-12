package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.SavedScenario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class UserPreferencesRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("cbse_marks_hub_prefs", Context.MODE_PRIVATE)

    private val _enrolledSubjects = MutableStateFlow<Set<String>>(loadEnrolledSubjects())
    val enrolledSubjects: StateFlow<Set<String>> = _enrolledSubjects.asStateFlow()

    private val _completedChapters = MutableStateFlow<Set<String>>(loadStringSet("completed_chapters"))
    val completedChapters: StateFlow<Set<String>> = _completedChapters.asStateFlow()

    private val _difficultChapters = MutableStateFlow<Set<String>>(loadStringSet("difficult_chapters"))
    val difficultChapters: StateFlow<Set<String>> = _difficultChapters.asStateFlow()

    private val _bookmarkedTopics = MutableStateFlow<Set<String>>(loadStringSet("bookmarked_topics"))
    val bookmarkedTopics: StateFlow<Set<String>> = _bookmarkedTopics.asStateFlow()

    private val _chapterNotes = MutableStateFlow<Map<String, String>>(loadChapterNotes())
    val chapterNotes: StateFlow<Map<String, String>> = _chapterNotes.asStateFlow()

    private val _savedScenarios = MutableStateFlow<List<SavedScenario>>(loadSavedScenarios())
    val savedScenarios: StateFlow<List<SavedScenario>> = _savedScenarios.asStateFlow()

    private val _targetPercentages = MutableStateFlow<Map<String, Float>>(loadTargetPercentages())
    val targetPercentages: StateFlow<Map<String, Float>> = _targetPercentages.asStateFlow()

    private val _overallTargetPercentage = MutableStateFlow(prefs.getFloat("overall_target_pct", 92f))
    val overallTargetPercentage: StateFlow<Float> = _overallTargetPercentage.asStateFlow()

    private val _selectedAcademicYear = MutableStateFlow("2026–27")
    val selectedAcademicYear: StateFlow<String> = _selectedAcademicYear.asStateFlow()

    private fun loadEnrolledSubjects(): Set<String> {
        val raw = prefs.getStringSet("enrolled_subjects", null)
        return raw ?: CurriculumRepository.getDefaultEnrolledSubjectCodes()
    }

    private fun loadStringSet(key: String): Set<String> {
        return prefs.getStringSet(key, emptySet()) ?: emptySet()
    }

    private fun saveStringSet(key: String, set: Set<String>) {
        prefs.edit().putStringSet(key, set).apply()
    }

    fun toggleEnrollSubject(code: String) {
        val current = _enrolledSubjects.value.toMutableSet()
        if (current.contains(code)) {
            // Keep at least 1 subject
            if (current.size > 1) {
                current.remove(code)
            }
        } else {
            current.add(code)
        }
        _enrolledSubjects.value = current
        saveStringSet("enrolled_subjects", current)
    }

    fun setEnrolledSubjects(codes: Set<String>) {
        _enrolledSubjects.value = codes
        saveStringSet("enrolled_subjects", codes)
    }

    fun toggleChapterCompleted(chapterId: String) {
        val current = _completedChapters.value.toMutableSet()
        if (current.contains(chapterId)) {
            current.remove(chapterId)
        } else {
            current.add(chapterId)
        }
        _completedChapters.value = current
        saveStringSet("completed_chapters", current)
    }

    fun toggleChapterDifficult(chapterId: String) {
        val current = _difficultChapters.value.toMutableSet()
        if (current.contains(chapterId)) {
            current.remove(chapterId)
        } else {
            current.add(chapterId)
        }
        _difficultChapters.value = current
        saveStringSet("difficult_chapters", current)
    }

    fun toggleBookmarkTopic(topicId: String) {
        val current = _bookmarkedTopics.value.toMutableSet()
        if (current.contains(topicId)) {
            current.remove(topicId)
        } else {
            current.add(topicId)
        }
        _bookmarkedTopics.value = current
        saveStringSet("bookmarked_topics", current)
    }

    fun setChapterNote(chapterId: String, note: String) {
        val current = _chapterNotes.value.toMutableMap()
        if (note.trim().isEmpty()) {
            current.remove(chapterId)
        } else {
            current[chapterId] = note.trim()
        }
        _chapterNotes.value = current
        saveChapterNotes(current)
    }

    private fun loadChapterNotes(): Map<String, String> {
        val jsonStr = prefs.getString("chapter_notes_json", null) ?: return emptyMap()
        return try {
            val json = JSONObject(jsonStr)
            val map = mutableMapOf<String, String>()
            json.keys().forEach { key ->
                map[key] = json.getString(key)
            }
            map
        } catch (e: Exception) {
            emptyMap()
        }
    }

    private fun saveChapterNotes(notes: Map<String, String>) {
        val json = JSONObject()
        notes.forEach { (k, v) -> json.put(k, v) }
        prefs.edit().putString("chapter_notes_json", json.toString()).apply()
    }

    fun saveScenario(scenario: SavedScenario) {
        val current = _savedScenarios.value.toMutableList()
        current.removeAll { it.id == scenario.id }
        current.add(0, scenario)
        _savedScenarios.value = current
        saveScenariosToPrefs(current)
    }

    fun deleteScenario(id: String) {
        val current = _savedScenarios.value.filter { it.id != id }
        _savedScenarios.value = current
        saveScenariosToPrefs(current)
    }

    private fun saveScenariosToPrefs(list: List<SavedScenario>) {
        val arr = JSONArray()
        list.forEach { sc ->
            val obj = JSONObject().apply {
                put("id", sc.id)
                put("title", sc.title)
                put("subjectCode", sc.subjectCode)
                put("subjectName", sc.subjectName)
                put("maxTheory", sc.maxTheory.toDouble())
                put("maxInternal", sc.maxInternal.toDouble())
                put("obtainedTheory", sc.obtainedTheory.toDouble())
                put("obtainedInternal", sc.obtainedInternal.toDouble())
                put("totalMarks", sc.totalMarks.toDouble())
                put("percentage", sc.percentage.toDouble())
                put("targetMarks", sc.targetMarks.toDouble())
                put("remainingNeeded", sc.remainingNeeded.toDouble())
                put("timestamp", sc.timestamp)
            }
            arr.put(obj)
        }
        prefs.edit().putString("saved_scenarios_json", arr.toString()).apply()
    }

    private fun loadSavedScenarios(): List<SavedScenario> {
        val jsonStr = prefs.getString("saved_scenarios_json", null) ?: return emptyList()
        return try {
            val arr = JSONArray(jsonStr)
            val list = mutableListOf<SavedScenario>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(
                    SavedScenario(
                        id = obj.getString("id"),
                        title = obj.getString("title"),
                        subjectCode = obj.getString("subjectCode"),
                        subjectName = obj.getString("subjectName"),
                        maxTheory = obj.getDouble("maxTheory").toFloat(),
                        maxInternal = obj.getDouble("maxInternal").toFloat(),
                        obtainedTheory = obj.getDouble("obtainedTheory").toFloat(),
                        obtainedInternal = obj.getDouble("obtainedInternal").toFloat(),
                        totalMarks = obj.getDouble("totalMarks").toFloat(),
                        percentage = obj.getDouble("percentage").toFloat(),
                        targetMarks = obj.getDouble("targetMarks").toFloat(),
                        remainingNeeded = obj.getDouble("remainingNeeded").toFloat(),
                        timestamp = obj.optLong("timestamp", System.currentTimeMillis())
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun setSubjectTarget(code: String, targetPct: Float) {
        val current = _targetPercentages.value.toMutableMap()
        current[code] = targetPct
        _targetPercentages.value = current
        val json = JSONObject()
        current.forEach { (k, v) -> json.put(k, v.toDouble()) }
        prefs.edit().putString("subject_targets_json", json.toString()).apply()
    }

    private fun loadTargetPercentages(): Map<String, Float> {
        val jsonStr = prefs.getString("subject_targets_json", null) ?: return mapOf(
            "041" to 95f, "241" to 90f, "086" to 95f, "087" to 95f, "184" to 92f, "002" to 92f, "402" to 98f
        )
        return try {
            val json = JSONObject(jsonStr)
            val map = mutableMapOf<String, Float>()
            json.keys().forEach { key ->
                map[key] = json.getDouble(key).toFloat()
            }
            map
        } catch (e: Exception) {
            emptyMap()
        }
    }

    fun setOverallTargetPercentage(pct: Float) {
        _overallTargetPercentage.value = pct
        prefs.edit().putFloat("overall_target_pct", pct).apply()
    }
}
