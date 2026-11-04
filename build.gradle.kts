plugins {
    java
    id("org.springframework.boot") version "4.1.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.praktikum"
version = "0.0.1-SNAPSHOT"
description = "miniWms"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(27)
    }
}

repositories {
    mavenCentral()
}


tasks.register<Exec>("startPostgresContainer") {
    group = "docker"
    description = "Starts a PostgreSQL Docker container with preset credentials."

    val containerName = "postgres_local"
    val dbUser = "test"
    val dbPassword = "test"
    val dbName = "mydatabase"
    val hostPort = "5432"
    val containerPort = "5432"

    executable = "docker"
    args = listOf(
        "run", "-d",
        "--name", containerName,
        "-e", "POSTGRES_USER=$dbUser",
        "-e", "POSTGRES_PASSWORD=$dbPassword",
        "-e", "POSTGRES_DB=$dbName",
        "-p", "$hostPort:$containerPort",
        "postgres:latest"
    )

    doLast {
        println("PostgreSQL container '$containerName' started successfully!")
        println("Connection URL: jdbc:postgresql://localhost:$hostPort/$dbName")
        println("Username: $dbUser")
        println("Password: $dbPassword")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
