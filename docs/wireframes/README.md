# MediVault UI/UX Wireframes

This directory contains wireframes and UI design documents for the MediVault application.

## Main Screens

### 1. Authentication Screen
```
┌─────────────────────────────┐
│                             │
│           MediVault         │
│                             │
│    ┌─────────────────────┐  │
│    │     Username        │  │
│    └─────────────────────┘  │
│                             │
│    ┌─────────────────────┐  │
│    │     Password        │  │
│    └─────────────────────┘  │
│                             │
│    ┌─────────────────────┐  │
│    │        Login        │  │
│    └─────────────────────┘  │
│                             │
│    [Biometric Login]        │
│                             │
└─────────────────────────────┘
```

### 2. Home Dashboard
```
┌─────────────────────────────┐
│ MediVault        [Settings] │
│ ─────────────────────────── │
│                             │
│ ┌─────────┐  ┌─────────┐    │
│ │ Records │  │ Documents│    │
│ └─────────┘  └─────────┘    │
│                             │
│ ┌─────────┐  ┌─────────┐    │
│ │ Medica- │  │ Appoint-│    │
│ │ tions   │  │ ments   │    │
│ └─────────┘  └─────────┘    │
│                             │
│ Recent Activity:            │
│ - Added blood test results  │
│ - Updated medication list   │
│ - New doctor appointment    │
│                             │
│ [Bottom Navigation Bar]     │
└─────────────────────────────┘
```

### 3. Medical Records List
```
┌─────────────────────────────┐
│ Records           [+] [🔍]  │
│ ─────────────────────────── │
│                             │
│ Categories:                 │
│ [All] [Lab] [Imaging] [Diag]│
│                             │
│ ┌─────────────────────┐     │
│ │ Blood Test Results  │     │
│ │ March 15, 2024      │     │
│ │ Dr. Smith           │     │
│ └─────────────────────┘     │
│                             │
│ ┌─────────────────────┐     │
│ │ X-Ray Right Ankle   │     │
│ │ February 2, 2024    │     │
│ │ City Hospital       │     │
│ └─────────────────────┘     │
│                             │
│ ┌─────────────────────┐     │
│ │ Annual Physical     │     │
│ │ January 10, 2024    │     │
│ │ Dr. Johnson         │     │
│ └─────────────────────┘     │
│                             │
└─────────────────────────────┘
```

### 4. Record Detail View
```
┌─────────────────────────────┐
│ ← Blood Test Results   [✏️] │
│ ─────────────────────────── │
│                             │
│ Date: March 15, 2024        │
│ Provider: Dr. Smith         │
│ Category: Lab Results       │
│                             │
│ Results:                    │
│ - Glucose: 95 mg/dL         │
│ - Cholesterol: 180 mg/dL    │
│ - HDL: 65 mg/dL             │
│ - LDL: 100 mg/dL            │
│                             │
│ Notes:                      │
│ All values within normal    │
│ range. Follow up in 6       │
│ months.                     │
│                             │
│ [View Attached Document]    │
│                             │
│ [Share] [Export PDF]        │
└─────────────────────────────┘
```

### 5. Profile Management
```
┌─────────────────────────────┐
│ Profile                     │
│ ─────────────────────────── │
│                             │
│ ┌─────┐ John Smith          │
│ │  J  │ DOB: 05/12/1980     │
│ └─────┘ Blood Type: O+      │
│                             │
│ ┌─────────────────────────┐ │
│ │ Emergency Contacts      │ │
│ └─────────────────────────┘ │
│                             │
│ ┌─────────────────────────┐ │
│ │ Allergies               │ │
│ └─────────────────────────┘ │
│                             │
│ ┌─────────────────────────┐ │
│ │ Insurance Information   │ │
│ └─────────────────────────┘ │
│                             │
│ ┌─────────────────────────┐ │
│ │ Primary Care Provider   │ │
│ └─────────────────────────┘ │
│                             │
│ [Family Members]            │
└─────────────────────────────┘
```

## User Flow Diagrams

### Primary User Flow
```
Login → Dashboard → Records List → Record Detail → Edit Record → Dashboard
```

### Document Import Flow
```
Dashboard → Documents → Import → Take Photo/Select File → Add Metadata → Save → View Document
```

### New Record Creation Flow
```
Dashboard → Records → Add New → Select Category → Fill Form → Save → Record Detail
```

## Color Scheme

- Primary: #1A73E8 (Blue)
- Secondary: #009688 (Teal)
- Background (Light): #F8F9FA
- Background (Dark): #121212
- Text Primary: #202124
- Text Secondary: #5F6368
- Success: #34A853 (Green)
- Error: #EA4335 (Red)
- Warning: #FBBC04 (Yellow)

## Typography

- Font Family: Roboto
- Headings: Roboto Medium
- Body: Roboto Regular
- Emphasis: Roboto Bold
- Caption: Roboto Light

## Implementation Notes

These wireframes serve as a starting point for UI development. Actual implementation may vary slightly based on platform constraints and usability testing feedback. All screens should support both light and dark mode versions. 