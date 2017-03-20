package es.osw.cafelatte.domain.prefs;

import io.reactivex.Maybe;

public interface UserPreferences {
  Maybe<String> getUserName();
}
