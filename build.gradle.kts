import org.gradle.api.JavaVersion.VERSION_17
import org.owasp.dependencycheck.reporting.ReportGenerator.Format

plugins {
    java
    jacoco
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.google.cloud.tools.jib") version "3.1.4"
    id("org.owasp.dependencycheck") version "6.5.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jib {
    to {
        image = "ghcr.io/partschi/${project.name}"
        tags = setOf("${project.version}", "latest")
        from {
            image = "openjdk:17"
        }
        auth {
            username = System.getenv("GITHUB_ACTOR") ?: ""
            password = System.getenv("GITHUB_TOKEN") ?: ""
        }
    }
}

dependencyCheck {
    format = Format.ALL
    junitFailOnCVSS = 6.0f
    failBuildOnCVSS = 6.0f
}

tasks.withType(Checkstyle::class) {
    ignoreFailures = false
    maxWarnings = 0
    reports {
        html.outputLocation.set(rootProject.file("build/reports/checkstyle.html"))
    }
}