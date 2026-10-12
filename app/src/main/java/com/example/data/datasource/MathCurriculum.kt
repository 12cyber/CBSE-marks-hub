package com.example.data.datasource

import com.example.data.model.*

object MathCurriculum {

    private const val ACADEMIC_YEAR = "2026–27"
    private const val SOURCE_REF = "CBSE Class 10 Curriculum 2026–27 (cbseacademic.nic.in)"

    private val mathInternalComponents = listOf(
        AssessmentComponent("Pen Paper Test & Multiple Assessment", 10, "Best 2 of 3 periodic tests (5 marks) + Quizzes/Oral/Concept tests (5 marks)"),
        AssessmentComponent("Portfolio", 5, "Classwork notebook maintenance, assignments completion, student work showcase"),
        AssessmentComponent("Lab Practical (Subject Enrichment)", 5, "Mathematics laboratory practical activities (minimum 10 activities) & viva voce")
    )

    private val mathUnits = listOf(
        UnitWeightage(
            id = "math_u1",
            unitNumber = "Unit I",
            name = "Number Systems",
            officialMarks = 6,
            chapters = listOf(
                Chapter(
                    id = "math_ch1",
                    unitId = "math_u1",
                    unitName = "Number Systems",
                    unitOfficialMarks = 6,
                    number = 1,
                    name = "Real Numbers",
                    topics = listOf("Fundamental Theorem of Arithmetic", "Proofs of irrationality of √2, √3, √5", "LCM & HCF applications"),
                    importantConcepts = listOf("Prime Factorization", "Proving irrational numbers (contradiction method)", "Product of two numbers = HCF × LCM"),
                    officialMarks = 6,
                    estimatedMarksDisplay = "6 Marks (Official)",
                    marksStatus = MarksStatus.OFFICIAL,
                    pyqFrequency = "6 Marks: 1 MCQ (1M) + 1 Short Question (2M or 3M) + 1 Assertion-Reason (1M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Standard question on proving √3 or (3 + 2√5) is irrational appears in almost every board paper set."
                )
            )
        ),
        UnitWeightage(
            id = "math_u2",
            unitNumber = "Unit II",
            name = "Algebra",
            officialMarks = 20,
            chapters = listOf(
                Chapter(
                    id = "math_ch2",
                    unitId = "math_u2",
                    unitName = "Algebra",
                    unitOfficialMarks = 20,
                    number = 2,
                    name = "Polynomials",
                    topics = listOf("Zeros of a polynomial", "Relationship between zeros and coefficients of quadratic polynomials"),
                    importantConcepts = listOf("Sum of roots α + β = -b/a", "Product of roots αβ = c/a", "Forming polynomial k(x² - (α+β)x + αβ)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~4 – 5 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "4 Marks: 1 MCQ (1M) + 1 Short Question (3M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.MEDIUM,
                    examTips = "Focus on evaluating expressions like α² + β² or 1/α + 1/β using symmetric relations."
                ),
                Chapter(
                    id = "math_ch3",
                    unitId = "math_u2",
                    unitName = "Algebra",
                    unitOfficialMarks = 20,
                    number = 3,
                    name = "Pair of Linear Equations in Two Variables",
                    topics = listOf("Graphical method of solution", "Consistency/inconsistency conditions", "Algebraic methods: Substitution and Elimination"),
                    importantConcepts = listOf("Conditions for unique, infinitely many, or no solutions (a1/a2 ratios)", "Word problems (speed-distance, fractions, age)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Short/Long Answer (3M/4M or Case Study)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Check consistency condition questions in Section A MCQs; practice upstream/downstream word problems."
                ),
                Chapter(
                    id = "math_ch4",
                    unitId = "math_u2",
                    unitName = "Algebra",
                    unitOfficialMarks = 20,
                    number = 4,
                    name = "Quadratic Equations",
                    topics = listOf("Standard form ax² + bx + c = 0", "Solutions by factorisation & Quadratic Formula", "Nature of roots discriminant D = b² - 4ac"),
                    importantConcepts = listOf("Discriminant conditions (D > 0, D = 0, D < 0)", "Real and equal roots condition b² - 4ac = 0 to find unknown k"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Long Question (4M/5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Questions finding 'k' when roots are equal are guaranteed; solve speed-distance quadratics."
                ),
                Chapter(
                    id = "math_ch5",
                    unitId = "math_u2",
                    unitName = "Algebra",
                    unitOfficialMarks = 20,
                    number = 5,
                    name = "Arithmetic Progressions",
                    topics = listOf("nth term of an AP (an = a + (n-1)d)", "Sum of first n terms (Sn = n/2[2a + (n-1)d])", "Real-life applications"),
                    importantConcepts = listOf("General term formulas", "Sum formulas and relation an = Sn - S(n-1)", "Word problems on AP sequences"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~4 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "4–6 Marks: 1 MCQ (1M) + 1 Case-Based Study (4M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "CBSE frequently frames 4-mark case study questions around daily life sequences using AP formulas."
                )
            )
        ),
        UnitWeightage(
            id = "math_u3",
            unitNumber = "Unit III",
            name = "Coordinate Geometry",
            officialMarks = 6,
            chapters = listOf(
                Chapter(
                    id = "math_ch6",
                    unitId = "math_u3",
                    unitName = "Coordinate Geometry",
                    unitOfficialMarks = 6,
                    number = 7,
                    name = "Coordinate Geometry",
                    topics = listOf("Distance Formula", "Section Formula (internal division)", "Midpoint formula"),
                    importantConcepts = listOf("Distance between points √((x2-x1)² + (y2-y1)²)", "Section formula: (mx2+nx1)/(m+n), (my2+ny1)/(m+n)", "Ratio determination k:1"),
                    officialMarks = 6,
                    estimatedMarksDisplay = "6 Marks (Official)",
                    marksStatus = MarksStatus.OFFICIAL,
                    pyqFrequency = "6 Marks: 1 MCQ (1M) + 1 Short (2M/3M) or 1 Case-Based Question (4M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Points of trisection and finding ratio k:1 where x-axis or y-axis divides a line segment are classic favorites."
                )
            )
        ),
        UnitWeightage(
            id = "math_u4",
            unitNumber = "Unit IV",
            name = "Geometry",
            officialMarks = 15,
            chapters = listOf(
                Chapter(
                    id = "math_ch7",
                    unitId = "math_u4",
                    unitName = "Geometry",
                    unitOfficialMarks = 15,
                    number = 6,
                    name = "Triangles",
                    topics = listOf("Basic Proportionality Theorem (Thales Theorem)", "Converse of BPT", "Criteria for similarity of triangles (AAA, SAS, SSS)"),
                    importantConcepts = listOf("BPT Theorem Proof (Mandatory for 5 marks)", "Similarity ratio applications in problem-solving"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~8 – 9 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "8–9 Marks: 1 MCQ (1M) + 1 Short (2M) + 1 Long Proof (5M Thales Theorem)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "The formal proof of the Basic Proportionality Theorem is one of the highest probability 5-mark questions."
                ),
                Chapter(
                    id = "math_ch8",
                    unitId = "math_u4",
                    unitName = "Geometry",
                    unitOfficialMarks = 15,
                    number = 10,
                    name = "Circles",
                    topics = listOf("Tangent to a circle at point of contact", "Theorems on lengths of tangents drawn from an external point"),
                    importantConcepts = listOf("Tangent is perpendicular to radius at point of contact", "Lengths of tangents from external point are equal (Theorem 10.2 proof)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~6 – 7 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "6–7 Marks: 1–2 MCQs (1M each) + 1 Short Proof (3M) or 1 Long (4M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Memorize the proof that tangents drawn from an external point to a circle are equal in length."
                )
            )
        ),
        UnitWeightage(
            id = "math_u5",
            unitNumber = "Unit V",
            name = "Trigonometry",
            officialMarks = 12,
            chapters = listOf(
                Chapter(
                    id = "math_ch9",
                    unitId = "math_u5",
                    unitName = "Trigonometry",
                    unitOfficialMarks = 12,
                    number = 8,
                    name = "Introduction to Trigonometry",
                    topics = listOf("Trigonometric ratios of acute angles", "Values of ratios at 0°, 30°, 45°, 60°, 90°", "Proof and application of identity sin²θ + cos²θ = 1"),
                    importantConcepts = listOf("Standard ratio table memorization", "Fundamental identities (1 + tan²θ = sec²θ, 1 + cot²θ = cosec²θ)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~7 – 8 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "7–8 Marks: 2 MCQs (2M) + 1 Short (2M) + 1 Identity Proof (3M/4M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Practice proving complex algebraic trigonometric identities using LHS to RHS transformations."
                ),
                Chapter(
                    id = "math_ch10",
                    unitId = "math_u5",
                    unitName = "Trigonometry",
                    unitOfficialMarks = 12,
                    number = 9,
                    name = "Some Applications of Trigonometry",
                    topics = listOf("Angle of elevation and depression", "Simple problems on heights and distances with angles 30°, 45°, 60°"),
                    importantConcepts = listOf("Draw clean geometric diagrams with line of sight", "Two-triangle height and distance scenarios"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~4 – 5 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "4–5 Marks: 1 Case-Based Study (4M) OR 1 Long Question (5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Drawing an accurate labelled diagram carries 1 mark. Angles must not exceed 30°, 45°, and 60° as per CBSE syllabus."
                )
            )
        ),
        UnitWeightage(
            id = "math_u6",
            unitNumber = "Unit VI",
            name = "Mensuration",
            officialMarks = 10,
            chapters = listOf(
                Chapter(
                    id = "math_ch11",
                    unitId = "math_u6",
                    unitName = "Mensuration",
                    unitOfficialMarks = 10,
                    number = 11,
                    name = "Areas Related to Circles",
                    topics = listOf("Area of sectors and segments of a circle", "Problems on finding areas with central angles 60°, 90°, 120°"),
                    importantConcepts = listOf("Sector Area: (θ/360) × πr²", "Segment Area = Sector Area - Triangle Area", "Arc length: (θ/360) × 2πr"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~4 – 5 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "4–5 Marks: 1 MCQ (1M) + 1 Short Answer (3M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.MEDIUM,
                    examTips = "Be careful with units (cm vs m) and whether question specifies π = 22/7 or 3.14."
                ),
                Chapter(
                    id = "math_ch12",
                    unitId = "math_u6",
                    unitName = "Mensuration",
                    unitOfficialMarks = 10,
                    number = 12,
                    name = "Surface Areas and Volumes",
                    topics = listOf("Surface areas and volumes of combinations of two solids (cubes, cuboids, spheres, hemispheres, right circular cylinders/cones)"),
                    importantConcepts = listOf("Total surface area of combined shapes (do NOT add buried surfaces)", "Volume conservation upon reshaping"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Long Question (5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Common error: Adding base areas when two solids are glued together. Only calculate exposed outer surface area!"
                )
            )
        ),
        UnitWeightage(
            id = "math_u7",
            unitNumber = "Unit VII",
            name = "Statistics and Probability",
            officialMarks = 11,
            chapters = listOf(
                Chapter(
                    id = "math_ch13",
                    unitId = "math_u7",
                    unitName = "Statistics & Probability",
                    unitOfficialMarks = 11,
                    number = 13,
                    name = "Statistics",
                    topics = listOf("Mean of grouped data (Direct and Assumed Mean Method)", "Median of grouped data", "Mode of grouped data"),
                    importantConcepts = listOf("Step-by-step formula application for Median = l + [(n/2 - cf)/f] × h", "Mode = l + [(f1 - f0)/(2f1 - f0 - f2)] × h", "Empirical relation: 3 Median = Mode + 2 Mean"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~7 – 8 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "7–8 Marks: 1 MCQ (1M) + 1 Long Answer (5M on missing frequencies)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Finding missing frequencies x and y when median or mean is given is a very recurring 5-mark question."
                ),
                Chapter(
                    id = "math_ch14",
                    unitId = "math_u7",
                    unitName = "Statistics & Probability",
                    unitOfficialMarks = 11,
                    number = 14,
                    name = "Probability",
                    topics = listOf("Classical definition of probability", "Simple problems on finding the probability of an event"),
                    importantConcepts = listOf("P(E) = favorable outcomes / total outcomes", "0 ≤ P(E) ≤ 1", "P(E) + P(not E) = 1", "Coins, dice, and deck of 52 playing cards sample spaces"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~3 – 4 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "3–4 Marks: 1 MCQ (1M) + 1 Short Question (2M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.MEDIUM,
                    examTips = "Master card classifications (12 face cards, 26 red, 26 black, 4 suits of 13 each) and sum of two dice possibilities."
                )
            )
        )
    )

    fun getMathStandard(): Subject = Subject(
        id = "math_041",
        code = "041",
        name = "Mathematics Standard",
        category = SubjectCategory.COMPULSORY,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = mathInternalComponents,
        units = mathUnits,
        questionTypology = QuestionTypology(50, 20, 30, "50% Competency-focused questions (Case-based, Assertion-Reason, conceptual), 20% MCQs, 30% Short/Long answer steps. Emphasizes proofs and analytical reasoning."),
        sourceTitle = "CBSE Class 10 Mathematics Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "CBSE officially mandates unit-wise weightage totaling 80 marks. Chapters with [OFFICIAL] tags form sole-chapter units. Other chapter marks are historical examination estimates."
    )

    fun getMathBasic(): Subject = Subject(
        id = "math_241",
        code = "241",
        name = "Mathematics Basic",
        category = SubjectCategory.COMPULSORY,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = mathInternalComponents,
        units = mathUnits,
        questionTypology = QuestionTypology(50, 20, 30, "Shares the identical syllabus and 80-mark unit-wise weightage as Math Standard, but questions focus primarily on direct NCERT formula application, basic definitions, and familiar textbook models rather than complex derivations."),
        sourceTitle = "CBSE Class 10 Mathematics Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "Mathematics Basic has the exact same syllabus and unit weightage as Mathematics Standard. The difference lies in question difficulty level and directness of problems."
    )
}
