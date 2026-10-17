package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.RevisionGrammarExercise
import com.example.data.RevisionGrammarItem
import com.example.data.RevisionGrammaireData
import com.example.ui.theme.Amber100
import com.example.ui.theme.Amber200
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
import com.example.ui.theme.Violet100
import com.example.ui.theme.Violet50
import com.example.ui.theme.Violet600
import com.example.ui.theme.Violet700
import com.example.ui.theme.Violet800
import com.example.ui.theme.Violet900

@Composable
fun RevisionGrammaireSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val exercises = remember { RevisionGrammaireData.allExercises }
    var selectedExerciseIndex by remember { mutableIntStateOf(0) }
    val currentExercise = exercises[selectedExerciseIndex]

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Exercise Selector Chips (7 Exercises from pages 72 to 77)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            exercises.forEachIndexed { idx, ex ->
                val isSelected = selectedExerciseIndex == idx
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) Indigo700 else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) Indigo700 else Slate200),
                    modifier = Modifier
                        .clickable {
                            audioHelper.playClick()
                            selectedExerciseIndex = idx
                        }
                        .testTag("grammar_ex_selector_$idx")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = when (ex.id) {
                                1 -> "🚫 1. Négation"
                                2 -> "📍 2. Articles"
                                3 -> "🔄 3. Pronoms"
                                4 -> "⏳ 4. Conjugaison"
                                5 -> "🎯 5. Choisis"
                                6 -> "🎒 6. Possessifs"
                                else -> "✍️ 7. Corrige"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else Indigo700
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isSelected) Indigo900 else Slate100
                        ) {
                            Text(
                                text = ex.pageNumber,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Amber400 else Slate600,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }

        // Selected Exercise View
        ExerciseContentCard(
            exercise = currentExercise,
            audioHelper = audioHelper,
            onScoreEarned = onScoreEarned
        )
    }
}

