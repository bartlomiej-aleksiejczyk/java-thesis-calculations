plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.apache.commons:commons-csv:1.10.0")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest.attributes["Main-Class"] = "org.example.Main"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}