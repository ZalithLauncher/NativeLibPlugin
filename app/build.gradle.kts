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

    defaultConfig {
        applicationId = "com.commonlauncher.nativeplugin"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            //应用名
            //app name
            resValue("string","app_name","imgui-java Plugin")
            //包名后缀
            //package name Suffix
            applicationIdSuffix = ".imgui_java"

            //插件包在启动器内显示的名称
            //Plugin package display name within the launcher
            manifestPlaceholders["des"] = "imgui-java v1.86.12 Patched"

            //JVM环境参数配置
            //JVM environment parameter configuration
            manifestPlaceholders["environment"] = mutableMapOf<String,String>().apply {
                put("imgui.library.path", "{nativeLibraryDir}")
                put("imgui.library.name", "libimgui-java.so")
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
}