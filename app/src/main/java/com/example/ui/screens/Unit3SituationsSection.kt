package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.components.TranslateIconButton
import com.example.ui.theme.Amber100
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber600
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber800
import com.example.ui.theme.Amber900
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald300
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Emerald800
import com.example.ui.theme.Emerald900
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
import com.example.ui.theme.Rose800
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

data class Unit3SituationItem(
    val id: Int,
    val isOfficialBookletPage63: Boolean,
    val frenchPrompt: String,
    val arabicPrompt: String,
    val options: List<Unit3SituationOption>,
    val correctKey: String,
    val explanation: String
)

data class Unit3SituationOption(
    val key: String, // "a", "b", "c"
    val frenchText: String,
    val arabicText: String
)

@Composable
fun Unit3SituationsSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val context = LocalContext.current
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: حل المواقف, 1: مفاتيح وقواعد المواقف
    val userAnswers = remember { mutableStateMapOf<Int, String>() }

    val situations = remember { getUnit3SituationsList() }

    val correctCount = userAnswers.count { (id, chosenKey) ->
        situations.find { it.id == id }?.correctKey == chosenKey
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Header Card (Booklet Page 63)
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Teal50),
            border = BorderStroke(1.5.dp, Teal600.copy(alpha = 0.35f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Teal700
                    ) {
                        Text(
                            text = "Situations • Unité 3",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Teal100)
                    ) {
                        Text(
                            text = "📖 كتيّب المعهد - صفحة 63",
                            color = Teal700,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Text(
                    text = "مواقف الصحة والمستشفى (Choisis le bon groupe)",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    color = Teal700
                )

                Text(
                    text = "أسئلة مواقف الوحدة الثالثة المقررة في صفحة 63 بكتيّب منهج Bienvenu 2 مع الاستماع الصوتي لكل جملة، الترجمة العربية الفورية، والشرح التوضيحي لكل خيار.",
                    fontSize = 11.sp,
                    color = Slate700,
                    lineHeight = 17.sp
                )

                // Score Bar
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Slate200),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                tint = Amber600,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "النتيجة: $correctCount من ${situations.size}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Slate800
                            )
                        }

                        if (userAnswers.isNotEmpty()) {
                            Row(
                                modifier = Modifier.clickable {
                                    audioHelper.playClick()
                                    userAnswers.clear()
                                    Toast.makeText(context, "تمت إعادة تعيين الإجابات", Toast.LENGTH_SHORT).show()
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.Default.Refresh,
                                    contentDescription = "إعادة المحاولة",
                                    tint = Slate600,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "إعادة المحاولة",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate600
                                )
                            }
                        }
                    }
                }
            }
        }

        // Sub Tabs (حل المواقف / مفاتيح وقواعد المواقف)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tabs = listOf(
                Pair("📝 حل المواقف (ص 63)", 0),
                Pair("💡 مفاتيح وقواعد مواقف الصحة", 1)
            )

            tabs.forEach { (label, idx) ->
                val isSelected = activeSubTab == idx
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) Teal700 else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) Teal700 else Slate200),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            audioHelper.playClick()
                            activeSubTab = idx
                        }
                        .testTag("unit3_situations_subtab_$idx")
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(vertical = 9.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isSelected) Color.White else Slate700
                    )
                }
            }
        }

        when (activeSubTab) {
            0 -> Unit3SituationsQuizView(
                situations = situations,
                userAnswers = userAnswers,
                audioHelper = audioHelper,
                onSelectOption = { situationId, optionKey ->
                    val isFirstAnswer = userAnswers[situationId] == null
                    userAnswers[situationId] = optionKey
                    val situation = situations.find { it.id == situationId }
                    if (isFirstAnswer && situation?.correctKey == optionKey) {
                        onScoreEarned(5)
                    }
                },
                onReset = {
                    userAnswers.clear()
                    Toast.makeText(context, "تمت إعادة ضبط إجابات المواقف بنجاح!", Toast.LENGTH_SHORT).show()
                }
            )
            1 -> Unit3SituationsRulesGuideView(audioHelper = audioHelper)
        }
    }
}

