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
**Authjsb** inicializa automáticamente todas las instancias necesarias para usar Auth0, sin embargo es necesario conocer el **domain** y el **clientId** de cada proyecto, para eso se debe crear una clase con las siguientes líneas de código:
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

## Configuración Dagger Hilt
> [!IMPORTANT]
> Para la inyección de dependencias **Authjsb** usa Dagger Hilt, por lo que es importante que tu proyecto también use esta librería.

En primera instancia, en el **build.gradle.kts(Project)**, se debe añadir lo siguiente:
```
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.android.library") version "8.1.2" apply false
}
```

Luego en **build.gradle.kts(Module: app)** las dependencias de Dagger Hilt son las siguientes:
```
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

implementation("com.google.dagger:hilt-android:2.48")
ksp("com.google.dagger:hilt-compiler:2.48")
kapt("androidx.hilt:hilt-compiler:1.1.0")
```

## Configuración en el manifest
En este caso, usamos Lock una libreria de Auth0 que permite heredar el login de forma nativa, para esto es necesario agregar un activity en el manifest, de la siguiente manera: 
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
       ...
      <activity android:name="com.auth0.android.lock.LockActivity"
            android:label="@string/app_name"
            android:launchMode="singleTask"
            android:screenOrientation="portrait"
            android:theme="@style/LockTheme"
            tools:replace="android:theme"/>
    </application>
</manifest>
```
adicionalmente en los recursos (themes.xml) anadir los siguiente:
```
<style name="LockTheme" parent="Lock.Theme">
        <item name="Auth0.HeaderLogo">@mipmap/ic_launcher_round</item>
        <item name="Auth0.HeaderTitle">@string/app_name</item>
        <item name="Auth0.HeaderTitleColor">@color/black</item>
        <item name="Auth0.HeaderBackground">@color/white</item>
        <item name="Auth0.PrimaryColor">@color/black</item>
        <item name="Auth0.DarkPrimaryColor">@color/white</item>
    </style>
```
> [!NOTE]
> Puedes personalizar los colores para que esten acordes con los de tu proyecto

