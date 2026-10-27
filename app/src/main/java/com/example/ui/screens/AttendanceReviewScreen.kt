package com.example.ui.screens

import androidx.compose.foundation.background
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
import com.example.data.model.StudentAttendance
import com.example.data.model.StudentStatus
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceReviewScreen(
    students: List<StudentAttendance>,
    onBackClick: () -> Unit,
    onResolveStudent: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
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
                            text = "Attendance Review",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("review_back_button")
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(DuSurfaceCanvas)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFEFF6FF)) {
                                Text(
                                    text = "Sec A • Cohort 1 • CNCS-B04",
                                    color = Color(0xFF1D4ED8),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "CS-3012: Adv. Database Systems", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text(text = "Semester II • Geofence Radius: 45m GPS / BLE Dual-Lock", fontSize = 10.sp, color = DuTextSecondary)

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = DuGreenContainer
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "64 Enrolled • 60 Verified (93.8%)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                                Text(text = "Live Lock", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreenDark)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 4 Breakdown Cards
                Text(text = "Telemetry Breakdown", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BreakdownCard(
                        count = "56",
                        total = "/ 64",
                        title = "Present",
                        sub = "Dual-Pass (99.4%)",
                        countColor = DuForestGreen,
                        modifier = Modifier.weight(1f)
                    )
                    BreakdownCard(
                        count = "04",
                        total = "",
                        title = "Tardy Arrival",
                        sub = "+15m delay • Late",
                        countColor = DuGoldDark,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BreakdownCard(
                        count = "03",
                        total = "",
                        title = "Beacon Drift",
                        sub = "Flagged Out of Bounds",
                        countColor = DuGoldDark,
                        modifier = Modifier.weight(1f)
                    )
                    BreakdownCard(
                        count = "01",
                        total = "",
                        title = "Absent",
                        sub = "Zero Telemetry",
                        countColor = DuAlertCrimson,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "STUDENT ROSTER AUDIT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary, letterSpacing = 0.5.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(students) { student ->
                StudentAuditItem(
                    student = student,
                    onFlag = { onResolveStudent(student.studentId, false) },
                    onExcuse = { onResolveStudent(student.studentId, true) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Resolve All Flagged Records", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun BreakdownCard(
    count: String,
    total: String,
    title: String,
    sub: String,
    countColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = title, fontSize = 10.sp, color = DuTextSecondary)
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = countColor, modifier = Modifier.size(14.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = count, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = countColor)
                if (total.isNotEmpty()) {
                    Text(text = " $total", fontSize = 11.sp, color = DuTextMuted, modifier = Modifier.padding(bottom = 2.dp))
                }
            }
            Text(text = sub, fontSize = 9.sp, color = DuTextMuted)
        }
    }
}

@Composable
private fun StudentAuditItem(
    student: StudentAttendance,
    onFlag: () -> Unit,
    onExcuse: () -> Unit
) {
    val isPresent = student.finalStatus == StudentStatus.PRESENT || student.initialStatus == StudentStatus.PRESENT || student.isExcused

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(if (isPresent) DuForestGreen else DuAlertCrimson),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = student.initials, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(text = student.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text(text = "${student.studentId} • ${student.department}", fontSize = 10.sp, color = DuTextSecondary)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = if (isPresent) DuGreenContainer else DuCrimsonLight
                ) {
                    Text(
                        text = if (student.isExcused) "Excused" else if (isPresent) "Present" else "Flagged",
                        color = if (isPresent) DuForestGreen else DuAlertCrimson,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            if (student.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = student.note, fontSize = 10.sp, color = if (student.isExcused) DuForestGreen else DuAlertCrimson)
            }

            if (student.isFlagged) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = onFlag,
                        modifier = Modifier.height(30.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Review Override", fontSize = 10.sp, color = DuForestGreen)
                    }
                    Button(
                        onClick = onExcuse,
                        modifier = Modifier.height(30.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Mark Excused", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
