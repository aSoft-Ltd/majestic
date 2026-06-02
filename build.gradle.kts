import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(androidx.plugins.library) apply false
    alias(kotlinz.plugins.multiplatform) apply false
    alias(kotlinz.plugins.serialization) apply false
    alias(asoft.plugins.library) apply false
    alias(kotlinz.plugins.compose) apply false
    alias(kotlinz.plugins.root.compiler.compose) apply false
    alias(vanniktech.plugins.maven.publish) apply false
    alias(kotlinz.plugins.dokka)
}

val v = libs.versions.asoft.get()

group = "tz.co.asoft"
version = v

repositories {
    publicRepos()
}

allprojects {
    group = "tz.co.asoft"
    version = v
    val p = this

    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    dokka {
        moduleName.set(p.name)
        dokkaSourceSets.configureEach {
            includes.from("Module.md", "README.md")
        }
        dokkaPublications.html {
            suppressInheritedMembers.set(true)
            failOnWarning.set(true)
            includes.from("Module.md", "README.md")
        }

        pluginsConfiguration.html {
            footerMessage.set("Copyright ⓒ aSoft Limited")
        }
    }
}

subprojects {
    val p = this
    version = v

    configure<MavenPublishBaseExtension> {
        publishToMavenCentral(SonatypeHost.DEFAULT, automaticRelease = true)

        signAllPublications()

        coordinates("tz.co.asoft", p.name, v)

        pom {
            name.set(p.name)
            description.set(p.description)
            inceptionYear.set("2019")
            url.set("https://github.com/aSoft-Ltd/majestic")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://github.com/aSoft-Ltd/majestic/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("andylamax")
                    name.set("Anderson Lameck")
                    url.set("https://github.com/andylamax/")
                }
            }
            scm {
                url.set("https://github.com/aSoft-Ltd/majestic/")
                connection.set("scm:git:git://github.com/aSoft-Ltd/majestic.git")
                developerConnection.set("scm:git:ssh://git@github.com/aSoft-Ltd/majestic.git")
            }
        }
    }
}

group = "tz.co.asoft"
version = libs.versions.asoft.get()

dependencies {
    dokka(projects.majesticDrawers)
}

val dokkaGeneratePublicationHtml by tasks.getting
val docs by tasks.creating(Copy::class.java) {
    dependsOn(dokkaGeneratePublicationHtml)
    from(layout.buildDirectory.dir("dokka/html"))
    into("docs")
}