package co.jonathanbernal.authjsb.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import co.jonathanbernal.libauth.manager.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {

    fun goToLogin(context: Context) {
        authManager.login(context, {}, {})
    }

}