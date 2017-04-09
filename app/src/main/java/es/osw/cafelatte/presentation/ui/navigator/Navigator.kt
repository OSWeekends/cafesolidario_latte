package es.osw.cafelatte.presentation.ui.navigator

import android.app.Activity
import android.content.Intent
import es.osw.cafelatte.domain.model.PaymentMethod
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.creditcard.AddCreditCardActivity
import es.osw.cafelatte.presentation.ui.login.LoginActivity
import es.osw.cafelatte.presentation.ui.main.HomeActivity
import es.osw.cafelatte.presentation.ui.profile.ProfileActivity
import es.osw.cafelatte.presentation.ui.splash.SplashActivity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import rx_activity_result2.RxActivityResult
import javax.inject.Inject

@PerActivity class Navigator @Inject constructor(val activity: BaseActivity) {

    fun startHomeScreen(): Completable {
        return Completable.fromAction { activity.startActivity(HomeActivity.getCallingIntent(activity)) }
    }

    fun startLoginScreen(): Single<Boolean> {
        return RxActivityResult.on(activity)
                .startIntent(LoginActivity.getCallingIntent(activity))
                .flatMapSingle({ result -> Single.just(result.resultCode() == Activity.RESULT_OK) })
                .singleOrError()
    }

    fun finishScreen(): Completable {
        return Completable.fromAction { activity.finish() }
    }

    fun startAddPaymentMethodScreen(): Maybe<PaymentMethod> {
        return RxActivityResult.on(activity)
                .startIntent(AddCreditCardActivity.getCallingIntent(activity))
                .filter({ result -> result.resultCode() == Activity.RESULT_OK })
                .map({ result -> AddCreditCardActivity.getCreditCard(result.data()) })
                .firstElement()
    }

    fun startProfileActivity(): Completable = Completable.fromAction {
        activity.startActivity(ProfileActivity
                .getCallingIntent(activity))
    }

    fun startSplashScreen(): Completable = Completable.fromAction {
        val intent = SplashActivity.getCallingIntent(activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }
}
