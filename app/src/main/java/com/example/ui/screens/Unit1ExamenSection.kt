package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.ui.components.AudioPlayButton
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Amber950
import com.example.ui.theme.Emerald100
import com.example.ui.theme.Emerald50
import com.example.ui.theme.Emerald500
import com.example.ui.theme.Emerald600
import com.example.ui.theme.Emerald700
import com.example.ui.theme.Indigo100
import com.example.ui.theme.Indigo50
import com.example.ui.theme.Indigo600
import com.example.ui.theme.Indigo700
import com.example.ui.theme.Indigo900
import com.example.ui.theme.Rose100
import com.example.ui.theme.Rose50
import com.example.ui.theme.Rose500
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
import com.example.ui.theme.Violet100
import com.example.ui.theme.Violet50
import com.example.ui.theme.Violet600
import com.example.ui.theme.Violet700

/**
 * Examen de Mi-Terme (Pages 32 - 33)
 * التبويب الأخير للوحدة الأولى: الامتحان الرسمي لنصف الفصل الدراسي الأول
 * كتيّب منهج Bienvenu 2 - إجمالي الدرجات: 20 درجة
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Unit1ExamenSection(
    audioHelper: AudioHelper,
    onScoreEarned: (Int) -> Unit
) {
    // Student info
    var studentName by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    var showModelAnswers by remember { mutableStateOf(false) }

    // Section 1: Document Questions State (Total: 8 marks)
    // A: Choisis (3 marks)
    val docChoisisAnswers = remember { mutableStateMapOf<Int, Int?>() }
    // B: Vrai ou Faux (3 marks)
    val docVraiFauxAnswers = remember { mutableStateMapOf<Int, Boolean?>() }
    // C: Questions directes (2 marks)
    val docOpenAnswers = remember { mutableStateMapOf<Int, Int?>() }

    // Section 2: Où vas-tu pour...? (1 mark: 0.5 each)
    val ouVasTuAnswers = remember { mutableStateMapOf<Int, Int?>() }

    // Section 3: Qui parle...? (1 mark: 0.5 each)
    val quiParleAnswers = remember { mutableStateMapOf<Int, String?>() }

    // Section 4: Grammaire (Total: 8 marks)
    // a) Présent (2 marks)
    val grammairePresentAnswers = remember { mutableStateMapOf<Int, Int?>() }
    // b) Fais comme indiqué (6 marks)
    val grammaireMiscAnswers = remember { mutableStateMapOf<Int, Int?>() }

    // Section 5: Situations (2 marks)
    val situationsAnswers = remember { mutableStateMapOf<Int, Int?>() }

    // Calculate total score out of 20
    val totalScore by remember {
        derivedStateOf {
            var score = 0.0

            // 1-A: (3 questions, 1 pt each)
            if (docChoisisAnswers[1] == 0) score += 1.0 // a) de Amal
            if (docChoisisAnswers[2] == 1) score += 1.0 // b) le soir
            if (docChoisisAnswers[3] == 2) score += 1.0 // c) au club

            // 1-B: (3 questions, 1 pt each)
            if (docVraiFauxAnswers[1] == true) score += 1.0  // Amal a 13 ans -> Vrai
            if (docVraiFauxAnswers[2] == false) score += 1.0 // 2eme secondaire -> Faux
            if (docVraiFauxAnswers[3] == true) score += 1.0  // grand-père photos -> Vrai

            // 1-C: (2 questions, 1 pt each)
            if (docOpenAnswers[1] == 0) score += 1.0 // Au club
            if (docOpenAnswers[2] == 0) score += 1.0 // Le grand-père

            // Section 2: (2 questions, 0.5 pt each -> 1 pt)
            if (ouVasTuAnswers[1] == 0) score += 0.5 // Au stade
            if (ouVasTuAnswers[2] == 0) score += 0.5 // Au cinéma

            // Section 3: (2 questions, 0.5 pt each -> 1 pt)
            if (quiParleAnswers[1] == "Le professeur") score += 0.5
            if (quiParleAnswers[2] == "Le malade") score += 0.5

            // Section 4-a: (2 questions, 1 pt each -> 2 pts)
            if (grammairePresentAnswers[1] == 0) score += 1.0 // regarde
            if (grammairePresentAnswers[2] == 0) score += 1.0 // choisis

            // Section 4-b: (6 questions, 1 pt each -> 6 pts)
            if (grammaireMiscAnswers[1] == 0) score += 1.0 // ses
            if (grammaireMiscAnswers[2] == 0) score += 1.0 // Nous ne jouons pas au club
            if (grammaireMiscAnswers[3] == 0) score += 1.0 // Qui
            if (grammaireMiscAnswers[4] == 0) score += 1.0 // Je joue au tennis
            if (grammaireMiscAnswers[5] == 0) score += 1.0 // Elle
            if (grammaireMiscAnswers[6] == 0) score += 1.0 // lui

            // Section 5: (2 questions, 1 pt each -> 2 pts)
            if (situationsAnswers[1] == 0) score += 1.0 // Avec plaisir
            if (situationsAnswers[2] == 2) score += 1.0 // On va manger des gâteaux

            score
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.testTag("unit1_examen_section")
    ) {
        // Exam Title & Student Header
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "📝 Examen de Mi-Terme",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "امتحان نصف الفصل الدراسي الأول (ص 32 - 33)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }

                    // Score Badge (20 Marks)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (totalScore >= 17) Emerald500 else if (totalScore >= 10) Violet700 else Slate800,
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "الدرجة",
                                fontSize = 10.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Text(
                                text = "${totalScore.toInt()} / 20",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }

                // Optional Student Name & Class input
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        placeholder = { Text("اسم الطالب...", fontSize = 11.sp, color = Slate500) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Slate50,
                            unfocusedContainerColor = Slate50,
                            focusedBorderColor = Violet600,
                            unfocusedBorderColor = Slate200
                        ),
                        modifier = Modifier.weight(1.2f)
                    )

                    OutlinedTextField(
                        value = studentClass,
                        onValueChange = { studentClass = it },
                        placeholder = { Text("الفصل...", fontSize = 11.sp, color = Slate500) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Slate50,
                            unfocusedContainerColor = Slate50,
                            focusedBorderColor = Violet600,
                            unfocusedBorderColor = Slate200
                        ),
                        modifier = Modifier.weight(0.8f)
                    )
                }

                // Control Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            showModelAnswers = !showModelAnswers
                            audioHelper.playClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (showModelAnswers) Amber400 else Violet50
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = if (showModelAnswers) "إخفاء نموذج الإجابة 📋" else "عرض نموذج الإجابة 📋",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (showModelAnswers) Amber950 else Violet700
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            docChoisisAnswers.clear()
                            docVraiFauxAnswers.clear()
                            docOpenAnswers.clear()
                            ouVasTuAnswers.clear()
                            quiParleAnswers.clear()
                            grammairePresentAnswers.clear()
                            grammaireMiscAnswers.clear()
                            situationsAnswers.clear()
                            showModelAnswers = false
                            audioHelper.playClick()
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("إعادة الامتحان 🔄", fontSize = 11.sp)
                    }
                }
            }
        }

        // =====================================================================
        // SECTION 1: COMPRÉHENSION DU DOCUMENT (8 MARKS)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                // Section Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "(1) Lis ce document puis réponds aux questions :",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "اقرأ الوثيقة التالية ثم أجب عن الأسئلة",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }

                    Surface(
                        color = Violet50,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "8 درجات",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Violet700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Document Content Box with Audio
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Violet50,
                    border = BorderStroke(1.dp, Violet100),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "📜 نص الوثيقة (Document) :",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Indigo900
                            )

                            AudioPlayButton(
                                textToSpeak = "C'est l'anniversaire de Amal. Elle a treize ans. Amal est en deuxième année préparatoire. Amal et ses amis sont au club. Elles chantent et dansent. Elles mangent des gâteaux. Le soir, on souffle les bougies. Le grand-père prend des photos. Amal a reçu des cadeaux de ses parents et de ses amis.",
                                audioHelper = audioHelper,
                                backgroundColor = Color.White,
                                size = 34
                            )
                        }

                        Text(
                            text = "« C'est l'anniversaire de Amal. Elle a treize ans. Amal est en deuxième année préparatoire. Amal et ses amis sont au club. Elles chantent et dansent. Elles mangent des gâteaux. Le soir, on souffle les bougies.\nLe grand-père prend des photos. Amal a reçu des cadeaux de ses parents et de ses amis. »",
                            fontSize = 13.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = Slate800
                        )

                        Surface(
                            color = Color.White,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "💡 Souffle les bougies = يطفئ الشمع",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber950,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // -------------------------------------------------------------
                // A) Choisis le bon groupe (3 Marks)
                // -------------------------------------------------------------
                Text(
                    text = "A) Choisis le bon groupe : (3 درجات)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                // Q1
                ExamenChoixQuestion(
                    number = "1",
                    prompt = "C'est l'anniversaire .............",
                    options = listOf("a) de Amal", "b) du grand-mère", "c) du père"),
                    correctIndex = 0,
                    selectedIndex = docChoisisAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        docChoisisAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // Q2
                ExamenChoixQuestion(
                    number = "2",
                    prompt = "On souffle les bougies .............",
                    options = listOf("a) le matin", "b) le soir", "c) l'aprés-midi"),
                    correctIndex = 1,
                    selectedIndex = docChoisisAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        docChoisisAnswers[2] = it
                        if (it == 1) onScoreEarned(1)
                    }
                )

                // Q3
                ExamenChoixQuestion(
                    number = "3",
                    prompt = "Amal et ses amis sont .............",
                    options = listOf("a) à la maison", "b) au stade", "c) au club"),
                    correctIndex = 2,
                    selectedIndex = docChoisisAnswers[3],
                    showModel = showModelAnswers,
                    onSelect = {
                        docChoisisAnswers[3] = it
                        if (it == 2) onScoreEarned(1)
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                // -------------------------------------------------------------
                // B) Mets (vrai) ou (faux) (3 Marks)
                // -------------------------------------------------------------
                Text(
                    text = "B) Mets (vrai) ou (faux) : (3 درجات)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                ExamenVraiFauxItem(
                    number = "1",
                    prompt = "Amal a treize ans.",
                    expected = true,
                    selected = docVraiFauxAnswers[1],
                    explanation = "صواب: ورد في الوثيقة «Elle a treize ans».",
                    showModel = showModelAnswers,
                    onSelect = {
                        docVraiFauxAnswers[1] = it
                        if (it == true) onScoreEarned(1)
                    }
                )

                ExamenVraiFauxItem(
                    number = "2",
                    prompt = "Amal est en deuxieme année secondaire.",
                    expected = false,
                    selected = docVraiFauxAnswers[2],
                    explanation = "خطأ: أمل في الصف الثاني الإعدادي «deuxième année préparatoire» وليس الثانوي.",
                    showModel = showModelAnswers,
                    onSelect = {
                        docVraiFauxAnswers[2] = it
                        if (it == false) onScoreEarned(1)
                    }
                )

                ExamenVraiFauxItem(
                    number = "3",
                    prompt = "Le grand-père prend des photos.",
                    expected = true,
                    selected = docVraiFauxAnswers[3],
                    explanation = "صواب: ورد في الوثيقة «Le grand-père prend des photos».",
                    showModel = showModelAnswers,
                    onSelect = {
                        docVraiFauxAnswers[3] = it
                        if (it == true) onScoreEarned(1)
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                // -------------------------------------------------------------
                // C) Réponds aux questions (2 Marks)
                // -------------------------------------------------------------
                Text(
                    text = "C) Réponds aux questions : (درجتان)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                ExamenOpenQuestion(
                    number = "1",
                    question = "Où sont Amal et ses amis ?",
                    options = listOf("Au club", "À l'école", "Au cinéma"),
                    correctIndex = 0,
                    selectedIndex = docOpenAnswers[1],
                    modelAnswer = "Elles sont au club. (في النادي)",
                    showModel = showModelAnswers,
                    onSelect = {
                        docOpenAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                ExamenOpenQuestion(
                    number = "2",
                    question = "Qui prend des photos ?",
                    options = listOf("Le grand-père", "Le père", "Amal"),
                    correctIndex = 0,
                    selectedIndex = docOpenAnswers[2],
                    modelAnswer = "Le grand-père prend des photos. (الجد)",
                    showModel = showModelAnswers,
                    onSelect = {
                        docOpenAnswers[2] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )
            }
        }

        // =====================================================================
        // SECTION 2: OÙ VAS-TU POUR.........? (1 MARK)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "(2) Où vas-tu pour.........? :",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "أين تذهب لكي...؟",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }
                    Surface(color = Violet50, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            text = "درجة واحدة",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Violet700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                ExamenChoixQuestion(
                    number = "1",
                    prompt = "1- Voir un match ➔",
                    options = listOf("Au stade", "Au cinéma", "Au restaurant"),
                    correctIndex = 0,
                    selectedIndex = ouVasTuAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        ouVasTuAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                ExamenChoixQuestion(
                    number = "2",
                    prompt = "2- Voir un film ➔",
                    options = listOf("Au cinéma", "Au club", "À la gare"),
                    correctIndex = 0,
                    selectedIndex = ouVasTuAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        ouVasTuAnswers[2] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )
            }
        }

        // =====================================================================
        // SECTION 3: QUI PARLE .........? (1 MARK)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "(3) Qui Parle .........? :",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "من المتحدث؟ (Le malade - le chauffeur - le professeur)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }
                    Surface(color = Violet50, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            text = "درجة واحدة",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Violet700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Bank of characters
                Surface(
                    color = Slate50,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Slate200),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "بنك الشخصيات: [ Le malade (المريض) • le chauffeur (السائق) • le professeur (المعلم) ]",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate700,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                // Q1
                ExamenQuiParleItem(
                    number = "1",
                    sentence = "« Va au tableau. »",
                    options = listOf("Le professeur", "Le malade", "Le chauffeur"),
                    correctPerson = "Le professeur",
                    selectedPerson = quiParleAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        quiParleAnswers[1] = it
                        if (it == "Le professeur") onScoreEarned(1)
                    }
                )

                // Q2
                ExamenQuiParleItem(
                    number = "2",
                    sentence = "« Docteur ! J'ai mal à l'estomac. »",
                    options = listOf("Le malade", "Le chauffeur", "Le professeur"),
                    correctPerson = "Le malade",
                    selectedPerson = quiParleAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        quiParleAnswers[2] = it
                        if (it == "Le malade") onScoreEarned(1)
                    }
                )
            }
        }

        // =====================================================================
        // SECTION 4: GRAMMAIRE (8 MARKS)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "(4) Grammaire :",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "القواعد النحوية (8 درجات)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }
                    Surface(color = Violet50, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            text = "8 درجات",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Violet700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Part a) Mets au présent (2 Marks)
                Text(
                    text = "a) Mets au présent : (درجتان)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                ExamenChoixQuestion(
                    number = "1",
                    prompt = "Je .............................. la télé. (regarder)",
                    options = listOf("regarde", "regardes", "regardez"),
                    correctIndex = 0,
                    selectedIndex = grammairePresentAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammairePresentAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                ExamenChoixQuestion(
                    number = "2",
                    prompt = "Tu .............................. une robe. (choisir)",
                    options = listOf("choisis", "choisit", "choisissez"),
                    correctIndex = 0,
                    selectedIndex = grammairePresentAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammairePresentAnswers[2] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Part b) Fais comme indiqué entre parenthèses (6 Marks)
                Text(
                    text = "b) Fais comme indiqué entre parenthèses : (6 درجات)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = Indigo900
                )

                // 1
                ExamenChoixQuestion(
                    number = "1",
                    prompt = "Elle aime ................... professeurs. [complète par un adj. possessif]",
                    options = listOf("ses", "son", "sa"),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 2
                ExamenChoixQuestion(
                    number = "2",
                    prompt = "Nous jouons au club. [Mets à la forme négative]",
                    options = listOf(
                        "Nous ne jouons pas au club.",
                        "Nous ne jouons au club.",
                        "Nous jouons pas au club."
                    ),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[2] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 3
                ExamenChoixQuestion(
                    number = "3",
                    prompt = ".................... explique la leçon ? - Le professeur explique la leçon.",
                    options = listOf("Qui", "Quand", "Comment"),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[3],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[3] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 4
                ExamenChoixQuestion(
                    number = "4",
                    prompt = "Jouer - au tennis [fais une phrase]",
                    options = listOf(
                        "Je joue au tennis.",
                        "Jouer tennis.",
                        "Tennis joue."
                    ),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[4],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[4] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 5
                ExamenChoixQuestion(
                    number = "5",
                    prompt = "Mona est Egyptienne. [Remplace par un pronom personnel]",
                    options = listOf(
                        "Elle est égyptienne.",
                        "Il est égyptien.",
                        "Elles sont égyptiennes."
                    ),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[5],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[5] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 6
                ExamenChoixQuestion(
                    number = "6",
                    prompt = "Je parle à mon ami. [Remplace par un pronom personnel]",
                    options = listOf(
                        "Je lui parle.",
                        "Je le parle.",
                        "Je leur parle."
                    ),
                    correctIndex = 0,
                    selectedIndex = grammaireMiscAnswers[6],
                    showModel = showModelAnswers,
                    onSelect = {
                        grammaireMiscAnswers[6] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )
            }
        }

        // =====================================================================
        // SECTION 5: SITUATIONS (2 MARKS)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.5.dp, Violet100)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "(5) Situations :",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Indigo900
                        )
                        Text(
                            text = "المواقف اليومية (درجتان)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violet700
                        )
                    }
                    Surface(color = Violet50, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            text = "درجتان",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Violet700,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // 1
                ExamenChoixQuestion(
                    number = "1",
                    prompt = "1. Ton ami t'invite à une fête, tu acceptes l'invitation, tu dis :",
                    options = listOf(
                        "a) Avec plaisir.",
                        "b) Désolé, je ne peux pas venir.",
                        "c) Pardon, j'ai un examen."
                    ),
                    correctIndex = 0,
                    selectedIndex = situationsAnswers[1],
                    showModel = showModelAnswers,
                    onSelect = {
                        situationsAnswers[1] = it
                        if (it == 0) onScoreEarned(1)
                    }
                )

                // 2
                ExamenChoixQuestion(
                    number = "2",
                    prompt = "2. Ton ami te demande ce qu'on fait à la fête, tu dis :",
                    options = listOf(
                        "a) On écrit des lettres.",
                        "b) On part à Alex.",
                        "c) On va manger des gâteaux."
                    ),
                    correctIndex = 2,
                    selectedIndex = situationsAnswers[2],
                    showModel = showModelAnswers,
                    onSelect = {
                        situationsAnswers[2] = it
                        if (it == 2) onScoreEarned(1)
                    }
                )
            }
        }

        // =====================================================================
        // RESULT BANNER & BONNE CHANCE (CONCLUSION)
        // =====================================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (totalScore >= 18) Emerald50 else Violet50
            ),
            border = BorderStroke(
                1.5.dp,
                if (totalScore >= 18) Emerald500 else Violet600
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (totalScore >= 18) "🌟 مبروك! نتيجة ممتازة" else "🎯 تقييم الامتحان الرسمي",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    color = if (totalScore >= 18) Emerald700 else Indigo900
                )

                Text(
                    text = "حصلت على: ${totalScore.toInt()} من 20 درجة",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = if (totalScore >= 18) Emerald700 else Violet700
                )

                if (studentName.isNotBlank()) {
                    Text(
                        text = "الطالب: $studentName ${if (studentClass.isNotBlank()) "($studentClass)" else ""}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate700
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "« Bonne Chance »",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Violet700
                )
                Text(
                    text = "بالتوفيق والنجاح لطلاب الصف الثاني الإعدادي • منهج Bienvenu 2",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Slate600
                )
            }
        }
    }
}

// -----------------------------------------------------------------------------
// Helper Question Components
// -----------------------------------------------------------------------------
@Composable
private fun ExamenChoixQuestion(
    number: String,
    prompt: String,
    options: List<String>,
    correctIndex: Int,
    selectedIndex: Int?,
    showModel: Boolean,
    onSelect: (Int) -> Unit
) {
    val shuffledIndices = remember(number, prompt, options) { options.indices.shuffled() }
    val hasAlphaPrefix = remember(number, prompt, options) {
        options.isNotEmpty() && options.all { it.matches(Regex("^[a-dA-D]\\)\\s+.*")) }
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "$number- $prompt",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Slate900
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            shuffledIndices.forEachIndexed { displayPos, origIndex ->
                val rawOptionText = options[origIndex]
                val optionText = if (hasAlphaPrefix) {
                    "${'a' + displayPos}) " + rawOptionText.replace(Regex("^[a-dA-D]\\)\\s+"), "")
                } else {
                    rawOptionText
                }
                val isSelected = selectedIndex == origIndex
                val isCorrect = origIndex == correctIndex

                val (bgColor, borderColor, textColor) = when {
                    showModel && isCorrect -> Triple(Emerald100, Emerald500, Emerald700)
                    isSelected && isCorrect -> Triple(Emerald100, Emerald500, Emerald700)
                    isSelected && !isCorrect -> Triple(Rose100, Rose500, Rose700)
                    else -> Triple(Slate50, Slate200, Slate800)
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = bgColor,
                    border = BorderStroke(if (isSelected || (showModel && isCorrect)) 2.dp else 1.dp, borderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(origIndex) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = optionText,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected || (showModel && isCorrect)) FontWeight.Black else FontWeight.Normal,
                            color = textColor
                        )

                        if (isSelected || showModel) {
                            if (isCorrect) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Emerald600, modifier = Modifier.size(16.dp))
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = Rose600, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExamenVraiFauxItem(
    number: String,
    prompt: String,
    expected: Boolean,
    selected: Boolean?,
    explanation: String,
    showModel: Boolean,
    onSelect: (Boolean) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "$number- $prompt",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // VRAI button
                val isVraiSelected = selected == true
                val isVraiCorrect = expected == true
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when {
                        showModel && isVraiCorrect -> Emerald100
                        isVraiSelected && isVraiCorrect -> Emerald100
                        isVraiSelected && !isVraiCorrect -> Rose100
                        else -> Color.White
                    },
                    border = BorderStroke(
                        if (isVraiSelected || (showModel && isVraiCorrect)) 2.dp else 1.dp,
                        if (isVraiSelected || (showModel && isVraiCorrect)) (if (isVraiCorrect) Emerald500 else Rose500) else Slate300
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelect(true) }
                ) {
                    Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Vrai (صح) ✔️",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isVraiSelected && !isVraiCorrect) Rose600 else Emerald700
                        )
                    }
                }

                // FAUX button
                val isFauxSelected = selected == false
                val isFauxCorrect = expected == false
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when {
                        showModel && isFauxCorrect -> Emerald100
                        isFauxSelected && isFauxCorrect -> Emerald100
                        isFauxSelected && !isFauxCorrect -> Rose100
                        else -> Color.White
                    },
                    border = BorderStroke(
                        if (isFauxSelected || (showModel && isFauxCorrect)) 2.dp else 1.dp,
                        if (isFauxSelected || (showModel && isFauxCorrect)) (if (isFauxCorrect) Emerald500 else Rose500) else Slate300
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelect(false) }
                ) {
                    Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Faux (خطأ) ❌",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isFauxSelected && !isFauxCorrect) Rose600 else Emerald700
                        )
                    }
                }
            }

            if (selected != null || showModel) {
                Text(
                    text = "💡 $explanation",
                    fontSize = 11.sp,
                    color = Slate600
                )
            }
        }
    }
}

@Composable
private fun ExamenOpenQuestion(
    number: String,
    question: String,
    options: List<String>,
    correctIndex: Int,
    selectedIndex: Int?,
    modelAnswer: String,
    showModel: Boolean,
    onSelect: (Int) -> Unit
) {
    val shuffledIndices = remember(number, question, options) { options.indices.shuffled() }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "$number- $question",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                shuffledIndices.forEach { origIndex ->
                    val option = options[origIndex]
                    val isSelected = selectedIndex == origIndex
                    val isCorrect = origIndex == correctIndex

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when {
                            showModel && isCorrect -> Emerald100
                            isSelected && isCorrect -> Emerald100
                            isSelected && !isCorrect -> Rose100
                            else -> Color.White
                        },
                        border = BorderStroke(
                            if (isSelected || (showModel && isCorrect)) 2.dp else 1.dp,
                            if (isSelected || (showModel && isCorrect)) (if (isCorrect) Emerald500 else Rose500) else Slate300
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSelect(origIndex) }
                    ) {
                        Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = option,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected && !isCorrect) Rose600 else Slate800
                            )
                        }
                    }
                }
            }

            if (selectedIndex != null || showModel) {
                Text(
                    text = "الإجابة النموذجية: $modelAnswer",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Emerald700
                )
            }
        }
    }
}

@Composable
private fun ExamenQuiParleItem(
    number: String,
    sentence: String,
    options: List<String>,
    correctPerson: String,
    selectedPerson: String?,
    showModel: Boolean,
    onSelect: (String) -> Unit
) {
    val shuffledOptions = remember(number, sentence, options) { options.shuffled() }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Slate50,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "$number- $sentence",
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Indigo900
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                shuffledOptions.forEach { person ->
                    val isSelected = selectedPerson == person
                    val isCorrect = person == correctPerson

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when {
                            showModel && isCorrect -> Emerald100
                            isSelected && isCorrect -> Emerald100
                            isSelected && !isCorrect -> Rose100
                            else -> Color.White
                        },
                        border = BorderStroke(
                            if (isSelected || (showModel && isCorrect)) 2.dp else 1.dp,
                            if (isSelected || (showModel && isCorrect)) (if (isCorrect) Emerald500 else Rose500) else Slate300
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSelect(person) }
                    ) {
                        Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = person,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected && !isCorrect) Rose600 else if (isSelected || (showModel && isCorrect)) Emerald700 else Slate800,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
