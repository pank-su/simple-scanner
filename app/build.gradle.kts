plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

protobuf{
    protoc {
        artifact = "com.google.protobuf:protoc:4.30.1"
    }

    plugins {
        generateProtoTasks {
            all().forEach {
                it.builtins {
                    register("java") {
                        option("lite")
                    }
                    register("kotlin"){
                        option("lite")
                    }
                }
            }
        }
    }
}

android {
    namespace = "su.pank.simplescanner"
    compileSdk = 36

    defaultConfig {
        applicationId = "su.pank.simplescanner"
        minSdk = 31 // PDF service need 31 but we can use smth another (before 24)
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }

}

dependencies {

        implementation("androidx.pdf:pdf-viewer:1.0.0-alpha10")
    implementation("androidx.pdf:pdf-document-service:1.0.0-alpha10")


    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)

    implementation(libs.lottie.compose)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.material)


    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.protobuf.kotlin)

    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.document.scanner)
    implementation(libs.androidx.datastore)
    implementation(libs.navigation.compose)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}