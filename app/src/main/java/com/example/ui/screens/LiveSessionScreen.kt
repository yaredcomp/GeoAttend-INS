package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.data.model.StudentAttendance
import com.example.data.model.StudentStatus
import com.example.ui.components.CircularProgressRing
import com.example.ui.components.StatusChip
import com.example.ui.components.StatusType
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveSessionScreen(
    session: ClassSession,
    students: List<StudentAttendance>,
    onBackClick: () -> Unit,
    onTriggerSecondaryPulse: () -> Unit,
    onStudentOverrideClick: (StudentAttendance) -> Unit,
    onConcludeClassClick: () -> Unit,
    onViewCheckpointClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("ALL") } // "ALL", "PRESENT", "PENDING"
    var pingMessage by remember { mutableStateOf<String?>(null) }

    val filteredStudents = remember(students, searchQuery, selectedFilter) {
        students.filter { student ->
            val matchesQuery = student.name.contains(searchQuery, ignoreCase = true) ||
                    student.studentId.contains(searchQuery, ignoreCase = true)
            val matchesFilter = when (selectedFilter) {
                "PRESENT" -> student.finalStatus == StudentStatus.PRESENT || student.initialStatus == StudentStatus.PRESENT
                "PENDING" -> student.initialStatus != StudentStatus.PRESENT && !student.isExcused
                else -> true
            }
            matchesQuery && matchesFilter
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "LIVE SESSION TELEMETRY",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuForestGreen
                        )
                        Text(
                            text = "${session.courseCode}: Live Roll-Call",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("live_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DuTextPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onViewCheckpointClick) {
                        Icon(Icons.Default.Layers, contentDescription = "Checkpoint", tint = DuForestGreen)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DuSurfaceCard)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(DuSurfaceCanvas)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                // Top status badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(shape = RoundedCornerShape(20.dp), color = DuGreenContainer) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "LIVE CLASS IN PROGRESS",
                                color = DuForestGreen,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(shape = RoundedCornerShape(20.dp), color = DuSurfaceSubtle) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.MeetingRoom, contentDescription = null, tint = DuTextSecondary, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = session.room,
                                color = DuTextPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "SEC A • COMPUTER SCIENCE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuForestGreen
                )
                Text(
                    text = "${session.courseCode}: ${session.courseName}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DuTextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Schedule and Elapsed Time Cards
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Schedule, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Schedule", fontSize = 10.sp, color = DuTextSecondary)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("09:30 – 11:30 AM", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text("Start: 09:31:14 AM (+1m 14s)", fontSize = 9.sp, color = DuGoldDark, fontWeight = FontWeight.Bold)
                        }
                    }

                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        shadowElevation = 1.dp
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Timelapse, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Elapsed Time", fontSize = 10.sp, color = DuTextSecondary)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("00:42:21", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                            Text("Geofence locked • Active", fontSize = 9.sp, color = DuForestGreenDark)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Telemetry & Roll-Call Gauge Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Radar, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Telemetry & Roll-Call",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DuTextPrimary
                                )
                            }
                            Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFEFF6FF)) {
                                Text(
                                    text = "GPS Locked • 25m Radius",
                                    color = Color(0xFF1D4ED8),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressRing(
                                percentage = session.quorumPercentage,
                                size = 80.dp,
                                subtitle = "In Zone"
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Enrolled Roster: ${session.enrolledCount} Students",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DuTextPrimary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Present (Geofence): ",
                                        fontSize = 11.sp,
                                        color = DuTextSecondary
                                    )
                                    Text(
                                        text = "${session.presentCount} / ${session.enrolledCount}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DuForestGreen
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${session.flaggedCount} Missing / Flagged",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DuAlertCrimson
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = DuCrimsonLight,
                                        modifier = Modifier.clickable { selectedFilter = "PENDING" }
                                    ) {
                                        Text(
                                            text = "View ${session.flaggedCount} Unverified",
                                            color = DuAlertCrimson,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Mid-Lecture Checkpoint Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFFEF3C7).copy(alpha = 0.6f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Mid-Lecture Checkpoint",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF92400E)
                                )
                                Text(
                                    text = "Stage 2: 21m 45s left • Broadcast Active",
                                    fontSize = 10.sp,
                                    color = Color(0xFF78350F)
                                )
                            }
                        }

                        Button(
                            onClick = onTriggerSecondaryPulse,
                            colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                            modifier = Modifier
                                .height(32.dp)
                                .testTag("live_trigger_pulse_button")
                        ) {
                            Text("Pulse Beacon", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Search & Filter Bar
                Text(
                    text = "Roster Status (Real-time BLE • GPS)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search student name or DU ID...", fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("live_search_input"),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = DuTextSecondary)
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tabs: All (64), Present (59), Pending (5)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterPill(
                        label = "All (${students.size})",
                        isSelected = selectedFilter == "ALL",
                        onClick = { selectedFilter = "ALL" },
                        modifier = Modifier.weight(1f)
                    )
                    FilterPill(
                        label = "Present (${session.presentCount})",
                        isSelected = selectedFilter == "PRESENT",
                        onClick = { selectedFilter = "PRESENT" },
                        modifier = Modifier.weight(1f)
                    )
                    FilterPill(
                        label = "Pending (${session.flaggedCount})",
                        isSelected = selectedFilter == "PENDING",
                        onClick = { selectedFilter = "PENDING" },
                        isWarning = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            // Student Roster List
            items(filteredStudents) { student ->
                StudentRosterItem(
                    student = student,
                    onOverrideClick = { onStudentOverrideClick(student) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                // Classroom Geofence Beacon controls
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Classroom Geofence Beacon",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                                Text(
                                    text = "BLE-B04 Active",
                                    color = DuForestGreen,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { pingMessage = "Beacon ping broadcasted to 64 devices." },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .testTag("live_ping_beacon_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFF6FF)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.Sensors, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Ping Beacon", fontSize = 11.sp, color = Color(0xFF1D4ED8), fontWeight = FontWeight.Bold)
                            }

                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.FactCheck, contentDescription = null, tint = DuTextPrimary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Manual Roll Call", fontSize = 11.sp, color = DuTextPrimary)
                            }
                        }

                        if (pingMessage != null) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = pingMessage!!,
                                fontSize = 10.sp,
                                color = DuForestGreen,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Footer Warning Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFFEF3C7).copy(alpha = 0.5f)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Closing or backgrounding this application does not end class session. You must tap Conclude Class to seal attendance records.",
                            fontSize = 11.sp,
                            color = Color(0xFF78350F),
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Conclude Class Action Button
                OutlinedButton(
                    onClick = onConcludeClassClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("live_conclude_class_button"),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFFFEE2E2),
                        contentColor = DuAlertCrimson
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, DuAlertCrimson),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.StopCircle, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Conclude & End Class Session",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuAlertCrimson
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun FilterPill(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    isWarning: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = when {
            isSelected && isWarning -> DuAlertCrimson
            isSelected -> DuForestGreen
            else -> Color.White
        },
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            when {
                isSelected && isWarning -> DuAlertCrimson
                isSelected -> DuForestGreen
                else -> DuSurfaceBorder
            }
        )
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = when {
                    isSelected -> Color.White
                    isWarning -> DuAlertCrimson
                    else -> DuTextPrimary
                }
            )
        }
    }
}

