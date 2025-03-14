import com.android.build.api.dsl.LibraryExtension
import com.madslangkilde.convention.configureAndroidCompose
import com.madslangkilde.convention.implementation
import com.madslangkilde.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidPresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("moviemate.android.library")
            }

            extensions.configure<LibraryExtension> {
                val extension = extensions.getByType<LibraryExtension>()
                configureAndroidCompose(extension)
            }

            dependencies {
                implementation(libs.findBundle("presentation").get())
                implementation(libs.findBundle("di").get())
                implementation(project(":presentation:ui_base"))
                implementation(project(":presentation:navigation"))
            }
        }
    }
}