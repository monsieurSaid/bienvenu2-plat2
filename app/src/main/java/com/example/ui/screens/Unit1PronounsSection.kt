package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.*
import com.example.ui.components.ArabicTranslationBanner
import com.example.ui.components.AudioPlayButton
import com.example.ui.components.TranslateIconButton
import com.example.ui.theme.*

/**
 * قسم الضمائر الشخصية الشامل (صفحات 20 و 21 و 22 و 23 من كتيّب منهج Bienvenu 2)
 * Les pronoms personnels : Sujet, C.O.D, C.O.I
 * والتمرين الرسمي المكون من 15 جملة كاملة (Remplace les mots soulignés...)
 */
@Composable
fun Unit1PronounsSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        "🌟 المخطط العام (ص 20)",
        "👤 الفاعل Sujet (ص 20-21)",
        "🎯 المباشر C.O.D (ص 20-21)",
        "🤝 غير المباشر C.O.I (ص 20-22)",
        "📝 تمرين الكتيّب 15 جملة (ص 22-23)"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // --- شريط عنوان الكتيّب الرسمي التراثي ---
        PronounsOfficialBookletHeader(audioHelper = audioHelper)

        // --- أزرار التبويب الفرعية الأنيقة ---
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            items(tabTitles.indices.toList()) { index ->
                val isSelected = selectedTab == index
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        audioHelper.playClick()
                        selectedTab = index
                    },
                    label = {
                        Text(
                            text = tabTitles[index],
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                            softWrap = false,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Violet600,
                        selectedLabelColor = Color.White,
                        containerColor = Slate100,
                        labelColor = Slate700
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = if (isSelected) Violet700 else Slate200,
                        selectedBorderColor = Violet700,
                        borderWidth = 1.5.dp
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        // --- محتوى التبويب المختار ---
        when (selectedTab) {
            0 -> PronounsTreeDiagramView(audioHelper = audioHelper)
            1 -> PronounsSujetView(audioHelper = audioHelper)
            2 -> PronounsCodView(audioHelper = audioHelper)
            3 -> PronounsCoiView(audioHelper = audioHelper)
            4 -> PronounsOfficial15ExercisesView(
                audioHelper = audioHelper,
                onScoreEarned = onScoreEarned
            )
        }
    }
}

