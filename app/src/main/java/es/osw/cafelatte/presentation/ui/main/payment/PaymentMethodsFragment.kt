package es.osw.cafelatte.presentation.ui.main.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.osw.cafelatte.R
import es.osw.cafelatte.databinding.FragmentPaymentMethodsBinding
import es.osw.cafelatte.presentation.ui.BaseFragment
import es.osw.cafelatte.presentation.ui.extensions.baseActivity
import es.osw.cafelatte.presentation.ui.extensions.inflateBinding
import es.osw.cafelatte.presentation.ui.main.HomeActivity
import javax.inject.Inject

class PaymentMethodsFragment : BaseFragment() {
    @Inject lateinit var viewModel: PaymentMethodsViewModel

    companion object {
        fun newInstance(): PaymentMethodsFragment = PaymentMethodsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPaymentMethodsBinding = container!!.inflateBinding(R.layout
                .fragment_payment_methods)

        baseActivity.getComponent(HomeActivity.HomeComponent::class)!!.inject(this)
        binding.viewModel = viewModel

        return binding.root
    }

}
