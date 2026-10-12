package com.example.data.datasource

import com.example.data.model.*

object LanguageCurriculum {

    private const val ACADEMIC_YEAR = "2026–27"
    private const val SOURCE_REF = "CBSE Class 10 Language Curriculum 2026–27 (cbseacademic.nic.in)"

    private val languageInternalComponents = listOf(
        AssessmentComponent("Assessment of Listening Skills (ASL)", 5, "Listening comprehension worksheets, recorded audio evaluation"),
        AssessmentComponent("Assessment of Speaking Skills (ASL)", 5, "Extempore, individual presentation, dialogue, conversation assessment"),
        AssessmentComponent("Periodic Tests (Pen-Paper)", 5, "School unit tests on grammar, reading, and literature"),
        AssessmentComponent("Portfolio & Notebook", 5, "Regular classwork, assignments, creative writing submissions")
    )

    fun getEnglishLanguageAndLiterature(): Subject = Subject(
        id = "eng_184",
        code = "184",
        name = "English Language & Literature",
        category = SubjectCategory.LANGUAGE,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = languageInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "eng_sec_a",
                unitNumber = "Section A",
                name = "Reading Skills",
                officialMarks = 20,
                chapters = listOf(
                    Chapter(
                        id = "eng_ch_read1",
                        unitId = "eng_sec_a",
                        unitName = "Reading Skills",
                        unitOfficialMarks = 20,
                        number = 1,
                        name = "Discursive Passage (400-450 words)",
                        topics = listOf("Unseen discursive passage testing inference, evaluation, vocabulary in context"),
                        importantConcepts = listOf("Inference questions, author's tone, synonym/antonym identification in context"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 10 Objective/MCQ & Short questions assessing inference and vocabulary",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Read questions first to locate keywords before reading the passage to save critical exam time."
                    ),
                    Chapter(
                        id = "eng_ch_read2",
                        unitId = "eng_sec_a",
                        unitName = "Reading Skills",
                        unitOfficialMarks = 20,
                        number = 2,
                        name = "Case-Based Factual Passage (200-250 words)",
                        topics = listOf("Unseen factual passage with visual input/statistical data/charts testing analysis"),
                        importantConcepts = listOf("Interpreting chart/graph data, comparing statistical statements, extracting core evidence"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 10 Questions evaluating analytical comparison of data/charts",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Pay close attention to percentages, trends, and comparisons highlighted in the accompanying graph or table."
                    )
                )
            ),
            UnitWeightage(
                id = "eng_sec_b",
                unitNumber = "Section B",
                name = "Writing Skills & Grammar",
                officialMarks = 20,
                chapters = listOf(
                    Chapter(
                        id = "eng_ch_gram",
                        unitId = "eng_sec_b",
                        unitName = "Writing Skills & Grammar",
                        unitOfficialMarks = 20,
                        number = 3,
                        name = "Applied Grammar",
                        topics = listOf("Determiners", "Tenses", "Modals", "Subject-verb concord", "Reported speech (Commands & requests, Statements, Questions)"),
                        importantConcepts = listOf("Reported speech tense conversions and pronoun shifts", "Subject-verb concord with collective nouns & either/or"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 10 out of 12 questions (Error correction & Dialogue completion)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Error correction sentences: First identify subject and verb tense. Check singular/plural concordance."
                    ),
                    Chapter(
                        id = "eng_ch_write1",
                        unitId = "eng_sec_b",
                        unitName = "Writing Skills & Grammar",
                        unitOfficialMarks = 20,
                        number = 4,
                        name = "Formal Letter Writing (100-120 words)",
                        topics = listOf("Letter to the Editor", "Letter of Complaint (Business/Official)", "Letter of Inquiry", "Placing an Order"),
                        importantConcepts = listOf("CBSE standard formal letter format: Sender address, Date, Receiver address, Subject, Salutation, Body, Closing"),
                        officialMarks = 5,
                        estimatedMarksDisplay = "5 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "5 Marks: Format (1M), Content (2M), Accuracy & Expression (2M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Subject line must be brief and underlined. Do not skip sender address and date formatting."
                    ),
                    Chapter(
                        id = "eng_ch_write2",
                        unitId = "eng_sec_b",
                        unitName = "Writing Skills & Grammar",
                        unitOfficialMarks = 20,
                        number = 5,
                        name = "Analytical Paragraph Writing (100-120 words)",
                        topics = listOf("Analysing given map, chart, graph, cue, outline, or reporting data trends"),
                        importantConcepts = listOf("Introduction, Body paragraph contrasting highest/lowest data, Conclusion summary"),
                        officialMarks = 5,
                        estimatedMarksDisplay = "5 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "5 Marks: Content (2M), Organization (2M), Accuracy (1M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Do not give personal opinions in an analytical paragraph; strictly interpret and compare the supplied data."
                    )
                )
            ),
            UnitWeightage(
                id = "eng_sec_c",
                unitNumber = "Section C",
                name = "Language Through Literature",
                officialMarks = 40,
                chapters = listOf(
                    Chapter(
                        id = "eng_ch_lit1",
                        unitId = "eng_sec_c",
                        unitName = "Literature Textbooks",
                        unitOfficialMarks = 40,
                        number = 6,
                        name = "First Flight (Prose & Poetry)",
                        topics = listOf("A Letter to God", "Nelson Mandela: Long Walk to Freedom", "Two Stories About Flying", "From the Diary of Anne Frank", "Glimpses of India", "Mijbil the Otter", "Madam Rides the Bus", "The Sermon at Benares", "The Proposal", "Poems: Dust of Snow, Fire and Ice, A Tiger in the Zoo, How to Tell Wild Animals, The Ball Poem, Amanda!, The Trees, Fog, Custard the Dragon, For Anne Gregory"),
                        importantConcepts = listOf("Nelson Mandela's concepts of freedom and twin obligations", "Amanda poem's contrast of adult nagging vs child fantasy", "Sermon at Benares teachings on the inevitability of death"),
                        officialMarks = null,
                        estimatedMarksDisplay = "~26 – 28 Marks (Est.)",
                        marksStatus = MarksStatus.HISTORICAL_TREND,
                        pyqFrequency = "26–28 Marks: 2 Extracts (5M each) + 4 Short Questions (3M each) + 1 Long Thematic Answer (6M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "CBSE loves 6-mark comparative value-based questions linking two characters from different chapters."
                    ),
                    Chapter(
                        id = "eng_ch_lit2",
                        unitId = "eng_sec_c",
                        unitName = "Literature Textbooks",
                        unitOfficialMarks = 40,
                        number = 7,
                        name = "Footprints Without Feet (Supplementary)",
                        topics = listOf("A Triumph of Surgery", "The Thief’s Story", "The Midnight Visitor", "A Question of Trust", "Footprints Without Feet", "The Making of a Scientist", "The Necklace", "Bholi", "The Book That Saved the Earth"),
                        importantConcepts = listOf("Bholi's character transformation through education", "Matilda Loisel's discontent in The Necklace", "Hari Singh's transformation by Anil's trust"),
                        officialMarks = null,
                        estimatedMarksDisplay = "~12 – 14 Marks (Est.)",
                        marksStatus = MarksStatus.HISTORICAL_TREND,
                        pyqFrequency = "12–14 Marks: 1 Extract (5M) + 2 Short Questions (3M each) + 1 Long Character Sketch (6M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Bholi and The Necklace are the most frequent chapters for the 6-mark character sketch question."
                    )
                )
            )
        ),
        sourceTitle = "CBSE Class 10 English Language & Literature Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "CBSE officially mandates: Section A Reading (20m), Section B Writing & Grammar (20m), Section C Literature (40m). Total = 80 marks theory."
    )

    fun getEnglishCommunicative(): Subject = Subject(
        id = "eng_101",
        code = "101",
        name = "English Communicative",
        category = SubjectCategory.LANGUAGE,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = languageInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "eng_comm_u1",
                unitNumber = "Section A",
                name = "Reading Skills",
                officialMarks = 22,
                chapters = listOf(
                    Chapter(
                        id = "eng_comm_ch1",
                        unitId = "eng_comm_u1",
                        unitName = "Reading Skills",
                        unitOfficialMarks = 22,
                        number = 1,
                        name = "Two Unseen Passages (Discursive & Factual)",
                        topics = listOf("Passage analysis, vocabulary in context, summary completion"),
                        importantConcepts = listOf("Extracting main points, contextual vocabulary, title justification"),
                        officialMarks = 22,
                        estimatedMarksDisplay = "22 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "22 Marks: Unseen passages assessing comprehension and deduction",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Pay attention to exact word count limits in short comprehension answers."
                    )
                )
            ),
            UnitWeightage(
                id = "eng_comm_u2",
                unitNumber = "Section B",
                name = "Writing Skills",
                officialMarks = 22,
                chapters = listOf(
                    Chapter(
                        id = "eng_comm_ch2",
                        unitId = "eng_comm_u2",
                        unitName = "Writing Skills",
                        unitOfficialMarks = 22,
                        number = 2,
                        name = "Creative & Functional Writing",
                        topics = listOf("Notice, Email writing, Letter to Editor, Article / Speech writing"),
                        importantConcepts = listOf("Notice format with box, formal email structure, article structuring"),
                        officialMarks = 22,
                        estimatedMarksDisplay = "22 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "22 Marks: Notice (4M), Email (3M), Letter (7M), Article/Speech (8M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Notice writing must always be enclosed inside a ruled box."
                    )
                )
            ),
            UnitWeightage(
                id = "eng_comm_u3",
                unitNumber = "Section C",
                name = "Grammar",
                officialMarks = 10,
                chapters = listOf(
                    Chapter(
                        id = "eng_comm_ch3",
                        unitId = "eng_comm_u3",
                        unitName = "Grammar",
                        unitOfficialMarks = 10,
                        number = 3,
                        name = "Applied Grammar Skills",
                        topics = listOf("Gap filling, Editing / Omission, Sentence reordering / Dialogue completion"),
                        importantConcepts = listOf("Prepositions, Conjunctions, Relative clauses, Correct verb forms"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: Gap filling (3M), Editing (4M), Reordering (3M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "In omission exercises, locate the word before, missing word, and word after."
                    )
                )
            ),
            UnitWeightage(
                id = "eng_comm_u4",
                unitNumber = "Section D",
                name = "Literature Textbook",
                officialMarks = 26,
                chapters = listOf(
                    Chapter(
                        id = "eng_comm_ch4",
                        unitId = "eng_comm_u4",
                        unitName = "Literature",
                        unitOfficialMarks = 26,
                        number = 4,
                        name = "Interact in English (Literature Reader & Drama)",
                        topics = listOf("Two Gentlemen of Verona, Mrs. Packletide’s Tiger, The Letter, A Shady Plot, Patol Babu, Virtually True", "Poems: The Frog and the Nightingale, Mirror, Not Marble nor the Gilded Monuments, Ozymandias, The Rime of the Ancient Mariner, Snake", "Drama: The Dear Departed, Julius Caesar"),
                        importantConcepts = listOf("Ozymandias theme of pride, The Dear Departed satire, Julius Caesar Antony's speech"),
                        officialMarks = 26,
                        estimatedMarksDisplay = "26 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "26 Marks: Extracts, short answers and thematic long questions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Antony's speech from Julius Caesar is the most cited drama question."
                    )
                )
            )
        ),
        sourceTitle = "CBSE Class 10 English Communicative Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session"
    )

    fun getHindiCourseA(): Subject = Subject(
        id = "hin_002",
        code = "002",
        name = "Hindi Course A",
        category = SubjectCategory.LANGUAGE,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = languageInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "hin_a_u1",
                unitNumber = "Khand Ka",
                name = "Apthit Bodh (Reading)",
                officialMarks = 14,
                chapters = listOf(
                    Chapter(
                        id = "hin_a_ch1",
                        unitId = "hin_a_u1",
                        unitName = "Apthit Bodh",
                        unitOfficialMarks = 14,
                        number = 1,
                        name = "Apthit Gadyansh & Kavyansh",
                        topics = listOf("Unseen prose passage (7m) and unseen poetry passage (7m)"),
                        importantConcepts = listOf("Central idea of poem, contextual word meanings, heading selection"),
                        officialMarks = 14,
                        estimatedMarksDisplay = "14 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "14 Marks: 7 Marks Gadyansh + 7 Marks Kavyansh MCQs",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Solve poetry questions after grasping the emotional tone of the poet."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_a_u2",
                unitNumber = "Khand Kha",
                name = "Vyakaran (Applied Grammar)",
                officialMarks = 16,
                chapters = listOf(
                    Chapter(
                        id = "hin_a_ch2",
                        unitId = "hin_a_u2",
                        unitName = "Vyakaran",
                        unitOfficialMarks = 16,
                        number = 2,
                        name = "Vyavaharik Vyakaran",
                        topics = listOf("Rachna ke aadhar par vakya bhed (4m)", "Vachya (4m)", "Pad Parichay (4m)", "Alankar (4m)"),
                        importantConcepts = listOf("Vakya roopantaran, Vachya parivartan, Pad parichay, Alankar identification"),
                        officialMarks = 16,
                        estimatedMarksDisplay = "16 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "16 Marks: 16 out of 20 MCQs (4 marks per grammar topic)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Pad parichay and Vakya roopantaran are 100% scoring with systematic rules practice."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_a_u3",
                unitNumber = "Khand Ga",
                name = "Pathyapustak (Literature)",
                officialMarks = 30,
                chapters = listOf(
                    Chapter(
                        id = "hin_a_ch3",
                        unitId = "hin_a_u3",
                        unitName = "Literature Textbooks",
                        unitOfficialMarks = 30,
                        number = 3,
                        name = "Kshitij Bhag-2 & Kritika Bhag-2",
                        topics = listOf("Kshitij Gadya (11m)", "Kshitij Kavya (11m)", "Kritika Bhag-2 (8m)"),
                        importantConcepts = listOf("Ram-Lakshman-Parshuram Samvad, Netaji ka Chashma, Balgobin Bhagat, Mata ka Aanchal"),
                        officialMarks = 30,
                        estimatedMarksDisplay = "30 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "30 Marks: Extracts (MCQ) + Short & Long subjective questions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Kritika questions are value-based and carry 4 marks each; quote incidents from the text."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_a_u4",
                unitNumber = "Khand Gha",
                name = "Rachnatmak Lekhan (Writing)",
                officialMarks = 20,
                chapters = listOf(
                    Chapter(
                        id = "hin_a_ch4",
                        unitId = "hin_a_u4",
                        unitName = "Rachnatmak Lekhan",
                        unitOfficialMarks = 20,
                        number = 4,
                        name = "Rachnatmak Lekhan",
                        topics = listOf("Anuched Lekhan (6m)", "Patra Lekhan (5m)", "Biodata/Swavrtt OR Email Lekhan (5m)", "Vigyapan OR Sandesh (4m)"),
                        importantConcepts = listOf("Biodata/Swavrtt formal standard format, Advertisement drawing in box with slogan"),
                        officialMarks = 20,
                        estimatedMarksDisplay = "20 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "20 Marks: Paragraph (6M) + Letter (5M) + Swavrtt/Email (5M) + Vigyapan (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Swavrtt lekhan format carries specific marks; practice writing complete resume in Hindi."
                    )
                )
            )
        ),
        sourceTitle = "CBSE Class 10 Hindi Course A Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session"
    )

    fun getHindiCourseB(): Subject = Subject(
        id = "hin_085",
        code = "085",
        name = "Hindi Course B",
        category = SubjectCategory.LANGUAGE,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = languageInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "hin_b_u1",
                unitNumber = "Khand Ka",
                name = "Apthit Gadyansh (Reading)",
                officialMarks = 14,
                chapters = listOf(
                    Chapter(
                        id = "hin_b_ch1",
                        unitId = "hin_b_u1",
                        unitName = "Reading",
                        unitOfficialMarks = 14,
                        number = 1,
                        name = "Two Unseen Prose Passages",
                        topics = listOf("Comprehension, inference, contextual vocabulary"),
                        importantConcepts = listOf("Inference and factual retrieval"),
                        officialMarks = 14,
                        estimatedMarksDisplay = "14 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "14 Marks: 14 MCQs evaluating comprehension",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Underline relevant phrases in paragraph while reading."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_b_u2",
                unitNumber = "Khand Kha",
                name = "Vyavaharik Vyakaran",
                officialMarks = 16,
                chapters = listOf(
                    Chapter(
                        id = "hin_b_ch2",
                        unitId = "hin_b_u2",
                        unitName = "Vyakaran",
                        unitOfficialMarks = 16,
                        number = 2,
                        name = "Applied Grammar (Course B)",
                        topics = listOf("Padbandh (4m)", "Vakya roopantaran (4m)", "Samas (4m)", "Muhavare (4m)"),
                        importantConcepts = listOf("5 types of Padbandh, Samas vigrah, Muhavare from textbook"),
                        officialMarks = 16,
                        estimatedMarksDisplay = "16 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "16 Marks: 16 out of 20 MCQs (4 marks each topic)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Master Padbandh rules; question tests identifying the underline boundary till the head word."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_b_u3",
                unitNumber = "Khand Ga",
                name = "Pathyapustak (Sparsh & Sanchayan)",
                officialMarks = 28,
                chapters = listOf(
                    Chapter(
                        id = "hin_b_ch3",
                        unitId = "hin_b_u3",
                        unitName = "Literature",
                        unitOfficialMarks = 28,
                        number = 3,
                        name = "Sparsh Bhag-2 & Sanchayan Bhag-2",
                        topics = listOf("Sparsh Gadya (11m)", "Sparsh Kavya (11m)", "Sanchayan (6m)"),
                        importantConcepts = listOf("Bade Bhai Sahab, Harihar Kaka, Kabir Sakhi, Tantara-Vamiro"),
                        officialMarks = 28,
                        estimatedMarksDisplay = "28 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "28 Marks: Extracts & Subjective analytical questions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Harihar Kaka and Bade Bhai Sahab are almost guaranteed 4-mark questions."
                    )
                )
            ),
            UnitWeightage(
                id = "hin_b_u4",
                unitNumber = "Khand Gha",
                name = "Lekhan (Writing Skills)",
                officialMarks = 22,
                chapters = listOf(
                    Chapter(
                        id = "hin_b_ch4",
                        unitId = "hin_b_u4",
                        unitName = "Writing Skills",
                        unitOfficialMarks = 22,
                        number = 4,
                        name = "Creative Writing (Course B)",
                        topics = listOf("Anuched Lekhan (5m)", "Formal Letter (5m)", "Soochna Lekhan (4m)", "Vigyapan Lekhan (4m)", "Laghu Katha OR Email (4m)"),
                        importantConcepts = listOf("Soochna lekhan inside box, Laghu katha writing with moral"),
                        officialMarks = 22,
                        estimatedMarksDisplay = "22 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "22 Marks: Structured functional writing formats",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Notice (Soochna) must clearly state date, subject, venue, and designated officer."
                    )
                )
            )
        ),
        sourceTitle = "CBSE Class 10 Hindi Course B Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session"
    )

    fun getSanskrit(): Subject = Subject(
        id = "san_122",
        code = "122",
        name = "Sanskrit",
        category = SubjectCategory.LANGUAGE,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = languageInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "san_u1",
                unitNumber = "Khand Ka",
                name = "Apthit Avabodhanam (Reading)",
                officialMarks = 10,
                chapters = listOf(
                    Chapter(
                        id = "san_ch1",
                        unitId = "san_u1",
                        unitName = "Apthit Avabodhanam",
                        unitOfficialMarks = 10,
                        number = 1,
                        name = "Apthit Gadyansh",
                        topics = listOf("Unseen Sanskrit prose passage with single word and full sentence answers, title, grammar"),
                        importantConcepts = listOf("Ekpaden uttaram, Poornavakyen uttaram, Shirshak"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: Comprehension + Grammar in passage",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Answer strictly in the required format (one word vs full sentence)."
                    )
                )
            ),
            UnitWeightage(
                id = "san_u2",
                unitNumber = "Khand Kha",
                name = "Rachnatmak Karyam (Writing)",
                officialMarks = 15,
                chapters = listOf(
                    Chapter(
                        id = "san_ch2",
                        unitId = "san_u2",
                        unitName = "Rachnatmak Karyam",
                        unitOfficialMarks = 15,
                        number = 2,
                        name = "Rachnatmak Karyam",
                        topics = listOf("Patra Lekhan with Manjusha (5m)", "Chitra Varnanam OR Anuchhed Lekhan (5m)", "Hindi to Sanskrit translation (5m)"),
                        importantConcepts = listOf("Fill in the blanks letter with Manjusha, Chitra varnanam using Asti/Santi"),
                        officialMarks = 15,
                        estimatedMarksDisplay = "15 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "15 Marks: Patra (5M) + Chitra (5M) + Anuvad (5M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "In Chitra Varnanam, write 5 simple, grammatically foolproof sentences."
                    )
                )
            ),
            UnitWeightage(
                id = "san_u3",
                unitNumber = "Khand Ga",
                name = "Anupryukt Vyakaranam",
                officialMarks = 25,
                chapters = listOf(
                    Chapter(
                        id = "san_ch3",
                        unitId = "san_u3",
                        unitName = "Anupryukt Vyakaranam",
                        unitOfficialMarks = 25,
                        number = 3,
                        name = "Applied Sanskrit Grammar",
                        topics = listOf("Sandhi (4m)", "Samas (4m)", "Pratyaya (4m)", "Vachya (3m)", "Samay (4m)", "Avyaya (3m)", "Ashuddhi Shodhanam (3m)"),
                        importantConcepts = listOf("Time: Sapad, Sardh, Padon; Pratyaya rules for Matup and Tap; Sandhi"),
                        officialMarks = 25,
                        estimatedMarksDisplay = "25 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "25 Marks: Objective MCQs on grammatical rules",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Time writing (Samay) is an easy 4 out of 4 marks with predictable rules."
                    )
                )
            ),
            UnitWeightage(
                id = "san_u4",
                unitNumber = "Khand Gha",
                name = "Pathit Avabodhanam (Shemushi)",
                officialMarks = 30,
                chapters = listOf(
                    Chapter(
                        id = "san_ch4",
                        unitId = "san_u4",
                        unitName = "Pathit Avabodhanam",
                        unitOfficialMarks = 30,
                        number = 4,
                        name = "Shemushi Bhag-2 Textbook",
                        topics = listOf("Gadyansh (5m)", "Padyansh (5m)", "Natyansh (5m)", "Shloka Anvaya (4m)", "Prashna Nirmanam (4m)", "Ghatanakram (4m)", "Bhavartha (3m)"),
                        importantConcepts = listOf("Shuchiparyavaranam, Buddhirbalvati Sada, Subhashitani, Anvaya completion"),
                        officialMarks = 30,
                        estimatedMarksDisplay = "30 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "30 Marks: Textbook prose, shloka and drama comprehension",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Memorize the Anvaya of all Subhashitani and Shuchiparyavaranam shlokas."
                    )
                )
            )
        ),
        sourceTitle = "CBSE Class 10 Sanskrit Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session"
    )
}
