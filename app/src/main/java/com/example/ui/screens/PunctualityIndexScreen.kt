package com.example.ui.screens

import androidx.compose.foundation.background
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
import com.example.data.model.PunctualityMetrics
import com.example.ui.components.CircularProgressRing
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PunctualityIndexScreen(
    metrics: PunctualityMetrics,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var reportDownloaded by remember { mutableStateOf(false) }
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
                            text = "Punctuality Analytics",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("punctuality_back_button")
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
            // Header Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "FACULTY STANDING", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary)
                        Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                            Text(text = "• Live Sync", color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Punctuality Index",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFFEF3C7)) {
                                Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Star, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(text = metrics.institutionalRating, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuGoldDark)
                                }
                            }
                        }

                        CircularProgressRing(
                            percentage = metrics.punctualityIndex,
                            size = 80.dp,
                            primaryColor = DuForestGreen,
                            subtitle = "%"
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFEFF6FF)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "Above College of Engineering Baseline", fontSize = 11.sp, color = Color(0xFF1E40AF))
                            }
                            Text(text = metrics.deptComparison, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Grid Parameters
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IndexParamCard(
                    icon = Icons.Default.Schedule,
                    badge = "Grace ≤5m",
                    title = "Inception Delta",
                    value = metrics.inceptionDeltaText,
                    subtitle = "Well within University grace threshold",
                    modifier = Modifier.weight(1f)
                )
                IndexParamCard(
                    icon = Icons.Default.MyLocation,
                    badge = "GNSS+BLE",
                    title = "Geofence Lock",
                    value = "${metrics.geofenceLockRate}%",
                    subtitle = "Dual-channel hardware boundary sync",
                    valueColor = DuForestGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IndexParamCard(
                    icon = Icons.Default.Groups,
                    badge = "MoE ≥75%",
                    title = "Cohort Quorum",
                    value = "${metrics.cohortQuorumRate}%",
                    subtitle = "Surpasses federal MoE minimum quorum",
                    valueColor = DuForestGreen,
                    modifier = Modifier.weight(1f)
                )
                IndexParamCard(
                    icon = Icons.Default.Lock,
                    badge = "Zero Leaks",
                    title = "Conclude Seal",
                    value = "${metrics.concludeSealRate}%",
                    subtitle = "Sessions sealed before room timetable swap",
                    valueColor = DuForestGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weekly Inception Profile Bar Chart
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
                        Column {
                            Text(text = "Weekly Inception Profile", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                            Text(text = "Average schedule variance by weekday", fontSize = 10.sp, color = DuTextSecondary)
                        }
                        Surface(shape = RoundedCornerShape(4.dp), color = DuSurfaceSubtle) {
                            Text(text = "Delta (Min)", fontSize = 9.sp, color = DuTextSecondary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 5 Day Bars
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        InceptionBar(day = "Mon", delta = "+0.8m", heightDp = 60, barColor = DuForestGreen)
                        InceptionBar(day = "Tue", delta = "+1.1m", heightDp = 75, barColor = DuForestGreen)
                        InceptionBar(day = "Wed", delta = "+0.4m", heightDp = 40, barColor = DuForestGreen)
                        InceptionBar(day = "Thu", delta = "+2.1m", heightDp = 95, barColor = DuGold)
                        InceptionBar(day = "Fri", delta = "+0.9m", heightDp = 65, barColor = DuForestGreen)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFEFF6FF)
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                            Icon(Icons.Default.TrendingUp, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Direct Correlation: Class sections launched within 2 minutes display an average of +8.4% higher final student test compliance.",
                                fontSize = 10.sp,
                                color = Color(0xFF1E40AF),
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Start Window Distribution
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = "Start Window Distribution", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                    Text(text = "Breakdown across ${metrics.totalLoggedSessions} logged lecture sessions", fontSize = 10.sp, color = DuTextSecondary)

                    Spacer(modifier = Modifier.height(10.dp))

                    // Combined multi-color bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                    ) {
                        Box(modifier = Modifier.weight(0.92f).fillMaxHeight().background(DuForestGreen))
                        Box(modifier = Modifier.weight(0.06f).fillMaxHeight().background(DuGold))
                        Box(modifier = Modifier.weight(0.02f).fillMaxHeight().background(Color(0xFF93C5FD)))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    DistributionRow(color = DuForestGreen, label = "Punctual Launch (< 2m grace)", count = "${metrics.punctualLaunchCount} Sessions", pct = "92%")
                    DistributionRow(color = DuGold, label = "Minor Transit Buffer (2–5m)", count = "${metrics.minorTransitBufferCount} Sessions", pct = "6%")
                    DistributionRow(color = Color(0xFF93C5FD), label = "Excused Classroom Reallocation", count = "${metrics.excusedReallocationCount} Session", pct = "2%")
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // QA Faculty Excellence Certificate Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(DuGreenContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "QA ACCREDITED • MoE Standards", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                            Text(text = "Faculty Excellence Certificate", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Formally verified by the Dilla University Quality Assurance & Enhancement Directorate under Federal Ministry of Education Academic Protocol Code ${metrics.accreditationProtocol}.",
                        fontSize = 10.sp,
                        color = DuTextSecondary,
                        lineHeight = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "🛡 Immutable Geostamp Audit", fontSize = 10.sp, color = DuForestGreen, fontWeight = FontWeight.SemiBold)
                        Text(text = "Ref: ${metrics.auditRef}", fontSize = 10.sp, color = DuTextMuted)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { reportDownloaded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("punctuality_generate_report_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Description, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Generate Official Audit Report (PDF)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }

            if (reportDownloaded) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Audit report generated and saved locally.", fontSize = 10.sp, color = DuForestGreen, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun IndexParamCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badge: String,
    title: String,
    value: String,
    subtitle: String,
    valueColor: Color = DuTextPrimary,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(10.dp), color = Color.White, shadowElevation = 1.dp) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                Surface(shape = RoundedCornerShape(4.dp), color = DuSurfaceSubtle) {
                    Text(text = badge, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuTextSecondary, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, fontSize = 10.sp, color = DuTextSecondary)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = valueColor)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 8.sp, color = DuTextMuted, lineHeight = 11.sp)
        }
    }
}

@Composable
private fun InceptionBar(
    day: String,
    delta: String,
    heightDp: Int,
    barColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = delta, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(28.dp)
                .height(heightDp.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(barColor)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = day, fontSize = 10.sp, color = DuTextSecondary)
    }
}

@Composable
private fun DistributionRow(
    color: Color,
    label: String,
    count: String,
    pct: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(color))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = label, fontSize = 10.sp, color = DuTextPrimary)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = count, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Spacer(modifier = Modifier.width(6.dp))
            Surface(shape = RoundedCornerShape(3.dp), color = DuGreenContainer) {
                Text(text = pct, color = DuForestGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
            }
        }
    }
}
