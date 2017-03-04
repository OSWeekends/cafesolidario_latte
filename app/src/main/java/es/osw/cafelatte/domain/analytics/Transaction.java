package es.osw.cafelatte.domain.analytics;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Transaction {

  public abstract String getOrderId();

  public abstract String getCustomerId();

  public abstract float getTotal();

  public abstract String getCoupon();

  public abstract String getProductId();

  public abstract String getCurrencyCode();

  @AutoValue.Builder public static abstract class Builder {
    public abstract Builder setOrderId(String val);

    public abstract Builder setCustomerId(String val);

    public abstract Builder setTotal(float val);

    public abstract Builder setCoupon(String val);

    public abstract Builder setProductId(String val);

    public abstract Builder setCurrencyCode(String val);

    public abstract Transaction build();
  }
}
