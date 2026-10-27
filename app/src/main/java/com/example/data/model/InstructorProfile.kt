package com.example.data.model

data class InstructorProfile(
    val id: String = "DU-FAC-089",
    val userId: String = "usr_dawit_089",
    val name: String = "Dr. Dawit Abebe Tadesse",
    val staffId: String = "DU/FAC/2024/089",
    val email: String = "dawit.a@du.edu.et",
    val department: String = "Computer Science & Software Engineering",
    val college: String = "College of Natural & Computational Sciences (CNCS)",
    val academicRank: String = "Assistant Professor",
    val officeLocation: String = "CNCS • Oda Ya'a Academic Complex • Level 3, Wing B",
    val teachingSemester: String = "2017 E.C. / Semester II",
    val academicYear: String = "2024 / 2025 GC Senate",
    val creditLoad: Double = 12.0,
    val activeCohortsCount: Int = 3,
    val hardwareAttestationKey: String = "DU-ECDSA Key #8F4A Secure",
    val knoxStrongBoxHash: String = "SHA256: 9e:4b:d1:77:c0:8a:f2",
    val biometricEnabled: Boolean = true,
    val ethiopianCalendarEnabled: Boolean = true,
    val portalLanguage: String = "EN", // "EN" or "AM"
    val isVerified: Boolean = true
)