// -------------------------------------------------------------------------------------------------
// شريط ترويسة الكتيّب التراثي
// -------------------------------------------------------------------------------------------------
@Composable
private fun PronounsOfficialBookletHeader(audioHelper: AudioHelper) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, Indigo100),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // شارة المنهج الرسمية
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    Text(
                        text = "Bienvenu 2 • 2ème prép",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        softWrap = false,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Violet50,
                    border = BorderStroke(1.dp, Violet200)
                ) {
                    Text(
                        text = "📖 الصفحات 20 - 23",
                        color = Violet700,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        softWrap = false,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // المستطيل الرسمي لعنوان الدرس
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Slate50,
                border = BorderStroke(2.dp, Slate800),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text(
                            text = "Les pronoms personnels",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Serif,
                            color = Slate900
                        )
                        Text(
                            text = "الضمائر الشخصية : فاعل • مفعول مباشر • مفعول غير مباشر",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate600
                        )
                    }

                    AudioPlayButton(
                        textToSpeak = "Les pronoms personnels remplacent le sujet, le complément d'objet direct ou le complément d'objet indirect.",
                        audioHelper = audioHelper,
                        backgroundColor = Violet100,
                        iconTint = Violet800,
                        size = 34
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// تبويب 0: المخطط الشجري العام ص 20
// -------------------------------------------------------------------------------------------------
@Composable
fun PronounsTreeDiagramView(audioHelper: AudioHelper) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.5.dp, Indigo100)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text(
                        text = "🌳 Les pronoms personnels remplacent :",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = Indigo900
                    )
                    Text(
                        text = "المخطط الشجري الثلاثي من كتيّب المعهد (ص 20)",
                        fontSize = 11.sp,
                        color = Slate600
                    )
                }

                AudioPlayButton(
                    textToSpeak = "Les pronoms personnels remplacent : un, Sujet. Deux, complément d'objet direct. Trois, complément d'objet indirect.",
                    audioHelper = audioHelper,
                    backgroundColor = Indigo50,
                    iconTint = Indigo700,
                    size = 32
                )
            }

            // الفروع الثلاثة
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 1- Sujet
                BranchCard(
                    modifier = Modifier.weight(1f),
                    number = "1",
                    titleFr = "Sujet",
                    titleAr = "فاعل",
                    pronouns = listOf("Il", "Elle", "Nous", "Vous", "Ils", "Elles"),
                    badgeColor = Indigo600,
                    containerColor = Indigo50
                )

                // 2- C.o.d
                BranchCard(
                    modifier = Modifier.weight(1f),
                    number = "2",
                    titleFr = "C.o.d",
                    titleAr = "مباشر",
                    pronouns = listOf("le (M.)", "la (F.)", "l' (Voyelle)", "les (Pl.)"),
                    badgeColor = Teal600,
                    containerColor = Teal50
                )

                // 3- C.O.I
                BranchCard(
                    modifier = Modifier.weight(1f),
                    number = "3",
                    titleFr = "C. O. I",
                    titleAr = "غير مباشر",
                    pronouns = listOf("Lui (sing.)", "Leur (plur.)"),
                    badgeColor = Amber600,
                    containerColor = Amber50,
                    extraNote = "à, au, aux\n+ personne"
                )
            }

            // بطاقة التلخيص الذهبي
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Slate50,
                border = BorderStroke(1.dp, Slate200)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "💡 كيف نميز نوع الضمير في الامتحان بنظرة واحدة؟",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "1. إذا كان الخط تحت اسم في أول الجملة قبل الفعل ➔ ضمير فاعل (Sujet).\n" +
                                "2. إذا كان بعد الفعل مباشرة وبدون أي حرف جر ➔ مفعول مباشر (C.O.D).\n" +
                                "3. إذا كان بعد الفعل ومسبوقاً بـ (à, au, aux) ومعه شخص عاقل ➔ مفعول غير مباشر (C.O.I).",
                        fontSize = 11.sp,
                        color = Slate700,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun BranchCard(
    modifier: Modifier = Modifier,
    number: String,
    titleFr: String,
    titleAr: String,
    pronouns: List<String>,
    badgeColor: Color,
    containerColor: Color,
    extraNote: String? = null
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = containerColor,
        border = BorderStroke(1.5.dp, badgeColor.copy(alpha = 0.3f)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = CircleShape,
                color = badgeColor,
                modifier = Modifier.size(24.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = number,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = titleFr,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = badgeColor,
                softWrap = false,
                maxLines = 1
            )
            Text(
                text = titleAr,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Slate600,
                softWrap = false,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            pronouns.forEach { p ->
                Text(
                    text = "• $p",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate800,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }

            if (extraNote != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = badgeColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = extraNote,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        color = badgeColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// تبويب 1: ضمائر الفاعل Sujet ص 20 - 21
// -------------------------------------------------------------------------------------------------
@Composable
fun PronounsSujetView(audioHelper: AudioHelper) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // شريط العنوان
        SectionTitleBanner(
            titleFr = "1- Les pronoms personnels sujets :-",
            titleAr = "ضمائر الفاعل (تحل محل الفاعل في أول الجملة)",
            color = Indigo600,
            page = "p. 20-21",
            audioHelper = audioHelper,
            spokenText = "Les pronoms personnels sujets : il remplace un nom masculin, Elle remplace un nom féminin, Nous remplace un nom plus moi, Vous remplace un nom plus toi, Ils remplace un nom masculin pluriel, Elles remplace un nom féminin pluriel."
        )

        // بطاقات القواعد الستة الرسمية من ص 20
        FrenchCourseData.bookletPronounsRulesSujet.forEach { rule ->
            RuleCardItem(
                rule = rule,
                accentColor = Indigo600,
                audioHelper = audioHelper
            )
        }

        // بطاقة أمثلة الكتيّب الثلاثة ص 21
        BookletExamplesSection(
            title = "Exemples (Sujet) - ص 21",
            examples = FrenchCourseData.bookletPronounsSujetExamples,
            accentColor = Indigo600,
            audioHelper = audioHelper
        )
    }
}

// -------------------------------------------------------------------------------------------------
// تبويب 2: المفعول المباشر C.O.D ص 20 - 21
// -------------------------------------------------------------------------------------------------
@Composable
fun PronounsCodView(audioHelper: AudioHelper) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitleBanner(
            titleFr = "2- Complément d'objet direct : C.O.D.",
            titleAr = "المفعول به المباشر (يأتي بعد الفعل مباشرة وبدون حرف جر)",
            color = Teal600,
            page = "p. 20-21",
            audioHelper = audioHelper,
            spokenText = "Complément d'objet direct C.O.D. Le remplace un nom masculin singulier. La remplace un nom féminin singulier. L apostrophe remplace un nom singulier avec verbe commençant par une voyelle. Les remplace un pluriel."
        )

        FrenchCourseData.bookletPronounsRulesCOD.forEach { rule ->
            RuleCardItem(
                rule = rule,
                accentColor = Teal600,
                audioHelper = audioHelper
            )
        }

        // ملحوظة الحروف المتحركة من ص 21
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = Amber50,
            border = BorderStroke(1.5.dp, Amber300)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = Amber700,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "الحروف المتحركة في اللغة الفرنسية (Les voyelles) :",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Amber900
                    )
                    Text(
                        text = "( a - e - i - o - u - h - y ) ➔ نضع L' بدلاً من Le أو La",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Amber800
                    )
                }
            }
        }

        BookletExamplesSection(
            title = "Exemples (C.O.D.) - ص 21",
            examples = FrenchCourseData.bookletPronounsCodExamples,
            accentColor = Teal600,
            audioHelper = audioHelper
        )
    }
}

