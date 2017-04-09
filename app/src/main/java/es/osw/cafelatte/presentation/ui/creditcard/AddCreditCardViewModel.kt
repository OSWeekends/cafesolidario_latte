package es.osw.cafelatte.presentation.ui.creditcard

import es.osw.cafelatte.domain.model.PaymentMethod
import javax.inject.Inject

class AddCreditCardViewModel @Inject internal constructor(val navigator: AddCreditCardNavigator) {

    fun clickSave(paymentMethod: PaymentMethod) {
        navigator.deliverResult(paymentMethod)
    }
}
