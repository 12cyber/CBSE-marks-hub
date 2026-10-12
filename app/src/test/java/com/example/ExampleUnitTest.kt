package com.example

import com.example.ai.StudyRecommendationEngine
import com.example.data.CurriculumRepository
import com.example.data.model.AssessmentStructure
import com.example.data.model.MarksStatus
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testAllSubjectsHaveValid2026Curriculum() {
        val subjects = CurriculumRepository.getAllSubjects()
        assertTrue("At least 12 subjects should be available", subjects.size >= 12)

        subjects.forEach { subject ->
            assertEquals("Academic year must be 2026–27", "2026–27", subject.academicYear)
            assertTrue("Subject ${subject.name} must have units", subject.units.isNotEmpty())
            assertTrue("Subject ${subject.name} must have chapters", subject.units.flatMap { it.chapters }.isNotEmpty())

            // Sum of official unit marks must equal max theory marks
            val unitSum = subject.units.sumOf { it.officialMarks }
            val expectedTheory = subject.assessmentStructure.maxTheory
            assertEquals(
                "Subject ${subject.name} unit marks sum ($unitSum) must equal theory marks ($expectedTheory)",
                expectedTheory,
                unitSum
            )

            // Total must be 100
            assertEquals(
                "Theory + Internal must equal 100 for ${subject.name}",
                100,
                subject.assessmentStructure.maxTheory + subject.assessmentStructure.maxInternal
            )
        }
    }

    @Test
    fun testCoreSubjectsWeightageIntegrity() {
        // Math Standard (041)
        val math = CurriculumRepository.getSubjectByCode("041")
        assertNotNull(math)
        assertEquals(80, math!!.units.sumOf { it.officialMarks })

        // Science (086)
        val sci = CurriculumRepository.getSubjectByCode("086")
        assertNotNull(sci)
        assertEquals(80, sci!!.units.sumOf { it.officialMarks })
        assertEquals(5, sci.units.size) // 5 official units

        // Social Science (087)
        val sst = CurriculumRepository.getSubjectByCode("087")
        assertNotNull(sst)
        assertEquals(80, sst!!.units.sumOf { it.officialMarks })
        assertEquals(4, sst.units.size) // 4 disciplines of 20 marks each
        sst.units.forEach { unit ->
            assertEquals("SST unit ${unit.name} must be 20 marks", 20, unit.officialMarks)
        }
    }

    @Test
    fun testChapterMarksStatusIntegrity() {
        val allChapters = CurriculumRepository.getAllChaptersFlat()
        allChapters.forEach { (sub, unit, ch) ->
            assertNotNull("Chapter name must not be blank", ch.name)
            assertNotNull("Estimated marks display must not be blank", ch.estimatedMarksDisplay)
            assertNotNull("PYQ frequency must be populated", ch.pyqFrequency)
            assertEquals("2026–27", ch.academicYear)
            assertTrue(
                "Status must be valid MarksStatus",
                ch.marksStatus == MarksStatus.OFFICIAL ||
                        ch.marksStatus == MarksStatus.HISTORICAL_TREND ||
                        ch.marksStatus == MarksStatus.ESTIMATE ||
                        ch.marksStatus == MarksStatus.NOT_AVAILABLE
            )
        }
    }

    @Test
    fun testCurriculumSearch() {
        // Search by chapter name
        val realNumbersResults = CurriculumRepository.searchCurriculum("Real Numbers")
        assertTrue("Search for 'Real Numbers' should return chapters", realNumbersResults.isNotEmpty())

        // Search by topic keyword
        val thalesResults = CurriculumRepository.searchCurriculum("Thales")
        assertTrue("Search for 'Thales' should match Triangles", thalesResults.isNotEmpty())

        // Search by subject name
        val scienceResults = CurriculumRepository.searchCurriculum("Science")
        assertTrue("Search for 'Science' should return subject and units", scienceResults.isNotEmpty())
    }

    @Test
    fun testAiStudyRecommendationEngineScoring() = runBlocking {
        val enrolled = setOf("041", "086", "087")
        val completed = setOf("math_ch1") // Real numbers completed
        val difficult = setOf("math_ch7") // Triangles flagged difficult

        val report = StudyRecommendationEngine.generateRecommendations(
            enrolledCodes = enrolled,
            completedChapterIds = completed,
            difficultChapterIds = difficult,
            targetPercentages = mapOf("041" to 95f),
            overallTargetPercentage = 95f
        )

        assertNotNull(report)
        assertTrue("Must generate prioritized chapters", report.priorityChapters.isNotEmpty())
        assertEquals(4, report.fourWeekRevisionPlan.size)

        // Triangles should have a very high priority score because it is difficult, incomplete, and in a 15-mark unit
        val trianglesItem = report.priorityChapters.find { it.chapterId == "math_ch7" }
        assertNotNull("Triangles should be in top prioritized list", trianglesItem)
        assertTrue("Triangles should be flagged difficult", trianglesItem!!.isDifficult)
        assertFalse("Triangles should be incomplete", trianglesItem.isCompleted)
    }
}
