# Simple Scanner

A modern, lightweight Android document scanner application built with Jetpack Compose and Google's ML Kit. Simple Scanner allows you to easily scan documents, organize them, and export them as PDF or JPEG files.

## Features

- **High-Quality Scanning**: Utilizes Google ML Kit Document Scanner for precise edge detection and image enhancement.
- **Multiple Formats**: Save your scans as PDF documents or JPEG images.
- **Scan Management**: View, organize, and manage your history of scanned documents.
- **Modern UI/UX**: Designed with Material 3 and Jetpack Compose for a fluid and intuitive user experience.
- **Offline Capable**: All data is stored locally using Room Database.
- **Customizable**: Settings for file formats and app preferences.

## Tech Stack

This project is built using modern Android development practices and libraries:

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Architecture**: MVVM (Model-View-ViewModel) with Clean Architecture principles
- **Navigation**: [AndroidX Navigation 3](https://developer.android.com/jetpack/androidx/releases/navigation3)
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Data Storage**: [Proto DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Scanning Engine**: [Google ML Kit Document Scanner](https://developers.google.com/ml-kit/vision/doc-scanner)
- **Concurrency**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Background Work**: [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- **Serialization**: Kotlinx Serialization

## Requirements

- Android Studio Koala Feature Drop | 2024.1.2 or newer
- JDK 17
- Android SDK 36 (compileSdk)
- Min SDK 26 (Android 8.0 Oreo)

## Setup & Installation

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/simple-scanner.git
    cd simple-scanner
    ```

2.  **Open in Android Studio**:
    Open Android Studio and select "Open an existing Android Studio project", then navigate to the cloned directory.

3.  **Sync Gradle**:
    Wait for Android Studio to download dependencies and sync the project.

4.  **Run the App**:
    Connect an Android device (or use an emulator with Google Play Services) and click the "Run" button (Shift+F10).

## License

```text
Copyright 2024 Simple Scanner Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