@Composable
private fun ExerciseContentCard(
    exercise: RevisionGrammarExercise,
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    val context = LocalContext.current
    var showRule by remember(exercise.id) { mutableStateOf(true) }
    var globalRevealAnswers by remember(exercise.id) { mutableStateOf(false) }

    // Map: Item ID -> Selected Option Index (or -1)
    val selectedOptions = remember(exercise.id) { mutableStateMapOf<String, Int>() }
    // Set of revealed item IDs
    val revealedItems = remember(exercise.id) { mutableStateMapOf<String, Boolean>() }
    // Set of scored item IDs to prevent repeated scoring
    val scoredItems = remember(exercise.id) { mutableStateMapOf<String, Boolean>() }

    // Header Card
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Slate200)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                        color = Indigo50,
                        border = BorderStroke(1.dp, Indigo200)
                    ) {
                        Text(
                            text = exercise.pageNumber,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = "${exercise.items.size} جمل تدريبية",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate500
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    // Audio button for instruction
                    Surface(
                        shape = CircleShape,
                        color = Indigo50,
                        border = BorderStroke(1.dp, Indigo200),
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                audioHelper.speak(exercise.instructionFr)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "استماع لرأس السؤال",
                                tint = Indigo700,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Reset button
                    Surface(
                        shape = CircleShape,
                        color = Slate100,
                        border = BorderStroke(1.dp, Slate200),
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                audioHelper.playClick()
                                selectedOptions.clear()
                                revealedItems.clear()
                                scoredItems.clear()
                                globalRevealAnswers = false
                                Toast.makeText(context, "تمت إعادة ضبط التمرين 🔄", Toast.LENGTH_SHORT).show()
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "إعادة ضبط",
                                tint = Slate700,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Text(
                text = exercise.titleFr,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                color = Slate900
            )

            Text(
                text = exercise.titleAr,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Indigo700
            )

            Text(
                text = exercise.instructionAr,
                fontSize = 12.sp,
                color = Slate600
            )

            // Rule Card (Collapsible)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Amber50),
                border = BorderStroke(1.dp, Amber200),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showRule = !showRule }
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Amber800, modifier = Modifier.size(16.dp))
                            Text(
                                text = "قاعدة وتلخيص نحوي سريع (Règle)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber900
                            )
                        }
                        Text(
                            text = if (showRule) "إخفاء ▲" else "عرض ▼",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Amber800
                        )
                    }

                    AnimatedVisibility(visible = showRule) {
                        Text(
                            text = exercise.ruleSummaryAr,
                            fontSize = 12.sp,
                            color = Slate800,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Global Show/Hide answers toggle button
            OutlinedButton(
                onClick = {
                    audioHelper.playClick()
                    globalRevealAnswers = !globalRevealAnswers
                    if (globalRevealAnswers) {
                        exercise.items.forEach { item ->
                            revealedItems[item.id] = true
                        }
                    } else {
                        revealedItems.clear()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, if (globalRevealAnswers) Rose200 else Indigo200),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (globalRevealAnswers) Rose50 else Indigo50
                )
            ) {
                Icon(
                    if (globalRevealAnswers) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    tint = if (globalRevealAnswers) Rose700 else Indigo700,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (globalRevealAnswers) "إخفاء كافة الحلول النموذجية" else "إظهار كافة الحلول النموذجية والشرح",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (globalRevealAnswers) Rose700 else Indigo700
                )
            }
        }
    }

    // Questions List
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        exercise.items.forEachIndexed { itemIndex, item ->
            val userChoice = selectedOptions[item.id] ?: -1
            val isRevealed = globalRevealAnswers || (revealedItems[item.id] == true)

            GrammarItemCard(
                index = itemIndex + 1,
                item = item,
                userChoice = userChoice,
                isRevealed = isRevealed,
                audioHelper = audioHelper,
                onSelectOption = { chosenIdx ->
                    selectedOptions[item.id] = chosenIdx
                    if (item.correctIndex != -1 && chosenIdx == item.correctIndex) {
                        if (scoredItems[item.id] != true) {
                            scoredItems[item.id] = true
                            audioHelper.playClick()
                            onScoreEarned(5)
                            Toast.makeText(context, "إجابة صحيحة! أحسنت 🌟 (+5 نقاط)", Toast.LENGTH_SHORT).show()
                        } else {
                            audioHelper.playClick()
                        }
                    } else {
                        audioHelper.playClick()
                        Toast.makeText(context, "إجابة غير صحيحة، راجع القاعدة وحاول ثانية!", Toast.LENGTH_SHORT).show()
                    }
                },
                onToggleReveal = {
                    audioHelper.playClick()
                    val cur = revealedItems[item.id] == true
                    revealedItems[item.id] = !cur
                }
            )
        }
    }
}

