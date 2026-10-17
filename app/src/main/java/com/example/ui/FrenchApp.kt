package com.example.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioHelper
import com.example.data.AppNavModule
import com.example.ui.screens.ExamScreen
import com.example.ui.screens.GrammarScreen
import com.example.ui.screens.RevisionScreen
import com.example.ui.screens.Unit1Screen
import com.example.ui.screens.Unit2Screen
import com.example.ui.screens.Unit3Screen
import com.example.ui.screens.WelcomePortalScreen
import com.example.ui.theme.Amber400
import com.example.ui.theme.Amber50
import com.example.ui.theme.Indigo100
import com.example.ui.theme.Indigo600
import com.example.ui.theme.Indigo700
import com.example.ui.theme.Indigo900
import com.example.ui.theme.Rose600
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@Composable
fun FrenchApp(audioHelper: AudioHelper) {
    val context = LocalContext.current
    var currentModule by rememberSaveable { mutableStateOf(AppNavModule.PORTAL) }
    var grammarInitialTab by rememberSaveable { mutableStateOf(0) }
    var revisionInitialTab by rememberSaveable { mutableStateOf(0) }

    // Navigation back stack to support exact step-by-step reverse history
    val navHistory = remember { mutableStateListOf<AppNavModule>() }
    var showExitDialog by remember { mutableStateOf(false) }

    val navigateToModule: (AppNavModule) -> Unit = { targetModule ->
        audioHelper.stopSpeech()
        if (targetModule != currentModule) {
            navHistory.add(currentModule)
            if (targetModule != AppNavModule.GRAMMAR) grammarInitialTab = 0
            if (targetModule != AppNavModule.REVISION) revisionInitialTab = 0
            currentModule = targetModule
        }
    }

    val navigateToHome: () -> Unit = {
        audioHelper.stopSpeech()
        if (currentModule != AppNavModule.PORTAL) {
            navHistory.add(currentModule)
            currentModule = AppNavModule.PORTAL
        }
    }

    val navigateBack: () -> Unit = {
        audioHelper.stopSpeech()
        if (navHistory.isNotEmpty()) {
            val previous = navHistory.removeAt(navHistory.size - 1)
            currentModule = previous
        } else if (currentModule != AppNavModule.PORTAL) {
            currentModule = AppNavModule.PORTAL
        } else {
            showExitDialog = true
        }
    }

    val navigateToIntroExercises = {
        audioHelper.stopSpeech()
        grammarInitialTab = 4
        navigateToModule(AppNavModule.GRAMMAR)
    }

    // Intercept system back button: step back through history or prompt exit at Portal
    BackHandler(enabled = true) {
        navigateBack()
    }

    // Exit Confirmation Dialog when back is pressed on the Home / Portal screen
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("🚪", fontSize = 22.sp)
                    Text(
                        text = "الخروج من التطبيق",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = Indigo900
                    )
                }
            },
            text = {
                Text(
                    text = "هل أنت متأكد أنك تريد مغادرة التطبيق الآن؟",
                    fontSize = 14.sp,
                    color = Slate700,
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        (context as? Activity)?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Rose600,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("dialog_confirm_exit_button")
                ) {
                    Text(
                        text = "نعم، خروج",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showExitDialog = false },
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Slate300),
                    modifier = Modifier.testTag("dialog_cancel_exit_button")
                ) {
                    Text(
                        text = "إلغاء ومتابعة",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Slate700
                    )
                }
            },
            modifier = Modifier.testTag("exit_confirmation_dialog")
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Slate50,
        topBar = {
            FrenchAppTopBar(
                currentModule = currentModule,
                canNavigateBack = navHistory.isNotEmpty() || currentModule != AppNavModule.PORTAL,
                onNavigateBack = navigateBack,
                onNavigateHome = navigateToHome,
                onModuleSelected = { module ->
                    navigateToModule(module)
                },
                audioHelper = audioHelper
            )
        },
        bottomBar = {
            FrenchAppBottomBar(
                currentModule = currentModule,
                onModuleSelected = { module ->
                    navigateToModule(module)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentModule, label = "ModuleTransition") { module ->
                when (module) {
                    AppNavModule.PORTAL -> WelcomePortalScreen(
                        audioHelper = audioHelper,
                        onNavigateToRevisionPage = { targetModule, tabIndex ->
                            audioHelper.stopSpeech()
                            when (targetModule) {
                                AppNavModule.GRAMMAR -> {
                                    grammarInitialTab = tabIndex
                                    navigateToModule(AppNavModule.GRAMMAR)
                                }
                                AppNavModule.REVISION -> {
                                    revisionInitialTab = tabIndex
                                    navigateToModule(AppNavModule.REVISION)
                                }
                                else -> {
                                    navigateToModule(targetModule)
                                }
                            }
                        },
                        onNavigateToUnit = { unitModule ->
                            audioHelper.stopSpeech()
                            navigateToModule(unitModule)
                        }
                    )
                    AppNavModule.REVISION -> RevisionScreen(
                        audioHelper = audioHelper,
                        onScoreEarned = {},
                        onNavigateToIntro = navigateToIntroExercises,
                        initialTab = revisionInitialTab
                    )
                    AppNavModule.GRAMMAR -> GrammarScreen(
                        audioHelper = audioHelper,
                        onScoreEarned = {},
                        initialTab = grammarInitialTab
                    )
                    AppNavModule.UNIT_1 -> Unit1Screen(
                        audioHelper = audioHelper,
                        onScoreEarned = {},
                        onNavigateToIntro = navigateToIntroExercises
                    )
                    AppNavModule.UNIT_2 -> Unit2Screen(
                        audioHelper = audioHelper,
                        onScoreEarned = {}
                    )
                    AppNavModule.UNIT_3 -> Unit3Screen(
                        audioHelper = audioHelper,
                        onScoreEarned = {}
                    )
                    AppNavModule.EXAM -> ExamScreen(
                        audioHelper = audioHelper,
                        onScoreEarned = {}
                    )
                    AppNavModule.WEB_SITE -> com.example.ui.screens.WebViewScreen(
                        audioHelper = audioHelper
                    )
                }
            }
        }
    }
}

