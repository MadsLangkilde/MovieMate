import com.android.build.gradle.LibraryExtension
import com.madslangkilde.convention.configureKotlinAndroid
import com.madslangkilde.convention.configureTargetSdk
import com.madslangkilde.convention.implementation
import com.madslangkilde.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions.configureTargetSdk()
            }
            dependencies {
                implementation(libs.findBundle("di").get())
            }
        }
    }
}