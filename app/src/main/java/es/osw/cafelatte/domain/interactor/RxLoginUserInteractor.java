package es.osw.cafelatte.domain.interactor;

import es.osw.cafelatte.domain.model.LoginUser;
import es.osw.cafelatte.domain.model.User;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import io.reactivex.Observable;
import javax.inject.Inject;
import timber.log.Timber;

@PerActivity public class RxLoginUserInteractor {
  @Inject public RxLoginUserInteractor() {
  }

  public Observable<User> createObservable(LoginUser param) {
    return Observable.just(createFakeUser())
        .doOnSubscribe(disposable -> Timber.d("Singing in %1$s in our servers", param.getEmail()));
  }

  private User createFakeUser() {
    return User.newBuilder().setEmail("email").setImage("image").setName("name").build();
  }
}
