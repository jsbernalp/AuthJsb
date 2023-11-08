# AuthJsb - Módulo de autenticación
Este modulo permite optimizar el proceso de configuracion de la libreria de Auth0. Antes de iniciar es importante que se cree primero el proyecto en el dashboard de Auth0.
## Agregar depencencias
primero se anaden las dependencias de auth0 y authjsb en el build.gradle.kts(Module: app) de tu proyecto en android studio
```
implementation("com.github.jsbernalp:authjsb:1.0.20")
implementation("com.auth0.android:auth0:2.9.2")
implementation("com.auth0.android:lock:3.2.2")
```
