package com.example.data

/**
 * بيانات كتيّب منهج Bienvenu 2 للصف الثاني الإعدادي
 * ص 25 : Les lieux et Les personnages
 * ص 26 : التمارين الرسمية الثلاثة (Où vas-tu pour...? / Qui parle? / Qui peut faire ce travail:)
 */
object BookletLieuxEtExercicesData {

    // ---------------------------------------------------------------------------------------------
    // كتيّب ص 25 : قائمة الشخصيات الكاملة (Les personnages - 23 شخصية)
    // ---------------------------------------------------------------------------------------------
    val personnagesList = listOf(
        BookletPersonnageItem(
            id = "perso_1",
            french = "un client",
            arabic = "زبون / عميل",
            gender = "m",
            typicalPlaces = listOf("au restaurant", "au café", "au magasin"),
            emoji = "🛒",
            exampleSentenceFr = "Le client demande le menu au garçon.",
            exampleSentenceAr = "يطلب الزبون قائمة الطعام من النادل."
        ),
        BookletPersonnageItem(
            id = "perso_2",
            french = "un vendeur",
            arabic = "بائع",
            gender = "m",
            typicalPlaces = listOf("au magasin", "au marché"),
            emoji = "🏬",
            exampleSentenceFr = "Le vendeur aide les clients dans le magasin.",
            exampleSentenceAr = "يساعد البائع الزبائن في المتجر."
        ),
        BookletPersonnageItem(
            id = "perso_3",
            french = "un garçon",
            arabic = "نادل / جرسون",
            gender = "m",
            typicalPlaces = listOf("au restaurant", "au café"),
            emoji = "🤵",
            exampleSentenceFr = "Le garçon apporte l'addition et les plats.",
            exampleSentenceAr = "يحضر النادل الحساب والأطباق."
        ),
        BookletPersonnageItem(
            id = "perso_4",
            french = "un guide",
            arabic = "مرشد سياحي",
            gender = "m",
            typicalPlaces = listOf("au musée", "aux pyramides"),
            emoji = "🧭",
            exampleSentenceFr = "Le guide explique l'histoire des monuments aux touristes.",
            exampleSentenceAr = "يشرح المرشد تاريخ الآثار للسياح."
        ),
        BookletPersonnageItem(
            id = "perso_5",
            french = "un élève",
            arabic = "تلميذ",
            gender = "m",
            typicalPlaces = listOf("à l'école", "en classe"),
            emoji = "🎒",
            exampleSentenceFr = "L'élève écoute attentivement son professeur en classe.",
            exampleSentenceAr = "ينصت التلميذ بانتباه لمعلمه في الفصل."
        ),
        BookletPersonnageItem(
            id = "perso_6",
            french = "un professeur",
            arabic = "معلم / أستاذ",
            gender = "m",
            typicalPlaces = listOf("à l'école", "en classe"),
            emoji = "👨‍🏫",
            exampleSentenceFr = "Le professeur explique la leçon au tableau.",
            exampleSentenceAr = "يشرح المعلم الدرس على السبورة."
        ),
        BookletPersonnageItem(
            id = "perso_7",
            french = "un journaliste",
            arabic = "صحفي",
            gender = "m",
            typicalPlaces = listOf("au journal", "au stade"),
            emoji = "📰",
            exampleSentenceFr = "Le journaliste écrit des articles pour le journal.",
            exampleSentenceAr = "يكتب الصحفي المقالات للجريدة."
        ),
        BookletPersonnageItem(
            id = "perso_8",
            french = "un guichetier",
            arabic = "موظف التذاكر (شباك التذاكر)",
            gender = "m",
            typicalPlaces = listOf("au guichet", "à la gare", "au cinéma"),
            emoji = "🎟️",
            exampleSentenceFr = "Le guichetier vend les billets aux voyageurs.",
            exampleSentenceAr = "يبيع موظف الشباك التذاكر للمسافرين."
        ),
        BookletPersonnageItem(
            id = "perso_9",
            french = "un réceptionniste",
            arabic = "موظف الاستقبال",
            gender = "m",
            typicalPlaces = listOf("à l'hôtel"),
            emoji = "🏨",
            exampleSentenceFr = "Le réceptionniste donne la clé de la chambre.",
            exampleSentenceAr = "يعطي موظف الاستقبال مفتاح الغرفة."
        ),
        BookletPersonnageItem(
            id = "perso_10",
            french = "un médecin",
            arabic = "طبيب",
            gender = "m",
            typicalPlaces = listOf("à l'hôpital", "à la clinique"),
            emoji = "👨‍⚕️",
            exampleSentenceFr = "Le médecin examine les malades et prescrit le traitement.",
            exampleSentenceAr = "يفحص الطبيب المرضى ويصف العلاج."
        ),
        BookletPersonnageItem(
            id = "perso_11",
            french = "une infirmière",
            arabic = "ممرضة",
            gender = "f",
            typicalPlaces = listOf("à l'hôpital", "à la clinique"),
            emoji = "👩‍⚕️",
            exampleSentenceFr = "L'infirmière soigne les malades avec douceur.",
            exampleSentenceAr = "تعتني الممرضة بالمرضى بلطف."
        ),
        BookletPersonnageItem(
            id = "perso_12",
            french = "un malade",
            arabic = "مريض",
            gender = "m",
            typicalPlaces = listOf("à l'hôpital", "à la pharmacie"),
            emoji = "🤒",
            exampleSentenceFr = "Le malade va chez le médecin pour se soigner.",
            exampleSentenceAr = "يذهب المريض إلى الطبيب ليتعالج."
        ),
        BookletPersonnageItem(
            id = "perso_13",
            french = "un pilote",
            arabic = "طيار",
            gender = "m",
            typicalPlaces = listOf("à l'aéroport", "dans l'avion"),
            emoji = "👨‍✈️",
            exampleSentenceFr = "Le pilote conduit l'avion vers Paris.",
            exampleSentenceAr = "يقود الطيار الطائرة نحو باريس."
        ),
        BookletPersonnageItem(
            id = "perso_14",
            french = "une hôtesse",
            arabic = "مضيفة طيران",
            gender = "f",
            typicalPlaces = listOf("à l'aéroport", "dans l'avion"),
            emoji = "👩‍✈️",
            exampleSentenceFr = "L'hôtesse accueille les passagers à bord de l'avion.",
            exampleSentenceAr = "ترحب المضيفة بالركاب على متن الطائرة."
        ),
        BookletPersonnageItem(
            id = "perso_15",
            french = "un pharmacien",
            arabic = "صيدلي",
            gender = "m",
            typicalPlaces = listOf("à la pharmacie"),
            emoji = "💊",
            exampleSentenceFr = "Le pharmacien vend les médicaments prescrits.",
            exampleSentenceAr = "يبيع الصيدلي الأدوية الموصوفة."
        ),
        BookletPersonnageItem(
            id = "perso_16",
            french = "un père",
            arabic = "أب",
            gender = "m",
            typicalPlaces = listOf("à la maison"),
            emoji = "👨",
            exampleSentenceFr = "Le père conseille ses enfants de bien étudier.",
            exampleSentenceAr = "ينصح الأب أولاده بالمذاكرة جيداً."
        ),
        BookletPersonnageItem(
            id = "perso_17",
            french = "une mère",
            arabic = "أم (une mère)",
            gender = "f",
            typicalPlaces = listOf("à la maison", "au marché"),
            emoji = "👩",
            exampleSentenceFr = "La mère prépare un délicieux dîner pour la famille.",
            exampleSentenceAr = "تجهز الأم عشاءً لذيذاً للأسرة."
        ),
        BookletPersonnageItem(
            id = "perso_18",
            french = "une fille / un fils",
            arabic = "ابنة / ابن",
            gender = "m/f",
            typicalPlaces = listOf("à la maison", "à l'école"),
            emoji = "👧👦",
            exampleSentenceFr = "Le fils fait ses devoirs avant d'aller au lit.",
            exampleSentenceAr = "يؤدي الابن واجباته قبل الذهاب إلى الفراش."
        ),
        BookletPersonnageItem(
            id = "perso_19",
            french = "un touriste",
            arabic = "سائح",
            gender = "m",
            typicalPlaces = listOf("au musée", "à l'hôtel", "aux pyramides"),
            emoji = "📸",
            exampleSentenceFr = "Le touriste prend des photos des monuments égyptiens.",
            exampleSentenceAr = "يلتقط السائح صوراً للمعالم المصرية."
        ),
        BookletPersonnageItem(
            id = "perso_20",
            french = "un passant",
            arabic = "عابر سبيل / أحد المارة",
            gender = "m",
            typicalPlaces = listOf("dans la rue"),
            emoji = "🚶",
            exampleSentenceFr = "Je demande mon chemin à un passant dans la rue.",
            exampleSentenceAr = "أسأل عابر سبيل في الشارع عن طريقي."
        ),
        BookletPersonnageItem(
            id = "perso_21",
            french = "un mécanicien",
            arabic = "ميكانيكي",
            gender = "m",
            typicalPlaces = listOf("au garage"),
            emoji = "🔧",
            exampleSentenceFr = "Le mécanicien répare la voiture en panne.",
            exampleSentenceAr = "يصلح الميكانيكي السيارة المعطلة."
        ),
        BookletPersonnageItem(
            id = "perso_22",
            french = "un voyageur",
            arabic = "مسافر",
            gender = "m",
            typicalPlaces = listOf("à la gare", "à l'aéroport"),
            emoji = "🧳",
            exampleSentenceFr = "Le voyageur attend son train à la gare.",
            exampleSentenceAr = "ينتظر المسافر قطاره في المحطة."
        ),
        BookletPersonnageItem(
            id = "perso_23",
            french = "un passager",
            arabic = "راكب",
            gender = "m",
            typicalPlaces = listOf("dans l'avion", "dans le train", "dans le bus"),
            emoji = "💺",
            exampleSentenceFr = "Le passager attache sa ceinture de sécurité.",
            exampleSentenceAr = "يربط الراكب حزام الأمان."
        )
    )

