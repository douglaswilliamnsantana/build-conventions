import org.gradle.api.provider.Property

abstract class MavenPublishingExtension {
    abstract val groupId: Property<String>
    abstract val artifactId: Property<String>
    abstract val version: Property<String>
    abstract val name: Property<String>
    abstract val description: Property<String>
    abstract val inceptionYear: Property<String>
    abstract val url: Property<String>
    abstract val developerId: Property<String>
    abstract val developerName: Property<String>
    abstract val developerEmail: Property<String>
    abstract val developerUrl: Property<String>
    abstract val licenseUrl: Property<String>
    abstract val scmUrl: Property<String>
}
