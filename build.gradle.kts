import org.gradle.api.JavaVersion.VERSION_17
import org.owasp.dependencycheck.reporting.ReportGenerator.Format

plugins {
    java
    jacoco
    checkstyle
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
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.sentry:sentry-spring-boot-starter:5.4.0")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    modules {
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2",
                "Use Log4j2 instead of Logback")
        }
    }

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