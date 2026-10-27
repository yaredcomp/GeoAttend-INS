# GeoAttend INS — Dilla University Instructor Mobile Application

An authoritative, secure mobile application for lecturers and instructors at Dilla University (DU), Ethiopia, providing perimeter-locked lecture management, biometric attendance seals, real-time spatial radar roll-call, class-time accountability, and cryptographic synchronization with the DU Academic Registrar & SIS.

---

## User Review & Critical Decisions

> [!IMPORTANT]
> The following architectural and UX choices have been confirmed based on the initial assessment and your clarifying responses:

- **Confirmed Decision 1 (Classroom Mapping)**: Campus radar with an interactive offline vector map of the Oda Ya'a Academic Complex (floors, lecture halls, BLE beacon nodes, distance to podium) backed by native GPS and a TomTom map fallback.
- **Confirmed Decision 2 (Hardware & Simulation Modes)**: Full integration with real Android hardware (Location Services, BiometricPrompt, BLE sensors) accompanied by an institutional Prototype Simulation Bar enabling instant testing of geofence locks, perimeter drifts, and mock beacon pings in emulator environments.
- **Confirmed Decision 3 (Initial Persona & Auth)**: Pre-populated with **Dr. Dawit Abebe Tadesse** (Assistant Professor, Dept. of Computer Science & Software Engineering, Staff ID: `DU/FAC/2024/089`) for immediate walkthrough, while offering full switchable authentication (DU Institutional SSO, Registration Request, Password Recovery, and Google Sign-In).
- **Confirmed Decision 4 (Branding & Identity)**: Fully branded as **GeoAttend INS** across metadata, launcher labels, strings, and UI components to designate the instructor application within the shared GeoAttend DU ecosystem.

---

## 1. Overview & Core Concept

### What It Does
**GeoAttend INS** enables university instructors to initiate, supervise, and cryptographically conclude lecture attendance sessions. Sessions can only be activated when 5 physical and cryptographic readiness criteria are satisfied:
1. Validated schedule window (e.g. 09:20–09:40 AM for a 09:30 AM lecture).
2. Verified classroom geofence lock (within assigned hall perimeter, e.g. Hall CNCS-B04).
3. BLE beacon mesh proximity signal integrity.
4. Enrolled cohort quorum threshold.
5. Android hardware attestation & faculty biometric verification (Fingerprint or Face ID).

During lectures, instructors monitor real-time telemetry (in-zone count, student drift, telemetry discrepancies, rolling beacon codes), pulse mid-lecture checkpoints, adjudicate anomalies, and seal finalized attendance sessions to the registrar.

### Target Audience & Persona
- **Primary Persona**: University lecturers, lab instructors, department heads, and academic deans at Dilla University ("University of the Green Land").
- **Context**: High-density lecture halls, computer laboratories, and seminar halls across Main Campus and the Oda Ya'a Complex.

### Key Value
Eliminates proxy attendance, enforces lecture punctuality accountability, and provides indisputable, timestamped attendance records synced to the institutional registrar.

---

## 2. User Experience & Visual Design

### Design Movement & Brand Aesthetic
Rooted in **Modern Material 3 Academic Architecture** and the Dilla University design system:
- **Primary Green (`#1B7A3E`)**: Institutional forest green for primary actions, active tabs, and validated seals.
- **Surface Neutrals (`#F8F9FA` canvas, `#FFFFFF` containers, `#E8F5E9` secure zone)**: High-contrast, clean surfaces designed for legibility under bright outdoor light.
- **Academic Gold (`#F59E0B` / `#FFD700`)**: Pending states, warning timers, and inception deltas.
- **Alert Crimson (`#E41F1F`)**: Unauthorized perimeter drifts, spoofing warnings, and unexcused absences.
- **Typography**: Crisp `Inter` typography hierarchy with tabular figures for countdowns and roll-call metrics.

