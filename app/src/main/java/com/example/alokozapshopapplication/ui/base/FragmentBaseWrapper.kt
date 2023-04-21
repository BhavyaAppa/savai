package com.example.alokozapshopapplication.ui.base

import androidx.fragment.app.Fragment
import com.example.alokozapshopapplication.ui.network.model.HttpErrorCode

/**
 * Created by rajanbhavsar on 26/11/19, 1:52 PM
 * Package Name : com.appname.structure.base
 * Project Name : BrainvireStructure
 */
abstract class FragmentBaseWrapper : Fragment() {
    abstract fun somethingWentWrong()

    abstract fun noInternet()

    abstract fun onRetryClicked(retryButtonType: String)

    abstract fun dataNotFound(message: String?, messageCode: String?)

    abstract fun verifyUser(message: String)

    abstract fun newVersionFound()

    abstract fun unAuthorizationUser(message: String?, messageCode: String?)

    open fun httpFailedHandler(code: Int, message: String?, messageCode: String?) {
        handleException(code, message, messageCode)
    }

    private fun handleException(code: Int, message: String?, messageCode: String?) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, messageCode)
            HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, messageCode)
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            else -> somethingWentWrong()
        }
    }
}