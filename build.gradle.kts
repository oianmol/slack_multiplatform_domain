plugins {
  kotlin("multiplatform") version "1.7.20"
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
  iosArm64()
  iosSimulatorArm64()
  iosX64()
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

