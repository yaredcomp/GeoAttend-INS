package com.example.data.model

data class StudentAttendance(
    val id: String,
    val studentId: String,
    val name: String,
    val department: String,
    val initials: String,
    val initialStatus: StudentStatus,
    val finalStatus: StudentStatus,
    val verificationTag: String, // "Geo + Bio", "Geo + BLE", "Geo + PIN", "Out of Range", "Unverified"
    val confidenceRate: String,
    val checkInTime: String,
    val driftMeters: Double = 0.0,
    val note: String = "",
    val isExcused: Boolean = false,
    val isFlagged: Boolean = false
)

enum class StudentStatus {
    PRESENT,
    TARDY,
    OUT_OF_RANGE,
    UNVERIFIED,
    ABSENT,
    EXCUSED
}
