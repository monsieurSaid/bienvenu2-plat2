package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.QuizQuestion
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber950
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald500
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Indigo600
import com.example.ui.theme.Indigo700
import com.example.ui.theme.Rose100
import com.example.ui.theme.Rose500
import com.example.ui.theme.Rose600
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import kotlin.random.Random

@Composable
fun UnitHeroBanner(
    title: String,
    subtitle: String,
    pageRange: String,
    instituteTag: String,
    gradientColors: List<Color>,
    speechText: String,
    audioHelper: AudioHelper,
    modifier: Modifier = Modifier,
    arabicSubtitle: String = ""
) {
    var showTranslation by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Brush.linearGradient(gradientColors))
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Amber400,
                            shape = CircleShape
                        ) {
                            Text(
                                text = pageRange,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = Amber950,
                                softWrap = false,
                                maxLines = 1
                            )
                        }
                        Text(
                            text = instituteTag,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.9f),
                            softWrap = false,
                            maxLines = 1
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (arabicSubtitle.isNotEmpty()) {
                            IconButton(
                                onClick = { showTranslation = !showTranslation },
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        if (showTranslation) Amber400 else Color.White.copy(alpha = 0.2f),
                                        CircleShape
                                    )
                                    .testTag("hero_translate_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Translate,
                                    contentDescription = "ترجمة الوحدة للعربية",
                                    tint = if (showTranslation) Amber950 else Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        val isHeroSpeaking = audioHelper.isSpeaking && audioHelper.currentSpeakingText == speechText

                        ElevatedButton(
                            onClick = {
                                if (isHeroSpeaking) {
                                    audioHelper.stopSpeech()
                                } else {
                                    audioHelper.speak(speechText)
                                }
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = if (isHeroSpeaking) Amber400 else Color.White,
                                contentColor = if (isHeroSpeaking) Amber950 else Slate900
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .defaultMinSize(minHeight = 44.dp)
                                .testTag("hero_listen_button")
                        ) {
                            Icon(
                                imageVector = if (isHeroSpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
                                contentDescription = if (isHeroSpeaking) "Arrêter la lecture" else "Écouter",
                                modifier = Modifier.size(18.dp),
                                tint = if (isHeroSpeaking) Amber950 else gradientColors.first()
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isHeroSpeaking) "Arrêter" else "Écouter",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isHeroSpeaking) Amber950 else Slate900
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 0.3.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.85f),
                    lineHeight = 16.sp
                )

                AnimatedVisibility(
                    visible = showTranslation && arabicSubtitle.isNotEmpty(),
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.2f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "🌐 $arabicSubtitle",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                lineHeight = 18.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AudioPlayButton(
    textToSpeak: String,
    audioHelper: AudioHelper,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Slate100,
    iconTint: Color = Slate800,
    size: Int = 40
) {
    val isSpeakingThis = audioHelper.isSpeaking && audioHelper.currentSpeakingText == textToSpeak

    val activeBg = if (isSpeakingThis) Indigo600 else backgroundColor
    val activeTint = if (isSpeakingThis) Color.White else iconTint
    val activeBorder = if (isSpeakingThis) Indigo700 else Slate200

    IconButton(
        onClick = {
            if (isSpeakingThis) {
                audioHelper.stopSpeech()
            } else {
                audioHelper.speak(textToSpeak)
            }
        },
        modifier = modifier
            .size(size.coerceAtLeast(44).dp)
            .background(activeBg, CircleShape)
            .border(1.dp, activeBorder, CircleShape)
            .testTag("audio_play_button_${textToSpeak.take(10).hashCode()}")
    ) {
        Icon(
            imageVector = if (isSpeakingThis) Icons.Default.Stop else Icons.Default.VolumeUp,
            contentDescription = if (isSpeakingThis) "Arrêter la lecture" else "Écouter la prononciation",
            tint = activeTint,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun TranslateIconButton(
    isTranslated: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = "ترجمة للعربية",
    size: Int = 40
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(size.coerceAtLeast(44).dp)
            .background(
                if (isTranslated) Emerald600 else Slate100,
                CircleShape
            )
            .border(
                1.dp,
                if (isTranslated) Emerald500 else Slate200,
                CircleShape
            )
            .testTag("translate_button_${contentDescription.hashCode()}")
    ) {
        Icon(
            imageVector = Icons.Default.Translate,
            contentDescription = contentDescription,
            tint = if (isTranslated) Color.White else Emerald600,
            modifier = Modifier.size(18.dp)
        )
    }
}

private fun cleanArabicTranslation(text: String): String {
    return text.replace(Regex("➔.*"), "")
        .replace(Regex("->.*"), "")
        .replace(Regex("\\[\\s*(صح|خطأ|Vrai|Faux|V|F)[^\\]]*\\]", RegexOption.IGNORE_CASE), "")
        .trim()
}

@Composable
fun ArabicTranslationBanner(
    translation: String,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val sanitizedText = remember(translation) { cleanArabicTranslation(translation) }
    AnimatedVisibility(
        visible = visible && sanitizedText.isNotEmpty(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF0FDF4),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC)),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Emerald600
                    ) {
                        Text(
                            text = "ترجمة 🌐",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Text(
                        text = sanitizedText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF166534),
                        lineHeight = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun InteractiveQuizCard(
    question: QuizQuestion,
    audioHelper: AudioHelper,
    onCorrectAnswer: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedOptionIndex by remember(question.id) { mutableStateOf<Int?>(null) }
    var hasAwardedPoint by remember(question.id) { mutableStateOf(false) }
    var showTranslation by remember(question.id) { mutableStateOf(false) }

    val shuffledIndices = remember(question.id, question.options) {
        val isVraiFaux = question.options.size == 2 &&
                question.options.any { it.contains("Vrai", ignoreCase = true) } &&
                question.options.any { it.contains("Faux", ignoreCase = true) }
        if (isVraiFaux) {
            val vraiIdx = question.options.indexOfFirst { it.contains("Vrai", ignoreCase = true) }
            val fauxIdx = question.options.indexOfFirst { it.contains("Faux", ignoreCase = true) }
            if (vraiIdx >= 0 && fauxIdx >= 0) listOf(vraiIdx, fauxIdx) else question.options.indices.toList()
        } else {
            question.options.indices.shuffled()
        }
    }

    val hasAlphaPrefix = remember(question.id, question.options) {
        question.options.isNotEmpty() && question.options.all { it.matches(Regex("^[a-dA-D]\\)\\s+.*")) }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Slate200)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question.question,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (question.arabicTranslation.isNotEmpty()) {
                        TranslateIconButton(
                            isTranslated = showTranslation,
                            onClick = { showTranslation = !showTranslation },
                            contentDescription = "ترجمة سؤال ${question.id}",
                            size = 40
                        )
                    }

                    if (question.pageReference.isNotEmpty()) {
                        Surface(
                            color = Amber50,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Amber400.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = question.pageReference,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber950,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            // Arabic Translation Helper
            ArabicTranslationBanner(
                translation = question.arabicTranslation,
                visible = showTranslation
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                shuffledIndices.forEachIndexed { displayPos, origIndex ->
                    val rawOptionText = question.options[origIndex]
                    val optionText = if (hasAlphaPrefix) {
                        "${'a' + displayPos}) " + rawOptionText.replace(Regex("^[a-dA-D]\\)\\s+"), "")
                    } else {
                        rawOptionText
                    }
                    val isSelected = selectedOptionIndex == origIndex
                    val isCorrect = origIndex == question.correctIndex
                    val isChecked = selectedOptionIndex != null

                    val (bgColor, borderColor, textColor) = when {
                        !isChecked -> Triple(Slate50, Slate200, Slate800)
                        isSelected && isCorrect -> Triple(Emerald500, Emerald600, Color.White)
                        isSelected && !isCorrect -> Triple(Rose100, Rose500, Rose600)
                        isChecked && isCorrect -> Triple(Emerald100, Emerald500, Emerald600)
                        else -> Triple(Slate50, Slate200, Slate600)
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = bgColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                            .clickable(enabled = selectedOptionIndex == null) {
                                selectedOptionIndex = origIndex
                                if (isCorrect) {
                                    audioHelper.playSuccessChime()
                                    if (!hasAwardedPoint) {
                                        hasAwardedPoint = true
                                        onCorrectAnswer()
                                    }
                                } else {
                                    audioHelper.playErrorBuzz()
                                }
                            }
                            .testTag("quiz_option_${question.id}_$displayPos")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = optionText,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected || (isChecked && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                                color = textColor,
                                modifier = Modifier.weight(1f)
                            )

                            if (isChecked && isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = if (isSelected) Color.White else Emerald600,
                                    modifier = Modifier.size(20.dp)
                                )
                            } else if (isSelected && !isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Incorrect",
                                    tint = Rose600,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = selectedOptionIndex != null && question.explanation.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    val isCorrect = selectedOptionIndex == question.correctIndex
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isCorrect) Emerald100 else Rose100,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (isCorrect) "💡 Bravo ! ${question.explanation}" else "❌ Attention : ${question.explanation}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isCorrect) Emerald600 else Rose600,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConfettiOverlay(
    active: Boolean,
    modifier: Modifier = Modifier
) {
    if (!active) return

    val particles = remember {
        List(40) {
            ConfettiParticle(
                x = Random.nextFloat(),
                y = -0.1f - Random.nextFloat() * 0.3f,
                speed = 0.5f + Random.nextFloat() * 0.7f,
                size = 8f + Random.nextFloat() * 10f,
                color = when (Random.nextInt(5)) {
                    0 -> Color(0xFFFBBF24) // Gold
                    1 -> Color(0xFF60A5FA) // Blue
                    2 -> Color(0xFF34D399) // Emerald
                    3 -> Color(0xFFF472B6) // Pink
                    else -> Color(0xFFA78BFA) // Violet
                }
            )
        }
    }

    val progress = remember { Animatable(0f) }

    LaunchedEffect(active) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2500, easing = LinearEasing)
        )
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val t = progress.value

        particles.forEach { p ->
            val curY = (p.y + p.speed * t) % 1.2f
            val curX = (p.x + kotlin.math.sin(t * 8f + p.x * 10f) * 0.05f) * w
            val py = curY * h
            drawCircle(
                color = p.color.copy(alpha = (1f - t * 0.5f).coerceIn(0f, 1f)),
                radius = p.size,
                center = Offset(curX, py)
            )
        }
    }
}

private data class ConfettiParticle(
    val x: Float,
    val y: Float,
    val speed: Float,
    val size: Float,
    val color: Color
)
