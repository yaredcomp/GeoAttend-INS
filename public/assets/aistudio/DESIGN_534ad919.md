---
name: GeoAttend DU Instructor
colors:
  surface: '#f8f9ff'
  surface-dim: '#d0dbed'
  surface-bright: '#f8f9ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#eff4ff'
  surface-container: '#e6eeff'
  surface-container-high: '#dee9fc'
  surface-container-highest: '#d9e3f6'
  on-surface: '#121c2a'
  on-surface-variant: '#3f493f'
  inverse-surface: '#27313f'
  inverse-on-surface: '#eaf1ff'
  outline: '#6f7a6e'
  outline-variant: '#becabc'
  surface-tint: '#026d33'
  primary: '#00602b'
  on-primary: '#ffffff'
  primary-container: '#1b7a3e'
  on-primary-container: '#abffb9'
  inverse-primary: '#80da93'
  secondary: '#855300'
  on-secondary: '#ffffff'
  secondary-container: '#fea619'
  on-secondary-container: '#684000'
  tertiary: '#086019'
  on-tertiary: '#ffffff'
  tertiary-container: '#2b7a2f'
  on-tertiary-container: '#b4ffac'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#9bf7ad'
  primary-fixed-dim: '#80da93'
  on-primary-fixed: '#00210b'
  on-primary-fixed-variant: '#005225'
  secondary-fixed: '#ffddb8'
  secondary-fixed-dim: '#ffb95f'
  on-secondary-fixed: '#2a1700'
  on-secondary-fixed-variant: '#653e00'
  tertiary-fixed: '#a3f69c'
  tertiary-fixed-dim: '#88d982'
  on-tertiary-fixed: '#002204'
  on-tertiary-fixed-variant: '#005312'
  background: '#f8f9ff'
  on-background: '#121c2a'
  surface-variant: '#d9e3f6'
typography:
  display-lg:
    fontFamily: Inter
    fontSize: 34px
    fontWeight: '700'
    lineHeight: 40px
  headline-lg:
    fontFamily: Inter
    fontSize: 28px
    fontWeight: '700'
    lineHeight: 34px
  headline-md:
    fontFamily: Inter
    fontSize: 22px
    fontWeight: '600'
    lineHeight: 28px
  headline-sm:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
  label-lg:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
  label-md:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
  label-sm:
    fontFamily: Inter
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 14px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  margin: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system establishes an authoritative, reliable, and functional academic mobility framework for higher education instructors at Dilla University ("University of the Green Land"). Rooted in Material 3 guidelines and native Android interaction standards, it balances rigorous institutional gravitas with everyday operational clarity in lecture halls, research facilities, and campus perimeters.

### Brand Personality & Emotional Impact
- **Institutional Reliability:** Delivers unshakeable trust through solid structure, predictable layout patterns, and explicit state feedback during geolocation-locked attendance sessions.
- **Academic Prestige:** Evokes the natural heritage of the Gedeo zone and Dilla University via deep forest green foundations punctuated by academic gold accents.
- **Field-Ready Utility:** Engineered for quick glanceability under direct sunlight across campus quads, rapid one-handed verification during class roll calls, and fail-safe feedback on GPS lock status.

### Design Movement
**Corporate / Modern (Material 3 Academic):** A disciplined implementation of Material 3 ergonomics. The UI uses structured surface tiers, clean linear cards, strict 4dp/8dp spatial rhythms, and high-contrast status chips tailored for rapid data entry and verification.

## Colors

The palette is derived directly from the institutional heritage of Dilla University, pairing deep forest green with vibrant academic gold and high-legibility neutrals.

