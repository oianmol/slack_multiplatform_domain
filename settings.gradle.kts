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
        useModule(BuildPlugins.ANDROID_TOOLS_BUILD_GRADLE)
      }
    }
  }
}
rootProject.name = "slack_kmp_domain"
