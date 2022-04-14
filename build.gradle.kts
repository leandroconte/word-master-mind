val ktor_version: String by project
val kotlin_version: String by project
val kodein_version: String by project
val logback_version: String by project
val exposed_version: String by project
val hikari_version: String by project
val postgres_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "br.com.leandro"
version = "0.0.1"

application {
    mainClass.set("br.com.leandro.mastermind.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
//    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.kodein.di:kodein-di:$kodein_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}