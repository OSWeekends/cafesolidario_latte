package es.osw.cafelatte.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class User {
  public static Builder newBuilder() {
    return new AutoValue_User.Builder();
  }

  public abstract String getName();

  public abstract String getEmail();

  public abstract String getImage();

  @AutoValue.Builder public static abstract class Builder {

    public abstract Builder setName(String val);

    public abstract Builder setEmail(String val);

    public abstract Builder setImage(String val);

    public abstract User build();
  }
}