### Key Screens & Interactive Flows

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                            GEOATTEND INS FLOW                               │
└─────────────────────────────────────────────────────────────────────────────┘
  [Welcome / Login] ──► [Registration Request] / [Password Recovery]
         │
         ▼
  [Instructor Dashboard] (Home)
    ├── Next Scheduled Class Card (Countdown, Geofence status, Verify & Start)
    ├── Urgent Alerts & Discrepancies (Review & Sign-Off)
    ├── Telemetry Overview (Classes, 98.2% Punctuality, Active Students)
    └── Remaining Classes Today
         │
         ▼ (Tap Class / Start Class)
  [Class Details & Readiness Checklist] (5/5 Security Parameters)
         │
         ▼ (Tap "Proceed to Biometric Verification")
  [Classroom Geofence Radar & Map] ──► [Faculty Biometric Seal (BiometricPrompt)]
         │
         ▼ (Success)
  [Start Class Confirmation] (Session Token, Rolling Beacon Code, QR broadcast)
         │
         ▼
  [Live Class Session Monitoring]
    ├── In-Zone Telemetry Ring (e.g. 92% present, 59/64 students)
    ├── Real-Time Student Roster (Filter: All, Present, Pending; Override actions)
    ├── Mid-Lecture Checkpoint & Secondary Beacon Pulse
    └── Conclude & End Class Session Modal
         │
         ▼ (Adjudicate Anomaly & Apply Cryptographic Seal)
  [Session Summary & Registrar Receipt] (Timing matrix, Quorum stats, PDF/CSV)
         │
         ▼
  [Supporting Hubs]:
    ├── Schedule (Daily View & Weekly Grid with Credit Hours)
    ├── Classes (Curriculum Progress, Archived Session Ledgers)
    ├── Alerts (Real-Time Dispatch, BLE Node Alerts, Telemetry Appeals)
    └── Profile (Hardware Attestation, Ethiopian Calendar, Workload)
