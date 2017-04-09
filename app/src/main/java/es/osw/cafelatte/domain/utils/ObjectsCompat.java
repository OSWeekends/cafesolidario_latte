package es.osw.cafelatte.domain.utils;

import android.os.Build;
import java.util.Objects;

@SuppressWarnings("PMD.SuspiciousEqualsMethodName") public final class ObjectsCompat {

  private ObjectsCompat() {

  }

  public static boolean notEquals(Object a, Object b) {
    return !equals(a, b);
  }

  public static boolean equals(Object a, Object b) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      return a == null ? b == null : a.equals(b);
    }
    return Objects.equals(a, b);
  }
}
