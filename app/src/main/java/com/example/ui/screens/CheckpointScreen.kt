package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ClassSession
import com.example.ui.components.SpatialRadarCanvas
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckpointScreen(
    session: ClassSession,
    onBackClick: () -> Unit,
    onTriggerSecondaryPulse: () -> Unit,
    onFastCheckRosterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pulseTriggered by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "GEOATTEND DU",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuForestGreen
                        )
                        Text(
                            text = "Checkpoint Progress",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("checkpoint_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DuTextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DuSurfaceCard)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DuSurfaceCanvas)
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Header Stage Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFFEF3C7)) {
                            Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(DuGold))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "FINAL CHECKPOINT ACTIVE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuGoldDark)
                            }
                        }
                        Text(text = "Stage 2 of 2", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Class Attendance Checkpoint",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuTextPrimary
                    )
                    Text(
                        text = "Lecture ${session.courseCode} • 2-Hour Verification Protocol",
                        fontSize = 11.sp,
                        color = DuTextSecondary
                    )
                    Text(
                        text = "College of Natural & Computational Sciences (CNCS)",
                        fontSize = 10.sp,
                        color = DuTextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Session Verification Flow Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Timeline, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Session Verification Flow", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                        Text(text = "90.6% Quorum", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Stage 1 Row
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(DuForestGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "Stage 1: Initial Roll-Call", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "Locked 09:45 AM", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                            }
                            Text(text = "09:30 - 09:50 AM • 61 Students validated", fontSize = 10.sp, color = DuTextSecondary)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Stage 2 Row
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(DuGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Sensors, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "Stage 2: Mid/Final Session", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "21m 45s left", fontSize = 10.sp, color = DuGoldDark, fontWeight = FontWeight.Bold)
                            }
                            Text(text = "11:00 - 11:25 AM • Broadcast active", fontSize = 10.sp, color = DuTextSecondary)
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { 0.906f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = DuForestGreen,
                                trackColor = DuGreenContainer
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "58 / 64 Students Verified", fontSize = 9.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                                Text(text = "6 Pending", fontSize = 9.sp, color = DuAlertCrimson, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Spatial BLE Radar Canvas
            Text(text = "Spatial BLE Radar (Room CNCS-B04)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Spacer(modifier = Modifier.height(6.dp))
            SpatialRadarCanvas(
                isInsidePerimeter = true,
                distanceToPodium = 8.2,
                accuracyMeters = 1.2,
                modifier = Modifier.height(260.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Integrity Telemetry
            Text(text = "INTEGRITY TELEMETRY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary, letterSpacing = 0.5.sp)
            Spacer(modifier = Modifier.height(8.dp))

            IntegrityTelemetryRow(
                icon = Icons.Default.Shield,
                iconBg = DuGreenContainer,
                iconTint = DuForestGreen,
                title = "Continuous Presence",
                subtitle = "Full session in 25m boundary",
                count = "55",
                badgeText = "Locked",
                badgeColor = DuForestGreen,
                badgeBg = DuGreenContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            IntegrityTelemetryRow(
                icon = Icons.Default.DirectionsWalk,
                iconBg = DuCrimsonLight,
                iconTint = DuAlertCrimson,
                title = "Perimeter Departure",
                subtitle = "Left room > 10 minutes",
                count = "3",
                badgeText = "Flagged",
                badgeColor = DuAlertCrimson,
                badgeBg = DuCrimsonLight
            )

            Spacer(modifier = Modifier.height(8.dp))

            IntegrityTelemetryRow(
                icon = Icons.Default.Fingerprint,
                iconBg = Color(0xFFEFF6FF),
                iconTint = Color(0xFF1D4ED8),
                title = "Awaiting Verification",
                subtitle = "Pending proximity ping",
                count = "6",
                badgeText = "Pending",
                badgeColor = DuGoldDark,
                badgeBg = Color(0xFFFEF3C7)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Actions
            Button(
                onClick = {
                    onTriggerSecondaryPulse()
                    pulseTriggered = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("checkpoint_pulse_beacon_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Sensors, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Trigger Secondary Beacon Pulse", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.MoreTime, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Extend (+5 mins)", fontSize = 11.sp, color = DuForestGreen)
                }

                OutlinedButton(
                    onClick = onFastCheckRosterClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.FactCheck, contentDescription = null, tint = DuTextPrimary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Fast-Check Roster", fontSize = 11.sp, color = DuTextPrimary)
                }
            }

            if (pulseTriggered) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Secondary BLE beacon pulse broadcasted. Proximity received on 58 student devices.",
                    fontSize = 11.sp,
                    color = DuForestGreen,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), color = DuGreenContainer) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dilla University Academic Statute 2016 E.C. requires both Initial Entrance and Concluding Exit validation for official lecture credit. Dual quorum thresholds ensure authentic lab attendance.",
                        fontSize = 10.sp,
                        color = DuForestGreenDark,
                        lineHeight = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun IntegrityTelemetryRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    count: String,
    badgeText: String,
    badgeColor: Color,
    badgeBg: Color
) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Text(text = subtitle, fontSize = 10.sp, color = DuTextSecondary)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = count, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Surface(shape = RoundedCornerShape(4.dp), color = badgeBg) {
                    Text(text = badgeText, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = badgeColor, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                }
            }
        }
    }
}
