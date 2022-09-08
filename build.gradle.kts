import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.github.attacktive"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

val springBootGroupName = "org.springframework.boot"
val kotlinGroupName = "org.jetbrains.kotlin"
val webjarsGroupName = "org.webjars"

repositories {
	mavenCentral()
}

dependencies {
	implementation(springBootGroupName, "spring-boot-starter")
	implementation(springBootGroupName, "spring-boot-starter-web")
	implementation(springBootGroupName, "spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")
	implementation(kotlinGroupName, "kotlin-reflect")
	implementation(kotlinGroupName, "kotlin-stdlib-jdk8")
	implementation(webjarsGroupName, "webjars-locator-core")
	implementation(webjarsGroupName, "sockjs-client", "1.0.2")
	implementation(webjarsGroupName, "stomp-websocket", "2.3.3")
	implementation(webjarsGroupName, "bootstrap", "3.3.7")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
