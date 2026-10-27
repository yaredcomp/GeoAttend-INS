package com.example.data.model

data class ClassSession(
    val sessionId: String,
    val scheduleId: String,
    val courseCode: String,
    val courseName: String,
    val section: String,
    val room: String,
    val beaconId: String,
    val scheduledStart: String,
    val scheduledEnd: String,
    val actualStart: String? = null,
    val actualEnd: String? = null,
    val sessionToken: String = "DU-SES-2024-B04-0931",
    val rollingBeaconCode: String = "DU-3012-K89X",
    val status: SessionStatus = SessionStatus.INITIALIZED,
    val enrolledCount: Int = 64,
    val presentCount: Int = 59,
    val tardyCount: Int = 3,
    val flaggedCount: Int = 2,
    val startDelayMinutes: Int = 1,
    val startDelaySeconds: Int = 14,
    val earlyConcludeMinutes: Int = 1,
    val earlyConcludeSeconds: Int = 20,
    val geofenceRadius: Double = 25.0,
    val verifiedAccuracy: Double = 1.8,
    val beaconRssi: Int = -56,
    val instructorDistanceToPodium: Double = 8.2,
    val quorumPercentage: Double = 92.2,
    val stage: Int = 1, // 1: Initial Roll-call, 2: Mid/Final Session
    val ledgerHash: String = "SHA256-DU-884A-99B1-F720"
)

enum class SessionStatus {
    SCHEDULED,
    VERIFYING_LOCATION,
    BIOMETRIC_PENDING,
    INITIALIZED,
    ACTIVE,
    CONCLUDING,
    CONCLUDED_SEALED,
    FLAGGED_FOR_REVIEW
}
