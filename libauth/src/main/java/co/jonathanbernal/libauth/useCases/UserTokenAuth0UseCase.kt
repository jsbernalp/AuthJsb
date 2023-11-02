package co.jonathanbernal.libauth.useCases

import android.content.Context
import co.jonathanbernal.libauth.R
import co.jonathanbernal.libauth.manager.AuthManager
import co.jonathanbernal.libauth.manager.IAuthUseCase
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.result.Credentials
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import com.auth0.android.callback.Callback

class UserTokenAuth0UseCase(
    private val auth0Manager: CredentialsManager,
    @ApplicationContext private val context: Context
) : IAuthUseCase {

    private val scope: String = context.getString(R.string.auth0_scope)

    override fun getUserTokenAuth0(): Single<String> {
        val userToken = BehaviorSubject.create<String>()
        if (auth0Manager.hasValidCredentials()) {
            auth0Manager.getCredentials(
                scope = scope,
                minTtl = 0,
                object : Callback<Credentials, CredentialsManagerException> {
                    override fun onFailure(error: CredentialsManagerException) {
                        userToken.onError(error)
                    }

                    override fun onSuccess(result: Credentials) {
                        userToken.onNext(result.idToken)
                    }

                }
            )
        }
        return userToken.firstOrError()
    }

    override fun hasValidCredentials(): Boolean {
        return auth0Manager.hasValidCredentials()
    }

}