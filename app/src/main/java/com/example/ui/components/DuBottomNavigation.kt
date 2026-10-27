package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

enum class NavigationTab(val label: String) {
    HOME("Home"),
    SCHEDULE("Schedule"),
    CLASSES("Classes"),
    ALERTS("Alerts"),
    PROFILE("Profile")
}

@Composable
fun DuBottomNavigation(
    selectedTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    alertsCount: Int = 3,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.testTag("du_bottom_navigation_bar"),
        containerColor = DuSurfaceCard,
        contentColor = DuTextPrimary,
        tonalElevation = 8.dp,
        windowInsets = WindowInsets.navigationBars
    ) {
        // HOME
        NavigationBarItem(
            selected = selectedTab == NavigationTab.HOME,
            onClick = { onTabSelected(NavigationTab.HOME) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.HOME) Icons.Filled.GridView else Icons.Outlined.GridView,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = NavigationTab.HOME.label,
                    fontSize = 11.sp,
                    fontWeight = if (selectedTab == NavigationTab.HOME) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DuForestGreen,
                selectedTextColor = DuForestGreen,
                indicatorColor = DuGreenContainer,
                unselectedIconColor = DuTextSecondary,
                unselectedTextColor = DuTextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_home")
        )

        // SCHEDULE
        NavigationBarItem(
            selected = selectedTab == NavigationTab.SCHEDULE,
            onClick = { onTabSelected(NavigationTab.SCHEDULE) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.SCHEDULE) Icons.Filled.CalendarMonth else Icons.Outlined.CalendarMonth,
                    contentDescription = "Schedule",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = NavigationTab.SCHEDULE.label,
                    fontSize = 11.sp,
                    fontWeight = if (selectedTab == NavigationTab.SCHEDULE) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DuForestGreen,
                selectedTextColor = DuForestGreen,
                indicatorColor = DuGreenContainer,
                unselectedIconColor = DuTextSecondary,
                unselectedTextColor = DuTextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_schedule")
        )

        // CLASSES
        NavigationBarItem(
            selected = selectedTab == NavigationTab.CLASSES,
            onClick = { onTabSelected(NavigationTab.CLASSES) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.CLASSES) Icons.Filled.School else Icons.Outlined.School,
                    contentDescription = "Classes",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = NavigationTab.CLASSES.label,
                    fontSize = 11.sp,
                    fontWeight = if (selectedTab == NavigationTab.CLASSES) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DuForestGreen,
                selectedTextColor = DuForestGreen,
                indicatorColor = DuGreenContainer,
                unselectedIconColor = DuTextSecondary,
                unselectedTextColor = DuTextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_classes")
        )

        // ALERTS
        NavigationBarItem(
            selected = selectedTab == NavigationTab.ALERTS,
            onClick = { onTabSelected(NavigationTab.ALERTS) },
            icon = {
                BadgedBox(
                    badge = {
                        if (alertsCount > 0) {
                            Badge(
                                containerColor = DuAlertCrimson,
                                contentColor = Color.White
                            ) {
                                Text("$alertsCount", fontSize = 9.sp)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (selectedTab == NavigationTab.ALERTS) Icons.Filled.Notifications else Icons.Outlined.Notifications,
                        contentDescription = "Alerts",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            label = {
                Text(
                    text = NavigationTab.ALERTS.label,
                    fontSize = 11.sp,
                    fontWeight = if (selectedTab == NavigationTab.ALERTS) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DuForestGreen,
                selectedTextColor = DuForestGreen,
                indicatorColor = DuGreenContainer,
                unselectedIconColor = DuTextSecondary,
                unselectedTextColor = DuTextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_alerts")
        )

        // PROFILE
        NavigationBarItem(
            selected = selectedTab == NavigationTab.PROFILE,
            onClick = { onTabSelected(NavigationTab.PROFILE) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.PROFILE) Icons.Filled.AccountCircle else Icons.Outlined.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = NavigationTab.PROFILE.label,
                    fontSize = 11.sp,
                    fontWeight = if (selectedTab == NavigationTab.PROFILE) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DuForestGreen,
                selectedTextColor = DuForestGreen,
                indicatorColor = DuGreenContainer,
                unselectedIconColor = DuTextSecondary,
                unselectedTextColor = DuTextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_profile")
        )
    }
}
