plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    id("com.google.protobuf") version "0.9.4"

}

android {
    namespace = "com.example.sanbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sandbox"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    hilt {
        enableAggregatingTask = false
    }

//    composeOptions {
//        // REQUIRED for Kotlin 2.x
//        kotlinCompilerExtensionVersion = "1.5.14"
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(
                org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
            )
        }
    }

    packagingOptions {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

dependencies {
    // --- Core ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    // --- Compose BOM ---
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Compose libs (NO versions here)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.preview)
    debugImplementation(libs.compose.tooling)

    // Lifecycle / Activity
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)

    // Navigation
    implementation(libs.navigation.compose)

    // --- Hilt ---
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Hilt + Compose
    implementation(libs.hilt.navigation.compose)


    // --- Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(project(":core:network"))

    // wearable
    implementation(libs.play.services.wearable.v1820)


    // grpc
    implementation("io.grpc:grpc-okhttp:1.63.0")
    implementation("io.grpc:grpc-stub:1.63.0")
    implementation("io.grpc:grpc-protobuf-lite:1.63.0")
    implementation("com.google.protobuf:protobuf-kotlin-lite:4.26.1")
    implementation("io.grpc:grpc-kotlin-stub:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")


}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.26.1"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.63.0"
        }
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") { option("lite") }
                create("kotlin") { option("lite") }
            }
            task.plugins {
                create("grpc") { option("lite") }
                create("grpckt") { option("lite") }
            }
        }
    }
}
