package es.osw.cafelatte.domain.analytics;

import java.util.Map;
import javax.annotation.Nullable;

public interface EventMessenger {
  void send(String eventName, @Nullable Map<String, String> properties);

  void register(String email);

  void login(String email);

  void transaction(Transaction transaction);

  void stopScreen(Object payload);

  void startScreen(Object payload);

  abstract class Message {
    public abstract void send();
  }
}
