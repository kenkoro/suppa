import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config.setFrom(rootProject.file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

val detekt = tasks.register("detektWithoutTests") {
    val paths = tasks.withType<Detekt>().matching {
        it.name.lowercase().contains("test").not()
    }
    dependsOn(paths)
}

tasks.matching { it.name == "check" }.configureEach {
    dependsOn(detekt)
}

tasks.withType<Detekt>().configureEach {
    mustRunAfter(tasks.matching { it.name.lowercase().contains("generate") })
    exclude { it.file.path.contains("build/") }
}

dependencies {
    detektPlugins(libs.safeLibrary("detekt-formatting"))
}