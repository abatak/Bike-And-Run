import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    // Enable KSP
    id("com.google.devtools.ksp")
}

val props = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    props.load(FileInputStream(localPropertiesFile))
}




android {
    namespace = "com.batakantonio.bikeandrun"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.batakantonio.bikeandrun"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"


        buildConfigField("String", "MAPS_API_KEY", "\"${props["MAPS_API_KEY"]}\"")


        // Export schema directory
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }


        buildFeatures {
            viewBinding = true
            buildConfig = true
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }


    buildFeatures {
        viewBinding = true
    }
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "true")
}


val mockitoAgent = configurations.create("mockitoAgent")
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.androidx.cardview)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.work.runtime.ktx)
    implementation (libs.vmadalin.easypermissions.ktx)

    // Navigation dependencies
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    // Firebase dependencies
    implementation(platform(libs.firebase.bom))
    implementation(libs.com.google.firebase.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.ui.auth)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.com.google.firebase.firebase.firestore)
    implementation(libs.firebase.database)
    implementation(libs.play.services.location)
    implementation(libs.androidx.lifecycle.service)
    implementation (libs.androidx.startup.runtime)

    // Room dependencies
    implementation(libs.androidx.room.runtime)
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.core.testing)
    annotationProcessor(libs.androidx.room.compiler)

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // Koin dependency
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.1.1"))
    implementation(libs.koin.core)
    // Koin annotation
    implementation(libs.koin.annotations)
    // Koin Annotations KSP Compiler
    ksp(libs.insert.koin.koin.ksp.compiler)
    // Navigation Graph
    implementation(libs.koin.androidx.navigation)

    // Koin Test features (JUnit and instrumentation)
    testImplementation(libs.koin.test)
    androidTestImplementation (libs.koin.test)
    androidTestImplementation (libs.koin.android.test)
    // Koin for JUnit 4
    testImplementation(libs.koin.test.junit4)
    // Koin for JUnit 5
    testImplementation(libs.koin.test.junit5)
    // For LiveData & ViewModel testing
    testImplementation (libs.androidx.core.testing) // or latest version

    testImplementation(libs.junit)
    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    mockitoAgent(libs.mockito.core) { isTransitive = false }
    androidTestImplementation (libs.mockito.mockito.android)

    // For unit tests (JVM)
    testImplementation(libs.kotlinx.coroutines.test)

    // For Android instrumented tests
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.androidx.navigation.testing)

    androidTestImplementation(libs.androidx.junit)

    // For launching activities in instrumentation tests
    androidTestImplementation (libs.core.ktx)

    androidTestImplementation (libs.androidx.startup.runtime)

    // Espresso
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation (libs.androidx.espresso.intents)
    // For testing fragments
    debugImplementation(libs.fragment.testing)
    debugImplementation(libs.androidx.fragment.ktx)
    debugImplementation(libs.androidx.core)
    debugImplementation(libs.androidx.rules)
    debugImplementation(libs.androidx.runner)

}

tasks.withType<Test>().configureEach {
    // Allow Byte Buddy / Mockito agent
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")

    // Optional: better test output
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}