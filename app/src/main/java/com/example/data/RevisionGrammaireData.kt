package com.example.data

// Models for Revision Grammaire

data class RevisionGrammarExercise(
    val id: Int,
    val pageNumber: String,
    val titleFr: String,
    val titleAr: String,
    val instructionFr: String,
    val instructionAr: String,
    val ruleSummaryAr: String,
    val items: List<RevisionGrammarItem>
)

data class RevisionGrammarItem(
    val id: String,
    val sentenceFr: String,
    val sentenceAr: String,
    val underlinedPart: String = "",
    val options: List<String> = emptyList(),
    val correctIndex: Int = -1,
    val answerFr: String,
    val fullTransformedSentenceFr: String = "",
    val fullTransformedSentenceAr: String = "",
    val explanationAr: String
)

object RevisionGrammaireData {

    // =========================================================================
    // EXERCISE 1 - Page 72: La négation (النفي)
    // =========================================================================
    val exercise1Negation = RevisionGrammarExercise(
        id = 1,
        pageNumber = "p. 72",
        titleFr = "1) Mets les phrases à la forme négative",
        titleAr = "التمرين 1 : حوّل الجمل التالية إلى صيغة النفي",
        instructionFr = "1) Mets les phrases suivantes à la forme négative :",
        instructionAr = "ضع الجمل الآتية في صيغة النفي مع الانتباه لقاعدة تحويل أدوات النكرة (un, une, des) إلى (de / d') ما عدا مع فعل être:",
        ruleSummaryAr = "📌 قاعدة النفي الأساسية:\n• نضع الفعل بين (ne ... pas) أو (n' ... pas أمام حرف متحرك).\n• أدوات النكرة (un, une, des) وأدوات التجزئة تتحول إلى (de / d') عند النفي، ما عدا إذا كان فعل الجملة هو (être) فتبقى كما هي بدون تغيير!",
        items = listOf(
            RevisionGrammarItem(
                id = "neg_1",
                sentenceFr = "1. Sophie aime les animaux.",
                sentenceAr = "سوفي تحب الحيوانات.",
                answerFr = "Sophie n'aime pas les animaux.",
                fullTransformedSentenceFr = "Sophie n'aime pas les animaux.",
                fullTransformedSentenceAr = "سوفي لا تحب الحيوانات.",
                explanationAr = "الفعل يبدأ بحرف متحرك (aime) فنضع (n' ... pas). أداة المعرفة (les) لا تتغير عند النفي."
            ),
            RevisionGrammarItem(
                id = "neg_2",
                sentenceFr = "2. Il a un chat.",
                sentenceAr = "هو يمتلك قطاً.",
                answerFr = "Il n'a pas de chat.",
                fullTransformedSentenceFr = "Il n'a pas de chat.",
                fullTransformedSentenceAr = "هو لا يمتلك قطاً.",
                explanationAr = "تحولت أداة النكرة (un) إلى (de) لوجود النفي مع فعل avoir."
            ),
            RevisionGrammarItem(
                id = "neg_3",
                sentenceFr = "3. Ce sont des élèves.",
                sentenceAr = "هؤلاء تلاميذ.",
                answerFr = "Ce ne sont pas des élèves.",
                fullTransformedSentenceFr = "Ce ne sont pas des élèves.",
                fullTransformedSentenceAr = "هؤلاء ليسوا تلاميذ.",
                explanationAr = "انتبه! فعل الجملة هو (être)، لذلك أداة النكرة (des) تظل كما هي ولا تتحول إلى de."
            ),
            RevisionGrammarItem(
                id = "neg_4",
                sentenceFr = "4. Les filles parlent en classe.",
                sentenceAr = "البنات يتحدثن في الفصل.",
                answerFr = "Les filles ne parlent pas en classe.",
                fullTransformedSentenceFr = "Les filles ne parlent pas en classe.",
                fullTransformedSentenceAr = "البنات لا يتحدثن في الفصل.",
                explanationAr = "نفي بسيط بوضع الفعل بين (ne ... pas)."
            ),
            RevisionGrammarItem(
                id = "neg_5",
                sentenceFr = "5. Omar et Sami ont des examens.",
                sentenceAr = "عمر وسامي لديهم امتحانات.",
                answerFr = "Omar et Sami n'ont pas d'examens.",
                fullTransformedSentenceFr = "Omar et Sami n'ont pas d'examens.",
                fullTransformedSentenceAr = "عمر وسامي ليس لديهم امتحانات.",
                explanationAr = "الفعل (ont) يبدأ بمتحرك فنضع (n' ... pas)، وأداة النكرة (des) تحولت إلى (d') لأن كلمة examens تبدأ بحرف متحرك."
            ),
            RevisionGrammarItem(
                id = "neg_6",
                sentenceFr = "6. J'ai un cartable.",
                sentenceAr = "أنا لدي حقيبة مدرسية.",
                answerFr = "Je n'ai pas de cartable.",
                fullTransformedSentenceFr = "Je n'ai pas de cartable.",
                fullTransformedSentenceAr = "أنا ليس لدي حقيبة مدرسية.",
                explanationAr = "الفعل (ai) وُضع بين (n' ... pas)، وتحولت أداة النكرة (un) إلى (de)."
            ),
            RevisionGrammarItem(
                id = "neg_7",
                sentenceFr = "7. C'est un garçon.",
                sentenceAr = "هذا ولد.",
                answerFr = "Ce n'est pas un garçon.",
                fullTransformedSentenceFr = "Ce n'est pas un garçon.",
                fullTransformedSentenceAr = "هذا ليس ولداً.",
                explanationAr = "مع فعل الكينونة être (c'est -> ce n'est pas) لا تتغير أداة النكرة (un) وتبقى كما هي."
            ),
            RevisionGrammarItem(
                id = "neg_8",
                sentenceFr = "8. Mon petit frère a un chien.",
                sentenceAr = "أخي الصغير لديه كلب.",
                answerFr = "Mon petit frère n'a pas de chien.",
                fullTransformedSentenceFr = "Mon petit frère n'a pas de chien.",
                fullTransformedSentenceAr = "أخي الصغير ليس لديه كلب.",
                explanationAr = "تحولت أداة النكرة (un) إلى (de) في النفي."
            ),
            RevisionGrammarItem(
                id = "neg_9",
                sentenceFr = "9. La petite fille a un ordinateur.",
                sentenceAr = "البنت الصغيرة لديها جهاز كمبيوتر.",
                answerFr = "La petite fille n'a pas d'ordinateur.",
                fullTransformedSentenceFr = "La petite fille n'a pas d'ordinateur.",
                fullTransformedSentenceAr = "البنت الصغيرة ليس لديها جهاز كمبيوتر.",
                explanationAr = "تحولت (un) إلى (d') لأن كلمة ordinateur تبدأ بحرف متحرك."
            ),
            RevisionGrammarItem(
                id = "neg_10",
                sentenceFr = "10. Je suis un bon élève.",
                sentenceAr = "أنا تلميذ مجتهد.",
                answerFr = "Je ne suis pas un bon élève.",
                fullTransformedSentenceFr = "Je ne suis pas un bon élève.",
                fullTransformedSentenceAr = "أنا لست تلميذاً مجتهداً.",
                explanationAr = "الفعل هو (suis) من أفعال الكينونة (être)، لذا تبقى أداة النكرة (un) كما هي دون تحويل."
            )
        )
    )