```

---

## 3. Key Product Decisions & Trade-Offs

### 1. Hybrid Geolocation & Spatial Radar
- **Approach**: Combine native Android GPS/Network location, BLE beacon proximity detection, and an interactive vector radar view of Oda Ya'a Hall CNCS-B04 with an optional TomTom map layer.
- **Why**: Pure GPS has indoor multipath drift in multi-story concrete academic complexes. The dual GPS + BLE beacon verification ensures high accuracy (±1.8m) while providing instructors with an intuitive spatial radar display.

### 2. Dual-Tier Authentication & Local Fast-Switching
- **Approach**: Implement official Firebase Authentication and Jetpack Credential Manager for Google Sign-In, coupled with pre-configured faculty profiles (Dr. Dawit Abebe) and prototype toggle controls.
- **Why**: Allows instant testing on browser-based streaming emulators without external sign-in friction, while preserving full compliance with cloud production security requirements.

### 3. Authoritative Class-Time Accounting
- **Approach**: Start and end timestamps are verified by backend time; late starts (+1m 14s delay) and early dismissals (-1m 20s) are logged separately into an immutable timing audit matrix.
- **Why**: Eliminates device-clock tampering while preventing duplicate penalty deductions.

---

## 4. Technical Architecture & Data Strategy

### System Component Diagram

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          PRESENTATION LAYER (Jetpack Compose)               │
├─────────────────────────────────────────────────────────────────────────────┤
│  TopAppBar (DU Crest, Title, Alerts, Profile)                               │
│  Navigation (Dashboard, Schedule, Classes, Alerts, Profile)                 │
│  Screens:                                                                   │
│   • DashboardScreen     • ClassDetailsScreen      • GeofenceRadarScreen     │
│   • BiometricSealDialog • LiveSessionScreen       • ConcludeClassSheet      │
│   • SessionSummaryView  • AttendanceReviewScreen  • PunctualityScreen       │
├─────────────────────────────────────────────────────────────────────────────┤
│                          VIEWMODEL & STATE FLOW                             │
├─────────────────────────────────────────────────────────────────────────────┤
│  InstructorViewModel   SessionViewModel   ScheduleViewModel   AuthViewModel │
│     └── StateFlow<UiState> combining sensor telemetry & Firestore streams    │
├─────────────────────────────────────────────────────────────────────────────┤
│                          DOMAIN & REPOSITORY LAYER                          │
├─────────────────────────────────────────────────────────────────────────────┤
│  SessionRepository       AttendanceRepository       InstructorRepository    │
│  GeofenceService         BiometricHelper            BeaconSimulationManager │
├─────────────────────────────────────────────────────────────────────────────┤
│                          DATA & CLOUD STORAGE                               │
├─────────────────────────────────────────────────────────────────────────────┤
│  Cloud Firestore (Shared with GeoAttend DU Student & Admin)                 │
│    Collections: users, instructors, courses, rooms, schedules,              │
│                 classSessions, studentAttendance, auditLogs                 │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Shared Firestore Data Models

1. **`instructors/{instructorId}`**:
   - `userId`: String (Auth UID)
   - `name`: String ("Dr. Dawit Abebe Tadesse")
   - `staffId`: String ("DU/FAC/2024/089")
   - `email`: String ("dawit.a@du.edu.et")
   - `department`: String ("Computer Science")
   - `academicRank`: String ("Assistant Professor")
   - `status`: String ("active" / "pending_approval")
   - `assignedCourses`: List<String> (["CS-3012", "SE-4210", "CS-4901"])

2. **`schedules/{scheduleId}`**:
   - `courseCode`: String ("CS-3012")
   - `courseName`: String ("Advanced Database Systems")
   - `instructorId`: String
   - `roomId`: String ("CNCS-B04")
   - `dayOfWeek`: Int (4 = Thursday)
   - `startTime`: String ("09:30")
   - `endTime`: String ("11:30")
   - `enrolledCount`: Int (64)
   - `beaconId`: String ("BLE-DU-CNCS-04-A")

3. **`classSessions/{sessionId}`**:
   - `scheduleId`: String
   - `courseCode`: String
   - `instructorId`: String
   - `roomId`: String
   - `status`: String ("scheduled" | "active" | "concluded" | "flagged")
   - `scheduledStart`: Timestamp
   - `actualStart`: Timestamp
   - `scheduledEnd`: Timestamp
   - `actualEnd`: Timestamp?
   - `sessionToken`: String ("DU-SES-2024-B04-0931")
   - `rollingBeaconCode`: String ("DU-3012-K89X")
   - `startDelayMinutes`: Int (1)
   - `quorumRate`: Double (96.8)

4. **`studentAttendance/{attendanceId}`**:
   - `sessionId`: String
   - `studentId`: String ("DU/14298/13")
   - `studentName`: String ("Bekele Tadesse")
   - `initialStatus`: String ("present" | "tardy" | "absent" | "flagged")
   - `finalStatus`: String ("present" | "excused" | "absent")
   - `verificationType`: String ("geo_bio" | "geo_ble" | "override")
   - `driftDistanceMeters`: Double (8.2)

---

## 5. Implementation Phases & Milestones

1. **App Identity & Core Setup**: Update `metadata.json`, `strings.xml` to `GeoAttend INS`, configure `applicationId`, add adaptive launcher icon with DU emblem palette, and configure Gradle dependencies.
2. **Design Tokens & Theme**: Implement the full M3 Academic palette (`#1B7A3E`, `#E8F5E9`, `#F59E0B`, `#E41F1F`) in `Color.kt` and typography in `Type.kt`.
3. **Data Layer & Repository**: Build type-safe models, `SessionRepository`, `ScheduleRepository`, and simulated sensor/beacon providers.
4. **Interactive Radar & Geofence Canvas**: Create high-fidelity visual radar with pulse animation, classroom boundaries, BLE nodes, and distance indicator.
5. **Session Initiation & Verification Flow**: Build Class Details, 5/5 security checklist, Biometric Seal prompt, and Start-Class confirmation with rolling token.
6. **Live Lecture & Roster Monitoring**: Live roll-call ring, student list with instant status pills, anomaly adjudication dialogs, and secondary beacon pulse.
7. **Lecture Conclusion & Summary**: Chronometer audit, registrar seal checkbox, signature commit, and detailed session summary receipt.
8. **Dashboard, Schedule, History & Profile**: Rich timetable, curriculum delivery progress, punctuality analytics index, and settings.
9. **Verification**: Run Gradle compilation and verify error-free execution.
