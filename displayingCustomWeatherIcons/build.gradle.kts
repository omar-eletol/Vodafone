plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")

}

android {
    namespace = "com.task.displayingcustomweathericons"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v121)
    androidTestImplementation(libs.androidx.espresso.core)
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("Release") {
//                groupId = "com.task"
//                artifactId = "displayingcustomweathericons"
//                version = "1.0.0"
//                artifact("$buildDir/outputs/aar/displayingcustomweathericons-release.aar")
//
//                pom.withXml {
//                    val dependenciesNode = asNode().appendNode("dependencies")
//                    configurations["implementation"].allDependencies.forEach {
//                        val dependencyNode = dependenciesNode.appendNode("dependency")
//                        dependencyNode.appendNode("groupId", it.group)
//                        dependencyNode.appendNode("artifactId", it.name)
//                        dependencyNode.appendNode("version", it.version)
//                    }
//                }
//            }
//        }
//        repositories {
//            maven {
//                name = "GithubPackages"
//                url = uri("https://maven.pkg.github.com/omar-eletol/Vodafone")
//                credentials {
//                    username = "omar-eletol"
//                }
//            }
//        }
//    }
//}