    // ---------------------------------------------------------------------------------------------
    // كتيّب ص 25 : قائمة الأماكن الكاملة مقسمة حسب حروف الجر (Les lieux - 22 مكان)
    // ---------------------------------------------------------------------------------------------
    val lieuxList = listOf(
        // Preposition "au" (المفرد المذكر: à + le = au)
        BookletLieuItem("lieu_1", "au restaurant", "restaurant", "au", "في المطعم", "🍽️", listOf("un client", "un garçon"), "مفرد مذكر يبدأ بساكن ➔ يأخذ au"),
        BookletLieuItem("lieu_2", "au café", "café", "au", "في المقهى", "☕", listOf("un client", "un garçon"), "مفرد مذكر يبدأ بساكن ➔ يأخذ au"),
        BookletLieuItem("lieu_3", "au magasin", "magasin", "au", "في المتجر / المحل", "🛍️", listOf("un client", "un vendeur"), "مفرد مذكر ➔ يأخذ au"),
        BookletLieuItem("lieu_4", "au club", "club", "au", "في النادي", "🎾", listOf("un sportif", "des amis"), "مفرد مذكر ➔ يأخذ au (لممارسة الرياضة)"),
        BookletLieuItem("lieu_5", "au musée", "musée", "au", "في المتحف", "🏛️", listOf("un guide", "un touriste"), "مفرد مذكر ➔ يأخذ au (لرؤية الآثار)"),
        BookletLieuItem("lieu_6", "au garage", "garage", "au", "في الورشة / الجراج", "🚗", listOf("un mécanicien", "un chauffeur"), "مفرد مذكر ➔ يأخذ au (لتصليح السيارة)"),
        BookletLieuItem("lieu_7", "au stade", "stade", "au", "في الاستاد / الملعب", "⚽", listOf("un journaliste", "des supporters"), "مفرد مذكر ➔ يأخذ au (لمشاهدة مباراة)"),
        BookletLieuItem("lieu_8", "au cinéma", "cinéma", "au", "في السينما", "🎬", listOf("un spectateur", "un guichetier"), "مفرد مذكر ➔ يأخذ au (لرؤية فيلم)"),
        BookletLieuItem("lieu_9", "au journal", "journal", "au", "في مقر الجريدة / الصحيفة", "🗞️", listOf("un journaliste"), "مفرد مذكر ➔ يأخذ au"),
        BookletLieuItem("lieu_10", "au guichet", "guichet", "au", "عند شباك التذاكر", "🎫", listOf("un guichetier", "un voyageur"), "مفرد مذكر ➔ يأخذ au (لشراء تذكرة)"),
        BookletLieuItem("lieu_11", "au marché", "marché", "au", "في السوق", "🥬", listOf("un vendeur", "une mère"), "مفرد مذكر ➔ يأخذ au (لشراء الخضار)"),

        // Preposition "à l'" (مفرد يبدأ بمتحرك أو h الصامتة)
        BookletLieuItem("lieu_12", "à l'école", "école", "à l'", "في المدرسة", "🏫", listOf("un élève", "un professeur"), "يبدأ بحرف متحرك é ➔ يأخذ à l'"),
        BookletLieuItem("lieu_13", "à l'hôtel", "hôtel", "à l'", "في الفندق", "🏨", listOf("un réceptionniste", "un touriste"), "يبدأ بحرف h صامتة ➔ يأخذ à l'"),
        BookletLieuItem("lieu_14", "à l'hôpital", "hôpital", "à l'", "في المستشفى", "🏥", listOf("un médecin", "une infirmière", "un malade"), "يبدأ بحرف h صامتة ➔ يأخذ à l'"),
        BookletLieuItem("lieu_15", "à l'aéroport", "aéroport", "à l'", "في المطار", "✈️", listOf("un pilote", "une hôtesse", "un voyageur"), "يبدأ بحرف متحرك a ➔ يأخذ à l'"),

        // Preposition "à la" (المفرد المؤنث المبدوء بساكن)
        BookletLieuItem("lieu_16", "à la gare", "gare", "à la", "في محطة القطار", "🚆", listOf("un guichetier", "un voyageur"), "مفرد مؤنث ➔ يأخذ à la"),
        BookletLieuItem("lieu_17", "à la pharmacie", "pharmacie", "à la", "في الصيدلية", "💊", listOf("un pharmacien", "un client"), "مفرد مؤنث ➔ يأخذ à la (لشراء دواء)"),
        BookletLieuItem("lieu_18", "à la poste", "poste", "à la", "في مكتب البريد", "📮", listOf("un employé", "un client"), "مفرد مؤنث ➔ يأخذ à la (لإرسال خطاب/طرد)"),
        BookletLieuItem("lieu_19", "à la maison", "maison", "à la", "في المنزل / البيت", "🏡", listOf("un père", "une mère", "un fils"), "مفرد مؤنث ➔ يأخذ à la"),
        BookletLieuItem("lieu_20", "à la piscine", "piscine", "à la", "في حمام السباحة", "🏊", listOf("un nageur", "des élèves"), "مفرد مؤنث ➔ يأخذ à la (للسباحة)"),

        // Preposition "dans la"
        BookletLieuItem("lieu_21", "dans la rue", "rue", "dans la", "في الشارع", "🛣️", listOf("un passant", "des piétons"), "تأخذ حرف الجر dans la"),

        // Preposition "en"
        BookletLieuItem("lieu_22", "en classe", "classe", "en", "في الفصل", "👨‍🎓", listOf("un élève", "un professeur"), "مكان مؤنث بدون أداة ➔ يأخذ en classe")
    )