@Composable
private fun GrammarItemCard(
    index: Int,
    item: RevisionGrammarItem,
    userChoice: Int,
    isRevealed: Boolean,
    audioHelper: AudioHelper,
    onSelectOption: (Int) -> Unit,
    onToggleReveal: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(
            1.dp,
            when {
                userChoice != -1 && userChoice == item.correctIndex -> Emerald300
                userChoice != -1 && userChoice != item.correctIndex -> Rose200
                isRevealed -> Indigo200
                else -> Slate200
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .testTag("grammar_item_$index")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Top Row: Number Badge & Audio Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Indigo50,
                    border = BorderStroke(1.dp, Indigo200)
                ) {
                    Text(
                        text = "جملة #$index",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Indigo700,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                IconButton(
                    onClick = {
                        val toSpeak = if (item.fullTransformedSentenceFr.isNotEmpty() && isRevealed) {
                            item.fullTransformedSentenceFr
                        } else {
                            item.sentenceFr
                        }
                        audioHelper.speak(toSpeak)
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "استماع للجملة",
                        tint = Indigo700,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Original French sentence
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (item.underlinedPart.isNotEmpty()) {
                    // Underlined highlight representation
                    val parts = item.sentenceFr.split(item.underlinedPart)
                    if (parts.size >= 2) {
                        Text(
                            text = parts[0],
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Amber100
                        ) {
                            Text(
                                text = item.underlinedPart,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = Amber900,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                        Text(
                            text = parts.drop(1).joinToString(item.underlinedPart),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                    } else {
                        Text(
                            text = item.sentenceFr,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                    }
                } else {
                    Text(
                        text = item.sentenceFr,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                }
            }

            // Arabic Translation of the sentence
            Text(
                text = "💬 ${item.sentenceAr}",
                fontSize = 12.sp,
                color = Slate600
            )

            // Multiple Choice Options (if available)
            if (item.options.isNotEmpty()) {
                val shuffledIndices = remember(item.id, item.options) {
                    item.options.indices.shuffled()
                }
                Text(
                    text = "اختر الإجابة الصحيحة:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Indigo900,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    shuffledIndices.forEachIndexed { displayPos, optIdx ->
                        val optText = item.options[optIdx]
                        val isChosen = userChoice == optIdx
                        val isOptionCorrect = optIdx == item.correctIndex

                        val bgColor = when {
                            isChosen && isOptionCorrect -> Emerald100
                            isChosen && !isOptionCorrect -> Rose100
                            isRevealed && isOptionCorrect -> Emerald50
                            else -> Slate50
                        }

                        val borderColor = when {
                            isChosen && isOptionCorrect -> Emerald600
                            isChosen && !isOptionCorrect -> Rose600
                            isRevealed && isOptionCorrect -> Emerald600
                            else -> Slate300
                        }

                        val textColor = when {
                            isChosen && isOptionCorrect -> Emerald900
                            isChosen && !isOptionCorrect -> Rose800
                            isRevealed && isOptionCorrect -> Emerald800
                            else -> Slate800
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = bgColor,
                            border = BorderStroke(1.2.dp, borderColor),
                            modifier = Modifier
                                .clickable { onSelectOption(optIdx) }
                                .testTag("grammar_opt_${index}_$displayPos")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = optText,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black,
                                    color = textColor
                                )
                                if (isChosen && isOptionCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Emerald700, modifier = Modifier.size(14.dp))
                                } else if (isChosen && !isOptionCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = null, tint = Rose700, modifier = Modifier.size(14.dp))
                                }
                            }
                        }
                    }
                }
            }

            // Reveal Model Answer Button & Box
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isRevealed) Indigo50 else Slate100,
                    border = BorderStroke(1.dp, if (isRevealed) Indigo200 else Slate200),
                    modifier = Modifier.clickable { onToggleReveal() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            if (isRevealed) Icons.Default.VisibilityOff else Icons.Default.HelpOutline,
                            contentDescription = null,
                            tint = if (isRevealed) Indigo700 else Slate700,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = if (isRevealed) "إخفاء الحل والشرح" else "عرض الحل والشرح",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isRevealed) Indigo700 else Slate700
                        )
                    }
                }
            }

            // Explanation & Model Answer Card
            AnimatedVisibility(visible = isRevealed) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Emerald50,
                    border = BorderStroke(1.dp, Emerald300),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "✅ الإجابة النموذجية:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Emerald900
                            )

                            IconButton(
                                onClick = {
                                    val speech = if (item.fullTransformedSentenceFr.isNotEmpty()) {
                                        item.fullTransformedSentenceFr
                                    } else {
                                        item.answerFr
                                    }
                                    audioHelper.speak(speech)
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.PlayArrow,
                                    contentDescription = "استماع للإجابة",
                                    tint = Emerald700,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        if (item.fullTransformedSentenceFr.isNotEmpty()) {
                            Text(
                                text = "➔ ${item.fullTransformedSentenceFr}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = Slate900
                            )
                        } else {
                            Text(
                                text = "➔ ${item.answerFr}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = Slate900
                            )
                        }

                        if (item.fullTransformedSentenceAr.isNotEmpty()) {
                            Text(
                                text = "💬 ${item.fullTransformedSentenceAr}",
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color.White,
                            border = BorderStroke(1.dp, Emerald100),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text("💡", fontSize = 12.sp)
                                Text(
                                    text = item.explanationAr,
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