    // =========================================================================
    // EXERCISE 2 - Page 73: Les articles contractés (أدوات الإدغام مع حرف الجر à)
    // =========================================================================
    val exercise2ArticlesContractes = RevisionGrammarExercise(
        id = 2,
        pageNumber = "p. 73",
        titleFr = "2) Complète avec un article contracté",
        titleAr = "التمرين 2 : أكمل بأداة إدغام مناسبة (au / à la / à l' / aux / à)",
        instructionFr = "2) Complète avec un article contracté : (au, à la, à l', aux, à)",
        instructionAr = "اختر أداة الإدغام أو حرف الجر المناسب للمكان أو المدينة:",
        ruleSummaryAr = "📌 قاعدة أدوات الإدغام مع المكان:\n• au = اسم مفرد مذكر (au zoo, au musée, au marché, au Caire, au Fayoum).\n• à la = اسم مفرد مؤنث (à la gare, à la maison, à la tour, à la poste).\n• à l' = اسم مفرد يبدأ بمتحرك أو h صامتة (à l'école, à l'hôtel, à l'hôpital).\n• aux = اسم جمع بنوعيه (aux pyramides).\n• à = أمام معظم أسماء المدن والمحافظات (à Alexandrie, à Tanta, à Paris).",
        items = listOf(
            RevisionGrammarItem(
                id = "art_1",
                sentenceFr = "1. Je vais ............ école.",
                sentenceAr = "أنا أذهب إلى المدرسة.",
                options = listOf("au", "à la", "à l'"),
                correctIndex = 2,
                answerFr = "à l'",
                fullTransformedSentenceFr = "Je vais à l'école.",
                explanationAr = "كلمة école مفرد مؤنث تبدأ بحرف متحرك (é) فنأخذ (à l')."
            ),
            RevisionGrammarItem(
                id = "art_2",
                sentenceFr = "2. Tu vas ............ magasin.",
                sentenceAr = "أنت تذهب إلى المحل / المتجر.",
                options = listOf("à l'", "à la", "au"),
                correctIndex = 2,
                answerFr = "au",
                fullTransformedSentenceFr = "Tu vas au magasin.",
                explanationAr = "كلمة magasin مفرد مذكر تبدأ بحرف ساكن فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_3",
                sentenceFr = "3. Elle va ............ pharmacie.",
                sentenceAr = "هي تذهب إلى الصيدلية.",
                options = listOf("au", "à l'", "à la"),
                correctIndex = 2,
                answerFr = "à la",
                fullTransformedSentenceFr = "Elle va à la pharmacie.",
                explanationAr = "كلمة pharmacie مفرد مؤنث ساكن فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_4",
                sentenceFr = "4. Nous allons ............ citadelle.",
                sentenceAr = "نحن نذهب إلى القلعة.",
                options = listOf("au", "à la", "aux"),
                correctIndex = 1,
                answerFr = "à la",
                fullTransformedSentenceFr = "Nous allons à la citadelle.",
                explanationAr = "كلمة citadelle مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_5",
                sentenceFr = "5. Vous allez ............ zoo.",
                sentenceAr = "أنتم تذهبون إلى حديقة الحيوان.",
                options = listOf("à la", "à l'", "au"),
                correctIndex = 2,
                answerFr = "au",
                fullTransformedSentenceFr = "Vous allez au zoo.",
                explanationAr = "كلمة zoo مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_6",
                sentenceFr = "6. Il va ............ hôtel.",
                sentenceAr = "هو يذهب إلى الفندق.",
                options = listOf("à l'", "au", "à la"),
                correctIndex = 0,
                answerFr = "à l'",
                fullTransformedSentenceFr = "Il va à l'hôtel.",
                explanationAr = "كلمة hôtel تبدأ بحرف h صامت (h muet) فنختار (à l')."
            ),
            RevisionGrammarItem(
                id = "art_7",
                sentenceFr = "7. Ils vont ............ musée.",
                sentenceAr = "هم يذهبون إلى المتحف.",
                options = listOf("au", "à la", "à l'"),
                correctIndex = 0,
                answerFr = "au",
                fullTransformedSentenceFr = "Ils vont au musée.",
                explanationAr = "كلمة musée مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_8",
                sentenceFr = "8. Je vais ............ pyramides.",
                sentenceAr = "أنا أذهب إلى الأهرامات.",
                options = listOf("au", "aux", "à la"),
                correctIndex = 1,
                answerFr = "aux",
                fullTransformedSentenceFr = "Je vais aux pyramides.",
                explanationAr = "كلمة pyramides جمع (تنتهي بـ s) فنختار (aux)."
            ),
            RevisionGrammarItem(
                id = "art_9",
                sentenceFr = "9. Adham va ............ tour.",
                sentenceAr = "أدهم يذهب إلى البرج (برج القاهرة).",
                options = listOf("à la", "à l'", "au"),
                correctIndex = 0,
                answerFr = "à la",
                fullTransformedSentenceFr = "Adham va à la tour.",
                explanationAr = "كلمة tour (برج) مفرد مؤنث فنختار (à la tour)."
            ),
            RevisionGrammarItem(
                id = "art_10",
                sentenceFr = "10. Je vais ............ gare.",
                sentenceAr = "أنا أذهب إلى محطة القطار.",
                options = listOf("au", "à l'", "à la"),
                correctIndex = 2,
                answerFr = "à la",
                fullTransformedSentenceFr = "Je vais à la gare.",
                explanationAr = "كلمة gare مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_11",
                sentenceFr = "11. Vous allez ............ Fayoum.",
                sentenceAr = "أنتم تذهبون إلى الفيوم.",
                options = listOf("à", "à la", "au"),
                correctIndex = 2,
                answerFr = "au",
                fullTransformedSentenceFr = "Vous allez au Fayoum.",
                explanationAr = "محافظة الفيوم مذكرة وتأخذ أداة الإدغام (au Fayoum)."
            ),
            RevisionGrammarItem(
                id = "art_12",
                sentenceFr = "12. Ils vont ............ marché.",
                sentenceAr = "هم يذهبون إلى السوق.",
                options = listOf("au", "à l'", "à la"),
                correctIndex = 0,
                answerFr = "au",
                fullTransformedSentenceFr = "Ils vont au marché.",
                explanationAr = "كلمة marché مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_13",
                sentenceFr = "13. Vous allez ............ Tanta.",
                sentenceAr = "أنتم تذهبون إلى طنطا.",
                options = listOf("en", "à", "au"),
                correctIndex = 1,
                answerFr = "à",
                fullTransformedSentenceFr = "Vous allez à Tanta.",
                explanationAr = "طنطا مدينة عادية تسبق بحرف الجر (à)."
            ),
            RevisionGrammarItem(
                id = "art_14",
                sentenceFr = "14. Je vais ............ aéroport.",
                sentenceAr = "أنا أذهب إلى المطار.",
                options = listOf("à la", "à l'", "au"),
                correctIndex = 1,
                answerFr = "à l'",
                fullTransformedSentenceFr = "Je vais à l'aéroport.",
                explanationAr = "كلمة aéroport تبدأ بحرف متحرك (a) فنختار (à l')."
            ),
            RevisionGrammarItem(
                id = "art_15",
                sentenceFr = "15. Nous allons ............ hôpital.",
                sentenceAr = "نحن نذهب إلى المستشفى.",
                options = listOf("au", "à l'", "à la"),
                correctIndex = 1,
                answerFr = "à l'",
                fullTransformedSentenceFr = "Nous allons à l'hôpital.",
                explanationAr = "كلمة hôpital تبدأ بحرف h صامت فنختار (à l')."
            ),
            RevisionGrammarItem(
                id = "art_16",
                sentenceFr = "16. Je vais ............ Alexandrie.",
                sentenceAr = "أنا أذهب إلى الإسكندرية.",
                options = listOf("à", "en", "au"),
                correctIndex = 0,
                answerFr = "à",
                fullTransformedSentenceFr = "Je vais à Alexandrie.",
                explanationAr = "الإسكندرية اسم مدينة تسبق بحرف الجر (à)."
            ),
            RevisionGrammarItem(
                id = "art_17",
                sentenceFr = "17. Ils vont ............ maison.",
                sentenceAr = "هم يذهبون إلى المنزل.",
                options = listOf("à l'", "à la", "au"),
                correctIndex = 1,
                answerFr = "à la",
                fullTransformedSentenceFr = "Ils vont à la maison.",
                explanationAr = "كلمة maison مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_18",
                sentenceFr = "18. Il va ............ plage.",
                sentenceAr = "هو يذهب إلى الشاطئ.",
                options = listOf("à la", "au", "aux"),
                correctIndex = 0,
                answerFr = "à la",
                fullTransformedSentenceFr = "Il va à la plage.",
                explanationAr = "كلمة plage مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_19",
                sentenceFr = "19. Nous allons ............ cuisine.",
                sentenceAr = "نحن نذهب إلى المطبخ.",
                options = listOf("à la", "au", "à l'"),
                correctIndex = 0,
                answerFr = "à la",
                fullTransformedSentenceFr = "Nous allons à la cuisine.",
                explanationAr = "كلمة cuisine مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_20",
                sentenceFr = "20. Je vais ............ poste.",
                sentenceAr = "أنا أذهب إلى مكتب البريد.",
                options = listOf("à l'", "au", "à la"),
                correctIndex = 2,
                answerFr = "à la",
                fullTransformedSentenceFr = "Je vais à la poste.",
                explanationAr = "كلمة poste (مكتب البريد) مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_21",
                sentenceFr = "21. Je vais ............ Caire.",
                sentenceAr = "أنا أذهب إلى القاهرة.",
                options = listOf("à", "au", "en"),
                correctIndex = 1,
                answerFr = "au",
                fullTransformedSentenceFr = "Je vais au Caire.",
                explanationAr = "مدينة القاهرة (Le Caire) مذكرة وتأخذ دائماً أداة الإدغام (au Caire)."
            ),
            RevisionGrammarItem(
                id = "art_22",
                sentenceFr = "22. Vous allez ............ guichet.",
                sentenceAr = "أنتم تذهبون إلى شباك التذاكر.",
                options = listOf("à la", "au", "à l'"),
                correctIndex = 1,
                answerFr = "au",
                fullTransformedSentenceFr = "Vous allez au guichet.",
                explanationAr = "كلمة guichet مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_23",
                sentenceFr = "23. Nous allons ............ clinique.",
                sentenceAr = "نحن نذهب إلى العيادة الطبية.",
                options = listOf("au", "à l'", "à la"),
                correctIndex = 2,
                answerFr = "à la",
                fullTransformedSentenceFr = "Nous allons à la clinique.",
                explanationAr = "كلمة clinique مفرد مؤنث فنختار (à la)."
            ),
            RevisionGrammarItem(
                id = "art_24",
                sentenceFr = "24. Il va ............ cinéma.",
                sentenceAr = "هو يذهب إلى السينما.",
                options = listOf("au", "à la", "aux"),
                correctIndex = 0,
                answerFr = "au",
                fullTransformedSentenceFr = "Il va au cinéma.",
                explanationAr = "كلمة cinéma مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_25",
                sentenceFr = "25. Je vais ............ stade.",
                sentenceAr = "أنا أذهب إلى الإستاد الرياضي.",
                options = listOf("à l'", "à la", "au"),
                correctIndex = 2,
                answerFr = "au",
                fullTransformedSentenceFr = "Je vais au stade.",
                explanationAr = "كلمة stade مفرد مذكر فنختار (au)."
            ),
            RevisionGrammarItem(
                id = "art_26",
                sentenceFr = "26. Ils vont ............ jardin.",
                sentenceAr = "هم يذهبون إلى الحديقة.",
                options = listOf("à la", "aux", "au"),
                correctIndex = 2,
                answerFr = "au",
                fullTransformedSentenceFr = "Ils vont au jardin.",
                explanationAr = "كلمة jardin مفرد مذكر فنختار (au)."
            )
        )
    )

