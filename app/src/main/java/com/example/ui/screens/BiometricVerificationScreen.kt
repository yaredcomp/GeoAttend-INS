package com.example.ui.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CourseSchedule
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiometricVerificationScreen(
    schedule: CourseSchedule,
    isBiometricSimPass: Boolean,
    onBackClick: () -> Unit,
    onAuthSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedMethod by remember { mutableStateOf("Touch ID") }
    var isAuthenticating by remember { mutableStateOf(false) }
    var authError by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    val pulseTransition = rememberInfiniteTransition(label = "BioPulse")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BioScale"
    )

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
                            text = "Biometric Verification",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("bio_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DuTextPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.HelpOutline, contentDescription = "Help", tint = DuTextSecondary)
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
            // Step Bar (Step 2 of 3)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SECURITY VERIFICATION • STEP 2 OF 3",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuForestGreen
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LockClock, contentDescription = null, tint = DuTextSecondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "02:42",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuTextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Faculty Biometric Seal",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = DuTextPrimary
                )
            )
            Text(
                text = "Dilla University Biometric Multi-Factor Authentication",
                fontSize = 11.sp,
                color = DuTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Active Class Capsule
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEFF6FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${schedule.courseCode}: ${schedule.courseName}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                        Text(
                            text = "${schedule.room} • Main Campus",
                            fontSize = 10.sp,
                            color = DuTextSecondary
                        )
                    }
                    Surface(shape = RoundedCornerShape(4.dp), color = DuGreenContainer) {
                        Text(
                            text = "Active",
                            color = DuForestGreen,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Central Biometric Sensor Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Method Toggle Pills
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = DuSurfaceSubtle
                    ) {
                        Row(modifier = Modifier.padding(3.dp)) {
                            Surface(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { selectedMethod = "Touch ID" },
                                color = if (selectedMethod == "Touch ID") Color.White else Color.Transparent,
                                shadowElevation = if (selectedMethod == "Touch ID") 1.dp else 0.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Fingerprint, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Touch ID", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuForestGreen)
                                }
                            }

                            Surface(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { selectedMethod = "Face ID" },
                                color = if (selectedMethod == "Face ID") Color.White else Color.Transparent,
                                shadowElevation = if (selectedMethod == "Face ID") 1.dp else 0.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Face, contentDescription = null, tint = DuTextSecondary, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Face ID", fontSize = 11.sp, color = DuTextSecondary)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Animated Sensor Graphics
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(DuGreenContainer.copy(alpha = 0.5f))
                            .border(3.dp, DuForestGreenLight, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(108.dp)
                                .clip(CircleShape)
                                .background(DuForestGreen)
                                .clickable {
                                    if (isBiometricSimPass) {
                                        onAuthSuccess()
                                    } else {
                                        authError = "Biometric check failed. Please retry with registered finger."
                                    }
                                }
                                .testTag("bio_sensor_touch_area"),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (selectedMethod == "Touch ID") Icons.Default.Fingerprint else Icons.Default.Face,
                                contentDescription = "Biometric Sensor",
                                tint = Color.White,
                                modifier = Modifier.size(54.dp)
                            )
                        }

                        // Verified shield badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp)
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(DuSuccessEmerald),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(shape = RoundedCornerShape(20.dp), color = DuGreenContainer) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DuForestGreen))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Ready for $selectedMethod",
                                color = DuForestGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Place your registered faculty finger on the sensor or glance at camera to digitally seal session authorization.",
                        fontSize = 11.sp,
                        color = DuTextSecondary,
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Shield, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Faculty Quota: 3 attempts remaining",
                            fontSize = 10.sp,
                            color = DuGoldDark,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    if (authError != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = authError!!,
                            color = DuAlertCrimson,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Institutional Cryptographic Chain (Hardware TEE)
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
                            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Institutional Cryptographic Chain",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                        }
                        Text(
                            text = "Hardware TEE",
                            fontSize = 10.sp,
                            color = DuForestGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    SecurityChainRow(
                        icon = Icons.Default.Memory,
                        title = "Knox Hardware Keystore",
                        subtitle = "DU-RSA-4096 Identity Validated",
                        isPassed = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    SecurityChainRow(
                        icon = Icons.Default.PinDrop,
                        title = "Geofence Perimeter Lock",
                        subtitle = "GPS 6.410317, 38.313422 (Locked)",
                        isPassed = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    SecurityChainRow(
                        icon = Icons.Default.VpnKey,
                        title = "Ephemeral Ticket",
                        subtitle = "HMAC-SHA256 Nonce #941B (Active)",
                        isPassed = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Primary Action: Authenticate Now
            Button(
                onClick = {
                    if (isBiometricSimPass) {
                        onAuthSuccess()
                    } else {
                        authError = "Hardware rejection: Simulated biometric verification failed."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("bio_authenticate_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Fingerprint, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Authenticate Now",
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
                    onClick = { onAuthSuccess() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Pin, contentDescription = null, tint = DuTextPrimary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Use Faculty PIN", fontSize = 11.sp, color = DuTextPrimary)
                }

                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DuAlertCrimson),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = DuAlertCrimson, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Cancel", fontSize = 11.sp, color = DuAlertCrimson)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFFEF3C7).copy(alpha = 0.5f)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = DuGoldDark, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Cancelling now invalidates step 1 and requires re-verifying GPS coordinates inside Room CNCS-B04.",
                        fontSize = 10.sp,
                        color = Color(0xFF78350F),
                        lineHeight = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SecurityChainRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    isPassed: Boolean
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
            Icon(icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
                Text(text = subtitle, fontSize = 9.sp, color = DuTextSecondary)
            }
        }
        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
    }
}