// -------------------------------------------------------------------------------------------------
// تبويب 3: غير المباشر C.O.I ص 20 - 22
// -------------------------------------------------------------------------------------------------
@Composable
fun PronounsCoiView(audioHelper: AudioHelper) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitleBanner(
            titleFr = "3- Complément d'objet indirect : C.O.I :-",
            titleAr = "المفعول به غير المباشر (حرف جر + شخص عاقل)",
            color = Amber600,
            page = "p. 20-22",
            audioHelper = audioHelper,
            spokenText = "Complément d'objet indirect C.O.I. Avec ces prépositions à, au, aux. Avec une personne. Lui remplace un nom singulier. Leur remplace un nom pluriel."
        )

        // شروط C.O.I الذهبية
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Amber50),
            border = BorderStroke(1.5.dp, Amber200)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "⚠️ شرطان أساسيان لكي نستخدم Lui أو Leur :",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Amber900
                )
                Spacer(modifier = Modifier.height(6.dp))
                FrenchCourseData.bookletCoiConditions.forEach { cond ->
                    Text(
                        text = "• $cond",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate800,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }

        FrenchCourseData.bookletPronounsRulesCOI.forEach { rule ->
            RuleCardItem(
                rule = rule,
                accentColor = Amber600,
                audioHelper = audioHelper
            )
        }

        BookletExamplesSection(
            title = "Exemples (C.O.I.) - ص 22",
            examples = FrenchCourseData.bookletPronounsCoiExamples,
            accentColor = Amber600,
            audioHelper = audioHelper
        )
    }
}