    // =========================================================================
    // EXERCISE 3 - Pages 74-75: Les pronoms personnels compléments (ضمائر المفعول)
    // =========================================================================
    val exercise3PronomsPersonnels = RevisionGrammarExercise(
        id = 3,
        pageNumber = "p. 74-75",
        titleFr = "3) Remplace par un pronom personnel",
        titleAr = "التمرين 3 : استبدل الكلمات التي تحتها خط بضمير شخصي",
        instructionFr = "3) Remplace les mots soulignés par un pronom personnel complément :",
        instructionAr = "استبدل المفعول به أو الفاعل المحدد بضمير شخصي مناسب (le, la, l', les, lui, leur, Il, Elle):",
        ruleSummaryAr = "📌 قاعدة الضمائر الشخصية:\n• ضمائر المفعول المباشر (COD): le (مذكر), la (مؤنث), l' (أمام متحرك), les (جمع).\n• ضمائر المفعول غير المباشر العاقل المسبوق بـ à (COI): lui (مفرد بنوعيه), leur (جمع بنوعيه).\n• ضمير الفاعل إذا كان الاسم فاعلاً: Il (هو), Elle (هي).\n• مكان الضمير: يوضع قبل الفعل مباشرة (Je les mange, Ali lui téléphone).",
        items = listOf(
            RevisionGrammarItem(
                id = "pro_1",
                sentenceFr = "1. Je mange mes sandwichs.",
                sentenceAr = "أنا آكل سندوتشاتي.",
                underlinedPart = "mes sandwichs",
                options = listOf("le", "lui", "les"),
                correctIndex = 2,
                answerFr = "les",
                fullTransformedSentenceFr = "Je les mange.",
                fullTransformedSentenceAr = "أنا آكلها.",
                explanationAr = "الكلمة (mes sandwichs) مفعول به مباشر جمع، نستبدلها بضمير (les) قبل الفعل: Je les mange."
            ),
            RevisionGrammarItem(
                id = "pro_2",
                sentenceFr = "2. Ali téléphone à son père.",
                sentenceAr = "علي يتصل بوالده هاتفياً.",
                underlinedPart = "à son père",
                options = listOf("le", "leur", "lui"),
                correctIndex = 2,
                answerFr = "lui",
                fullTransformedSentenceFr = "Ali lui téléphone.",
                fullTransformedSentenceAr = "علي يتصل به.",
                explanationAr = "المفعول (à son père) غير مباشر عاقل مفرد مسبوق بـ à، نستبدله بـ (lui): Ali lui téléphone."
            ),
            RevisionGrammarItem(
                id = "pro_3",
                sentenceFr = "3. Nous faisons ce devoir.",
                sentenceAr = "نحن نقوم بهذا الواجب.",
                underlinedPart = "ce devoir",
                options = listOf("la", "le", "les"),
                correctIndex = 1,
                answerFr = "le",
                fullTransformedSentenceFr = "Nous le faisons.",
                fullTransformedSentenceAr = "نحن نقوم به.",
                explanationAr = "(ce devoir) مفعول مباشر مفرد مذكر، نستبدله بـ (le): Nous le faisons."
            ),
            RevisionGrammarItem(
                id = "pro_4",
                sentenceFr = "4. Mona est brave.",
                sentenceAr = "منى شجاعة وممتازة.",
                underlinedPart = "Mona",
                options = listOf("Elle", "La", "Il"),
                correctIndex = 0,
                answerFr = "Elle",
                fullTransformedSentenceFr = "Elle est brave.",
                fullTransformedSentenceAr = "هي شجاعة.",
                explanationAr = "(Mona) فاعل مفرد مؤنث، نستبدلها بضمير الفاعل (Elle): Elle est brave."
            ),
            RevisionGrammarItem(
                id = "pro_5",
                sentenceFr = "5. Les élèves écoutent la leçon.",
                sentenceAr = "التلاميذ يستمعون إلى الدرس.",
                underlinedPart = "la leçon",
                options = listOf("l'", "la", "le"),
                correctIndex = 0,
                answerFr = "l'",
                fullTransformedSentenceFr = "Les élèves l'écoutent.",
                fullTransformedSentenceAr = "التلاميذ يستمعون إليه.",
                explanationAr = "(la leçon) مفعول مباشر مؤنث، وحيث أن الفعل (écoutent) يبدأ بحرف متحرك، تُختصر (la) إلى (l'): Les élèves l'écoutent."
            ),
            RevisionGrammarItem(
                id = "pro_6",
                sentenceFr = "6. Ils parlent aux parents.",
                sentenceAr = "هم يتحدثون إلى الوالدين.",
                underlinedPart = "aux parents",
                options = listOf("les", "lui", "leur"),
                correctIndex = 2,
                answerFr = "leur",
                fullTransformedSentenceFr = "Ils leur parlent.",
                fullTransformedSentenceAr = "هم يتحدثون إليهم.",
                explanationAr = "(aux parents) مفعول غير مباشر عاقل جمع مسبوق بـ aux، نستبدله بـ (leur): Ils leur parlent."
            ),
            RevisionGrammarItem(
                id = "pro_7",
                sentenceFr = "7. Ali écrit son devoir.",
                sentenceAr = "علي يكتب واجبه.",
                underlinedPart = "son devoir",
                options = listOf("l'", "lui", "le"),
                correctIndex = 0,
                answerFr = "l'",
                fullTransformedSentenceFr = "Ali l'écrit.",
                fullTransformedSentenceAr = "علي يكتبه.",
                explanationAr = "(son devoir) مذكر، وبما أن الفعل (écrit) يبدأ بحرف متحرك تصبح (le) هي (l'): Ali l'écrit."
            ),
            RevisionGrammarItem(
                id = "pro_8",
                sentenceFr = "8. Nous écrivons notre leçon.",
                sentenceAr = "نحن نكتب درسنا.",
                underlinedPart = "notre leçon",
                options = listOf("l'", "la", "le"),
                correctIndex = 0,
                answerFr = "l'",
                fullTransformedSentenceFr = "Nous l'écrivons.",
                fullTransformedSentenceAr = "نحن نكتبه.",
                explanationAr = "(notre leçon) مفرد، وأمام الفعل (écrivons) البادئ بمتحرك نستخدم (l'): Nous l'écrivons."
            ),
            RevisionGrammarItem(
                id = "pro_9",
                sentenceFr = "9. Mona lit le journal.",
                sentenceAr = "منى تقرأ الجريدة.",
                underlinedPart = "le journal",
                options = listOf("l'", "la", "le"),
                correctIndex = 2,
                answerFr = "le",
                fullTransformedSentenceFr = "Mona le lit.",
                fullTransformedSentenceAr = "منى تقرأها.",
                explanationAr = "(le journal) مفعول مباشر مفرد مذكر، نستبدله بـ (le): Mona le lit."
            ),
            RevisionGrammarItem(
                id = "pro_10",
                sentenceFr = "10. Les filles donnent les livres au professeur.",
                sentenceAr = "البنات يعطين الكتب للمدرس.",
                underlinedPart = "au professeur",
                options = listOf("lui", "le", "leur"),
                correctIndex = 0,
                answerFr = "lui",
                fullTransformedSentenceFr = "Les filles lui donnent les livres.",
                fullTransformedSentenceAr = "البنات يعطينه الكتب.",
                explanationAr = "(au professeur) مفعول غير مباشر عاقل مفرد، نستبدله بـ (lui): Les filles lui donnent les livres."
            ),
            RevisionGrammarItem(
                id = "pro_11",
                sentenceFr = "11. Les filles portent leurs robes.",
                sentenceAr = "البنات يرتدين فساتينهن.",
                underlinedPart = "leurs robes",
                options = listOf("les", "la", "leur"),
                correctIndex = 0,
                answerFr = "les",
                fullTransformedSentenceFr = "Les filles les portent.",
                fullTransformedSentenceAr = "البنات يرتدينها.",
                explanationAr = "(leurs robes) مفعول مباشر جمع، نستبدله بـ (les): Les filles les portent."
            ),
            RevisionGrammarItem(
                id = "pro_12",
                sentenceFr = "12. Nous regardons ce film.",
                sentenceAr = "نحن نشاهد هذا الفيلم.",
                underlinedPart = "ce film",
                options = listOf("la", "le", "les"),
                correctIndex = 1,
                answerFr = "le",
                fullTransformedSentenceFr = "Nous le regardons.",
                fullTransformedSentenceAr = "نحن نشاهده.",
                explanationAr = "(ce film) مفعول مباشر مفرد مذكر، نستبدله بـ (le): Nous le regardons."
            ),
            RevisionGrammarItem(
                id = "pro_13",
                sentenceFr = "13. La maîtresse explique cette leçon.",
                sentenceAr = "المعلمة تشرح هذا الدرس.",
                underlinedPart = "cette leçon",
                options = listOf("l'", "le", "la"),
                correctIndex = 0,
                answerFr = "l'",
                fullTransformedSentenceFr = "La maîtresse l'explique.",
                fullTransformedSentenceAr = "المعلمة تشرحه.",
                explanationAr = "(cette leçon) مؤنث والفعل (explique) يبدأ بمتحرك فتصبح (l'): La maîtresse l'explique."
            ),
            RevisionGrammarItem(
                id = "pro_14",
                sentenceFr = "14. Ali écrit à ses enfants.",
                sentenceAr = "علي يكتب لأطفاله.",
                underlinedPart = "à ses enfants",
                options = listOf("les", "lui", "leur"),
                correctIndex = 2,
                answerFr = "leur",
                fullTransformedSentenceFr = "Ali leur écrit.",
                fullTransformedSentenceAr = "علي يكتب لهم.",
                explanationAr = "(à ses enfants) مفعول غير مباشر عاقل جمع مسبوق بـ à، نستبدله بـ (leur): Ali leur écrit."
            ),
            RevisionGrammarItem(
                id = "pro_15",
                sentenceFr = "15. Le marchand vend ces légumes.",
                sentenceAr = "التاجر يبيع هذه الخضروات.",
                underlinedPart = "ces légumes",
                options = listOf("le", "les", "leur"),
                correctIndex = 1,
                answerFr = "les",
                fullTransformedSentenceFr = "Le marchand les vend.",
                fullTransformedSentenceAr = "التاجر يبيعها.",
                explanationAr = "(ces légumes) مفعول مباشر جمع، نستبدله بـ (les): Le marchand les vend."
            ),
            RevisionGrammarItem(
                id = "pro_16",
                sentenceFr = "16. Le garçon sert les clients.",
                sentenceAr = "الجرسون يخدم الزبائن.",
                underlinedPart = "les clients",
                options = listOf("les", "leur", "lui"),
                correctIndex = 0,
                answerFr = "les",
                fullTransformedSentenceFr = "Le garçon les sert.",
                fullTransformedSentenceAr = "الجرسون يخدمهم.",
                explanationAr = "(les clients) مفعول مباشر جمع بدون حرف جر، نستبدله بـ (les): Le garçon les sert."
            ),
            RevisionGrammarItem(
                id = "pro_17",
                sentenceFr = "17. Nous prenons notre livre.",
                sentenceAr = "نحن نأخذ كتابنا.",
                underlinedPart = "notre livre",
                options = listOf("les", "la", "le"),
                correctIndex = 2,
                answerFr = "le",
                fullTransformedSentenceFr = "Nous le prenons.",
                fullTransformedSentenceAr = "نحن نأخذه.",
                explanationAr = "(notre livre) مفعول مباشر مفرد مذكر، نستبدله بـ (le): Nous le prenons."
            ),
            RevisionGrammarItem(
                id = "pro_18",
                sentenceFr = "18. Ali achète la voiture rouge.",
                sentenceAr = "علي يشتري السيارة الحمراء.",
                underlinedPart = "la voiture rouge",
                options = listOf("la", "l'", "le"),
                correctIndex = 1,
                answerFr = "l'",
                fullTransformedSentenceFr = "Ali l'achète.",
                fullTransformedSentenceAr = "علي يشتريها.",
                explanationAr = "(la voiture rouge) مؤنث والفعل (achète) يبدأ بمتحرك فتصبح (l'): Ali l'achète."
            ),
            RevisionGrammarItem(
                id = "pro_19",
                sentenceFr = "19. Ma mère prépare ce gâteau.",
                sentenceAr = "أمي تُجهّز هذه الكعكة.",
                underlinedPart = "ce gâteau",
                options = listOf("les", "le", "la"),
                correctIndex = 1,
                answerFr = "le",
                fullTransformedSentenceFr = "Ma mère le prépare.",
                fullTransformedSentenceAr = "أمي تجهزها.",
                explanationAr = "(ce gâteau) مفعول مباشر مفرد مذكر، نستبدله بـ (le): Ma mère le prépare."
            ),
            RevisionGrammarItem(
                id = "pro_20",
                sentenceFr = "20. Tu envoies tes lettres.",
                sentenceAr = "أنت ترسل خطاباتك.",
                underlinedPart = "tes lettres",
                options = listOf("la", "les", "leur"),
                correctIndex = 1,
                answerFr = "les",
                fullTransformedSentenceFr = "Tu les envoies.",
                fullTransformedSentenceAr = "أنت ترسلها.",
                explanationAr = "(tes lettres) مفعول مباشر جمع، نستبدله بـ (les): Tu les envoies."
            ),
            RevisionGrammarItem(
                id = "pro_21",
                sentenceFr = "21. Ahmed va au club.",
                sentenceAr = "أحمد يذهب إلى النادي.",
                underlinedPart = "Ahmed",
                options = listOf("Il", "Elle", "Le"),
                correctIndex = 0,
                answerFr = "Il",
                fullTransformedSentenceFr = "Il va au club.",
                fullTransformedSentenceAr = "هو يذهب إلى النادي.",
                explanationAr = "(Ahmed) فاعل مفرد مذكر، نستبدله بضمير الفاعل (Il): Il va au club."
            )
        )
    )

