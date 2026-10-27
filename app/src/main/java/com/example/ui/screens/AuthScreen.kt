package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*

@Composable
fun AuthScreen(
    onSignInSuccess: () -> Unit,
    onRegisterRequestClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DuSurfaceCanvas)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Dilla University Crest Card
        Surface(
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(20.dp))
                .testTag("auth_logo_surface"),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.du_logo),
                contentDescription = "Dilla University Official Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Campus Tag
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(DuGreenContainer)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(DuForestGreen)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "ዲላ ዩኒቨርሲቲ • Green Campus",
                color = DuForestGreen,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "GeoAttend ",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = DuTextPrimary
                )
            )
            Text(
                text = "INS",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = DuForestGreen
                )
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFEFF6FF)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.VerifiedUser,
                    contentDescription = null,
                    tint = Color(0xFF1D4ED8),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Faculty & Lecturer Portal v2.4",
                    color = Color(0xFF1E40AF),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Next-generation biometric & perimeter-authenticated lecture management system for higher education.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 13.sp,
                color = DuTextSecondary,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Three Core Value Cards (Matching screen__1__26dbadd0.png)
        FeatureInfoCard(
            icon = Icons.Default.GpsFixed,
            iconTint = DuForestGreen,
            badge = "GPS 4m",
            badgeColor = Color(0xFFE0F2FE),
            badgeTextColor = Color(0xFF0369A1),
            title = "Automated Geofence & BLE",
            description = "Rapid lecture roll-call within authorized hall perimeters with multi-node proximity verification."
        )

        Spacer(modifier = Modifier.height(10.dp))

        FeatureInfoCard(
            icon = Icons.Default.Fingerprint,
            iconTint = Color(0xFF0284C7),
            badge = "MoE Safe",
            badgeColor = DuGreenContainer,
            badgeTextColor = DuForestGreen,
            title = "Anti-Spoofing & Biometrics",
            description = "Tamper-proof classroom attendance tracking fully compliant with Federal Ministry standards."
        )

        Spacer(modifier = Modifier.height(10.dp))

        FeatureInfoCard(
            icon = Icons.Default.Sync,
            iconTint = DuForestGreen,
            badge = "Live API",
            badgeColor = Color(0xFFEDE9FE),
            badgeTextColor = Color(0xFF6D28D9),
            title = "Instant Registrar Sync",
            description = "Real-time academic credit validation, student punctuality logs, and instant CSV / SIS exports."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campus Gates Live Status
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFF1F5F9)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(DuForestGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Main Campus & Odayaa Gates: Active",
                        fontSize = 11.sp,
                        color = DuTextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Sensors,
                        contentDescription = null,
                        tint = DuForestGreen,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Online",
                        fontSize = 11.sp,
                        color = DuForestGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Primary Action: Sign In
        Button(
            onClick = onSignInSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("auth_sign_in_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign In with DU Credentials",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Secondary Action: Request Faculty Access / Register
        OutlinedButton(
            onClick = onRegisterRequestClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("auth_register_button"),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFFEFF6FF),
                contentColor = Color(0xFF1E40AF)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PersonAdd,
                contentDescription = null,
                tint = Color(0xFF1E40AF),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Request Faculty Access / Register",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E40AF)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot password link
        TextButton(
            onClick = onForgotPasswordClick,
            modifier = Modifier.testTag("auth_forgot_password_button")
        ) {
            Text(
                text = "Forgot password or lost institutional key?",
                color = DuTextSecondary,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Links footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Student Attendance Portal ↗",
                color = DuForestGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "  •  ",
                color = DuTextMuted,
                fontSize = 12.sp
            )
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = null,
                tint = DuTextSecondary,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "DU IT Helpdesk",
                color = DuTextSecondary,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FeatureInfoCard(
    icon: ImageVector,
    iconTint: Color,
    badge: String,
    badgeColor: Color,
    badgeTextColor: Color,
    title: String,
    description: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DuSurfaceSubtle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = badgeColor
                    ) {
                        Text(
                            text = badge,
                            color = badgeTextColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        color = DuTextSecondary,
                        lineHeight = 16.sp
                    )
                )
            }
        }
    }
}
