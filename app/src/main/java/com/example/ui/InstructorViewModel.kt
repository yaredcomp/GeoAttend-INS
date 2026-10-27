package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.InstructorRepository
import com.example.ui.components.NavigationTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppScreen {
    AUTH,
    REGISTRATION_REQUEST,
    PASSWORD_RECOVERY,
    MAIN_TABS,
    CLASS_DETAILS,
    GEOFENCE_RADAR,
    BIOMETRIC_VERIFY,
    START_CONFIRMATION,
    LIVE_SESSION,
    CHECKPOINT_SWEEP,
    ATTENDANCE_REVIEW,
    CONCLUDE_MODAL,
    SESSION_SUMMARY,
    PUNCTUALITY_ANALYTICS
}

class InstructorViewModel(
    val repository: InstructorRepository = InstructorRepository()
) : ViewModel() {

    // Default persona Dr. Dawit Abebe Tadesse is authenticated by default as chosen in clarifying questions
    private val _currentScreen = MutableStateFlow(AppScreen.MAIN_TABS)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedTab = MutableStateFlow(NavigationTab.HOME)
    val selectedTab: StateFlow<NavigationTab> = _selectedTab.asStateFlow()

    val profile = repository.profile
    val schedules = repository.schedules
    val selectedSchedule = repository.selectedSchedule
    val currentSession = repository.currentSession
    val students = repository.students
    val alerts = repository.alerts
    val punctuality = repository.punctuality

    val isInsideGeofence = repository.simulatedInsideGeofence
    val isBeaconStrong = repository.simulatedBeaconSignal
    val isBiometricPass = repository.simulatedBiometricPass
    val isSessionActive = repository.isSessionActive

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun selectTab(tab: NavigationTab) {
        _selectedTab.value = tab
        _currentScreen.value = AppScreen.MAIN_TABS
    }

    fun selectClass(schedule: CourseSchedule) {
        repository.selectSchedule(schedule.id)
        _currentScreen.value = AppScreen.CLASS_DETAILS
    }

    fun onVerifyAndStartClick() {
        _currentScreen.value = AppScreen.CLASS_DETAILS
    }

    fun onProceedToBiometrics() {
        _currentScreen.value = AppScreen.BIOMETRIC_VERIFY
    }

    fun onBiometricSuccess() {
        val activated = repository.activateClassSession()
        if (activated) {
            _currentScreen.value = AppScreen.START_CONFIRMATION
        }
    }

    fun onEnterLiveDashboard() {
        _currentScreen.value = AppScreen.LIVE_SESSION
    }

    fun onTriggerSecondaryPulse() {
        repository.triggerSecondaryPulse()
    }

    fun onConcludeClassClick() {
        _currentScreen.value = AppScreen.CONCLUDE_MODAL
    }

    fun onFinalSealSuccess() {
        val concluded = repository.concludeClassSession(digitalSealConfirmed = true)
        if (concluded) {
            _currentScreen.value = AppScreen.SESSION_SUMMARY
        }
    }

    fun onResolveStudent(studentId: String, markExcused: Boolean) {
        repository.resolveAnomaly(studentId, markExcused)
    }

    fun onToggleGeofence() = repository.toggleGeofenceSimulation()
    fun onToggleBeacon() = repository.toggleBeaconSignal()
    fun onToggleBiometric() = repository.toggleBiometricSimulation()

    fun onSignOut() {
        _currentScreen.value = AppScreen.AUTH
    }

    fun onSignIn() {
        _currentScreen.value = AppScreen.MAIN_TABS
    }
}