    // =========================================================================
    // EXERCISE 4 - Page 76: Conjugue au présent (تصريف الأفعال في المضارع)
    // =========================================================================
    val exercise4ConjugueAuPresent = RevisionGrammarExercise(
        id = 4,
        pageNumber = "p. 76",
        titleFr = "4) Conjugue au présent",
        titleAr = "التمرين 4 : صرّف الأفعال بين القوسين في زمن المضارع",
        instructionFr = "4) Conjugue au présent :",
        instructionAr = "اكتب تصريف الفعل الصحيح في زمن المضارع حسب الضمير أو الفاعل المعطى:",
        ruleSummaryAr = "📌 نهايات المضارع لأفعال المجموعة الأولى (-er):\n• je: -e | tu: -es | il/elle: -e | nous: -ons | vous: -ez | ils/elles: -ent.\n• مع الفعل (manger) نضيف e مع nous: nous mangeons.\n• تصريف être: suis, es, est, sommes, êtes, sont.\n• تصريف avoir: ai, as, a, avons, avez, ont.\n• تصريف faire: fais, fais, fait, faisons, faites, font.",
        items = listOf(
            RevisionGrammarItem(
                id = "conj_1",
                sentenceFr = "1. Chaque soir, je .................... le devoir. (faire)",
                sentenceAr = "كل مساء، أنا أعمل الواجب.",
                options = listOf("fait", "faisons", "fais"),
                correctIndex = 2,
                answerFr = "fais",
                fullTransformedSentenceFr = "Chaque soir, je fais le devoir.",
                explanationAr = "تصريف فعل (faire) مع الضمير (je) هو (fais)."
            ),
            RevisionGrammarItem(
                id = "conj_2",
                sentenceFr = "2. Aujourd'hui, tu .................... content? (être)",
                sentenceAr = "اليوم، هل أنت مسرور؟",
                options = listOf("est", "suis", "es"),
                correctIndex = 2,
                answerFr = "es",
                fullTransformedSentenceFr = "Aujourd'hui, tu es content?",
                explanationAr = "تصريف فعل (être) مع الضمير (tu) هو (es)."
            ),
            RevisionGrammarItem(
                id = "conj_3",
                sentenceFr = "3. Chaque jour, je .................... aux élèves. (parler)",
                sentenceAr = "كل يوم، أنا أتحدث إلى التلاميذ.",
                options = listOf("parlez", "parles", "parle"),
                correctIndex = 2,
                answerFr = "parle",
                fullTransformedSentenceFr = "Chaque jour, je parle aux élèves.",
                explanationAr = "فعل مجموعة أولى مع (je) ينتهي بـ (e) -> parle."
            ),
            RevisionGrammarItem(
                id = "conj_4",
                sentenceFr = "4. Chaque mois, nous .................... des gâteaux. (manger)",
                sentenceAr = "كل شهر، نحن نأكل الكعك والجاتوه.",
                options = listOf("mangeons", "mangons", "mangez"),
                correctIndex = 0,
                answerFr = "mangeons",
                fullTransformedSentenceFr = "Chaque mois, nous mangeons des gâteaux.",
                explanationAr = "مع (nous) نحتفظ بحرف (e) قبل -ons للحفاظ على النطق المعطش للجيم -> mangeons."
            ),
            RevisionGrammarItem(
                id = "conj_5",
                sentenceFr = "5. Maintenant, vous .................... l'explication. (regarder)",
                sentenceAr = "الآن، أنتم تشاهدون الشرح.",
                options = listOf("regardons", "regardez", "regarde"),
                correctIndex = 1,
                answerFr = "regardez",
                fullTransformedSentenceFr = "Maintenant, vous regardez l'explication.",
                explanationAr = "مع الضمير (vous) ينتهي الفعل بـ (-ez) -> regardez."
            ),
            RevisionGrammarItem(
                id = "conj_6",
                sentenceFr = "6. Chaque jour, les garçons .................... au ballon. (jouer)",
                sentenceAr = "كل يوم، الأولاد يلعبون بالكرة.",
                options = listOf("jouent", "jouez", "joue"),
                correctIndex = 0,
                answerFr = "jouent",
                fullTransformedSentenceFr = "Chaque jour, les garçons jouent au ballon.",
                explanationAr = "الفاعل (les garçons) يساوي (ils)، ونهاية الفعل معه هي (-ent) -> jouent."
            ),
            RevisionGrammarItem(
                id = "conj_7",
                sentenceFr = "7. Chaque jour, je .................... à l'école. (faire)",
                sentenceAr = "كل يوم، أنا ... إلى المدرسة.",
                options = listOf("fais", "fait", "vais"),
                correctIndex = 0,
                answerFr = "fais",
                fullTransformedSentenceFr = "Chaque jour, je fais à l'école.",
                explanationAr = "المطلوب بين القوسين تصريف فعل (faire) مع je وهو (fais) (أو vais إن قصد الكتيّب فعل aller)."
            ),
            RevisionGrammarItem(
                id = "conj_8",
                sentenceFr = "8. Maintenant, ils .................... 16 ans. (avoir)",
                sentenceAr = "الآن، هم يبلغون من العمر 16 سنة.",
                options = listOf("sont", "ont", "avons"),
                correctIndex = 1,
                answerFr = "ont",
                fullTransformedSentenceFr = "Maintenant, ils ont 16 ans.",
                explanationAr = "تصريف فعل (avoir) مع الضمير (ils) هو (ont)."
            ),
            RevisionGrammarItem(
                id = "conj_9",
                sentenceFr = "9. Chaque jour, mon père .................... médecin. (être)",
                sentenceAr = "كل يوم، أبي ... طبيب.",
                options = listOf("est", "es", "suis"),
                correctIndex = 0,
                answerFr = "est",
                fullTransformedSentenceFr = "Chaque jour, mon père est médecin.",
                explanationAr = "الفاعل (mon père) مفرد غائب يساوي (il)، وتصريف être معه هو (est)."
            ),
            RevisionGrammarItem(
                id = "conj_10",
                sentenceFr = "10. Aujourd'hui, Ali .................... son devoir. (écouter)",
                sentenceAr = "اليوم، علي يستمع لواجباته.",
                options = listOf("écoutes", "écoutent", "écoute"),
                correctIndex = 2,
                answerFr = "écoute",
                fullTransformedSentenceFr = "Aujourd'hui, Ali écoute son devoir.",
                explanationAr = "الفاعل (Ali) مفرد غائب يساوي (il)، ونهاية الفعل معه (-e) -> écoute."
            )
        )
    )