// -------------------------------------------------------------------------------------------------
// تبويب 4: تمرين الكتيّب الرسمي الـ 15 جملة ص 22 و 23
// -------------------------------------------------------------------------------------------------
@Composable
fun PronounsOfficial15ExercisesView(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    var categoryFilter by remember { mutableStateOf<BookletPronounCategory?>(null) }
    val allExercises = FrenchCourseData.bookletPronounsOfficial15Exercises
    val filteredExercises = remember(categoryFilter) {
        if (categoryFilter == null) allExercises
        else allExercises.filter { it.category == categoryFilter }
    }

    var selectedExerciseId by remember { mutableStateOf(filteredExercises.firstOrNull()?.id ?: "") }
    var currentExerciseIndex by remember { mutableIntStateOf(0) }
    val currentExercise = filteredExercises.getOrElse(currentExerciseIndex) { allExercises[0] }

    var userSelectedAnswer by remember(currentExercise.id) { mutableStateOf<String?>(null) }
    var showFullSentenceTransformed by remember(currentExercise.id) { mutableStateOf(false) }
    var showTranslation by remember(currentExercise.id) { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // شريط عنوان التمرين الرسمي
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Violet50),
            border = BorderStroke(1.5.dp, Violet200)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text(
                            text = "Remplace les mots soulignés par un pronom personnel convenable:-",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Serif,
                            color = Violet900
                        )
                        Text(
                            text = "استبدل الكلمات التي تحتها خط بالضمير الشخصي المناسب (15 جملة كاملة ص 22-23)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate700
                        )
                    }

                    AudioPlayButton(
                        textToSpeak = "Remplace les mots soulignés par un pronom personnel convenable.",
                        audioHelper = audioHelper,
                        backgroundColor = Color.White,
                        iconTint = Violet800,
                        size = 32
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // فلاتر الفئات (الكل 15 / Sujet / COD / COI)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilterChip(
                        selected = categoryFilter == null,
                        onClick = {
                            categoryFilter = null
                            currentExerciseIndex = 0
                            audioHelper.playClick()
                        },
                        label = {
                            Text(
                                "الكل (15)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = false,
                                maxLines = 1
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Violet700,
                            selectedLabelColor = Color.White
                        )
                    )

                    BookletPronounCategory.values().forEach { cat ->
                        val count = allExercises.count { it.category == cat }
                        FilterChip(
                            selected = categoryFilter == cat,
                            onClick = {
                                categoryFilter = cat
                                currentExerciseIndex = 0
                                audioHelper.playClick()
                            },
                            label = {
                                Text(
                                    "${cat.code} ($count)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    softWrap = false,
                                    maxLines = 1
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(cat.badgeColorHex),
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // بطاقة الجملة التفاعلية الحالية
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, Indigo100),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // شريط بيانات السؤال (رقم الجملة، الصفحة، التصنيف)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color(currentExercise.category.badgeColorHex),
                            modifier = Modifier.size(28.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${currentExercise.number}",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(currentExercise.category.badgeColorHex).copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "${currentExercise.category.code} • ${currentExercise.pageReference}",
                                color = Color(currentExercise.category.badgeColorHex),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                softWrap = false,
                                maxLines = 1
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TranslateIconButton(
                            isTranslated = showTranslation,
                            onClick = {
                                showTranslation = !showTranslation
                                audioHelper.playClick()
                            },
                            contentDescription = "ترجمة الجملة",
                            size = 30
                        )

                        AudioPlayButton(
                            textToSpeak = currentExercise.fullSentenceFr,
                            audioHelper = audioHelper,
                            backgroundColor = Slate100,
                            iconTint = Slate800,
                            size = 30
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // نص الجملة الأصلية مع إبراز وتسطير الجزء المطلوب استبداله
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Slate50,
                    border = BorderStroke(1.dp, Slate200),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        val sentenceAnnotated = androidx.compose.ui.text.buildAnnotatedString {
                            val fullText = "${currentExercise.number}- ${currentExercise.fullSentenceFr}"
                            append(fullText)
                            val parts = if (currentExercise.underlinedPart.contains("...")) {
                                currentExercise.underlinedPart.split("...").map { it.trim() }.filter { it.isNotEmpty() }
                            } else {
                                listOf(currentExercise.underlinedPart.trim())
                            }
                            parts.forEach { part ->
                                var searchIndex = 0
                                while (searchIndex < fullText.length) {
                                    val idx = fullText.indexOf(part, searchIndex, ignoreCase = true)
                                    if (idx == -1) break
                                    addStyle(
                                        style = androidx.compose.ui.text.SpanStyle(
                                            color = Color(currentExercise.category.badgeColorHex),
                                            fontWeight = FontWeight.Black,
                                            textDecoration = TextDecoration.Underline
                                        ),
                                        start = idx,
                                        end = idx + part.length
                                    )
                                    searchIndex = idx + part.length
                                }
                            }
                        }

                        Text(
                            text = sentenceAnnotated,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Serif,
                            color = Slate900
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // شارة توضح الجزء الذي تحته خط
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "الكلمة المسطرة في الكتيّب : ",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate600
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(currentExercise.category.badgeColorHex).copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = currentExercise.underlinedPart,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black,
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(currentExercise.category.badgeColorHex),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }

                ArabicTranslationBanner(
                    translation = currentExercise.sentenceAr,
                    visible = showTranslation
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "اختر الضمير المناسب للاستبدال :",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate700
                )

                Spacer(modifier = Modifier.height(8.dp))

                // خيارات الإجابة
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    currentExercise.options.forEach { option ->
                        val isChosen = userSelectedAnswer == option
                        val isCorrect = option.trim().equals(currentExercise.targetPronoun.trim(), ignoreCase = true) ||
                                option.trim().equals(currentExercise.transformedSentenceFr.trim(), ignoreCase = true) ||
                                option.trim().replace(".", "").equals(currentExercise.transformedSentenceFr.trim().replace(".", ""), ignoreCase = true)

                        Button(
                            onClick = {
                                userSelectedAnswer = option
                                if (isCorrect) {
                                    audioHelper.playSuccessChime()
                                    onScoreEarned(1)
                                    showFullSentenceTransformed = true
                                } else {
                                    audioHelper.playErrorBuzz()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when {
                                    userSelectedAnswer == null -> Violet600
                                    isChosen && isCorrect -> Emerald600
                                    isChosen && !isCorrect -> Rose600
                                    else -> Slate200
                                }
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("btn_pronoun_${currentExercise.number}_$option")
                        ) {
                            Text(
                                text = option,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = if (userSelectedAnswer != null && !isChosen) Slate700 else Color.White,
                                softWrap = false,
                                maxLines = 1
                            )
                        }
                    }
                }

                // بطاقة نتيجة الإجابة + الجملة المتحولة بالكامل + الشرح النحوي
                AnimatedVisibility(visible = userSelectedAnswer != null) {
                    val isCorrect = userSelectedAnswer == currentExercise.targetPronoun ||
                            userSelectedAnswer == currentExercise.transformedSentenceFr

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // شريط الجملة المحولة الرسمية بعد الاستبدال
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isCorrect) Emerald50 else Rose50,
                            border = BorderStroke(1.5.dp, if (isCorrect) Emerald300 else Rose300)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = if (isCorrect) "🎉 إجابة ممتازة! الجملة بعد الاستبدال:" else "❌ الإجابة الصحيحة هي:",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Black,
                                            color = if (isCorrect) Emerald700 else Rose700
                                        )
                                        Text(
                                            text = currentExercise.transformedSentenceFr,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Black,
                                            color = Slate900,
                                            fontFamily = FontFamily.Serif
                                        )
                                        Text(
                                            text = currentExercise.transformedSentenceAr,
                                            fontSize = 11.sp,
                                            color = Slate600
                                        )
                                    }

                                    AudioPlayButton(
                                        textToSpeak = currentExercise.transformedSentenceFr,
                                        audioHelper = audioHelper,
                                        backgroundColor = if (isCorrect) Emerald100 else Rose100,
                                        iconTint = if (isCorrect) Emerald800 else Rose800,
                                        size = 32
                                    )
                                }
                            }
                        }

                        // بطاقة التبرير النحوي ومكان وضع الضمير
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Slate50,
                            border = BorderStroke(1.dp, Slate200)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "🔍 التحليل النحوي ومكان الضمير في الجملة :",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Slate900
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentExercise.explanationAr,
                                    fontSize = 11.sp,
                                    color = Slate700,
                                    lineHeight = 16.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "📍 موضع الضمير : ${currentExercise.positionRuleAr}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Indigo700
                                )
                            }
                        }

                        // زر الانتقال للجملة التالية
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "السؤال ${currentExerciseIndex + 1} من ${filteredExercises.size}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate500
                            )

                            Button(
                                onClick = {
                                    audioHelper.playClick()
                                    userSelectedAnswer = null
                                    showTranslation = false
                                    currentExerciseIndex = (currentExerciseIndex + 1) % filteredExercises.size
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Slate900),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("الجملة التالية", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }

        // قائمة الانتقال السريع بين الـ 15 جملة
        Text(
            text = "⚡ اختر أي جملة للانتقال إليها مباشرة :",
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            color = Slate800
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            items(filteredExercises.indices.toList()) { idx ->
                val ex = filteredExercises[idx]
                val isCurrent = idx == currentExerciseIndex
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isCurrent) Color(ex.category.badgeColorHex) else Slate100,
                    border = BorderStroke(1.dp, if (isCurrent) Color(ex.category.badgeColorHex) else Slate300),
                    modifier = Modifier
                        .clickable {
                            audioHelper.playClick()
                            currentExerciseIndex = idx
                            userSelectedAnswer = null
                            showTranslation = false
                        }
                ) {
                    Text(
                        text = "ج ${ex.number}",
                        fontSize = 11.sp,
                        fontWeight = if (isCurrent) FontWeight.Black else FontWeight.Bold,
                        color = if (isCurrent) Color.White else Slate700,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// المكونات المساعدة القابلة لإعادة الاستخدام
// -------------------------------------------------------------------------------------------------
@Composable
private fun SectionTitleBanner(
    titleFr: String,
    titleAr: String,
    color: Color,
    page: String,
    audioHelper: AudioHelper,
    spokenText: String
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.08f)),
        border = BorderStroke(1.5.dp, color.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = titleFr,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = color
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = color
                    ) {
                        Text(
                            text = page,
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = titleAr,
                    fontSize = 11.sp,
                    color = Slate700
                )
            }

            AudioPlayButton(
                textToSpeak = spokenText,
                audioHelper = audioHelper,
                backgroundColor = Color.White,
                iconTint = color,
                size = 32
            )
        }
    }
}

