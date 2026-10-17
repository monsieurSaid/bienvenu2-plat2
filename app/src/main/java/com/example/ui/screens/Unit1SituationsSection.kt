package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.FrenchCourseData
import com.example.data.SituationItem
import com.example.ui.components.AudioPlayButton
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber950
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald500
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Indigo100
import com.example.ui.theme.Indigo50
import com.example.ui.theme.Indigo600
import com.example.ui.theme.Indigo700
import com.example.ui.theme.Indigo900
import com.example.ui.theme.Orange100
import com.example.ui.theme.Orange50
import com.example.ui.theme.Orange600
import com.example.ui.theme.Rose100
import com.example.ui.theme.Rose50
import com.example.ui.theme.Rose500
import com.example.ui.theme.Rose600
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
import com.example.ui.theme.Teal600
import com.example.ui.theme.Violet100
import com.example.ui.theme.Violet50
import com.example.ui.theme.Violet600
import com.example.ui.theme.Violet700

@Composable
fun Unit1SituationsSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    var selectedSituationsMode by remember { mutableIntStateOf(0) }
    val modes = listOf(
        "📝 كتيّب المعهد (ص 15)",
        "💬 محاكاة المحادثات (Chat)",
        "🔑 القواعد الذهبية للحل"
    )

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Header Banner for Page 15 Booklet
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = "🎭 Situations de communication",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "مواقف كتيّب منهج Bienvenu 2 (ص 15) • شرح وتدريبات ذكية",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }
                    Surface(
                        color = Violet700,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "p. 15 • 6 مواقف",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            softWrap = false,
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Mode Selector Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    modes.forEachIndexed { index, title ->
                        val isSelected = selectedSituationsMode == index
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Violet700 else Slate100,
                            border = BorderStroke(1.dp, if (isSelected) Violet700 else Slate200),
                            modifier = Modifier.clickable { selectedSituationsMode = index }
                        ) {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium,
                                color = if (isSelected) Color.White else Slate800,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        // Sub-mode view
        when (selectedSituationsMode) {
            0 -> SituationsOfficialExercisesMode(audioHelper = audioHelper, onScoreEarned = onScoreEarned)
            1 -> SituationsChatSimulationMode(audioHelper = audioHelper, onScoreEarned = onScoreEarned)
            2 -> SituationsGoldenRulesMode(audioHelper = audioHelper)
        }
    }
}

