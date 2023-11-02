package co.jonathanbernal.libauth.di

import android.content.Context
import co.jonathanbernal.libauth.useCases.UserTokenAuth0UseCase
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun getAuth0(@DomainAuth0 domainAuth0: String, @ClientIDAuth0 clientIdAuth0: String): Auth0 =
        Auth0(domainAuth0, clientIdAuth0)

    @Provides
    @Singleton
    fun getAuthenticationAPIClient(auth0: Auth0): AuthenticationAPIClient =
        AuthenticationAPIClient(auth0)

    @Provides
    @Singleton
    fun getCredentialsManager(
        @ApplicationContext context: Context,
        apiClient: AuthenticationAPIClient
    ): CredentialsManager =
        CredentialsManager(apiClient, SharedPreferencesStorage(context))

    @Provides
    @Singleton
    fun getUserTokenAuth0UseCase(
        credentialsManager: CredentialsManager,
        @ApplicationContext context: Context
    ): UserTokenAuth0UseCase = UserTokenAuth0UseCase(credentialsManager, context)
}