package co.jonathanbernal.authjsb.di

import android.content.Context
import co.jonathanbernal.authjsb.R
import co.jonathanbernal.libauth.di.ClientIDAuth0
import co.jonathanbernal.libauth.di.DomainAuth0
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Auth0Module {

    @Provides
    @DomainAuth0
    fun domainAuth0(@ApplicationContext context: Context) = context.getString(R.string.com_auth0_domain)

    @Provides
    @ClientIDAuth0
    fun clientIDAuth0(@ApplicationContext context: Context) = context.getString(R.string.com_auth0_clientId)
}