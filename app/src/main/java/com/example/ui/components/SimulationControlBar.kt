package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.ui.theme.*

@Composable
fun SimulationControlBar(
    isInsideGeofence: Boolean,
    onToggleGeofence: () -> Unit,
    isBeaconStrong: Boolean,
    onToggleBeacon: () -> Unit,
    isBiometricPass: Boolean,
    onToggleBiometric: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("simulation_control_bar"),
        color = Color(0xFF1E293B),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isInsideGeofence && isBeaconStrong) DuRadarAccent else DuAlertCrimson)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hardware Test Controls (Simulation Mode)",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isExpanded) "Hide" else "Controls",
                        color = DuGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand controls",
                        tint = DuGold,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = "Tap buttons to test how GeoAttend INS responds when hardware boundaries change:",
                        color = Color(0xFF94A3B8),
                        fontSize = 10.sp,
                        lineHeight = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // GEOFENCE TOGGLE
                        Button(
                            onClick = onToggleGeofence,
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp)
                                .testTag("sim_toggle_geofence"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isInsideGeofence) DuForestGreen else DuAlertCrimson
                            ),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = if (isInsideGeofence) Icons.Default.LocationOn else Icons.Default.LocationOff,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isInsideGeofence) "GPS: Inside" else "GPS: Drifted",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        // BEACON RSSI TOGGLE
                        Button(
                            onClick = onToggleBeacon,
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp)
                                .testTag("sim_toggle_beacon"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isBeaconStrong) Color(0xFF0F766E) else Color(0xFFB45309)
                            ),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bluetooth,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isBeaconStrong) "BLE: -56dBm" else "BLE: Weak",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        // BIOMETRIC TOGGLE
                        Button(
                            onClick = onToggleBiometric,
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp)
                                .testTag("sim_toggle_biometric"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isBiometricPass) Color(0xFF15803D) else Color(0xFFDC2626)
                            ),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Fingerprint,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isBiometricPass) "Bio: Valid" else "Bio: Reject",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
