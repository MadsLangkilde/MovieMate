import com.android.build.gradle.LibraryExtension
import com.madslangkilde.convention.configureKotlinAndroid
import com.madslangkilde.convention.configureTargetSdk
import com.madslangkilde.convention.implementation
import com.madslangkilde.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidDataServerRepositoryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("moviemate.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions.configureTargetSdk()
            }

            dependencies {
                implementation(libs.findBundle("di").get())
                implementation(libs.findBundle("serverrepository").get())
            }
        }
    }
}