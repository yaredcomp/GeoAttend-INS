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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ClassSession
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartClassConfirmationScreen(
    session: ClassSession,
    onBackClick: () -> Unit,
    onEnterLiveDashboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showQrDialog by remember { mutableStateOf(false) }
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
                            text = "Start Class Confirm...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("start_confirm_back_button")
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Verified GPS Lock Circle Icon
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(DuGreenContainer)
                    .border(3.dp, DuForestGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = DuForestGreen,
                    modifier = Modifier.size(46.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Surface(shape = RoundedCornerShape(12.dp), color = DuGreenContainer) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "GPS LOCK", color = DuForestGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Session Initialized!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = DuTextPrimary
                )
            )

            Text(
                text = "Geofence radar is actively broadcasting to registered students in ${session.room}.",
                fontSize = 12.sp,
                color = DuTextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Live Student Join Window (Broadcasting)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFEFF6FF),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Sensors, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Live Student Join Window",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E40AF)
                            )
                        }
                        Surface(shape = RoundedCornerShape(4.dp), color = DuForestGreen) {
                            Text(
                                text = "BROADCASTING",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "48",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = " / 64 Students in-range",
                                fontSize = 12.sp,
                                color = DuTextSecondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                        Text(
                            text = "75% Verified",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuForestGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = DuForestGreen,
                        trackColor = DuSurfaceBorder
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Rolling Beacon Code
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Bluetooth, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = "Rolling Beacon Code", fontSize = 10.sp, color = DuTextSecondary)
                                    Text(text = session.rollingBeaconCode, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Sync, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Sync Clock\n28s rotate", fontSize = 9.sp, color = DuGoldDark, lineHeight = 11.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Official Session Token Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "OFFICIAL SESSION TOKEN", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                            Text(text = session.sessionToken, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = DuSurfaceBorder)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Course Unit", fontSize = 10.sp, color = DuTextSecondary)
                    Text(text = "${session.courseCode}: ${session.courseName}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Text(text = "${session.section} • Morning Cohort", fontSize = 11.sp, color = DuTextSecondary)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.School, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Dr. Dawit Abebe Tadesse", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = session.room, fontSize = 12.sp, color = DuTextPrimary)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Started at & scheduled end
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = "Started At", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = session.actualStart ?: "09:31:14 AM", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "+1m 14s delay", fontSize = 9.sp, color = DuGoldDark, fontWeight = FontWeight.Bold)
                            }
                        }

                        Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = "Scheduled End", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = session.scheduledEnd, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "2hr Allocated", fontSize = 9.sp, color = DuTextMuted)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Geofence guardrail
                    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), color = DuGreenContainer) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Shield, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(text = "Geofence Guardrail", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                                    Text(text = "25m Radius lock active • BLE ON", fontSize = 9.sp, color = DuForestGreenDark)
                                }
                            }
                            Surface(shape = RoundedCornerShape(4.dp), color = DuForestGreen) {
                                Text(text = "Secured", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Guideline warning card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFEE2E2).copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECACA))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Important Instructor Guideline",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuAlertCrimson
                        )
                        Text(
                            text = "Please remain inside the classroom and keep GPS active. Logging remains tethered to your host device until you tap 'Conclude Class' on the live session dashboard.",
                            fontSize = 11.sp,
                            color = DuTextPrimary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Primary Action
            Button(
                onClick = onEnterLiveDashboard,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("start_confirm_enter_dashboard_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Enter Live Class Dashboard",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            // QR Projection Button
            OutlinedButton(
                onClick = { showQrDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("start_confirm_project_qr_button"),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFEFF6FF),
                    contentColor = Color(0xFF1D4ED8)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.QrCode2, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Project Attendance QR on Screen",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1D4ED8)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "☁ Synced with Dilla University SIS / Registrar Gateway\nAcademic Year 2024/2025 • Semester II • Oda Ya'a ICT Infrastructure",
                fontSize = 10.sp,
                color = DuTextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showQrDialog) {
        AlertDialog(
            onDismissRequest = { showQrDialog = false },
            title = {
                Text(
                    text = "Classroom Projector QR Beacon",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .background(Color.White)
                            .border(2.dp, DuForestGreen, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = "Projector QR",
                            tint = DuForestGreen,
                            modifier = Modifier.size(150.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Rolling Token: ${session.rollingBeaconCode}",
                        fontWeight = FontWeight.Bold,
                        color = DuForestGreen,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "Students scanning this QR must still satisfy native GPS perimeter requirements in Room CNCS-B04.",
                        fontSize = 11.sp,
                        color = DuTextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showQrDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen)
                ) {
                    Text("Close Projector View")
                }
            }
        )
    }
}
