import com.android.build.api.dsl.ApplicationExtension
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

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.configureTargetSdk()
                val extension = extensions.getByType<ApplicationExtension>()
                configureAndroidCompose(extension)
            }

            dependencies {
                implementation(libs.findBundle("application").get())
                implementation(libs.findBundle("presentation").get())
                implementation(project(":presentation:ui_base"))
                implementation(project(":presentation:navigation"))
            }
        }
    }
}