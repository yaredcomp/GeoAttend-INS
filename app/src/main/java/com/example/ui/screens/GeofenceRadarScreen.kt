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
import com.example.data.model.CourseSchedule
import com.example.ui.components.SpatialRadarCanvas
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeofenceRadarScreen(
    schedule: CourseSchedule,
    isInsideGeofence: Boolean,
    onBackClick: () -> Unit,
    onVerifyAtDoorSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLevel by remember { mutableStateOf("Level 2 (Active)") }
    val levels = listOf("Basement", "Level 1 (Ground)", "Level 2 (Active)", "Level 3")
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
                            text = "Classroom Geofence Radar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("radar_back_button")
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
            // Dual GNSS + BLE Triangulation Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = DuGreenContainer
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Dual GNSS + BLE Triangulation • ±1.8m accuracy",
                            color = DuForestGreenDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Icon(Icons.Default.Layers, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Active Geofence Target
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                                Text(
                                    text = "Active Geofence Target",
                                    color = DuForestGreen,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(shape = RoundedCornerShape(4.dp), color = DuSurfaceSubtle) {
                                Text(
                                    text = "Auto-Lock",
                                    color = DuTextSecondary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Icon(Icons.Default.MeetingRoom, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(20.dp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Room CNCS-B04, Oda Ya'a Complex",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuTextPrimary
                    )
                    Text(
                        text = "${schedule.courseCode}: ${schedule.courseName}",
                        fontSize = 12.sp,
                        color = DuTextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Next Lecture: 01:30 PM • SE-4210 in Lab 02",
                                fontSize = 11.sp,
                                color = DuTextPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Level Switcher Tabs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                levels.forEach { lvl ->
                    val isSelected = selectedLevel == lvl
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { selectedLevel = lvl },
                        color = if (isSelected) DuForestGreenDark else Color.White,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) DuForestGreen else DuSurfaceBorder
                        )
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = lvl.replace(" (Active)", "").replace(" (Ground)", ""),
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else DuTextPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Large Radar Canvas
            SpatialRadarCanvas(
                isInsidePerimeter = isInsideGeofence,
                distanceToPodium = if (isInsideGeofence) 8.2 else 58.4,
                accuracyMeters = if (isInsideGeofence) 1.8 else 18.5,
                modifier = Modifier.height(290.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Legend indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                LegendItem(color = Color(0xFF4ADE80), label = "Enclosed Students (7)")
                LegendItem(color = DuGold, label = "Corridor Queue (3)")
                LegendItem(color = Color(0xFF38BDF8), label = "BLE Transmitters")
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Proximity Card (12 meters to Room Entrance)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(DuForestGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.DirectionsWalk, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isInsideGeofence) "12 meters to Room Entrance" else "58 meters to Hall Entrance",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Text(
                            text = if (isInsideGeofence) "Estimated arrival: ~30 seconds • Level 2 Wing" else "Outside authorized lecture perimeter",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )
                    }
                    Icon(Icons.Default.TurnRight, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(24.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3 Telemetry chips (GNSS, BLE, Campus Net)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TelemetryParamCard(
                    icon = Icons.Default.SatelliteAlt,
                    title = "Dual GNSS",
                    metric = "14 Satellites",
                    status = "±1.8m High Fix",
                    modifier = Modifier.weight(1f)
                )
                TelemetryParamCard(
                    icon = Icons.Default.BluetoothSearching,
                    title = "BLE RSSI",
                    metric = "-56 dBm",
                    status = "Optimal Signal",
                    modifier = Modifier.weight(1f)
                )
                TelemetryParamCard(
                    icon = Icons.Default.Wifi,
                    title = "Campus Net",
                    metric = "DU-Faculty",
                    status = "eduroam mesh",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Active BLE Transmitters
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
                            text = "Active BLE Perimeter Transmitters",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Text(
                            text = "2 Calibrated",
                            fontSize = 10.sp,
                            color = DuTextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    BleBeaconRow(
                        beaconId = "BLE-DU-CNCS-04-A",
                        location = "Instructor Podium • Oda Ya'a 2nd Fl",
                        rssi = "-52 dBm (Strong)",
                        rssiColor = DuForestGreen
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    BleBeaconRow(
                        beaconId = "BLE-DU-CNCS-04-B",
                        location = "Rear Emergency Doorway",
                        rssi = "-68 dBm (Fair)",
                        rssiColor = Color(0xFF0284C7)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onVerifyAtDoorSuccess,
                enabled = isInsideGeofence,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("radar_verify_at_door_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Verify Geofence at Classroom Door",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Explore, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Walking Guide", fontSize = 11.sp, color = DuForestGreen)
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Re-calibrate GNSS", fontSize = 11.sp, color = DuForestGreen)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 9.sp, color = DuTextSecondary)
    }
}

@Composable
private fun TelemetryParamCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    metric: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = DuSurfaceSubtle
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Icon(icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, fontSize = 9.sp, color = DuTextSecondary)
            Text(text = metric, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = status, fontSize = 9.sp, color = DuForestGreen, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun BleBeaconRow(
    beaconId: String,
    location: String,
    rssi: String,
    rssiColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(DuSurfaceSubtle)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Sensors, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(15.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = beaconId, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Text(text = location, fontSize = 9.sp, color = DuTextSecondary)
            }
        }
        Text(text = rssi, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = rssiColor)
    }
}
