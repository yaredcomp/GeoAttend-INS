package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun StatusChip(
    text: String,
    modifier: Modifier = Modifier,
    statusType: StatusType = StatusType.SUCCESS,
    showDot: Boolean = true
) {
    val (bgColor, textColor, dotColor) = when (statusType) {
        StatusType.SUCCESS -> Triple(DuGreenContainer, DuForestGreen, DuForestGreen)
        StatusType.WARNING -> Triple(DuGoldLight, DuGoldDark, DuGold)
        StatusType.ERROR -> Triple(DuCrimsonLight, DuAlertCrimson, DuAlertCrimson)
        StatusType.INFO -> Triple(Color(0xFFE0F2FE), Color(0xFF0369A1), Color(0xFF0284C7))
        StatusType.NEUTRAL -> Triple(DuSurfaceSubtle, DuTextSecondary, DuTextMuted)
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showDot) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

enum class StatusType {
    SUCCESS,
    WARNING,
    ERROR,
    INFO,
    NEUTRAL
}
