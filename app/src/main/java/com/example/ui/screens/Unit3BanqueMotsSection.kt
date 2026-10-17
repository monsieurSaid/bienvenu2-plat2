package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.QuizQuestion
import com.example.ui.components.InteractiveQuizCard
import com.example.ui.theme.Amber100
import com.example.ui.theme.Amber200
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber600
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber800
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald300
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Indigo100
import com.example.ui.theme.Indigo200
import com.example.ui.theme.Indigo50
import com.example.ui.theme.Indigo600
import com.example.ui.theme.Indigo700
import com.example.ui.theme.Indigo900
import com.example.ui.theme.Orange100
import com.example.ui.theme.Orange50
import com.example.ui.theme.Orange600
import com.example.ui.theme.Orange700
import com.example.ui.theme.Rose100
import com.example.ui.theme.Rose200
import com.example.ui.theme.Rose50
import com.example.ui.theme.Rose600
import com.example.ui.theme.Rose700
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.Teal100
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.Teal700
import com.example.ui.theme.Violet100
import com.example.ui.theme.Violet50
import com.example.ui.theme.Violet600
import com.example.ui.theme.Violet700
import com.example.ui.theme.Violet900

enum class Unit3WordCategory(
    val labelFr: String,
    val labelAr: String,
    val emoji: String,
    val color: Color
) {
    ALL("Tous", "الكل (31)", "📚", Teal700),
    MASCULINE("Noms masculins", "مذكر (9)", "👔", Indigo700),
    FEMININE("Noms féminins", "مؤنث (7)", "👗", Rose600),
    CORPS("Le corps", "جسم الإنسان (15)", "🧍", Emerald700)
}

data class Unit3BanqueWord(
    val id: String,
    val french: String,
    val arabic: String,
    val category: Unit3WordCategory,
    val page: Int = 62,
    val emoji: String = "📖",
    val note: String? = null
)

@Composable
fun Unit3BanqueMotsSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val allWords = remember { getUnit3BanqueWords() }
    var selectedCategory by remember { mutableStateOf(Unit3WordCategory.ALL) }
    var searchQuery by remember { mutableStateOf("") }
    var activeMode by remember { mutableIntStateOf(0) } // 0: القاموس, 1: بطاقات الفلاش كاردز, 2: اختبار المفردات

    val filteredWords = remember(selectedCategory, searchQuery) {
        allWords.filter { word ->
            val matchesCat = (selectedCategory == Unit3WordCategory.ALL) || (word.category == selectedCategory)
            val matchesSearch = searchQuery.isBlank() ||
                    word.french.contains(searchQuery, ignoreCase = true) ||
                    word.arabic.contains(searchQuery, ignoreCase = true)
            matchesCat && matchesSearch
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Header (Booklet Page 62)
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Teal50),
            border = BorderStroke(1.5.dp, Teal600.copy(alpha = 0.4f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(shape = RoundedCornerShape(8.dp), color = Teal100) {
                        Text(
                            text = "📖 كتيّب المعهد - صفحة 62",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Teal700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Surface(shape = RoundedCornerShape(8.dp), color = Color.White) {
                        Text(
                            text = "31 كلمة مقررة 🩺",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Emerald700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Text(
                    text = "Banque des mots (Unité 3)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Teal700
                )

                Text(
                    text = "قاموس المفردات الشامل للصحة والمستشفى وأعضاء جسم الإنسان وفقاً لجدول صفحة 62 بكتيّب منهج Bienvenu 2، مع النطق الفرنسي الصحيح والاختبارات التفاعلية.",
                    fontSize = 12.sp,
                    color = Slate700,
                    lineHeight = 18.sp
                )

                // Quick stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    MiniStatBadge("9 أسماء مذكرة", "👔", Indigo100, Indigo700, Modifier.weight(1f))
                    MiniStatBadge("7 أسماء مؤنثة", "👗", Rose100, Rose700, Modifier.weight(1f))
                    MiniStatBadge("15 جسم الإنسان", "🧍", Emerald100, Emerald800, Modifier.weight(1f))
                }
            }
        }

        // Mode Navigation Bar (قاموس / بطاقات / اختبار)
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Slate200),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val modes = listOf(
                    Triple("🗂️ القاموس الناطق", "Dictionnaire", 0),
                    Triple("🎴 بطاقات الحفظ", "Flashcards", 1),
                    Triple("🎯 اختبار الكلمات", "Quiz Mots", 2)
                )

                modes.forEach { (titleAr, titleFr, idx) ->
                    val isSelected = activeMode == idx
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) Teal700 else Color.Transparent,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                audioHelper.playClick()
                                activeMode = idx
                            }
                            .testTag("unit3_banque_mode_$idx")
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = titleAr,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                color = if (isSelected) Color.White else Slate700
                            )
                            Text(
                                text = titleFr,
                                fontSize = 10.sp,
                                color = if (isSelected) Teal100 else Slate500
                            )
                        }
                    }
                }
            }
        }

        // Mode content
        when (activeMode) {
            0 -> Unit3DictionaryView(
                words = filteredWords,
                selectedCategory = selectedCategory,
                onSelectCategory = { selectedCategory = it },
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it },
                audioHelper = audioHelper
            )
            1 -> Unit3FlashcardsView(
                words = filteredWords,
                audioHelper = audioHelper
            )
            2 -> Unit3VocabQuizView(
                audioHelper = audioHelper,
                onScoreEarned = onScoreEarned
            )
        }
    }
}

