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

## Inicializar instancias de Auth0
**Authjsb** inicializa automaticamente todas las instancias necesarias para usar Auth0, sin embargo es necesario conocer el **domain** y el **clientId** de cada proyecto, para eso se debe crear una clase con las siguientes lineas de codigo:
```
import android.content.Context
import co.jonathanbernal.libauth.di.ClientIDAuth0
import co.jonathanbernal.libauth.di.DomainAuth0
import co.jonathanbernal.libauth.manager.IAuthUseCase
import co.jonathanbernal.libauth.useCases.UserTokenAuth0UseCase
import co.jonathanbernal.newauthjsbapplication.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Auth0Module {

    @Singleton
    @Provides
    fun bindAuth0UseCase(userTokenAuth0UseCase: UserTokenAuth0UseCase): IAuthUseCase{
        return userTokenAuth0UseCase
    }

    @Provides
    @DomainAuth0
    fun domainAuth0(@ApplicationContext context: Context) = context.getString(R.string.com_auth0_domain)

    @Provides
    @ClientIDAuth0
    fun clientIDAuth0(@ApplicationContext context: Context) = context.getString(R.string.com_auth0_client_id)
}
```
> [!IMPORTANT]
> Para la inyeccion de dependencias la **Authjsb** usar Dagger Hilt, por lo que es importante que tu proyecto tambien use esta libreria.

las dependencias de Dagger Hilt son las siguientes:
```
implementation("com.google.dagger:hilt-android:2.48")
ksp("com.google.dagger:hilt-compiler:2.48")
kapt("androidx.hilt:hilt-compiler:1.1.0")
```

