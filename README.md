# AuthJsb - Módulo de autenticación
Este módulo permite optimizar el proceso de configuración de la librería de Auth0. Antes de iniciar es importante que se cree primero el proyecto en el dashboard de Auth0.
## Agregar dependencias
primero se añaden las dependencias de auth0 y authjsb en el build.gradle.kts(Module: app) de tu proyecto en android studio
```
implementation("com.github.jsbernalp:authjsb:1.0.20")
implementation("com.auth0.android:auth0:2.9.2")
implementation("com.auth0.android:lock:3.2.2")
```
Adicionalmente,
```
android {
   ...
    packaging {
        resources.excludes.add("META-INF/auth0_release.kotlin_module")
    }

    defaultConfig {
        ...
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["auth0Domain"] = "@string/com_auth0_domain"
        manifestPlaceholders["auth0Scheme"] = "app"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

```
Luego en los recursos string de tu proyecto.
```
<string name="com_auth0_domain" translatable="false">domain</string>
<string name="com_auth0_client_id" translatable="false">clientId</string>
```
> [!NOTE]
> Debes reemplazar los valores con los datos de tu proyecto en Auth0.
