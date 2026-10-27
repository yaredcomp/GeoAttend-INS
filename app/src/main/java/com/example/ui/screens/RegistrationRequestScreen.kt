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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationRequestScreen(
    onBackToLogin: () -> Unit,
    onSubmitSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fullName by remember { mutableStateOf("Dr. Dawit Abebe Tadesse") }
    var email by remember { mutableStateOf("dawit.abebe@du.edu.et") }
    var staffId by remember { mutableStateOf("DU/FAC/2024/089") }
    var selectedRank by remember { mutableStateOf("Assistant Prof.") }
    var isSubmitted by remember { mutableStateOf(false) }

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
                            text = "Registration Request",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackToLogin,
                        modifier = Modifier.testTag("reg_back_button")
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
                            Icon(
                                imageVector = Icons.Default.AppRegistration,
                                contentDescription = null,
                                tint = DuForestGreen,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "FACULTY PROVISIONING",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuForestGreen
                            )
                            Text(
                                text = "Faculty Account Registration",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DuTextPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Submit your institutional credentials for Dean & Registrar authorization to unlock geofence radar rosters.",
                        fontSize = 12.sp,
                        color = DuTextSecondary,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Three badges
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BadgeStat(
                            icon = Icons.Default.CheckCircle,
                            title = "HR Linked",
                            subtitle = "Auto Match",
                            modifier = Modifier.weight(1f)
                        )
                        BadgeStat(
                            icon = Icons.Default.GpsFixed,
                            title = "GPS Radius",
                            subtitle = "Hall Lock",
                            modifier = Modifier.weight(1f)
                        )
                        BadgeStat(
                            icon = Icons.Default.Schedule,
                            title = "24-48 Hrs",
                            subtitle = "Turnaround",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Form Fields
            Text(
                text = "Full Legal Academic Name",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("reg_input_fullname"),
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null, tint = DuTextSecondary)
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Text(
                text = "Must match your official Dilla University employment letter.",
                fontSize = 11.sp,
                color = DuTextMuted,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Institutional Email Address (@du.edu.et only)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("reg_input_email"),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = DuTextSecondary)
                },
                trailingIcon = {
                    Surface(
                        color = DuGreenContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "DU SSO",
                            color = DuForestGreen,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Faculty / Staff ID",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = staffId,
                onValueChange = { staffId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("reg_input_staffid"),
                leadingIcon = {
                    Icon(Icons.Default.Badge, contentDescription = null, tint = DuTextSecondary)
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Designated Academic Rank",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DuTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RankSelectorCard(
                    title = "Assistant Prof.",
                    subtitle = "Senior Level",
                    isSelected = selectedRank == "Assistant Prof.",
                    onClick = { selectedRank = "Assistant Prof." },
                    modifier = Modifier.weight(1f)
                )
                RankSelectorCard(
                    title = "Lecturer",
                    subtitle = "Full Faculty",
                    isSelected = selectedRank == "Lecturer",
                    onClick = { selectedRank = "Lecturer" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RankSelectorCard(
                    title = "Assoc. Professor",
                    subtitle = "Post-Doc",
                    isSelected = selectedRank == "Assoc. Professor",
                    onClick = { selectedRank = "Assoc. Professor" },
                    modifier = Modifier.weight(1f)
                )
                RankSelectorCard(
                    title = "Graduate Assist.",
                    subtitle = "Lab Practicum",
                    isSelected = selectedRank == "Graduate Assist.",
                    onClick = { selectedRank = "Graduate Assist." },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // College Placement
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = DuSurfaceCard,
                border = androidx.compose.foundation.BorderStroke(1.dp, DuSurfaceBorder)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AccountBalance, contentDescription = null, tint = DuForestGreen)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "College & Department Placement",
                            fontSize = 11.sp,
                            color = DuTextSecondary
                        )
                        Text(
                            text = "CNCS • Computer Science",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuTextPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Notice
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFEF3C7).copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFFB45309),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Registrar Policy Notice",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E)
                        )
                        Text(
                            text = "Access requests are verified against official DU HR & SIS records within 24-48 business hours. You will receive an SMS and institutional email activation link.",
                            fontSize = 11.sp,
                            color = Color(0xFF78350F),
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    isSubmitted = true
                    onSubmitSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("reg_submit_button"),
                colors = ButtonDefaults.buttonColors(containerColor = DuForestGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Submit Registration Request",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BadgeStat(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = DuSurfaceSubtle
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = DuForestGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DuTextPrimary)
            Text(text = subtitle, fontSize = 9.sp, color = DuTextSecondary)
        }
    }
}

@Composable
private fun RankSelectorCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = if (isSelected) DuGreenContainer else DuSurfaceCard,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) DuForestGreen else DuSurfaceBorder
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = DuForestGreen)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) DuForestGreen else DuTextPrimary
                )
                Text(
                    text = subtitle,
                    fontSize = 9.sp,
                    color = DuTextSecondary
                )
            }
        }
    }
}