// ---------------------------------------------------------------------
// 1. القاموس الناطق المصور (Dictionnaire)
// ---------------------------------------------------------------------
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Unit3DictionaryView(
    words: List<Unit3BanqueWord>,
    selectedCategory: Unit3WordCategory,
    onSelectCategory: (Unit3WordCategory) -> Unit,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    audioHelper: AudioHelper
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Search TextField
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("ابحث عن كلمة بالفرنسية أو العربية...", fontSize = 12.sp, color = Slate500) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "بحث", tint = Teal700) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "مسح", tint = Slate500)
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Teal700,
                unfocusedBorderColor = Slate200
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Unit3WordCategory.values().forEach { cat ->
                val isSelected = selectedCategory == cat
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) cat.color else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) cat.color else Slate200),
                    modifier = Modifier.clickable {
                        audioHelper.playClick()
                        onSelectCategory(cat)
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(cat.emoji, fontSize = 13.sp)
                        Text(
                            text = cat.labelAr,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                            color = if (isSelected) Color.White else Slate700
                        )
                    }
                }
            }
        }

        // Results count
        Text(
            text = "تم العثور على ${words.size} كلمة مع النطق الفرنسي:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate600
        )

        // Words Cards
        words.forEach { word ->
            Unit3WordCard(word = word, audioHelper = audioHelper)
        }

        if (words.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Slate50,
                border = BorderStroke(1.dp, Slate200),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("🔍", fontSize = 28.sp)
                    Text("لا توجد كلمات مطابقة لبحثك", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate700)
                    Text("جرّب البحث بكلمة أخرى أو اضغط على تصنيف (الكل).", fontSize = 11.sp, color = Slate500)
                }
            }
        }
    }
}

