package es.osw.cafelatte.presentation.model.mapper;

import com.firebase.ui.auth.IdpResponse;
import es.osw.cafelatte.domain.model.LoginUser;

public class LoginUserMapper {
  public static LoginUser map(IdpResponse idpResponse) {
    return LoginUser.newBuilder()
        .setEmail(idpResponse.getEmail())
        .setToken(idpResponse.getIdpToken())
        .setProvider(idpResponse.getProviderType())
        .setSecret(idpResponse.getIdpSecret())
        .build();
  }
}
