plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a12_rest_01"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.a12_rest_01"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Agregamos la dependencia de retrofit a nuestro proyecto, que previamente
    //hemos añadido la libreria en "libs.versions.toml"
    implementation(libs.retrofit)

    implementation(libs.gson)
    implementation(libs.converterGson)
}