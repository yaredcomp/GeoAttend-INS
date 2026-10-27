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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.CourseSchedule
import com.example.data.model.InstructorProfile
import com.example.ui.components.StatusChip
import com.example.ui.components.StatusType
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    profile: InstructorProfile,
    nextClass: CourseSchedule,
    remainingClasses: List<CourseSchedule>,
    isInsideGeofence: Boolean,
    onClassClick: (CourseSchedule) -> Unit,
    onVerifyAndStartClick: () -> Unit,
    onReviewAlertClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Faculty Profile Header Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Avatar with green online dot
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_dr_dawit),
                        contentDescription = "Dr. Dawit Abebe Tadesse",
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .size(14.dp)
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

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = DuTextPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = DuGreenContainer
                        ) {
                            Text(
                                text = "Faculty",
                                color = DuForestGreen,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${profile.academicRank} • Dept. CS",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )
                    }
                }

                // Week Indicator
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(11.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Week 08",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuForestGreen
                            )
                        }
                        Text(
                            text = "Thursday, Oct 24",
                            fontSize = 9.sp,
                            color = DuTextMuted
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Cloud sync status banner
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = DuGreenContainer
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CloudDone,
                        contentDescription = null,
                        tint = DuForestGreen,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "All local attendance synced with DU Central SIS",
                        color = DuForestGreenDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = "100% Reliable",
                    color = DuForestGreen,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // NEXT SCHEDULED CLASS CARD (Hero Section)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, DuForestGreen, RoundedCornerShape(16.dp))
                .testTag("dashboard_next_class_card"),
            color = Color.White,
            shadowElevation = 3.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = DuForestGreen,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "NEXT SCHEDULED CLASS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuForestGreen,
                            letterSpacing = 0.5.sp
                        )
                    }

                    StatusChip(
                        text = if (isInsideGeofence) "Classroom Beacon (15m)" else "Perimeter Drift (58m)",
                        statusType = if (isInsideGeofence) StatusType.SUCCESS else StatusType.ERROR
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${nextClass.courseCode}: ${nextClass.courseName}",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = DuTextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFFEFF6FF)
                    ) {
                        Text(
                            text = nextClass.section,
                            color = Color(0xFF1D4ED8),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.MeetingRoom,
                        contentDescription = null,
                        tint = DuTextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = nextClass.room,
                        fontSize = 13.sp,
                        color = DuTextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Time window & cohort row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Time Window", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = "09:30 AM – 11:30 AM", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            }
                        }
                    }

                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Groups, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Enrolled Cohort", fontSize = 10.sp, color = DuTextSecondary)
                                Text(text = "${nextClass.enrolledCount} Students", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Start Window Countdown Banner
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFEF3C7)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.HourglassBottom, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Start Window Opens in:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = DuGoldDark
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = DuGold
                        ) {
                            Text(
                                text = "04m 29s",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Location verified badge
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = if (isInsideGeofence) DuGreenContainer else DuCrimsonLight
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isInsideGeofence) Icons.Default.LocationOn else Icons.Default.LocationOff,
                                contentDescription = null,
                                tint = if (isInsideGeofence) DuForestGreen else DuAlertCrimson,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = if (isInsideGeofence) "Campus Geofence Verified" else "Outside Assigned Perimeter",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isInsideGeofence) DuForestGreen else DuAlertCrimson
                                )
                                Text(
                                    text = if (isInsideGeofence) "Accuracy: ±2.4m • Ready to deploy session" else "Instructor must be in CNCS-B04",
                                    fontSize = 10.sp,
                                    color = if (isInsideGeofence) DuForestGreenDark else DuAlertCrimson
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isInsideGeofence) DuForestGreen else DuAlertCrimson
                        ) {
                            Text(
                                text = if (isInsideGeofence) "GPS Lock" else "No Lock",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Prominent Action Button
                Button(
                    onClick = onVerifyAndStartClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("dashboard_verify_and_start_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NearMe,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Verify Location & Start Class",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Attendance portal automatically locks upon lecturer departure from CNCS-B04.",
                    fontSize = 10.sp,
                    color = DuTextMuted,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Urgent Alert Card
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
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = DuAlertCrimson,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Pending Approvals & Alerts",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = DuCrimsonLight
                    ) {
                        Text(
                            text = "1 Urgent",
                            color = DuAlertCrimson,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(DuCrimsonLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AssignmentLate, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Session CS-2041 Attendance",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = "Yesterday",
                                fontSize = 10.sp,
                                color = DuTextMuted
                            )
                        }
                        Text(
                            text = "Requires final sign-off before midnight to sync official Registrar credits.",
                            fontSize = 11.sp,
                            color = DuTextSecondary,
                            lineHeight = 15.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = onReviewAlertClick,
                                modifier = Modifier
                                    .height(34.dp)
                                    .testTag("dashboard_review_signoff_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                            ) {
                                Text("Review & Sign Off", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier.height(34.dp),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                            ) {
                                Text("Dismiss", fontSize = 11.sp, color = DuTextSecondary)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Telemetry Overview 3-Card Grid
        Text(
            text = "Instructor Telemetry Overview",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DuTextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TelemetryMetricCard(
                icon = Icons.Default.School,
                number = "3",
                label = "Classes",
                badge = "Today",
                badgeColor = DuSurfaceSubtle,
                modifier = Modifier.weight(1f)
            )
            TelemetryMetricCard(
                icon = Icons.Default.Speed,
                number = "98.4%",
                label = "Avg Punctuality",
                badge = "High",
                badgeColor = DuGreenContainer,
                badgeTextColor = DuForestGreen,
                modifier = Modifier.weight(1.1f)
            )
            TelemetryMetricCard(
                icon = Icons.Default.School,
                number = "182",
                label = "Students",
                badge = "Active",
                badgeColor = Color(0xFFEFF6FF),
                badgeTextColor = Color(0xFF1D4ED8),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Remaining Classes Today
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.MenuBook, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Remaining Classes Today",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
            }
            Text(
                text = "${remainingClasses.size} Sessions Remaining",
                fontSize = 11.sp,
                color = DuTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        remainingClasses.forEach { sched ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onClassClick(sched) },
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (sched.courseCode.startsWith("SE")) DuGold else Color(0xFF64748B))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${sched.startTime} – ${sched.endTime}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Text(
                            text = "${sched.courseCode}: ${sched.courseName}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Text(
                            text = "${sched.room} • ${sched.enrolledCount} Enrolled",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "View class",
                        tint = DuTextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun TelemetryMetricCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    number: String,
    label: String,
    badge: String,
    badgeColor: Color,
    badgeTextColor: Color = DuTextSecondary,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = badgeColor
                ) {
                    Text(
                        text = badge,
                        color = badgeTextColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = number,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DuTextPrimary
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = DuTextSecondary
            )
        }
    }
}