@Composable
private fun FrenchAppTopBar(
    currentModule: AppNavModule,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onModuleSelected: (AppNavModule) -> Unit,
    audioHelper: AudioHelper
) {
    Surface(
        color = Indigo700,
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Title and Navigation Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    // Back Button (shown when inside inner tabs / can step back)
                    if (canNavigateBack) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .size(40.dp)
                                .background(Indigo900, RoundedCornerShape(12.dp))
                                .border(1.dp, Indigo100.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                .testTag("top_bar_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "رجوع",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Home Button (Direct access to Main Portal from anywhere)
                    IconButton(
                        onClick = onNavigateHome,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (currentModule == AppNavModule.PORTAL) Amber400 else Indigo900,
                                RoundedCornerShape(12.dp)
                            )
                            .border(1.dp, Indigo100.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                            .testTag("top_bar_home_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "الصفحة الرئيسية",
                            tint = if (currentModule == AppNavModule.PORTAL) Indigo900 else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // App Title & Badge
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "2eme Prep",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            Surface(
                                color = Indigo900,
                                shape = CircleShape,
                                border = androidx.compose.foundation.BorderStroke(1.dp, Indigo100.copy(alpha = 0.4f))
                            ) {
                                Text(
                                    text = "Bienvenu 2",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Amber400,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = if (currentModule == AppNavModule.PORTAL) "1er Semestre • الرئيسية" else currentModule.title,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Indigo100,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Sound Toggle Button
                IconButton(
                    onClick = { audioHelper.toggleSound() },
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (audioHelper.soundEnabled) Indigo900 else Slate700,
                            RoundedCornerShape(12.dp)
                        )
                        .border(1.dp, Indigo100.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .testTag("sound_toggle_button")
                ) {
                    Icon(
                        imageVector = if (audioHelper.soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                        contentDescription = "Activer ou désactiver le son",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Scrollable Module Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                AppNavModule.entries.forEach { module ->
                    val isSelected = currentModule == module
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = when {
                            isSelected -> Color.White
                            module == AppNavModule.EXAM -> Amber400
                            else -> Indigo900.copy(alpha = 0.7f)
                        },
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color.White) else null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onModuleSelected(module) }
                            .defaultMinSize(minHeight = 36.dp)
                            .testTag("top_module_chip_${module.name}")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(module.icon, fontSize = 12.sp)
                            Text(
                                text = module.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    isSelected -> Indigo900
                                    module == AppNavModule.EXAM -> Indigo900
                                    else -> Color.White.copy(alpha = 0.9f)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FrenchAppBottomBar(
    currentModule: AppNavModule,
    onModuleSelected: (AppNavModule) -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 10.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
    ) {
        NavigationBar(
            containerColor = Color.White,
            contentColor = Indigo700,
            tonalElevation = 0.dp,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            AppNavModule.entries.forEach { module ->
                val isSelected = currentModule == module
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onModuleSelected(module) },
                    alwaysShowLabel = false,
                    icon = {
                        Text(
                            text = module.icon,
                            fontSize = if (isSelected) 20.sp else 16.sp
                        )
                    },
                    label = {
                        Text(
                            text = module.shortLabel,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            maxLines = 1,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Indigo700,
                        selectedTextColor = Indigo700,
                        indicatorColor = Indigo100,
                        unselectedIconColor = Slate600,
                        unselectedTextColor = Slate600
                    ),
                    modifier = Modifier.testTag("bottom_nav_${module.name}")
                )
            }
        }
    }
}
