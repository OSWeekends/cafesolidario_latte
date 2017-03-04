package es.osw.cafelatte.domain.analytics;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum EventsTracker {
  INSTANCE;

  private final List<EventMessenger> messengers = new ArrayList<>();

  public void send(String eventName, Map<String, String> properties) {
    Completable.create(subscriber -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.send(eventName, properties);
      }
      subscriber.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public void send(String eventName) {
    send(eventName, null);
  }

  public EventsTracker add(EventMessenger eventMessenger) {
    messengers.add(eventMessenger);
    return this;
  }

  public void registerUser(final String email) {
    Completable.create(subscriber -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.register(email);
      }
      subscriber.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public void loginUser(final String email) {
    Completable.create(subscriber -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.login(email);
      }
      subscriber.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public void transaction(final Transaction transaction) {
    Completable.create(emitter -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.transaction(transaction);
      }

      emitter.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public void startScreen(Object payload) {
    Completable.create(emitter -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.startScreen(payload);
      }

      emitter.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public void stopScreen(Object payload) {
    Completable.create(emitter -> {
      for (EventMessenger eventMessenger : messengers) {
        eventMessenger.stopScreen(payload);
      }

      emitter.onComplete();
    }).subscribeOn(Schedulers.io()).subscribe(() -> {
    }, error -> {
    });
  }

  public static class Events {

  }
}
