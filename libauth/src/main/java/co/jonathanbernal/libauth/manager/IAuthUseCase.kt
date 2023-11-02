package co.jonathanbernal.libauth.manager

import io.reactivex.Single

interface IAuthUseCase {
    fun getUserTokenAuth0(): Single<String>
    fun hasValidCredentials(): Boolean
}