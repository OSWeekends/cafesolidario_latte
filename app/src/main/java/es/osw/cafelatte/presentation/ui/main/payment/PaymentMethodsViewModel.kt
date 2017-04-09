package es.osw.cafelatte.presentation.ui.main.payment

import android.databinding.ObservableField
import es.osw.cafelatte.domain.model.PaymentMethod
import es.osw.cafelatte.presentation.ui.navigator.Navigator
import timber.log.Timber
import javax.inject.Inject

class PaymentMethodsViewModel @Inject constructor(val navigator: Navigator) {
    var paymentMethod: ObservableField<PaymentMethod> = ObservableField()

    fun clickAddPaymentMethod() {
        Timber.d("Starting add payment method flow")
        navigator.startAddPaymentMethodScreen().subscribe({
            Timber.d("The user has added a credit card")
            Timber.i("\t$it")
            paymentMethod.set(it)
        }, { Timber.e(it, "Adding a payment method") })
    }
}
