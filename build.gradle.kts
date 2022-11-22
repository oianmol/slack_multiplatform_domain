import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
    id("maven-publish")
}

group = "dev.baseio.slackclone"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
    google() // here
}

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    val iosx64 = iosX64()
    val iosarm64 = iosArm64()
    val iossimulatorarm64 = iosSimulatorArm64()

    configure(listOf(iosx64, iosarm64,iossimulatorarm64)) {
        binaries.framework {
            baseName = "slack_domain_layer"
        }
    }

    // Create a task to build a fat framework.
    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("debugFatFramework") {
        // The fat framework must have the same base name as the initial frameworks.
        baseName = "slack_domain_layer"
        // The default destination directory is "<build directory>/fat-framework".
        destinationDir = buildDir.resolve("fat-framework/debug")
        // Specify the frameworks to be merged.
        from(
            iosarm64.binaries.getFramework("DEBUG"),
            iosx64.binaries.getFramework("DEBUG"),
            //iossimulatorarm64.binaries.getFramework("DEBUG")
        )
    }

    // Create a task to build a fat framework.
    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("releaseFatFramework") {
        // The fat framework must have the same base name as the initial frameworks.
        baseName = "slack_domain_layer"
        // The default destination directory is "<build directory>/fat-framework".
        destinationDir = buildDir.resolve("fat-framework/release")
        // Specify the frameworks to be merged.
        from(
            iosarm64.binaries.getFramework("RELEASE"),
            iosx64.binaries.getFramework("RELEASE"),
            //iossimulatorarm64.binaries.getFramework("RELEASE")
        )
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}

kotlin {
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

