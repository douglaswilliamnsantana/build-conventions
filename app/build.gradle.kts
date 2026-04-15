plugins {
    `kotlin-dsl`
    id("com.vanniktech.maven.publish") version "0.30.0"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:9.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.3.20")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.10.3")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.3.6")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.59.2")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.3.20")
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates(
        groupId = "io.github.douglaswilliamnsantana",
        artifactId = "build-conventions",
        version = "1.0.0"
    )

    pom {
        name.set("Build Conventions")
        description.set("Gradle convention plugins for Android and Kotlin Multiplatform projects")
        inceptionYear.set("2025")
        url.set("https://github.com/douglaswilliamnsantana/build-conventions")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("douglaswilliamnsantana")
                name.set("Douglas Santana")
                email.set("douglaswilliamnsantana@gmail.com")
                url.set("https://github.com/douglaswilliamnsantana")
            }
        }

        scm {
            url.set("https://github.com/douglaswilliamnsantana/build-conventions")
            connection.set("scm:git:git://github.com/douglaswilliamnsantana/build-conventions.git")
            developerConnection.set("scm:git:ssh://git@github.com/douglaswilliamnsantana/build-conventions.git")
        }
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "convention.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "convention.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kmpApplication") {
            id = "convention.kmp.application"
            implementationClass = "KmpApplicationConventionPlugin"
        }
        register("kmpLibrary") {
            id = "convention.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("jacoco") {
            id = "convention.jacoco"
            implementationClass = "JacocoConventionPlugin"
        }
    }
}