    // =========================================================================
    // EXERCISE 5 - Page 76: Choisis la bonne réponse (اختر التصريف الصحيح)
    // =========================================================================
    val exercise5Choisis = RevisionGrammarExercise(
        id = 5,
        pageNumber = "p. 76",
        titleFr = "5) Choisis la bonne réponse",
        titleAr = "التمرين 5 : اختر الإجابة الصحيحة",
        instructionFr = "5) Choisis :",
        instructionAr = "اختر الفعل أو التصريف المناسب لكل جملة:",
        ruleSummaryAr = "📌 تطابق الفاعل مع الفعل:\n• Nous -> -ons (allons, aimons, faisons)\n• Tu -> -es, -s (es, regardes, fais)\n• Elle / Il -> -e, -a, -t (a, va, regarde)\n• Elles / Ils -> -ont, -ent (écoutent, ont, vont)",
        items = listOf(
            RevisionGrammarItem(
                id = "choix_1",
                sentenceFr = "1. Nous .................... à Paris.",
                sentenceAr = "نحن نذهب إلى باريس.",
                options = listOf("va", "allons", "allez"),
                correctIndex = 1,
                answerFr = "allons",
                fullTransformedSentenceFr = "Nous allons à Paris.",
                explanationAr = "مع الضمير (Nous) نختار الفعل المنتهي بـ ons: (allons)."
            ),
            RevisionGrammarItem(
                id = "choix_2",
                sentenceFr = "2. Tu .................... Égyptien.",
                sentenceAr = "أنت مصري.",
                options = listOf("es", "est", "suis"),
                correctIndex = 0,
                answerFr = "es",
                fullTransformedSentenceFr = "Tu es Égyptien.",
                explanationAr = "مع الضمير (Tu) من تصريف فعل être نختار (es)."
            ),
            RevisionGrammarItem(
                id = "choix_3",
                sentenceFr = "3. Elle .................... 15 ans.",
                sentenceAr = "هي تبلغ من العمر 15 سنة.",
                options = listOf("as", "a", "ai"),
                correctIndex = 1,
                answerFr = "a",
                fullTransformedSentenceFr = "Elle a 15 ans.",
                explanationAr = "مع الضمير (Elle) من تصريف فعل avoir نختار (a)."
            ),
            RevisionGrammarItem(
                id = "choix_4",
                sentenceFr = "4. Il .................... au stade.",
                sentenceAr = "هو يذهب إلى الإستاد.",
                options = listOf("va", "vas", "vais"),
                correctIndex = 0,
                answerFr = "va",
                fullTransformedSentenceFr = "Il va au stade.",
                explanationAr = "مع الضمير (Il) من تصريف فعل aller نختار (va)."
            ),
            RevisionGrammarItem(
                id = "choix_5",
                sentenceFr = "5. Nous .................... nos professeurs.",
                sentenceAr = "نحن نحب معلمينا.",
                options = listOf("aimez", "aimons", "aime"),
                correctIndex = 1,
                answerFr = "aimons",
                fullTransformedSentenceFr = "Nous aimons nos professeurs.",
                explanationAr = "مع الضمير (Nous) نختار (aimons)."
            ),
            RevisionGrammarItem(
                id = "choix_6",
                sentenceFr = "6. J' .................... un nouveau pantalon.",
                sentenceAr = "أنا لدي بنطال جديد.",
                options = listOf("as", "a", "ai"),
                correctIndex = 2,
                answerFr = "ai",
                fullTransformedSentenceFr = "J'ai un nouveau pantalon.",
                explanationAr = "مع الضمير (J') من فعل avoir نختار (ai) -> J'ai."
            ),
            RevisionGrammarItem(
                id = "choix_7",
                sentenceFr = "7. Je .................... le match.",
                sentenceAr = "أنا أشاهد المباراة.",
                options = listOf("regardes", "regardez", "regarde"),
                correctIndex = 2,
                answerFr = "regarde",
                fullTransformedSentenceFr = "Je regarde le match.",
                explanationAr = "مع الضمير (Je) ينتهي فعل المجموعة الأولى بـ (-e) -> regarde."
            ),
            RevisionGrammarItem(
                id = "choix_8",
                sentenceFr = "8. Elles .................... l'explication.",
                sentenceAr = "هنّ يستمعن إلى الشرح.",
                options = listOf("écoute", "écoutez", "écoutent"),
                correctIndex = 2,
                answerFr = "écoutent",
                fullTransformedSentenceFr = "Elles écoutent l'explication.",
                explanationAr = "مع الضمير (Elles) ينتهي الفعل بـ (-ent) -> écoutent."
            ),
            RevisionGrammarItem(
                id = "choix_9",
                sentenceFr = "9. Tu .................... le devoir?",
                sentenceAr = "هل تقوم بالواجب؟",
                options = listOf("font", "fait", "fais"),
                correctIndex = 2,
                answerFr = "fais",
                fullTransformedSentenceFr = "Tu fais le devoir?",
                explanationAr = "مع الضمير (Tu) من فعل faire نختار (fais)."
            ),
            RevisionGrammarItem(
                id = "choix_10",
                sentenceFr = "10. Nous .................... notre devoir.",
                sentenceAr = "نحن نقوم بواجبنا.",
                options = listOf("fais", "faites", "faisons"),
                correctIndex = 2,
                answerFr = "faisons",
                fullTransformedSentenceFr = "Nous faisons notre devoir.",
                explanationAr = "مع الضمير (Nous) من فعل faire نختار (faisons)."
            )
        )
    )

