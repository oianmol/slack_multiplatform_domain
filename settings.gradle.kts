pluginManagement {
  repositories {
    google()
    jcenter()
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
  }

  resolutionStrategy {
    eachPlugin {
      if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
        useModule("com.android.tools.build:gradle:7.2.2")
      }
    }
  }
}
rootProject.name = "slack_kmp_domain"