### Palette Architecture
- **Primary (`#1B7A3E`):** Dilla University Forest Green. Anchors major actions, primary app bars, active navigation states, and authorized validation buttons.
- **Secondary (`#F59E0B`):** Academic Gold. Applied to pending geofence verifications, session warning flags, audit reminders, and high-visibility counter badges.
- **Tertiary (`#2E7D32`):** Emerald Accent. Used for successful geofence locks, verified attendance confirmation badges, and positive statistical trends.
- **Neutral Surface & Text (`#1F2937`, `#F8F9FA`):** Dark charcoal for high-contrast legible typography (`#1F2937`), resting on crisp surface neutrals (`#F8F9FA`) and tinted container tones (`#E8F5E9`).
- **Error / Alert Crimson (`#E41F1F`):** Dedicated to unauthorized geofence breaches, spoofed coordinate alerts, and absent rosters.

### Surface Tiers & Container Strategy
- **Surface Default (`#F8F9FA`):** Outer canvas canvas background.
- **Surface Container Low (`#FFFFFF`):** High-clarity elevated cards, student roster tables, and input containers.
- **Surface Container (`#E8F5E9`):** Soft mint contextual containers indicating an active, secure geofenced session zone.
- **Surface Container High (`#E2E8F0`):** Inactive panel backings, structural divider lines, and neutral chips.

## Typography

Typography relies on `Inter` across all functional roles to guarantee crisp metric rendering, precise numerals in tabular student rosters, and legibility at smaller mobile scales.

### Hierarchy & Functional Roles
- **Display & Large Headlines (`display-lg`, `headline-lg`):** Reserved for top-level departmental dashboard headers, dynamic overall attendance percentages, and course identifiers.
- **Headline Medium & Small (`headline-md`, `headline-sm`):** Assigned to active lecture hall identifiers, module card headings, and modal sheet titles.
- **Body (`body-lg`, `body-md`, `body-sm`):** Standardizes student names, ID strings, GPS accuracy metadata, and academic session notes.
- **Label (`label-lg`, `label-md`, `label-sm`):** Geofence status indicators, interactive button actions, timestamps, and compact metric badges. Tabular figures (`tnum`) must be enforced for attendance counting widgets.

## Layout & Spacing

This design system uses an Android-native 8dp spatial grid with a 4dp baseline micro-grid for fine alignments like badge paddings and icon offsets.

### Screen Adaptability & Grid Structures
- **Compact Devices (Mobile < 600dp):** Single-column layout. 16dp (`1rem`) outer canvas margins, 16dp (`1rem`) vertical section gutters. Cards span 100% of the available width between gutters.
- **Expanded / Tablet Viewports (>= 600dp):** Two-column split layout. Left pane dedicates 40% width to real-time geofence map perimeter tracking; right pane utilizes 60% for live roll-call and metrics stream.

### Rhythmic Padding System
- **Dense Grouping (`space-xs` = 4dp, `space-sm` = 8dp):** Used for student avatar-to-name gaps, badge insets, chip gaps, and tabular row padding.
- **Standard Structural Gap (`space-md` = 16dp):** Inner card padding, list item separations, and form control fields.
- **Section Isolation (`space-lg` = 24dp, `space-xl` = 32dp):** Separates session control modules from student rosters, and top app bars from primary dashboards.

## Elevation & Depth

Visual hierarchy combines Material 3 tonal elevation with subtle, ambient shadow tints to separate active lecture telemetry from baseline canvas backgrounds.

### Elevation Levels
- **Level 0 (Flat, 0dp):** Outer canvas (`#F8F9FA`) and inline separator borders (`1px solid #E2E8F0`).
- **Level 1 (Card Default, 1dp):** Student roster cards, inactive course tiles, and session logs. Box shadow: `0px 1px 3px rgba(31, 41, 55, 0.08), 0px 1px 2px rgba(31, 41, 55, 0.04)`.
- **Level 2 (Active Interactive Cards, 3dp):** Live active session card, geofence radius control panel. Box shadow: `0px 4px 6px rgba(27, 122, 62, 0.08), 0px 2px 4px rgba(31, 41, 55, 0.06)`.
- **Level 3 (Navigation & App Bars, 4dp):** Sticky Android top app bar and bottom navigation bar. Employs crisp tonal tinting with surface `#FFFFFF` elevated over scrolling lists.
- **Level 4 (Floating Overlays, 6dp):** Floating Action Buttons (FAB for manual roll additions) and active geofence radar pings. Box shadow: `0px 8px 16px rgba(27, 122, 62, 0.20), 0px 4px 8px rgba(31, 41, 55, 0.08)`.