@Composable
private fun RuleCardItem(
    rule: BookletPronounRuleItem,
    accentColor: Color,
    audioHelper: AudioHelper
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Slate200)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = accentColor,
                        modifier = Modifier.defaultMinSize(minWidth = 38.dp)
                    ) {
                        Text(
                            text = rule.pronoun,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                            softWrap = false,
                            maxLines = 1
                        )
                    }

                    Column {
                        Text(
                            text = rule.replacesFr,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = rule.replacesAr,
                            fontSize = 10.sp,
                            color = Slate600
                        )
                    }
                }

                AudioPlayButton(
                    textToSpeak = "${rule.pronoun} : ${rule.transformedFr}",
                    audioHelper = audioHelper,
                    backgroundColor = Slate100,
                    iconTint = Slate800,
                    size = 28
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // المستطيل الصغير للمثال
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Slate50,
                border = BorderStroke(1.dp, Slate200),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = rule.transformedFr,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = accentColor
                        )
                        Text(
                            text = rule.noteAr,
                            fontSize = 10.sp,
                            color = Slate600
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BookletExamplesSection(
    title: String,
    examples: List<Triple<String, String, String>>,
    accentColor: Color,
    audioHelper: AudioHelper
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.5.dp, accentColor.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "⚡ $title :",
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(8.dp))

            examples.forEach { (original, transformed, note) ->
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Slate50,
                    border = BorderStroke(1.dp, Slate200),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                            Text(
                                text = original,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate800
                            )
                            Text(
                                text = transformed,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = accentColor
                            )
                            Text(
                                text = "💡 $note",
                                fontSize = 10.sp,
                                color = Slate600
                            )
                        }

                        AudioPlayButton(
                            textToSpeak = "$original. $transformed",
                            audioHelper = audioHelper,
                            backgroundColor = accentColor.copy(alpha = 0.12f),
                            iconTint = accentColor,
                            size = 28
                        )
                    }
                }
            }
        }
    }
}
