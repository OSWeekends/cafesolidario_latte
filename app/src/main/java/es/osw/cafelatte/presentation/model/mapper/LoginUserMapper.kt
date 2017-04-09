package es.osw.cafelatte.presentation.model.mapper

import com.firebase.ui.auth.IdpResponse
import es.osw.cafelatte.domain.model.LoginUser

class LoginUserMapper {
    companion object {
        fun map(idpResponse: IdpResponse): LoginUser {
            return LoginUser(
                    email = idpResponse.email,
                    token = idpResponse.idpToken,
                    provider = idpResponse.providerType,
                    secret = idpResponse.idpSecret)
        }
    }

}
