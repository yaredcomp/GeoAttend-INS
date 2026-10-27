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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen(
    onBackToLogin: () -> Unit,
    onResetComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedChannel by remember { mutableStateOf("SMS") }
    var pinInput by remember { mutableStateOf("740") }
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
                            text = "Password Recovery",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackToLogin,
                        modifier = Modifier.testTag("pwd_back_button")
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
            // Header
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(DuGreenContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.LockReset, contentDescription = null, tint = DuForestGreen)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Reset DU Instructor Password",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = "Verify your institutional identity to securely restore access.",
                                fontSize = 11.sp,
                                color = DuTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Step Progress (1. Identity -> 2. OTP Verify -> 3. New Key)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StepIndicator(number = "1", label = "Identity", isCompleted = true, isActive = false)
                        HorizontalDivider(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            color = DuForestGreen,
                            thickness = 2.dp
                        )
                        StepIndicator(number = "2", label = "OTP Verify", isCompleted = false, isActive = true)
                        HorizontalDivider(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            color = DuSurfaceBorder,
                            thickness = 2.dp
                        )
                        StepIndicator(number = "3", label = "New Key", isCompleted = false, isActive = false)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Verified Academic ID
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = DuSurfaceCard,
                border = androidx.compose.foundation.BorderStroke(1.dp, DuSurfaceBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "INSTRUCTOR ACADEMIC ID",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextSecondary
                        )
                        Surface(
                            color = DuGreenContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Verified",
                                    color = DuForestGreen,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Badge, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "dawit.a@du.edu.et",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                            Text(
                                text = "Staff ID: DU/FAC/089 • Dept. of Information Sys",
                                fontSize = 11.sp,
                                color = DuTextSecondary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // OTP Channel Select
            Text(
                text = "Select OTP Destination",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // SMS option
            ChannelOptionCard(
                icon = Icons.Default.Sms,
                title = "Institutional SMS Broadcast",
                subtitle = "+251 91 **** 45 (Safaricom/Ethio Tel)",
                isSelected = selectedChannel == "SMS",
                onClick = { selectedChannel = "SMS" }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Zimbra Webmail option
            ChannelOptionCard(
                icon = Icons.Default.MailOutline,
                title = "Dilla University Zimbra Webmail",
                subtitle = "dawit.a@du.edu.et",
                isSelected = selectedChannel == "Email",
                onClick = { selectedChannel = "Email" }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Enter 6-digit code
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enter 6-Digit Code",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = DuGold, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Expires in 14:18",
                        fontSize = 11.sp,
                        color = DuGoldDark,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 6-digit PIN boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("7", "4", "0", "•", "•", "•").forEachIndexed { index, char ->
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(10.dp),
                        color = if (index < 3) Color.White else Color(0xFFEFF6FF),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (index < 3) DuForestGreen else DuSurfaceBorder
                        )
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = char,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (index < 3) DuTextPrimary else DuTextMuted
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Didn't receive passcode?",
                    fontSize = 11.sp,
                    color = DuTextSecondary
                )
                Text(
                    text = "↺ Resend OTP in 01:39",
                    fontSize = 11.sp,
                    color = DuForestGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campus Network Security Policy
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFEF3C7).copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = Color(0xFFB45309),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Campus Network Security Policy",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E)
                        )
                        Text(
                            text = "For security reasons, password resets expire in 15 minutes and require active Dilla University VPN/eduroam authentication if accessed off-campus.",
                            fontSize = 11.sp,
                            color = Color(0xFF78350F),
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onResetComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("pwd_verify_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Verify Code & Proceed to Reset  →",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onBackToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("pwd_back_login_button"),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = DuSurfaceCard,
                    contentColor = DuTextPrimary
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "← Back to Login",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = DuTextPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun StepIndicator(
    number: String,
    label: String,
    isCompleted: Boolean,
    isActive: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(
                    if (isCompleted || isActive) DuForestGreen else DuSurfaceSubtle
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            } else {
                Text(
                    text = number,
                    color = if (isActive) Color.White else DuTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            color = if (isCompleted || isActive) DuForestGreen else DuTextMuted,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun ChannelOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() },
        color = if (isSelected) DuGreenContainer else DuSurfaceCard,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) DuForestGreen else DuSurfaceBorder
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) DuForestGreen else DuSurfaceSubtle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else DuForestGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuTextPrimary
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = DuTextSecondary
                )
            }
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = DuForestGreen)
            )
        }
    }
}
