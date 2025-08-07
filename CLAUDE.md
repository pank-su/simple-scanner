# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

### Building the App
```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleRelease        # Build release APK
./gradlew installDebug           # Install debug APK to connected device
```

### Testing
```bash
./gradlew test                   # Run unit tests
./gradlew connectedAndroidTest   # Run instrumented tests on device/emulator
```

### Code Quality
```bash
./gradlew lint                   # Run Android lint checks
./gradlew lintDebug              # Run lint on debug variant only
```

### Gradle Tasks
```bash
./gradlew tasks                  # List all available tasks
./gradlew clean                  # Clean build artifacts
```

## Project Architecture

### High-Level Structure
This is an Android document scanning app built with:
- **Kotlin** with Jetpack Compose for UI
- **Hilt** for dependency injection (migrated from Koin)
- **DataStore** with Protocol Buffers for data persistence
- **MVVM + Clean Architecture** pattern
- **Google ML Kit Document Scanner** for document scanning functionality

### Package Structure
```
su.pank.simplescanner/
├── data/                    # Data layer
│   ├── di/                 # Dependency injection modules
│   ├── preferences/        # User preferences repository
│   └── scans/             # Scans repository and data models
├── domain/                 # Domain layer (use cases)
├── proto/                  # Generated Protocol Buffer classes
└── ui/                     # Presentation layer
    ├── nav/               # Navigation
    ├── theme/             # Compose theme and styling
    └── ui/                # UI components and screens
```

### Key Components

#### Data Layer
- **ScansRepository**: Manages scanned documents using DataStore with protobuf serialization
- **UserPreferencesRepository**: Handles app preferences (theme settings, Google API status)
- **Protocol Buffers**: Used for efficient data serialization (`scanned.proto`, `user_preferences.proto`)

#### Dependency Injection
- Uses **Hilt** (recently migrated from Koin)
- Main DI module: `app/src/main/java/su/pank/simplescanner/data/di/dataModule.kt`
- Application class: `MainApplication.kt` with `@HiltAndroidApp`
- ViewModels use `@HiltViewModel` annotation

#### UI Architecture
- **Jetpack Compose** for all UI
- **Material 3** theming with dynamic colors support
- **Splash screen** with conditional display based on Google Services check
- **Edge-to-edge** display support

### Data Models
The app handles two main types of scanned items:
- **PdfFile**: Single PDF document
- **JpgItem**: Collection of JPG images

Both are serialized using Protocol Buffers for efficient storage in DataStore.

### Google Services Integration
- Uses Google ML Kit Document Scanner (`play-services-mlkit-document-scanner`)
- Includes Google Services availability checking via `CheckGoogleServicesUseCase`
- Handles different states: OK, RESOLVABLE (needs update), ERROR

## Development Notes

### Protocol Buffers
- Proto files are in `app/src/main/proto/`
- Generated classes are in the `su.pank.simplescanner.proto` package
- Uses "lite" runtime for reduced APK size

### Theme and Styling
- Material 3 theming with dynamic colors
- Custom icons in `ui/theme/icons/`
- Supports both light and dark themes

### Migration History
- Recently migrated from Koin to Hilt for dependency injection
- Reorganized data layer with separate packages for different concerns

### Target Configuration
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)
- **Compile SDK**: 35
- **Java**: Version 11