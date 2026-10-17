package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.ui.theme.Amber100
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber600
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber900
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Emerald700
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
import com.example.ui.theme.Rose50
import com.example.ui.theme.Rose600
import com.example.ui.theme.Rose700
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.Teal100
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal700

data class SituationItem(
    val id: Int,
    val frenchPrompt: String,
    val arabicPrompt: String,
    val options: List<SituationOption>,
    val correctKey: String,
    val explanation: String
)

data class SituationOption(
    val key: String, // "a", "b", "c"
    val frenchText: String,
    val arabicText: String
)

@Composable
fun Unit2SituationsSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val context = LocalContext.current
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: التدريب التفاعلي, 1: مفاتيح وملاحظات الحل
    val userAnswers = remember { mutableStateMapOf<Int, String>() }

    val situations = remember {
        listOf(
            SituationItem(
                id = 1,
                frenchPrompt = "1. Tu veux manger, tu dis :",
                arabicPrompt = "تريد أن تأكل، فماذا تقول؟",
                options = listOf(
                    SituationOption("a", "je vais au club.", "أذهب إلى النادي."),
                    SituationOption("b", "Mon frère préfère le poisson", "أخي يفضل السمك."),
                    SituationOption("c", "j'ai faim.", "أنا جائع.")
                ),
                correctKey = "c",
                explanation = "عندما تريد أن تأكل تعبر عن الجوع قائلاً: J'ai faim (أنا جائع)."
            ),
            SituationItem(
                id = 2,
                frenchPrompt = "2. Avant de prendre un repas, le garçon te dit :",
                arabicPrompt = "قبل تناول الوجبة، ماذا يقول لك النادل (الجرسون)؟",
                options = listOf(
                    SituationOption("a", "Qu'est-ce que vous voulez comme dessert?", "ماذا تريد كحلوى؟"),
                    SituationOption("b", "je prends de la glace.", "أنا أتناول الآيس كريم."),
                    SituationOption("c", "Bon appétit !", "شهية طيبة! / بالهناء والشفاء!")
                ),
                correctKey = "c",
                explanation = "قبل بدء الوجبة يتمنى لك الجرسون شهية طيبة: Bon appétit !"
            ),
            SituationItem(
                id = 3,
                frenchPrompt = "3. Tu demandes au garçon l'addition, tu dis :",
                arabicPrompt = "تطلب الحساب (الفاتورة) من النادل، فماذا تقول؟",
                options = listOf(
                    SituationOption("a", "l'addition, s.v.p !", "الحساب، من فضلك!"),
                    SituationOption("b", "Que voulez-vous", "ماذا تريد؟"),
                    SituationOption("c", "Vous voulez autre chose?", "هل تريد شيئاً آخر؟")
                ),
                correctKey = "a",
                explanation = "لطلب الفاتورة تقول للنادل بتهذيب: L'addition, s.v.p !"
            ),
            SituationItem(
                id = 4,
                frenchPrompt = "4. Tu demandes à ton ami le nombre des repas qu'il prend, il te dit :",
                arabicPrompt = "تسأل صديقك عن عدد الوجبات التي يتناولها، فماذا يقول لك (هو يجيب)؟",
                options = listOf(
                    SituationOption("a", "Tu prends trois repas?", "هل تتناول 3 وجبات؟"),
                    SituationOption("b", "Combien de repas prends-tu?", "كم وجبة تتناول؟"),
                    SituationOption("c", "Je prends trois repas par jour.", "أتناول ثلاث وجبات في اليوم.")
                ),
                correctKey = "c",
                explanation = "انتبه لصيغة الموقف: (il te dit هو يقول لك)، إذن المطلوب إجابته هو: Je prends trois repas par jour."
            ),
            SituationItem(
                id = 5,
                frenchPrompt = "5. Au restaurant, tu demandes le menu au garçon, tu dis :",
                arabicPrompt = "في المطعم، تطلب قائمة الطعام (المنيو) من النادل، فماذا تقول؟",
                options = listOf(
                    SituationOption("a", "Apportez-moi le menu, s.v.p!", "أحضر لي قائمة الطعام، من فضلك!"),
                    SituationOption("b", "Voilà le menu !", "ها هي قائمة الطعام!"),
                    SituationOption("c", "Que voulez-vous", "ماذا تريد؟")
                ),
                correctKey = "a",
                explanation = "لطلب المنيو تقول للنادل: Apportez-moi le menu, s.v.p !"
            ),
            SituationItem(
                id = 6,
                frenchPrompt = "6. Tu demandes à ton ami pourquoi il va au marché, il dit :",
                arabicPrompt = "تسأل صديقك لماذا يذهب إلى السوق، فماذا يقول؟",
                options = listOf(
                    SituationOption("a", "Pour acheter une maison.", "لكي أشتري منزلاً."),
                    SituationOption("b", "Pour acheter des légumes et des fruits.", "لكي أشتري خضروات وفواكه."),
                    SituationOption("c", "Pour acheter un médicament.", "لكي أشتري دواءً.")
                ),
                correctKey = "b",
                explanation = "الذهاب إلى السوق (au marché) يكون لشراء الخضار والفاكهة: Pour acheter des légumes et des fruits."
            ),
            SituationItem(
                id = 7,
                frenchPrompt = "7. Ton ami te demande combien de repas tu prends par jour, tu dis :",
                arabicPrompt = "صديقك يسألك كم وجبة تتناول في اليوم، فماذا تقول له؟",
                options = listOf(
                    SituationOption("a", "j'étudie 3 heures par jour.", "أنا أذاكر 3 ساعات يومياً."),
                    SituationOption("b", "Ce repas n'est pas bon.", "هذه الوجبة ليست جيدة."),
                    SituationOption("c", "je prends 3 repas chaque jour.", "أنا أتناول 3 وجبات كل يوم.")
                ),
                correctKey = "c",
                explanation = "صديقك يسألك وأنت ترد: Je prends 3 repas chaque jour."
            )
        )
    }

    val correctCount = userAnswers.count { (id, chosenKey) ->
        situations.find { it.id == id }?.correctKey == chosenKey
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Header Card
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Indigo50),
            border = BorderStroke(1.5.dp, Indigo600.copy(alpha = 0.35f))
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
                        color = Indigo600
                    ) {
                        Text(
                            text = "Situations • Unité 2",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Indigo200)
                    ) {
                        Text(
                            text = "📖 صفحة 54 (ختام الوحدة)",
                            color = Indigo900,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Text(
                    text = "سؤال المواقف الرسمي: Choisis le bon groupe",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                Text(
                    text = "المواقف السبعة الرسمية المقررة بالكامل في كتيّب منهج Bienvenu 2 ص 54، مع خياراتها ونطقها الصوتي وتعليلاتها النموذجية لضمان الدرجة النهائية بإذن الله.",
                    fontSize = 12.sp,
                    color = Slate700,
                    lineHeight = 18.sp
                )

                // Score bar
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Indigo100),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("النتيجة الحالية: ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate700)
                            Text("$correctCount / ${situations.size}", fontSize = 14.sp, fontWeight = FontWeight.Black, color = Indigo700)
                        }

                        if (correctCount == situations.size) {
                            Surface(shape = RoundedCornerShape(8.dp), color = Emerald600) {
                                Text("ممتاز! 100% 🏆", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                            }
                        } else {
                            Text("+5 درجات لكل إجابة صحيحة", fontSize = 10.sp, color = Amber700, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Sub Tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tabs = listOf(
                Pair("📝 حل المواقف (7 تمارين)", 0),
                Pair("💡 مفاتيح وقواعد المواقف", 1)
            )

            tabs.forEach { (label, idx) ->
                val isSelected = activeSubTab == idx
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) Indigo600 else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) Indigo600 else Slate200),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { activeSubTab = idx }
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
            0 -> SituationsQuizView(
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
            1 -> SituationsRulesGuideView(audioHelper = audioHelper)
        }

        // Completion Banner for Unit 2
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Amber50),
            border = BorderStroke(1.dp, Amber400)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = Amber600,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🏁", fontSize = 18.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "اكتملت الوحدة الثانية بالكامل بنجاح!",
                        fontWeight = FontWeight.Black,
                        fontSize = 13.sp,
                        color = Amber900
                    )
                    Text(
                        text = "النص (ص35-37)، الكلمات (ص38)، الإنتاج والتحدث (ص39-40)، الوجبات (ص41-42)، أدوات التجزئة (ص43-45)، الاستفهام (ص46-50)، بنك الكلمات (ص51-53)، والمواقف (ص54).",
                        fontSize = 10.sp,
                        color = Slate700,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------
// VIEW 1: قائمة المواقف مع الخيارات التفاعلية والتفسير
// -------------------------------------------------------------
@Composable
private fun SituationsQuizView(
    situations: List<SituationItem>,
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

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        !isAnswered -> Color.White
                        isCorrect -> Emerald50.copy(alpha = 0.5f)
                        else -> Rose50.copy(alpha = 0.5f)
                    }
                ),
                border = BorderStroke(
                    1.5.dp,
                    when {
                        !isAnswered -> Slate200
                        isCorrect -> Emerald600
                        else -> Rose600
                    }
                )
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Header: Situation prompt + Voice Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.frenchPrompt,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black,
                                color = Slate900
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.arabicPrompt,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Indigo700
                            )
                        }

                        IconButton(
                            onClick = { audioHelper.speak(item.frenchPrompt) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "استمع للموقف",
                                tint = Indigo600,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Options a, b, c
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        val shuffledIndices = remember(item.id) { item.options.indices.shuffled() }
                        shuffledIndices.forEach { optIdx ->
                            val opt = item.options[optIdx]
                            val isThisSelected = chosenKey == opt.key
                            val isThisTheCorrectAnswer = opt.key == item.correctKey

                            val cardBg = when {
                                !isAnswered -> if (isThisSelected) Indigo100 else Slate50
                                isThisSelected && isCorrect -> Emerald600
                                isThisSelected && !isCorrect -> Rose600
                                isThisTheCorrectAnswer && !isCorrect -> Emerald100
                                else -> Slate50
                            }

                            val borderCol = when {
                                !isAnswered -> if (isThisSelected) Indigo600 else Slate200
                                isThisSelected && isCorrect -> Emerald700
                                isThisSelected && !isCorrect -> Rose700
                                isThisTheCorrectAnswer && !isCorrect -> Emerald600
                                else -> Slate200
                            }

                            val titleColor = when {
                                !isAnswered -> Slate900
                                isThisSelected -> Color.White
                                isThisTheCorrectAnswer && !isCorrect -> Emerald900
                                else -> Slate800
                            }

                            val descColor = when {
                                !isAnswered -> Slate600
                                isThisSelected -> Color.White.copy(alpha = 0.9f)
                                isThisTheCorrectAnswer && !isCorrect -> Emerald700
                                else -> Slate600
                            }

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = cardBg,
                                border = BorderStroke(1.dp, borderCol),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSelectOption(item.id, opt.key)
                                        audioHelper.speak(opt.frenchText)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = when {
                                            isThisSelected && isCorrect -> Color.White
                                            isThisSelected && !isCorrect -> Color.White
                                            isThisTheCorrectAnswer && isAnswered -> Emerald600
                                            else -> Slate200
                                        },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = opt.key + ")",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Black,
                                                color = when {
                                                    isThisSelected && isCorrect -> Emerald700
                                                    isThisSelected && !isCorrect -> Rose700
                                                    isThisTheCorrectAnswer && isAnswered -> Color.White
                                                    else -> Slate700
                                                }
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = opt.frenchText,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Black,
                                            color = titleColor
                                        )
                                        Text(
                                            text = opt.arabicText,
                                            fontSize = 11.sp,
                                            color = descColor
                                        )
                                    }

                                    IconButton(
                                        onClick = { audioHelper.speak(opt.frenchText) },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.PlayArrow,
                                            contentDescription = null,
                                            tint = if (isThisSelected) Color.White else Indigo600,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Explanation Box if Answered
                    AnimatedVisibility(visible = isAnswered) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isCorrect) Emerald100.copy(alpha = 0.6f) else Rose100.copy(alpha = 0.6f),
                            border = BorderStroke(1.dp, if (isCorrect) Emerald600 else Rose600),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (isCorrect) Emerald700 else Rose700,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = if (isCorrect) "إجابة صحيحة وممتازة! 🎉 (+5 درجات)" else "إجابة غير صحيحة، لاحظ التعليل التالي:",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 12.sp,
                                        color = if (isCorrect) Emerald900 else Rose700
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
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

        // Reset Button
        OutlinedButton(
            onClick = onReset,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("إعادة حل المواقف من البداية 🔄", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

// -------------------------------------------------------------
// VIEW 2: مفاتيح حل المواقف في الامتحان (Clés des situations)
// -------------------------------------------------------------
@Composable
private fun SituationsRulesGuideView(audioHelper: AudioHelper) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Tip 1: Qui parle et à qui
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Indigo100)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💡", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "القاعدة الذهبية: تحديد المتحدث والمخاطب",
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp,
                        color = Indigo900
                    )
                }

                Text(
                    text = "قبل اختيار الإجابة في سؤال المواقف، حدد بداية الجملة ونهايتها بدقة لمعرفة هل المطلوب «سؤال» أم «إجابة»:",
                    fontSize = 12.sp,
                    color = Slate700
                )

                val rules = listOf(
                    Triple("Tu demandes ... tu dis :", "أنت تسأل ... أنت تقول", "المطلوب صيغة «سؤال» أو «طلب» ❓"),
                    Triple("Tu demandes ... il dit :", "أنت تسأل ... هو يقول", "المطلوب «إجابة» من الشخص الآخر 💬"),
                    Triple("Ton ami te demande ... tu dis :", "صديقك يسألك ... أنت تقول", "المطلوب «إجابتك أنت» 💬"),
                    Triple("Le garçon te dit :", "الجرسون (النادل) يقول لك", "ما يقوله النادل للزبون (مثل: Bon appétit) 🍽️")
                )

                rules.forEach { (fr, ar, meaning) ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Slate50,
                        border = BorderStroke(1.dp, Slate200),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(fr, fontWeight = FontWeight.Black, fontSize = 12.sp, color = Indigo700)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(ar, fontSize = 11.sp, color = Slate600)
                                Text(meaning, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Orange600)
                            }
                        }
                    }
                }
            }
        }

        // Summary table of the 7 official situations
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Slate200)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "📋 جدول ملخص مواقف الوحدة الثانية (صفحة 54)",
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                    color = Slate900
                )

                val quickTable = listOf(
                    Pair("Tu veux manger", "J'ai faim."),
                    Pair("Avant le repas (le garçon)", "Bon appétit !"),
                    Pair("Demander l'addition au garçon", "L'addition, s.v.p !"),
                    Pair("Le nombre des repas (il te dit)", "Je prends trois repas par jour."),
                    Pair("Demander le menu au garçon", "Apportez-moi le menu, s.v.p !"),
                    Pair("Pourquoi aller au marché", "Pour acheter des légumes et des fruits."),
                    Pair("Combien de repas tu prends (tu dis)", "Je prends 3 repas chaque jour.")
                )

                quickTable.forEachIndexed { i, (sit, ans) ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (i % 2 == 0) Indigo50.copy(alpha = 0.6f) else Color.White,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${i + 1}. $sit",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate800,
                                modifier = Modifier.weight(1.2f)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "➔ $ans",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Emerald700,
                                    textAlign = TextAlign.End
                                )
                                IconButton(
                                    onClick = { audioHelper.speak(ans) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Emerald600, modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
