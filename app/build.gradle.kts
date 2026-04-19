plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.commonlauncher.nativeplugin"
    compileSdk = 34

    signingConfigs {
        create("releaseBuild") {
            storeFile = file("common_plugin.jks")
            storePassword = "common_plugin"
            keyAlias = "common_plugin"
            keyPassword = "common_plugin"
        }
    }

    val versionString = "0.1.0 NDK BUILD"

    defaultConfig {
        applicationId = "com.commonlauncher.nativeplugin"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        this.versionName = versionString
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            //应用名
            //app name
            resValue("string","app_name","Sable Rapier Plugin")
            //包名后缀
            //package name Suffix
            applicationIdSuffix = ".sable_rapier"

            //插件包在启动器内显示的名称
            //Plugin package display name within the launcher
            manifestPlaceholders["des"] = "sable_rapier $versionString"

            //JVM环境参数配置
            //JVM environment parameter configuration
            manifestPlaceholders["environment"] = mutableMapOf<String,String>().apply {
                put("sable_rapier_path", "{nativeLibraryDir}libsable_rapier.so")
            }.run {
                buildList {
                    this@run.forEach { (key, value) ->
                        add("-D$key=$value")
                    }
                }.joinToString(" ")
            }
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
}