import org.jetbrains.kotlin.utils.addToStdlib.ifTrue

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("io.realm.kotlin")
}

val flavorList = listOf(
    "room",
    "realm",
    "empty",
    "sqlite",
)

android {
    namespace = "com.thever4.dbcomparison"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thever4.dbcomparison"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions += listOf("database")

    productFlavors {
        flavorList.forEach {
            create(it) {
                dimension = "database"
                applicationIdSuffix = ".$it"
            }
        }
    }

    sourceSets {
        flavorList.forEach {
            named(it) {
                kotlin.srcDir("src/$it/java")
                res.srcDir("src/$it/res")
            }
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //vbpd
    implementation("com.github.kirich1409:viewbindingpropertydelegate-full:1.5.9")

    //dagger2
    implementation("com.google.dagger:dagger:2.47")
    kapt("com.google.dagger:dagger-compiler:2.47")

    implementation("io.github.serpro69:kotlin-faker:1.6.0")

    implementation(kotlin("reflect"))

    // Room
    val roomVersion = "2.5.0"

    add("roomImplementation", "androidx.room:room-runtime:$roomVersion")
    add("roomImplementation", "androidx.room:room-ktx:$roomVersion")

    gradle.startParameter.taskNames.any { it.lowercase().contains("room") }.ifTrue {
        add("ksp", "androidx.room:room-compiler:$roomVersion")
    }

    // Realm
    val realmVersion = "1.11.0"
    add("realmImplementation", "io.realm.kotlin:library-base:$realmVersion")
}
