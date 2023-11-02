plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "co.jonathanbernal.authjsb"
    compileSdk = 33

    defaultConfig {
        applicationId = "co.jonathanbernal.authjsb"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":libauth"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //UI
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.zxing:core:3.3.3")

    // Zxing
    implementation("com.journeyapps:zxing-android-embedded:3.6.0@aar")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    //Rx
    implementation("com.jakewharton.rxbinding2:rxbinding:2.2.0")
    implementation("com.jakewharton.rxbinding2:rxbinding-appcompat-v7:2.2.0")

    //Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")

    implementation("androidx.multidex:multidex:2.0.1")
}