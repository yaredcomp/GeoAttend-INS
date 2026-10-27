package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppScreen
import com.example.ui.InstructorViewModel
import com.example.ui.components.DuBottomNavigation
import com.example.ui.components.DuTopAppBar
import com.example.ui.components.NavigationTab
import com.example.ui.components.SimulationControlBar
import com.example.ui.screens.*
import com.example.ui.theme.DuSurfaceCanvas
import com.example.ui.theme.GeoAttendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoAttendTheme {
                val viewModel: InstructorViewModel = viewModel()
                GeoAttendApp(viewModel)
            }
        }
    }
}

@Composable
fun GeoAttendApp(
    viewModel: InstructorViewModel,
    modifier: Modifier = Modifier
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val schedules by viewModel.schedules.collectAsStateWithLifecycle()
    val selectedSchedule by viewModel.selectedSchedule.collectAsStateWithLifecycle()
    val currentSession by viewModel.currentSession.collectAsStateWithLifecycle()
    val students by viewModel.students.collectAsStateWithLifecycle()
    val alerts by viewModel.alerts.collectAsStateWithLifecycle()
    val punctuality by viewModel.punctuality.collectAsStateWithLifecycle()

    val isInsideGeofence by viewModel.isInsideGeofence.collectAsStateWithLifecycle()
    val isBeaconStrong by viewModel.isBeaconStrong.collectAsStateWithLifecycle()
    val isBiometricPass by viewModel.isBiometricPass.collectAsStateWithLifecycle()

    // Back button handling for sub-screens
    BackHandler(enabled = currentScreen != AppScreen.MAIN_TABS && currentScreen != AppScreen.AUTH) {
        when (currentScreen) {
            AppScreen.REGISTRATION_REQUEST, AppScreen.PASSWORD_RECOVERY -> {
                viewModel.navigateTo(AppScreen.AUTH)
            }
            AppScreen.CLASS_DETAILS -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
            AppScreen.GEOFENCE_RADAR -> {
                viewModel.navigateTo(AppScreen.CLASS_DETAILS)
            }
            AppScreen.BIOMETRIC_VERIFY -> {
                viewModel.navigateTo(AppScreen.CLASS_DETAILS)
            }
            AppScreen.START_CONFIRMATION -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
            AppScreen.LIVE_SESSION -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
            AppScreen.CHECKPOINT_SWEEP -> {
                viewModel.navigateTo(AppScreen.LIVE_SESSION)
            }
            AppScreen.ATTENDANCE_REVIEW -> {
                viewModel.navigateTo(AppScreen.LIVE_SESSION)
            }
            AppScreen.CONCLUDE_MODAL -> {
                viewModel.navigateTo(AppScreen.LIVE_SESSION)
            }
            AppScreen.SESSION_SUMMARY -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
            AppScreen.PUNCTUALITY_ANALYTICS -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
            else -> {
                viewModel.navigateTo(AppScreen.MAIN_TABS)
            }
        }
    }

    // Back handling inside MAIN_TABS if not on Home tab
    BackHandler(enabled = currentScreen == AppScreen.MAIN_TABS && selectedTab != NavigationTab.HOME) {
        viewModel.selectTab(NavigationTab.HOME)
    }

    Crossfade(targetState = currentScreen, label = "ScreenTransition") { screen ->
        when (screen) {
            AppScreen.AUTH -> {
                AuthScreen(
                    onSignInSuccess = { viewModel.onSignIn() },
                    onRegisterRequestClick = { viewModel.navigateTo(AppScreen.REGISTRATION_REQUEST) },
                    onForgotPasswordClick = { viewModel.navigateTo(AppScreen.PASSWORD_RECOVERY) },
                    modifier = modifier
                )
            }
            AppScreen.REGISTRATION_REQUEST -> {
                RegistrationRequestScreen(
                    onBackToLogin = { viewModel.navigateTo(AppScreen.AUTH) },
                    onSubmitSuccess = { viewModel.navigateTo(AppScreen.AUTH) },
                    modifier = modifier
                )
            }
            AppScreen.PASSWORD_RECOVERY -> {
                PasswordRecoveryScreen(
                    onBackToLogin = { viewModel.navigateTo(AppScreen.AUTH) },
                    onResetComplete = { viewModel.navigateTo(AppScreen.AUTH) },
                    modifier = modifier
                )
            }
            AppScreen.CLASS_DETAILS -> {
                ClassDetailsScreen(
                    schedule = selectedSchedule,
                    profile = profile,
                    isInsideGeofence = isInsideGeofence,
                    onBackClick = { viewModel.navigateTo(AppScreen.MAIN_TABS) },
                    onProceedToBiometrics = { viewModel.onProceedToBiometrics() },
                    onViewCohortClick = { viewModel.navigateTo(AppScreen.ATTENDANCE_REVIEW) },
                    onRadarExpandClick = { viewModel.navigateTo(AppScreen.GEOFENCE_RADAR) },
                    modifier = modifier
                )
            }
            AppScreen.GEOFENCE_RADAR -> {
                GeofenceRadarScreen(
                    schedule = selectedSchedule,
                    isInsideGeofence = isInsideGeofence,
                    onBackClick = { viewModel.navigateTo(AppScreen.CLASS_DETAILS) },
                    onVerifyAtDoorSuccess = { viewModel.navigateTo(AppScreen.CLASS_DETAILS) },
                    modifier = modifier
                )
            }
            AppScreen.BIOMETRIC_VERIFY -> {
                BiometricVerificationScreen(
                    schedule = selectedSchedule,
                    isBiometricSimPass = isBiometricPass,
                    onBackClick = { viewModel.navigateTo(AppScreen.CLASS_DETAILS) },
                    onAuthSuccess = { viewModel.onBiometricSuccess() },
                    modifier = modifier
                )
            }
            AppScreen.START_CONFIRMATION -> {
                StartClassConfirmationScreen(
                    session = currentSession,
                    onBackClick = { viewModel.navigateTo(AppScreen.MAIN_TABS) },
                    onEnterLiveDashboard = { viewModel.onEnterLiveDashboard() },
                    modifier = modifier
                )
            }
            AppScreen.LIVE_SESSION -> {
                LiveSessionScreen(
                    session = currentSession,
                    students = students,
                    onBackClick = { viewModel.navigateTo(AppScreen.MAIN_TABS) },
                    onTriggerSecondaryPulse = { viewModel.onTriggerSecondaryPulse() },
                    onStudentOverrideClick = { student ->
                        viewModel.onResolveStudent(student.id, markExcused = true)
                    },
                    onConcludeClassClick = { viewModel.onConcludeClassClick() },
                    onViewCheckpointClick = { viewModel.navigateTo(AppScreen.CHECKPOINT_SWEEP) },
                    modifier = modifier
                )
            }
            AppScreen.CHECKPOINT_SWEEP -> {
                CheckpointScreen(
                    session = currentSession,
                    onBackClick = { viewModel.navigateTo(AppScreen.LIVE_SESSION) },
                    onTriggerSecondaryPulse = { viewModel.onTriggerSecondaryPulse() },
                    onFastCheckRosterClick = { viewModel.navigateTo(AppScreen.LIVE_SESSION) },
                    modifier = modifier
                )
            }
            AppScreen.ATTENDANCE_REVIEW -> {
                AttendanceReviewScreen(
                    students = students,
                    onBackClick = { viewModel.navigateTo(AppScreen.CLASS_DETAILS) },
                    onResolveStudent = { id, excused -> viewModel.onResolveStudent(id, excused) },
                    modifier = modifier
                )
            }
            AppScreen.CONCLUDE_MODAL -> {
                ConcludeClassModal(
                    session = currentSession,
                    students = students,
                    onBackToLive = { viewModel.navigateTo(AppScreen.LIVE_SESSION) },
                    onResolveStudent = { id, excused -> viewModel.onResolveStudent(id, excused) },
                    onFinalSealSuccess = { viewModel.onFinalSealSuccess() },
                    modifier = modifier
                )
            }
            AppScreen.SESSION_SUMMARY -> {
                SessionSummaryScreen(
                    session = currentSession,
                    onReturnToDashboard = { viewModel.navigateTo(AppScreen.MAIN_TABS) },
                    onDownloadPdf = { /* Triggered notification in screen */ },
                    modifier = modifier
                )
            }
            AppScreen.PUNCTUALITY_ANALYTICS -> {
                PunctualityIndexScreen(
                    metrics = punctuality,
                    onBackClick = { viewModel.navigateTo(AppScreen.MAIN_TABS) },
                    modifier = modifier
                )
            }
            AppScreen.MAIN_TABS -> {
                val nextClass = schedules.firstOrNull() ?: selectedSchedule
                val remainingClasses = schedules.drop(1)

                val tabTitle = when (selectedTab) {
                    NavigationTab.HOME -> "GeoAttend DU"
                    NavigationTab.SCHEDULE -> "Weekly Schedule"
                    NavigationTab.CLASSES -> "Curriculum History"
                    NavigationTab.ALERTS -> "Real-Time Dispatch"
                    NavigationTab.PROFILE -> "Faculty Profile"
                }

                val tabSubtitle = when (selectedTab) {
                    NavigationTab.HOME -> "Dilla University • Green Campus"
                    NavigationTab.SCHEDULE -> "2017 E.C. • Semester II"
                    NavigationTab.CLASSES -> "Accredited Term Delivery"
                    NavigationTab.ALERTS -> "Active Telemetry & Alerts"
                    NavigationTab.PROFILE -> "Academic Identity & Security"
                }

                Scaffold(
                    modifier = modifier.fillMaxSize(),
                    topBar = {
                        DuTopAppBar(
                            title = tabTitle,
                            subtitle = tabSubtitle,
                            showBack = false,
                            onAlertsClick = { viewModel.selectTab(NavigationTab.ALERTS) },
                            onProfileClick = { viewModel.selectTab(NavigationTab.PROFILE) }
                        )
                    },
                    bottomBar = {
                        DuBottomNavigation(
                            selectedTab = selectedTab,
                            onTabSelected = { tab -> viewModel.selectTab(tab) },
                            alertsCount = alerts.count { !it.isAcknowledged }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(DuSurfaceCanvas)
                    ) {
                        // Prototype hardware telemetry & simulation bar (interactive test control)
                        SimulationControlBar(
                            isInsideGeofence = isInsideGeofence,
                            onToggleGeofence = { viewModel.onToggleGeofence() },
                            isBeaconStrong = isBeaconStrong,
                            onToggleBeacon = { viewModel.onToggleBeacon() },
                            isBiometricPass = isBiometricPass,
                            onToggleBiometric = { viewModel.onToggleBiometric() }
                        )

                        Box(modifier = Modifier.weight(1f)) {
                            when (selectedTab) {
                                NavigationTab.HOME -> {
                                    DashboardScreen(
                                        profile = profile,
                                        nextClass = nextClass,
                                        remainingClasses = remainingClasses,
                                        isInsideGeofence = isInsideGeofence,
                                        onClassClick = { schedule -> viewModel.selectClass(schedule) },
                                        onVerifyAndStartClick = { viewModel.onVerifyAndStartClick() },
                                        onReviewAlertClick = { viewModel.selectTab(NavigationTab.ALERTS) }
                                    )
                                }
                                NavigationTab.SCHEDULE -> {
                                    ScheduleScreen(
                                        schedules = schedules,
                                        onClassClick = { schedule -> viewModel.selectClass(schedule) },
                                        onExportPdfClick = { /* Notified in UI */ }
                                    )
                                }
                                NavigationTab.CLASSES -> {
                                    ClassesHistoryScreen(
                                        onViewReceipt = { _ -> viewModel.navigateTo(AppScreen.SESSION_SUMMARY) },
                                        onPunctualityClick = { viewModel.navigateTo(AppScreen.PUNCTUALITY_ANALYTICS) }
                                    )
                                }
                                NavigationTab.ALERTS -> {
                                    AlertsScreen(
                                        alerts = alerts,
                                        onAcknowledgeAlert = { id -> viewModel.repository.acknowledgeAlert(id) },
                                        onReviewTelemetry = { alert ->
                                            if (alert.scheduleId != null) {
                                                viewModel.selectClass(selectedSchedule)
                                            }
                                        }
                                    )
                                }
                                NavigationTab.PROFILE -> {
                                    ProfileScreen(
                                        profile = profile,
                                        onSignOut = { viewModel.onSignOut() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