// -----------------------------------------------------------------------------
// 1. 📝 تمارين كتيّب المعهد (ص 15) - Situations: 1- Choisis:
// -----------------------------------------------------------------------------
@Composable
private fun SituationsOfficialExercisesMode(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val situations = FrenchCourseData.unit1Situations
    val userAnswers = remember { mutableStateMapOf<String, Int>() }

    // Official Title Banner exactly as page 15
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "1- Choisis la bonne réponse :",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "اختر الإجابة الصحيحة للمواقف الآتية (نص الكتيّب ص 15):",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate700
                )
            }

            AudioPlayButton(
                textToSpeak = "Situations. Choisis la bonne réponse.",
                audioHelper = audioHelper,
                backgroundColor = Violet50,
                iconTint = Violet700,
                size = 34
            )
        }
    }

    // List of 6 Situations
    situations.forEach { situation ->
        val selectedOption = userAnswers[situation.id]
        val isAnswered = selectedOption != null

        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(
                if (isAnswered) 1.5.dp else 1.dp,
                if (isAnswered) Violet600.copy(alpha = 0.5f) else Slate200
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Header: Situation Number + Category Badge + Audio
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
                            shape = CircleShape,
                            color = Violet700
                        ) {
                            Text(
                                text = "${situation.number}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Violet50,
                            border = BorderStroke(1.dp, Violet100)
                        ) {
                            Text(
                                text = situation.categoryAr,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Violet700,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    AudioPlayButton(
                        textToSpeak = situation.promptFr,
                        audioHelper = audioHelper,
                        backgroundColor = Slate100,
                        size = 32
                    )
                }

                // Situation Prompt (French + Arabic)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Slate50, RoundedCornerShape(12.dp))
                        .border(1.dp, Slate200, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = situation.promptFr,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "📌 ${situation.promptAr}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Indigo700,
                        lineHeight = 18.sp
                    )
                }

                // Options (a, b, c)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    val letters = listOf("a", "b", "c")
                    val shuffledIndices = remember(situation.id) { situation.options.indices.shuffled() }
                    shuffledIndices.forEachIndexed { displayPos, optIndex ->
                        val option = situation.options[optIndex]
                        val letter = letters.getOrElse(displayPos) { "${displayPos + 1}" }
                        val isSelected = selectedOption == optIndex
                        val isThisCorrect = option.isCorrect

                        val (containerColor, borderColor, textColor) = when {
                            isAnswered && isThisCorrect -> Triple(Emerald100, Emerald500, Emerald600)
                            isAnswered && isSelected && !isThisCorrect -> Triple(Rose100, Rose500, Rose600)
                            isSelected -> Triple(Violet100, Violet600, Violet700)
                            else -> Triple(Color.White, Slate200, Slate800)
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = containerColor,
                            border = BorderStroke(if (isSelected || (isAnswered && isThisCorrect)) 2.dp else 1.dp, borderColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (!isAnswered) {
                                        userAnswers[situation.id] = optIndex
                                        if (option.isCorrect) {
                                            onScoreEarned(1)
                                            audioHelper.speak(option.textFr)
                                        }
                                    } else {
                                        // Allow listening to option
                                        audioHelper.speak(option.textFr)
                                    }
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = if (isAnswered && isThisCorrect) Emerald600 else if (isAnswered && isSelected) Rose600 else Slate200
                                    ) {
                                        Text(
                                            text = "$letter)",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Black,
                                            color = if (isAnswered && (isThisCorrect || isSelected)) Color.White else Slate800,
                                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = option.textFr,
                                            fontSize = 13.sp,
                                            fontWeight = if (isAnswered && isThisCorrect) FontWeight.Black else FontWeight.Bold,
                                            color = textColor
                                        )
                                        if (isAnswered) {
                                            Text(
                                                text = option.textAr,
                                                fontSize = 11.sp,
                                                color = Slate600
                                            )
                                        }
                                    }
                                }

                                if (isAnswered) {
                                    if (isThisCorrect) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = Emerald600, modifier = Modifier.size(20.dp))
                                    } else if (isSelected) {
                                        Icon(Icons.Default.Close, contentDescription = null, tint = Rose600, modifier = Modifier.size(20.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                // Explanation & Golden Rule (revealed after answering)
                AnimatedVisibility(visible = isAnswered) {
                    val answeredOption = userAnswers[situation.id]?.let { situation.options[it] }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Violet50, RoundedCornerShape(12.dp))
                            .border(1.dp, Violet100, RoundedCornerShape(12.dp))
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Violet700, modifier = Modifier.size(16.dp))
                            Text(
                                text = "توضيح وسر الموقف :",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = Violet700
                            )
                        }

                        answeredOption?.let {
                            Text(
                                text = it.explanation,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (it.isCorrect) Emerald600 else Rose600
                            )
                        }

                        Text(
                            text = "💡 القاعدة الذهبية: ${situation.goldenRule}",
                            fontSize = 11.sp,
                            color = Slate700,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// 2. 💬 محاكاة المحادثات الحقيقية (Chat Simulation / Role-Play)
// -----------------------------------------------------------------------------
@Composable
private fun SituationsChatSimulationMode(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val situations = FrenchCourseData.unit1Situations
    var currentScenarioIndex by remember { mutableIntStateOf(0) }
    var selectedResponseIndex by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }

    val currentSituation = situations[currentScenarioIndex]
    val correctOptionIndex = currentSituation.options.indexOfFirst { it.isCorrect }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Chat Header
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Indigo900),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Amber400,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "👦", fontSize = 20.sp)
                        }
                    }
                    Column {
                        Text(
                            text = currentSituation.rolePlaySpeaker,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Text(
                            text = "متصل الآن • المحادثة ${currentScenarioIndex + 1} من ${situations.size}",
                            fontSize = 11.sp,
                            color = Indigo100
                        )
                    }
                }

                Surface(
                    color = Violet600,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = currentSituation.categoryAr,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        softWrap = false,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Chat Bubble Arena
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Slate50),
            border = BorderStroke(1.dp, Slate200),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Incoming Message Bubble from Friend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Surface(
                        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Slate200),
                        shadowElevation = 1.dp,
                        modifier = Modifier.fillMaxWidth(0.85f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = currentSituation.rolePlaySpeaker,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Indigo700
                                )
                                AudioPlayButton(
                                    textToSpeak = currentSituation.rolePlayMessage.ifEmpty { currentSituation.promptFr },
                                    audioHelper = audioHelper,
                                    backgroundColor = Violet50,
                                    size = 28
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentSituation.rolePlayMessage.ifEmpty { currentSituation.promptFr },
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = currentSituation.promptAr,
                                fontSize = 11.sp,
                                color = Slate600
                            )
                        }
                    }
                }

                // Outgoing Message Bubble (User's chosen reply)
                if (isSubmitted && selectedResponseIndex != null) {
                    val chosen = currentSituation.options[selectedResponseIndex!!]
                    val isCorrect = chosen.isCorrect

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Surface(
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
                            color = if (isCorrect) Emerald500 else Rose500,
                            shadowElevation = 2.dp,
                            modifier = Modifier.fillMaxWidth(0.85f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "أنت (ردك)",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color.White
                                    )
                                    Text(
                                        text = if (isCorrect) "✓ رد رائع ومتقن!" else "✗ رد غير مناسب للموقف",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = chosen.textFr,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = chosen.textAr,
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick Reply Choices (3 Buttons)
        Text(
            text = "👇 اختر أفضل رد للمحادثة :",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Slate700
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val shuffledIndices = remember(currentSituation.id) { currentSituation.options.indices.shuffled() }
            shuffledIndices.forEach { optIndex ->
                val option = currentSituation.options[optIndex]
                val isSelected = selectedResponseIndex == optIndex
                val isCorrect = option.isCorrect

                val (btnColor, textColor) = when {
                    isSubmitted && isCorrect -> Emerald600 to Color.White
                    isSubmitted && isSelected && !isCorrect -> Rose600 to Color.White
                    else -> Color.White to Slate800
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = btnColor,
                    border = BorderStroke(
                        if (isSelected) 2.dp else 1.dp,
                        if (isSubmitted && isCorrect) Emerald600 else if (isSubmitted && isSelected) Rose600 else Slate200
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !isSubmitted) {
                            selectedResponseIndex = optIndex
                            isSubmitted = true
                            if (option.isCorrect) {
                                onScoreEarned(2)
                                audioHelper.speak(option.textFr)
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = option.textFr,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = textColor
                            )
                            Text(
                                text = option.textAr,
                                fontSize = 11.sp,
                                color = if (isSubmitted && (isCorrect || isSelected)) Color.White.copy(alpha = 0.9f) else Slate600
                            )
                        }

                        if (isSubmitted) {
                            if (isCorrect) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
            }
        }

        // Navigation to Next Scenario
        if (isSubmitted) {
            Button(
                onClick = {
                    selectedResponseIndex = null
                    isSubmitted = false
                    currentScenarioIndex = (currentScenarioIndex + 1) % situations.size
                },
                colors = ButtonDefaults.buttonColors(containerColor = Violet700),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (currentScenarioIndex < situations.size - 1) "المحادثة التالية ➔" else "إعادة المحادثات من البداية 🔄",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

// -----------------------------------------------------------------------------
// 3. 🔑 القواعد الذهبية لفك شفرة المواقف (Règles d'or & Astuces)
// -----------------------------------------------------------------------------
@Composable
private fun SituationsGoldenRulesMode(
    audioHelper: AudioHelper
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Golden Key 1: Who asks and who speaks?
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                            shape = CircleShape,
                            color = Amber400,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("🔑", fontSize = 16.sp)
                            }
                        }
                        Text(
                            text = "سر فك شفرة الفاعل والمطلوب",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                    }

                    AudioPlayButton(
                        textToSpeak = "Tu demandes, tu dis, question. Tu demandes, il dit, réponse.",
                        audioHelper = audioHelper,
                        backgroundColor = Violet50,
                        size = 30
                    )
                }

                Text(
                    text = "انتبه دائماً لبداية الموقف ونهايته لمعرفة هل تختار (سؤالاً ❓) أم (إجابة 💬):",
                    fontSize = 12.sp,
                    color = Slate700
                )

                // 3 Formula Pills
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    RuleFormulaRow(
                        start = "Tu demandes ...",
                        end = "... tu dis",
                        result = "سؤال ❓",
                        resultColor = Violet700,
                        bgColor = Violet50
                    )
                    RuleFormulaRow(
                        start = "Tu demandes ...",
                        end = "... il dit",
                        result = "إجابة 💬",
                        resultColor = Emerald600,
                        bgColor = Emerald100
                    )
                    RuleFormulaRow(
                        start = "Ton ami te demande ...",
                        end = "... tu dis",
                        result = "إجابة 💬",
                        resultColor = Indigo700,
                        bgColor = Indigo50
                    )
                }
            }
        }

        // Golden Key 2: Essential Vocabulary & Expressions Table
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "📚 قاموس التعبيرات السحرية لكل موقف (ص 15)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                // Category 1: Invitation
                ExpressionCategoryCard(
                    title = "1. توجيه الدعوة (L'invitation)",
                    icon = "✉️",
                    badgeColor = Violet700,
                    expressions = listOf(
                        "Je t'invite à la fête de mon anniversaire." to "أدعوك إلى حفلة عيد ميلادي.",
                        "Viens chez moi ce soir !" to "تعال إلى منزلي الليلة !"
                    ),
                    audioHelper = audioHelper
                )

                // Category 2: Acceptance
                ExpressionCategoryCard(
                    title = "2. قبول الدعوة (L'acceptation)",
                    icon = "✅",
                    badgeColor = Emerald600,
                    expressions = listOf(
                        "Avec plaisir !" to "بكل سرور !",
                        "D'accord !" to "موافق !",
                        "Pourquoi pas !" to "لِمَ لا !"
                    ),
                    audioHelper = audioHelper
                )

                // Category 3: Refusal / Apology
                ExpressionCategoryCard(
                    title = "3. رفض الدعوة والاعتذار (Le refus)",
                    icon = "❌",
                    badgeColor = Rose600,
                    expressions = listOf(
                        "Désolé, je ne peux pas venir." to "آسف، لا أستطيع المجيء.",
                        "Pardon, j'ai un examen." to "عذراً، لدي امتحان.",
                        "Désolé, j'ai un rendez-vous." to "آسف، لدي موعد."
                    ),
                    audioHelper = audioHelper
                )

                // Category 4: Congratulations
                ExpressionCategoryCard(
                    title = "4. التهنئة بعيد الميلاد (Les félicitations)",
                    icon = "🎉",
                    badgeColor = Orange600,
                    expressions = listOf(
                        "Bon anniversaire !" to "عيد ميلاد سعيد !",
                        "Joyeux anniversaire !" to "عيد ميلاد بهيج !"
                    ),
                    audioHelper = audioHelper
                )

                // Category 5: Activities & Gifts
                ExpressionCategoryCard(
                    title = "5. أنشطة الحفلة والهدايا (Fête & Cadeaux)",
                    icon = "🎂",
                    badgeColor = Teal600,
                    expressions = listOf(
                        "On va manger des gâteaux." to "سوف نأكل الجاتوه والحلويات.",
                        "Un bouquet de fleurs." to "باقة من الزهور الجميلة."
                    ),
                    audioHelper = audioHelper
                )
            }
        }
    }
}

@Composable
private fun RuleFormulaRow(
    start: String,
    end: String,
    result: String,
    resultColor: Color,
    bgColor: Color
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = bgColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = start, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate800)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = end, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate800)
            }
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = resultColor
            ) {
                Text(
                    text = result,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun ExpressionCategoryCard(
    title: String,
    icon: String,
    badgeColor: Color,
    expressions: List<Pair<String, String>>,
    audioHelper: AudioHelper
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = icon, fontSize = 15.sp)
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = badgeColor
                    )
                }
                AudioPlayButton(
                    textToSpeak = expressions.joinToString(". ") { it.first },
                    audioHelper = audioHelper,
                    backgroundColor = Color.White,
                    size = 28
                )
            }

            expressions.forEach { (fr, ar) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = fr,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = ar,
                            fontSize = 11.sp,
                            color = Slate600
                        )
                    }
                    AudioPlayButton(
                        textToSpeak = fr,
                        audioHelper = audioHelper,
                        backgroundColor = Slate100,
                        size = 24
                    )
                }
            }
        }
    }
}
