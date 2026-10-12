package com.example.ai

import com.example.BuildConfig
import com.example.data.CurriculumRepository
import com.example.data.model.Chapter
import com.example.data.model.RevisionPriority
import com.example.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class PriorityRevisionItem(
    val chapterId: String,
    val chapterName: String,
    val subjectName: String,
    val subjectCode: String,
    val unitName: String,
    val marksDisplay: String,
    val isDifficult: Boolean,
    val isCompleted: Boolean,
    val priorityScore: Int,
    val whyPrioritized: String,
    val targetedFocusArea: String,
    val recommendedResource: String
)

data class SubjectStudyAdvice(
    val subjectCode: String,
    val subjectName: String,
    val totalChapters: Int,
    val completedCount: Int,
    val difficultCount: Int,
    val completionPercentage: Int,
    val targetMarksPercentage: Float,
    val strategicAdvice: String
)

data class StudyRecommendationReport(
    val headline: String,
    val overallStrategicRoadmap: String,
    val priorityChapters: List<PriorityRevisionItem>,
    val subjectAdvices: List<SubjectStudyAdvice>,
    val highYieldAlerts: List<String>,
    val fourWeekRevisionPlan: List<String>,
    val isAiEnhanced: Boolean,
    val modelNotice: String
)

object StudyRecommendationEngine {

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    suspend fun generateRecommendations(
        enrolledCodes: Set<String>,
        completedChapterIds: Set<String>,
        difficultChapterIds: Set<String>,
        targetPercentages: Map<String, Float>,
        overallTargetPercentage: Float
    ): StudyRecommendationReport = withContext(Dispatchers.Default) {
        val allChaptersWithMeta = CurriculumRepository.getAllChaptersFlat()
            .filter { enrolledCodes.contains(it.first.code) }

        // 1. Calculate Priority Scores for each chapter
        val scoredChapters = allChaptersWithMeta.map { (subject, unit, chapter) ->
            val isCompleted = completedChapterIds.contains(chapter.id)
            val isDifficult = difficultChapterIds.contains(chapter.id)
            val targetPct = targetPercentages[subject.code] ?: overallTargetPercentage

            // Priority Score Logic:
            // Base score from unit marks:
            var score = unit.officialMarks
            // If flagged difficult: +15 points
            if (isDifficult) score += 15
            // If incomplete: +10 points
            if (!isCompleted) score += 10
            // If subject has high target (>= 90%): +5 points
            if (targetPct >= 90f) score += 5
            // If inherent chapter priority is HIGH: +5 points
            if (chapter.priority == RevisionPriority.HIGH) score += 5

            val whyReason = buildWhyExplanation(isDifficult, isCompleted, unit.officialMarks, chapter, targetPct)
            val focusArea = chapter.importantConcepts.take(2).joinToString("; ")
            val resourceTip = "CBSE 2026–27 SQP Section C/D + NCERT Exemplar for ${chapter.name}"

            PriorityRevisionItem(
                chapterId = chapter.id,
                chapterName = chapter.name,
                subjectName = subject.name,
                subjectCode = subject.code,
                unitName = unit.name,
                marksDisplay = chapter.estimatedMarksDisplay,
                isDifficult = isDifficult,
                isCompleted = isCompleted,
                priorityScore = score,
                whyPrioritized = whyReason,
                targetedFocusArea = focusArea,
                recommendedResource = resourceTip
            )
        }.sortedByDescending { it.priorityScore }

        // 2. Compute Subject Advices
        val subjectAdvices = CurriculumRepository.getAllSubjects()
            .filter { enrolledCodes.contains(it.code) }
            .map { subject ->
                val chapters = subject.units.flatMap { it.chapters }
                val totalCh = chapters.size
                val completed = chapters.count { completedChapterIds.contains(it.id) }
                val difficult = chapters.count { difficultChapterIds.contains(it.id) }
                val pct = if (totalCh > 0) (completed * 100) / totalCh else 0
                val target = targetPercentages[subject.code] ?: overallTargetPercentage

                val advice = when {
                    difficult >= 3 -> "High friction detected in ${difficult} chapters. Focus on NCERT solved examples and step-marking templates before full-length sample papers."
                    pct < 40 -> "Syllabus coverage is behind schedule ($pct%). Prioritize high-weightage units first to secure a solid 70%+ baseline."
                    target >= 95f -> "Aiming for 95%+ requires mastering Section E Case-Based questions (4M) and eliminating silly calculation errors in 1M MCQs."
                    else -> "Steady progress. Regular weekly chapter-end revisions will comfortably maintain your ${target.toInt()}% target trajectory."
                }

                SubjectStudyAdvice(
                    subjectCode = subject.code,
                    subjectName = subject.name,
                    totalChapters = totalCh,
                    completedCount = completed,
                    difficultCount = difficult,
                    completionPercentage = pct,
                    targetMarksPercentage = target,
                    strategicAdvice = advice
                )
            }

        // 3. High Yield Critical Alerts
        val highYieldAlerts = mutableListOf<String>()
        val difficultIncomplete = scoredChapters.filter { it.isDifficult && !it.isCompleted }
        if (difficultIncomplete.isNotEmpty()) {
            highYieldAlerts.add(
                "Immediate Attention: You have marked ${difficultIncomplete.size} chapters as difficult that are still incomplete, including '${difficultIncomplete.first().chapterName}'."
            )
        }
        val highWeightIncomplete = scoredChapters.filter { !it.isCompleted && it.priorityScore >= 30 }
        if (highWeightIncomplete.isNotEmpty()) {
            highYieldAlerts.add(
                "Core Marks Risk: Major units like '${highWeightIncomplete.first().unitName}' carry significant board marks and should be studied before minor electives."
            )
        }
        if (highYieldAlerts.isEmpty()) {
            highYieldAlerts.add("Great momentum! Your syllabus completion is well-distributed. Continue with timed mock test simulations.")
        }

        // 4. 4-Week Milestone Revision Plan
        val fourWeekPlan = listOf(
            "Week 1 (Diagnostic & Bottlenecks): Tackle student-flagged difficult chapters: ${scoredChapters.filter { it.isDifficult }.take(2).joinToString(", ") { it.chapterName }.ifEmpty { "High-weightage core chapters" }}.",
            "Week 2 (High-Weightage Mastery): Master guaranteed 5-mark proofs (Triangles BPT, Chemical reactions, Nationalism in India, Electricity numericals).",
            "Week 3 (Competency & Case Studies): Practice CBSE 2026–27 50% Competency Questions (Case-based 4-mark scenarios, Assertion-Reason, Data interpretation).",
            "Week 4 (Timed Board Simulation): Attempt 2 full-length 80-mark 3-hour sample papers under strict board exam conditions with step-wise self-evaluation."
        )

        // 5. Try calling Gemini API if configured
        val geminiReport = tryCallGemini(
            scoredChapters.take(5),
            overallTargetPercentage,
            difficultChapterIds.size,
            completedChapterIds.size
        )

        if (geminiReport != null) {
            StudyRecommendationReport(
                headline = "AI-Generated Personalized Strategy for Target ${overallTargetPercentage.toInt()}%",
                overallStrategicRoadmap = geminiReport,
                priorityChapters = scoredChapters.take(8),
                subjectAdvices = subjectAdvices,
                highYieldAlerts = highYieldAlerts,
                fourWeekRevisionPlan = fourWeekPlan,
                isAiEnhanced = true,
                modelNotice = "Generated using Gemini 3.5 Flash & CBSE 2026–27 Examination Weightage"
            )
        } else {
            StudyRecommendationReport(
                headline = "Strategic Revision Roadmap for Target ${overallTargetPercentage.toInt()}%",
                overallStrategicRoadmap = "To achieve your ${overallTargetPercentage.toInt()}% CBSE Class 10 goal for session 2026–27, your study schedule prioritizes high-weightage units (Algebra 20M, Science Chemistry 25M, Social Science 20M each) and addresses your self-identified difficulty bottlenecks first.",
                priorityChapters = scoredChapters.take(8),
                subjectAdvices = subjectAdvices,
                highYieldAlerts = highYieldAlerts,
                fourWeekRevisionPlan = fourWeekPlan,
                isAiEnhanced = false,
                modelNotice = "Calculated by CBSE Marks Hub Pedagogical Recommendation Engine"
            )
        }
    }

