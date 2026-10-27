package com.example.data.model

data class AttendanceAlert(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val category: AlertCategory,
    val priority: AlertPriority,
    val timestamp: String,
    val actionLabel: String? = null,
    val secondaryActionLabel: String? = null,
    val isUnacknowledged: Boolean = true,
    val details: String? = null,
    val scheduleId: String? = null
) {
    val isAcknowledged: Boolean
        get() = !isUnacknowledged
}

enum class AlertCategory {
    ALL,
    ACADEMIC_REGISTRAR,
    HARDWARE_BEACON
}

enum class AlertPriority {
    HIGH,
    NORMAL,
    INFO
}

data class PunctualityMetrics(
    val punctualityIndex: Double = 98.2,
    val institutionalRating: String = "Grade A+",
    val deptComparison: String = "+1.4% vs Dept",
    val inceptionDeltaText: String = "+1m 12s",
    val geofenceLockRate: Double = 99.4,
    val cohortQuorumRate: Double = 96.1,
    val concludeSealRate: Double = 99.8,
    val totalLoggedSessions: Int = 64,
    val punctualLaunchCount: Int = 59,
    val minorTransitBufferCount: Int = 4,
    val excusedReallocationCount: Int = 1,
    val accreditationProtocol: String = "DU-ET-2017-QA",
    val auditRef: String = "#DU-8842-P"
)