@Composable
private fun StudentRosterItem(
    student: StudentAttendance,
    onOverrideClick: () -> Unit
) {
    val isPresent = student.finalStatus == StudentStatus.PRESENT || student.initialStatus == StudentStatus.PRESENT || student.isExcused

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Student Initials Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            student.isExcused -> Color(0xFF0284C7)
                            isPresent -> DuForestGreen
                            student.initialStatus == StudentStatus.OUT_OF_RANGE -> DuAlertCrimson
                            else -> DuGoldDark
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.initials,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = student.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
                Text(
                    text = "${student.studentId} • ${student.checkInTime}",
                    fontSize = 10.sp,
                    color = DuTextSecondary
                )
                if (student.note.isNotEmpty()) {
                    Text(
                        text = student.note,
                        fontSize = 9.sp,
                        color = if (student.isExcused) DuForestGreen else DuAlertCrimson
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = when {
                        student.isExcused -> Color(0xFFE0F2FE)
                        isPresent -> DuGreenContainer
                        student.initialStatus == StudentStatus.OUT_OF_RANGE -> DuCrimsonLight
                        else -> Color(0xFFFEF3C7)
                    }
                ) {
                    Text(
                        text = if (student.isExcused) "Excused" else student.verificationTag,
                        color = when {
                            student.isExcused -> Color(0xFF0369A1)
                            isPresent -> DuForestGreen
                            student.initialStatus == StudentStatus.OUT_OF_RANGE -> DuAlertCrimson
                            else -> DuGoldDark
                        },
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                if (student.isFlagged) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Override",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuForestGreen,
                        modifier = Modifier
                            .clickable { onOverrideClick() }
                            .padding(2.dp)
                    )
                } else {
                    Text(
                        text = student.confidenceRate,
                        fontSize = 9.sp,
                        color = DuTextMuted
                    )
                }
            }
        }
    }
}