## Shapes

The system uses a balanced `roundedness: 2` geometry, creating modern, comfortable tactile targets while preserving institutional rigor.

### Shape Scale
- **Small Corners (`0.5rem` / 8px):** Input fields, interactive chips, status tags, table cell outlines, and metric summary badges.
- **Medium Corners (`1rem` / 16px):** Attendance cards, student profile modals, geofence radius map overlays, and bottom sheet containers.
- **Large & Circular (`1.5rem` / 24px to Full Pill / 9999px):** Android Floating Action Buttons (FAB), segmented control pills, student status toggles, and circular profile status rings.

## Components

### Buttons
- **Filled Primary Button:** Solid `#1B7A3E` background, white bold text (`label-lg`), 8px corner radius (`0.5rem`), height 48px to satisfy Android accessibility touch standards. Triggers actions such as "Initialize Geofence Session" and "Submit Final Roster".
- **Tonal Button:** Soft mint background (`#E8F5E9`), forest green text (`#1B7A3E`). Used for secondary actions like "Export Roster CSV" or "Recalibrate GPS".
- **Outlined / Destructive Button:** Border 1.5px solid `#E41F1F`, crimson text (`#E41F1F`), transparent background. Used for "Terminate Session Early" or "Revoke Attendance Record".

### Geofence Status Chips
- **Locked / Inside Perimeter:** Tonal `#E8F5E9` background with `#1B7A3E` text, featuring a filled 6px green circular status dot icon.
- **Pending / Calibrating:** Warm `#FEF3C7` background with `#B45309` (Amber) text, featuring a pulsating amber dot icon.
- **Breach / Outside Perimeter:** Light crimson `#FEE2E2` background with `#B91C1C` text, paired with an alert cross icon.

### Cards & Attendance Metrics Indicators
- **Active Session Module:** Elevated Level 2 white container encased in an active `#1B7A3E` 2px left-accent border. Top row showcases course code (`headline-sm`) and enrolled count badge.
- **Telemetry Counter Ring:** Center-weighted KPI block displaying present, late, and absent counts with radial percentage progress rings colored respectively in Emerald (`#2E7D32`), Academic Gold (`#F59E0B`), and Alert Crimson (`#E41F1F`).

### Roster Lists & Selection Controls
- **Student Roll Item:** 64px row height. Displays circular student avatar (with green/amber presence border), 2-line body text (Full Name, Student ID), and a quick-action 3-state attendance pill (Present, Late, Absent).
- **Checkboxes & Radios:** Primary green fill (`#1B7A3E`) when selected, high-contrast dark charcoal unchecked border (`#9CA3AF`). Ripple feedback conforms to Android Material touch states (`rgba(27, 122, 62, 0.12)`).

### Input Fields
- **Outlined Text Field:** 56px height, 8px radius. Inactive border `#D1D5DB`, active focused border 2px solid `#1B7A3E` with an elevated floating label. Error state triggers a `#E41F1F` outline with an accompanying error message.

### Android Navigation Bars
- **Top App Bar:** `#FFFFFF` background with subtle Level 1 divider, showing Dilla University crest emblem, contextual view title (`headline-md`), and class switch icon.
- **Bottom Navigation Bar:** Level 3 elevation, white background, 80px container height. Active tab items feature an oval pill indicator filled with `#E8F5E9` and an icon/label tinted with `#1B7A3E`. Inactive items render with `#6B7280`.