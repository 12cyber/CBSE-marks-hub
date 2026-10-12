package com.example.data.model

enum class SubjectCategory(val label: String) {
    COMPULSORY("Core Compulsory"),
    LANGUAGE("Languages"),
    SKILL_ELECTIVE("Skill & Electives")
}

enum class AssessmentStructure(
    val maxTheory: Int,
    val maxInternal: Int,
    val total: Int,
    val label: String
) {
    STANDARD_80_20(80, 20, 100, "80 Theory + 20 Internal Assessment"),
    SKILL_50_50(50, 50, 100, "50 Theory + 50 Practical & Project")
}

enum class MarksStatus(
    val tag: String,
    val description: String,
    val badgeColorHex: Long,
    val textColorHex: Long
) {
    OFFICIAL("OFFICIAL", "Directly mandated in official CBSE Curriculum document", 0xFF059669, 0xFFFFFFFF),
    HISTORICAL_TREND("HISTORICAL TREND", "Verified distribution from 2019–2024 CBSE Board Papers & SQP", 0xFF2563EB, 0xFFFFFFFF),
    ESTIMATE("ESTIMATED", "Estimated study priority from unit weightage and chapter depth", 0xFFD97706, 0xFFFFFFFF),
    NOT_AVAILABLE("NOT AVAILABLE", "Official chapter-wise marks not specified by CBSE", 0xFF64748B, 0xFFFFFFFF)
}

enum class RevisionPriority(val label: String, val weightScore: Int) {
    HIGH("High Priority", 3),
    MEDIUM("Medium Priority", 2),
    NORMAL("Standard Priority", 1)
}

data class AssessmentComponent(
    val name: String,
    val maxMarks: Int,
    val description: String
)

data class QuestionTypology(
    val competencyPercentage: Int = 50,
    val objectiveMcqPercentage: Int = 20,
    val constructedResponsePercentage: Int = 30,
    val details: String = "CBSE mandates 50% Competency-focused questions (MCQ, case-based, source-based), 20% Select response type (MCQs), and 30% Constructed response questions (short/long answer)."
)

data class Chapter(
    val id: String,
    val unitId: String,
    val unitName: String = "",
    val unitOfficialMarks: Int = 0,
    val number: Int,
    val name: String,
    val topics: List<String>,
    val importantConcepts: List<String>,
    val officialMarks: Int? = null, // Set only if CBSE officially specifies chapter-level marks
    val estimatedMarksDisplay: String, // E.g., "6 Marks (Official)" or "~5-6 Marks (Est.)"
    val marksStatus: MarksStatus,
    val pyqFrequency: String, // E.g., "1 MCQ (1M) + 1 Short (3M) + 1 Case Study (4M) across 2020-2024"
    val academicYear: String = "2024-25 / 2025-26",
    val source: String = "CBSE Secondary Curriculum & Official SQP Blueprint",
    val priority: RevisionPriority = RevisionPriority.HIGH,
    val examTips: String = ""
)

data class UnitWeightage(
    val id: String,
    val unitNumber: String,
    val name: String,
    val officialMarks: Int,
    val chapters: List<Chapter>,
    val marksStatus: MarksStatus = MarksStatus.OFFICIAL
)

data class Subject(
    val id: String,
    val code: String,
    val name: String,
    val category: SubjectCategory,
    val assessmentStructure: AssessmentStructure,
    val academicYear: String = "2024-25 / 2025-26",
    val assessmentComponents: List<AssessmentComponent>,
    val units: List<UnitWeightage>,
    val questionTypology: QuestionTypology = QuestionTypology(),
    val sourceTitle: String = "CBSE Secondary School Curriculum (Class X)",
    val sourceUrl: String = "https://cbseacademic.nic.in/curriculum_2025.html",
    val publicationDate: String = "March 2024 / Revised 2025",
    val verificationStatus: String = "VERIFIED OFFICIAL CBSE SYLLABUS",
    val lastVerifiedDate: String = "October 2024",
    val officialNotice: String = "CBSE officially mandates unit-wise marks allocation and question typologies. While individual question marks within units vary across board paper sets, unit totals are strictly adhered to."
)

data class SavedScenario(
    val id: String,
    val title: String,
    val subjectCode: String,
    val subjectName: String,
    val maxTheory: Float,
    val maxInternal: Float,
    val obtainedTheory: Float,
    val obtainedInternal: Float,
    val totalMarks: Float,
    val percentage: Float,
    val targetMarks: Float,
    val remainingNeeded: Float,
    val timestamp: Long = System.currentTimeMillis()
)
