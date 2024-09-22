plugins {
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val cucumberVersion: String by project
val restAssuredVersion: String by project
val assertjVersion: String by project
val allureVersion: String by project
val selenideVersion: String by project
val junitVersion: String by project

dependencies {
    // Selenide for browser automation
    implementation("com.codeborne:selenide:$selenideVersion")
    // AssertJ for fluent assertions
    implementation("org.assertj:assertj-core:$assertjVersion")

    // Cucumber dependencies
    implementation("io.cucumber:cucumber-java:$cucumberVersion")
    implementation("io.cucumber:cucumber-junit:$cucumberVersion")
    implementation("io.cucumber:cucumber-picocontainer:$cucumberVersion")
    implementation("io.cucumber:cucumber-junit:$cucumberVersion")
    implementation("io.qameta.allure:allure-cucumber6-jvm:$allureVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11 // Adjust as needed
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}



val argsForCucumber = listOf(
        "--plugin", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
        "--glue", "com.teladoc.definitions",
        "--threads", System.getProperty("threads", "1"),
        "--tags", System.getProperty("tags", "@regression")
)

tasks.register("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            systemProperties["cucumber.options"] = "--plugin pretty --glue com.teladoc.definitions src/test/resources/features"
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = configurations["cucumberRuntime"] + sourceSets["main"].output + sourceSets["test"].output
            args = argsForCucumber.toMutableList().apply {
                add("--plugin")
                add("rerun:rerun.txt")

            }
        }
    }
}
configurations {
    create("cucumberRuntime") {
        extendsFrom(configurations["testImplementation"])
    }
}
tasks.named("build") {
    dependsOn("cucumber")
}

tasks.register("regression"){
    dependsOn("cucumber")
}