# Build Conventions

Gradle convention plugins for Android and Kotlin Multiplatform projects, published on Maven Central.

## Setup

Add `mavenCentral()` to the `pluginManagement` block in your project's `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}
```

## Available Plugins

### `convention.android.application`

For Android application modules. Applies: AGP, Kotlin Android, Compose compiler, KSP, Hilt.

```kotlin
// build.gradle.kts
plugins {
    id("convention.android.application") version "1.0.0"
}

android(namespace = "com.example.app")
```

---

### `convention.android.library`

For Android library modules without Compose. Applies: AGP library, Kotlin Android, KSP, Serialization.

```kotlin
plugins {
    id("convention.android.library") version "1.0.0"
}

android(namespace = "com.example.feature.data")
```

---

### `convention.android.library.compose`

For Android library modules with Compose. Applies: AGP library, Kotlin Android, Compose compiler, KSP, Serialization.

```kotlin
plugins {
    id("convention.android.library.compose") version "1.0.0"
}

androidCompose(namespace = "com.example.feature.ui")
```

---

### `convention.kotlin.library`

For pure Kotlin JVM modules (no Android). Applies: Kotlin JVM.

```kotlin
plugins {
    id("convention.kotlin.library") version "1.0.0"
}
```

---

### `convention.kmp.application`

For Kotlin Multiplatform application modules with Compose Multiplatform. Applies: KMP, AGP application, Compose Multiplatform, Compose compiler.

```kotlin
plugins {
    id("convention.kmp.application") version "1.0.0"
}
```

---

### `convention.kmp.library`

For Kotlin Multiplatform library modules. Applies: KMP, AGP library, Serialization. Targets: `androidTarget`, `iosX64`, `iosArm64`, `iosSimulatorArm64`.

```kotlin
plugins {
    id("convention.kmp.library") version "1.0.0"
}
```

---

### `convention.jacoco`

Adds JaCoCo coverage reporting to any module. Generates XML and HTML reports via the `jacocoReport` task.

```kotlin
plugins {
    id("convention.android.library") version "1.0.0"
    id("convention.jacoco") version "1.0.0"
}
```

Run the report:

```bash
./gradlew :module-name:jacocoReport
```

## Default Configuration

All plugins share the following defaults, defined in `AppConfig`:

| Property | Value |
|---|---|
| `compileSdk` | 36 |
| `targetSdk` | 36 |
| `minSdk` | 31 |
| `jvmTarget` | 11 |

## Dependency Versions

| Dependency | Version |
|---|---|
| Android Gradle Plugin | 9.1.0 |
| Kotlin | 2.3.20 |
| Compose Multiplatform | 1.10.3 |
| KSP | 2.3.6 |
| Hilt | 2.59.2 |

## Publishing a New Version

Create and push a git tag — the GitHub Actions workflow publishes automatically:

```bash
git tag v1.0.0
git push origin v1.0.0
```

## License

```
Copyright 2025 Douglas Santana

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
