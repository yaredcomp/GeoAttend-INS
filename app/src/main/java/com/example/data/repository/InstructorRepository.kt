package com.example.data.repository

import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InstructorRepository {

    // Current Authenticated Faculty Profile
    private val _profile = MutableStateFlow(InstructorProfile())
    val profile: StateFlow<InstructorProfile> = _profile.asStateFlow()

    // Schedules
    private val _schedules = MutableStateFlow(sampleSchedules())
    val schedules: StateFlow<List<CourseSchedule>> = _schedules.asStateFlow()

    // Next / Selected Class
    private val _selectedSchedule = MutableStateFlow(_schedules.value.first())
    val selectedSchedule: StateFlow<CourseSchedule> = _selectedSchedule.asStateFlow()

    // Active Session State
    private val _currentSession = MutableStateFlow(sampleInitialSession())
    val currentSession: StateFlow<ClassSession> = _currentSession.asStateFlow()

    // Live Student Roster for Current Session
    private val _students = MutableStateFlow(sampleStudents())
    val students: StateFlow<List<StudentAttendance>> = _students.asStateFlow()

    // Alerts
    private val _alerts = MutableStateFlow(sampleAlerts())
    val alerts: StateFlow<List<AttendanceAlert>> = _alerts.asStateFlow()

    // Punctuality
    private val _punctuality = MutableStateFlow(PunctualityMetrics())
    val punctuality: StateFlow<PunctualityMetrics> = _punctuality.asStateFlow()

    // Prototype Hardware Simulation Controls
    private val _simulatedInsideGeofence = MutableStateFlow(true)
    val simulatedInsideGeofence: StateFlow<Boolean> = _simulatedInsideGeofence.asStateFlow()

    private val _simulatedBeaconSignal = MutableStateFlow(true) // Strong vs weak
    val simulatedBeaconSignal: StateFlow<Boolean> = _simulatedBeaconSignal.asStateFlow()

    private val _simulatedBiometricPass = MutableStateFlow(true)
    val simulatedBiometricPass: StateFlow<Boolean> = _simulatedBiometricPass.asStateFlow()

    private val _isSessionActive = MutableStateFlow(false)
    val isSessionActive: StateFlow<Boolean> = _isSessionActive.asStateFlow()

    fun selectSchedule(scheduleId: String) {
        _schedules.value.find { it.id == scheduleId }?.let { found ->
            _selectedSchedule.value = found
        }
    }

    fun toggleGeofenceSimulation() {
        _simulatedInsideGeofence.update { !it }
        val inside = _simulatedInsideGeofence.value
        _currentSession.update { current ->
            current.copy(
                instructorDistanceToPodium = if (inside) 8.2 else 58.4,
                verifiedAccuracy = if (inside) 1.8 else 18.5
            )
        }
    }

    fun toggleBeaconSignal() {
        _simulatedBeaconSignal.update { !it }
        val strong = _simulatedBeaconSignal.value
        _currentSession.update { current ->
            current.copy(beaconRssi = if (strong) -56 else -92)
        }
    }

    fun toggleBiometricSimulation() {
        _simulatedBiometricPass.update { !it }
    }

    fun activateClassSession(): Boolean {
        if (!_simulatedInsideGeofence.value) return false
        if (!_simulatedBiometricPass.value) return false

        _isSessionActive.value = true
        _currentSession.update { current ->
            current.copy(
                status = SessionStatus.ACTIVE,
                actualStart = "09:31:14 AM",
                sessionToken = "DU-SES-2024-B04-0931",
                rollingBeaconCode = "DU-3012-K89X",
                stage = 1
            )
        }
        _schedules.update { list ->
            list.map {
                if (it.id == _selectedSchedule.value.id) it.copy(status = ScheduleStatus.CLASS_ACTIVE)
                else it
            }
        }
        return true
    }

    fun triggerSecondaryPulse() {
        _currentSession.update { current ->
            current.copy(stage = 2)
        }
    }

    fun resolveAnomaly(studentId: String, markExcused: Boolean) {
        _students.update { list ->
            list.map { student ->
                if (student.studentId == studentId) {
                    student.copy(
                        isExcused = markExcused,
                        isFlagged = false,
                        finalStatus = if (markExcused) StudentStatus.EXCUSED else StudentStatus.PRESENT,
                        note = if (markExcused) "Excused by Instructor Dr. Dawit" else "Flagged for Registrar"
                    )
                } else student
            }
        }
        // Update counts
        val excusedCount = _students.value.count { it.isExcused }
        val presentCount = _students.value.count { it.initialStatus == StudentStatus.PRESENT || it.isExcused }
        _currentSession.update { current ->
            current.copy(
                presentCount = presentCount,
                flaggedCount = _students.value.count { it.isFlagged }
            )
        }
    }

    fun concludeClassSession(digitalSealConfirmed: Boolean): Boolean {
        if (!digitalSealConfirmed) return false
        _isSessionActive.value = false
        _currentSession.update { current ->
            current.copy(
                status = SessionStatus.CONCLUDED_SEALED,
                actualEnd = "11:30:12 AM"
            )
        }
        _schedules.update { list ->
            list.map {
                if (it.id == _selectedSchedule.value.id) it.copy(
                    status = ScheduleStatus.CONCLUDED,
                    presentCount = 59,
                    syncDelayText = "+1m 14s",
                    sessionReceiptId = "DU-SES-0931"
                )
                else it
            }
        }
        return true
    }

    fun acknowledgeAlert(alertId: String) {
        _alerts.update { list ->
            list.map { if (it.id == alertId) it.copy(isUnacknowledged = false) else it }
        }
    }

    companion object {
        fun sampleSchedules(): List<CourseSchedule> = listOf(
            CourseSchedule(
                id = "sched-cs3012",
                courseCode = "CS-3012",
                courseName = "Advanced Database Systems",
                section = "Section A",
                studentGroup = "B.Sc. Computer Science (Year III)",
                room = "Room CNCS-B04",
                building = "Oda Ya'a Academic Complex",
                floorLevel = "Level 2 (West Wing)",
                timeSlot = "09:30 AM – 11:30 AM",
                startTime = "09:30 AM",
                endTime = "11:30 AM",
                durationText = "2h 00m",
                enrolledCount = 64,
                beaconId = "BLE-DU-CNCS-04-A",
                beaconName = "BLE-DU-CNCS-04-A (Instructor Podium)",
                dayOfWeek = "Thursday",
                isToday = true,
                status = ScheduleStatus.START_WINDOW_OPEN,
                presentCount = 59,
                syncDelayText = "+1m 14s",
                sessionReceiptId = "DU-SES-0931"
            ),
            CourseSchedule(
                id = "sched-se4210",
                courseCode = "SE-4210",
                courseName = "Software Architecture & Design",
                section = "Section B",
                studentGroup = "Software Engineering (Graduating Class)",
                room = "Computing Lab 02",
                building = "New Tech Tower",
                floorLevel = "3rd Floor",
                timeSlot = "01:30 PM – 03:30 PM",
                startTime = "01:30 PM",
                endTime = "03:30 PM",
                durationText = "2h 00m",
                enrolledCount = 58,
                beaconId = "BLE-TECH-LAB-02",
                beaconName = "BLE-TECH-02 (Central Server Rack)",
                dayOfWeek = "Thursday",
                isToday = true,
                status = ScheduleStatus.UPCOMING
            ),
            CourseSchedule(
                id = "sched-cs4901",
                courseCode = "CS-4901",
                courseName = "Senior Capstone Project Seminar",
                section = "Cohort 1",
                studentGroup = "Research & Implementation Reviews",
                room = "Seminar Hall 01",
                building = "Main Library Wing",
                floorLevel = "Ground Floor",
                timeSlot = "04:00 PM – 05:30 PM",
                startTime = "04:00 PM",
                endTime = "05:30 PM",
                durationText = "1.5h",
                enrolledCount = 32,
                beaconId = "BLE-LIB-SEM-01",
                beaconName = "BLE-LIB-SEM-01 (Auditorium)",
                dayOfWeek = "Thursday",
                isToday = true,
                status = ScheduleStatus.UPCOMING
            )
        )

        fun sampleInitialSession(): ClassSession = ClassSession(
            sessionId = "ses-cs3012-today",
            scheduleId = "sched-cs3012",
            courseCode = "CS-3012",
            courseName = "Advanced Database Systems",
            section = "Section A",
            room = "Room CNCS-B04, Oda Ya'a Complex",
            beaconId = "BLE-DU-CNCS-04-A",
            scheduledStart = "09:30 AM",
            scheduledEnd = "11:30 AM",
            status = SessionStatus.SCHEDULED,
            enrolledCount = 64,
            presentCount = 59,
            tardyCount = 3,
            flaggedCount = 2,
            quorumPercentage = 92.2
        )

        fun sampleStudents(): List<StudentAttendance> = listOf(
            StudentAttendance(
                id = "stu-1",
                studentId = "DU/14298/13",
                name = "Bekele Tadesse",
                department = "CompSci",
                initials = "BT",
                initialStatus = StudentStatus.PRESENT,
                finalStatus = StudentStatus.PRESENT,
                verificationTag = "Geo + Bio",
                confidenceRate = "99.8% Conf. Inside 45m",
                checkInTime = "09:32 AM",
                driftMeters = 3.4
            ),
            StudentAttendance(
                id = "stu-2",
                studentId = "DU/14305/13",
                name = "Rahel Getachew",
                department = "Software Eng",
                initials = "RG",
                initialStatus = StudentStatus.PRESENT,
                finalStatus = StudentStatus.PRESENT,
                verificationTag = "Geo + BLE",
                confidenceRate = "99.1% Conf. Inside 38m",
                checkInTime = "09:31 AM",
                driftMeters = 4.1
            ),
            StudentAttendance(
                id = "stu-3",
                studentId = "DU/14112/13",
                name = "Yohannes Haile",
                department = "CompSci",
                initials = "YH",
                initialStatus = StudentStatus.OUT_OF_RANGE,
                finalStatus = StudentStatus.OUT_OF_RANGE,
                verificationTag = "Out of Range",
                confidenceRate = "Perimeter Drift (18m)",
                checkInTime = "10:42 AM",
                driftMeters = 18.2,
                note = "GPS coordinates flagged outside Hall B04 (320m drift toward Student Union Lounge)",
                isFlagged = true
            ),
            StudentAttendance(
                id = "stu-4",
                studentId = "DU/14589/13",
                name = "Meron Assefa",
                department = "Information Sys",
                initials = "MA",
                initialStatus = StudentStatus.UNVERIFIED,
                finalStatus = StudentStatus.UNVERIFIED,
                verificationTag = "Unverified BLE",
                confidenceRate = "Delay: 18 mins",
                checkInTime = "09:48 AM",
                driftMeters = 22.0,
                note = "GPS signal lost / Sensor unacknowledged BLE Ping",
                isFlagged = true
            ),
            StudentAttendance(
                id = "stu-5",
                studentId = "DU/14388/13",
                name = "Dawit Kebede",
                department = "Software Eng",
                initials = "DK",
                initialStatus = StudentStatus.PRESENT,
                finalStatus = StudentStatus.PRESENT,
                verificationTag = "Geo + PIN",
                confidenceRate = "99.7% Conf. Inside 14m",
                checkInTime = "09:30 AM",
                driftMeters = 2.1
            ),
            StudentAttendance(
                id = "stu-6",
                studentId = "DU/14402/13",
                name = "Bethlehem Mengistu",
                department = "CompSci",
                initials = "BM",
                initialStatus = StudentStatus.ABSENT,
                finalStatus = StudentStatus.ABSENT,
                verificationTag = "Zero Telemetry",
                confidenceRate = "No Ping",
                checkInTime = "--",
                driftMeters = 0.0,
                note = "Zero beacon telemetry recorded"
            )
        )

        fun sampleAlerts(): List<AttendanceAlert> = listOf(
            AttendanceAlert(
                id = "alt-1",
                title = "Room CNCS-B04 BLE Node Beacon Battery Low (12%)",
                subtitle = "Oda Ya'a Complex Level 2 • Node ID: #ODA-B04-RADAR",
                description = "Geo-boundary signal drift detected during CS-3012 morning lecture roll call. Geofence radius tolerance automatically widened to 25m to prevent false student absences.",
                category = AlertCategory.HARDWARE_BEACON,
                priority = AlertPriority.HIGH,
                timestamp = "08:24 AM",
                actionLabel = "Acknowledge",
                secondaryActionLabel = "Report to Campus IT"
            ),
            AttendanceAlert(
                id = "alt-2",
                title = "Flagged Telemetry: Yohannes Haile",
                subtitle = "ID: DU/14112/13 • Lecture: CS-3012 Distributed Systems",
                description = "Telemetry Lock Reason: GPS coordinates flagged outside Hall B04... Pending verification.",
                category = AlertCategory.ACADEMIC_REGISTRAR,
                priority = AlertPriority.NORMAL,
                timestamp = "10:45 AM",
                actionLabel = "Review Telemetry"
            ),
            AttendanceAlert(
                id = "alt-3",
                title = "CS-4901 Senior Capstone Mentoring Rescheduled",
                subtitle = "Rescheduled for Friday Oct 25 • 02:00 PM",
                description = "Previous venue: Seminar Hall A. New assigned venue: Oda Ya'a Amphitheater. Geofence auto-updated in SIS.",
                category = AlertCategory.ACADEMIC_REGISTRAR,
                priority = AlertPriority.INFO,
                timestamp = "11:15 AM",
                actionLabel = "Sync Calendar"
            ),
            AttendanceAlert(
                id = "alt-4",
                title = "Semester II Midterm Quorum Audit Sealed",
                subtitle = "Central Academic Registrar • Office of Institutional Data",
                description = "38 lecture sessions locked to cryptographic hash ledger (100% complete).",
                category = AlertCategory.ACADEMIC_REGISTRAR,
                priority = AlertPriority.INFO,
                timestamp = "Tue, 04:30 PM",
                actionLabel = "View SIS Receipt"
            ),
            AttendanceAlert(
                id = "alt-5",
                title = "Dilla University Smart Campus: GNSS Base Station Update",
                subtitle = "Firmware v4.2.1 installed across Main Campus towers",
                description = "Provides enhanced multi-path mitigation during cloudy weather conditions over the Gedeo highlands.",
                category = AlertCategory.HARDWARE_BEACON,
                priority = AlertPriority.INFO,
                timestamp = "Mon, 10:00 AM",
                actionLabel = "Release Notes"
            )
        )
    }
}
