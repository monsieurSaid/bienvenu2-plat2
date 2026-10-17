package com.example.data

object FrenchCourseData {

    val unit1FullText = "Unité 1 : Invitation. Les vacances sont vite passées. Jean Morelle et sa femme Suzanne sont rentrés au Caire. Gamal les a invités chez lui avec Moustafa, pilote à EgyptAir."
    val unit1FullTextArabic = "الوحدة الأولى : الدعوة. مرت الإجازة بسرعة. عاد جان موريل وزوجته سوزان إلى القاهرة. دعاهما جمال إلى منزله مع مصطفى، طيار في مصر للطيران."

    val unit1Dialogues = listOf(
        DialogueLine(
            speaker = "Récit du texte",
            text = "« Les vacances sont vite passées. Jean Morelle et sa femme Suzanne sont rentrés au Caire. Gamal les a invités chez lui avec Moustafa (pilote à EgyptAir). »",
            arabicNote = "مرت الإجازة بسرعة. عاد جان وزوجته سوزان إلى القاهرة. دعاهما جمال إلى منزله مع مصطفى (طيار بمصر للطيران).",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Jean Morelle",
            text = "« Tiens Gamal. Ce livre, nous l'avons acheté pour toi. »",
            arabicNote = "تفضل يا جمال. هذا الكتاب اشتريناه لك (كتاب تاريخ هدية). 📚",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal",
            text = "« Oh ! Merci beaucoup. Les livres d'histoire, moi, je les adore ! J'ai passé mes vacances à la Mer Rouge avec mes élèves. »",
            arabicNote = "أوه! شكراً جزيلاً. كتب التاريخ أنا أعشقها! قضيت إجازتي في البحر الأحمر مع تلاميذي.",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Moustafa",
            text = "« Moi, je n'ai pas eu de vacances cette année. Je suis pilote à EgyptAir. »",
            arabicNote = "أما أنا، فلم أحصل على إجازة هذا العام. أنا طيار في مصر للطيران. ✈️",
            borderColorHex = 4283385573L
        )
    )