    // ---------------------------------------------------------------------------------------------
    // كتيّب ص 26 : التمرين الأول (1. Où vas- tu pour ..........?)
    // ---------------------------------------------------------------------------------------------
    val ouVasTuItems = listOf(
        BookletOuVasTuItem(
            id = "ou_vas_tu_1",
            number = 1,
            activityFr = "Voir un film.",
            activityAr = "لرؤية / مشاهدة فيلم.",
            expectedAnswerFr = "Au cinéma.",
            alternativeAnswersFr = listOf("Je vais au cinéma.", "au cinéma"),
            answerAr = "إلى السينما (Au cinéma).",
            options = listOf("Au stade.", "Au restaurant.", "Au cinéma."),
            explanationAr = "نذهب إلى السينما (Au cinéma) لمشاهدة الأفلام. cinéma كلمة مفرد مذكر مسبوقة بـ au."
        ),
        BookletOuVasTuItem(
            id = "ou_vas_tu_2",
            number = 2,
            activityFr = "consulter le médecin.",
            activityAr = "لاستشارة الطبيب أو الكشف الطبي.",
            expectedAnswerFr = "À l'hôpital.",
            alternativeAnswersFr = listOf("Chez le médecin.", "À la clinique.", "à l'hôpital"),
            answerAr = "إلى المستشفى (À l'hôpital) أو عند الطبيب (Chez le médecin).",
            options = listOf("À la gare.", "À l'hôpital.", "Au club."),
            explanationAr = "نذهب إلى المستشفى (À l'hôpital) أو العيادة أو عند الطبيب (Chez le médecin) للكشف والاستشارة."
        ),
        BookletOuVasTuItem(
            id = "ou_vas_tu_3",
            number = 3,
            activityFr = "voir un match.",
            activityAr = "لمشاهدة مباراة رياضية.",
            expectedAnswerFr = "Au stade.",
            alternativeAnswersFr = listOf("Je vais au stade.", "au stade"),
            answerAr = "إلى الاستاد / الملعب (Au stade).",
            options = listOf("À la poste.", "Au stade.", "Au cinéma."),
            explanationAr = "نذهب إلى الاستاد (Au stade) لمشاهدة مباريات كرة القدم والرياضات المختلفة. stade مذكر يأخذ au."
        ),
        BookletOuVasTuItem(
            id = "ou_vas_tu_4",
            number = 4,
            activityFr = "Apprendre les leçons.",
            activityAr = "لتعلّم الدروس واستذكارها.",
            expectedAnswerFr = "À l'école.",
            alternativeAnswersFr = listOf("En classe.", "Je vais à l'école.", "à l'école"),
            answerAr = "إلى المدرسة (À l'école) أو في الفصل (En classe).",
            options = listOf("À l'école.", "Au musée.", "Au garage."),
            explanationAr = "المكان النموذجي لتلقي وتعلم الدروس هو المدرسة (À l'école) أو قاعة الفصل (En classe)."
        ),
        BookletOuVasTuItem(
            id = "ou_vas_tu_5",
            number = 5,
            activityFr = "Faire du sport.",
            activityAr = "لممارسة الرياضة والأنشطة البدنية.",
            expectedAnswerFr = "Au club.",
            alternativeAnswersFr = listOf("Au stade.", "À la salle de sport.", "au club"),
            answerAr = "إلى النادي (Au club) أو الاستاد (Au stade).",
            options = listOf("À l'aéroport.", "Au club.", "À la pharmacie."),
            explanationAr = "نذهب إلى النادي (Au club) أو الاستاد (Au stade) لممارسة الأنشطة الرياضية والتمارين."
        )
    )