@Composable
private fun Unit3WordCard(
    word: Unit3BanqueWord,
    audioHelper: AudioHelper
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, word.category.color.copy(alpha = 0.25f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left: Icon + French Word + Category
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = CircleShape,
                    color = word.category.color.copy(alpha = 0.12f),
                    modifier = Modifier.size(38.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = word.emoji, fontSize = 18.sp)
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = word.french,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = word.category.color.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = word.category.labelFr,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = word.category.color,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                        if (word.note != null) {
                            Text(
                                text = word.note,
                                fontSize = 9.sp,
                                color = Slate500
                            )
                        }
                    }
                }
            }

            // Right: Arabic meaning + Audio Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = word.arabic,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = word.category.color,
                    textAlign = TextAlign.End
                )

                IconButton(
                    onClick = { audioHelper.speak(word.french) },
                    modifier = Modifier
                        .size(34.dp)
                        .background(word.category.color.copy(alpha = 0.12f), CircleShape)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "استماع",
                        tint = word.category.color,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// ---------------------------------------------------------------------
// 2. بطاقات الحفظ والمراجعة السريعة (Flashcards)
// ---------------------------------------------------------------------
@Composable
private fun Unit3FlashcardsView(
    words: List<Unit3BanqueWord>,
    audioHelper: AudioHelper
) {
    if (words.isEmpty()) {
        Text("لا توجد كلمات في هذا القسم", fontSize = 12.sp, color = Slate600)
        return
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }

    // Safe index bounds
    val safeIndex = currentIndex.coerceIn(0, words.size - 1)
    val currentWord = words[safeIndex]

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress text
        Text(
            text = "بطاقة ${safeIndex + 1} من أصل ${words.size} (اضغط على البطاقة لقلبها 🔄)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Slate600
        )

        // Main Flashcard
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isFlipped) Teal50 else Color.White
            ),
            border = BorderStroke(2.dp, if (isFlipped) Teal700 else Slate300),
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clickable {
                    audioHelper.playClick()
                    isFlipped = !isFlipped
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(currentWord.emoji, fontSize = 38.sp)

                    if (!isFlipped) {
                        Text(
                            text = currentWord.french,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = Slate900,
                            textAlign = TextAlign.Center
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = currentWord.category.color.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = currentWord.category.labelFr,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = currentWord.category.color,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Text(
                            text = "اضغط لعرض المعنى بالعربية 👆",
                            fontSize = 11.sp,
                            color = Slate500
                        )
                    } else {
                        Text(
                            text = currentWord.arabic,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            color = Teal700,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = currentWord.french,
                            fontSize = 14.sp,
                            color = Slate600
                        )
                        if (currentWord.note != null) {
                            Text(
                                text = "💡 ${currentWord.note}",
                                fontSize = 11.sp,
                                color = Amber800
                            )
                        }
                    }
                }
            }
        }

        // Sound + Nav Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    audioHelper.playClick()
                    isFlipped = false
                    if (safeIndex > 0) currentIndex--
                },
                enabled = safeIndex > 0,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("⬅️ السابقة", fontSize = 12.sp)
            }

            IconButton(
                onClick = { audioHelper.speak(currentWord.french) },
                modifier = Modifier
                    .size(44.dp)
                    .background(Teal700, CircleShape)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "استماع", tint = Color.White)
            }

            Button(
                onClick = {
                    audioHelper.playClick()
                    isFlipped = false
                    if (safeIndex < words.size - 1) currentIndex++
                },
                enabled = safeIndex < words.size - 1,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Teal700)
            ) {
                Text("التالية ➡️", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

// ---------------------------------------------------------------------
// 3. اختبار مفردات بنك الكلمات ص 62 (Quiz Vocabulaire)
// ---------------------------------------------------------------------
@Composable
private fun Unit3VocabQuizView(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val quizQuestions = remember { getUnit3VocabQuizQuestions() }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Amber50),
            border = BorderStroke(1.dp, Amber400)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🎯 اختبار إتقان مفردات صفحة 62 :",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Amber800
                    )
                    Surface(shape = RoundedCornerShape(6.dp), color = Color.White) {
                        Text(
                            text = "${quizQuestions.size} أسئلة",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate700,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = "اختر الإجابة الصحيحة لمعاني الكلمات والمصطلحات وتصنيفاتها المذكرة والمؤنثة وأعضاء الجسم (+5 نقاط لكل إجابة صحيحة):",
                    fontSize = 11.sp,
                    color = Slate700
                )
            }
        }

        quizQuestions.forEach { q ->
            InteractiveQuizCard(
                question = q,
                audioHelper = audioHelper,
                onCorrectAnswer = { onScoreEarned(5) }
            )
        }
    }
}

@Composable
private fun MiniStatBadge(title: String, icon: String, bg: Color, fg: Color, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = bg,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(icon, fontSize = 14.sp)
            Text(title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = fg)
        }
    }
}

