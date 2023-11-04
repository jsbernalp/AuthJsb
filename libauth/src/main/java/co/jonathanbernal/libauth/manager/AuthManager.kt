package co.jonathanbernal.libauth.manager

import android.content.Context
import co.jonathanbernal.libauth.R
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.lock.AuthenticationCallback
import com.auth0.android.lock.Lock
import com.auth0.android.lock.utils.CustomField
import com.auth0.android.lock.utils.HiddenField
import com.auth0.android.result.Credentials
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

const val LANGUAGE_CUSTOM_FIELD = "language"

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val account: Auth0,
    private val auth0Manager: CredentialsManager
) : HandlerAuthManager {

    private val scheme: String = context.getString(R.string.auth0_scheme)
    private val scope: String = context.getString(R.string.auth0_scope)
    private lateinit var lock: Lock

    override fun login(
        context: Context,
        onSuccess: (result: String) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val callback = object : AuthenticationCallback() {
            override fun onError(error: AuthenticationException) {
                onError.invoke(error.getDescription())
            }

            override fun onAuthentication(credentials: Credentials) {
                val token = credentials.idToken
                auth0Manager.saveCredentials(credentials)
                onSuccess.invoke(token)
            }

        }

        val currentLanguage = Locale.getDefault().language
        val androidField =
            HiddenField(LANGUAGE_CUSTOM_FIELD, currentLanguage, CustomField.Storage.USER_METADATA)
        val customField = listOf(androidField)
        lock = Lock.newBuilder(account, callback)
            .closable(true)
            .withScheme(scheme)
            .withScope(scope)
            .setShowTerms(false)
            .withSignUpFields(customField)
            .build(context)
        context.startActivity(lock.newIntent(context))
    }

    override fun logOut() {
        auth0Manager.clearCredentials()
    }

    override fun destroyLock(context: Context) {
        if (::lock.isInitialized) {
            lock.onDestroy(context)
        }
    }

}