    // =========================================================================
    // EXERCISE 6 - Page 77: Adjectifs possessifs (صفات الملكية)
    // =========================================================================
    val exercise6AdjectifsPossessifs = RevisionGrammarExercise(
        id = 6,
        pageNumber = "p. 77",
        titleFr = "6) Complète avec un adjectif possessif",
        titleAr = "التمرين 6 : أكمل بصفة ملكية مناسبة",
        instructionFr = "6) Complète avec un adjectif possessif convenable :",
        instructionAr = "ضع صفة الملكية الصحيحة (mon, ma, mes / ton, ta, tes / son, sa, ses / notre, nos / votre, vos / leur, leurs):",
        ruleSummaryAr = "📌 جدول صفات الملكية:\n• Je: mon (مذكر), ma (مؤنث), mes (جمع)\n• Tu: ton (مذكر), ta (مؤنث), tes (جمع)\n• Il / Elle: son (مذكر), sa (مؤنث), ses (جمع)\n• Nous: notre (مفرد), nos (جمع)\n• Vous: votre (مفرد), vos (جمع)\n• Ils / Elles: leur (مفرد), leurs (جمع)\n⚠️ تنبيه: أمام الاسم المفرد المؤنث البادئ بحرف متحرك نستخدم (mon, ton, son) بدلاً من (ma, ta, sa) مثل: son amie.",
        items = listOf(
            RevisionGrammarItem(
                id = "pos_1",
                sentenceFr = "1. Je mets ............ pantalon et ............ chemise pour sortir.",
                sentenceAr = "أنا أرتدي بنطالي وقميصي للخروج.",
                options = listOf("mes / ma", "mon / ma", "ma / mon"),
                correctIndex = 1,
                answerFr = "mon / ma",
                fullTransformedSentenceFr = "Je mets mon pantalon et ma chemise pour sortir.",
                explanationAr = "مع الفاعل (Je): pantalon مفرد مذكر يأخذ (mon)، و chemise مفرد مؤنث تأخذ (ma)."
            ),
            RevisionGrammarItem(
                id = "pos_2",
                sentenceFr = "2. Tu aimes ............ sœur et ............ frère?",
                sentenceAr = "هل تحب أختك وأخاك؟",
                options = listOf("tes / ton", "ta / ton", "ton / ta"),
                correctIndex = 1,
                answerFr = "ta / ton",
                fullTransformedSentenceFr = "Tu aimes ta sœur et ton frère?",
                explanationAr = "مع الفاعل (Tu): sœur مفرد مؤنث تأخذ (ta)، و frère مفرد مذكر يأخذ (ton)."
            ),
            RevisionGrammarItem(
                id = "pos_3",
                sentenceFr = "3. Il respecte ............ professeur et il aime ............ copains.",
                sentenceAr = "هو يحترم معلمه ويحب زملاءه.",
                options = listOf("son / son", "sa / ses", "son / ses"),
                correctIndex = 2,
                answerFr = "son / ses",
                fullTransformedSentenceFr = "Il respecte son professeur et il aime ses copains.",
                explanationAr = "مع الفاعل (Il): professeur مفرد مذكر يأخذ (son)، و copains جمع يأخذ (ses)."
            ),
            RevisionGrammarItem(
                id = "pos_4",
                sentenceFr = "4. Elle met ............ robe blanche et ............ chaussures.",
                sentenceAr = "هي ترتدي فستانها الأبيض وحذاءها.",
                options = listOf("sa / ses", "son / ses", "sa / sa"),
                correctIndex = 0,
                answerFr = "sa / ses",
                fullTransformedSentenceFr = "Elle met sa robe blanche et ses chaussures.",
                explanationAr = "مع الفاعل (Elle): robe مفرد مؤنث تأخذ (sa)، و chaussures جمع تأخذ (ses)."
            ),
            RevisionGrammarItem(
                id = "pos_5",
                sentenceFr = "5. Nous finissons ............ devoirs pour aller chez ............ tante.",
                sentenceAr = "نحن ننهي واجباتنا لنذهب عند عمتنا.",
                options = listOf("notre / nos", "nos / notre", "nos / nos"),
                correctIndex = 1,
                answerFr = "nos / notre",
                fullTransformedSentenceFr = "Nous finissons nos devoirs pour aller chez notre tante.",
                explanationAr = "مع الفاعل (Nous): devoirs جمع يأخذ (nos)، و tante مفرد تأخذ (notre)."
            ),
            RevisionGrammarItem(
                id = "pos_6",
                sentenceFr = "6. Vous invitez ............ amie française.",
                sentenceAr = "أنتم تدعون صديقتكم الفرنسية.",
                options = listOf("notre", "vos", "votre"),
                correctIndex = 2,
                answerFr = "votre",
                fullTransformedSentenceFr = "Vous invitez votre amie française.",
                explanationAr = "مع الفاعل (Vous): amie مفرد مؤنث تأخذ (votre)."
            ),
            RevisionGrammarItem(
                id = "pos_7",
                sentenceFr = "7. Elles vont avec ............ mère chez ............ cousins.",
                sentenceAr = "هن يذهبن مع والدتهن عند أبناء عمومتهن.",
                options = listOf("sa / ses", "leur / leurs", "leurs / leur"),
                correctIndex = 1,
                answerFr = "leur / leurs",
                fullTransformedSentenceFr = "Elles vont avec leur mère chez leurs cousins.",
                explanationAr = "مع الفاعل (Elles): mère مفرد تأخذ (leur)، و cousins جمع تأخذ (leurs)."
            ),
            RevisionGrammarItem(
                id = "pos_8",
                sentenceFr = "8. Ali et Ahmed visitent ............ ami.",
                sentenceAr = "علي وأحمد يزوران صديقهما.",
                options = listOf("leur", "son", "leurs"),
                correctIndex = 0,
                answerFr = "leur",
                fullTransformedSentenceFr = "Ali et Ahmed visitent leur ami.",
                explanationAr = "الفاعل (Ali et Ahmed) جمع غائب (Ils)، وكلمة (ami) مفرد فتأخذ (leur)."
            ),
            RevisionGrammarItem(
                id = "pos_9",
                sentenceFr = "9. Mona met ............ livre et ............ trousse dans ............ cartable.",
                sentenceAr = "منى تضع كتابها ومقلمتها في حقيبتها المدرسية.",
                options = listOf("sa / son / sa", "son / son / son", "son / sa / son"),
                correctIndex = 2,
                answerFr = "son / sa / son",
                fullTransformedSentenceFr = "Mona met son livre et sa trousse dans son cartable.",
                explanationAr = "مع الفاعل (Mona): livre مذكر (son)، trousse مؤنث (sa)، cartable مذكر (son)."
            )
        )
    )

