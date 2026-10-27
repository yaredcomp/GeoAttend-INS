package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.InstructorProfile
import com.example.ui.theme.*

@Composable
fun ProfileScreen(
    profile: InstructorProfile,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    var ethiopianCalendar by remember { mutableStateOf(profile.ethiopianCalendarEnabled) }
    var bleBeaconActive by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf(profile.portalLanguage) }
    var calibrationMode by remember { mutableStateOf("Strict (±3m)") }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Faculty Header Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar_dr_dawit),
                            contentDescription = "Dr. Dawit Abebe Tadesse",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(DuForestGreen)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                                Text(
                                    text = "Active Faculty",
                                    color = DuForestGreen,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFEFF6FF)) {
                                Text(
                                    text = "SSO Knox L3",
                                    color = Color(0xFF1D4ED8),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = profile.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )

                        Text(
                            text = "${profile.academicRank} of Computer Science",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Badge, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = profile.staffId,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dept Placement Box
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = DuGreenContainer
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBalance, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Dept. of Computer Science & Software Engineering",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuForestGreenDark
                            )
                            Text(
                                text = profile.officeLocation,
                                fontSize = 9.sp,
                                color = DuForestGreen
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Hardware Attestation
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = DuSurfaceSubtle
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Shield, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Hardware Attestation: ${profile.hardwareAttestationKey}",
                                fontSize = 10.sp,
                                color = DuTextPrimary
                            )
                        }
                        Text(text = "• Secure", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Academic Workload & Sync
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Academic Workload & Sync", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = "2017 E.C. / Semester I", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(Icons.Default.MenuBook, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Text(text = "Reg. Load", fontSize = 9.sp, color = DuGoldDark, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "12.0", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Text(text = "Cr.Hr (3 Active Cohorts)", fontSize = 10.sp, color = DuTextSecondary)
                }
            }

            Surface(modifier = Modifier.weight(1f), shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                        Text(text = "Dual-Cal", fontSize = 9.sp, color = Color(0xFF1D4ED8), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "2017 E.C.", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Text(text = "2024 / 2025 GC Senate", fontSize = 10.sp, color = DuTextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Email Sync Box
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(text = profile.email, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text(text = "• Zimbra Enterprise IMAP sync active", fontSize = 10.sp, color = DuForestGreen)
                    }
                }
                Icon(Icons.Default.Sync, contentDescription = "Sync", tint = DuTextSecondary, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Security & Hardware Telemetry
        Text(text = "Security & Hardware Telemetry", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)

        Spacer(modifier = Modifier.height(8.dp))

        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), color = Color.White, shadowElevation = 1.dp) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Biometric toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Fingerprint, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Biometric Attendance Sign-off", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = "Fingerprint & Face ID hardware-backed", fontSize = 10.sp, color = DuTextSecondary)
                        }
                    }
                    Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                        Text(text = "Enabled", color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = DuSurfaceBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Geofence Calibration Pills
                Text(text = "Geofence Boundary Calibration: $calibrationMode", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("Strict (±3m)", "Standard", "Quad/Field").forEach { mode ->
                        val isSel = calibrationMode == mode
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { calibrationMode = mode },
                            color = if (isSel) DuForestGreen else DuSurfaceSubtle
                        ) {
                            Box(modifier = Modifier.padding(vertical = 6.dp), contentAlignment = Alignment.Center) {
                                Text(
                                    text = mode,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSel) Color.White else DuTextPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = DuSurfaceBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // BLE Presence Beacon Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "BLE Presence Beacon", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        Text(text = "Auto-broadcast when inside lecture radius", fontSize = 10.sp, color = DuTextSecondary)
                    }
                    Switch(
                        checked = bleBeaconActive,
                        onCheckedChange = { bleBeaconActive = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = DuForestGreen, checkedTrackColor = DuGreenContainer)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Knox StrongBox TEE Root
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    color = DuSurfaceSubtle
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Key, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(text = "Knox StrongBox TEE Root", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                                Text(text = profile.knoxStrongBoxHash, fontSize = 9.sp, color = DuTextMuted)
                            }
                        }
                        Text(text = "Audit", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Preferences & System
        Text(text = "Preferences & System", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)

        Spacer(modifier = Modifier.height(8.dp))

        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), color = Color.White, shadowElevation = 1.dp) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Ethiopian calendar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DateRange, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Ethiopian Calendar (E.C.)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = "Primary view for timetables and deadlines", fontSize = 10.sp, color = DuTextSecondary)
                        }
                    }
                    Switch(
                        checked = ethiopianCalendar,
                        onCheckedChange = { ethiopianCalendar = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = DuForestGreen, checkedTrackColor = DuGreenContainer)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = DuSurfaceBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Language
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Translate, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Portal Language", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = "English (US) • አማርኛ Secondary", fontSize = 10.sp, color = DuTextSecondary)
                        }
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = DuSurfaceSubtle,
                        modifier = Modifier.clickable {
                            selectedLanguage = if (selectedLanguage == "EN") "AM" else "EN"
                        }
                    ) {
                        Text(
                            text = if (selectedLanguage == "EN") "EN / አማ" else "አማ / EN",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuForestGreen,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Institutional Governance
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), color = DuSurfaceSubtle) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.Gavel, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Senate Legislation Art. 114.2", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Text(
                        text = "Instructor verified presence mandatory during credit allocation periods. Geofenced telemetry serves as official record.",
                        fontSize = 10.sp,
                        color = DuTextSecondary,
                        lineHeight = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Out Button
        Button(
            onClick = onSignOut,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("profile_sign_out_button"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE2E2)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.Logout, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign Out of DU Instructor Portal", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuAlertCrimson)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "GeoAttend INS • Instructor Mobile Portal\nv2.4.2 (Production Release • Build 24209)\nDilla University • Green Land Technology Center",
            fontSize = 10.sp,
            color = DuTextMuted,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}
