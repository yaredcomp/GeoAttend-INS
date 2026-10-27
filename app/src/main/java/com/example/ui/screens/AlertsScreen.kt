package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.data.model.AlertCategory
import com.example.data.model.AlertPriority
import com.example.data.model.AttendanceAlert
import com.example.ui.theme.*

@Composable
fun AlertsScreen(
    alerts: List<AttendanceAlert>,
    onAcknowledgeAlert: (String) -> Unit,
    onReviewTelemetry: (AttendanceAlert) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(AlertCategory.ALL) }

    val filteredAlerts = remember(alerts, selectedCategory) {
        if (selectedCategory == AlertCategory.ALL) alerts
        else alerts.filter { it.category == selectedCategory }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            // Header Dispatch Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Sensors, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "REAL-TIME DISPATCH",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuForestGreen,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(DuForestGreen))
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = DuGreenContainer,
                    modifier = Modifier.clickable {
                        alerts.forEach { onAcknowledgeAlert(it.id) }
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DoneAll, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Mark All Read", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // RTK Base Station Online Status Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFEFF6FF),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFDBEAFE)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.CellTower, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Dilla Oda Ya'a Base Station", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = "RTK Geofence Active (±0.4m precision)", fontSize = 10.sp, color = DuTextSecondary)
                        }
                    }

                    Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                        Text(text = "Online", color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Pills (All 12, Academic & Registrar 5, Hardware & BLE 7)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryPill(
                    label = "All 12",
                    isSelected = selectedCategory == AlertCategory.ALL,
                    onClick = { selectedCategory = AlertCategory.ALL },
                    modifier = Modifier.weight(1f)
                )
                CategoryPill(
                    label = "Academic 5",
                    isSelected = selectedCategory == AlertCategory.ACADEMIC_REGISTRAR,
                    onClick = { selectedCategory = AlertCategory.ACADEMIC_REGISTRAR },
                    modifier = Modifier.weight(1.2f)
                )
                CategoryPill(
                    label = "Hardware 7",
                    isSelected = selectedCategory == AlertCategory.HARDWARE_BEACON,
                    onClick = { selectedCategory = AlertCategory.HARDWARE_BEACON },
                    modifier = Modifier.weight(1.2f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Today", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                }
                Text(text = "${filteredAlerts.count { it.isUnacknowledged }} unacknowledged", fontSize = 10.sp, color = DuTextSecondary)
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(filteredAlerts) { alert ->
            AlertCardItem(
                alert = alert,
                onAcknowledge = { onAcknowledgeAlert(alert.id) },
                onActionClick = { onReviewTelemetry(alert) }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

            // Notification Controls
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
                            Icon(Icons.Default.Tune, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Notification Controls", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                        Text(text = "Smart Sync ON", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Configure instant push alerts for sudden geofence signal collapses, student quorum violations, or SIS synchronization errors.",
                        fontSize = 11.sp,
                        color = DuTextSecondary,
                        lineHeight = 15.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Settings, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Alert Preferences", fontSize = 10.sp, color = DuForestGreen)
                        }

                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.HelpOutline, contentDescription = null, tint = DuTextSecondary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("IT Helpdesk", fontSize = 10.sp, color = DuTextPrimary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CategoryPill(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
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
                text = label,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DuTextPrimary
            )
        }
    }
}

@Composable
private fun AlertCardItem(
    alert: AttendanceAlert,
    onAcknowledge: () -> Unit,
    onActionClick: () -> Unit
) {
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
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = when (alert.priority) {
                        AlertPriority.HIGH -> DuCrimsonLight
                        AlertPriority.NORMAL -> Color(0xFFFEF3C7)
                        AlertPriority.INFO -> Color(0xFFEFF6FF)
                    }
                ) {
                    Text(
                        text = if (alert.priority == AlertPriority.HIGH) "• High Priority" else "Institutional",
                        color = when (alert.priority) {
                            AlertPriority.HIGH -> DuAlertCrimson
                            AlertPriority.NORMAL -> DuGoldDark
                            AlertPriority.INFO -> Color(0xFF1D4ED8)
                        },
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text(text = alert.timestamp, fontSize = 10.sp, color = DuTextMuted)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = alert.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = alert.subtitle, fontSize = 10.sp, color = DuTextSecondary)

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = alert.description, fontSize = 11.sp, color = DuTextPrimary, lineHeight = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (alert.actionLabel != null) {
                    Button(
                        onClick = onActionClick,
                        modifier = Modifier
                            .height(34.dp)
                            .testTag("alert_action_button_${alert.id}"),
                        colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(alert.actionLabel, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }

                if (alert.secondaryActionLabel != null) {
                    OutlinedButton(
                        onClick = onAcknowledge,
                        modifier = Modifier.height(34.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(alert.secondaryActionLabel, fontSize = 10.sp, color = DuTextSecondary)
                    }
                }
            }
        }
    }
}
