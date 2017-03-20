package es.osw.cafelatte.domain.model;

import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

@AutoValue public abstract class LoginUser {
  public static Builder newBuilder() {
    return new AutoValue_LoginUser.Builder();
  }

  public abstract String getToken();

  @Nullable abstract String getSecret();

  public abstract String getProvider();

  public abstract String getEmail();

  @AutoValue.Builder public static abstract class Builder {
    public abstract Builder setToken(String val);

    public abstract Builder setSecret(String val);

    public abstract Builder setProvider(String val);

    public abstract Builder setEmail(String val);

    public abstract LoginUser build();
  }
}
