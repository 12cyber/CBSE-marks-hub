package com.example.data.datasource

import com.example.data.model.*

object SkillCurriculum {

    private const val ACADEMIC_YEAR = "2026–27"
    private const val SOURCE_REF = "CBSE Class 10 Skill Education Curriculum 2026–27 (cbseacademic.nic.in)"

    private val skillInternalComponents = listOf(
        AssessmentComponent("Practical Examination", 20, "Hands-on coding, practical lab performance, executing assigned problem statement"),
        AssessmentComponent("Viva Voce", 10, "Oral examination on lab concepts, syntax, and logic"),
        AssessmentComponent("Project Work / Portfolio", 10, "Submission of verified project code / AI model prototype / application report"),
        AssessmentComponent("Student Practical File", 10, "Maintenance of practical exercise file with 15-20 documented problem solutions")
    )

    fun getInformationTechnology(): Subject = Subject(
        id = "it_402",
        code = "402",
        name = "Information Technology",
        category = SubjectCategory.SKILL_ELECTIVE,
        assessmentStructure = AssessmentStructure.SKILL_50_50,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = skillInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "it_part_a",
                unitNumber = "Part A",
                name = "Employability Skills",
                officialMarks = 10,
                chapters = listOf(
                    Chapter(
                        id = "it_ch_emp1",
                        unitId = "it_part_a",
                        unitName = "Employability Skills",
                        unitOfficialMarks = 10,
                        number = 1,
                        name = "Communication & Self-Management Skills",
                        topics = listOf("Methods of communication, Communication cycle, Stress management, Working independently"),
                        importantConcepts = listOf("7 Cs of Communication, ABC of stress management"),
                        officialMarks = 4,
                        estimatedMarksDisplay = "4 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "4 Marks: MCQs and direct definitions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.MEDIUM,
                        examTips = "Focus on the 7 Cs of effective communication and barrier types."
                    ),
                    Chapter(
                        id = "it_ch_emp2",
                        unitId = "it_part_a",
                        unitName = "Employability Skills",
                        unitOfficialMarks = 10,
                        number = 2,
                        name = "ICT, Entrepreneurial & Green Skills",
                        topics = listOf("Basic computer maintenance, Virus protection, Entrepreneurship characteristics, Sustainable development goals (SDGs)"),
                        importantConcepts = listOf("Threats to computer data, 17 SDGs overview, Entrepreneurial traits"),
                        officialMarks = 6,
                        estimatedMarksDisplay = "6 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "6 Marks: MCQs and Short 2M questions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Learn key Sustainable Development Goals (SDGs) and disk cleanup utilities."
                    )
                )
            ),
            UnitWeightage(
                id = "it_part_b",
                unitNumber = "Part B",
                name = "Subject Specific Skills",
                officialMarks = 40,
                chapters = listOf(
                    Chapter(
                        id = "it_ch_doc",
                        unitId = "it_part_b",
                        unitName = "Subject Specific Skills",
                        unitOfficialMarks = 40,
                        number = 1,
                        name = "Digital Documentation (Advanced)",
                        topics = listOf("Applying Styles in documents", "Inserting and using images", "Creating and using Templates", "Creating Table of Contents", "Using Mail Merge"),
                        importantConcepts = listOf("Fill Format mode in OpenOffice Writer, Main document & Data source in Mail Merge, TOC hierarchy"),
                        officialMarks = 8,
                        estimatedMarksDisplay = "8 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "8 Marks: 2 MCQs + 2 Short/Descriptive Questions (2M/4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Steps to create Table of Contents and Mail Merge steps are frequently asked."
                    ),
                    Chapter(
                        id = "it_ch_calc",
                        unitId = "it_part_b",
                        unitName = "Subject Specific Skills",
                        unitOfficialMarks = 40,
                        number = 2,
                        name = "Electronic Spreadsheet (Advanced)",
                        topics = listOf("Consolidating data, Subtotals", "What-If Analysis (Scenarios, Goal Seek, Solver)", "Linking spreadsheet data, Hyperlinks", "Sharing and reviewing spreadsheets, Track changes", "Using Macros"),
                        importantConcepts = listOf("Goal Seek vs Solver differences, Consolidate function with multiple sheets, Absolute vs Relative hyperlinks"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 2 MCQs + 1 Short (2M) + 1 Long (4M on Goal Seek / Solver)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Goal Seek definition and difference from Solver is tested in almost every paper."
                    ),
                    Chapter(
                        id = "it_ch_dbms",
                        unitId = "it_part_b",
                        unitName = "Subject Specific Skills",
                        unitOfficialMarks = 40,
                        number = 3,
                        name = "Database Management System (DBMS)",
                        topics = listOf("Concepts of DBMS, Relational Database Model", "Creating and editing tables using wizard & SQL DDL commands", "Performing operations on tables (Primary key, Composite key, Foreign key)", "Retrieving data using queries (SELECT, WHERE, ORDER BY)", "Creating Forms and Reports"),
                        importantConcepts = listOf("Primary key vs Candidate key vs Alternate key, Referential Integrity, SQL queries: SELECT with WHERE conditions"),
                        officialMarks = 12,
                        estimatedMarksDisplay = "12 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "12 Marks: Highest weightage in IT 402! 3 MCQs + 1 Short (2M) + 1 SQL Query Set (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Writing SQL queries for given tables is guaranteed 4-6 marks. Learn Primary Key definition."
                    ),
                    Chapter(
                        id = "it_ch_web",
                        unitId = "it_part_b",
                        unitName = "Subject Specific Skills",
                        unitOfficialMarks = 40,
                        number = 4,
                        name = "Web Applications and Security",
                        topics = listOf("Working with Accessibility options", "Networking fundamentals (LAN, WAN, Client-server, P2P, ISP)", "Introduction to Instant Messaging & Blogs", "Online transactions & Internet security (Antivirus, Firewalls, Strong passwords, Phishing)"),
                        importantConcepts = listOf("High contrast, sticky keys, filter keys accessibility features, Phishing vs Spoofing, Best practices for secure passwords"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 2 MCQs + 2 Short Questions (2M/4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Accessibility options (StickyKeys, FilterKeys, MouseKeys) are tested in MCQs."
                    )
                )
            )
        ),
        questionTypology = QuestionTypology(50, 20, 30, "Theory Exam: 50 Marks (2 hours). Practical / Project: 50 Marks. Part A: Employability Skills (10M), Part B: Subject Specific Skills (40M)."),
        sourceTitle = "CBSE Class 10 Information Technology (402) Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "Information Technology (402) has a 50-mark Theory + 50-mark Practical evaluation pattern as officially mandated by the CBSE Skill Education Department."
    )

    fun getArtificialIntelligence(): Subject = Subject(
        id = "ai_417",
        code = "417",
        name = "Artificial Intelligence",
        category = SubjectCategory.SKILL_ELECTIVE,
        assessmentStructure = AssessmentStructure.SKILL_50_50,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = skillInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "ai_part_a",
                unitNumber = "Part A",
                name = "Employability Skills",
                officialMarks = 10,
                chapters = listOf(
                    Chapter(
                        id = "ai_ch_emp",
                        unitId = "ai_part_a",
                        unitName = "Employability Skills",
                        unitOfficialMarks = 10,
                        number = 1,
                        name = "Communication, Self-Management & ICT Skills",
                        topics = listOf("Communication barriers, Emotional intelligence, Green skills, Sustainable development"),
                        importantConcepts = listOf("Non-verbal cues, Time management quadrants, SDG goals"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: MCQs and Short 2M direct questions",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.MEDIUM,
                        examTips = "Answer brief direct definitions of employability concepts."
                    )
                )
            ),
            UnitWeightage(
                id = "ai_part_b",
                unitNumber = "Part B",
                name = "Subject Specific Skills (AI Domains)",
                officialMarks = 40,
                chapters = listOf(
                    Chapter(
                        id = "ai_ch_intro",
                        unitId = "ai_part_b",
                        unitName = "AI Domains",
                        unitOfficialMarks = 40,
                        number = 1,
                        name = "Introduction to AI & AI Project Cycle",
                        topics = listOf("What is AI, Rule-based vs Learning-based approach, 3 Domains of AI (Data Science, Computer Vision, Natural Language Processing)", "AI Project Cycle: Problem Scoping (4Ws canvas), Data Acquisition, Data Exploration, Modelling, Evaluation"),
                        importantConcepts = listOf("4Ws Canvas: Who, What, Where, Why", "Data acquisition sources, Training vs Testing dataset", "Difference between Data Science, CV, and NLP"),
                        officialMarks = 12,
                        estimatedMarksDisplay = "12 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "12 Marks: 3 MCQs + 1 Short (2M) + 1 Problem Scoping 4Ws Question (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "4Ws Problem Scoping Canvas is guaranteed in the subjective section."
                    ),
                    Chapter(
                        id = "ai_ch_cv_nlp",
                        unitId = "ai_part_b",
                        unitName = "AI Domains",
                        unitOfficialMarks = 40,
                        number = 2,
                        name = "Computer Vision & Natural Language Processing",
                        topics = listOf("Computer Vision: Image classification, Object detection, Segmentation, Pixel concepts, RGB values", "NLP: Text normalisation steps (Sentence segmentation, Tokenisation, Stop words removal, Stemming, Lemmatisation), Bag of Words (BoW), TF-IDF"),
                        importantConcepts = listOf("Stemming vs Lemmatisation difference", "Tokenisation and stop words removal algorithm", "Bag of Words term frequency calculation matrix"),
                        officialMarks = 14,
                        estimatedMarksDisplay = "14 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "14 Marks: 3 MCQs + 1 Short (2M) + 1 NLP Normalisation / BoW Calculation (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Calculate Bag of Words vector and explain difference between Stemming (rule-based chop) and Lemmatisation."
                    ),
                    Chapter(
                        id = "ai_ch_eval",
                        unitId = "ai_part_b",
                        unitName = "AI Domains",
                        unitOfficialMarks = 40,
                        number = 3,
                        name = "Evaluation & Confusion Matrix",
                        topics = listOf("Evaluation parameters: Accuracy, Precision, Recall, F1-Score", "Confusion Matrix (True Positive, True Negative, False Positive, False Negative)", "Scenarios where Precision is critical vs Recall is critical"),
                        importantConcepts = listOf("Confusion Matrix structure (2x2 grid)", "Formulas: Accuracy, Precision, Recall, F1 Score calculation"),
                        officialMarks = 14,
                        estimatedMarksDisplay = "14 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "14 Marks: 2 MCQs + 1 Short (2M) + 1 Numerical on Confusion Matrix (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Confusion matrix numerical calculating Accuracy, Precision, Recall, and F1 Score is a confirmed 4-mark question!"
                    )
                )
            )
        ),
        questionTypology = QuestionTypology(50, 20, 30, "Theory Exam: 50 Marks (2 hours). Practical / Project: 50 Marks. Part A: Employability (10M), Part B: Subject Skills (40M)."),
        sourceTitle = "CBSE Class 10 Artificial Intelligence (417) Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "Artificial Intelligence (417) follows the CBSE Skill Education assessment model with 50 theory marks and 50 hands-on practical marks."
    )

    fun getComputerApplications(): Subject = Subject(
        id = "ca_165",
        code = "165",
        name = "Computer Applications",
        category = SubjectCategory.SKILL_ELECTIVE,
        assessmentStructure = AssessmentStructure.SKILL_50_50,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = skillInternalComponents,
        units = listOf(
            UnitWeightage(
                id = "ca_u1",
                unitNumber = "Unit I",
                name = "Networking",
                officialMarks = 15,
                chapters = listOf(
                    Chapter(
                        id = "ca_ch1",
                        unitId = "ca_u1",
                        unitName = "Networking",
                        unitOfficialMarks = 15,
                        number = 1,
                        name = "Internet & Web Services",
                        topics = listOf("Internet, WWW, Web servers, Web clients, Websites, Web pages, Web browsers, Blog, Newsgroup, HTML, Web address, Email address", "Web services: Chat, Email, Video conferencing, e-Learning, e-Shopping", "Mobile technologies: SMS, MMS, 3G, 4G"),
                        importantConcepts = listOf("Web server vs Web client", "URL structure components (protocol, domain, path)", "e-Commerce security measures"),
                        officialMarks = 15,
                        estimatedMarksDisplay = "15 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "15 Marks: 3 MCQs + 2 Short (2M each) + 1 Long Answer (4M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Protocols (HTTP, HTTPS, FTP) and URL structure definitions are standard."
                    )
                )
            ),
            UnitWeightage(
                id = "ca_u2",
                unitNumber = "Unit II",
                name = "HTML (HyperText Markup Language)",
                officialMarks = 25,
                chapters = listOf(
                    Chapter(
                        id = "ca_ch2",
                        unitId = "ca_u2",
                        unitName = "HTML",
                        unitOfficialMarks = 25,
                        number = 2,
                        name = "HTML5 & CSS Styling",
                        topics = listOf("Basic HTML tags: html, head, title, body", "Text formatting: p, br, hr, h1-h6, b, i, u", "Lists: Ordered (ol), Unordered (ul), Definition (dl)", "Inserting images", "Creating links", "Tables: table, tr, th, td, border, colspan, rowspan", "CSS: Inline, Internal, External"),
                        importantConcepts = listOf("Writing complete HTML code for given tabular layout using colspan and rowspan", "CSS styling attributes", "Ordered vs Unordered list attributes"),
                        officialMarks = 25,
                        estimatedMarksDisplay = "25 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "25 Marks: 4 MCQs + 1 Short (3M) + 2 Webpage Coding Questions (5M each)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.HIGH,
                        examTips = "Design of a webpage table with colspan and rowspan is a guaranteed 5-mark question."
                    )
                )
            ),
            UnitWeightage(
                id = "ca_u3",
                unitNumber = "Unit III",
                name = "Cyber Ethics",
                officialMarks = 10,
                chapters = listOf(
                    Chapter(
                        id = "ca_ch3",
                        unitId = "ca_u3",
                        unitName = "Cyber Ethics",
                        unitOfficialMarks = 10,
                        number = 3,
                        name = "Cyber Ethics & Netiquette",
                        topics = listOf("Netiquette", "Software licenses and the open source movement", "Intellectual property rights (IPR), patents and copyright", "Freedom of information and the digital divide", "E-commerce: Privacy, fraud, secure data transmission"),
                        importantConcepts = listOf("Plagiarism and copyright infringement differences", "Open Source Software vs Proprietary software", "Digital divide causes and remedies"),
                        officialMarks = 10,
                        estimatedMarksDisplay = "10 Marks (Official)",
                        marksStatus = MarksStatus.OFFICIAL,
                        pyqFrequency = "10 Marks: 2 MCQs + 2 Short Questions (2M/3M)",
                        academicYear = ACADEMIC_YEAR,
                        source = SOURCE_REF,
                        priority = RevisionPriority.MEDIUM,
                        examTips = "Netiquette rules and Intellectual Property Rights definitions carry direct marks."
                    )
                )
            )
        ),
        questionTypology = QuestionTypology(50, 20, 30, "Theory Exam: 50 Marks. Practical Lab: 50 Marks (Lab test 20M, Report file 20M, Viva 10M)."),
        sourceTitle = "CBSE Class 10 Computer Applications (165) Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "Computer Applications (165) evaluates Networking (15M), HTML (25M), and Cyber Ethics (10M) in the 50-mark board theory paper."
    )
}