// ---------------------------------------------------------------------
// قائمة الكلمات الـ 31 الرسمية من صفحة 62
// ---------------------------------------------------------------------
private fun getUnit3BanqueWords(): List<Unit3BanqueWord> = listOf(
    // --- Noms masculins (أسماء مذكرة) - ص 62 ---
    Unit3BanqueWord("u3_w1", "un thermomètre", "ميزان حرارة", Unit3WordCategory.MASCULINE, 62, "🌡️"),
    Unit3BanqueWord("u3_w2", "un médicament", "دواء", Unit3WordCategory.MASCULINE, 62, "💊"),
    Unit3BanqueWord("u3_w3", "un pansement", "رباط طبي (ضمادة)", Unit3WordCategory.MASCULINE, 62, "🩹"),
    Unit3BanqueWord("u3_w4", "un rapport", "تقرير", Unit3WordCategory.MASCULINE, 62, "📋", "un rapport médical"),
    Unit3BanqueWord("u3_w5", "un calmant", "مهدىء / مسكن", Unit3WordCategory.MASCULINE, 62, "💉"),
    Unit3BanqueWord("u3_w6", "l'alcool", "الكحول", Unit3WordCategory.MASCULINE, 62, "🧴", "nom masculin"),
    Unit3BanqueWord("u3_w7", "des ciseaux", "مقص", Unit3WordCategory.MASCULINE, 62, "✂️", "masculin pluriel"),
    Unit3BanqueWord("u3_w8", "un accident", "حادثة", Unit3WordCategory.MASCULINE, 62, "🚗💥"),
    Unit3BanqueWord("u3_w9", "un conseil", "نصيحة", Unit3WordCategory.MASCULINE, 62, "💡"),

    // --- Noms féminins (أسماء مؤنثة) - ص 62 ---
    Unit3BanqueWord("u3_w10", "une seringue", "سرنجة", Unit3WordCategory.FEMININE, 62, "💉"),
    Unit3BanqueWord("u3_w11", "une piqûre", "حقنة", Unit3WordCategory.FEMININE, 62, "💉"),
    Unit3BanqueWord("u3_w12", "une ordonnance", "روشتة", Unit3WordCategory.FEMININE, 62, "📝", "nom féminin"),
    Unit3BanqueWord("u3_w13", "une radiographie", "أشعة", Unit3WordCategory.FEMININE, 62, "🩻"),
    Unit3BanqueWord("u3_w14", "une ambulance", "سيارة إسعاف", Unit3WordCategory.FEMININE, 62, "🚑"),
    Unit3BanqueWord("u3_w15", "la santé", "الصحة", Unit3WordCategory.FEMININE, 62, "❤️"),
    Unit3BanqueWord("u3_w16", "des affaires", "أغراض - أمتعة", Unit3WordCategory.FEMININE, 62, "🧳", "féminin pluriel"),

    // --- Le corps : جسم الإنسان - ص 62 ---
    Unit3BanqueWord("u3_w17", "la tête", "الرأس", Unit3WordCategory.CORPS, 62, "🗣️", "féminin : à la tête"),
    Unit3BanqueWord("u3_w18", "le front", "الجبهة", Unit3WordCategory.CORPS, 62, "🧑", "masculin : au front"),
    Unit3BanqueWord("u3_w19", "l'oeil", "العين", Unit3WordCategory.CORPS, 62, "👁️", "pluriel: les yeux"),
    Unit3BanqueWord("u3_w20", "le nez", "الأنف", Unit3WordCategory.CORPS, 62, "👃", "masculin : au nez"),
    Unit3BanqueWord("u3_w21", "la bouche", "الفم", Unit3WordCategory.CORPS, 62, "👄", "féminin : à la bouche"),
    Unit3BanqueWord("u3_w22", "l'oreille", "الأذن", Unit3WordCategory.CORPS, 62, "👂", "féminin voyelle : à l'oreille"),
    Unit3BanqueWord("u3_w23", "la gorge", "الحلق", Unit3WordCategory.CORPS, 62, "🧣", "féminin : à la gorge"),
    Unit3BanqueWord("u3_w24", "le ventre", "البطن", Unit3WordCategory.CORPS, 62, "🤰", "masculin : au ventre"),
    Unit3BanqueWord("u3_w25", "l'estomac", "المعدة", Unit3WordCategory.CORPS, 62, "🫁", "masculin voyelle : à l'estomac"),
    Unit3BanqueWord("u3_w26", "le bras", "الذراع", Unit3WordCategory.CORPS, 62, "💪", "masculin : au bras"),
    Unit3BanqueWord("u3_w27", "la main", "اليد", Unit3WordCategory.CORPS, 62, "✋", "féminin : à la main"),
    Unit3BanqueWord("u3_w28", "le dos", "الظهر", Unit3WordCategory.CORPS, 62, "🥋", "masculin : au dos"),
    Unit3BanqueWord("u3_w29", "la jambe", "الساق", Unit3WordCategory.CORPS, 62, "🦵", "féminin : à la jambe"),
    Unit3BanqueWord("u3_w30", "le genou", "الركبة", Unit3WordCategory.CORPS, 62, "🧎", "masculin : au genou"),
    Unit3BanqueWord("u3_w31", "le pied", "القدم", Unit3WordCategory.CORPS, 62, "🦶", "masculin : au pied")
)