// -------------------------------------------------------------
// VIEW 1: قائمة المواقف مع الخيارات التفاعلية والتفسير
// -------------------------------------------------------------
@Composable
private fun Unit3SituationsQuizView(
    situations: List<Unit3SituationItem>,
    userAnswers: Map<Int, String>,
    audioHelper: AudioHelper,
    onSelectOption: (Int, String) -> Unit,
    onReset: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        situations.forEach { item ->
            val chosenKey = userAnswers[item.id]
            val isAnswered = chosenKey != null
            val isCorrect = chosenKey == item.correctKey

            var showTranslation by remember { mutableStateOf(false) }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        !isAnswered -> Color.White
                        isCorrect -> Emerald50
                        else -> Rose50
                    }
                ),
                border = BorderStroke(
                    width = if (isAnswered) 1.5.dp else 1.dp,
                    color = when {
                        !isAnswered -> Slate200
                        isCorrect -> Emerald600
                        else -> Rose600
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Header row: Badge + Question Prompt
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
                                shape = RoundedCornerShape(6.dp),
                                color = if (item.isOfficialBookletPage63) Amber100 else Slate100
                            ) {
                                Text(
                                    text = if (item.isOfficialBookletPage63) "⭐ ص 63 بالكتيّب" else "سؤال تدريبي",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (item.isOfficialBookletPage63) Amber900 else Slate700,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            TranslateIconButton(
                                isTranslated = showTranslation,
                                onClick = { showTranslation = !showTranslation },
                                contentDescription = "ترجمة الموقف"
                            )

                            IconButton(
                                onClick = { audioHelper.speak(item.frenchPrompt) },
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Teal50, CircleShape)
                            ) {
                                Icon(
                                    Icons.Default.PlayArrow,
                                    contentDescription = "استماع للموقف",
                                    tint = Teal700,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    // Prompt text
                    Text(
                        text = item.frenchPrompt,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900,
                        lineHeight = 20.sp
                    )

                    // Arabic translation banner
                    AnimatedVisibility(visible = showTranslation) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Teal50,
                            border = BorderStroke(1.dp, Teal100),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💬 الترجمة: ${item.arabicPrompt}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Teal700,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    // Options list (a, b, c)
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        val shuffledIndices = remember(item.id) { item.options.indices.shuffled() }
                        shuffledIndices.forEach { optIdx ->
                            val opt = item.options[optIdx]
                            val isThisSelected = chosenKey == opt.key
                            val isThisCorrect = opt.key == item.correctKey

                            val optionBg = when {
                                !isAnswered -> Color.White
                                isThisSelected && isThisCorrect -> Emerald100
                                isThisSelected && !isThisCorrect -> Rose100
                                !isThisSelected && isThisCorrect -> Emerald50
                                else -> Color.White
                            }

                            val optionBorder = when {
                                !isAnswered -> Slate200
                                isThisSelected && isThisCorrect -> Emerald600
                                isThisSelected && !isThisCorrect -> Rose600
                                !isThisSelected && isThisCorrect -> Emerald600
                                else -> Slate200
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = optionBg,
                                border = BorderStroke(1.dp, optionBorder),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (!isAnswered) {
                                            audioHelper.playClick()
                                            onSelectOption(item.id, opt.key)
                                        }
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Surface(
                                            shape = CircleShape,
                                            color = when {
                                                isThisSelected && isThisCorrect -> Emerald600
                                                isThisSelected && !isThisCorrect -> Rose600
                                                !isThisSelected && isAnswered && isThisCorrect -> Emerald600
                                                else -> Slate100
                                            },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Box(contentAlignment = Alignment.Center) {
                                                Text(
                                                    text = opt.key,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Black,
                                                    color = if (isAnswered && (isThisSelected || isThisCorrect)) Color.White else Slate700
                                                )
                                            }
                                        }

                                        Column {
                                            Text(
                                                text = opt.frenchText,
                                                fontSize = 13.sp,
                                                fontWeight = if (isThisSelected) FontWeight.Black else FontWeight.Bold,
                                                color = Slate900
                                            )
                                            if (showTranslation || isAnswered) {
                                                Text(
                                                    text = opt.arabicText,
                                                    fontSize = 10.sp,
                                                    color = Slate600
                                                )
                                            }
                                        }
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        if (isAnswered && isThisCorrect) {
                                            Icon(
                                                Icons.Default.Check,
                                                contentDescription = "صحيح",
                                                tint = Emerald600,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        } else if (isAnswered && isThisSelected && !isThisCorrect) {
                                            Icon(
                                                Icons.Default.Close,
                                                contentDescription = "خطأ",
                                                tint = Rose600,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }

                                        IconButton(
                                            onClick = { audioHelper.speak(opt.frenchText) },
                                            modifier = Modifier.size(26.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.PlayArrow,
                                                contentDescription = "استماع للخيار",
                                                tint = Slate500,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Feedback and explanation after answering
                    if (isAnswered) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isCorrect) Emerald50 else Rose50,
                            border = BorderStroke(1.dp, if (isCorrect) Emerald300 else Rose200),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        if (isCorrect) Icons.Default.Check else Icons.Default.Info,
                                        contentDescription = null,
                                        tint = if (isCorrect) Emerald700 else Rose700,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = if (isCorrect) "إجابة صحيحة وممتازة! (+5 نقاط) 🎉" else "إجابة غير صحيحة، انتبه للتفسير: 💡",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 11.sp,
                                        color = if (isCorrect) Emerald900 else Rose800
                                    )
                                }
                                Text(
                                    text = item.explanation,
                                    fontSize = 11.sp,
                                    color = Slate800,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// VIEW 2: مفاتيح وقواعد حل مواقف الصحة والمستشفى
// -------------------------------------------------------------
@Composable
private fun Unit3SituationsRulesGuideView(audioHelper: AudioHelper) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Golden Formula Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Indigo50),
            border = BorderStroke(1.5.dp, Indigo600.copy(alpha = 0.35f))
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "🔑 القاعدة الذهبية لحل سؤال المواقف (Formule Magique) :",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )
                Text(
                    text = "انتبه دائماً لبداية الجملة ونهايتها لمعرفة هل المطلوب «سؤال» أم «إجابة»:",
                    fontSize = 11.sp,
                    color = Slate700
                )

                RuleFormulaRow(
                    start = "Tu demandes ...",
                    end = "... tu dis",
                    meaning = "أنت تسأل ... أنت تقول",
                    result = "سؤال ❓",
                    color = Indigo700
                )
                RuleFormulaRow(
                    start = "Tu demandes ...",
                    end = "... il dit",
                    meaning = "أنت تسأل ... هو يقول",
                    result = "إجابة 💬",
                    color = Emerald700
                )
                RuleFormulaRow(
                    start = "Ton ami te demande ...",
                    end = "... tu dis",
                    meaning = "صديقك يسألك ... أنت تقول",
                    result = "إجابة 💬",
                    color = Emerald700
                )
                RuleFormulaRow(
                    start = "Le médecin donne un conseil ...",
                    end = "... il dit",
                    meaning = "الطبيب ينصح ... هو يقول",
                    result = "نصيحة طبية 🩺",
                    color = Amber700
                )
            }
        }

        // Medical Roles Guide
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Slate200)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "🏥 مهام الفريق الطبي وسيارات الإسعاف (ص 59-63) :",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Teal700
                )

                MedicalRoleItem(
                    role = "L'ambulancier (المسعف)",
                    action = "Il transporte les personnes malades ou blessées à l'hôpital.",
                    actionAr = "ينقل الأشخاص المرضى أو المصابين إلى المستشفى.",
                    audioHelper = audioHelper
                )

                MedicalRoleItem(
                    role = "Le médecin (الطبيب)",
                    action = "Il examine les malades, fait des ordonnances et donne des conseils.",
                    actionAr = "يفحص المرضى، يكتب الروشتات ويقدم النصائح الطبية.",
                    audioHelper = audioHelper
                )

                MedicalRoleItem(
                    role = "L'infirmière (الممرضة)",
                    action = "Elle aide le médecin, donne les piqûres et prend la température.",
                    actionAr = "تساعد الطبيب، تعطي الحقن وتقيس درجة الحرارة.",
                    audioHelper = audioHelper
                )
            }
        }

        // Doctor's advices (Conseils du médecin)
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Amber50),
            border = BorderStroke(1.dp, Amber400)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "💡 نصائح الطبيب للمريض (Conseils du médecin) :",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Amber900
                )

                val advices = listOf(
                    Pair("Repose-toi bien !", "استرح جيداً!"),
                    Pair("Prends tes médicaments à l'heure !", "تناول أدويتك في موعدها!"),
                    Pair("Fais une radiographie !", "قم بعمل أشعة!"),
                    Pair("Ne t'inquiète pas !", "لا تقلق!")
                )

                advices.forEach { (fr, ar) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("🩺", fontSize = 12.sp)
                            Column {
                                Text(fr, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate900)
                                Text(ar, fontSize = 10.sp, color = Slate600)
                            }
                        }
                        IconButton(
                            onClick = { audioHelper.speak(fr) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Amber800, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RuleFormulaRow(
    start: String,
    end: String,
    meaning: String,
    result: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$start ... $end",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = meaning,
                    fontSize = 10.sp,
                    color = Slate600
                )
            }
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = color.copy(alpha = 0.15f)
            ) {
                Text(
                    text = result,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    color = color,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun MedicalRoleItem(
    role: String,
    action: String,
    actionAr: String,
    audioHelper: AudioHelper
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(role, fontSize = 12.sp, fontWeight = FontWeight.Black, color = Teal700)
                Text(action, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Slate900)
                Text(actionAr, fontSize = 10.sp, color = Slate600)
            }
            IconButton(
                onClick = { audioHelper.speak("$role. $action") },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Teal700, modifier = Modifier.size(18.dp))
            }
        }
    }
}