    val unit1Ex2RepondsAuxQuestions = listOf(
        QuizQuestion(
            id = "u1_ex2_a",
            question = "a- Où Gamal a-t-il invité ses amis français ?",
            options = listOf("Au restaurant", "Au club", "Chez lui (à la maison)"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Gamal a invité ses amis français chez lui.",
            arabicTranslation = "أين دعا جمال أصدقاءه الفرنسيين؟ ➔ في منزله (Chez lui)."
        ),
        QuizQuestion(
            id = "u1_ex2_b",
            question = "b- Avec qui Suzanne est-elle rentrée au Caire ?",
            options = listOf("Avec son mari Jean", "Avec Moustafa", "Avec ses élèves"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Suzanne est rentrée au Caire avec son mari Jean Morelle.",
            arabicTranslation = "مع من عادت سوزان إلى القاهرة؟ ➔ مع زوجها جان (Avec son mari Jean)."
        ),
        QuizQuestion(
            id = "u1_ex2_c",
            question = "c- À qui Gamal a – t – il présenté Moustafa ?",
            options = listOf("À son père", "À ses élèves", "À ses amis français (Jean et Suzanne)"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Gamal a présenté Moustafa à Jean et Suzanne : « Je vous présente Moustafa ».",
            arabicTranslation = "إلى من قدّم جمال صديقه مصطفى؟ ➔ إلى أصدقائه الفرنسيين (جان وسوزان)."
        )
    )

    val unit1Ex3VraiOuFaux = listOf(
        QuizQuestion(
            id = "u1_ex3_a",
            question = "a- Moustafa est l'ami de Jean.",
            options = listOf("Vrai (صح)", "Faux (خطأ)"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : Moustafa est l'ami égyptien de Gamal (pilote à EgyptAir).",
            arabicTranslation = "مصطفى هو صديق جان ➔ [خطأ Faux]. مصطفى هو صديق جمال."
        ),
        QuizQuestion(
            id = "u1_ex3_b",
            question = "b- Gamal aime beaucoup les livres d'histoire.",
            options = listOf("Vrai (صح)", "Faux (خطأ)"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Vrai : Gamal dit : « Les livres d'histoire, moi, je les adore ! »",
            arabicTranslation = "جمال يحب كتب التاريخ كثيراً ➔ [صح Vrai]. قال جمال: 'Les livres d'histoire, moi, je les adore !'"
        ),
        QuizQuestion(
            id = "u1_ex3_c",
            question = "c- Sur la table, il y a beaucoup de livres.",
            options = listOf("Vrai (صح)", "Faux (خطأ)"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : Sur la table, il y a des gâteaux et des boissons fraîches.",
            arabicTranslation = "على الطاولة يوجد الكثير من الكتب ➔ [خطأ Faux]. على الطاولة توجد حلوى ومشروبات باردة."
        ),
        QuizQuestion(
            id = "u1_ex3_d",
            question = "d- Gamal n'est pas content de ses vacances.",
            options = listOf("Vrai (صح)", "Faux (خطأ)"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : Gamal a passé de très bonnes vacances à la Mer Rouge avec ses élèves.",
            arabicTranslation = "جمال ليس سعيداً بإجازته ➔ [خطأ Faux]. قضى جمال إجازة سعيدة جداً في البحر الأحمر."
        )
    )

    val unit1Ex4Associe = listOf(
        QuizQuestion(
            id = "u1_ex4_a",
            question = "a- Moustafa est :",
            options = listOf("5) des monuments français", "1) au bord de la mer", "2) pendant la Soirée", "3) à la Mer Rouge.", "4) L'ami de Gamal."),
            correctIndex = 4,
            pageReference = "",
            explanation = "Moustafa est l'ami de Gamal.",
            arabicTranslation = "مصطفى يكون ➔ 4) صديق جمال (L'ami de Gamal)."
        ),
        QuizQuestion(
            id = "u1_ex4_b",
            question = "b- Gamal a passé ses vacances :",
            options = listOf("1) au bord de la mer", "3) à la Mer Rouge.", "2) pendant la Soirée", "4) L'ami de Gamal.", "5) des monuments français"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Gamal a passé ses vacances à la Mer Rouge.",
            arabicTranslation = "جمال قضى إجازته ➔ 3) في البحر الأحمر (à la Mer Rouge)."
        ),
        QuizQuestion(
            id = "u1_ex4_c",
            question = "c- Le livre d'histoire parle :",
            options = listOf("2) pendant la Soirée", "3) à la Mer Rouge.", "1) au bord de la mer", "5) des monuments français", "4) L'ami de Gamal."),
            correctIndex = 3,
            pageReference = "",
            explanation = "Le livre d'histoire parle des monuments français.",
            arabicTranslation = "كتاب التاريخ يتحدث عن ➔ 5) المعالم والآثار الفرنسية (des monuments français)."
        ),
        QuizQuestion(
            id = "u1_ex4_d",
            question = "d- Moustafa n'est pas allé :",
            options = listOf("1) au bord de la mer", "3) à la Mer Rouge.", "4) L'ami de Gamal.", "2) pendant la Soirée", "5) des monuments français"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Moustafa n'a pas eu de vacances, il n'est pas allé au bord de la mer.",
            arabicTranslation = "مصطفى لم يذهب ➔ 1) إلى شاطئ البحر (au bord de la mer)."
        ),
        QuizQuestion(
            id = "u1_ex4_e",
            question = "e- Les amis ont beaucoup parlé :",
            options = listOf("3) à la Mer Rouge.", "1) au bord de la mer", "5) des monuments français", "4) L'ami de Gamal.", "2) pendant la Soirée"),
            correctIndex = 4,
            pageReference = "",
            explanation = "Les amis ont beaucoup parlé pendant la Soirée.",
            arabicTranslation = "الأصدقاء تحدثوا كثيراً ➔ 2) أثناء السهرة (pendant la Soirée)."
        )
    )

    val unit1Ex5Complete = listOf(
        QuizQuestion(
            id = "u1_ex5_1",
            question = "1. Gamal a reçu jean Morelle, sa ______ et son ______ Moustafa chez lui.",
            options = listOf("femme (Suzanne) / ami", "sœur / cousin", "mère / frère"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Gamal a reçu Jean Morelle, sa femme Suzanne et son ami Moustafa.",
            arabicTranslation = "استقبل جمال جان موريل وزوجته (سوزان) وصديقه مصطفى في منزله."
        ),
        QuizQuestion(
            id = "u1_ex5_2",
            question = "2. IL était content parce que jean Morelle lui a offert ______",
            options = listOf("une voiture", "un livre d'histoire", "des gâteaux"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Jean Morelle lui a offert un livre d'histoire sur les monuments français.",
            arabicTranslation = "كان سعيداً لأن جان موريل أهداه كتاب تاريخ عن المعالم الفرنسية."
        ),
        QuizQuestion(
            id = "u1_ex5_3",
            question = "3. Gamal, qui est professeur, a passé ______ au bord de la mer ______ avec ses ______",
            options = listOf("ses vacances / Rouge / élèves", "la soirée / Noire / amis", "l'été / Morte / voisins"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Gamal a passé ses vacances au bord de la mer Rouge avec ses élèves.",
            arabicTranslation = "جمال (الأستاذ) قضى إجازته على شاطئ البحر الأحمر مع تلاميذه."
        )
    )

    val unit1ComprehensionQuestions = unit1Ex2RepondsAuxQuestions + unit1Ex3VraiOuFaux + unit1Ex4Associe + unit1Ex5Complete

    val pronounExercises = listOf(
        PronounExercise(
            phrase = "1. Il regarde le match.",
            choices = listOf("la", "le", "lui"),
            correct = "le",
            explanation = "« le match » est masculin singulier direct ➔ Il le regarde.",
            arabicTranslation = "هو يشاهد المباراة. (مباشر مفرد مذكر بدون حرف جر ➔ نضع le)"
        ),
        PronounExercise(
            phrase = "2. Je parle à mon ami.",
            choices = listOf("le", "lui", "leur"),
            correct = "lui",
            explanation = "« à mon ami » = à + personne au singulier ➔ Je lui parle.",
            arabicTranslation = "أنا أتحدث إلى صديقي. (غير مباشر: à + مفرد ➔ نضع lui)"
        ),
        PronounExercise(
            phrase = "3. Il téléphone à ses parents.",
            choices = listOf("les", "lui", "leur"),
            correct = "leur",
            explanation = "« à ses parents » = à + personnes au pluriel ➔ Il leur téléphone.",
            arabicTranslation = "هو يتصل بوالديه. (غير مباشر: à + جمع ➔ نضع leur)"
        ),
        PronounExercise(
            phrase = "4. Ali mange la pomme.",
            choices = listOf("lui", "la", "le"),
            correct = "la",
            explanation = "« la pomme » est féminin singulier direct ➔ Ali la mange.",
            arabicTranslation = "علي يأكل التفاحة. (مباشر مفرد مؤنث ➔ نضع la)"
        )
    )

    val placesCharacters = listOf(
        CharacterPlacePair("Un client / garçon", "au restaurant / au café", "👨‍🍳", "زبون / نادل ➔ في المطعم أو المقهى"),
        CharacterPlacePair("Un professeur / élève", "à l'école / en classe", "👨‍🏫", "معلم / طالب ➔ في المدرسة أو الفصل"),
        CharacterPlacePair("Un médecin / malade", "à l'hôpital / clinique", "👨‍⚕️", "طبيب / مريض ➔ في المستشفى أو العيادة"),
        CharacterPlacePair("Un pilote / hôtesse", "à l'aéroport", "✈️", "طيار / مضيفة ➔ في المطار"),
        CharacterPlacePair("Un guichetier", "au guichet / au cinéma", "🎟️", "موظف شباك التذاكر ➔ عند الشباك أو السينما"),
        CharacterPlacePair("Un journaliste", "au journal / au stade", "📰", "صحفي ➔ في الجريدة أو الاستاد")
    )

    val placesQuestions = listOf(
        QuizQuestion(
            id = "u1_p1",
            question = "1. Où vas-tu pour voir un film ?",
            options = listOf("à l'hôpital", "au cinéma", "au club"),
            correctIndex = 1,
            pageReference = "p. 25",
            explanation = "Pour voir un film, on va au cinéma.",
            arabicTranslation = "أين تذهب لمشاهدة فيلم؟ ➔ في السينما (au cinéma)."
        ),
        QuizQuestion(
            id = "u1_p2",
            question = "2. Où vas-tu pour consulter le médecin ?",
            options = listOf("au stade", "au musée", "à l'hôpital"),
            correctIndex = 2,
            pageReference = "p. 25",
            explanation = "Pour consulter le médecin, on va à l'hôpital.",
            arabicTranslation = "أين تذهب لاستشارة الطبيب؟ ➔ في المستشفى (à l'hôpital)."
        ),
        QuizQuestion(
            id = "u1_p3",
            question = "3. Où vas-tu pour faire du sport ?",
            options = listOf("au club", "à la pharmacie", "à la gare"),
            correctIndex = 0,
            pageReference = "p. 25",
            explanation = "Pour faire du sport, on va au club.",
            arabicTranslation = "أين تذهب لممارسة الرياضة؟ ➔ في النادي (au club)."
        ),
        QuizQuestion(
            id = "u1_p4",
            question = "4. Où vas-tu pour acheter des médicaments ?",
            options = listOf("au café", "à la pharmacie", "à l'aéroport"),
            correctIndex = 1,
            pageReference = "p. 25",
            explanation = "Pour acheter des médicaments, on va à la pharmacie.",
            arabicTranslation = "أين تذهب لشراء الأدوية؟ ➔ في الصيدلية (à la pharmacie)."
        )
    )

    val unit2FullText = "Unité 2 : Repas & Restaurant. Le 15 juin à l'occasion de leur fête de mariage, Jean Morelle et sa femme ont invité des amis à dîner à 22 heures. Ils ont réservé une table pour 20 personnes dans un grand restaurant. De retour chez lui, Gamal a parlé de la soirée avec ses parents. Le père : Ça s'est bien passé ? Gamal : Oui, J'ai rencontré les amis français de Jean, des journalistes et des stagiaires d'El Ahram Hebdo on a très bien mangé. Le père : Qu'est-ce qu'on a servi ? Gamal : Comme entrée, on a servi des crudités : des tomates, des concombres en salade et des laitues. Comme plat principal : du veau ou du poulet avec des frites et des légumes. Et comme dessert : des fruits de toutes sortes et un gâteau au chocolat en forme de pyramide avec 5 bougies. La mère : Et tu as bien mangé ? Gamal : Bien sûr."
    val unit2FullTextArabic = "الوحدة الثانية : الوجبات والمطعم. في 15 يونيو بمناسبة عيد زواجهما، دعا جان موريل وزوجته الأصدقاء لتناول العشاء في الساعة العاشرة مساءً (22h). وحجزوا طاولة لـ 20 شخصاً في مطعم كبير. ولدى عودته، تحدث جمال عن السهرة مع والديه. الأب: هل سارت الأمور بخير؟ جمال: نعم، قابلت أصدقاء جان الفرنسيين، صحفيين ومتدربين بجريدة الأهرام إبدو، وأكلنا بشكل ممتاز. الأب: ماذا قُدِّم في العشاء؟ جمال: كمقبلات قُدِّمت خضروات طازجة (طماطم وخيار سلطة وخس)، وطبق رئيسي لحم بتلو أو دجاج مع بطاطس وخضار، والتحلية فواكه وكعكة شوكولاتة على شكل هرم بـ 5 شمعات. الأم: وهل أكلت جيداً؟ جمال: بكل تأكيد!"

    val unit2Dialogues = listOf(
        DialogueLine(
            speaker = "Récit du texte (ص 35)",
            text = "« Le 15 juin à l'occasion de leur fête de mariage, Jean Morelle et sa femme ont invité des amis à dîner à 22 heures. Ils ont réservé une table pour 20 personnes dans un grand restaurant. De retour chez lui, Gamal a parlé de la soirée avec ses parents. »",
            arabicNote = "في 15 يونيو بمناسبة عيد زواجهما، دعا جان موريل وزوجته أصدقاءهما لتناول العشاء في الساعة العاشرة مساءً (22h). وحجزوا طاولة لـ 20 شخصاً في مطعم كبير. ولدى عودته إلى المنزل، تحدث جمال عن السهرة مع والديه. 🍽️",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Le père",
            text = "« Ça s'est bien passé ? »",
            arabicNote = "هل سارت الأمور على ما يرام؟ (كيف كانت السهرة؟) 👨",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal",
            text = "« Oui, J'ai rencontré les amis français de Jean, des journalistes et des stagiaires d'El Ahram Hebdo on a très bien mangé. »",
            arabicNote = "نعم، لقد التقيت بأصدقاء جان الفرنسيين، وهم صحفيون ومتدربون بجريدة الأهرام إبدو (El Ahram Hebdo)، وقد تناولنا طعاماً لذيذاً جداً. 📰",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Le père",
            text = "« Qu'est – ce qu'on a servi ? »",
            arabicNote = "ماذا قُدِّم في قائمة الطعام؟ ❓",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal (Menu du dîner)",
            text = "« Comme entrée, on a servi des crudités : des tomates, des concombres en salade et des laitues. Comme plat principal : du veau ou du poulet avec des frites et des légumes. Et comme dessert : des fruits de toutes sortes et un gâteau au chocolat en forme de pyramide avec 5 bougies. »",
            arabicNote = "كمقبلات: قُدِّمت خضروات طازجة (طماطم، خيار سلطة، وخس). كطبق رئيسي: لحم بتلو (عجل) أو دجاج مع بطاطس مقلية وخضار. وللتحلية: فواكه مشكلة وتورتة شوكولاتة على شكل هرم بـ 5 شمعات. 🥗🥩🍗🍟🍫🎂🕯️",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "La mère",
            text = "« Et tu as bien mangé ? »",
            arabicNote = "وهل أكلت جيداً؟ 👩",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal",
            text = "« Bien sûr. »",
            arabicNote = "بالتأكيد! (طبعاً). 👍",
            borderColorHex = 4283385573L
        )
    )

    val unit2Ex1BonGroupe = listOf(
        QuizQuestion(
            id = "u2_ex1_1",
            question = "1. Jean et Suzanne fêtent leur mariage ....................",
            options = listOf("a) Le 15 juin.", "b) Le 20 juin.", "c) Le 22 juin."),
            correctIndex = 0,
            pageReference = "",
            explanation = "Le 15 juin à l'occasion de leur fête de mariage.",
            arabicTranslation = "يحتفل جان وسوزان بعيد زواجهما ➔ في 15 يونيو (Le 15 juin)."
        ),
        QuizQuestion(
            id = "u2_ex1_2",
            question = "2. Jean et Suzanne invitent leurs amis à dîner ....................",
            options = listOf("a) à El Ahram Hebo.", "b) Chez eux.", "c) dans un restaurant."),
            correctIndex = 2,
            pageReference = "",
            explanation = "Ils ont réservé une table pour 20 personnes dans un grand restaurant.",
            arabicTranslation = "دعا جان وسوزان أصدقائهما للعشاء ➔ في مطعم (dans un restaurant)."
        ),
        QuizQuestion(
            id = "u2_ex1_3",
            question = "3. Comme plat principal on a mangé ....................",
            options = listOf("a) des concombres en salade.", "b) de la viande avec des légumes.", "c) des fruits et gâteaux."),
            correctIndex = 1,
            pageReference = "",
            explanation = "Comme plat principal : du veau ou du poulet avec des frites et des légumes (de la viande avec des légumes).",
            arabicTranslation = "كتصنيف للطبق الرئيسي تناولوا ➔ اللحم مع الخضار (de la viande avec des légumes)."
        ),
        QuizQuestion(
            id = "u2_ex1_4",
            question = "4. Gamal a raconté la soirée ....................",
            options = listOf("a) à ses amis.", "b) aux journalistes.", "c) à son père et sa mère."),
            correctIndex = 2,
            pageReference = "",
            explanation = "De retour chez lui, Gamal a parlé de la soirée avec ses parents (son père et sa mère).",
            arabicTranslation = "روى جمال أحداث السهرة ➔ لوالده ووالدته (à son père et sa mère)."
        )
    )

    val unit2Ex2VraiOuFaux = listOf(
        QuizQuestion(
            id = "u2_ex2_a",
            question = "a) Au dîner, il y a 20 personnes.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Vrai : « Ils ont réservé une table pour 20 personnes ».",
            arabicTranslation = "في العشاء يوجد 20 شخصاً ➔ [صح Vrai]. حجزوا طاولة لعشرين شخصاً."
        ),
        QuizQuestion(
            id = "u2_ex2_b",
            question = "b) Au menu, il y a de la viande.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Vrai : Le veau (لحم العجل) et le poulet sont de la viande.",
            arabicTranslation = "في قائمة الطعام يوجد لحم ➔ [صح Vrai]. تم تقديم لحم العجل du veau والدجاج."
        ),
        QuizQuestion(
            id = "u2_ex2_c",
            question = "c) De ce restaurant, on voit les Pyramides.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : Le gâteau est en forme de pyramide, mais le texte ne dit pas qu'on voit les pyramides depuis le restaurant.",
            arabicTranslation = "من هذا المطعم نرى الأهرامات ➔ [خطأ Faux]. الكعكة على شكل هرم وليس المطعم يطل على الأهرامات."
        ),
        QuizQuestion(
            id = "u2_ex2_d",
            question = "d) Le 5 juin, C'est la fête de mariage de Jean et sa femme.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : La fête de mariage a lieu le 15 juin (et non le 5 juin).",
            arabicTranslation = "في 5 يونيو عيد زواج جان وزوجته ➔ [خطأ Faux]. التاريخ الصحيح هو 15 يونيو (Le 15 juin)."
        ),
        QuizQuestion(
            id = "u2_ex2_e",
            question = "e) On a mangé un gâteau aux fruits.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : C'est « un gâteau au chocolat en forme de pyramide » (les fruits étaient à part).",
            arabicTranslation = "أكلوا كعكة بالفواكه ➔ [خطأ Faux]. الكعكة كانت بالشوكولاتة (au chocolat) والفواكه قُدمت كصنف مستقل."
        )
    )

    val unit2Ex3RepondsPrisDuTexte = listOf(
        QuizQuestion(
            id = "u2_ex3_a",
            question = "a) Où travaillent les journalistes, amis de Jean ?",
            options = listOf("À l'hôpital", "À El Ahram Hebdo", "Au restaurant"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Ils travaillent à « El Ahram Hebdo » (جريدة الأهرام إبدو).",
            arabicTranslation = "أين يعمل الصحفيون أصدقاء جان؟ ➔ في جريدة الأهرام إبدو (À El Ahram Hebdo)."
        ),
        QuizQuestion(
            id = "u2_ex3_b",
            question = "b) De quelle fête on parle ?",
            options = listOf("De la fête de mariage de Jean et sa femme", "De la fête du Nouvel An", "De la fête d'anniversaire"),
            correctIndex = 0,
            pageReference = "",
            explanation = "On parle de la fête de mariage de Jean Morelle et sa femme.",
            arabicTranslation = "عن أي حفلة نتحدث؟ ➔ عن حفلة عيد زواج جان وزوجته."
        ),
        QuizQuestion(
            id = "u2_ex3_c",
            question = "c) Pour quelle heure Jean a réservé la table ?",
            options = listOf("À 20 heures", "À 22 heures (dix heures du soir)", "À 12 heures (midi)"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Jean a invité des amis à dîner à 22 heures.",
            arabicTranslation = "لأي ساعة حجز جان الطاولة؟ ➔ الساعة 22:00 (العاشرة مساءً)."
        ),
        QuizQuestion(
            id = "u2_ex3_d",
            question = "d) Au menu qu'est – ce qu'il y a comme crudités ?",
            options = listOf("Des tomates, des concombres en salade et des laitues", "Des frites et des légumes", "Des fruits de toutes sortes"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Comme entrée, on a servi des crudités : des tomates, des concombres en salade et des laitues.",
            arabicTranslation = "ماذا يوجد في القائمة كمقبلات سلطة خضراء؟ ➔ طماطم، خيار سلطة، وخس."
        ),
        QuizQuestion(
            id = "u2_ex3_e",
            question = "e) Combien de bougies il y a sur le gâteau ?",
            options = listOf("15 bougies", "5 bougies (cinq bougies)", "20 bougies"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Il y a un gâteau au chocolat en forme de pyramide avec 5 bougies.",
            arabicTranslation = "كم شمعة توجد على الكعكة؟ ➔ 5 شمعات (5 bougies)."
        )
    )

    val unit2Ex4Complete = listOf(
        QuizQuestion(
            id = "u2_ex4_1",
            question = "1- Le 15 juin, ____________ de la fête de mariage.",
            options = listOf("à l'Occasion", "une table", "ses parents"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Le 15 juin, à l'occasion de la fête de mariage.",
            arabicTranslation = "15 يونيو، [بمناسبة] عيد الزواج ➔ à l'Occasion"
        ),
        QuizQuestion(
            id = "u2_ex4_2",
            question = "2- Il a réservé ____________ pour cinq personnes.",
            options = listOf("ses amis", "une table", "des crudités"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Il a réservé une table (dans le texte pour 20 personnes).",
            arabicTranslation = "هو حجز [طاولة] ➔ une table"
        ),
        QuizQuestion(
            id = "u2_ex4_3",
            question = "3- Jean et sa femme ont invité ____________ à dîner.",
            options = listOf("ses parents", "une table", "ses amis"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Jean et sa femme ont invité ses amis à dîner.",
            arabicTranslation = "جان وزوجته دعوا [أصدقاءه] للعشاء ➔ ses amis"
        ),
        QuizQuestion(
            id = "u2_ex4_4",
            question = "4- Gamal a parlé de la fête avec ____________",
            options = listOf("ses parents", "des crudités", "ses amis"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Gamal a parlé de la soirée avec ses parents.",
            arabicTranslation = "تحدث جمال عن الحفلة مع [والديه] ➔ ses parents"
        ),
        QuizQuestion(
            id = "u2_ex4_5",
            question = "5- Jean et Suzanne fêtent leur mariage ____________",
            options = listOf("à l'Occasion", "ses amis", "Le 15 juin"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Jean et Suzanne fêtent leur mariage Le 15 juin.",
            arabicTranslation = "جان وسوزان يحتفلان بعيد زواجهما [في 15 يونيو] ➔ Le 15 juin"
        ),
        QuizQuestion(
            id = "u2_ex4_6",
            question = "6- Comme entrée, on a servi ____________",
            options = listOf("une table", "des crudités", "Le 15 juin"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Comme entrée, on a servi des crudités (tomates, concombres, laitues).",
            arabicTranslation = "كمقبلات، تم تقديم [خضروات طازجة / سلطات خضراء] ➔ des crudités"
        )
    )

    val unit2VocabWords = listOf(
        VocabWord(
            id = "u2_v1",
            french = "à l'occasion de",
            arabic = "بمناسبة",
            category = VocabCategory.EXPRESSION,
            exampleFr = "À l'occasion de leur fête de mariage, ils ont invité des amis.",
            exampleAr = "بمناسبة حفل عيد زواجهما، دعوا الأصدقاء.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v2",
            french = "la fête de mariage",
            arabic = "عيد الزواج",
            category = VocabCategory.FEMININE,
            exampleFr = "Jean et Suzanne célèbrent la fête de mariage.",
            exampleAr = "جان وسوزان يحتفلان بعيد زواجهما.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v3",
            french = "Une femme",
            arabic = "زوجة / سيدة",
            category = VocabCategory.FEMININE,
            exampleFr = "Jean Morelle et sa femme ont réservé une table.",
            exampleAr = "جان موريل وزوجته حجزا طاولة.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v4",
            french = "Une soirée",
            arabic = "أمسية / سهرة",
            category = VocabCategory.FEMININE,
            exampleFr = "Gamal a parlé de la soirée avec ses parents.",
            exampleAr = "تحدث جمال عن السهرة مع والديه.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v5",
            french = "Un invité",
            arabic = "مدعو",
            category = VocabCategory.MASCULINE,
            exampleFr = "Chaque invité a passé une bonne soirée.",
            exampleAr = "كل مدعو قضى سهرة ممتعة.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v6",
            french = "Une invitation",
            arabic = "دعوة",
            category = VocabCategory.FEMININE,
            exampleFr = "J'ai reçu une invitation pour la fête.",
            exampleAr = "استلمت دعوة للحفلة.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v7",
            french = "Une pyramide",
            arabic = "هرم",
            category = VocabCategory.FEMININE,
            exampleFr = "Un gâteau au chocolat en forme de pyramide.",
            exampleAr = "تورتة شوكولاتة على شكل هرم.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v8",
            french = "Une bougie",
            arabic = "شمعة",
            category = VocabCategory.FEMININE,
            exampleFr = "Le gâteau est décoré avec 5 bougies.",
            exampleAr = "التورتة مزينة بـ 5 شمعات.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v9",
            french = "Comme entrée",
            arabic = "فاتح شهية / كمقبلات",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Comme entrée, on a servi des crudités.",
            exampleAr = "كمقبلات، قُدمت خضروات طازجة وسلطات.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v10",
            french = "Comme plat principal",
            arabic = "الطبق الرئيسي",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Comme plat principal, du veau ou du poulet.",
            exampleAr = "كطبق رئيسي، لحم بتلو أو دجاج.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v11",
            french = "Comme dessert",
            arabic = "الحلوى / للتحلية",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Comme dessert, des fruits et un gâteau.",
            exampleAr = "للتحلية، فواكه وتورتة.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v12",
            french = "Magnifique",
            arabic = "رائع",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Le restaurant est magnifique.",
            exampleAr = "المطعم رائع جداً.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v13",
            french = "En Forme de",
            arabic = "على شكل",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Le gâteau est en forme de pyramide.",
            exampleAr = "التورتة على شكل هرم.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v14",
            french = "Fêter",
            arabic = "يحتفل بـ",
            category = VocabCategory.VERB,
            exampleFr = "Jean et sa femme fêtent leur anniversaire de mariage.",
            exampleAr = "جان وزوجته يحتفلان بعيد زواجهما.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v15",
            french = "se passer",
            arabic = "يحدث / يجري",
            category = VocabCategory.VERB,
            exampleFr = "Ça s'est très bien passé hier soir.",
            exampleAr = "سارت الأمور بشكل رائع أمس.",
            phoneticOrNote = "Verbe pronominal",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v16",
            french = "Réserver",
            arabic = "يحجز",
            category = VocabCategory.VERB,
            exampleFr = "Jean a réservé une table pour 20 personnes.",
            exampleAr = "حجز جان طاولة لعشرين شخصاً.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v17",
            french = "manger",
            arabic = "يأكل",
            category = VocabCategory.VERB,
            exampleFr = "On a très bien mangé au restaurant.",
            exampleAr = "تناولنا طعاماً لذيذاً في المطعم.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v18",
            french = "inviter",
            arabic = "يدعو",
            category = VocabCategory.VERB,
            exampleFr = "Jean a invité ses amis français à dîner.",
            exampleAr = "دعا جان أصدقاءه الفرنسيين للعشاء.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v19",
            french = "prendre",
            arabic = "يأخذ / يتناول",
            category = VocabCategory.VERB,
            exampleFr = "Je prends le dîner à 22 heures.",
            exampleAr = "أتناول العشاء في العاشرة مساءً.",
            phoneticOrNote = "3ème groupe (irrégulier)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v20",
            french = "rencontrer",
            arabic = "يقابل",
            category = VocabCategory.VERB,
            exampleFr = "Gamal a rencontré les amis de Jean.",
            exampleAr = "قابل جمال أصدقاء جان.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v21",
            french = "boire",
            arabic = "يشرب",
            category = VocabCategory.VERB,
            exampleFr = "Il boit de l'eau minérale fraîche.",
            exampleAr = "هو يشرب ماءً معدنياً منعشاً.",
            phoneticOrNote = "3ème groupe (irrégulier)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v22",
            french = "raconter",
            arabic = "يحكى / يروي",
            category = VocabCategory.VERB,
            exampleFr = "Gamal raconte la soirée à ses parents.",
            exampleAr = "جمال يروي أحداث السهرة لوالديه.",
            phoneticOrNote = "1er groupe (-er)",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v23",
            french = "servir",
            arabic = "يقدم / يخدم",
            category = VocabCategory.VERB,
            exampleFr = "Le garçon sert les plats aux clients.",
            exampleAr = "النادل يقدم الأطباق للزبائن.",
            phoneticOrNote = "3ème groupe",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v24",
            french = "Un Client",
            arabic = "زبون",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Le client demande l'addition au restaurant.",
            exampleAr = "الزبون يطلب الفاتورة في المطعم.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v25",
            french = "Un garçon",
            arabic = "جرسون / نادل",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Le garçon apporte le menu et l'eau.",
            exampleAr = "الجرسون يُحضر القائمة والماء.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v26",
            french = "Un vendeur",
            arabic = "بائع",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Le vendeur travaille dans un magasin.",
            exampleAr = "البائع يعمل في المحل التجاري.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v27",
            french = "Un Touriste",
            arabic = "سائح",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Le touriste prend des photos des pyramides.",
            exampleAr = "السائح يلتقط صوراً للأهرامات.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v28",
            french = "Un guide",
            arabic = "مرشد سياحي",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Le guide explique l'histoire du monument.",
            exampleAr = "المرشد يشرح تاريخ الأثر.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v29",
            french = "Un invité",
            arabic = "مدعو",
            category = VocabCategory.PERSONNAGE,
            exampleFr = "Chaque invité félicite les mariés.",
            exampleAr = "كل مدعو يُهنئ العروسين.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v30",
            french = "Au restaurant",
            arabic = "في المطعم",
            category = VocabCategory.LIEU,
            exampleFr = "Nous dînons au restaurant ce soir.",
            exampleAr = "نتناول العشاء في المطعم هذا المساء.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v31",
            french = "à l'hôtel",
            arabic = "في الفندق",
            category = VocabCategory.LIEU,
            exampleFr = "Les touristes réservent une chambre à l'hôtel.",
            exampleAr = "السياح يحجزون غرفة في الفندق.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v32",
            french = "Au magasin",
            arabic = "في المحل",
            category = VocabCategory.LIEU,
            exampleFr = "On achète des vêtements au magasin.",
            exampleAr = "نشتري ملابس من المحل.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v33",
            french = "au musée",
            arabic = "في المتحف",
            category = VocabCategory.LIEU,
            exampleFr = "On voit les antiquités au musée égyptien.",
            exampleAr = "نرى الآثار في المتحف المصري.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v34",
            french = "Au café",
            arabic = "في القهوة / المقهى",
            category = VocabCategory.LIEU,
            exampleFr = "Il boit un jus d'orange au café.",
            exampleAr = "يشرب عصير برتقال في المقهى.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        ),
        VocabWord(
            id = "u2_v35",
            french = "au club",
            arabic = "في النادي",
            category = VocabCategory.LIEU,
            exampleFr = "Je joue au football au club avec mes amis.",
            exampleAr = "ألعب كرة القدم في النادي مع أصدقائي.",
            phoneticOrNote = "",
            pageReference = "p. 38"
        )
    )

    val partitiveQuestions = listOf(
        QuizQuestion(
            id = "u2_part1",
            question = "1. Au petit déjeuner, je prends ______ fromage et ______ confiture.",
            options = listOf("du / de la", "du / des", "des / de l'"),
            correctIndex = 0,
            pageReference = "",
            explanation = "fromage est masculin (du) et confiture est féminin (de la).",
            arabicTranslation = "في وجبة الإفطار، أتناول [جبناً] و[مربى]. الجبن مذكر (du) والمربى مؤنث (de la)."
        ),
        QuizQuestion(
            id = "u2_part2",
            question = "2. Je bois ______ eau minérale.",
            options = listOf("du", "de l'", "de la"),
            correctIndex = 1,
            pageReference = "",
            explanation = "« eau » commence par une voyelle ➔ de l'eau.",
            arabicTranslation = "أنا أشرب [ماء معدنياً]. كلمة eau تبدأ بحرف متحرك ➔ نختار de l'."
        ),
        QuizQuestion(
            id = "u2_part3",
            question = "3. À la forme négative : Je ne mange pas ______ viande.",
            options = listOf("de la", "des", "de"),
            correctIndex = 2,
            pageReference = "",
            explanation = "En négation, les partitifs deviennent « de » ou « d' ».",
            arabicTranslation = "في صيغة النفي: أنا لا آكل [لحماً]. أدوات التجزئة تتحول إلى de في النفي."
        ),
        QuizQuestion(
            id = "u2_part4",
            question = "4. Verbe de goût : J'adore ______ chocolat.",
            options = listOf("un", "le", "du"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Avec les verbes d'appréciation (aimer, adorer...), on emploie les articles définis (le, la, les).",
            arabicTranslation = "فعل تفضيل ومشاعر: أنا أعشق [الشوكولاتة]. مع أفعال الميول نستخدم أدوات المعرفة (le)."
        )
    )

    val interrogativeQuestions = listOf(
        QuizQuestion(
            id = "u2_int1",
            question = "1. ______ vas-tu le vendredi ? - Je vais au club.",
            options = listOf("Quand", "Qui", "Où"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Pour demander le lieu (au club), on utilise « Où ».",
            arabicTranslation = "[أين] تذهب يوم الجمعة؟ - أذهب إلى النادي. (للسؤال عن المكان: Où)"
        ),
        QuizQuestion(
            id = "u2_int2",
            question = "2. ______ est absent aujourd'hui ? - Ali est absent.",
            options = listOf("Où", "Qui", "Que"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Pour demander la personne (Ali), on utilise « Qui ».",
            arabicTranslation = "[مَن] غائب اليوم؟ - علي غائب. (للسؤال عن العاقل: Qui)"
        ),
        QuizQuestion(
            id = "u2_int3",
            question = "3. ______ fais-tu le soir ? - Je fais mon devoir.",
            options = listOf("Que", "Où", "Comment"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Pour demander l'action/chose, on utilise « Que » ou « Qu'est-ce que ».",
            arabicTranslation = "[ماذا] تفعل في المساء؟ - أعمل واجبي. (للسؤال عن الشيء: Que)"
        ),
        QuizQuestion(
            id = "u2_int4",
            question = "4. ______ vas-tu à l'école ? - En voiture.",
            options = listOf("Quand", "Comment", "Pourquoi"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Pour demander le moyen de transport, on utilise « Comment ».",
            arabicTranslation = "[كيف] تذهب إلى المدرسة؟ - بالسيارة. (للسؤال عن وسيلة المواصلات: Comment)"
        )
    )

    val unit3FullText = "Unité 3 : Santé / Hôpital. Samir, l'ami de Gamal a eu un accident dans la rue. L'ambulance l'a vite transporté à l'hôpital. Gamal et Ali sont allés le voir. Dans sa chambre à l'hôpital, Samir était couché. Il avait l'air fatigué et il parlait difficilement. Gamal : Ça va bien maintenant ? Samir : Oui, merci. Gamal : Qu'est-ce qui est arrivé ? Samir : Une voiture m'a renversé et je suis tombé. À ce moment, le médecin et l'infirmière sont entrés. Le médecin : Ça va aujourd'hui ? Samir : Oui docteur, mais j'ai encore mal au bras et à la jambe. Le médecin l'a examiné, il a lu la radiographie et le rapport et lui a dit : Ne t'inquiète pas, repose-toi bien, et prends tes médicaments. Je te conseille de prendre encore ce calmant. Tu peux marcher dans quelques jours. Quand le médecin est sorti, Gamal et Ali ont salué leur ami et sont rentrés chez eux."
    val unit3FullTextArabic = "الوحدة الثالثة : الصحة والمستشفى. سمير، صديق جمال، تعرض لحادث في الشارع. ونقلته سيارة الإسعاف سريعاً إلى المستشفى. ذهب جمال وعلي لزيارته. في غرفته بالمستشفى، كان سمير مستلقياً على السرير. كان يبدو عليه التعب وكان يتحدث بصعوبة. جمال: هل أنت بخير الآن؟ سمير: نعم، شكراً. جمال: ماذا حدث؟ سمير: صدمتني سيارة وسقطت أرضاً. في هذه اللحظة، دخل الطبيب والممرضة. الطبيب: كيف حالك اليوم؟ سمير: نعم يا دكتور، لكن ما زال ذراعي وساقي يؤلماني. فحصه الطبيب، وقرأ الأشعة والتقرير الطبي وقال له: «لا تقلق، استرح جيداً، وتناول أدويتك. أنصحك أيضاً بأخذ هذا المسكن. يمكنك المشي خلال بضعة أيام». وعندما خرج الطبيب، ودع جمال وعلي صديقهما وعادا إلى منزليهما."
    val unit3Page59Text = "Samir, l'ami de Gamal a eu un accident dans la rue. L'ambulance l'a vite transporté à l'hôpital. Gamal et Ali sont allés le voir. Dans sa chambre à l'hôpital, Samir était couché. Il avait l'air fatigué et il parlait difficilement.\n- Gamal : Ça va bien maintenant?\n- Samir : Oui, merci.\n- Gamal : Qu'est-ce qui est arrivé?\n- Samir : Une voiture m'a renversé et je suis tombé."
    val unit3Page59TextArabic = "سمير، صديق جمال، تعرض لحادث في الشارع. نقلته سيارة الإسعاف سريعاً إلى المستشفى. ذهب جمال وعلي لزيارته. في غرفته بالمستشفى، كان سمير مستلقياً على السرير. كان يبدو متعباً ويتحدث بصعوبة.\n- جمال: هل أنت بخير الآن؟\n- سمير: نعم، شكراً.\n- جمال: ماذا حدث؟\n- سمير: صدمتني سيارة وسقطت أرضاً."
    val unit3Page60Text = "À ce moment, le médecin et l'infirmière sont entrés.\n- Le médecin : Ça va aujourd'hui?\n- Samir : Oui docteur, mais j'ai encore mal au bras et à la jambe.\nLe médecin l'a examiné, il a lu la radiographie et le rapport et lui a dit:\n\" Ne t'inquiète pas, repose-toi bien, et prends tes médicaments. Je te conseille de prendre encore ce calmant. Tu peux marcher dans quelques jours\".\nQuand le médecin est sorti, Gamal et Ali ont salué leur ami et sont rentrés chez eux."
    val unit3Page60TextArabic = "في هذه اللحظة، دخل الطبيب والممرضة.\n- الطبيب: كيف حالك اليوم؟\n- سمير: نعم يا دكتور، لكن ما زال يؤلمني ذراعي وساقي.\nفحصه الطبيب، وقرأ الأشعة والتقرير الطبي وقال له:\n\"لا تقلق، استرح جيداً، وتناول أدويتك. أنصحك أيضاً بتناول هذا المسكن. يمكنك المشي بعد بضعة أيام\".\nوعندما خرج الطبيب، سلّم جمال وعلي على صديقهما وعادا إلى منزليهما."

    val unit3Dialogues = listOf(
        DialogueLine(
            speaker = "Récit (ص 59)",
            text = "« Samir, l'ami de Gamal a eu un accident dans la rue. L'ambulance l'a vite transporté à l'hôpital. Gamal et Ali sont allés le voir. Dans sa chambre à l'hôpital, Samir était couché. Il avait l'air fatigué et il parlait difficilement. »",
            arabicNote = "سمير صديق جمال تعرض لحادث في الشارع. نقلته سيارة الإسعاف سريعاً للمستشفى. ذهب جمال وعلي لزيارته. كان مستلقياً في غرفته يبدو متعباً ويتكلم بصعوبة. 🚑",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal",
            text = "« Ça va bien maintenant? »",
            arabicNote = "هل أنت على ما يرام الآن؟ 🤝",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Samir",
            text = "« Oui, merci. »",
            arabicNote = "نعم، شكراً. 🛌",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Gamal",
            text = "« Qu'est-ce qui est arrivé? »",
            arabicNote = "ماذا حدث؟ (كيف وقع الحادث؟) ❓",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Samir",
            text = "« Une voiture m'a renversé et je suis tombé. »",
            arabicNote = "صدمتني سيارة وسقطت أرضاً. 🚗💥",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Récit (ص 60)",
            text = "« À ce moment, le médecin et l'infirmière sont entrés. »",
            arabicNote = "في هذه اللحظة، دخل الطبيب والممرضة إلى الغرفة. 👨‍⚕️👩‍⚕️",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Le médecin",
            text = "« Ça va aujourd'hui? »",
            arabicNote = "كيف حالك اليوم؟ 🩺",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Samir",
            text = "« Oui docteur, mais j'ai encore mal au bras et à la jambe. »",
            arabicNote = "نعم يا دكتور، لكن لا يزال ذراعي وساقي يؤلماني. 🤕",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Le médecin (نصائح الطبيب)",
            text = "« Ne t'inquiète pas, repose-toi bien, et prends tes médicaments. Je te conseille de prendre encore ce calmant. Tu peux marcher dans quelques jours. »",
            arabicNote = "لا تقلق، استرح جيداً، وتناول أدويتك. أنصحك بأخذ هذا المسكن مجدداً. يمكنك المشي خلال بضعة أيام. 💊",
            borderColorHex = 4283385573L
        ),
        DialogueLine(
            speaker = "Récit (خاتمة الزيارة)",
            text = "« Quand le médecin est sorti, Gamal et Ali ont salué leur ami et sont rentrés chez eux. »",
            arabicNote = "وعندما خرج الطبيب، ودع جمال وعلي صديقهما وعادا إلى منزليهما. 👋🏠",
            borderColorHex = 4283385573L
        )
    )

    val unit3Ex1VraiOuFaux = listOf(
        QuizQuestion(
            id = "u3_vf_1",
            question = "1. Ali et Gamal sont allés voir Samir chez lui.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : Ils sont allés le voir à l'hôpital (dans sa chambre à l'hôpital).",
            arabicTranslation = "علي وجمال ذهبا لرؤية سمير في بيته ➔ [خطأ Faux]. ذهبا لزيارته في المستشفى وليس في بيته."
        ),
        QuizQuestion(
            id = "u3_vf_2",
            question = "2. Le médecin a parlé à Samir.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Vrai : Le médecin a parlé à Samir : « Ça va aujourd'hui? » et lui a donné des conseils.",
            arabicTranslation = "تحدث الطبيب إلى سمير ➔ [صح Vrai]. سأله عن حاله وقدم له النصائح الطبية."
        ),
        QuizQuestion(
            id = "u3_vf_3",
            question = "3. Le médecin a donné à Samir quelques conseils.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Vrai : Le médecin lui a dit : « Ne t'inquiète pas, repose-toi bien, et prends tes médicaments. Je te conseille... ».",
            arabicTranslation = "أعطى الطبيب لسمير بعض النصائح ➔ [صح Vrai]. نصحه بالراحة وتناول الدواء والمسكن."
        ),
        QuizQuestion(
            id = "u3_vf_4",
            question = "4. L'infirmière a examiné le malade.",
            options = listOf("Vrai (صح) ✔️", "Faux (خطأ) ❌"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Faux : C'est le médecin qui a examiné le malade (« Le médecin l'a examiné »).",
            arabicTranslation = "الممرضة فحصت المريض ➔ [خطأ Faux]. الطبيب هو الذي فحصه وقرأ التقرير والأشعة."
        )
    )

    val unit3Ex2QuestionsReponses = listOf(
        QuizQuestion(
            id = "u3_q_1",
            question = "1. Où est Samir ?",
            options = listOf("À l'école", "Chez lui à la maison", "À l'hôpital (dans sa chambre)", "Au club de sport"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Samir est à l'hôpital (dans sa chambre à l'hôpital).",
            arabicTranslation = "أين يكون سمير؟ ➔ في المستشفى (في غرفته بالمستشفى)."
        ),
        QuizQuestion(
            id = "u3_q_2",
            question = "2. Pourquoi l'ambulance l'a transporté ?",
            options = listOf("Parce qu'il a eu un accident dans la rue (une voiture l'a renversé)", "Pour visiter un ami malade", "Pour acheter des médicaments", "Pour passer les vacances"),
            correctIndex = 0,
            pageReference = "",
            explanation = "L'ambulance l'a transporté à l'hôpital parce qu'il a eu un accident dans la rue (une voiture l'a renversé).",
            arabicTranslation = "لماذا نقلته سيارة الإسعاف؟ ➔ لأنه تعرض لحادث في الشارع (صدمته سيارة)."
        ),
        QuizQuestion(
            id = "u3_q_3",
            question = "3. Où a-t-il mal ?",
            options = listOf("À la tête et aux yeux", "Au ventre et au dos", "Au bras et à la jambe", "Aux dents et à la gorge"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Il a encore mal au bras et à la jambe.",
            arabicTranslation = "أين يشعر بالألم؟ ➔ في ذراعه وساقه (au bras et à la jambe)."
        ),
        QuizQuestion(
            id = "u3_q_4",
            question = "4. Que fait le médecin ?",
            options = listOf("Il conduit l'ambulance", "Il salue Samir et rentre chez lui", "Il prépare le repas du malade", "Il examine le malade, lit la radiographie et lui donne des conseils"),
            correctIndex = 3,
            pageReference = "",
            explanation = "Le médecin l'a examiné, il a lu la radiographie et le rapport et lui a donné des conseils.",
            arabicTranslation = "ماذا يفعل الطبيب؟ ➔ فحص المريض، وقرأ الأشعة والتقرير الطبي وقدم له النصائح."
        )
    )

    val bodyPainQuestions = listOf(
        QuizQuestion(
            id = "u3_p1",
            question = "1. J'ai mal ______ tête.",
            options = listOf("à la", "à l'", "aux", "au"),
            correctIndex = 0,
            pageReference = "",
            explanation = "tête est féminin singulier ➔ à la tête.",
            arabicTranslation = "أشعر بألم في الرأس. (الرأس tête مؤنث ➔ نختار à la)"
        ),
        QuizQuestion(
            id = "u3_p2",
            question = "2. Sami a mal ______ pieds.",
            options = listOf("au", "à l'", "aux", "à la"),
            correctIndex = 2,
            pageReference = "",
            explanation = "pieds est au pluriel ➔ aux pieds.",
            arabicTranslation = "سامي يشعر بألم في القدمين. (القدمين pieds جمع ➔ نختار aux)"
        ),
        QuizQuestion(
            id = "u3_p3",
            question = "3. Ma mère a mal ______ ventre.",
            options = listOf("aux", "au", "à l'", "à la"),
            correctIndex = 1,
            pageReference = "",
            explanation = "ventre est masculin singulier ➔ au ventre.",
            arabicTranslation = "أمي تشعر بألم في البطن. (البطن ventre مذكر ➔ نختار au)"
        ),
        QuizQuestion(
            id = "u3_p4",
            question = "4. Nous avons mal ______ estomac.",
            options = listOf("à l'", "aux", "à la", "au"),
            correctIndex = 0,
            pageReference = "",
            explanation = "estomac commence par une voyelle ➔ à l'estomac.",
            arabicTranslation = "نشعر بألم في المعدة. (المعدة estomac تبدأ بحرف متحرك ➔ نختار à l')"
        ),
        QuizQuestion(
            id = "u3_p5",
            question = "5. Farida a mal ______ yeux.",
            options = listOf("à l'", "aux", "au", "à la"),
            correctIndex = 1,
            pageReference = "",
            explanation = "yeux est au pluriel ➔ aux yeux.",
            arabicTranslation = "فريدة تشعر بألم في العينين. (العينين yeux جمع ➔ نختار aux)"
        )
    )

    val medicalSituations = listOf(
        QuizQuestion(
            id = "u3_med1",
            question = "1. Tu as très mal au bras, le médecin te dit :",
            options = listOf("Fais une radiographie.", "Mange une glace !", "Fais tes devoirs !"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Pour un bras blessé, on fait une radiographie.",
            arabicTranslation = "ذراعك يؤلمك بشدة، الطبيب يقول لك : (اعمل واجباتك! / قم بعمل أشعة. / تناول آيس كريم!)"
        ),
        QuizQuestion(
            id = "u3_med2",
            question = "2. Ton ami te demande ce que l'ambulancier fait :",
            options = listOf("Il enseigne la leçon.", "Il transporte les malades.", "Il examine les dents."),
            correctIndex = 1,
            pageReference = "",
            explanation = "L'ambulancier transporte les personnes malades ou blessées.",
            arabicTranslation = "صديقك يسألك عما يفعله المسعف : (ينقل المرضى. / يفحص الأسنان. / يشرح الدرس.)"
        ),
        QuizQuestion(
            id = "u3_med3",
            question = "3. Le médecin donne un conseil à Samir :",
            options = listOf("Repose-toi bien et prends tes médicaments.", "Va au cinéma !", "Fais attention au feu !"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Conseil du médecin : du repos et les médicaments à l'heure.",
            arabicTranslation = "الطبيب يقدم نصيحة لسمير : (اذهب إلى السينما! / ارتح جيداً وتناول أدويتك في موعدها. / انتبه للنار!)"
        )
    )

    val revisionEx1MetsAuPresent = listOf(
        QuizQuestion(
            id = "rev_ex1_1",
            question = "1- J' ______ un livre. (avoir)",
            options = listOf("a", "as", "ai"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Verbe avoir avec J' ➔ J'ai.",
            arabicTranslation = "تصريف فعل يملك avoir مع الضمير J' ➔ J'ai un livre (أنا أملك كتاباً)."
        ),
        QuizQuestion(
            id = "rev_ex1_2",
            question = "2- Tu ______ content. (être)",
            options = listOf("es", "est", "suis"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe être avec Tu ➔ Tu es.",
            arabicTranslation = "تصريف فعل الكينونة être مع الضمير Tu ➔ Tu es content (أنت سعيد)."
        ),
        QuizQuestion(
            id = "rev_ex1_3",
            question = "3- Je ______ aux élèves. (parler)",
            options = listOf("parlons", "parle", "parles"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Verbe parler (1er groupe) avec Je ➔ Je parle.",
            arabicTranslation = "تصريف فعل يتحدث parler (مجموعة أولى) مع Je ➔ Je parle (أنا أتحدث إلى التلاميذ)."
        ),
        QuizQuestion(
            id = "rev_ex1_4",
            question = "4- Nous ______ des gâteaux. (manger)",
            options = listOf("mangons", "mangez", "mangeons"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Verbe manger avec Nous ➔ Nous mangeons (on ajoute 'e' pour la prononciation).",
            arabicTranslation = "تصريف فعل يأكل manger مع Nous: نضيف حرف e قبل ons للحفاظ على النطق ➔ Nous mangeons (نحن نأكل كعكاً)."
        ),
        QuizQuestion(
            id = "rev_ex1_5",
            question = "5- Vous ______ la télé. (regarder)",
            options = listOf("regardez", "regardons", "regardent"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe regarder avec Vous ➔ Vous regardez.",
            arabicTranslation = "تصريف فعل يشاهد regarder مع Vous ➔ Vous regardez la télé (أنتم تشاهدون التلفاز)."
        ),
        QuizQuestion(
            id = "rev_ex1_6",
            question = "6- Héba ______ au ballon. (jouer)",
            options = listOf("joues", "joue", "jouent"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Héba (elle) avec verbe jouer ➔ joue.",
            arabicTranslation = "هبة (مفرد مؤنث elle) مع فعل يلعب jouer ➔ Héba joue au ballon (هبة تلعب بالكرة)."
        ),
        QuizQuestion(
            id = "rev_ex1_7",
            question = "7- Je ______ le devoir. (finir)",
            options = listOf("finis", "finit", "finissons"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe finir (2ème groupe) avec Je ➔ Je finis.",
            arabicTranslation = "تصريف فعل ينهي finir (مجموعة ثانية) مع Je ➔ Je finis le devoir (أنا أنهي الواجب)."
        ),
        QuizQuestion(
            id = "rev_ex1_8",
            question = "8- Ils ______ 16 ans. (avoir)",
            options = listOf("avons", "sont", "ont"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Verbe avoir pour l'âge avec Ils ➔ Ils ont.",
            arabicTranslation = "تصريف فعل يملك avoir للعمر مع الجمع Ils ➔ Ils ont 16 ans (هم عندهم 16 سنة)."
        ),
        QuizQuestion(
            id = "rev_ex1_9",
            question = "9- Nous ______ des filles. (être)",
            options = listOf("sommes", "sont", "êtes"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe être avec Nous ➔ Nous sommes.",
            arabicTranslation = "تصريف فعل الكينونة être مع Nous ➔ Nous sommes des filles (نحن فتيات)."
        ),
        QuizQuestion(
            id = "rev_ex1_10",
            question = "10- Ali ______ la chanson. (écouter)",
            options = listOf("écoutes", "écoutent", "écoute"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Ali (il) avec verbe écouter ➔ écoute.",
            arabicTranslation = "علي (مفرد مذكر il) مع فعل يستمع écouter ➔ Ali écoute la chanson (علي يستمع للأغنية)."
        )
    )

    val revisionEx2ChoisisBonneReponse = listOf(
        QuizQuestion(
            id = "rev_ex2_1",
            question = "1. Maintenant, le professeur ______ la leçon.",
            options = listOf("expliquez", "explique", "expliques"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Le professeur (il) explique.",
            arabicTranslation = "الآن، المعلم (il) يشرح الدرس ➔ le professeur explique la leçon."
        ),
        QuizQuestion(
            id = "rev_ex2_2",
            question = "2. Aujourd'hui, nous ______ au stade.",
            options = listOf("allons", "aller", "allez"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe aller avec nous ➔ nous allons.",
            arabicTranslation = "اليوم، نحن نذهب إلى الاستاد ➔ nous allons au stade."
        ),
        QuizQuestion(
            id = "rev_ex2_3",
            question = "3. Chaque jour, ils ______ leurs devoirs.",
            options = listOf("finissons", "finissent", "finis"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Verbe finir (2ème groupe) avec ils ➔ ils finissent.",
            arabicTranslation = "كل يوم، هم ينهون واجباتهم (ils + finir ➔ finissent) ➔ ils finissent."
        ),
        QuizQuestion(
            id = "rev_ex2_4",
            question = "4. Chaque matin, je ______ prendre le petit déjeuner tôt.",
            options = listOf("préfère", "préférez", "préfères"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Verbe préférer avec je ➔ je préfère.",
            arabicTranslation = "كل صباح، أنا أفضل تناول الإفطار مبكراً (je + préférer ➔ préfère) ➔ je préfère."
        ),
        QuizQuestion(
            id = "rev_ex2_5",
            question = "5. Nous ______ les fruits.",
            options = listOf("aimes", "aimons", "aimez"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Verbe aimer avec nous ➔ nous aimons.",
            arabicTranslation = "نحن نحب الفواكه (nous + aimer ➔ aimons) ➔ nous aimons."
        )
    )

    val revisionEx3Interrogatifs = listOf(
        QuizQuestion(
            id = "rev_ex3_1",
            question = "1) ______ allez-vous ?\n— ça va bien merci.",
            options = listOf("Que", "Qui", "Où", "Comment", "Qu'est ce que"),
            correctIndex = 3,
            pageReference = "",
            explanation = "On demande l'état de santé par 'Comment' ➔ Comment allez-vous ?",
            arabicTranslation = "كيف حالكم؟ السؤال عن الحال والصحة بـ Comment ➔ Comment allez-vous ? (كيف حالكم؟ - بخير شكراً)."
        ),
        QuizQuestion(
            id = "rev_ex3_2",
            question = "2) ______ tu as ?\n— J'ai un examen.",
            options = listOf("Comment", "Que", "Qu'est ce que", "Qui", "Où"),
            correctIndex = 2,
            pageReference = "",
            explanation = "On demande ce qu'on a par 'Qu'est ce que' ➔ Qu'est ce que tu as ?",
            arabicTranslation = "ماذا عندك؟ السؤال عن غير عاقل بـ Qu'est ce que tu as ? (ماذا عندك؟ - عندي امتحان)."
        ),
        QuizQuestion(
            id = "rev_ex3_3",
            question = "3) ______ c'est ?\n— C'est la maitresse de français.",
            options = listOf("Qu'est ce que", "Que", "Comment", "Qui", "Où"),
            correctIndex = 3,
            pageReference = "",
            explanation = "On demande l'identité d'une personne par 'Qui' ➔ Qui c'est ?",
            arabicTranslation = "من هذا؟ السؤال عن شخص عاقل بـ Qui c'est? (من هذا؟ - إنها معلمة اللغة الفرنسية)."
        ),
        QuizQuestion(
            id = "rev_ex3_4",
            question = "4) ______ sont les élèves ?\n— Les élèves sont dans la cour.",
            options = listOf("Où", "Comment", "Qui", "Qu'est ce que", "Que"),
            correctIndex = 0,
            pageReference = "",
            explanation = "On demande le lieu (dans la cour) par 'Où' ➔ Où sont les élèves ?",
            arabicTranslation = "أين التلاميذ؟ السؤال عن المكان (dans la cour في الفناء) بـ Où ➔ Où sont les élèves ?"
        ),
        QuizQuestion(
            id = "rev_ex3_5",
            question = "5) ______ fais-tu ?\n— J'écoute de la musique.",
            options = listOf("Qu'est ce que", "Qui", "Où", "Que", "Comment"),
            correctIndex = 3,
            pageReference = "",
            explanation = "On demande l'action par 'Que' avec inversion ➔ Que fais-tu ?",
            arabicTranslation = "ماذا تفعل؟ السؤال عن الفعل مع تقديم الفعل على الفاعل بـ Que ➔ Que fais-tu ? (ماذا تفعل؟ - أستمع للموسيقى)."
        )
    )

    val revisionEx4ArticlesLieux = listOf(
        QuizQuestion(
            id = "rev_ex4_1",
            question = "1) ______ pharmacie",
            options = listOf("aux", "à l'", "au", "à", "à la"),
            correctIndex = 4,
            pageReference = "",
            explanation = "Pharmacie est féminin singulier ➔ à la pharmacie.",
            arabicTranslation = "الصيدلية مكان مؤنث مفرد ➔ à la pharmacie."
        ),
        QuizQuestion(
            id = "rev_ex4_2",
            question = "2) ______ école",
            options = listOf("aux", "au", "à la", "à l'", "à"),
            correctIndex = 3,
            pageReference = "",
            explanation = "École commence par une voyelle ➔ à l'école.",
            arabicTranslation = "المدرسة تبدأ بحرف متحرك é ➔ à l'école."
        ),
        QuizQuestion(
            id = "rev_ex4_3",
            question = "3) ______ restaurant",
            options = listOf("aux", "à la", "à l'", "à", "au"),
            correctIndex = 4,
            pageReference = "",
            explanation = "Restaurant est masculin singulier ➔ au restaurant.",
            arabicTranslation = "المطعم مكان مذكر مفرد ➔ au restaurant."
        ),
        QuizQuestion(
            id = "rev_ex4_4",
            question = "4) ______ zoo",
            options = listOf("aux", "à", "au", "à la", "à l'"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Zoo est masculin singulier ➔ au zoo.",
            arabicTranslation = "حديقة الحيوان مكان مذكر مفرد ➔ au zoo."
        ),
        QuizQuestion(
            id = "rev_ex4_5",
            question = "5) ______ tour",
            options = listOf("à", "à la", "aux", "à l'", "au"),
            correctIndex = 1,
            pageReference = "",
            explanation = "Tour (البرج) est féminin singulier ➔ à la tour.",
            arabicTranslation = "البرج مكان مؤنث مفرد ➔ à la tour."
        ),
        QuizQuestion(
            id = "rev_ex4_6",
            question = "6) ______ poste",
            options = listOf("au", "aux", "à la", "à", "à l'"),
            correctIndex = 2,
            pageReference = "",
            explanation = "Poste (مكتب البريد) est féminin singulier ➔ à la poste.",
            arabicTranslation = "مكتب البريد مكان مؤنث مفرد ➔ à la poste."
        ),
        QuizQuestion(
            id = "rev_ex4_7",
            question = "7) ______ cinéma",
            options = listOf("au", "à", "à la", "aux", "à l'"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Cinéma est masculin singulier ➔ au cinéma.",
            arabicTranslation = "السينما مكان مذكر مفرد ➔ au cinéma."
        ),
        QuizQuestion(
            id = "rev_ex4_8",
            question = "8) ______ hôtel",
            options = listOf("à", "à la", "au", "à l'", "aux"),
            correctIndex = 3,
            pageReference = "",
            explanation = "Hôtel commence par 'h' muet (voyelle) ➔ à l'hôtel.",
            arabicTranslation = "الفندق يبدأ بحرف h صامت ➔ à l'hôtel."
        ),
        QuizQuestion(
            id = "rev_ex4_9",
            question = "9) ______ club",
            options = listOf("à", "à l'", "à la", "aux", "au"),
            correctIndex = 4,
            pageReference = "",
            explanation = "Club est masculin singulier ➔ au club.",
            arabicTranslation = "النادي مكان مذكر مفرد ➔ au club."
        ),
        QuizQuestion(
            id = "rev_ex4_10",
            question = "10) ______ pyramides",
            options = listOf("aux", "à", "à la", "à l'", "au"),
            correctIndex = 0,
            pageReference = "",
            explanation = "Pyramides est au pluriel (-s) ➔ aux pyramides.",
            arabicTranslation = "الأهرامات جمع ينتهي بـ s ➔ aux pyramides."
        ),
        QuizQuestion(
            id = "rev_ex4_11",
            question = "11) ______ jardin",
            options = listOf("aux", "à", "à la", "au", "à l'"),
            correctIndex = 3,
            pageReference = "",
            explanation = "Jardin est masculin singulier ➔ au jardin.",
            arabicTranslation = "الحديقة مكان مذكر مفرد ➔ au jardin."
        ),
        QuizQuestion(
            id = "rev_ex4_12",
            question = "12) ______ magasin",
            options = listOf("à l'", "aux", "à", "à la", "au"),
            correctIndex = 4,
            pageReference = "",
            explanation = "Magasin est masculin singulier ➔ au magasin.",
            arabicTranslation = "المتجر مكان مذكر مفرد ➔ au magasin."
        )
    )

    val revisionEx5Negation = listOf(
        QuizQuestion(
            id = "rev_ex5_1",
            question = "1. « Nous avons des amis. »\n➔ À la forme négative :",
            options = listOf("Nous n'avons pas des amis.", "Nous ne avons pas d'amis.", "Nous n'avons pas d'amis."),
            correctIndex = 2,
            pageReference = "",
            explanation = "L'article 'des' se transforme en 'd'' devant voyelle ➔ d'amis.",
            arabicTranslation = "أداة الجمع des تتحول في النفي إلى d' أمام حرف متحرك a ➔ Nous n'avons pas d'amis."
        ),
        QuizQuestion(
            id = "rev_ex5_2",
            question = "2. « Je vais au stade. »\n➔ À la forme négative :",
            options = listOf("Je ne vais pas au stade.", "Je n'ai pas au stade.", "Je ne va pas au stade."),
            correctIndex = 0,
            pageReference = "",
            explanation = "Négation simple : ne + verbe + pas ➔ Je ne vais pas.",
            arabicTranslation = "نفي بسيط بحصر الفعل vais بين ne و pas ➔ Je ne vais pas au stade."
        ),
        QuizQuestion(
            id = "rev_ex5_3",
            question = "3. « Nous sommes des amis. »\n➔ À la forme négative :",
            options = listOf("Nous n'avons pas des amis.", "Nous ne sommes pas d'amis.", "Nous ne sommes pas des amis."),
            correctIndex = 2,
            pageReference = "",
            explanation = "Attention : Avec le verbe ÊTRE, l'article 'des' NE change PAS !",
            arabicTranslation = "⚠️ انتبه: مع فعل الكينونة être لا تتغير des بل تظل كما هي ➔ Nous ne sommes pas des amis."
        ),
        QuizQuestion(
            id = "rev_ex5_4",
            question = "4. « Il a une nouvelle voiture. »\n➔ À la forme négative :",
            options = listOf("Il n'a pas de nouvelle voiture.", "Il n'a pas une nouvelle voiture.", "Il ne a pas de nouvelle voiture."),
            correctIndex = 0,
            pageReference = "",
            explanation = "L'article 'une' se transforme en 'de' à la négation.",
            arabicTranslation = "تتحول أداة النكرة une في النفي إلى de ➔ Il n'a pas de nouvelle voiture."
        ),
        QuizQuestion(
            id = "rev_ex5_5",
            question = "5. « Elle est une élève. »\n➔ À la forme négative :",
            options = listOf("Elle n'est pas de élève.", "Elle n'est pas d'élève.", "Elle n'est pas une élève."),
            correctIndex = 2,
            pageReference = "",
            explanation = "Attention : Avec le verbe ÊTRE, on conserve 'une' !",
            arabicTranslation = "⚠️ انتبه: مع فعل الكينونة être تبقى أداة النكرة une كما هي بدون تغيير ➔ Elle n'est pas une élève."
        )
    )

    val grammarQuizQuestions = revisionEx1MetsAuPresent + revisionEx2ChoisisBonneReponse + revisionEx3Interrogatifs + revisionEx4ArticlesLieux + revisionEx5Negation

    val examText = "Le garçon : Bonjour monsieur, vous voulez prendre le déjeuner au menu ou à la carte ? Ahmed : À la carte. Le garçon : Que voulez-vous comme entrée ? Ahmed : Des tomates et des concombres en salade. Le garçon : et le plat principal ? Ahmed : De la viande, du riz et des frites. Le garçon : Voulez-vous du dessert ? Ahmed : Oui, j'aime le gâteau."
    val examTextArabic = "النادل: مرحباً سيدي، هل تريد تناول الغداء ضمن قائمة اليوم أم حسب الطلب؟ أحمد: حسب الطلب. النادل: ماذا تريد كطبق مقبلات؟ أحمد: طماطم وخيار وسلطة. النادل: والطبق الرئيسي؟ أحمد: لحم وأرز وبطاطس مقلية. النادل: هل تريد حلوى؟ أحمد: نعم، أحب الكعك."

    val examQuestions = listOf(
        ExamQuestion(
            section = "I. Compréhension du texte",
            question = "1. Ahmed prend son repas :",
            options = listOf("au menu", "à la carte", "au dîner"),
            correctIndex = 1,
            arabicTranslation = "يتناول أحمد وجبته : (حسب الطلب à la carte / قائمة اليوم au menu / في العشاء au dîner)"
        ),
        ExamQuestion(
            section = "I. Compréhension du texte",
            question = "2. Ahmed demande comme plat principal :",
            options = listOf("de la glace", "du poisson", "de la viande et du riz"),
            correctIndex = 2,
            arabicTranslation = "طلب أحمد كطبق رئيسي : (سمك / آيس كريم / لحم وأرز)"
        ),
        ExamQuestion(
            section = "I. Compréhension du texte",
            question = "3. Ahmed va prendre :",
            options = listOf("le déjeuner", "le dîner", "le petit-déjeuner"),
            correctIndex = 0,
            arabicTranslation = "أحمد سيتناول : (الإفطار / الغداء / العشاء)"
        ),
        ExamQuestion(
            section = "II. Grammaire",
            question = "4. Nous ______ aux élèves. (parler)",
            options = listOf("parle", "parlez", "parlons"),
            correctIndex = 2,
            arabicTranslation = "نحن [نتحدث] إلى التلاميذ. تصريف فعل parler مع nous ➔ parlons."
        ),
        ExamQuestion(
            section = "II. Grammaire",
            question = "5. On peut prendre ______ boissons fraîches.",
            options = listOf("des", "du", "de l'"),
            correctIndex = 0,
            arabicTranslation = "يمكننا تناول [مشروبات باردة]. كلمة boissons جمع ➔ نختار des."
        ),
        ExamQuestion(
            section = "II. Adjectifs Possessifs",
            question = "6. Tu aimes ______ parents.",
            options = listOf("ton", "tes", "ta"),
            correctIndex = 1,
            arabicTranslation = "أنت تحب [والديك]. كلمة parents جمع مع الضمير tu ➔ نختار tes."
        ),
        ExamQuestion(
            section = "II. Pronoms Personnels",
            question = "7. « Je parle à mon ami » ➔ Je ______ parle.",
            options = listOf("lui", "le", "leur"),
            correctIndex = 0,
            arabicTranslation = "«أنا أتحدث إلى صديقي» ➔ أنا أتحدث [إليه]. المفعول شخص مفرد مسبوق بـ à ➔ نختار lui."
        ),
        ExamQuestion(
            section = "II. Négation",
            question = "8. Elle mange des légumes. ➔ Elle ne mange pas ______ légumes.",
            options = listOf("des", "les", "de"),
            correctIndex = 2,
            arabicTranslation = "هي تأكل خضروات ➔ هي لا تأكل [خضروات]. أداة التجزئة des تتحول إلى de في النفي."
        ),
        ExamQuestion(
            section = "III. Situations officielles",
            question = "9. Tu demandes au garçon l'addition, tu dis :",
            options = listOf("L'addition, S.V.P !", "Bon appétit !", "Que voulez-vous ?"),
            correctIndex = 0,
            arabicTranslation = "تطلب من النادل الحساب والفاتورة، ماذا تقول؟ (الحساب لو سمحت! / ماذا تريد؟ / بالهناء والشفاء!)"
        ),
        ExamQuestion(
            section = "III. Situations officielles",
            question = "10. Au restaurant, tu demandes le menu au garçon, tu dis :",
            options = listOf("C'est combien ?", "Apportez-moi le menu, S.V.P !", "Je prends de la glace."),
            correctIndex = 1,
            arabicTranslation = "في المطعم، تطلب قائمة الطعام من النادل، ماذا تقول؟ (أنا آخذ آيس كريم / أحضر لي القائمة لو سمحت! / كم الحساب؟)"
        )
    )

    val unit1VocabWords = listOf(
        VocabWord(
            id = "u1_v1",
            french = "Une invitation",
            arabic = "دعوة",
            category = VocabCategory.FEMININE,
            exampleFr = "Gamal a envoyé une invitation à ses amis.",
            exampleAr = "أرسل جمال دعوة إلى أصدقائه.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v2",
            french = "Les vacances",
            arabic = "الإجازة",
            category = VocabCategory.FEMININE,
            exampleFr = "Les vacances sont vite passées.",
            exampleAr = "مرت الإجازة بسرعة.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v3",
            french = "Le congé = le weekend",
            arabic = "يوم العطلة",
            category = VocabCategory.MASCULINE,
            exampleFr = "Vendredi, c'est le congé hebdomadaire.",
            exampleAr = "يوم الجمعة هو يوم العطلة الأسبوعية.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v4",
            french = "Les monuments",
            arabic = "الآثار",
            category = VocabCategory.MASCULINE,
            exampleFr = "Le livre parle des monuments français.",
            exampleAr = "الكتاب يتحدث عن المعالم والآثار الفرنسية.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v5",
            french = "Une boisson fraîche",
            arabic = "مشروب طازج / منعش",
            category = VocabCategory.FEMININE,
            exampleFr = "Sur la table, il y a des boissons fraîches.",
            exampleAr = "على الطاولة، توجد مشروبات باردة ومنعشة.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v6",
            french = "La mer rouge",
            arabic = "البحر الأحمر",
            category = VocabCategory.FEMININE,
            exampleFr = "Gamal a passé ses vacances à la Mer Rouge.",
            exampleAr = "قضى جمال إجازته في البحر الأحمر.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v7",
            french = "Ici ≠ là bas",
            arabic = "هنا ≠ هناك",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Moustafa est ici, Jean est là-bas.",
            exampleAr = "مصطفى هنا، وجان هناك.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v8",
            french = "Un ami",
            arabic = "صديق",
            category = VocabCategory.MASCULINE,
            exampleFr = "Moustafa est l'ami égyptien de Gamal.",
            exampleAr = "مصطفى هو صديق جمال المصري.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v9",
            french = "Un invité",
            arabic = "مدعو / ضيف",
            category = VocabCategory.MASCULINE,
            exampleFr = "Jean Morelle est un invité chez Gamal.",
            exampleAr = "جان موريل ضيف ومدعو عند جمال.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v10",
            french = "Les vacances d'été",
            arabic = "إجازة الصيف",
            category = VocabCategory.FEMININE,
            exampleFr = "En été, nous passons les vacances d'été à Alexandrie.",
            exampleAr = "في الصيف، نقضي إجازة الصيف في الإسكندرية.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v11",
            french = "Le livre d'histoire",
            arabic = "كتاب التاريخ",
            category = VocabCategory.MASCULINE,
            exampleFr = "Jean a offert à Gamal un livre d'histoire.",
            exampleAr = "أهدى جان لجمال كتاب تاريخ.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v12",
            french = "Du poisson",
            arabic = "سمك",
            category = VocabCategory.MASCULINE,
            exampleFr = "À Hurghada, Gamal et ses élèves ont mangé du poisson.",
            exampleAr = "في الغردقة، أكل جمال وتلاميذه سمكاً.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v13",
            french = "Une semaine",
            arabic = "أسبوع",
            category = VocabCategory.FEMININE,
            exampleFr = "Gamal a passé une semaine à la Mer Rouge.",
            exampleAr = "أمضى جمال أسبوعاً في البحر الأحمر.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v14",
            french = "Au bord de",
            arabic = "على شاطئ",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Ils se promènent au bord de la mer.",
            exampleAr = "يتنزهون على شاطئ البحر.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v15",
            french = "Pendant",
            arabic = "أثناء / خلال",
            category = VocabCategory.EXPRESSION,
            exampleFr = "Les amis ont beaucoup parlé pendant la soirée.",
            exampleAr = "تحدث الأصدقاء كثيراً أثناء السهرة.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v16",
            french = "inviter",
            arabic = "يدعو",
            category = VocabCategory.VERB,
            exampleFr = "Gamal invite ses amis chez lui.",
            exampleAr = "يدعو جمال أصدقاءه إلى منزله.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v17",
            french = "passer",
            arabic = "يقضي / يمر",
            category = VocabCategory.VERB,
            exampleFr = "J'ai passé de bonnes vacances.",
            exampleAr = "قضيت إجازة سعيدة.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v18",
            french = "adorer",
            arabic = "يعشق",
            category = VocabCategory.VERB,
            exampleFr = "Moi, j'adore les livres d'histoire !",
            exampleAr = "أنا أعشق كتب التاريخ!",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v19",
            french = "présenter",
            arabic = "يقدّم (شخصاً)",
            category = VocabCategory.VERB,
            exampleFr = "Je vous présente Moustafa.",
            exampleAr = "أقدّم لكم مصطفى.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v20",
            french = "bavarder",
            arabic = "يثرثر / يدردش",
            category = VocabCategory.VERB,
            exampleFr = "Les amis aiment bavarder le soir.",
            exampleAr = "يحب الأصدقاء الدردشة في المساء.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v21",
            french = "visiter",
            arabic = "يزور (مكاناً أو معلماً)",
            category = VocabCategory.VERB,
            exampleFr = "Jean visite les monuments égyptiens.",
            exampleAr = "يزور جان الآثار المصرية.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v22",
            french = "aimer",
            arabic = "يحب",
            category = VocabCategory.VERB,
            exampleFr = "Suzanne aime voyager en Égypte.",
            exampleAr = "تحب سوزان السفر إلى مصر.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v23",
            french = "préparer",
            arabic = "يجهّز / يُعد",
            category = VocabCategory.VERB,
            exampleFr = "Gamal prépare des gâteaux pour les invités.",
            exampleAr = "يجهّز جمال الحلوى للضيوف.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v24",
            french = "offrir",
            arabic = "يُهدي / يقدّم هدية",
            category = VocabCategory.VERB,
            exampleFr = "Jean a offert un cadeau à Gamal.",
            exampleAr = "أهدى جان هدية لجمال.",
            phoneticOrNote = "",
            pageReference = "p. 14"
        ),
        VocabWord(
            id = "u1_v25",
            french = "se présenter",
            arabic = "يقدّم نفسه",
            category = VocabCategory.VERB,
            exampleFr = "Moustafa se présente : « Je suis pilote à EgyptAir ».",
            exampleAr = "مصطفى يقدّم نفسه: «أنا طيار في مصر للطيران».",
            phoneticOrNote = "",
            pageReference = "p. 14"
        )
    )

    val unit1BanqueDesMots = listOf(
        VocabWord(
            id = "u1_bm_m1",
            french = "un bouquet",
            arabic = "باقة (ورد)",
            category = VocabCategory.MASCULINE,
            exampleFr = "J'offre un bouquet de fleurs à Suzanne.",
            exampleAr = "أهدي باقة زهور لسوزان.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m2",
            french = "un cadeau",
            arabic = "هدية",
            category = VocabCategory.MASCULINE,
            exampleFr = "Jean apporte un cadeau pour Gamal.",
            exampleAr = "يحضر جان هدية لجمال.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m3",
            french = "un disque",
            arabic = "أسطوانة",
            category = VocabCategory.MASCULINE,
            exampleFr = "Nous écoutons de la musique sur un disque.",
            exampleAr = "نستمع إلى الموسيقى من أسطوانة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m4",
            french = "un anniversaire",
            arabic = "عيد الميلاد",
            category = VocabCategory.MASCULINE,
            exampleFr = "Bon anniversaire mon ami !",
            exampleAr = "عيد ميلاد سعيد يا صديقي!",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m5",
            french = "un oiseau",
            arabic = "عصفور",
            category = VocabCategory.MASCULINE,
            exampleFr = "L'oiseau chante dans le jardin.",
            exampleAr = "العصفور يغرد في الحديقة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m6",
            french = "un chien",
            arabic = "كلب",
            category = VocabCategory.MASCULINE,
            exampleFr = "Gamal a un petit chien fidèle.",
            exampleAr = "جمال لديه كلب صغير وفيّ.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m7",
            french = "un chat",
            arabic = "قط",
            category = VocabCategory.MASCULINE,
            exampleFr = "Le chat blanc dort sur la chaise.",
            exampleAr = "القط الأبيض ينام على الكرسي.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m8",
            french = "un chapeau",
            arabic = "قبعة",
            category = VocabCategory.MASCULINE,
            exampleFr = "Jean porte un chapeau élégant.",
            exampleAr = "يرتدي جان قبعة أنيقة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m9",
            french = "un enfant",
            arabic = "طفل",
            category = VocabCategory.MASCULINE,
            exampleFr = "L'enfant souffle les bougies.",
            exampleAr = "الطفل يطفئ الشموع.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m10",
            french = "un gâteau",
            arabic = "تورتة / جاتوه",
            category = VocabCategory.MASCULINE,
            exampleFr = "Maman prépare un délicieux gâteau.",
            exampleAr = "تجهز أمي كعكة لذيذة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m11",
            french = "un biscuit",
            arabic = "بسكويت",
            category = VocabCategory.MASCULINE,
            exampleFr = "Les enfants mangent un biscuit sucré.",
            exampleAr = "يأكل الأطفال بسكويتاً حلو المذاق.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m12",
            french = "un poste de radio",
            arabic = "جهاز راديو",
            category = VocabCategory.MASCULINE,
            exampleFr = "Il écoute les informations sur un poste de radio.",
            exampleAr = "يستمع إلى الأخبار على جهاز راديو.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m13",
            french = "un jardin",
            arabic = "حديقة",
            category = VocabCategory.MASCULINE,
            exampleFr = "Nous fêtons l'anniversaire dans le jardin.",
            exampleAr = "نحتفل بعيد الميلاد في الحديقة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m14",
            french = "un chocolat",
            arabic = "شيكولاتة",
            category = VocabCategory.MASCULINE,
            exampleFr = "J'aime manger du chocolat au lait.",
            exampleAr = "أحب أكل الشوكولاتة بالحليب.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m15",
            french = "un parapluie",
            arabic = "شمسية مطر",
            category = VocabCategory.MASCULINE,
            exampleFr = "Il pleut, prends un parapluie !",
            exampleAr = "إنها تمطر، خذ معك شمسية مطر!",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m16",
            french = "un invité",
            arabic = "مدعو / ضيف",
            category = VocabCategory.MASCULINE,
            exampleFr = "Gamal accueille chaque invité avec joie.",
            exampleAr = "يستقبل جمال كل ضيف بفرح.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m17",
            french = "un monument",
            arabic = "أثر تاريخي",
            category = VocabCategory.MASCULINE,
            exampleFr = "Les Pyramides sont un monument célèbre.",
            exampleAr = "الأهرامات هي أثر تاريخي مشهور.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m18",
            french = "un ballon",
            arabic = "بالونة / كرة",
            category = VocabCategory.MASCULINE,
            exampleFr = "Nous gonflons un ballon pour la fête.",
            exampleAr = "ننفخ بالونة من أجل الحفلة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_m19",
            french = "des gens",
            arabic = "ناس / أشخاص",
            category = VocabCategory.MASCULINE,
            exampleFr = "Il y a beaucoup de gens à la soirée.",
            exampleAr = "يوجد الكثير من الناس في السهرة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f1",
            french = "une invitation",
            arabic = "دعوة",
            category = VocabCategory.FEMININE,
            exampleFr = "J'ai accepté ton invitation avec plaisir.",
            exampleAr = "قبلت دعوتك بكل سرور.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f2",
            french = "une fleur",
            arabic = "زهرة",
            category = VocabCategory.FEMININE,
            exampleFr = "Elle met une belle fleur sur la table.",
            exampleAr = "تضع زهرة جميلة على الطاولة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f3",
            french = "une carte",
            arabic = "بطاقة / خريطة",
            category = VocabCategory.FEMININE,
            exampleFr = "Il achète une carte de France.",
            exampleAr = "يشتري خريطة لفرنسا.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f4",
            french = "une photo",
            arabic = "صورة",
            category = VocabCategory.FEMININE,
            exampleFr = "Nous prenons une photo souvenir ensemble.",
            exampleAr = "نلتقط صورة تذكارية معاً.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f5",
            french = "une bougie",
            arabic = "شمعة",
            category = VocabCategory.FEMININE,
            exampleFr = "Sur le gâteau, il y a une bougie colorée.",
            exampleAr = "على التورتة توجد شمعة ملونة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f6",
            french = "une cage",
            arabic = "قفص",
            category = VocabCategory.FEMININE,
            exampleFr = "L'oiseau est dans une cage en fer.",
            exampleAr = "العصفور في قفص حديدي.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f7",
            french = "une bicyclette",
            arabic = "دراجة",
            category = VocabCategory.FEMININE,
            exampleFr = "L'élève va à l'école sur une bicyclette.",
            exampleAr = "يذهب الطالب إلى المدرسة بالدراجة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f8",
            french = "une femme",
            arabic = "امرأة / زوجة",
            category = VocabCategory.FEMININE,
            exampleFr = "Suzanne est la femme de Jean.",
            exampleAr = "سوزان هي زوجة جان.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f9",
            french = "une radiocassette",
            arabic = "راديو كاسيت",
            category = VocabCategory.FEMININE,
            exampleFr = "Nous écoutons une cassette de chansons.",
            exampleAr = "نستمع إلى شريط كاسيت أغانٍ.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f10",
            french = "une cigarette",
            arabic = "سيجارة",
            category = VocabCategory.FEMININE,
            exampleFr = "Fumer une cigarette est interdit ici.",
            exampleAr = "تدخين سيجارة ممنوع هنا.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f11",
            french = "une occasion",
            arabic = "مناسبة",
            category = VocabCategory.FEMININE,
            exampleFr = "C'est une belle occasion pour se réunir.",
            exampleAr = "إنها مناسبة جميلة للاجتماع معاً.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f12",
            french = "une pipe",
            arabic = "بايب للتدخين / غليون",
            category = VocabCategory.FEMININE,
            exampleFr = "Le grand-père fume une vieille pipe.",
            exampleAr = "الجد يدخن غليوناً قديماً.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f13",
            french = "une fête",
            arabic = "حفلة",
            category = VocabCategory.FEMININE,
            exampleFr = "Nous organisons une grande fête chez Gamal.",
            exampleAr = "ننظم حفلة كبيرة في منزل جمال.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f14",
            french = "une fois",
            arabic = "مرة",
            category = VocabCategory.FEMININE,
            exampleFr = "Je vais au cinéma une fois par semaine.",
            exampleAr = "أذهب إلى السينما مرة في الأسبوع.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f15",
            french = "une carte postale",
            arabic = "بطاقة بريدية",
            category = VocabCategory.FEMININE,
            exampleFr = "J'envoie une carte postale à mes cousins.",
            exampleAr = "أرسل بطاقة بريدية لأبناء عمي.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f16",
            french = "les vacances",
            arabic = "الإجازة",
            category = VocabCategory.FEMININE,
            exampleFr = "Les vacances sont vite passées.",
            exampleAr = "مرت الإجازة بسرعة.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f17",
            french = "des lunettes de soleil",
            arabic = "نظارة شمسية",
            category = VocabCategory.FEMININE,
            exampleFr = "À la plage, il met des lunettes de soleil.",
            exampleAr = "على الشاطئ، يضع نظارة شمسية.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_f18",
            french = "une carte d'invitation",
            arabic = "بطاقة دعوة",
            category = VocabCategory.FEMININE,
            exampleFr = "Gamal envoie une carte d'invitation à ses amis.",
            exampleAr = "يرسل جمال بطاقة دعوة لأصدقائه.",
            phoneticOrNote = "",
            pageReference = "p. 27"
        ),
        VocabWord(
            id = "u1_bm_v1",
            french = "organiser",
            arabic = "ينظم",
            category = VocabCategory.VERB,
            exampleFr = "Nous organisons une fête d'anniversaire.",
            exampleAr = "نحن ننظم حفلة عيد ميلاد.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v2",
            french = "inviter",
            arabic = "يدعو",
            category = VocabCategory.VERB,
            exampleFr = "Je t'invite chez moi ce soir.",
            exampleAr = "أدعوك إلى منزلي هذا المساء.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v3",
            french = "commencer",
            arabic = "يبدأ",
            category = VocabCategory.VERB,
            exampleFr = "La fête commence à sept heures.",
            exampleAr = "تبدأ الحفلة في الساعة السابعة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v4",
            french = "danser",
            arabic = "يرقص",
            category = VocabCategory.VERB,
            exampleFr = "Les invités dansent avec joie.",
            exampleAr = "يرقص المدعوون بفرح.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v5",
            french = "bavarder",
            arabic = "يثرثر / يتحدث كثيراً",
            category = VocabCategory.VERB,
            exampleFr = "Les amis bavardent pendant la soirée.",
            exampleAr = "يتحدث الأصدقاء أثناء السهرة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v6",
            french = "manger",
            arabic = "يأكل",
            category = VocabCategory.VERB,
            exampleFr = "Nous mangeons des gâteaux délicieux.",
            exampleAr = "نأكل حلويات لذيذة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v7",
            french = "écouter",
            arabic = "يسمع / يستمع إلى",
            category = VocabCategory.VERB,
            exampleFr = "Les élèves écoutent la musique.",
            exampleAr = "يستمع التلاميذ إلى الموسيقى.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v8",
            french = "oublier",
            arabic = "ينسى",
            category = VocabCategory.VERB,
            exampleFr = "N'oublie pas ton devoir !",
            exampleAr = "لا تنسَ واجبك!",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v9",
            french = "raconter",
            arabic = "يحكي",
            category = VocabCategory.VERB,
            exampleFr = "Gamal raconte ses vacances à la Mer Rouge.",
            exampleAr = "يحكي جمال عن إجازته في البحر الأحمر.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v10",
            french = "apporter",
            arabic = "يحضر / يجلب",
            category = VocabCategory.VERB,
            exampleFr = "Jean apporte un livre à Gamal.",
            exampleAr = "يحضر جان كتاباً لجمال.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v11",
            french = "chanter",
            arabic = "يغني",
            category = VocabCategory.VERB,
            exampleFr = "Tout le monde chante une belle chanson.",
            exampleAr = "الجميع يغني أغنية جميلة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v12",
            french = "visiter",
            arabic = "يزور",
            category = VocabCategory.VERB,
            exampleFr = "Suzanne visite le musée égyptien.",
            exampleAr = "تزور سوزان المتحف المصري.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v13",
            french = "chercher",
            arabic = "يبحث عن",
            category = VocabCategory.VERB,
            exampleFr = "Je cherche mes clés partout.",
            exampleAr = "أبحث عن مفاتيحي في كل مكان.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v14",
            french = "accepter",
            arabic = "يقبل / يوافق",
            category = VocabCategory.VERB,
            exampleFr = "J'accepte l'invitation avec plaisir.",
            exampleAr = "أقبل الدعوة بكل سرور.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v15",
            french = "refuser",
            arabic = "يرفض",
            category = VocabCategory.VERB,
            exampleFr = "Il refuse l'invitation car il est malade.",
            exampleAr = "يرفض الدعوة لأنه مريض.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v16",
            french = "déjeuner",
            arabic = "يتغذى / يتناول الغداء",
            category = VocabCategory.VERB,
            exampleFr = "Nous déjeunons au restaurant à midi.",
            exampleAr = "نتناول الغداء في المطعم عند الظهر.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v17",
            french = "rentrer",
            arabic = "يعود / يرجع",
            category = VocabCategory.VERB,
            exampleFr = "Jean et sa femme sont rentrés au Caire.",
            exampleAr = "عاد جان وزوجته إلى القاهرة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v18",
            french = "adorer",
            arabic = "يعشق / يحب بشدة",
            category = VocabCategory.VERB,
            exampleFr = "Gamal adore les livres d'histoire.",
            exampleAr = "يعشق جمال كتب التاريخ.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v19",
            french = "préparer",
            arabic = "يجهز / يعد",
            category = VocabCategory.VERB,
            exampleFr = "La mère prépare de bons plats pour la fête.",
            exampleAr = "تجهز الأم أطباقاً طيبة للحفلة.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v20",
            french = "offrir",
            arabic = "يقدم / يهدي",
            category = VocabCategory.VERB,
            exampleFr = "Jean offre un beau cadeau à son ami.",
            exampleAr = "يقدم جان هدية جميلة لصديقه.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v21",
            french = "venir",
            arabic = "يأتي",
            category = VocabCategory.VERB,
            exampleFr = "Moustafa vient à la soirée chez Gamal.",
            exampleAr = "يأتي مصطفى إلى السهرة في منزل جمال.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v22",
            french = "faire",
            arabic = "يعمل / يفعل",
            category = VocabCategory.VERB,
            exampleFr = "Qu'est-ce qu'on fait à la fête ?",
            exampleAr = "ماذا نفعل في الحفلة؟",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v23",
            french = "écrire",
            arabic = "يكتب",
            category = VocabCategory.VERB,
            exampleFr = "Suzanne écrit une lettre à sa famille.",
            exampleAr = "تكتب سوزان رسالة لعائلتها.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        ),
        VocabWord(
            id = "u1_bm_v24",
            french = "dormir",
            arabic = "ينام",
            category = VocabCategory.VERB,
            exampleFr = "Après la fête, les enfants vont dormir.",
            exampleAr = "بعد الحفلة، يذهب الأطفال للنوم.",
            phoneticOrNote = "",
            pageReference = "p. 28"
        )
    )

    val unit1Situations = listOf(
        SituationItem(
            id = "u1_sit_1",
            number = 1,
            promptFr = "Ton ami t'invite à une fête, tu acceptes l'invitation; tu dis:",
            promptAr = "صديقك يدعوك إلى حفلة، وأنت تقبل الدعوة؛ ماذا تقول؟",
            categoryAr = "قبول الدعوة ✅",
            categoryFr = "Accepter une invitation",
            options = listOf(
                SituationOption(
                    textFr = "Pardon, j'ai un examen",
                    textAr = "عذراً، لدي امتحان (اعتذار)",
                    isCorrect = false,
                    explanation = "خطأ! هذا اعتذار وتبرير لعدم الحضور."
                ),
                SituationOption(
                    textFr = "avec plaisir !",
                    textAr = "بكل سرور ! (قبول الدعوة)",
                    isCorrect = true,
                    explanation = "صحيح! (Avec plaisir) هو التعبير الصريح والمباشر لقبول أي دعوة."
                ),
                SituationOption(
                    textFr = "Désolé, je ne peux pas venir",
                    textAr = "آسف، لا أستطيع المجيء (رفض)",
                    isCorrect = false,
                    explanation = "خطأ! هذا تعبير لرفض الدعوة والاعتذار وليس لقبولها."
                )
            ),
            goldenRule = "عند قبول الدعوة (Tu acceptes l'invitation) نستخدم تعبيرات القبول مثل: Avec plaisir ! (بكل سرور) أو D'accord ! أو Pourquoi pas !",
            rolePlaySpeaker = "Ton ami Gamal",
            rolePlayMessage = "« Je t'invite à ma fête ce soir, tu peux venir ? »",
            pageReference = "p. 15"
        ),
        SituationItem(
            id = "u1_sit_2",
            number = 2,
            promptFr = "Tu invites ton ami à la fête de ton anniversaire; tu dis:",
            promptAr = "أنت تدعو صديقك إلى حفلة عيد ميلادك؛ ماذا تقول له؟",
            categoryAr = "توجيه الدعوة ✉️",
            categoryFr = "Inviter un ami",
            options = listOf(
                SituationOption(
                    textFr = "Je ne peux pas venir.",
                    textAr = "أنا لا أستطيع المجيء.",
                    isCorrect = false,
                    explanation = "خطأ! أنت هنا صاحب الدعوة ولست المدعو المعتذر."
                ),
                SituationOption(
                    textFr = "Désolé, j'ai un rendez-vous.",
                    textAr = "آسف، لدي موعد.",
                    isCorrect = false,
                    explanation = "خطأ! هذا اعتذار عند تلقي دعوة، بينما المطلوب أن توجه أنت الدعوة."
                ),
                SituationOption(
                    textFr = "je t'invite à la fête de mon anniversaire.",
                    textAr = "أدعوك إلى حفلة عيد ميلادي.",
                    isCorrect = true,
                    explanation = "صحيح! تبدأ بـ (Je t'invite à) لأنك أنت صاحب الدعوة وأنت من يتكلم."
                )
            ),
            goldenRule = "عند توجيه الدعوة (Tu invites... tu dis) تبدأ بجملة دعوة مثل: « Je t'invite à... » (أدعوك إلى...) أو صيغة اقتراح.",
            rolePlaySpeaker = "Toi (أنت)",
            rolePlayMessage = "ماذا ستقول لصديقك لتدعوه لحفلة عيد ميلادك؟",
            pageReference = "p. 15"
        ),
        SituationItem(
            id = "u1_sit_3",
            number = 3,
            promptFr = "Ton ami te demande ce qu'on fait à la fête; tu dis:",
            promptAr = "صديقك يسألك عما نفعله في الحفلة؛ ماذا تقول له؟",
            categoryAr = "أنشطة الحفلة 🎂",
            categoryFr = "Activités de la fête",
            options = listOf(
                SituationOption(
                    textFr = "On part à Alex.",
                    textAr = "نسافر إلى الإسكندرية.",
                    isCorrect = false,
                    explanation = "خطأ! هذا نشاط سفر وإجازة وليس داخل الحفلة."
                ),
                SituationOption(
                    textFr = "On va manger des gâteaux.",
                    textAr = "سوف نأكل الجاتوه والحلويات.",
                    isCorrect = true,
                    explanation = "ممتاز! أكل الجاتوه والحلوى وسماع الموسيقى هي الأنشطة الأساسية في الحفلات."
                ),
                SituationOption(
                    textFr = "On écrit des lettres.",
                    textAr = "نكتب خطابات.",
                    isCorrect = false,
                    explanation = "خطأ! في الحفلات لا نكتب خطابات بريدية."
                )
            ),
            goldenRule = "عند السؤال عن أنشطة الحفلة (ce qu'on fait à la fête) نذكر أنشطة الاحتفال مثل: أكل الجاتوه (manger des gâteaux)، وسماع الموسيقى والرقص.",
            rolePlaySpeaker = "Ton ami Jean",
            rolePlayMessage = "« Qu'est-ce qu'on fait pendant la fête d'anniversaire ? »",
            pageReference = "p. 15"
        ),
        SituationItem(
            id = "u1_sit_4",
            number = 4,
            promptFr = "Tu félicites ton ami pour son anniversaire; tu dis:",
            promptAr = "أنت تهنئ صديقك بمناسبة عيد ميلاده؛ ماذا تقول؟",
            categoryAr = "التهنئة بعيد الميلاد 🎉",
            categoryFr = "Féliciter pour l'anniversaire",
            options = listOf(
                SituationOption(
                    textFr = "Je t'invite chez moi.",
                    textAr = "أدعوك إلى منزلي.",
                    isCorrect = false,
                    explanation = "خطأ! هذه دعوة وليست تهنئة بعيد ميلاده."
                ),
                SituationOption(
                    textFr = "Pardon, je suis fatigué.",
                    textAr = "عذراً، أنا مرهق.",
                    isCorrect = false,
                    explanation = "خطأ! هذا اعتذار وشعور بالتعب، ولا يعبر عن تهنئة."
                ),
                SituationOption(
                    textFr = "Bon anniversaire !",
                    textAr = "عيد ميلاد سعيد !",
                    isCorrect = true,
                    explanation = "أحسنت! (Bon anniversaire !) هي عبارة التهنئة المباشرة بعيد الميلاد."
                )
            ),
            goldenRule = "عند التهنئة بعيد الميلاد (Tu félicites... pour son anniversaire) نستخدم العبارة الرسمية الشهيرة: Bon anniversaire ! أو Joyeux anniversaire !",
            rolePlaySpeaker = "Ton ami Moustafa",
            rolePlayMessage = "« Aujourd'hui c'est le 14 mai, c'est mon anniversaire ! »",
            pageReference = "p. 15"
        ),
        SituationItem(
            id = "u1_sit_5",
            number = 5,
            promptFr = "Tu invites ton ami à déjeuner, mais il n'accepte pas; il dit:",
            promptAr = "تدعو صديقك لتناول الغداء، لكنه لا يقبل (يرفض)؛ ماذا يقول هو؟",
            categoryAr = "رفض الدعوة والاعتذار ❌",
            categoryFr = "Refuser une invitation",
            options = listOf(
                SituationOption(
                    textFr = "Désolé, je vais sortir avec mes parents.",
                    textAr = "آسف، سأخرج مع والديّ. (رفض بأدب واعتذار)",
                    isCorrect = true,
                    explanation = "صحيح جداً! بدأ بـ (Désolé) وقدم سبباً مقنعاً لعدم القدوم."
                ),
                SituationOption(
                    textFr = "Avec plaisir.",
                    textAr = "بكل سرور. (قبول)",
                    isCorrect = false,
                    explanation = "خطأ! هذا قبول، بينما الموقف يحدد أنه لا يقبل (il n'accepte pas)."
                ),
                SituationOption(
                    textFr = "Pourquoi pas.",
                    textAr = "لِمَ لا. (قبول)",
                    isCorrect = false,
                    explanation = "خطأ! (Pourquoi pas) تعني الموافقة والقبول."
                )
            ),
            goldenRule = "لاحظ الفاعل في نهاية الموقف: « il dit » (هو يقول) + « il n'accepte pas » (هو لا يقبل) ➔ المطلوب جملة اعتذار ورفض بأدب: Désolé / Pardon...",
            rolePlaySpeaker = "Ton ami (qui refuse)",
            rolePlayMessage = "تدعوه: « Viens déjeuner avec moi ! » وهو يعتذر، فماذا يقول؟",
            pageReference = "p. 15"
        ),
        SituationItem(
            id = "u1_sit_6",
            number = 6,
            promptFr = "Ton ami te demande ce que tu achètes pour ta mère à l'occasion de son anniversaire: tu dis:",
            promptAr = "يسألك صديقك عما تشتريه لوالدتك بمناسبة عيد ميلادها؛ ماذا تقول؟",
            categoryAr = "هدية عيد الأم / الميلاد 💐",
            categoryFr = "Cadeau d'anniversaire",
            options = listOf(
                SituationOption(
                    textFr = "J'ai un examen.",
                    textAr = "لدي امتحان.",
                    isCorrect = false,
                    explanation = "خطأ! هذا اعتذار لا علاقة له بالسؤال عن الهدية."
                ),
                SituationOption(
                    textFr = "Bon anniversaire.",
                    textAr = "عيد ميلاد سعيد.",
                    isCorrect = false,
                    explanation = "خطأ! هذه جملة تهنئة تقال للأم، وليست الشيء الذي تشتريه كهدية."
                ),
                SituationOption(
                    textFr = "Un bouquet de fleurs.",
                    textAr = "باقة من الزهور الجميلة. (هدية)",
                    isCorrect = true,
                    explanation = "رائع! باقة الزهور (Un bouquet de fleurs) هي الهدية النموذجية والمثالية."
                )
            ),
            goldenRule = "صديقك يسألك ماذا تشتري (ce que tu achètes) وأنت تجيب (tu dis) ➔ نذكر شيئاً مادياً مناسباً يُهدى للأم، مثل: Un bouquet de fleurs (باقة ورد) أو Un cadeau.",
            rolePlaySpeaker = "Ton ami Jean",
            rolePlayMessage = "« Qu'est-ce que tu achètes comme cadeau pour ta mère ? »",
            pageReference = "p. 15"
        )
    )

    val unit1CompositionTopic = CompositionTopic(
        id = "u1_comp_famille",
        titleFr = "Présente ta famille",
        titleAr = "قدّم عائلتك / تحدث عن أسرتك",
        instructionFr = "Présente ta famille (Nom, âge, membres, professions).",
        instructionAr = "تحدث عن عائلتك (اسمك وسنك، عدد أفراد الأسرة، أسماء ومهن الوالدين، إخوتك).",
        pageReference = "p. 16",
        requiredElements = listOf(
            Pair("الاسم والسن (Nom & Âge)", "Je m'appelle Rami, j'ai 14 ans."),
            Pair("عدد أفراد الأسرة (Membres)", "Ma famille se compose de cinq personnes."),
            Pair("مهنة الأب (Père)", "Mon père est médecin / professeur / ingénieur."),
            Pair("مهنة الأم (Mère)", "Ma mère est pharmacienne / maîtresse de maison."),
            Pair("الإخوة والأخوات (Frères & Sœurs)", "J'ai un frère et une sœur."),
            Pair("خاتمة إيجابية (Sentiment)", "J'aime beaucoup ma famille.")
        ),
        sentences = listOf(
            CompositionSentence(
                order = 1,
                french = "Je m'appelle Rami et j'ai 14 ans.",
                arabic = "اسمي رامي وعمري 14 عاماً.",
                hint = "مقدمة تعريفية بنفسك وسنك."
            ),
            CompositionSentence(
                order = 2,
                french = "Ma famille se compose de cinq personnes.",
                arabic = "عائلتي تتكون من خمسة أفراد.",
                hint = "تحديد عدد أفراد العائلة (تعبير se compose de)."
            ),
            CompositionSentence(
                order = 3,
                french = "Mon père s'appelle Ahmed, il est médecin à l'hôpital.",
                arabic = "والدي اسمه أحمد، وهو طبيب في المستشفى.",
                hint = "اسم الأب مع مهنته ومكان عمله."
            ),
            CompositionSentence(
                order = 4,
                french = "Ma mère s'appelle Mona, elle est professeure de français.",
                arabic = "والدتي اسمها منى، وهي معلمة لغة فرنسية.",
                hint = "اسم الأم ومهنتها (تأنيث المهنة)."
            ),
            CompositionSentence(
                order = 5,
                french = "J'ai un frère et une sœur.",
                arabic = "لدي أخ وأخت.",
                hint = "ذكر الإخوة باستخدام أدوات النكرة un / une."
            ),
            CompositionSentence(
                order = 6,
                french = "Nous habitons au Caire dans un bel appartement.",
                arabic = "نحن نسكن في القاهرة في شقة جميلة.",
                hint = "مكان الإقامة والمدينة (حرف الجر au / à)."
            ),
            CompositionSentence(
                order = 7,
                french = "J'aime beaucoup ma famille.",
                arabic = "أنا أحب عائلتي كثيراً.",
                hint = "خاتمة نموذجية ممتازة لإنهاء موضوع التعبير."
            )
        ),
        scrambleWords = listOf("famille", "médecin", "professeure", "personnes", "frère", "sœur", "appartement", "aime"),
        fillBlankText = "Je m'appelle Ali. Ma famille se compose de cinq {1}. Mon père est {2} et ma mère est {3}. J'ai un {4} et une {5}. J'aime beaucoup ma {6}.",
        fillBlankSolutions = listOf("personnes", "médecin", "professeure", "frère", "sœur", "famille")
    )

    val unit1PossessiveTable = listOf(
        PossessiveTableRow(
            subject = "Je",
            mascSingular = "Mon",
            femSingular = "Ma",
            plural = "Mes",
            arabicSubject = "أنا",
            exampleFr = "Je prends mon stylo, ma règle et mes livres.",
            exampleAr = ""
        ),
        PossessiveTableRow(
            subject = "Tu",
            mascSingular = "Ton",
            femSingular = "Ta",
            plural = "Tes",
            arabicSubject = "أنت",
            exampleFr = "Tu ranges ton cahier, ta trousse et tes stylos.",
            exampleAr = ""
        ),
        PossessiveTableRow(
            subject = "Il / Elle",
            mascSingular = "Son",
            femSingular = "Sa",
            plural = "Ses",
            arabicSubject = "هو / هي",
            exampleFr = "Il fait son devoir, écoute sa mère et aide ses amis.",
            exampleAr = ""
        ),
        PossessiveTableRow(
            subject = "Nous",
            mascSingular = "Notre",
            femSingular = "Notre",
            plural = "Nos",
            arabicSubject = "نحن",
            exampleFr = "Nous aimons notre école, notre professeur et nos camarades.",
            exampleAr = ""
        ),
        PossessiveTableRow(
            subject = "Vous",
            mascSingular = "Votre",
            femSingular = "Votre",
            plural = "Vos",
            arabicSubject = "أنتم",
            exampleFr = "Vous prenez votre sac, votre voiture et vos clés.",
            exampleAr = ""
        ),
        PossessiveTableRow(
            subject = "Ils / Elles",
            mascSingular = "Leur",
            femSingular = "Leur",
            plural = "Leurs",
            arabicSubject = "هم / هن",
            exampleFr = "Ils invitent leur ami, leur cousine et leurs professeurs.",
            exampleAr = ""
        )
    )

    val unit1VowelRuleExamples = listOf(
        Triple("amie", "ton amie", "صديقتك (مؤنث يبدأ بحرف a ➔ ton بدلاً من ta)"),
        Triple("adresse", "son adresse", "عنوانه/عنوانها (مؤنث يبدأ بحرف a ➔ son بدلاً من sa)"),
        Triple("école", "mon école", "مدرستي (مؤنث يبدأ بحرف é ➔ mon بدلاً من ma)")
    )

    val unit1PossessiveExercises = listOf(
        PossessiveExerciseItem(
            id = "u1_poss_1",
            number = 1,
            sentenceParts = listOf("Il fait ", " devoir et ", " examens."),
            targetBlanks = listOf("son", "ses"),
            optionsPerBlank = listOf(listOf("sa", "son", "ses"), listOf("son", "ses", "sa")),
            subject = "Il",
            complements = listOf("devoir (مفرد مذكر)", "examens (جمع)"),
            fullSentenceFr = "Il fait son devoir et ses examens.",
            translationAr = "هو يؤدي واجبه وامتحاناته.",
            explanationAr = "الفاعل هو Il فيأخذ (son, sa, ses). الكلمة الأولى devoir مفرد مذكر نختار لها son، والكلمة الثانية examens جمع ينتهي بـ s فنختار لها ses.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_2",
            number = 2,
            sentenceParts = listOf("Nous prenons ", " voiture."),
            targetBlanks = listOf("notre"),
            optionsPerBlank = listOf(listOf("nos", "notre", "votre")),
            subject = "Nous",
            complements = listOf("voiture (مفرد مؤنث)"),
            fullSentenceFr = "Nous prenons notre voiture.",
            translationAr = "نحن نأخذ سيارتنا.",
            explanationAr = "الفاعل هو Nous فيأخذ (notre للمفرد بنوعيه، و nos للجمع). كلمة voiture مفرد مؤنث فنختار notre.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_3",
            number = 3,
            sentenceParts = listOf("Je vais à ", " école."),
            targetBlanks = listOf("mon"),
            optionsPerBlank = listOf(listOf("ma", "mon", "mes")),
            subject = "Je",
            complements = listOf("école (مفرد مؤنث يبدأ بمتحرك é)"),
            fullSentenceFr = "Je vais à mon école.",
            translationAr = "أنا أذهب إلى مدرستي.",
            explanationAr = "⚠️ قاعدة ذهبية (N.B): الفاعل Je يأخذ (mon, ma, mes). كلمة école مؤنث مفرد ولكنها تبدأ بحرف متحرك (é)، لذلك نضع mon بدلاً من ma منعاً لالتقاء حرفين متحركين.",
            isVowelRule = true,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_4",
            number = 4,
            sentenceParts = listOf("Elles aiment ", " professeurs."),
            targetBlanks = listOf("leurs"),
            optionsPerBlank = listOf(listOf("leur", "leurs", "nos")),
            subject = "Elles",
            complements = listOf("professeurs (جمع مذكر/مؤنث)"),
            fullSentenceFr = "Elles aiment leurs professeurs.",
            translationAr = "هن يحببن أساتذتهن.",
            explanationAr = "الفاعل Elles جمع غائب فيأخذ (leur للمفرد، و leurs للجمع). كلمة professeurs جمع تنتهي بـ s، فنختار leurs مع s الجمع.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_5",
            number = 5,
            sentenceParts = listOf("Il parle à ", " amis."),
            targetBlanks = listOf("ses"),
            optionsPerBlank = listOf(listOf("son", "sa", "ses")),
            subject = "Il",
            complements = listOf("amis (جمع)"),
            fullSentenceFr = "Il parle à ses amis.",
            translationAr = "هو يتحدث إلى أصدقائه.",
            explanationAr = "الفاعل Il يأخذ (son, sa, ses). كلمة amis جمع تنتهي بـ s، فنختار ses.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_6",
            number = 6,
            sentenceParts = listOf("Il aime ", " amie."),
            targetBlanks = listOf("son"),
            optionsPerBlank = listOf(listOf("sa", "ses", "son")),
            subject = "Il",
            complements = listOf("amie (مفرد مؤنث يبدأ بمتحرك a)"),
            fullSentenceFr = "Il aime son amie.",
            translationAr = "هو يحب صديقته.",
            explanationAr = "⚠️ قاعدة ذهبية (N.B): الفاعل Il يأخذ (son, sa, ses). كلمة amie مفرد مؤنث لكنها تبدأ بحرف متحرك (a)، لذا نستبدل sa بـ son لسهولة النطق وتفادي التقاء ساكنين ومتحركين.",
            isVowelRule = true,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_7",
            number = 7,
            sentenceParts = listOf("Vous allez chez ", " grand-parents."),
            targetBlanks = listOf("vos"),
            optionsPerBlank = listOf(listOf("votre", "vos", "nos")),
            subject = "Vous",
            complements = listOf("grand-parents (جمع)"),
            fullSentenceFr = "Vous allez chez vos grand-parents.",
            translationAr = "أنتم تذهبون عند أجدادكم.",
            explanationAr = "الفاعل Vous يأخذ (votre للمفرد، و vos للجمع). كلمة grand-parents جمع تنتهي بـ s، فنختار vos.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_8",
            number = 8,
            sentenceParts = listOf("Tu prends ", " petit déjeuner ?"),
            targetBlanks = listOf("ton"),
            optionsPerBlank = listOf(listOf("ta", "ton", "tes")),
            subject = "Tu",
            complements = listOf("petit déjeuner (مفرد مذكر)"),
            fullSentenceFr = "Tu prends ton petit déjeuner ?",
            translationAr = "هل تتناول فطورك؟",
            explanationAr = "الفاعل Tu يأخذ (ton, ta, tes). تعبير petit déjeuner (وجبة الإفطار) مفرد مذكر، فنختار ton.",
            isVowelRule = false,
            pageReference = "p. 18"
        ),
        PossessiveExerciseItem(
            id = "u1_poss_9",
            number = 9,
            sentenceParts = listOf("Je prends ", " cadeau."),
            targetBlanks = listOf("mon"),
            optionsPerBlank = listOf(listOf("mes", "ma", "mon")),
            subject = "Je",
            complements = listOf("cadeau (مفرد مذكر)"),
            fullSentenceFr = "Je prends mon cadeau.",
            translationAr = "أنا آخذ هديتي.",
            explanationAr = "الفاعل Je يأخذ (mon, ma, mes). كلمة cadeau (هدية) مفرد مذكر، فنختار mon.",
            isVowelRule = false,
            pageReference = "p. 18"
        )
    )

    val unit1PronomOnChoisisExercises = listOf(
        PronomOnExerciseItem(
            id = "u1_on_ex1_1",
            number = 1,
            predicateFr = "est Egyptien.",
            correctAnswer = "On",
            fullSentenceFr = "On est Egyptien.",
            translationAr = "نحن مصريون / المرء مصري.",
            explanationAr = "الفعل « est » هو تصريف فعل Être مع المفرد الغائب (il / elle)، لذلك نختار الضمير « On » وليس Nous.",
            verbAnalyzed = "est (تصريف être مع المفرد مثل il/elle)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_2",
            number = 2,
            predicateFr = "faisons le devoir.",
            correctAnswer = "Nous",
            fullSentenceFr = "Nous faisons le devoir.",
            translationAr = "نحن نؤدي الواجب.",
            explanationAr = "الفعل « faisons » هو تصريف فعل Faire مع ضمير المتكلم الجمع وينتهي بـ (-ons)، لذلك نختار « Nous ».",
            verbAnalyzed = "faisons (ينتهي بـ -ons مع Nous)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_3",
            number = 3,
            predicateFr = "choisit les cadeaux.",
            correctAnswer = "On",
            fullSentenceFr = "On choisit les cadeaux.",
            translationAr = "نحن نختار الهدايا.",
            explanationAr = "الفعل « choisit » هو فعل مجموعة ثانية مصرف في المفرد وينتهي بـ (-t) مثل (il / elle)، لذلك نختار « On ».",
            verbAnalyzed = "choisit (ينتهي بـ -t في المفرد مع il/elle)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_4",
            number = 4,
            predicateFr = "finissons le travail.",
            correctAnswer = "Nous",
            fullSentenceFr = "Nous finissons le travail.",
            translationAr = "نحن ننهي العمل.",
            explanationAr = "الفعل « finissons » ينتهي بنهاية الجمع (-issons / -ons)، وهي علامة تصريف ضمير « Nous ».",
            verbAnalyzed = "finissons (ينتهي بـ -ons مع Nous)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_5",
            number = 5,
            predicateFr = "adore les livres d'histoire.",
            correctAnswer = "On",
            fullSentenceFr = "On adore les livres d'histoire.",
            translationAr = "نحن نعشق كتب التاريخ.",
            explanationAr = "الفعل « adore » ينتهي بـ (-e) في زمن المضارع كفعل مجموعة أولى مصرف في المفرد مثل (il / elle)، لذا نختار « On ».",
            verbAnalyzed = "adore (ينتهي بـ -e في المفرد مع il/elle)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_6",
            number = 6,
            predicateFr = "va au club.",
            correctAnswer = "On",
            fullSentenceFr = "On va au club.",
            translationAr = "نحن نذهب إلى النادي.",
            explanationAr = "الفعل « va » هو تصريف فعل Aller مع المفرد (il / elle)، لذلك نضع الضمير « On » (كما في مثال ص 19 الرسمي).",
            verbAnalyzed = "va (تصريف aller مع il/elle في المفرد)",
            pageReference = "p. 19"
        ),
        PronomOnExerciseItem(
            id = "u1_on_ex1_7",
            number = 7,
            predicateFr = "sommes Egyptiens.",
            correctAnswer = "Nous",
            fullSentenceFr = "Nous sommes Egyptiens.",
            translationAr = "نحن مصريون.",
            explanationAr = "الفعل « sommes » هو تصريف فعل Être الحصري مع ضمير المتكلم الجمع « Nous ».",
            verbAnalyzed = "sommes (تصريف être الحصري مع Nous)",
            pageReference = "p. 19"
        )
    )

    val unit1PronomOnReplaceExercises = listOf(
        PronomOnReplaceItem(
            id = "u1_on_ex2_1",
            number = 1,
            onSentenceFr = "On va au club.",
            nousSentenceFr = "Nous allons au club.",
            onSentenceAr = "نحن نذهب إلى النادي. (مع On)",
            nousSentenceAr = "نحن نذهب إلى النادي. (مع Nous)",
            verbTransformation = "va (مع on) ➔ allons (مع nous)",
            pageReference = "p. 19"
        ),
        PronomOnReplaceItem(
            id = "u1_on_ex2_2",
            number = 2,
            onSentenceFr = "On joue dans le jardin.",
            nousSentenceFr = "Nous jouons dans le jardin.",
            onSentenceAr = "نحن نلعب في الحديقة. (مع On)",
            nousSentenceAr = "نحن نلعب في الحديقة. (مع Nous)",
            verbTransformation = "joue (-e) ➔ jouons (-ons)",
            pageReference = "p. 19"
        ),
        PronomOnReplaceItem(
            id = "u1_on_ex2_3",
            number = 3,
            onSentenceFr = "On est content.",
            nousSentenceFr = "Nous sommes contents.",
            onSentenceAr = "نحن مسرورون / سعداء. (مع On)",
            nousSentenceAr = "نحن مسرورون / سعداء. (مع Nous)",
            verbTransformation = "est content ➔ sommes contents",
            pageReference = "p. 19"
        ),
        PronomOnReplaceItem(
            id = "u1_on_ex2_4",
            number = 4,
            onSentenceFr = "On a congé.",
            nousSentenceFr = "Nous avons congé.",
            onSentenceAr = "لدينا عطلة / إجازة. (مع On)",
            nousSentenceAr = "لدينا عطلة / إجازة. (مع Nous)",
            verbTransformation = "a (verbe avoir) ➔ avons",
            pageReference = "p. 19"
        ),
        PronomOnReplaceItem(
            id = "u1_on_ex2_5",
            number = 5,
            onSentenceFr = "On choisit le livre.",
            nousSentenceFr = "Nous choisissons le livre.",
            onSentenceAr = "نحن نختار الكتاب. (مع On)",
            nousSentenceAr = "نحن نختار الكتاب. (مع Nous)",
            verbTransformation = "choisit (-it) ➔ choisissons (-issons)",
            pageReference = "p. 19"
        )
    )

    val bookletPronounsRulesSujet = listOf(
        BookletPronounRuleItem("pr_suj_il", BookletPronounCategory.SUJET, "Il", "remplace un nom masculin.", "يحل محل اسم مفرد مذكر (عاقل أو غير عاقل)", "Ali va au club.", "Il va au club.", "علي ذهب إلى النادي ➔ هو ذهب إلى النادي.", "p. 20-21"),
        BookletPronounRuleItem("pr_suj_elle", BookletPronounCategory.SUJET, "Elle", "remplace un nom féminin.", "يحل محل اسم مفرد مؤنث", "Mona est Egyptienne.", "Elle est Egyptienne.", "منى مصرية ➔ هي مصرية.", "p. 20-21"),
        BookletPronounRuleItem("pr_suj_nous", BookletPronounCategory.SUJET, "Nous", "remplace un nom + moi", "يحل محل (أي اسم شخص + moi)", "Rami et moi jouons au tennis.", "Nous jouons au tennis.", "رامي وأنا نلعب التنس ➔ نحن نلعب التنس.", "p. 20-21"),
        BookletPronounRuleItem("pr_suj_vous", BookletPronounCategory.SUJET, "Vous", "remplace un nom + toi", "يحل محل (أي اسم شخص + toi)", "Karim et toi êtes gentils.", "Vous êtes gentils.", "كريم وأنت لطفاء ➔ أنتم لطفاء.", "p. 20-21"),
        BookletPronounRuleItem("pr_suj_ils", BookletPronounCategory.SUJET, "Ils", "remplace nom masculin pluriel.", "يحل محل اسم جمع مذكر (أو مذكر ومؤنث معاً)", "Les élèves écoutent.", "Ils écoutent.", "التلاميذ يستمعون ➔ هم يستمعون.", "p. 20-21"),
        BookletPronounRuleItem("pr_suj_elles", BookletPronounCategory.SUJET, "Elles", "remplace nom féminin pluriel.", "يحل محل اسم جمع مؤنث خالص", "Les filles sont contentes.", "Elles sont contentes.", "البنات مسرورات ➔ هن مسرورات.", "p. 20-21")
    )

    val bookletPronounsSujetExamples = listOf(
        Triple("1- Ali va au club", "➔ Il va au club.", "علي ➔ Il (مفرد مذكر)"),
        Triple("2- Adham et toi êtes Egyptiens", "➔ Vous êtes Egyptiens.", "Adham et toi ➔ Vous (اسم + toi)"),
        Triple("3- La maitresse est gentille", "➔ Elle est gentille.", "La maitresse ➔ Elle (مفرد مؤنث)")
    )

    val bookletPronounsRulesCOD = listOf(
        BookletPronounRuleItem("pr_cod_le", BookletPronounCategory.COD, "Le", "Remplace un nom masculin singulier.", "يحل محل مفعول مباشر مفرد مذكر (بدون حرف جر)", "Il regarde le match.", "- Il le regarde.", "هو يشاهد المباراة ➔ هو يشاهدها (مباشر مفرد مذكر).", "p. 20-21"),
        BookletPronounRuleItem("pr_cod_la", BookletPronounCategory.COD, "La", "Remplace un nom féminin singulier.", "يحل محل مفعول مباشر مفرد مؤنث (بدون حرف جر)", "Elle prend la robe.", "- Elle la prend.", "هي تأخذ الفستان ➔ هي تأخذه.", "p. 20-21"),
        BookletPronounRuleItem("pr_cod_lapostrophe", BookletPronounCategory.COD, "L'", "Remplace un nom masculin ou féminin singulier mais le verbe commence par une voyelle (a - e - i - o - u - h - y)", "يحل محل مذكر أو مؤنث مفرد إذا بدأ الفعل بحرف متحرك", "Tu aimes ton frère.", "- Tu l'aimes.", "أنت تحب أخاك ➔ أنت تحبه.", "p. 20-21"),
        BookletPronounRuleItem("pr_cod_les", BookletPronounCategory.COD, "Les", "remplace un pluriel. (s - x)", "يحل محل مفعول مباشر جمع بنوعيه (ينتهي بـ s أو x)", "Elles aiment les fruits.", "- Elles les aiment.", "هن يحببن الفواكه ➔ هن يحببنها (les لا تختصر أمام المتحرك).", "p. 20-21")
    )

    val bookletPronounsCodExamples = listOf(
        Triple("1- Il regarde le match.", "- Il le regarde.", "le match (مفرد مذكر) ➔ le قبل الفعل"),
        Triple("2- Tu aimes ton frère.", "- Tu l'aimes.", "ton frère + فعل يبدأ بمتحرك (aimes) ➔ l'"),
        Triple("3- Elles aiment les fruits.", "- Elles les aiment.", "les fruits (جمع) ➔ les (لا تختصر أبداً)")
    )

    val bookletCoiConditions = listOf(
        "1- précédé d'une Préposition (à, au, aux) — مسبوق بحرف جر",
        "2- suivi d'une personne (شخص عاقل) — يدل على إنسان عاقل"
    )

    val bookletPronounsRulesCOI = listOf(
        BookletPronounRuleItem("pr_coi_lui", BookletPronounCategory.COI, "Lui", "Remplace un nom singulier (précédé de à, au, à la, à l' + une personne)", "يحل محل مفعول غير مباشر مفرد (مذكر أو مؤنث) مسبوق بحرف جر + شخص عاقل", "1- Je parle à mon ami.", "- Je lui parle.", "أنا أتحدث إلى صديقي ➔ أنا أتحدث إليه (Lui للمفرد بنوعيه).", "p. 20-22"),
        BookletPronounRuleItem("pr_coi_leur", BookletPronounCategory.COI, "Leur", "Remplace un nom pluriel (précédé de à, aux + personnes)", "يحل محل مفعول غير مباشر جمع (مذكر أو مؤنث) مسبوق بحرف جر + أشخاص", "2- Il téléphone à mes amis.", "- Il leur téléphone.", "هو يتصل بأصدقائي ➔ هو يتصل بهم (Leur ضمير بدون s دائماً).", "p. 20-22")
    )

    val bookletPronounsCoiExamples = listOf(
        Triple("1- Je parle à mon ami.", "- Je lui parle.", "à mon ami (مفرد عاقل) ➔ lui"),
        Triple("2- Il téléphone à mes amis.", "- Il leur téléphone.", "à mes amis (جمع عاقل) ➔ leur"),
        Triple("3- Mona offre des fleurs à ses parents.", "- Mona leur offre des fleurs", "à ses parents (جمع عاقل) ➔ leur"),
        Triple("4- Nous demandons à nos professeurs", "- Nous leur demandons.", "à nos professeurs (جمع عاقل) ➔ leur")
    )

    val bookletPronounsOfficial15Exercises = listOf(
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_1",
            number = 1,
            fullSentenceFr = "Elle parle à son amie.",
            underlinedPart = "à son amie",
            category = BookletPronounCategory.COI,
            targetPronoun = "lui",
            options = listOf("lui", "leur", "la"),
            transformedSentenceFr = "Elle lui parle.",
            sentenceAr = "هي تتحدث إلى صديقتها.",
            transformedSentenceAr = "هي تتحدث إليها.",
            explanationAr = "« à son amie » مفعول به غير مباشر عاقل مفرد مؤنث مسبوق بحرف الجر (à)، لذلك يستبدل بالضمير (lui) ويوضع أمام الفعل المصرف مباشرة.",
            ruleTag = "C.O.I (مفرد عاقل مسبوق بـ à ➔ lui)",
            positionRuleAr = "يوضع قبل الفعل المصرف (parle).",
            pageReference = "p. 22"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_2",
            number = 2,
            fullSentenceFr = "Mona est Egyptienne.",
            underlinedPart = "Mona",
            category = BookletPronounCategory.SUJET,
            targetPronoun = "Elle",
            options = listOf("Elles", "Elle", "Il"),
            transformedSentenceFr = "Elle est Egyptienne.",
            sentenceAr = "منى مصرية.",
            transformedSentenceAr = "هي مصرية.",
            explanationAr = "« Mona » فاعل مفرد مؤنث (Sujet féminin singulier)، لذلك يُستبدل بضمير الفاعل (Elle).",
            ruleTag = "Sujet (اسم مفرد مؤنث ➔ Elle)",
            positionRuleAr = "يحل محل الفاعل في بداية الجملة.",
            pageReference = "p. 22"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_3",
            number = 3,
            fullSentenceFr = "Elle va prendre cette jupe.",
            underlinedPart = "cette jupe",
            category = BookletPronounCategory.COD,
            targetPronoun = "la",
            options = listOf("la", "l'", "lui"),
            transformedSentenceFr = "Elle va la prendre.",
            sentenceAr = "هي سوف تأخذ هذه الجونة.",
            transformedSentenceAr = "هي سوف تأخذها.",
            explanationAr = "« cette jupe » مفعول به مباشر مفرد مؤنث (COD féminin singulier). وحيث أن الجملة بها فعلان (مصرف va + مصدر prendre)، فإن الضمير يوضع قبل المصدر مباشرة.",
            ruleTag = "C.O.D (مفرد مؤنث + يوضع قبل المصدر)",
            positionRuleAr = "يوضع قبل الفعل في المصدر (prendre).",
            pageReference = "p. 22"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_4",
            number = 4,
            fullSentenceFr = "Nous écoutons la radio.",
            underlinedPart = "la radio",
            category = BookletPronounCategory.COD,
            targetPronoun = "l'",
            options = listOf("lui", "la", "l'"),
            transformedSentenceFr = "Nous l'écoutons.",
            sentenceAr = "نحن نستمع إلى الراديو.",
            transformedSentenceAr = "نحن نستمع إليه.",
            explanationAr = "« la radio » مفعول مباشر مفرد مؤنث، كان الأصل وضع (la)، ولكن الفعل (écoutons) يبدأ بحرف متحرك (é)، فتحولت (la) إلى (l') منعاً لالتقاء متحركين.",
            ruleTag = "C.O.D (مفرد مؤنث + فعل يبدأ بمتحرك ➔ l')",
            positionRuleAr = "يوضع قبل الفعل المصرف الذي يبدأ بمتحرك.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_5",
            number = 5,
            fullSentenceFr = "Elles aiment les fruits.",
            underlinedPart = "les fruits",
            category = BookletPronounCategory.COD,
            targetPronoun = "les",
            options = listOf("des", "les", "leur"),
            transformedSentenceFr = "Elles les aiment.",
            sentenceAr = "هن يحببن الفواكه.",
            transformedSentenceAr = "هن يحببنها.",
            explanationAr = "« les fruits » مفعول به مباشر جمع (COD pluriel)، يستبدل بـ (les) ويوضع أمام الفعل المصرف (aiment). ملحوظة: ضمير الجمع (les) لا يختصر أمام المتحرك.",
            ruleTag = "C.O.D (جمع مذكر/مؤنث ➔ les)",
            positionRuleAr = "يوضع أمام الفعل المصرف (les aiment).",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_6",
            number = 6,
            fullSentenceFr = "J'ai répondu à mon professeur.",
            underlinedPart = "à mon professeur",
            category = BookletPronounCategory.COI,
            targetPronoun = "lui",
            options = listOf("le", "leur", "lui"),
            transformedSentenceFr = "Je lui ai répondu.",
            sentenceAr = "لقد أجبت أستاذي.",
            transformedSentenceAr = "لقد أجبت عليه.",
            explanationAr = "« à mon professeur » مفعول غير مباشر عاقل مفرد مسبوق بـ (à). في زمن الماضي المركب (passé composé)، يوضع الضمير قبل الفعل المساعد (ai).",
            ruleTag = "C.O.I (مفرد عاقل + يوضع قبل المساعد ai)",
            positionRuleAr = "يوضع قبل الفعل المساعد (ai) في الماضي المركب.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_7",
            number = 7,
            fullSentenceFr = "Les professeurs expliquent les leçons.",
            underlinedPart = "Les professeurs ... les leçons",
            category = BookletPronounCategory.COD,
            targetPronoun = "Ils / les",
            options = listOf("Ils expliquent les leçons.", "Les professeurs leur expliquent.", "Ils les expliquent."),
            transformedSentenceFr = "Ils les expliquent.",
            sentenceAr = "الأساتذة يشرحون الدروس.",
            transformedSentenceAr = "هم يشرحونها.",
            explanationAr = "في كتيّب المعهد تم وضع خط تحت الفاعل «Les professeurs» (جمع مذكر ➔ Ils) وتحت المفعول المباشر الجمع «les leçons» (جمع مباشر ➔ les)؛ فتصبح الجملة بالكامل: Ils les expliquent.",
            ruleTag = "Sujet + COD (استبدال مزدوج: Ils + les)",
            positionRuleAr = "استبدال الفاعل بـ Ils ووضع les قبل الفعل المصرف.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_8",
            number = 8,
            fullSentenceFr = "Vous aimez ce pantalon.",
            underlinedPart = "ce pantalon",
            category = BookletPronounCategory.COD,
            targetPronoun = "l'",
            options = listOf("le", "l'", "lui"),
            transformedSentenceFr = "Vous l'aimez.",
            sentenceAr = "أنتم تحبون هذا البنطال.",
            transformedSentenceAr = "أنتم تحبونه.",
            explanationAr = "« ce pantalon » مفعول به مباشر مفرد مذكر (COD). يوضع قبل الفعل (aimez) ولأنه يبدأ بحرف متحرك (a)، تتحول (le) إلى (l').",
            ruleTag = "C.O.D (مفرد مذكر + فعل يبدأ بمتحرك ➔ l')",
            positionRuleAr = "يوضع قبل الفعل المصرف ويبدأ بمتحرك.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_9",
            number = 9,
            fullSentenceFr = "Il fait son devoir.",
            underlinedPart = "son devoir",
            category = BookletPronounCategory.COD,
            targetPronoun = "le",
            options = listOf("le", "la", "lui"),
            transformedSentenceFr = "Il le fait.",
            sentenceAr = "هو يؤدي واجبه.",
            transformedSentenceAr = "هو يؤديه.",
            explanationAr = "« son devoir » مفعول مباشر مفرد مذكر (COD)، يستبدل بالضمير (le) ويوضع مباشرة أمام الفعل المصرف (fait).",
            ruleTag = "C.O.D (مفرد مذكر بدون حرف جر ➔ le)",
            positionRuleAr = "يوضع أمام الفعل المصرف مباشرةً.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_10",
            number = 10,
            fullSentenceFr = "Il offre un cadeau à son père.",
            underlinedPart = "à son père",
            category = BookletPronounCategory.COI,
            targetPronoun = "lui",
            options = listOf("le", "lui", "leur"),
            transformedSentenceFr = "Il lui offre un cadeau.",
            sentenceAr = "هو يقدم هدية لوالده.",
            transformedSentenceAr = "هو يقدم له هدية.",
            explanationAr = "« à son père » مفعول به غير مباشر عاقل مفرد مسبوق بـ (à)، يستبدل بـ (lui) ويوضع قبل الفعل (offre) مع بقاء المفعول المباشر un cadeau.",
            ruleTag = "C.O.I (مفرد عاقل مسبوق بـ à ➔ lui)",
            positionRuleAr = "يوضع قبل الفعل المصرف (Il lui offre un cadeau).",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_11",
            number = 11,
            fullSentenceFr = "Rami et moi jouons au tennis.",
            underlinedPart = "Rami et moi",
            category = BookletPronounCategory.SUJET,
            targetPronoun = "Nous",
            options = listOf("Nous", "Ils", "Vous"),
            transformedSentenceFr = "Nous jouons au tennis.",
            sentenceAr = "رامي وأنا نلعب التنس.",
            transformedSentenceAr = "نحن نلعب التنس.",
            explanationAr = "« Rami et moi » قاعدة ص 20 الرسمية: (un nom + moi) يُستبدل بضمير الفاعل (Nous).",
            ruleTag = "Sujet (اسم + moi ➔ Nous)",
            positionRuleAr = "يحل محل الفاعل في بداية الجملة.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_12",
            number = 12,
            fullSentenceFr = "Ali a mangé le sandwich.",
            underlinedPart = "le sandwich",
            category = BookletPronounCategory.COD,
            targetPronoun = "l'",
            options = listOf("le", "lui", "l'"),
            transformedSentenceFr = "Ali l'a mangé.",
            sentenceAr = "علي أكل الساندويتش.",
            transformedSentenceAr = "علي أكله.",
            explanationAr = "« le sandwich » مفعول مباشر مفرد مذكر. يوضع قبل المساعد (a) في الماضي المركب، وبما أن (a) حرف متحرك، تصبح (le) ➔ (l').",
            ruleTag = "C.O.D (مفرد مذكر + مساعد يبدأ بمتحرك ➔ l')",
            positionRuleAr = "يوضع قبل الفعل المساعد (a) في الماضي المركب.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_13",
            number = 13,
            fullSentenceFr = "Elle va téléphoner aux directeurs.",
            underlinedPart = "aux directeurs",
            category = BookletPronounCategory.COI,
            targetPronoun = "leur",
            options = listOf("les", "leur", "leurs"),
            transformedSentenceFr = "Elle va leur téléphoner.",
            sentenceAr = "هي سوف تتصل بالمديرين.",
            transformedSentenceAr = "هي سوف تتصل بهم.",
            explanationAr = "« aux directeurs » مفعول غير مباشر جمع عاقل مسبوق بـ (aux)، يستبدل بـ (leur بدون s). وعند وجود فعلين (مصرف + مصدر)، يوضع الضمير قبل المصدر (téléphoner).",
            ruleTag = "C.O.I (جمع عاقل + يوضع قبل المصدر ➔ leur)",
            positionRuleAr = "يوضع قبل الفعل في المصدر (téléphoner).",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_14",
            number = 14,
            fullSentenceFr = "Il prend ces cahiers.",
            underlinedPart = "ces cahiers",
            category = BookletPronounCategory.COD,
            targetPronoun = "les",
            options = listOf("les", "des", "leur"),
            transformedSentenceFr = "Il les prend.",
            sentenceAr = "هو يأخذ هذه الكراريس.",
            transformedSentenceAr = "هو يأخذها.",
            explanationAr = "« ces cahiers » مفعول به مباشر جمع (ينتهي بـ s)، يستبدل بالضمير (les) ويوضع مباشرة أمام الفعل المصرف (prend).",
            ruleTag = "C.O.D (جمع مباشر ➔ les)",
            positionRuleAr = "يوضع أمام الفعل المصرف مباشرةً.",
            pageReference = "p. 23"
        ),
        BookletPronounOfficialExerciseItem(
            id = "u1_pr_ex_15",
            number = 15,
            fullSentenceFr = "Karim et toi êtes gentils.",
            underlinedPart = "Karim et toi",
            category = BookletPronounCategory.SUJET,
            targetPronoun = "Vous",
            options = listOf("Nous", "Vous", "Ils"),
            transformedSentenceFr = "Vous êtes gentils.",
            sentenceAr = "كريم وأنت لطفاء.",
            transformedSentenceAr = "أنتم لطفاء.",
            explanationAr = "« Karim et toi » قاعدة ص 20 الرسمية: (un nom + toi) يُستبدل بضمير المخاطب الجمع (Vous).",
            ruleTag = "Sujet (اسم + toi ➔ Vous)",
            positionRuleAr = "يحل محل الفاعل في بداية الجملة.",
            pageReference = "p. 23"
        )
    )

    val unit1FaisDesPhrasesList = listOf(
        FaisDesPhrasesItem(
            id = "u1_fp_1",
            number = 1,
            promptFr = "Préparer – le diner",
            promptAr = "يُجهّز / يُعِدّ – العشاء",
            verbInfinitive = "Préparer",
            complement = "le dîner",
            modelSentenceFr = "Maman prépare le dîner.",
            modelSentenceAr = "ماما تُعِدّ العشاء.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("prépare", "Maman", "le", "dîner."),
            correctOrder = listOf("Maman", "prépare", "le", "dîner."),
            grammarTipAr = "فعل (Préparer) من أفعال المجموعة الأولى (1er groupe) المنتهية بـ -er. عند تصريفه في المضارع مع (Maman = Elle) نحذف -er ونضيف e ➔ prépare. بنية الجملة البسيطة في الفرنسية: فاعل (Sujet) + فعل مصرف (Verbe) + مفعول (Complément).",
            conjugationSamples = listOf(),
            pageReference = "p. 24"
        ),
        FaisDesPhrasesItem(
            id = "u1_fp_2",
            number = 2,
            promptFr = "Adorer – livres d'histoire .",
            promptAr = "يعشق / يحب بشدة – كتب التاريخ",
            verbInfinitive = "Adorer",
            complement = "les livres d'histoire",
            modelSentenceFr = "J'adore les livres d'histoire.",
            modelSentenceAr = "أنا أعشق كتب التاريخ.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("les", "J'adore", "d'histoire.", "livres"),
            correctOrder = listOf("J'adore", "les", "livres", "d'histoire."),
            grammarTipAr = "فعل (Adorer) يبدأ بحرف متحرك (A)، لذلك مع ضمير المتكلم Je يتحول إلى (J'adore) بحذف حرف الـ e ووضع الفاصلة العليا (l'apostrophe). لاحظ أيضاً إضافة أداة المعرفة الجمع (les) قبل كلمة livres، لأن أفعال الميول والتفضيل (aimer, adorer, préférer) تأخذ دائماً أدوات معرفة (le, la, les).",
            conjugationSamples = listOf(),
            pageReference = "p. 24"
        ),
        FaisDesPhrasesItem(
            id = "u1_fp_3",
            number = 3,
            promptFr = "acheter un billet – pour le Caire.",
            promptAr = "يشتري تذكرة – إلى القاهرة",
            verbInfinitive = "Acheter",
            complement = "un billet pour le Caire",
            modelSentenceFr = "J'achète un billet pour le Caire.",
            modelSentenceAr = "أنا أشتري تذكرة إلى القاهرة.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("un", "J'achète", "pour", "billet", "le Caire."),
            correctOrder = listOf("J'achète", "un", "billet", "pour", "le Caire."),
            grammarTipAr = "فعل (Acheter) يبدأ بحرف متحرك لذلك مع Je يصبح (J'achète). انتبه للنبرة المائلة (accent grave) على حرف الـ e في (achète, achètes, achètent). كما أن القاهرة تأخذ أداة المعرفة المذكرة (le Caire) وحرف الجر pour يعني (إلى / من أجل).",
            conjugationSamples = listOf(),
            pageReference = "p. 24"
        ),
        FaisDesPhrasesItem(
            id = "u1_fp_4",
            number = 4,
            promptFr = "Passer – Vacances",
            promptAr = "يقضي – الإجازة / العطلة",
            verbInfinitive = "Passer",
            complement = "les vacances",
            modelSentenceFr = "Je passe les vacances à Alexandrie.",
            modelSentenceAr = "أنا أقضي الإجازة في الإسكندرية.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("les", "Je", "passe", "à Alexandrie.", "vacances"),
            correctOrder = listOf("Je", "passe", "les", "vacances", "à Alexandrie."),
            grammarTipAr = "فعل (Passer) عند استخدامه مع الوقت أو الإجازة يعني (يقضي). كلمة (Vacances) في اللغة الفرنسية تأتي بصيغة الجمع المؤنث دائماً (les vacances). نستخدم حرف الجر (à) مع المدن: (à Alexandrie, à Louxor, à Assouan).",
            conjugationSamples = listOf(),
            pageReference = "p. 24"
        )
    )

    val unit2QuiParleItems = listOf(
        BookletQuiParleItem(
            id = "u2_qp_1",
            number = 1,
            quoteFr = "« Bonjour monsieur, que désirez – vous ? »",
            quoteAr = "«صباح الخير سيدي، ماذا ترغب / تطلب؟»",
            speakerFr = "Le garçon",
            alternativeSpeakersFr = listOf("Le serveur", "Le vendeur"),
            speakerAr = "النادل / الجرسون (Le garçon)",
            options = listOf("Le client", "Le médecin", "Le garçon"),
            situationContextAr = "سؤال تحية واستفسار يوجهه النادل أو الجرسون للزبون في المطعم لتلقي طلبات الطعام أو الشراب."
        ),
        BookletQuiParleItem(
            id = "u2_qp_2",
            number = 2,
            quoteFr = "« Où est le musée Egyptien ? »",
            quoteAr = "«أين يقع المتحف المصري؟»",
            speakerFr = "Le touriste",
            alternativeSpeakersFr = listOf("Un touriste", "Un passant"),
            speakerAr = "السائح (Le touriste)",
            options = listOf("Le guide", "Le touriste", "Le vendeur"),
            situationContextAr = "سؤال يطرحه السائح الأجنبي في الشارع لمعرفة طريق وموقع المتحف المصري."
        ),
        BookletQuiParleItem(
            id = "u2_qp_3",
            number = 3,
            quoteFr = "« L'addition, s'il Vous plait. »",
            quoteAr = "«الحساب / الفاتورة من فضلك.»",
            speakerFr = "Le client",
            alternativeSpeakersFr = listOf("Un client"),
            speakerAr = "الزبون (Le client)",
            options = listOf("Le chauffeur", "Le garçon", "Le client"),
            situationContextAr = "عبارة شهيرة يطلب بها الزبون في المطعم أو الكافيه من النادل إحضار فاتورة الحساب بعد الانتهاء من تناول الوجبة."
        ),
        BookletQuiParleItem(
            id = "u2_qp_4",
            number = 4,
            quoteFr = "« Je Voudrais du poisson, du riz et de la salade. »",
            quoteAr = "«أود سمكاً وأرزاً وسلطة من فضلك.»",
            speakerFr = "Le client",
            alternativeSpeakersFr = listOf("Un client"),
            speakerAr = "الزبون (Le client)",
            options = listOf("Le client", "Le guide", "Le cuisinier"),
            situationContextAr = "طلب مهذب باستخدام صيغة (Je voudrais) وأدوات التجزئة يوجهه الزبون للنادل لتحديد مكونات وجبته."
        ),
        BookletQuiParleItem(
            id = "u2_qp_5",
            number = 5,
            quoteFr = "« Ça Fait combien, cette robe? »",
            quoteAr = "«بكم هذا الفستان؟ / كم سعر هذا الفستان؟»",
            speakerFr = "La cliente",
            alternativeSpeakersFr = listOf("Une cliente", "L'acheteuse"),
            speakerAr = "الزبونة (La cliente)",
            options = listOf("Le vendeur", "Le réceptionniste", "La cliente"),
            situationContextAr = "سؤال تطرحه الزبونة أو المشترية على البائع في متجر الملابس للاستفسار عن ثمن الفستان."
        ),
        BookletQuiParleItem(
            id = "u2_qp_6",
            number = 6,
            quoteFr = "« C'est le trésor de tout Ankh Amoun. »",
            quoteAr = "«هذا كنز ومقتنيات توت عنخ آمون.»",
            speakerFr = "Le guide",
            alternativeSpeakersFr = listOf("Un guide touristique"),
            speakerAr = "المرشد السياحي (Le guide)",
            options = listOf("Le mécanicien", "Le guide", "Le touriste"),
            situationContextAr = "معلومة وشرح تاريخي يقدمه المرشد السياحي للسياح أثناء جولتهم داخل المتحف المصري."
        )
    )

    val unit2OuVasTuItems = listOf(
        BookletOuVasTuItem(
            id = "u2_ovt_1",
            number = 1,
            activityFr = "Prendre des repas.",
            activityAr = "لتناول وجبات الطعام (الغداء أو العشاء).",
            expectedAnswerFr = "Au restaurant.",
            alternativeAnswersFr = listOf("Au restaurant.", "Je vais au restaurant.", "au restaurant"),
            answerAr = "إلى المطعم (Au restaurant).",
            options = listOf("Au restaurant.", "À la pharmacie.", "Au cinéma."),
            explanationAr = "نذهب إلى المطعم (Au restaurant) لتناول الوجبات الشهية. كلمة restaurant مفرد مذكر مسبوقة بحرف الجر المدغم (au)."
        ),
        BookletOuVasTuItem(
            id = "u2_ovt_2",
            number = 2,
            activityFr = "Acheter des vêtements.",
            activityAr = "لشراء الملابس والأزياء.",
            expectedAnswerFr = "Au magasin.",
            alternativeAnswersFr = listOf("Au magasin.", "Au magasin de vêtements.", "au magasin"),
            answerAr = "إلى المحل / المتجر (Au magasin).",
            options = listOf("Au musée.", "Au magasin.", "À l'hôpital."),
            explanationAr = "المحل التجاري (Au magasin) هو المكان المناسب لشراء الملابس وتجربتها."
        ),
        BookletOuVasTuItem(
            id = "u2_ovt_3",
            number = 3,
            activityFr = "Boire du thé.",
            activityAr = "لشرب الشاي أو المشروبات الخفيفة.",
            expectedAnswerFr = "Au café.",
            alternativeAnswersFr = listOf("Au café.", "au café", "Au salon de thé."),
            answerAr = "إلى المقهى (Au café).",
            options = listOf("Au café.", "Au club.", "À la gare."),
            explanationAr = "المقهى (Au café) هو المكان المخصص للجلوس وتناول المشروبات الساخنة والباردة كالشاي والقهوة."
        ),
        BookletOuVasTuItem(
            id = "u2_ovt_4",
            number = 4,
            activityFr = "Regarder les monuments.",
            activityAr = "لمشاهدة الآثار والتماثيل التاريخية.",
            expectedAnswerFr = "Au musée.",
            alternativeAnswersFr = listOf("Au musée.", "Aux pyramides.", "au musée"),
            answerAr = "إلى المتحف (Au musée) أو الأهرامات (Aux pyramides).",
            options = listOf("À la poste.", "Au stade.", "Au musée."),
            explanationAr = "نذهب إلى المتحف (Au musée) لمشاهدة الآثار والمعالم التاريخية والحضارية العظيمة."
        ),
        BookletOuVasTuItem(
            id = "u2_ovt_5",
            number = 5,
            activityFr = "Consulter le médecin.",
            activityAr = "لاستشارة الطبيب أو إجراء الفحص الطبي.",
            expectedAnswerFr = "À l'hôpital.",
            alternativeAnswersFr = listOf("À l'hôpital.", "Chez le médecin.", "À la clinique.", "à l'hôpital"),
            answerAr = "إلى المستشفى (À l'hôpital) أو عند الطبيب (Chez le médecin).",
            options = listOf("À l'hôpital.", "Au restaurant.", "Au club."),
            explanationAr = "المستشفى (À l'hôpital) والعيادة هما المكانان الرسميان لإجراء الفحوصات الطبية واستشارة الطبيب."
        ),
        BookletOuVasTuItem(
            id = "u2_ovt_6",
            number = 6,
            activityFr = "Acheter des médicaments.",
            activityAr = "لشراء الأدوية والعقاقير الطبية.",
            expectedAnswerFr = "À la pharmacie.",
            alternativeAnswersFr = listOf("À la pharmacie.", "à la pharmacie"),
            answerAr = "إلى الصيدلية (À la pharmacie).",
            options = listOf("À l'hôtel.", "Au café.", "À la pharmacie."),
            explanationAr = "الصيدلية (À la pharmacie) هي المكان الحصري لصرف وشراء الأدوية. كلمة مؤنثة تأخذ (à la)."
        )
    )

    val unit2FaisDesPhrasesItems = listOf(
        FaisDesPhrasesItem(
            id = "u2_fp_1",
            number = 1,
            promptFr = "Réserver – une table",
            promptAr = "يحجز – طاولة",
            verbInfinitive = "Réserver",
            complement = "une table",
            modelSentenceFr = "Je réserve une table au restaurant.",
            modelSentenceAr = "أنا أحجز طاولة في المطعم.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("table", "Je", "au restaurant.", "réserve", "une"),
            correctOrder = listOf("Je", "réserve", "une", "table", "au restaurant."),
            grammarTipAr = "فعل (Réserver) مجموعة أولى ينتهي بـ (-er). يصرف مع Je بحذف النهاية وإضافة e ➔ (Je réserve). كلمة table مفرد مؤنث تأخذ أداة النكرة (une).",
            conjugationSamples = listOf(),
            pageReference = "p. 39"
        ),
        FaisDesPhrasesItem(
            id = "u2_fp_2",
            number = 2,
            promptFr = "manger – Au restaurant",
            promptAr = "يأكل – في المطعم",
            verbInfinitive = "Manger",
            complement = "au restaurant",
            modelSentenceFr = "Nous mangeons le déjeuner au restaurant.",
            modelSentenceAr = "نحن نأكل طعام الغداء في المطعم.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("au restaurant.", "le déjeuner", "mangeons", "Nous"),
            correctOrder = listOf("Nous", "mangeons", "le déjeuner", "au restaurant."),
            grammarTipAr = "فعل (Manger) مجموعة أولى، مع (Nous) نضيف حرف e قبل النهاية للحفاظ على سلامة النطق: (Nous mangeons). اسم المكان restaurant مذكر يأخذ (au).",
            conjugationSamples = listOf(),
            pageReference = "p. 39"
        ),
        FaisDesPhrasesItem(
            id = "u2_fp_3",
            number = 3,
            promptFr = "Inviter – anniversaire – le mardi",
            promptAr = "يدعو – عيد ميلاد – يوم الثلاثاء",
            verbInfinitive = "Inviter",
            complement = "mes amis pour mon anniversaire le mardi",
            modelSentenceFr = "J'invite mes amis pour mon anniversaire le mardi.",
            modelSentenceAr = "أدعو أصدقائي لحفل عيد ميلادي يوم الثلاثاء.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("pour", "J'invite", "anniversaire", "mes amis", "mon", "le mardi."),
            correctOrder = listOf("J'invite", "mes amis", "pour", "mon", "anniversaire", "le mardi."),
            grammarTipAr = "فعل (Inviter) يبدأ بحرف متحرك، فيصبح مع ضمير المتكلم: (J'invite). أيام الأسبوع في الفرنسية تسبق بأداة المعرفة المذكرة (le mardi) للدلالة على الموعد.",
            conjugationSamples = listOf(),
            pageReference = "p. 40"
        ),
        FaisDesPhrasesItem(
            id = "u2_fp_4",
            number = 4,
            promptFr = "aller – au café",
            promptAr = "يذهب – إلى المقهى",
            verbInfinitive = "Aller",
            complement = "au café",
            modelSentenceFr = "Je vais au café avec mes amis.",
            modelSentenceAr = "أنا أذهب إلى المقهى مع أصدقائي.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("au café", "Je", "vais", "avec", "mes amis."),
            correctOrder = listOf("Je", "vais", "au café", "avec", "mes amis."),
            grammarTipAr = "فعل (Aller) فعل شاذ من المجموعة الثالثة. تصريفه مع Je هو (Je vais) ومع Nous هو (Nous allons). كلمة café مذكر وتأخذ حرف الجر المدغم (au).",
            conjugationSamples = listOf(),
            pageReference = "p. 40"
        ),
        FaisDesPhrasesItem(
            id = "u2_fp_5",
            number = 5,
            promptFr = "être – stagiaire",
            promptAr = "يكون – متدرب / تحت التدريب",
            verbInfinitive = "Être",
            complement = "stagiaire",
            modelSentenceFr = "Je suis stagiaire à El Ahram Hebdo.",
            modelSentenceAr = "أنا متدرب في جريدة الأهرام إبدو.",
            alternativeSentences = listOf(),
            scrambledWords = listOf("à El Ahram Hebdo.", "suis", "Je", "stagiaire"),
            correctOrder = listOf("Je", "suis", "stagiaire", "à El Ahram Hebdo."),
            grammarTipAr = "فعل الكينونة (Être) هو الرابط الأساسي بين الفاعل والصفة أو المسمى المهني: (Je suis stagiaire). لاحظ أن المسمى الوظيفي بعد être لا يسبق بأداة نكرة في هذا السياق.",
            conjugationSamples = listOf(),
            pageReference = "p. 40"
        )
    )

    val unit2CompositionTopic = CompositionTopic(
        id = "u2_comp_restaurant",
        titleFr = "Un déjeuner au restaurant en famille",
        titleAr = "غداء في المطعم مع العائلة",
        instructionFr = "Ton père vous invite pour prendre le déjeuner au restaurant. Raconte ce qui se passe.",
        instructionAr = "والدك يدعوكم لتناول طعام الغداء في المطعم. احكِ ما حدث خلال هذه الزيارة بالتفصيل.",
        pageReference = "p. 40",
        requiredElements = listOf(
            Pair("المناسبة والدعوة", "Mon père nous invite à déjeuner"),
            Pair("موعد ووسيلة الذهاب", "Nous allons en voiture à 14 heures"),
            Pair("الاستقبال وقائمة الطعام", "Le garçon nous apporte le menu"),
            Pair("المقبلات وفاتح الشهية", "Comme entrée, de la salade et des crudités"),
            Pair("الطبق الرئيسي", "Comme plat principal, du poulet et du riz"),
            Pair("التحلية والحلويات", "Comme dessert, un gâteau au chocolat"),
            Pair("الحساب والانطباع العام", "Mon père paie l'addition, c'était magnifique")
        ),
        sentences = listOf(
            CompositionSentence(
                order = 1,
                french = "Vendredi dernier, mon père nous a invités à prendre le déjeuner au restaurant.",
                arabic = "الجمعة الماضية، دعانا والدي لتناول طعام الغداء في المطعم.",
                hint = "جملة افتتاحية تذكر الزمان (Vendredi dernier) والدعوة من الوالد."
            ),
            CompositionSentence(
                order = 2,
                french = "Nous sommes allés au restaurant en voiture à quatorze heures.",
                arabic = "ذهبنا إلى المطعم بالسيارة في تمام الساعة الثانية بعد الظهر.",
                hint = "بيان وسيلة المواصلات (en voiture) والتوقيت (à quatorze heures)."
            ),
            CompositionSentence(
                order = 3,
                french = "Le garçon était souriant et il nous a apporté la carte du menu.",
                arabic = "كان النادل مبتسماً ولطيفاً وأحضر لنا قائمة الطعام.",
                hint = "استقبال النادل للأسرة وتقديم المنيو."
            ),
            CompositionSentence(
                order = 4,
                french = "Comme entrée, nous avons pris de la salade verte et des crudités fraîches.",
                arabic = "كمقبلات وفاتح شهية، تناولنا سلطة خضراء وخضروات طازجة.",
                hint = "استخدام التعبير (Comme entrée) مع أدوات التجزئة."
            ),
            CompositionSentence(
                order = 5,
                french = "Comme plat principal, nous avons mangé du délicieux poulet rôti avec du riz.",
                arabic = "كطبق رئيسي، أكلنا دجاجاً مشوياً لذيذاً مع الأرز.",
                hint = "استخدام التعبير (Comme plat principal) مع ذكر الوجبة الرئيسية."
            ),
            CompositionSentence(
                order = 6,
                french = "Comme dessert, nous avons choisi un gâteau au chocolat et des fruits.",
                arabic = "للتحلية، اخترنا تورتة شوكولاتة شهية وفواكه موسمية.",
                hint = "استخدام التعبير (Comme dessert) مع الحلويات."
            ),
            CompositionSentence(
                order = 7,
                french = "À la fin du repas, mon père a demandé l'addition et a payé avec le pourboire.",
                arabic = "في نهاية الوجبة، طلب والدي الفاتورة ودفع الحساب مع إكرامية للنادل.",
                hint = "ختام الجلسة وطلب الفاتورة (l'addition)."
            ),
            CompositionSentence(
                order = 8,
                french = "C'était une journée magnifique et nous avons passé de très bons moments en famille.",
                arabic = "كان يوماً رائعاً للغاية وقضينا أوقاتاً ممتعة جداً بصحبة العائلة.",
                hint = "جملة ختامية تعبر عن المشاعر الإيجابية والانطباع العام."
            )
        ),
        scrambleWords = listOf("Vendredi", "mon père", "nous", "a invités", "au restaurant.", "Le garçon", "a apporté", "le menu.", "Comme entrée,", "de la salade.", "Comme plat principal,", "du poulet.", "Comme dessert,", "un gâteau.", "C'était", "magnifique."),
        fillBlankText = "Vendredi dernier, mon père nous a _____ au restaurant. Nous sommes allés en _____. Le _____ nous a apporté le menu. Comme entrée, nous avons pris de la _____. Comme plat principal, du _____ et du riz. Comme dessert, un gâteau au _____. Mon père a payé l'_____. C'était une fête _____ !",
        fillBlankSolutions = listOf("invités", "voiture", "garçon", "salade", "poulet", "chocolat", "addition", "magnifique")
    )

    val unit2RepasList = listOf("[object Object]", "[object Object]", "[object Object]")

    val unit2RepasItems = listOf(
        FoodDrinkItem(
            id = "repas_pd_m1",
            frenchWithArticle = "du pain",
            frenchBase = "pain",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "خبز",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🥖",
            sampleSentenceFr = "Je mange du pain au petit déjeuner.",
            sampleSentenceAr = "أنا آكل خبزاً في وجبة الإفطار.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m2",
            frenchWithArticle = "du miel",
            frenchBase = "miel",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "عسل",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🍯",
            sampleSentenceFr = "Je mange du pain avec du miel.",
            sampleSentenceAr = "آكل خبزاً مع عسل نقي.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m3",
            frenchWithArticle = "du beurre",
            frenchBase = "beurre",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "زبدة",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🧈",
            sampleSentenceFr = "Je mets du beurre sur ma tartine.",
            sampleSentenceAr = "أضع زبدة على شريحة الخبز.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m4",
            frenchWithArticle = "du croissant",
            frenchBase = "croissant",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "كرواسون",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🥐",
            sampleSentenceFr = "J'aime manger du croissant chaud.",
            sampleSentenceAr = "أحب أكل الكرواسون الساخن.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m5",
            frenchWithArticle = "de la confiture",
            frenchBase = "confiture",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "مربى",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🍓",
            sampleSentenceFr = "Je prends de la confiture de fraises.",
            sampleSentenceAr = "أتناول مربى الفراولة اللذيذة.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m6",
            frenchWithArticle = "de la tartine",
            frenchBase = "tartine",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "شريحة خبز مدهونة بالزبد أو المربى",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🍞",
            sampleSentenceFr = "Je mange de la tartine au beurre et à la confiture.",
            sampleSentenceAr = "آكل شريحة خبز مدهونة بالزبد والمربى.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m7",
            frenchWithArticle = "des œufs",
            frenchBase = "œufs",
            articlePartitive = "des",
            genderAr = "جمع (des)",
            arabic = "بيض",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🥚",
            sampleSentenceFr = "Je mange des œufs au petit déjeuner.",
            sampleSentenceAr = "آكل بيضاً في الإفطار.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m8",
            frenchWithArticle = "des fèves",
            frenchBase = "fèves",
            articlePartitive = "des",
            genderAr = "جمع مؤنث (des)",
            arabic = "فول مدمس",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🫘",
            sampleSentenceFr = "Les Égyptiens aiment manger des fèves le matin.",
            sampleSentenceAr = "يحب المصريون أكل الفول في الصباح.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_m9",
            frenchWithArticle = "du fromage",
            frenchBase = "fromage",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "جبن",
            itemType = "mange",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🧀",
            sampleSentenceFr = "Je mange du pain avec du fromage blanc.",
            sampleSentenceAr = "آكل خبزاً مع جبن أبيض.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b1",
            frenchWithArticle = "du lait",
            frenchBase = "lait",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "حليب / لبن",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🥛",
            sampleSentenceFr = "Mon petit frère boit du lait chaque matin.",
            sampleSentenceAr = "يشرب أخي الصغير حليباً كل صباح.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b2",
            frenchWithArticle = "du jus",
            frenchBase = "jus",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "عصير",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🧃",
            sampleSentenceFr = "Je bois du jus d'orange frais.",
            sampleSentenceAr = "أشرب عصير برتقال طازجاً.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b3",
            frenchWithArticle = "du thé au lait",
            frenchBase = "thé au lait",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "شاي بالحليب",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "☕",
            sampleSentenceFr = "Mon père préfère boire du thé au lait.",
            sampleSentenceAr = "يفضل والدي شرب الشاي بالحليب.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b4",
            frenchWithArticle = "de la limonade",
            frenchBase = "limonade",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "عصير ليمون / ليمونادة",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "🍋",
            sampleSentenceFr = "Quand il fait chaud, je bois de la limonade.",
            sampleSentenceAr = "عندما يكون الجو حاراً، أشرب عصير ليمون.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b5",
            frenchWithArticle = "de l'eau",
            frenchBase = "eau",
            articlePartitive = "de l'",
            genderAr = "مفرد يبدأ بمتحرك (de l')",
            arabic = "ماء",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "💧",
            sampleSentenceFr = "Il faut boire beaucoup d'eau.",
            sampleSentenceAr = "يجب شرب الكثير من الماء يومياً.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_pd_b6",
            frenchWithArticle = "du café",
            frenchBase = "café",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "قهوة",
            itemType = "bois",
            mealType = MealType.PETIT_DEJEUNER,
            courseCategory = "",
            emoji = "☕",
            sampleSentenceFr = "Ma mère prend du café sans sucre.",
            sampleSentenceAr = "تتناول أمي القهوة بدون سكر.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_1",
            frenchWithArticle = "de la salade",
            frenchBase = "salade",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "سلطة خضراء",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme entrée",
            emoji = "🥗",
            sampleSentenceFr = "Comme entrée, je mange de la salade verte.",
            sampleSentenceAr = "كمقبلات، آكل سلطة خضراء طازجة.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_2",
            frenchWithArticle = "du riz",
            frenchBase = "riz",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "أرز",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme plat principal",
            emoji = "🍚",
            sampleSentenceFr = "Comme plat principal, je mange du riz et de la viande.",
            sampleSentenceAr = "كطبق رئيسي، آكل أرزاً ولحماً.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_3",
            frenchWithArticle = "du poulet",
            frenchBase = "poulet",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "دجاج",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme plat principal",
            emoji = "🍗",
            sampleSentenceFr = "Mon frère préfère manger du poulet avec des frites.",
            sampleSentenceAr = "يفضل أخي أكل الدجاج مع البطاطس المقلية.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_4",
            frenchWithArticle = "du poisson",
            frenchBase = "poisson",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "سمك",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme plat principal",
            emoji = "🐟",
            sampleSentenceFr = "Le vendredi, nous mangeons du poisson au restaurant.",
            sampleSentenceAr = "يوم الجمعة، نأكل سمكاً في المطعم.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_5",
            frenchWithArticle = "de la viande",
            frenchBase = "viande",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "لحم",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme plat principal",
            emoji = "🥩",
            sampleSentenceFr = "Mon père prend de la viande bien cuite.",
            sampleSentenceAr = "يأخذ والدي لحماً ناضجاً جيداً.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_dej_6",
            frenchWithArticle = "des frites",
            frenchBase = "frites",
            articlePartitive = "des",
            genderAr = "جمع مؤنث (des)",
            arabic = "بطاطس مقلية",
            itemType = "mange",
            mealType = MealType.DEJEUNER,
            courseCategory = "Comme plat principal",
            emoji = "🍟",
            sampleSentenceFr = "Comme plat principal, je mange du poulet avec des frites.",
            sampleSentenceAr = "كطبق رئيسي، آكل دجاجاً مع بطاطس مقلية.",
            pageReference = "p. 41"
        ),
        FoodDrinkItem(
            id = "repas_din_1",
            frenchWithArticle = "du yaourt",
            frenchBase = "yaourt",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "زبادي",
            itemType = "mange",
            mealType = MealType.DINER,
            courseCategory = "",
            emoji = "🥣",
            sampleSentenceFr = "Le soir, je prends du yaourt avant de dormir.",
            sampleSentenceAr = "في المساء، أتناول زبادي قبل النوم.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_din_2",
            frenchWithArticle = "des fruits",
            frenchBase = "fruits",
            articlePartitive = "des",
            genderAr = "جمع مذكر (des)",
            arabic = "فواكه",
            itemType = "mange",
            mealType = MealType.DINER,
            courseCategory = "",
            emoji = "🍇",
            sampleSentenceFr = "Pour le dîner, je prends des fruits frais.",
            sampleSentenceAr = "لوجبة العشاء، أتناول فواكه طازجة.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_din_3",
            frenchWithArticle = "du gâteau",
            frenchBase = "gâteau",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "كيك / جاتوه",
            itemType = "mange",
            mealType = MealType.DINER,
            courseCategory = "",
            emoji = "🍰",
            sampleSentenceFr = "Au dîner, je prends une part du gâteau.",
            sampleSentenceAr = "في العشاء، أتناول قطعة جاتوه.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_des_1",
            frenchWithArticle = "du gâteau",
            frenchBase = "gâteau",
            articlePartitive = "du",
            genderAr = "مذكر (du)",
            arabic = "جاتوه / كيك",
            itemType = "dessert",
            mealType = MealType.DESSERTS,
            courseCategory = "Comme dessert",
            emoji = "🍰",
            sampleSentenceFr = "Comme dessert, je prends du gâteau au chocolat.",
            sampleSentenceAr = "كتحلية، أتناول كعكة شوكولاتة.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_des_2",
            frenchWithArticle = "de la tarte",
            frenchBase = "tarte",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "تارت / فطيرة حلوة",
            itemType = "dessert",
            mealType = MealType.DESSERTS,
            courseCategory = "Comme dessert",
            emoji = "🥧",
            sampleSentenceFr = "Comme dessert, ma sœur prend de la tarte aux fraises.",
            sampleSentenceAr = "كتحلية، تأخذ أختي تارت الفراولة.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_des_3",
            frenchWithArticle = "de la glace",
            frenchBase = "glace",
            articlePartitive = "de la",
            genderAr = "مؤنث (de la)",
            arabic = "آيس كريم / مثلجات",
            itemType = "dessert",
            mealType = MealType.DESSERTS,
            courseCategory = "Comme dessert",
            emoji = "🍨",
            sampleSentenceFr = "En été, nous mangeons de la glace à la vanille.",
            sampleSentenceAr = "في الصيف، نأكل آيس كريم فانيليا.",
            pageReference = "p. 42"
        ),
        FoodDrinkItem(
            id = "repas_des_4",
            frenchWithArticle = "des fruits",
            frenchBase = "fruits",
            articlePartitive = "des",
            genderAr = "جمع مذكر (des)",
            arabic = "فواكه مشكلة",
            itemType = "dessert",
            mealType = MealType.DESSERTS,
            courseCategory = "Comme dessert",
            emoji = "🍓",
            sampleSentenceFr = "Comme dessert, je préfère manger des fruits de saison.",
            sampleSentenceAr = "كتحلية، أفضل أكل فواكه الموسم.",
            pageReference = "p. 42"
        )
    )

    val unit2RepasQuizList = listOf(
        RepasQuizItem(
            id = "rq_1",
            questionFr = "Combien de repas y a-t-il par jour ?",
            questionAr = "كم عدد الوجبات المقررة في اليوم الواحد وفقاً لكتاب المعهد؟",
            options = listOf("Trois repas", "Un seul repas", "Quatre repas", "Deux repas"),
            correctIndex = 0,
            explanationAr = "نص الكتيّب ص 41: « Il y a trois repas par jour : le petit déjeuner, le déjeuner, le dîner ».",
            tip = "احفظ هذا السؤال بنصه فهو سؤال امتحانات متكرر!"
        ),
        RepasQuizItem(
            id = "rq_2",
            questionFr = "Au petit déjeuner, pour boire je prends :",
            questionAr = "في وجبة الإفطار، للشرب أتناول :",
            options = listOf("des œufs", "de la confiture", "du thé au lait", "du fromage"),
            correctIndex = 2,
            explanationAr = "الشاي بالحليب (du thé au lait) من المشروبات (Je bois)، بينما الجبن والمربى والبيض أطعمة تؤكل (Je mange).",
            tip = "انتبه لفعل الجملة: boire = يشرب، manger = يأكل."
        ),
        RepasQuizItem(
            id = "rq_3",
            questionFr = "Comme entrée au déjeuner, nous mangeons :",
            questionAr = "كمقبلات في وجبة الغداء، نأكل :",
            options = listOf("du poisson", "de la salade", "des frites", "de la tarte"),
            correctIndex = 1,
            explanationAr = "وفقاً لص 41: كـ entrée مقبلات نأكل (de la salade). والسمك والبطاطس طبق رئيسي (plat principal).",
            tip = "Comme entrée = كمقبلات في بداية الوجبة."
        ),
        RepasQuizItem(
            id = "rq_4",
            questionFr = "Comme plat principal, mon père commande :",
            questionAr = "كطبق رئيسي، يطلب والدي :",
            options = listOf("de la confiture", "de la glace", "du thé", "du poulet avec du riz"),
            correctIndex = 3,
            explanationAr = "الطبق الرئيسي (plat principal) ص 41 يشمل: du riz, du poulet, du poisson, de la viande, des frites.",
            tip = "plat principal = الطبق الرئيسي الأساسي للغداء."
        ),
        RepasQuizItem(
            id = "rq_5",
            questionFr = "Au dîner, je prends du yaourt, des fruits ou comme :",
            questionAr = "في العشاء، أتناول زبادي وفواكه أو مثل :",
            options = listOf("le petit déjeuner", "la gare", "le train", "le stade"),
            correctIndex = 0,
            explanationAr = "نص ص 42: « 3- le dîner: Je prends du yaourt, des fruits, du gâteau. Ou comme le petit déjeuner. »",
            tip = "وجبة العشاء تكون خفيفة أو مثل وجبة الإفطار."
        ),
        RepasQuizItem(
            id = "rq_6",
            questionFr = "Comme dessert, Suzanne adore prendre :",
            questionAr = "كحلوى بعد الأكل، تعشق سوزان تناول :",
            options = listOf("du beurre", "de la glace", "des fèves", "du pain"),
            correctIndex = 1,
            explanationAr = "الحلويات (Les desserts) المقررة ص 42 هي: du gâteau, de la tarte, de la glace, des fruits.",
            tip = "de la glace = مثلجات / آيس كريم (مفرد مؤنث)."
        ),
        RepasQuizItem(
            id = "rq_7",
            questionFr = "Je bois toujours ..... eau fraîche à midi.",
            questionAr = "أنا أشرب دائماً ..... ماء عذباً ظهراً.",
            options = listOf("de l'", "des", "de la", "du"),
            correctIndex = 0,
            explanationAr = "كلمة eau تبدأ بحرف متحرك (e)، فتأخذ أداة التجزئة (de l').",
            tip = "الاسم المفرد الذي يبدأ بحرف متحرك يأخذ دائماً (de l')."
        ),
        RepasQuizItem(
            id = "rq_8",
            questionFr = "Le matin, les Égyptiens mangent souvent ..... fèves.",
            questionAr = "في الصباح، يأكل المصريون غالباً ..... فول.",
            options = listOf("de la", "de l'", "du", "des"),
            correctIndex = 3,
            explanationAr = "كلمة fèves جمع تنتهي بـ s، لذا تأخذ أداة التجزئة للجمع (des).",
            tip = "des fèves = الفول (جمع)."
        ),
        RepasQuizItem(
            id = "rq_9",
            questionFr = "Je mange ..... tartine beurrée pour le petit déjeuner.",
            questionAr = "آكل ..... شريحة خبز بالزبدة في الإفطار.",
            options = listOf("des", "du", "de la", "de l'"),
            correctIndex = 2,
            explanationAr = "كلمة tartine مفرد مؤنث في الفرنسية، لذا تأخذ أداة التجزئة (de la).",
            tip = "de la tartine = شريحة خبز مدهونة (مؤنث)."
        ),
        RepasQuizItem(
            id = "rq_10",
            questionFr = "Le soir, mon frère prend ..... yaourt nature.",
            questionAr = "في المساء، يتناول أخي ..... زبادي طبيعي.",
            options = listOf("de l'", "de la", "des", "du"),
            correctIndex = 3,
            explanationAr = "انتبه جيداً: كلمة yaourt مفرد مذكر في الفرنسية ولا تعامل كحرف متحرك مع التجزئة بل تأخذ (du yaourt).",
            tip = "قاعدة ذهبية: du yaourt (مذكر بأداة du)."
        )
    )
}
