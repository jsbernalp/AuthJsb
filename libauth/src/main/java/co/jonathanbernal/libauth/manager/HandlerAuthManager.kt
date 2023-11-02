package co.jonathanbernal.libauth.manager

import android.content.Context

interface HandlerAuthManager {

    fun login(
        context: Context,
        onSuccess: (result: String) -> Unit = {},
        onError: (error: String) -> Unit = {}
    )

    fun logOut()

    fun destroyLock(context: Context)
}