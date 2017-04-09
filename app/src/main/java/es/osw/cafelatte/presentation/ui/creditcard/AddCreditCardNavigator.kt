package es.osw.cafelatte.presentation.ui.creditcard

import android.app.Activity
import android.content.Intent
import es.osw.cafelatte.domain.model.PaymentMethod
import es.osw.cafelatte.presentation.ui.BaseActivity
import javax.inject.Inject

class AddCreditCardNavigator @Inject constructor(val activity: BaseActivity) {
    fun deliverResult(paymentCard: PaymentMethod) {
        val intent = Intent()
        intent.putExtra(AddCreditCardActivity.EXTRA_CREDIT_CARD, paymentCard)
        activity.setResult(Activity.RESULT_OK, intent)
        activity.finish()
    }
}
