package com.example.data.datasource

import com.example.data.model.*

object ScienceCurriculum {

    private const val ACADEMIC_YEAR = "2026–27"
    private const val SOURCE_REF = "CBSE Class 10 Science Curriculum 2026–27 (cbseacademic.nic.in)"

    private val scienceInternalComponents = listOf(
        AssessmentComponent("Periodic Tests (Pen-Paper)", 5, "Best 2 of 3 periodic tests conducted by school averaged and converted to 5 marks"),
        AssessmentComponent("Diverse Assessment", 5, "Quizzes, oral tests, concept maps, project assignments"),
        AssessmentComponent("Practical / Laboratory Work", 5, "Record of experimental work, observation notebooks, lab activity completion"),
        AssessmentComponent("Portfolio", 5, "Classwork notebooks, peer assessments, self-reflection records")
    )

    private val scienceUnits = listOf(
        UnitWeightage(
            id = "sci_u1",
            unitNumber = "Unit I",
            name = "Chemical Substances - Nature & Behaviour",
            officialMarks = 25,
            chapters = listOf(
                Chapter(
                    id = "sci_ch1",
                    unitId = "sci_u1",
                    unitName = "Chemical Substances",
                    unitOfficialMarks = 25,
                    number = 1,
                    name = "Chemical Reactions and Equations",
                    topics = listOf("Chemical equation balance", "Types of reactions (Combination, Decomposition, Displacement, Double displacement)", "Precipitation", "Endothermic & Exothermic", "Oxidation and reduction"),
                    importantConcepts = listOf("Balancing chemical equations by hit-and-trial with state symbols", "Redox identification (substance oxidized vs oxidizing agent)", "Thermal decomposition of FeSO4, Pb(NO3)2"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Short Question (3M) + 1 Assertion-Reason (1M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Thermal decomposition of lead nitrate with brown fumes of NO2 is a classic board question."
                ),
                Chapter(
                    id = "sci_ch2",
                    unitId = "sci_u1",
                    unitName = "Chemical Substances",
                    unitOfficialMarks = 25,
                    number = 2,
                    name = "Acids, Bases and Salts",
                    topics = listOf("pH scale concept", "Neutralisation", "Preparation and uses of Sodium Hydroxide, Bleaching powder, Baking soda, Washing soda, Plaster of Paris"),
                    importantConcepts = listOf("Chemical formulas and preparation equations for salts (Chlor-alkali process, POP dehydration/hydration)", "pH in everyday life (soil, tooth decay, digestive system)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~6 – 7 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "6–7 Marks: 1–2 MCQs (1M each) + 1 Long structured question (5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Chlor-alkali process and equations for preparation of bleaching powder and plaster of paris carry direct marks."
                ),
                Chapter(
                    id = "sci_ch3",
                    unitId = "sci_u1",
                    unitName = "Chemical Substances",
                    unitOfficialMarks = 25,
                    number = 3,
                    name = "Metals and Non-Metals",
                    topics = listOf("Properties of metals and non-metals", "Reactivity series", "Formation and properties of ionic compounds", "Basic metallurgical processes", "Corrosion and its prevention"),
                    importantConcepts = listOf("Electron dot structures of NaCl, MgCl2", "Roasting vs Calcination differences and equations", "Thermite reaction equation", "Reactivity series order"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~6 – 7 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "6–7 Marks: 1 MCQ (1M) + 1 Short (3M) + 1 Conceptual Reasoning (2M/3M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Practice drawing electron transfer Lewis dot diagrams for ionic compound formation."
                ),
                Chapter(
                    id = "sci_ch4",
                    unitId = "sci_u1",
                    unitName = "Chemical Substances",
                    unitOfficialMarks = 25,
                    number = 4,
                    name = "Carbon and Its Compounds",
                    topics = listOf("Covalent bonding in carbon compounds", "Versatile nature of carbon (Catenation, Tetravalency)", "Homologous series", "Nomenclature of carbon compounds", "Chemical properties (Combustion, Oxidation, Addition, Substitution)", "Ethanol and Ethanoic acid properties", "Soaps and detergents (Micelle formation)"),
                    importantConcepts = listOf("Saponification and Esterification reactions", "Micelle mechanism diagram with hydrophobic & hydrophilic ends", "Differentiating saturated vs unsaturated hydrocarbons (Bromine water test)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~7 – 8 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "7–8 Marks: 1 MCQ (1M) + 1 Case-Based Question (4M) or 5-Mark Long Answer",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Esterification reaction between ethanol and ethanoic acid, along with sweet smell identification, is high-yield."
                )
            )
        ),
        UnitWeightage(
            id = "sci_u2",
            unitNumber = "Unit II",
            name = "World of Living (Biology)",
            officialMarks = 25,
            chapters = listOf(
                Chapter(
                    id = "sci_ch5",
                    unitId = "sci_u2",
                    unitName = "World of Living",
                    unitOfficialMarks = 25,
                    number = 5,
                    name = "Life Processes",
                    topics = listOf("Nutrition (Autotrophic, Heterotrophic, Human digestive system)", "Respiration (Aerobic vs Anaerobic pathway of glucose breakdown)", "Transportation (Human circulatory system, double circulation, xylem & phloem in plants)", "Excretion (Structure of nephron, urine formation)"),
                    importantConcepts = listOf("3 pathways of glucose breakdown flowchart (cytoplasm to mitochondria/yeast/muscle)", "Labelled diagrams: Human Heart, Alimentary canal, Nephron", "Double circulation explanation with pulmonary & systemic circuit"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~8 – 9 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "8–9 Marks: 1 MCQ (1M) + 1 Short (2M) + 1 Long 5-Mark Question with Diagram",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "NCERT textbook diagram of the Nephron and Heart must be drawn with correct anatomical labels."
                ),
                Chapter(
                    id = "sci_ch6",
                    unitId = "sci_u2",
                    unitName = "World of Living",
                    unitOfficialMarks = 25,
                    number = 6,
                    name = "Control and Coordination",
                    topics = listOf("Tropic movements in plants", "Introduction to plant hormones (Auxin, Gibberellin, Cytokinin, Abscisic acid)", "Control and coordination in animals: Nervous system", "Reflex arc", "Endocrine glands and hormones in humans"),
                    importantConcepts = listOf("Reflex arc sequence: Receptor -> Sensory neuron -> Spinal cord -> Motor neuron -> Effector", "Phototropism mechanism via Auxin diffusion", "Endocrine table: Thyroid/Thyroxine, Pancreas/Insulin, Adrenal/Adrenaline"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Short (2M/3M reasoning)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "CBSE frequently asks why diabetic patients are treated with insulin injections and the role of iodised salt."
                ),
                Chapter(
                    id = "sci_ch7",
                    unitId = "sci_u2",
                    unitName = "World of Living",
                    unitOfficialMarks = 25,
                    number = 7,
                    name = "How do Organisms Reproduce?",
                    topics = listOf("Asexual reproduction (Binary fission, Multiple fission, Fragmentation, Regeneration, Budding, Vegetative propagation, Spore formation)", "Sexual reproduction in flowering plants (Pollination, Double fertilisation)", "Human reproductive system (Male, Female, Menstruation, Contraception)"),
                    importantConcepts = listOf("Diagram of flower with pistil & stamen", "Pollen tube growth diagram through style into ovary", "Contraceptive methods: Barrier, Chemical, Surgical (Vasectomy, Tubectomy)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~6 – 7 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "6–7 Marks: 1 MCQ (1M) + 1 Long 5-Mark Question on Flower / Human Reproduction",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Clear diagrams of longitudinal section of flower and germination of pollen on stigma are tested repeatedly."
                ),
                Chapter(
                    id = "sci_ch8",
                    unitId = "sci_u2",
                    unitName = "World of Living",
                    unitOfficialMarks = 25,
                    number = 8,
                    name = "Heredity",
                    topics = listOf("Mendel’s contribution - Laws for inheritance of traits", "Monohybrid cross (3:1 phenotypic ratio)", "Dihybrid cross (9:3:3:1 ratio)", "Sex determination in human beings"),
                    importantConcepts = listOf("Punnett squares for monohybrid (Tt × Tt) and dihybrid crosses", "Sex determination in humans diagram (XX vs XY, 50% probability)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~4 – 5 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "4–5 Marks: 1 MCQ (1M) + 1 Case Study (4M) or 3-Mark Cross Explanation",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.MEDIUM,
                    examTips = "Note: Evolution section is deleted for the 2026–27 CBSE syllabus. Focus purely on Mendel's crosses and sex determination!"
                )
            )
        ),
        UnitWeightage(
            id = "sci_u3",
            unitNumber = "Unit III",
            name = "Natural Phenomena (Physics Optics)",
            officialMarks = 12,
            chapters = listOf(
                Chapter(
                    id = "sci_ch9",
                    unitId = "sci_u3",
                    unitName = "Natural Phenomena",
                    unitOfficialMarks = 12,
                    number = 9,
                    name = "Light - Reflection and Refraction",
                    topics = listOf("Reflection of light by curved surfaces", "Images formed by spherical mirrors (concave, convex)", "Centre of curvature, principal axis, focal length", "Mirror formula and magnification", "Refraction: Laws of refraction, refractive index", "Refraction by spherical lenses", "Lens formula, magnification, power of a lens"),
                    importantConcepts = listOf("Ray diagrams for concave mirror (all 6 positions) and convex lens", "Sign convention rules (+ and - for u, v, f)", "Numerical problems solving for v, h', and power P = 1/f (in meters)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~8 – 9 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "8–9 Marks: 1 MCQ (1M) + 1 Numerical (3M) + 1 Ray Diagram (3M/5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Ray diagrams must include arrows indicating direction of light propagation; omit arrows and lose marks."
                ),
                Chapter(
                    id = "sci_ch10",
                    unitId = "sci_u3",
                    unitName = "Natural Phenomena",
                    unitOfficialMarks = 12,
                    number = 10,
                    name = "The Human Eye & Colourful World",
                    topics = listOf("Functioning of a lens in human eye", "Defects of vision and their correction (Myopia, Hypermetropia, Presbyopia)", "Refraction of light through a prism", "Dispersion of light", "Scattering of light, applications in daily life (Tyndall effect, blue sky, reddish sunset)"),
                    importantConcepts = listOf("Ray diagrams showing defective eye and corrective lens for Myopia (concave) & Hypermetropia (convex)", "Atmospheric refraction phenomena (Twinkling of stars, Advanced sunrise & delayed sunset)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~3 – 4 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "3–4 Marks: 1 MCQ (1M) + 1 Ray Diagram (2M/3M on defect correction)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.MEDIUM,
                    examTips = "Be ready to calculate the focal length and power of the corrective lens required for a person with myopic far point."
                )
            )
        ),
        UnitWeightage(
            id = "sci_u4",
            unitNumber = "Unit IV",
            name = "Effects of Current (Physics Electricity)",
            officialMarks = 13,
            chapters = listOf(
                Chapter(
                    id = "sci_ch11",
                    unitId = "sci_u4",
                    unitName = "Effects of Current",
                    unitOfficialMarks = 13,
                    number = 11,
                    name = "Electricity",
                    topics = listOf("Electric current, potential difference, electric circuit", "Ohm’s law (V = IR)", "Factors on which resistance depends (R = ρl/A)", "Resistors in series and parallel", "Heating effect of electric current (Joule’s Law H = I²Rt)", "Electric power (P = VI = I²R = V²/R)"),
                    importantConcepts = listOf("Circuit numericals calculating equivalent resistance and current in branches", "Resistivity formula applications with wire stretching/compression", "Commercial unit of electrical energy (1 kWh = 3.6 × 10⁶ J)"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~7 – 8 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "7–8 Marks: 1 MCQ (1M) + 1 Short (2M) + 1 Circuit Numerical (4M/5M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Wire stretching numericals: When wire length is doubled by stretching, area halves, so resistance becomes 4× original."
                ),
                Chapter(
                    id = "sci_ch12",
                    unitId = "sci_u4",
                    unitName = "Effects of Current",
                    unitOfficialMarks = 13,
                    number = 12,
                    name = "Magnetic Effects of Electric Current",
                    topics = listOf("Magnetic field and field lines around a straight conductor, circular loop, and solenoid", "Force on a current-carrying conductor in a magnetic field", "Fleming’s Left-Hand Rule", "Domestic electric circuits (Live, neutral, earth wire, overloading, short circuiting)"),
                    importantConcepts = listOf("Properties of magnetic field lines", "Fleming's Left Hand Rule application to find direction of force on charge/conductor", "Earth wire role and fuse safety function in domestic circuits"),
                    officialMarks = null,
                    estimatedMarksDisplay = "~5 – 6 Marks (Est.)",
                    marksStatus = MarksStatus.HISTORICAL_TREND,
                    pyqFrequency = "5–6 Marks: 1 MCQ (1M) + 1 Diagram / Reasoning Question (3M/4M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "Note: Electric Motor and Electromagnetic Induction / AC Generator were deleted in recent CBSE rationalized syllabus."
                )
            )
        ),
        UnitWeightage(
            id = "sci_u5",
            unitNumber = "Unit V",
            name = "Natural Resources (Environmental Science)",
            officialMarks = 5,
            chapters = listOf(
                Chapter(
                    id = "sci_ch13",
                    unitId = "sci_u5",
                    unitName = "Natural Resources",
                    unitOfficialMarks = 5,
                    number = 13,
                    name = "Our Environment",
                    topics = listOf("Eco-system (Biotic and abiotic components)", "Food chains and webs", "Trophic levels", "10 Percent Law of energy transfer", "Biological magnification", "Ozone layer depletion (CFCs role)", "Waste management (Biodegradable and non-biodegradable substances)"),
                    importantConcepts = listOf("10% Law energy calculations across successive trophic levels", "Biological magnification definition with pesticide concentration example", "Ozone breakdown chemical equation (O + O2 -> O3, and CFC chlorine radical impact)"),
                    officialMarks = 5,
                    estimatedMarksDisplay = "5 Marks (Official)",
                    marksStatus = MarksStatus.OFFICIAL,
                    pyqFrequency = "5 Marks: 1 MCQ (1M) + 1 Short Question (3M) + 1 Assertion-Reason (1M)",
                    academicYear = ACADEMIC_YEAR,
                    source = SOURCE_REF,
                    priority = RevisionPriority.HIGH,
                    examTips = "100% predictable 5 marks! High scoring chapter. Master 10% law calculations and biomagnification definition."
                )
            )
        )
    )

    fun getScience(): Subject = Subject(
        id = "sci_086",
        code = "086",
        name = "Science",
        category = SubjectCategory.COMPULSORY,
        assessmentStructure = AssessmentStructure.STANDARD_80_20,
        academicYear = ACADEMIC_YEAR,
        assessmentComponents = scienceInternalComponents,
        units = scienceUnits,
        questionTypology = QuestionTypology(50, 20, 30, "Section A: 20 Objective MCQs (including Assertion-Reason); Section B: 6 Very Short (2 marks each); Section C: 7 Short (3 marks each); Section D: 3 Long (5 marks each); Section E: 3 Source/Case-based (4 marks each)."),
        sourceTitle = "CBSE Class 10 Science Curriculum 2026–27",
        sourceUrl = "https://cbseacademic.nic.in/curriculum_2026.html",
        publicationDate = "CBSE Session 2026–27",
        lastVerifiedDate = "Official 2026–27 Session",
        officialNotice = "CBSE officially fixes unit-wise marks: Chemistry (25m), Biology (25m), Optics (12m), Electricity & Magnetism (13m), Natural Resources (5m). Total = 80 marks. Rationalized syllabus (Periodic Classification and Sources of Energy removed)."
    )
}
