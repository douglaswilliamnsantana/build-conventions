import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.application")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.fromTarget(AppConfig.jvmTarget))
                    }
                }
            }

            extensions.configure<BaseAppModuleExtension> {
                compileSdk = AppConfig.compileSdk

                defaultConfig {
                    applicationId = AppConfig.applicationId
                    minSdk = AppConfig.minSdk
                    targetSdk = AppConfig.targetSdk
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.valueOf(AppConfig.javaVersion)
                    targetCompatibility = JavaVersion.valueOf(AppConfig.javaVersion)
                }
            }
        }
    }
}
