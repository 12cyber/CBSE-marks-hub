package com.example.data

import com.example.data.datasource.*
import com.example.data.model.*

object CurriculumRepository {

    private val allSubjectsList: List<Subject> = listOf(
        MathCurriculum.getMathStandard(),
        MathCurriculum.getMathBasic(),
        ScienceCurriculum.getScience(),
        SocialScienceCurriculum.getSocialScience(),
        LanguageCurriculum.getEnglishLanguageAndLiterature(),
        LanguageCurriculum.getEnglishCommunicative(),
        LanguageCurriculum.getHindiCourseA(),
        LanguageCurriculum.getHindiCourseB(),
        LanguageCurriculum.getSanskrit(),
        SkillCurriculum.getInformationTechnology(),
        SkillCurriculum.getArtificialIntelligence(),
        SkillCurriculum.getComputerApplications()
    )

    fun getAllSubjects(): List<Subject> = allSubjectsList

    fun getSubjectByCode(code: String): Subject? {
        return allSubjectsList.find { it.code.equals(code, ignoreCase = true) || it.id.equals(code, ignoreCase = true) }
    }

    fun getDefaultEnrolledSubjectCodes(): Set<String> {
        return setOf("041", "086", "087", "184", "002", "402")
    }

    fun searchCurriculum(
        query: String,
        selectedSubjectCodes: Set<String>? = null
    ): List<SearchResultItem> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()

        val results = mutableListOf<SearchResultItem>()
        val targetSubjects = if (selectedSubjectCodes != null && selectedSubjectCodes.isNotEmpty()) {
            allSubjectsList.filter { selectedSubjectCodes.contains(it.code) }
        } else {
            allSubjectsList
        }

        for (subject in targetSubjects) {
            // Check subject name or code match
            if (subject.name.lowercase().contains(q) || subject.code.contains(q)) {
                results.add(
                    SearchResultItem(
                        type = ResultType.SUBJECT,
                        title = "${subject.name} (${subject.code})",
                        subtitle = "Subject • ${subject.category.label} • ${subject.assessmentStructure.label}",
                        subjectCode = subject.code,
                        subjectName = subject.name,
                        unitId = null,
                        chapterId = null
                    )
                )
            }

            for (unit in subject.units) {
                if (unit.name.lowercase().contains(q) || unit.unitNumber.lowercase().contains(q)) {
                    results.add(
                        SearchResultItem(
                            type = ResultType.UNIT,
                            title = "${unit.unitNumber}: ${unit.name} (${unit.officialMarks} Marks)",
                            subtitle = "${subject.name} (${subject.code}) • Official Unit Weightage",
                            subjectCode = subject.code,
                            subjectName = subject.name,
                            unitId = unit.id,
                            chapterId = null
                        )
                    )
                }

                for (chapter in unit.chapters) {
                    val nameMatch = chapter.name.lowercase().contains(q)
                    val topicMatch = chapter.topics.any { it.lowercase().contains(q) }
                    val conceptMatch = chapter.importantConcepts.any { it.lowercase().contains(q) }

                    if (nameMatch || topicMatch || conceptMatch) {
                        val matchedTopic = if (nameMatch) {
                            "Chapter ${chapter.number} • ${chapter.pyqFrequency}"
                        } else {
                            val matched = chapter.topics.firstOrNull { it.lowercase().contains(q) }
                                ?: chapter.importantConcepts.firstOrNull { it.lowercase().contains(q) }
                            "Matched: $matched"
                        }

                        results.add(
                            SearchResultItem(
                                type = ResultType.CHAPTER,
                                title = "Ch ${chapter.number}: ${chapter.name}",
                                subtitle = "${subject.name} (${subject.code}) • ${unit.name} • $matchedTopic",
                                subjectCode = subject.code,
                                subjectName = subject.name,
                                unitId = unit.id,
                                chapterId = chapter.id,
                                chapterObj = chapter
                            )
                        )
                    }
                }
            }
        }
        return results
    }

    fun getAllChaptersFlat(subjectCode: String? = null): List<Triple<Subject, UnitWeightage, Chapter>> {
        val list = mutableListOf<Triple<Subject, UnitWeightage, Chapter>>()
        val subjects = if (subjectCode != null) {
            allSubjectsList.filter { it.code == subjectCode }
        } else {
            allSubjectsList
        }

        for (sub in subjects) {
            for (u in sub.units) {
                for (ch in u.chapters) {
                    list.add(Triple(sub, u, ch))
                }
            }
        }
        return list
    }
}

enum class ResultType {
    SUBJECT, UNIT, CHAPTER
}

data class SearchResultItem(
    val type: ResultType,
    val title: String,
    val subtitle: String,
    val subjectCode: String,
    val subjectName: String,
    val unitId: String?,
    val chapterId: String?,
    val chapterObj: Chapter? = null
)
