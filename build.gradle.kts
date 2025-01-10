plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("jacoco")
}

group = "wa.umiushi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
	reports {
		junitXml.required.set(true)
		html.required.set(true)
	}

	finalizedBy(tasks.jacocoTestReport)
}

tasks.register<Test>("unitTest") {
	group = "verification"
	description = "This is Unit Test Task"

	useJUnitPlatform {
		includeTags("unit")
	}
}

tasks.register<Test>("integrationTest") {
	group = "verification"
	description = "This is integration Test Task"

	useJUnitPlatform {
		includeTags("integration")
	}
}

tasks.named<JacocoReport>("jacocoTestReport") {
	dependsOn(tasks.test)
}