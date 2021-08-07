import org.gradle.api.JavaVersion

ext.androidLibraryModule = { isComposeEnabled ->
    android {
        compileSdk 30

        defaultConfig {
            minSdk 21
            targetSdk 30
            versionCode 1
            versionName "1.0"

            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles "consumer-rules.pro"
        }

        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
        }
        if (isComposeEnabled) {
            buildFeatures {
                compose true
            }
            composeOptions {
                kotlinCompilerExtensionVersion compose_version
                kotlinCompilerVersion '1.5.10'
            }
        }
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = '1.8'
        }
    }
}