    // =========================================================================
    // EXERCISE 7 - Page 77: Corrige les verbes (تصحيح الأفعال)
    // =========================================================================
    val exercise7CorrigeLesVerbes = RevisionGrammarExercise(
        id = 7,
        pageNumber = "p. 77",
        titleFr = "7) Corrige les verbes",
        titleAr = "التمرين 7 : صحّح الأفعال التي بين القوسين",
        instructionFr = "7) Corrige les verbes :",
        instructionAr = "صحّح صياغة الفعل بين القوسين في زمن المضارع:",
        ruleSummaryAr = "📌 نهايات الأفعال في المضارع:\n• المجموعة الأولى (-er): terminer -> je termine | manger -> vous mangez | écouter -> ils écoutent | fermer -> tu fermes.\n• المجموعة الثانية (-ir): réussir -> nous réussissons | finir -> elle finit.",
        items = listOf(
            RevisionGrammarItem(
                id = "cor_1",
                sentenceFr = "1. Chaque jour, je (terminer) .................... les devoirs.",
                sentenceAr = "كل يوم، أنا أنهي الواجبات.",
                options = listOf("terminons", "termines", "termine"),
                correctIndex = 2,
                answerFr = "termine",
                fullTransformedSentenceFr = "Chaque jour, je termine les devoirs.",
                explanationAr = "فعل مجموعة أولى مع (je) ينتهي بـ (-e) -> termine."
            ),
            RevisionGrammarItem(
                id = "cor_2",
                sentenceFr = "2. Vous (manger) .................... vos sandwichs.",
                sentenceAr = "أنتم تأكلون سندوتشاتكم.",
                options = listOf("manges", "mangeons", "mangez"),
                correctIndex = 2,
                answerFr = "mangez",
                fullTransformedSentenceFr = "Vous mangez vos sandwichs.",
                explanationAr = "فعل مجموعة أولى مع (vous) ينتهي بـ (-ez) -> mangez."
            ),
            RevisionGrammarItem(
                id = "cor_3",
                sentenceFr = "3. Nous (réussir) .................... à l'examen.",
                sentenceAr = "نحن ننجح في الامتحان.",
                options = listOf("réussissez", "réussisons", "réussissons"),
                correctIndex = 2,
                answerFr = "réussissons",
                fullTransformedSentenceFr = "Nous réussissons à l'examen.",
                explanationAr = "فعل مجموعة ثانية (-ir) مع (nous) يأخذ (-issons) -> réussissons."
            ),
            RevisionGrammarItem(
                id = "cor_4",
                sentenceFr = "4. Les élèves (écouter) .................... la leçon.",
                sentenceAr = "التلاميذ يستمعون إلى الدرس.",
                options = listOf("écoutent", "écoute", "écoutez"),
                correctIndex = 0,
                answerFr = "écoutent",
                fullTransformedSentenceFr = "Les élèves écoutent la leçon.",
                explanationAr = "الفاعل (Les élèves) يساوي (ils) ونهاية الفعل معه هي (-ent) -> écoutent."
            ),
            RevisionGrammarItem(
                id = "cor_5",
                sentenceFr = "5. Tu (fermer) .................... la porte.",
                sentenceAr = "أنت تغلق الباب.",
                options = listOf("fermes", "fermez", "ferme"),
                correctIndex = 0,
                answerFr = "fermes",
                fullTransformedSentenceFr = "Tu fermes la porte.",
                explanationAr = "فعل مجموعة أولى مع (tu) ينتهي بـ (-es) -> fermes."
            ),
            RevisionGrammarItem(
                id = "cor_6",
                sentenceFr = "6. Elle (finir) .................... ses devoirs.",
                sentenceAr = "هي تنهي واجباتها.",
                options = listOf("finis", "finissent", "finit"),
                correctIndex = 2,
                answerFr = "finit",
                fullTransformedSentenceFr = "Elle finit ses devoirs.",
                explanationAr = "فعل مجموعة ثانية (-ir) مع (Elle) ينتهي بـ (-it) -> finit."
            )
        )
    )

    val allExercises = listOf(
        exercise1Negation,
        exercise2ArticlesContractes,
        exercise3PronomsPersonnels,
        exercise4ConjugueAuPresent,
        exercise5Choisis,
        exercise6AdjectifsPossessifs,
        exercise7CorrigeLesVerbes
    )
}