    // ---------------------------------------------------------------------------------------------
    // كتيّب ص 26 : التمرين الثاني (2. Qui parle ?)
    // ---------------------------------------------------------------------------------------------
    val quiParleItems = listOf(
        BookletQuiParleItem(
            id = "qui_parle_1",
            number = 1,
            quoteFr = "« Il faut bien étudier. »",
            quoteAr = "«يجب المذاكرة والاجتهاد جيداً.»",
            speakerFr = "Le professeur",
            alternativeSpeakersFr = listOf("Le père", "La mère"),
            speakerAr = "المعلم (Le professeur) أو الأب/الأم",
            options = listOf("Le guichetier", "Le professeur", "Le mécanicien"),
            situationContextAr = "نصيحة يوجهها المعلم في المدرسة لتلاميذه، أو يوجهها الوالد في المنزل لأبنائه للحث على التفوق."
        ),
        BookletQuiParleItem(
            id = "qui_parle_2",
            number = 2,
            quoteFr = "« Fais le devoir et va au lit. »",
            quoteAr = "«قم بعمل واجبك المدرسي واذهب إلى الفراش للنوم.»",
            speakerFr = "La mère",
            alternativeSpeakersFr = listOf("Le père", "Les parents"),
            speakerAr = "الأم (La mère) أو الأب (Le père)",
            options = listOf("La mère", "Le pilote", "Le pharmacien"),
            situationContextAr = "تعليمات أسرية موجهة من الأم أو الأب للطفل في المنزل لتنظيم وقت الواجب والنوم."
        ),
        BookletQuiParleItem(
            id = "qui_parle_3",
            number = 3,
            quoteFr = "« Va au tableau. »",
            quoteAr = "«اذهب إلى السبورة.»",
            speakerFr = "Le professeur",
            alternativeSpeakersFr = listOf("L'enseignant", "Le maître"),
            speakerAr = "المعلم / الأستاذ (Le professeur)",
            options = listOf("Le chauffeur", "Le professeur", "L'infirmière"),
            situationContextAr = "طلب حصري داخل الفصل الدراسي من المعلم لتلميذه للكتابة أو الحل على السبورة (le tableau)."
        ),
        BookletQuiParleItem(
            id = "qui_parle_4",
            number = 4,
            quoteFr = "« Désolé l'hôtel est complet. »",
            quoteAr = "«عذراً، الفندق ممتلئ بالكامل (لا توجد غرف شاغرة).»",
            speakerFr = "Le réceptionniste",
            alternativeSpeakersFr = listOf("L'employé de l'hôtel"),
            speakerAr = "موظف الاستقبال (Le réceptionniste)",
            options = listOf("Le guide", "Le vendeur", "Le réceptionniste"),
            situationContextAr = "عبارة شائعة يقولها موظف الاستقبال في الفندق (à l'hôtel) لنزيل أو سائح عند عدم وجود غرف شاغرة."
        ),
        BookletQuiParleItem(
            id = "qui_parle_5",
            number = 5,
            quoteFr = "« Docteur! J'ai mal à l'estomac. »",
            quoteAr = "«يا دكتور! لدي ألم شديد في معدتي.»",
            speakerFr = "Le malade",
            alternativeSpeakersFr = listOf("Un patient"),
            speakerAr = "المريض (Le malade) أو المستشير",
            options = listOf("Le journaliste", "Le malade", "Le touriste"),
            situationContextAr = "شكوى يصف بها المريض موضع الألم للطبيب في المستشفى أو العيادة."
        )
    )