// -------------------------------------------------------------
// قائمة المواقف (4 مواقف نصاً من صفحة 63 + مواقف إثرائية للوحدة)
// -------------------------------------------------------------
private fun getUnit3SituationsList(): List<Unit3SituationItem> = listOf(
    // 1. الموقف الأول من صفحة 63
    Unit3SituationItem(
        id = 1,
        isOfficialBookletPage63 = true,
        frenchPrompt = "1. Tu as très mal au bras, le médecin te dit :",
        arabicPrompt = "تشعر بألم شديد في ذراعك، ماذا يقول لك الطبيب؟",
        options = listOf(
            Unit3SituationOption("a", "fais une radiographie.", "قم بعمل أشعة."),
            Unit3SituationOption("b", "fais un plat de salade !", "اصنع طبق سلطة!"),
            Unit3SituationOption("c", "fais tes devoirs !", "اعمل واجباتك!")
        ),
        correctKey = "a",
        explanation = "عندما يكون هناك ألم شديد أو كسر في الذراع، يطلب الطبيب عمل أشعة: fais une radiographie."
    ),

    // 2. الموقف الثاني من صفحة 63
    Unit3SituationItem(
        id = 2,
        isOfficialBookletPage63 = true,
        frenchPrompt = "2. Ton père te demande pourquoi tu vas aller à l'hôpital, tu lui dis :",
        arabicPrompt = "والدك يسألك لماذا ستذهب إلى المستشفى، فماذا تقول له؟",
        options = listOf(
            Unit3SituationOption("a", "pour acheter des mouchoirs en papier.", "لكي أشتري مناديل ورقية."),
            Unit3SituationOption("b", "pour visiter mon ami malade.", "لكي أزور صديقي المريض."),
            Unit3SituationOption("c", "pour jouer avec mes amis.", "لكي ألعب مع أصدقائي.")
        ),
        correctKey = "b",
        explanation = "الذهاب إلى المستشفى يكون لعيادة وزيارة مريض: pour visiter mon ami malade."
    ),

    // 3. الموقف الثالث من صفحة 63
    Unit3SituationItem(
        id = 3,
        isOfficialBookletPage63 = true,
        frenchPrompt = "3. Le médecin donne un conseil à un malade, le médecin lui dit :",
        arabicPrompt = "الطبيب يقدم نصيحة لمريض، فماذا يقول له؟",
        options = listOf(
            Unit3SituationOption("a", "Repose-toi bien !", "استرح جيداً!"),
            Unit3SituationOption("b", "Étudie bien !", "ذاكر جيداً!"),
            Unit3SituationOption("c", "Fais attention en classe !", "انتبه في الفصل!")
        ),
        correctKey = "a",
        explanation = "نصيحة الطبيب للمريض هي أخذ قسط من الراحة والتعافي: Repose-toi bien !"
    ),

    // 4. الموقف الرابع من صفحة 63
    Unit3SituationItem(
        id = 4,
        isOfficialBookletPage63 = true,
        frenchPrompt = "4. Ton ami te demande ce que l'ambulancier fait, tu dis :",
        arabicPrompt = "صديقك يسألك عما يفعله المسعف (سائق الإسعاف)، فماذا تقول؟",
        options = listOf(
            Unit3SituationOption("a", "Il examine les malades.", "يفحص المرضى."),
            Unit3SituationOption("b", "Il transporte les malades.", "ينقل المرضى."),
            Unit3SituationOption("c", "Il soigne les malades.", "يعالج المرضى.")
        ),
        correctKey = "b",
        explanation = "مهمة المسعف وسائق الإسعاف هي نقل المرضى والمصابين إلى المستشفى: Il transporte les malades."
    ),

    // 5. موقف إضافي للوحدة: السؤال عن مكان الألم
    Unit3SituationItem(
        id = 5,
        isOfficialBookletPage63 = false,
        frenchPrompt = "5. Tu demandes à ton ami où il a mal, tu dis :",
        arabicPrompt = "تسأل صديقك أين يشعر بالألم، فماذا تقول؟",
        options = listOf(
            Unit3SituationOption("a", "Où as-tu mal ?", "أين تشعر بالألم؟"),
            Unit3SituationOption("b", "Comment vas-tu ?", "كيف حالك؟"),
            Unit3SituationOption("c", "Quel est ton nom ?", "ما اسمك؟")
        ),
        correctKey = "a",
        explanation = "للسؤال عن مكان الألم في الجسم نستخدم: Où as-tu mal ?"
    ),

    // 6. موقف إضافي للوحدة: التعبير عن الألم
    Unit3SituationItem(
        id = 6,
        isOfficialBookletPage63 = false,
        frenchPrompt = "6. Tu exprimes une douleur physique, tu dis :",
        arabicPrompt = "تعبر عن ألم جسدي تشعر به، فماذا تقول؟",
        options = listOf(
            Unit3SituationOption("a", "J'ai mal au ventre.", "أشعر بألم في بطني."),
            Unit3SituationOption("b", "Je vais au restaurant.", "أنا أذهب إلى المطعم."),
            Unit3SituationOption("c", "J'aime le français.", "أنا أحب اللغة الفرنسية.")
        ),
        correctKey = "a",
        explanation = "للتعبير عن الألم نستخدم تعبير Avoir mal à + عضو الجسم: J'ai mal au ventre."
    ),

    // 7. موقف إضافي للوحدة: دور الطبيب
    Unit3SituationItem(
        id = 7,
        isOfficialBookletPage63 = false,
        frenchPrompt = "7. Ton ami te demande ce que fait le médecin, tu dis :",
        arabicPrompt = "صديقك يسألك ماذا يفعل الطبيب، فماذا تقول؟",
        options = listOf(
            Unit3SituationOption("a", "Il examine et soigne les malades.", "يفحص ويعالج المرضى."),
            Unit3SituationOption("b", "Il conduit l'autobus.", "يقود الأتوبيس."),
            Unit3SituationOption("c", "Il explique la leçon.", "يشرح الدرس.")
        ),
        correctKey = "a",
        explanation = "عمل الطبيب هو فحص وعلاج المرضى: Il examine et soigne les malades."
    ),

    // 8. موقف إضافي للوحدة: ألم في الأسنان
    Unit3SituationItem(
        id = 8,
        isOfficialBookletPage63 = false,
        frenchPrompt = "8. Tu as très mal aux dents, tu dis :",
        arabicPrompt = "تشعر بألم شديد في أسنانك، فماذا تقول؟",
        options = listOf(
            Unit3SituationOption("a", "Je vais chez le dentiste.", "أذهب إلى طبيب الأسنان."),
            Unit3SituationOption("b", "Je vais au stade.", "أذهب إلى الاستاد."),
            Unit3SituationOption("c", "Je mange des bonbons.", "أتناول حلوى.")
        ),
        correctKey = "a",
        explanation = "عند الشعور بألم في الأسنان نذهب إلى طبيب الأسنان: Je vais chez le dentiste."
    )
)
