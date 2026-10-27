package com.example.data.model

data class CourseSchedule(
    val id: String,
    val courseCode: String,
    val courseName: String,
    val section: String,
    val studentGroup: String,
    val room: String,
    val building: String,
    val floorLevel: String,
    val timeSlot: String,
    val startTime: String,
    val endTime: String,
    val durationText: String,
    val enrolledCount: Int,
    val beaconId: String,
    val beaconName: String,
    val dayOfWeek: String, // "Monday", "Tuesday", etc.
    val isToday: Boolean = false,
    val status: ScheduleStatus = ScheduleStatus.UPCOMING,
    val presentCount: Int = 0,
    val syncDelayText: String = "",
    val sessionReceiptId: String = ""
)

enum class ScheduleStatus {
    UPCOMING,
    READY_FOR_LOCATION,
    START_WINDOW_OPEN,
    CLASS_ACTIVE,
    CONCLUDED,
    CANCELLED,
    REQUIRES_REVIEW
}
