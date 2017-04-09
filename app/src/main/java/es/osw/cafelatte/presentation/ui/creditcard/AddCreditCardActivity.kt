package es.osw.cafelatte.presentation.ui.creditcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.braintreepayments.cardform.utils.CardType
import dagger.Subcomponent
import es.osw.cafelatte.R
import es.osw.cafelatte.databinding.ActivityAddCreditCardBinding
import es.osw.cafelatte.domain.model.PaymentMethod
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.di.base.component.ActivityComponent
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.extensions.setContentBinding
import kotlinx.android.synthetic.main.activity_add_credit_card.*
import javax.inject.Inject

class AddCreditCardActivity : BaseActivity() {
    @PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class)) interface AddCreditCardComponent :
            ActivityComponent {
        fun inject(activity: AddCreditCardActivity)
    }

    val addCreditCardComponent: AddCreditCardComponent by lazy {
        applicationComponent.addCreditCardComponent(ActivityModule(this))
    }

    @Inject lateinit var viewModel: AddCreditCardViewModel

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, AddCreditCardActivity::class.java)
        }

        fun getCreditCard(data: Intent): PaymentMethod {
            return data.getParcelableExtra(EXTRA_CREDIT_CARD)
        }

        val EXTRA_CREDIT_CARD = "extra_credit_card"

        private val SUPPORTED_CARD_TYPES = arrayOf(CardType.VISA, CardType.MASTERCARD,
                CardType.MAESTRO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddCreditCardBinding = setContentBinding(R.layout.activity_add_credit_card)

        addCreditCardComponent.inject(this)

        binding.viewModel = viewModel

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supported_card_types.setSupportedCardTypes(*SUPPORTED_CARD_TYPES)

        card_form.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .actionLabel(getString(R.string.save))
                .setup(this)
    }

    @Suppress("UNUSED_PARAMETER")
    fun clickSave(view: View) {
        card_form.validate()

        if (card_form.isValid) {
            viewModel.clickSave(PaymentMethod(cardNumber = card_form.cardNumber,
                    expirationMonth = card_form.expirationMonth,
                    expirationYear = card_form.expirationYear,
                    cvv = card_form.cvv))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_card, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.card_io_item -> {
                card_form.scanCard(this)
            }

            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
