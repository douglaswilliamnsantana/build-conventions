import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class MavenPublishingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.vanniktech.maven.publish")

            val ext = extensions.create<MavenPublishingExtension>("publishingConfig")

            afterEvaluate {
                extensions.configure<MavenPublishBaseExtension> {
                    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
                    signAllPublications()

                    coordinates(
                        groupId = ext.groupId.get(),
                        artifactId = ext.artifactId.get(),
                        version = ext.version.get()
                    )

                    pom {
                        if (ext.name.isPresent) name.set(ext.name.get())
                        if (ext.description.isPresent) description.set(ext.description.get())
                        if (ext.inceptionYear.isPresent) inceptionYear.set(ext.inceptionYear.get())
                        if (ext.url.isPresent) url.set(ext.url.get())

                        licenses {
                            license {
                                val licenseLink = ext.licenseUrl
                                    .getOrElse("https://www.apache.org/licenses/LICENSE-2.0.txt")
                                name.set("The Apache License, Version 2.0")
                                url.set(licenseLink)
                                distribution.set(licenseLink)
                            }
                        }

                        if (ext.developerId.isPresent) {
                            developers {
                                developer {
                                    id.set(ext.developerId.get())
                                    if (ext.developerName.isPresent) name.set(ext.developerName.get())
                                    if (ext.developerEmail.isPresent) email.set(ext.developerEmail.get())
                                    if (ext.developerUrl.isPresent) url.set(ext.developerUrl.get())
                                }
                            }
                        }

                        if (ext.scmUrl.isPresent) {
                            val scmLink = ext.scmUrl.get()
                            val path = scmLink.removePrefix("https://")
                            scm {
                                url.set(scmLink)
                                connection.set("scm:git:git://$path.git")
                                developerConnection.set("scm:git:ssh://git@$path.git")
                            }
                        }
                    }
                }
            }
        }
    }
}