// ---------------------------------------------------------------------
// أسئلة اختبار بنك الكلمات ص 62
// ---------------------------------------------------------------------
private fun getUnit3VocabQuizQuestions(): List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "u3_bm_q1",
        question = "1. Que signifie « un thermomètre » ?",
        options = listOf("سرنجة 💉", "ميزان حرارة 🌡️", "دواء 💊", "مقص ✂️"),
        correctIndex = 1,
        pageReference = "p. 62",
        explanation = "un thermomètre = ميزان حرارة (لقياس درجة الحرارة).",
        arabicTranslation = "ما معنى كلمة « un thermomètre » ؟ ➔ ميزان حرارة."
    ),
    QuizQuestion(
        id = "u3_bm_q2",
        question = "2. Le mot « ordonnance » (روشتة) est un nom :",
        options = listOf("Féminin (مؤنث) ➔ une ordonnance", "Adjectif (صفة)", "Masculin (مذكر)", "Verbe (فعل)"),
        correctIndex = 0,
        pageReference = "p. 62",
        explanation = "Le mot ordonnance est féminin : « une ordonnance ».",
        arabicTranslation = "كلمة روشتة (ordonnance) اسم مؤنث ➔ une ordonnance."
    ),
    QuizQuestion(
        id = "u3_bm_q3",
        question = "3. « un calmant » signifie en arabe :",
        options = listOf("تقرير", "مستشفى", "مهدىء / مسكن", "رباط طبي"),
        correctIndex = 2,
        pageReference = "p. 62",
        explanation = "un calmant = مهدىء أو مسكن للآلام.",
        arabicTranslation = "كلمة « un calmant » تعني باللغة العربية: مهدىء / مسكن."
    ),
    QuizQuestion(
        id = "u3_bm_q4",
        question = "4. Quel est le sens de « le ventre » dans les parties du corps ?",
        options = listOf("البطن", "الرأس", "الساق", "الظهر"),
        correctIndex = 0,
        pageReference = "p. 62",
        explanation = "le ventre = البطن (وهو اسم مذكر : au ventre).",
        arabicTranslation = "ما معنى « le ventre » من أعضاء الجسم؟ ➔ البطن."
    ),
    QuizQuestion(
        id = "u3_bm_q5",
        question = "5. « des ciseaux » (مقص) est un mot :",
        options = listOf("Féminin pluriel (مؤنث جمع)", "Masculin pluriel (مذكر جمع)", "Féminin singulier (مؤنث مفرد)"),
        correctIndex = 1,
        pageReference = "p. 62",
        explanation = "des ciseaux est un nom masculin pluriel.",
        arabicTranslation = "كلمة « des ciseaux » (مقص) اسم مذكر جمع."
    ),
    QuizQuestion(
        id = "u3_bm_q6",
        question = "6. Comment dit-on « أشعة » en français ?",
        options = listOf("un accident 🚗", "une piqûre 💉", "une ambulance 🚑", "une radiographie 🩻"),
        correctIndex = 3,
        pageReference = "p. 62",
        explanation = "أشعة = une radiographie.",
        arabicTranslation = "كيف نقول « أشعة » بالفرنسية؟ ➔ une radiographie."
    ),
    QuizQuestion(
        id = "u3_bm_q7",
        question = "7. Laquelle de ces parties du corps est FÉMININE ?",
        options = listOf("le bras (الذراع)", "la jambe (الساق)", "le genou (الركبة)", "le dos (الظهر)"),
        correctIndex = 1,
        pageReference = "p. 62",
        explanation = "la jambe est féminin (on dit: à la jambe). Les autres sont masculins (le bras, le dos, le genou).",
        arabicTranslation = "أي من هذه الأعضاء اسم مؤنث؟ ➔ la jambe (الساق)."
    ),
    QuizQuestion(
        id = "u3_bm_q8",
        question = "8. « l'estomac » (المعدة) commence par :",
        options = listOf("Un mot invariable", "Une consonne (حرف ساكن)", "Une voyelle (حرف متحرك) ➔ à l'estomac"),
        correctIndex = 2,
        pageReference = "p. 62",
        explanation = "l'estomac commence par la voyelle 'e' ➔ à l'estomac.",
        arabicTranslation = "المعدة « l'estomac » تبدأ بحرف متحرك 'e' ولذلك تأخذ à l'."
    )
)
