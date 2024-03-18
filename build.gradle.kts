plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.github.soupedog:hygge-util-json-jackson:0.0.13")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.5")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.5")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.5")

    testImplementation("io.github.soupedog:hygge-util-json-jackson:0.0.13")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.5")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.5")
    testImplementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.5")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2.5")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
