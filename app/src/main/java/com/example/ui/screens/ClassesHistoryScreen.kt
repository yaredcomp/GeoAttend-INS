package com.example.ui.screens

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CircularProgressRing
import com.example.ui.theme.*

@Composable
fun ClassesHistoryScreen(
    onViewReceipt: (String) -> Unit,
    onPunctualityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf("SEM2") }
    var downloadMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Registrar Synced Banner
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
                    Icon(Icons.Default.CloudDone, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Central Registrar Synced • Oda Ya'a Node 04 Online",
                        color = DuForestGreenDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Icon(Icons.Default.Refresh, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Semester selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { selectedTab = "SEM2" },
                color = if (selectedTab == "SEM2") DuForestGreen else Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, DuForestGreen)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        tint = if (selectedTab == "SEM2") Color.White else DuForestGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "2017 E.C. Semester II",
                        color = if (selectedTab == "SEM2") Color.White else DuForestGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { selectedTab = "SEM1" },
                color = if (selectedTab == "SEM1") DuForestGreen else Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, DuSurfaceBorder)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "2017 Sem I",
                        color = if (selectedTab == "SEM1") Color.White else DuTextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        Icons.Default.Tune,
                        contentDescription = null,
                        tint = if (selectedTab == "SEM1") Color.White else DuTextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Curriculum Delivery Progress Card
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Curriculum Delivery Progress",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                    Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                        Text(
                            text = "On Pace",
                            color = DuForestGreen,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Delivery Milestone", fontSize = 10.sp, color = DuTextSecondary)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(text = "38", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = " / 42 Sessions", fontSize = 13.sp, color = DuTextSecondary, modifier = Modifier.padding(bottom = 3.dp))
                        }
                        Text(
                            text = "90.5% statutory target met for College of Natural & Computational Sciences.",
                            fontSize = 10.sp,
                            color = DuTextMuted,
                            lineHeight = 14.sp
                        )
                    }

                    CircularProgressRing(
                        percentage = 90.0,
                        size = 72.dp,
                        subtitle = "Statutory"
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MetricBox(icon = Icons.Default.Schedule, value = "76", label = "Credit Hrs", modifier = Modifier.weight(1f))
                    MetricBox(icon = Icons.Default.Groups, value = "96.8%", label = "Avg Quorum", valueColor = DuForestGreen, modifier = Modifier.weight(1f))
                    MetricBox(icon = Icons.Default.CheckCircle, value = "0", label = "Unexcused", valueColor = DuForestGreen, modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Punctuality analytics shortcut button
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onPunctualityClick() },
            color = Color(0xFFEFF6FF),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Speed, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "Faculty Punctuality Index: 98.2%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E40AF))
                        Text(text = "Grade A+ Institutional Rating • View Analytics Profile", fontSize = 10.sp, color = Color(0xFF1D4ED8))
                    }
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF1D4ED8))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Download Ledger Action
        Button(
            onClick = { downloadMessage = "Official Ledger CSV/PDF generated and signed." },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("history_download_ledger_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.Download, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download Ledger (PDF/CSV)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        if (downloadMessage != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = downloadMessage!!, fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Week 07 List
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Week 07 • Academic Term (3 Sessions)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Text(
                text = "Tikimt 13 - 17, 2017",
                fontSize = 10.sp,
                color = DuTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Card 1
        HistorySessionCard(
            courseCode = "CS-3012",
            section = "Section B • Reg",
            title = "Distributed Systems",
            venue = "Room CNCS-B04, Oda Ya'a Complex • Main Campus",
            date = "Tikimt 14, 2017 (Oct 24, 2024)",
            time = "09:30 AM - 11:30 AM (Delivered 1h 58m)",
            metric = "59 / 64 Students (92.2%)",
            progress = 0.922f,
            receiptId = "DU-SES-2024-B04-0931",
            onViewClick = { onViewReceipt("DU-SES-2024-B04-0931") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Card 2
        HistorySessionCard(
            courseCode = "SE-4210",
            section = "Lab Practicum",
            title = "Software Architecture & Design",
            venue = "Computing Lab 02, New Tech Tower • 3rd Floor",
            date = "Tikimt 12, 2017 (Oct 22, 2024)",
            time = "02:00 PM - 05:00 PM (Delivered 2h 55m)",
            metric = "42 / 42 Students (100% Present)",
            progress = 1.0f,
            receiptId = "DU-SES-2024-LAB-1022",
            onViewClick = { onViewReceipt("DU-SES-2024-LAB-1022") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Card 3
        HistorySessionCard(
            courseCode = "CS-4901",
            section = "Makeup Session",
            title = "Senior Capstone Project Seminar",
            venue = "Seminar Hall 01, Main Library Wing • Approved Reschedule",
            date = "Tikimt 10, 2017 (Oct 20, 2024)",
            time = "10:00 AM - 12:00 PM (Delivered 2h 00m)",
            metric = "36 / 38 Students (94.7%)",
            progress = 0.947f,
            receiptId = "DU-SES-2024-MKP-1020",
            onViewClick = { onViewReceipt("DU-SES-2024-MKP-1020") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Legal text
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = DuSurfaceSubtle
        ) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.AccountBalance, contentDescription = null, tint = DuTextSecondary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Dilla University • Office of the Registrar & Alumni Directorate\nRecords sealed with SHA-256 campus geofence timestamp. Legally admissible under DU Academic Commission Directive No. 44/2016.",
                    fontSize = 9.sp,
                    color = DuTextSecondary,
                    lineHeight = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun MetricBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    valueColor: Color = DuTextPrimary,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(8.dp), color = DuSurfaceSubtle) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = valueColor)
            Text(text = label, fontSize = 9.sp, color = DuTextSecondary)
        }
    }
}

@Composable
private fun HistorySessionCard(
    courseCode: String,
    section: String,
    title: String,
    venue: String,
    date: String,
    time: String,
    metric: String,
    progress: Float,
    receiptId: String,
    onViewClick: () -> Unit
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFEFF6FF)) {
                        Text(
                            text = courseCode,
                            color = Color(0xFF1D4ED8),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = section, fontSize = 10.sp, color = DuTextSecondary)
                }

                Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Sealed & Synced", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = venue, fontSize = 10.sp, color = DuTextSecondary)

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, contentDescription = null, tint = DuTextMuted, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = date, fontSize = 10.sp, color = DuTextSecondary)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Schedule, contentDescription = null, tint = DuTextMuted, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = time, fontSize = 10.sp, color = DuTextSecondary)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Geo-Verified Present", fontSize = 10.sp, color = DuTextSecondary)
                Text(text = metric, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
            }
            Spacer(modifier = Modifier.height(3.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = DuForestGreen,
                trackColor = DuGreenContainer
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = receiptId, fontSize = 9.sp, color = DuTextMuted)
                Text(
                    text = "View Ledger →",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuForestGreen,
                    modifier = Modifier.clickable { onViewClick() }
                )
            }
        }
    }
}
