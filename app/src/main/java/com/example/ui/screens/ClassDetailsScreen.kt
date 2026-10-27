package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.CourseSchedule
import com.example.data.model.InstructorProfile
import com.example.ui.components.SpatialRadarCanvas
import com.example.ui.components.StatusChip
import com.example.ui.components.StatusType
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassDetailsScreen(
    schedule: CourseSchedule,
    profile: InstructorProfile,
    isInsideGeofence: Boolean,
    onBackClick: () -> Unit,
    onProceedToBiometrics: () -> Unit,
    onViewCohortClick: () -> Unit,
    onRadarExpandClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                            text = "Class Details",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("details_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DuTextPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.HelpOutline, contentDescription = "Help", tint = DuTextSecondary)
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
            // Initiation Progress Bar (Step 1 of 3)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "LECTURE INITIATION • STEP 1 OF 3",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuForestGreen
                    )
                }
                Text(
                    text = "2017 E.C. Sem II",
                    fontSize = 11.sp,
                    color = DuTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { 0.33f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = DuForestGreen,
                trackColor = DuGreenContainer
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Course Module Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "COURSE MODULE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuGoldDark
                            )
                            Text(
                                text = "${schedule.courseCode}: ${schedule.courseName}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = "${schedule.section} • Dept. of Computer Science",
                                fontSize = 11.sp,
                                color = DuTextSecondary
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFEFF6FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Dns, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(22.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Lecturer Row
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(DuForestGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("DA", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = profile.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DuTextPrimary
                                )
                                Text(
                                    text = "Staff ID: ${profile.staffId} • Chief Lecturer",
                                    fontSize = 10.sp,
                                    color = DuTextSecondary
                                )
                            }
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SPATIAL & TIME SCHEDULE
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SPATIAL & TIME SCHEDULE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextSecondary,
                    letterSpacing = 0.5.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Live Sync", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(16.dp))
                            Surface(shape = RoundedCornerShape(4.dp), color = DuSurfaceSubtle) {
                                Text("2h 00m", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("09:30 AM", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text("To 11:30 AM today", fontSize = 10.sp, color = DuTextSecondary)
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(Icons.Default.Group, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                                Text("100% Enrolled", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuForestGreen, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("64 Students", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text("43 Geofence • 21 Near", fontSize = 10.sp, color = DuTextSecondary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Hall Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEFF6FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.MeetingRoom, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Allocated Lecture Hall",
                                fontSize = 10.sp,
                                color = DuTextSecondary
                            )
                            Text(
                                text = schedule.room + ", Oda Ya'a Complex",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = "Natural & Computational Sciences Block • Level 2",
                                fontSize = 10.sp,
                                color = DuTextMuted
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(DuSurfaceSubtle)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Sensors, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Beacon: ${schedule.beaconId}", fontSize = 10.sp, color = DuTextPrimary)
                        }
                        Text(text = "• 12m range", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Spatial Radar Telemetry Card Preview
            Text(
                text = "Spatial Radar Telemetry",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextSecondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            SpatialRadarCanvas(
                isInsidePerimeter = isInsideGeofence,
                distanceToPodium = if (isInsideGeofence) 8.2 else 58.4,
                accuracyMeters = if (isInsideGeofence) 1.2 else 18.2,
                modifier = Modifier.clickable { onRadarExpandClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SECURITY & READINESS CHECKLIST (5/5 PASSED)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SECURITY & READINESS CHECKLIST",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isInsideGeofence) DuGreenContainer else DuCrimsonLight
                ) {
                    Text(
                        text = if (isInsideGeofence) "5 / 5 PASSED" else "4 / 5 PASSED",
                        color = if (isInsideGeofence) DuForestGreen else DuAlertCrimson,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ChecklistItem(
                title = "Assigned Schedule Window",
                subtitle = "Opens 09:25 AM (5m buffer active)",
                tag = "On Time",
                tagColor = DuForestGreen,
                tagBg = DuGreenContainer,
                isPassed = true
            )

            Spacer(modifier = Modifier.height(6.dp))

            ChecklistItem(
                title = "Geofence Perimeter Lock",
                subtitle = if (isInsideGeofence) "Lecturer detected 8.2m inside boundary" else "Perimeter breach detected (58m)",
                tag = if (isInsideGeofence) "Inside" else "Breach",
                tagColor = if (isInsideGeofence) DuForestGreen else DuAlertCrimson,
                tagBg = if (isInsideGeofence) DuGreenContainer else DuCrimsonLight,
                isPassed = isInsideGeofence
            )

            Spacer(modifier = Modifier.height(6.dp))

            ChecklistItem(
                title = "BLE Mesh Signal Integrity",
                subtitle = "Strong RSSI -58 dBm, Hall beacon locked",
                tag = "-58 dBm",
                tagColor = Color(0xFF0369A1),
                tagBg = Color(0xFFE0F2FE),
                isPassed = true
            )

            Spacer(modifier = Modifier.height(6.dp))

            ChecklistItem(
                title = "Enrolled Cohort Quorum",
                subtitle = "Quorum threshold met, 64 synced to SIS",
                tag = "Synced",
                tagColor = DuForestGreen,
                tagBg = DuGreenContainer,
                isPassed = true
            )

            Spacer(modifier = Modifier.height(6.dp))

            ChecklistItem(
                title = "Device & Location Hardware Trust",
                subtitle = "No mock GPS detected • MoE v2.4 compliant",
                tag = "Trusted",
                tagColor = Color(0xFF1D4ED8),
                tagBg = Color(0xFFEFF6FF),
                isPassed = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Start class requirement box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = if (isInsideGeofence) DuGreenContainer else DuCrimsonLight
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = if (isInsideGeofence) Icons.Default.Lock else Icons.Default.LockOpen,
                        contentDescription = null,
                        tint = if (isInsideGeofence) DuForestGreen else DuAlertCrimson,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (isInsideGeofence) "Start Class Requirement Met" else "Geofence Verification Blocked",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isInsideGeofence) DuForestGreenDark else DuAlertCrimson
                        )
                        Text(
                            text = if (isInsideGeofence) "All 5 security parameters validated. Proceeding to Biometric Authentication will lock attendance telemetry for this lecture and trigger student broadcast check-in."
                            else "Physical location must be inside Room CNCS-B04 boundary before starting attendance.",
                            fontSize = 11.sp,
                            color = DuTextPrimary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Action Buttons
            Button(
                onClick = onProceedToBiometrics,
                enabled = isInsideGeofence,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("details_proceed_biometric_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Proceed to Biometric Verification",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onViewCohortClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("details_view_cohort_button"),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Badge, contentDescription = null, tint = DuTextPrimary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("View Cohort (64)", fontSize = 12.sp, color = DuTextPrimary)
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.ReportProblem, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Report Issue", fontSize = 12.sp, color = DuAlertCrimson)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ChecklistItem(
    title: String,
    subtitle: String,
    tag: String,
    tagColor: Color,
    tagBg: Color,
    isPassed: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isPassed) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                tint = if (isPassed) DuForestGreen else DuAlertCrimson,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Text(text = subtitle, fontSize = 10.sp, color = DuTextSecondary)
            }
            Surface(shape = RoundedCornerShape(4.dp), color = tagBg) {
                Text(
                    text = tag,
                    color = tagColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}