    // ---------------------------------------------------------------------------------------------
    // كتيّب ص 26 : التمرين الثالث (3. Qui peut faire ce travail:)
    // ---------------------------------------------------------------------------------------------
    val quiFaitCeTravailItems = listOf(
        BookletQuiFaitCeTravailItem(
            id = "qui_travail_1",
            number = 1,
            actionFr = "Conduire l'avion.",
            actionAr = "قيادة الطائرة في الجو.",
            professionFr = "Le pilote",
            alternativeProfessionsFr = listOf("Un pilote"),
            professionAr = "الطيار (Le pilote)",
            options = listOf("Le professeur", "Le mécanicien", "Le pilote"),
            descriptionAr = "الطيار (Le pilote) هو المسؤول عن قيادة الطائرة ونقل الركاب جواً في المطار (à l'aéroport)."
        ),
        BookletQuiFaitCeTravailItem(
            id = "qui_travail_2",
            number = 2,
            actionFr = "Conduire la voiture.",
            actionAr = "قيادة السيارة على الطرقات.",
            professionFr = "Le chauffeur",
            alternativeProfessionsFr = listOf("Le conducteur", "Un chauffeur"),
            professionAr = "السائق (Le chauffeur / Le conducteur)",
            options = listOf("Le guichetier", "Le médecin", "Le chauffeur"),
            descriptionAr = "السائق (Le chauffeur / Le conducteur) هو من يتولى قيادة السيارات والحافلات."
        ),
        BookletQuiFaitCeTravailItem(
            id = "qui_travail_3",
            number = 3,
            actionFr = "réparer la voiture.",
            actionAr = "إصلاح وصيانة السيارات المعطلة.",
            professionFr = "Le mécanicien",
            alternativeProfessionsFr = listOf("Un mécanicien"),
            professionAr = "الميكانيكي (Le mécanicien)",
            options = listOf("Le garçon", "Le réceptionniste", "Le mécanicien"),
            descriptionAr = "الميكانيكي (Le mécanicien) يقوم بصيانة وإصلاح أعطال السيارات داخل الورشة (au garage)."
        ),
        BookletQuiFaitCeTravailItem(
            id = "qui_travail_4",
            number = 4,
            actionFr = "expliquer la leçon.",
            actionAr = "شرح وتوضيح الدروس.",
            professionFr = "Le professeur",
            alternativeProfessionsFr = listOf("L'enseignant", "Le maître"),
            professionAr = "المعلم / الأستاذ (Le professeur)",
            options = listOf("Le touriste", "L'hôtesse de l'air", "Le professeur"),
            descriptionAr = "المعلم (Le professeur) يشرح الدروس للطلاب داخل المدرسة (à l'école) وفي الفصل (en classe)."
        ),
        BookletQuiFaitCeTravailItem(
            id = "qui_travail_5",
            number = 5,
            actionFr = "Examiner les malade.",
            actionAr = "فحص والكشف الطبي على المرضى.",
            professionFr = "Le médecin",
            alternativeProfessionsFr = listOf("Le docteur", "Un médecin"),
            professionAr = "الطبيب (Le médecin / Le docteur)",
            options = listOf("Le journaliste", "Le médecin", "Le vendeur"),
            descriptionAr = "الطبيب (Le médecin) يفحص المرضى ويصف لهم الدواء المناسب داخل المستشفى (à l'hôpital)."
        )
    )
}