    private fun buildWhyExplanation(
        isDifficult: Boolean,
        isCompleted: Boolean,
        unitMarks: Int,
        chapter: Chapter,
        targetPct: Float
    ): String {
        return when {
            isDifficult && !isCompleted -> "Flagged as Difficult & currently Incomplete in a $unitMarks-mark official unit. High risk of losing marks unless revised immediately."
            isDifficult && isCompleted -> "Flagged as Difficult despite completion. Needs an active recall pass and formula review."
            !isCompleted && unitMarks >= 15 -> "Part of a high-stake official unit ($unitMarks marks). Incomplete status directly jeopardizes your ${targetPct.toInt()}% target."
            chapter.priority == RevisionPriority.HIGH -> "High exam frequency with recurring 4M/5M board questions: ${chapter.pyqFrequency}."
            else -> "Regular syllabus component contributing to overall balanced preparation."
        }
    }

    private suspend fun tryCallGemini(
        topChapters: List<PriorityRevisionItem>,
        targetPct: Float,
        difficultCount: Int,
        completedCount: Int
    ): String? = withContext(Dispatchers.IO) {
        val apiKey = try { BuildConfig.GEMINI_API_KEY } catch (e: Exception) { "" }
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext null
        }

        try {
            val prompt = """
                You are an expert CBSE Class 10 academic mentor for the 2026–2027 board examinations.
                Student target: ${targetPct}% overall marks.
                Student progress: $completedCount completed chapters, $difficultCount flagged difficult chapters.
                Top 5 prioritized chapters needing revision:
                ${topChapters.joinToString("\n") { "- ${it.subjectName} -> ${it.chapterName} (${it.marksDisplay}), Difficult: ${it.isDifficult}, Completed: ${it.isCompleted}" }}
                
                Provide a concise, motivating 3-paragraph strategic study advice:
                1. How to overcome the difficult bottleneck chapters without feeling overwhelmed.
                2. How to leverage high-weightage units for the 2026-27 50% competency question format.
                3. Concrete step-marking and time management tips for board exam day.
            """.trimIndent()

            val requestJson = JSONObject().apply {
                val contentsArr = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val partsArr = JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        }
                        put("parts", partsArr)
                    }
                    put(contentObj)
                }
                put("contents", contentsArr)
            }

            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestJson.toString().toRequestBody("application/json".toMediaType()))
                .build()

            val response = httpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body?.string() ?: return@withContext null
                val json = JSONObject(body)
                val candidates = json.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val cand = candidates.getJSONObject(0)
                    val content = cand.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        return@withContext parts.getJSONObject(0).optString("text")
                    }
                }
            }
            null
        } catch (e: Exception) {
            null
        }
    }
}
