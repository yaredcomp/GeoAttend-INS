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
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionSummaryScreen(
    session: ClassSession,
    onReturnToDashboard: () -> Unit,
    onDownloadPdf: () -> Unit,
    modifier: Modifier = Modifier
) {
    var copyNotice by remember { mutableStateOf(false) }
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
                            text = "Session Summary",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onReturnToDashboard,
                        modifier = Modifier.testTag("summary_back_button")
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
            // Success Card Header
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(shape = RoundedCornerShape(20.dp), color = DuGreenContainer) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(DuForestGreen))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "SEALED & ARCHIVED",
                                    color = DuForestGreen,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Lecture Successfully Concluded",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuTextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Session Receipt ID
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "SESSION RECEIPT ID", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary)
                                Text(
                                    text = "Copy",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DuForestGreen,
                                    modifier = Modifier.clickable { copyNotice = true }
                                )
                            }
                            Text(
                                text = session.sessionToken,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuForestGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Timestamp: Thursday, Oct 24, 2024 • 11:30:12 AM", fontSize = 10.sp, color = DuTextSecondary)
                            Text(text = "Ledger Hash: ${session.ledgerHash}", fontSize = 9.sp, color = DuTextMuted)
                        }
                    }

                    if (copyNotice) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Receipt token copied to clipboard.", fontSize = 10.sp, color = DuForestGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Timing Audit Matrix
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
                            Icon(Icons.Default.Timer, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Timing Audit Matrix", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                        Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                            Text(text = "99.1% Quota Met", color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "Class Start", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = "09:31:14", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "+1m 14s delay", fontSize = 9.sp, color = DuGoldDark, fontWeight = FontWeight.Bold)
                                Text(text = "Sched: 09:30:00 AM", fontSize = 9.sp, color = DuTextMuted)
                            }
                        }

                        Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "Class Concluded", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = "11:30:12", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = "✓ +12s buffer", fontSize = 9.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                                Text(text = "Sched: 11:30:00 AM", fontSize = 9.sp, color = DuTextMuted)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Total Verified Instruction: 01h 58m 58s", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { 0.991f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = DuForestGreen,
                        trackColor = DuGreenContainer
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Room CNCS-B04, Oda Ya'a Complex (Lat 6.410317, Lon 38.313422)",
                                fontSize = 10.sp,
                                color = DuTextPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Attendance & Quorum
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
                            Icon(Icons.Default.Groups, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Attendance & Quorum", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                        Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                            Text(text = "64 Enrolled", color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Quorum threshold: 75% met • 95.3% Credited Total", fontSize = 11.sp, color = DuTextSecondary)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuorumPill(metric = "59", pct = "92.2%", label = "Present (Full)", color = DuForestGreen, modifier = Modifier.weight(1f))
                        QuorumPill(metric = "2", pct = "3.1%", label = "Excused/Over", color = Color(0xFF0369A1), modifier = Modifier.weight(1f))
                        QuorumPill(metric = "2", pct = "3.1%", label = "Late / Tardy", color = DuGoldDark, modifier = Modifier.weight(1f))
                        QuorumPill(metric = "1", pct = "1.6%", label = "Unexcused", color = DuAlertCrimson, modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Central Registrar SIS Link
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBalance, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Central Registrar SIS Link", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFEFF6FF)
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CloudDone, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Synced with Central SIS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E40AF))
                                Text(text = "Payload acknowledged by Registrar Gateway", fontSize = 9.sp, color = Color(0xFF1D4ED8))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Curriculum Quota: 2 / 30 Hours (2017 Sem II)", fontSize = 10.sp, color = DuTextSecondary)
                        Text(text = "6.7% completed", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { 0.067f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = DuForestGreen,
                        trackColor = DuSurfaceBorder
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Faculty Workload: Dr. Dawit Abebe (+2.0 Credit Hrs)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Button(
                onClick = onReturnToDashboard,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("summary_return_dashboard_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.GridView, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Return to Instructor Dashboard", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onDownloadPdf,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("summary_download_pdf_button"),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFEFF6FF),
                    contentColor = Color(0xFF1D4ED8)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Download, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Download Official PDF Summary", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1D4ED8))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Institutional Seal Disclaimer
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = DuGreenContainer
            ) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Institutional Compliance & Audit Seal: Cryptographically signed by Dr. Dawit Abebe (DU/FAC/2024/089). Dilla University Academic Records and Quality Assurance Directorate.",
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
private fun QuorumPill(
    metric: String,
    pct: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = metric, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = color)
            Text(text = pct, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = color)
            Text(text = label, fontSize = 8.sp, color = DuTextMuted)
        }
    }
}
