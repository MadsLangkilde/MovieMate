import com.android.build.api.dsl.LibraryExtension
import com.madslangkilde.convention.configureAndroidCompose
import com.madslangkilde.convention.configureKotlinAndroid
import com.madslangkilde.convention.configureTargetSdk
import com.madslangkilde.convention.implementation
import com.madslangkilde.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryUiBaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<com.android.build.gradle.LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.configureTargetSdk()
                val extension = extensions.getByType<LibraryExtension>()
                configureAndroidCompose(extension)
            }
            dependencies {
                implementation(libs.findBundle("presentation").get())
            }
        }
    }
}
