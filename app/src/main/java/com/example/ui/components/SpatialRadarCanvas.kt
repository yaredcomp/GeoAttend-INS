package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun SpatialRadarCanvas(
    isInsidePerimeter: Boolean = true,
    distanceToPodium: Double = 8.2,
    accuracyMeters: Double = 1.8,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RadarSweep")
    val sweepAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "SweepAngle"
    )

    val pulseRadius by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "PulseRadius"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF334155), RoundedCornerShape(16.dp))
            .testTag("spatial_radar_canvas")
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val centerX = width * 0.72f
            val centerY = height * 0.38f
            val maxRadius = width * 0.28f

            // 1. Draw Architectural Floorplan Rooms
            // Central Concourse Corridor
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(width * 0.05f, height * 0.44f),
                size = Size(width * 0.90f, height * 0.12f),
                cornerRadius = CornerRadius(6f, 6f)
            )

            // Room CNCS-B01 (Physics Lab)
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(width * 0.05f, height * 0.10f),
                size = Size(width * 0.26f, height * 0.30f),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // Room CNCS-B02 (Bio-Informatics)
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(width * 0.35f, height * 0.10f),
                size = Size(width * 0.26f, height * 0.30f),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // Room CNCS-B04 (Active Database Lecture Hall)
            drawRoundRect(
                color = Color(0xFF064E3B).copy(alpha = 0.85f),
                topLeft = Offset(width * 0.65f, height * 0.08f),
                size = Size(width * 0.32f, height * 0.34f),
                cornerRadius = CornerRadius(8f, 8f)
            )
            // Border around CNCS-B04
            drawRoundRect(
                color = DuRadarAccent,
                topLeft = Offset(width * 0.65f, height * 0.08f),
                size = Size(width * 0.32f, height * 0.34f),
                cornerRadius = CornerRadius(8f, 8f),
                style = Stroke(width = 2.5f)
            )

            // Lower Wing: Faculty Suites & Seminar Wing B
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(width * 0.05f, height * 0.60f),
                size = Size(width * 0.40f, height * 0.32f),
                cornerRadius = CornerRadius(8f, 8f)
            )
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(width * 0.50f, height * 0.60f),
                size = Size(width * 0.45f, height * 0.32f),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // 2. Geofence Concentric Boundary Rings (Centered at CNCS-B04)
            for (step in 1..3) {
                val r = maxRadius * (step / 3f)
                drawCircle(
                    color = DuRadarAccent.copy(alpha = 0.35f),
                    radius = r,
                    center = Offset(centerX, centerY),
                    style = Stroke(
                        width = 1.5f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                )
            }

            // Animated Pulse Wave
            drawCircle(
                color = DuRadarAccent.copy(alpha = (1f - pulseRadius) * 0.4f),
                radius = maxRadius * pulseRadius,
                center = Offset(centerX, centerY),
                style = Stroke(width = 3f)
            )

            // 3. BLE Beacon Transmitters (Podium & Exit)
            drawCircle(
                color = Color(0xFF38BDF8),
                radius = 6f,
                center = Offset(centerX, centerY)
            )
            drawCircle(
                color = Color(0xFF38BDF8),
                radius = 5f,
                center = Offset(width * 0.88f, height * 0.40f)
            )

            // 4. Enclosed Students Cluster Dots
            val studentOffsets = listOf(
                Offset(centerX - 24f, centerY - 28f),
                Offset(centerX + 32f, centerY - 20f),
                Offset(centerX - 35f, centerY + 24f),
                Offset(centerX + 26f, centerY + 30f),
                Offset(centerX - 12f, centerY + 38f),
                Offset(centerX + 40f, centerY + 10f),
                Offset(centerX - 42f, centerY - 8f)
            )
            for (offset in studentOffsets) {
                drawCircle(
                    color = Color(0xFF4ADE80),
                    radius = 4.5f,
                    center = offset
                )
            }

            // Waiting Queue Students in Corridor
            drawCircle(
                color = DuGold,
                radius = 4.5f,
                center = Offset(width * 0.62f, height * 0.48f)
            )
            drawCircle(
                color = DuGold,
                radius = 4.5f,
                center = Offset(width * 0.58f, height * 0.50f)
            )

            // 5. Instructor Position Indicator (Podium target)
            val instructorPos = if (isInsidePerimeter) {
                Offset(centerX - 10f, centerY + 14f)
            } else {
                Offset(width * 0.20f, height * 0.75f) // Drifted to Faculty Suites
            }

            // Instructor Glow
            drawCircle(
                color = if (isInsidePerimeter) DuForestGreen.copy(alpha = 0.5f) else DuAlertCrimson.copy(alpha = 0.5f),
                radius = 16f,
                center = instructorPos
            )
            drawCircle(
                color = if (isInsidePerimeter) DuForestGreenLight else DuAlertCrimson,
                radius = 7f,
                center = instructorPos
            )
            drawCircle(
                color = Color.White,
                radius = 3f,
                center = instructorPos
            )
        }

        // Overlay Headers and Badges
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = Color(0xFF1E293B).copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Sensors,
                        contentDescription = null,
                        tint = DuRadarAccent,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "BLE Mesh Active (2 Nodes)",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Surface(
                color = if (isInsidePerimeter) DuForestGreenDark.copy(alpha = 0.9f) else DuAlertCrimson.copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.MyLocation,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isInsidePerimeter) "±${accuracyMeters}m High Fix" else "Perimeter Drift (58m)",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Room Labels Overlay
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 46.dp, end = 24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Room CNCS-B04",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cap: 75 Seats",
                    color = Color(0xFF94A3B8),
                    fontSize = 9.sp
                )
            }
        }

        // Bottom Instructor Distance Indicator
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            color = Color(0xFF0F172A).copy(alpha = 0.95f),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (isInsidePerimeter) DuRadarAccent else DuAlertCrimson)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isInsidePerimeter) DuRadarAccent else DuAlertCrimson)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isInsidePerimeter) "You are ${distanceToPodium}m from podium (Inside)" else "Outside Hall Perimeter (${distanceToPodium}m)",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
