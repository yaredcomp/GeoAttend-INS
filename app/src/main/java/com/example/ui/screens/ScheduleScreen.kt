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
import com.example.ui.theme.*

@Composable
fun ScheduleScreen(
    schedules: List<CourseSchedule>,
    onClassClick: (CourseSchedule) -> Unit,
    onExportPdfClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isWeeklyGrid by remember { mutableStateOf(false) }
    var selectedDayIndex by remember { mutableStateOf(3) } // Thursday = 3
    val scrollState = rememberScrollState()

    val daysOfWeek = listOf(
        Triple("Mon 21", "2", "4.0h"),
        Triple("Tue 22", "1", "2.0h"),
        Triple("Wed 23", "3", "5.0h"),
        Triple("Thu 24", "3", "5.5h"),
        Triple("Fri 25", "2", "3.0h"),
        Triple("Sat 26", "0", "0.0h")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Academic Term Bar
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
                    Icon(Icons.Default.School, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "2017 E.C. • Semester II (Week 08)",
                        color = DuForestGreenDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Synchronized",
                        color = DuForestGreen,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Faculty Timetable",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = DuTextPrimary
                )
            )
            Text(
                text = "College of Eng & Tech",
                fontSize = 11.sp,
                color = DuTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Toggle: Daily vs Weekly Grid
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = DuSurfaceSubtle
        ) {
            Row(modifier = Modifier.padding(3.dp)) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable { isWeeklyGrid = false },
                    color = if (!isWeeklyGrid) Color.White else Color.Transparent,
                    shadowElevation = if (!isWeeklyGrid) 1.dp else 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.ViewAgenda,
                            contentDescription = null,
                            tint = if (!isWeeklyGrid) DuForestGreen else DuTextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Daily View",
                            fontSize = 12.sp,
                            fontWeight = if (!isWeeklyGrid) FontWeight.Bold else FontWeight.Normal,
                            color = if (!isWeeklyGrid) DuForestGreen else DuTextSecondary
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable { isWeeklyGrid = true },
                    color = if (isWeeklyGrid) Color.White else Color.Transparent,
                    shadowElevation = if (isWeeklyGrid) 1.dp else 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CalendarViewWeek,
                            contentDescription = null,
                            tint = if (isWeeklyGrid) DuForestGreen else DuTextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Weekly Grid",
                            fontSize = 12.sp,
                            fontWeight = if (isWeeklyGrid) FontWeight.Bold else FontWeight.Normal,
                            color = if (isWeeklyGrid) DuForestGreen else DuTextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Week Selector Row
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Prev Week", tint = DuTextSecondary)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Oct 21 – Oct 26, 2024",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuTextPrimary
                    )
                    Text(
                        text = "Tikimt 11 – 16, 2017 E.C.",
                        fontSize = 10.sp,
                        color = DuTextSecondary
                    )
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFFEFF6FF)
                ) {
                    Text(
                        text = "Current",
                        color = Color(0xFF1D4ED8),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Icon(Icons.Default.ChevronRight, contentDescription = "Next Week", tint = DuTextSecondary)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4 KPI Summary Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KpiMiniCard(
                icon = Icons.Default.Schedule,
                value = "14.0",
                unit = "hrs/wk",
                label = "Total Lectures",
                subtext = "Norm: 12-16 hrs",
                modifier = Modifier.weight(1f)
            )
            KpiMiniCard(
                icon = Icons.Default.MilitaryTech,
                value = "12.0",
                unit = "Cr. Hrs",
                label = "Credit Load",
                subtext = "3 Assigned Secs",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KpiMiniCard(
                icon = Icons.Default.MeetingRoom,
                value = "3",
                unit = "Rooms",
                label = "Assigned Halls",
                subtext = "Oda Ya'a Complex",
                modifier = Modifier.weight(1f)
            )
            KpiMiniCard(
                icon = Icons.Default.Verified,
                value = "96.4%",
                unit = "avg",
                label = "Quorum Rate",
                subtext = "Above Target +4.2%",
                valueColor = DuForestGreen,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Day of week tabs
        Text(
            text = "Weekly Distribution",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DuTextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            daysOfWeek.forEachIndexed { index, item ->
                val isSelected = selectedDayIndex == index
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectedDayIndex = index },
                    color = if (isSelected) DuForestGreenDark else Color.White,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isSelected) DuForestGreen else DuSurfaceBorder
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.first,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else DuTextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.second,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else DuForestGreen
                        )
                        Text(
                            text = item.third,
                            fontSize = 9.sp,
                            color = if (isSelected) DuGreenContainer else DuTextMuted
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Timetable List for Thursday
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Today, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Thursday Timetable",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
            }
            Text(
                text = "3 Sessions • 5.5 Hours",
                fontSize = 11.sp,
                color = DuTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Session 1: Concluded
        TimetableSlotCard(
            time = "09:30\n11:00",
            statusText = "Concluded",
            statusBg = DuGreenContainer,
            statusColor = DuForestGreen,
            duration = "1h 30m",
            courseCode = "CS-3012: Operating Systems",
            courseDesc = "Lecture • 4th Year Cohort B (62 Students)",
            venue = "Oda Ya'a LH-04",
            beacon = "BCN-OY4-02",
            metric = "98.4% Present",
            onClick = { onClassClick(schedules.first()) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Session 2: Next / Active
        TimetableSlotCard(
            time = "13:30\n15:30",
            statusText = "Next Session (Starts 35m)",
            statusBg = Color(0xFFFEF3C7),
            statusColor = DuGoldDark,
            duration = "2h 00m",
            courseCode = "SE-4210: Software Architecture",
            courseDesc = "Interactive Design Critique • Section 1",
            venue = "Oda Ya'a Lab-02",
            beacon = "Geofence Auto-Arm",
            metric = "58 Enrolled",
            isNext = true,
            onClick = { onClassClick(schedules[1]) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Session 3: Upcoming
        TimetableSlotCard(
            time = "16:00\n17:30",
            statusText = "Upcoming Slot",
            statusBg = Color(0xFFEFF6FF),
            statusColor = Color(0xFF1D4ED8),
            duration = "1h 30m",
            courseCode = "CS-4901: Senior Capstone Mentorship",
            courseDesc = "Project Milestone Review • Groups A1-A4",
            venue = "Oda Ya'a Seminar Hall",
            beacon = "Beacon BCN-OY-SEM",
            metric = "32 Enrolled",
            onClick = { onClassClick(schedules[2]) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PDF Export Button
        Button(
            onClick = onExportPdfClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("schedule_export_pdf_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Export Official Semester Timetable (PDF)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Request Room / Slot Reschedule with Dean", fontSize = 12.sp, color = DuForestGreen)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun KpiMiniCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    unit: String,
    label: String,
    subtext: String,
    valueColor: Color = DuTextPrimary,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = label, fontSize = 10.sp, color = DuTextSecondary)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = value, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = valueColor)
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = unit, fontSize = 10.sp, color = DuTextMuted, modifier = Modifier.padding(bottom = 2.dp))
            }
            Text(text = subtext, fontSize = 9.sp, color = DuTextMuted)
        }
    }
}

@Composable
private fun TimetableSlotCard(
    time: String,
    statusText: String,
    statusBg: Color,
    statusColor: Color,
    duration: String,
    courseCode: String,
    courseDesc: String,
    venue: String,
    beacon: String,
    metric: String,
    isNext: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = if (isNext) Color(0xFF064E3B) else Color.White,
        shadowElevation = 1.dp,
        border = if (isNext) androidx.compose.foundation.BorderStroke(2.dp, DuGold) else null
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = time,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isNext) Color.White else DuTextSecondary,
                lineHeight = 15.sp,
                modifier = Modifier.width(46.dp)
            )

            VerticalDivider(
                modifier = Modifier
                    .height(68.dp)
                    .padding(horizontal = 8.dp),
                color = if (isNext) DuGold else DuForestGreen,
                thickness = 2.dp
            )

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = statusBg
                    ) {
                        Text(
                            text = statusText,
                            color = statusColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Text(
                        text = duration,
                        fontSize = 10.sp,
                        color = if (isNext) DuGreenContainer else DuTextMuted
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = courseCode,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNext) Color.White else DuTextPrimary
                )
                Text(
                    text = courseDesc,
                    fontSize = 10.sp,
                    color = if (isNext) Color(0xFFE2E8F0) else DuTextSecondary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.MeetingRoom,
                            contentDescription = null,
                            tint = if (isNext) DuGold else DuTextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = venue,
                            fontSize = 10.sp,
                            color = if (isNext) Color.White else DuTextPrimary
                        )
                    }
                    Text(
                        text = metric,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isNext) DuGold else DuForestGreen
                    )
                }
            }
        }
    }
}
