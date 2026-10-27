package com.example.ui.screens

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
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcludeClassModal(
    session: ClassSession,
    students: List<StudentAttendance>,
    onBackToLive: () -> Unit,
    onResolveStudent: (String, Boolean) -> Unit,
    onFinalSealSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isComplianceChecked by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val flaggedStudents = students.filter { it.isFlagged }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "REGISTRAR PROTOCOL • DU-SIS v4.2",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuGoldDark
                        )
                        Text(
                            text = "Conclude Lecture Session",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackToLive,
                        modifier = Modifier.testTag("conclude_back_button")
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
            // Notice card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFEF3C7).copy(alpha = 0.6f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "${session.courseCode}: ${session.courseName}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E)
                        )
                        Text(
                            text = "Room ${session.room} • College of Natural & Computational Sciences",
                            fontSize = 10.sp,
                            color = Color(0xFF78350F)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Closing or backgrounding the mobile app does NOT terminate the session. You must explicitly conclude and digitally seal the attendance ledger to commit to DU Registrar SIS.",
                            fontSize = 10.sp,
                            color = DuTextPrimary,
                            lineHeight = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Session Chronometer Card
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
                            Icon(Icons.Default.Timelapse, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Session Chronometer",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                        }
                        Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                            Text(
                                text = "Ready for Sign-Off",
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
                        ChronometerBox(
                            label = "Scheduled Slot",
                            mainText = "09:30 AM – 11:30 AM",
                            subText = "Target: 2h 00m",
                            modifier = Modifier.weight(1f)
                        )
                        ChronometerBox(
                            label = "Actual Inception",
                            mainText = "09:31:14 AM",
                            subText = "GPS Locked",
                            subColor = DuForestGreen,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChronometerBox(
                            label = "Conclude Timestamp",
                            mainText = "11:28:40 AM",
                            subText = "-1m 20s early",
                            subColor = DuGoldDark,
                            modifier = Modifier.weight(1f)
                        )
                        ChronometerBox(
                            label = "Total In-Hall Time",
                            mainText = "01h 57m 26s",
                            subText = "Geofence 98.6% up",
                            subColor = DuForestGreen,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Session Quota Fulfilled", fontSize = 10.sp, color = DuTextSecondary)
                        Text(text = "97.8% Completed", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { 0.978f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = DuForestGreen,
                        trackColor = DuGreenContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Pre-Conclude Audit Summary
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
                        Text(
                            text = "Pre-Conclude Audit Summary",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFEFF6FF)) {
                            Text(
                                text = "Class Roster: 64",
                                color = Color(0xFF1D4ED8),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AuditStatPill(number = "59", label = "PRESENT", color = DuForestGreen)
                        AuditStatPill(number = "3", label = "TARDY", color = DuGoldDark)
                        AuditStatPill(number = "${flaggedStudents.size}", label = "PENDING", color = DuAlertCrimson)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Discrepancies Requiring Adjudication
            if (flaggedStudents.isNotEmpty()) {
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
                                Icon(Icons.Default.Warning, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Discrepancies Requiring Adjudication (${flaggedStudents.size})",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DuTextPrimary
                                )
                            }
                            Surface(shape = RoundedCornerShape(4.dp), color = DuCrimsonLight) {
                                Text(
                                    text = "Action mandatory",
                                    color = DuAlertCrimson,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Students recorded anomaly telemetry. Select resolution status prior to cryptographic sealing.",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        flaggedStudents.forEach { student ->
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                shape = RoundedCornerShape(10.dp),
                                color = DuSurfaceSubtle
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = student.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                        Surface(shape = RoundedCornerShape(4.dp), color = DuCrimsonLight) {
                                            Text(
                                                text = student.confidenceRate,
                                                color = DuAlertCrimson,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                    Text(text = "ID: ${student.studentId} • ${student.note}", fontSize = 10.sp, color = DuTextSecondary)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedButton(
                                            onClick = { onResolveStudent(student.studentId, false) },
                                            modifier = Modifier.height(32.dp),
                                            shape = RoundedCornerShape(6.dp),
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                        ) {
                                            Text("Flag for Registrar", fontSize = 10.sp, color = DuAlertCrimson)
                                        }

                                        Button(
                                            onClick = { onResolveStudent(student.studentId, true) },
                                            modifier = Modifier.height(32.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                                            shape = RoundedCornerShape(6.dp),
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                        ) {
                                            Text("Mark as Excused", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            // Instructor Digital Seal Certification
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Instructor Digital Seal Certification",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { isComplianceChecked = !isComplianceChecked },
                        color = Color(0xFFEFF6FF)
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                            Checkbox(
                                checked = isComplianceChecked,
                                onCheckedChange = { isComplianceChecked = it },
                                colors = CheckboxDefaults.colors(checkedColor = DuForestGreen),
                                modifier = Modifier.testTag("conclude_compliance_checkbox")
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = "Institutional Compliance Declaration",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E40AF)
                                )
                                Text(
                                    text = "I solemnly certify that all 64 attendance telemetry records accurately reflect student physical presence in Hall CNCS-B04 pursuant to Dilla University Academic Regulations (Senate Legislation Article 114.2).",
                                    fontSize = 10.sp,
                                    color = DuTextPrimary,
                                    lineHeight = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.VpnKey, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Signer: Dr. Dawit Abebe (DU-FAC-089)", fontSize = 10.sp, color = DuTextSecondary)
                        }
                        Text(text = "• SEAL ONLINE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action: Digitally Seal & Conclude Class
            Button(
                onClick = onFinalSealSuccess,
                enabled = isComplianceChecked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("conclude_seal_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Digitally Seal & Conclude Class",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onBackToLive,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "← Return to Live Session Dashboard", fontSize = 12.sp, color = DuTextPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ChronometerBox(
    label: String,
    mainText: String,
    subText: String,
    subColor: Color = DuTextSecondary,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = label, fontSize = 9.sp, color = DuTextSecondary)
            Text(text = mainText, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = subText, fontSize = 9.sp, color = subColor, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun AuditStatPill(number: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, fontSize = 9.sp, color = DuTextSecondary)
    }
}
