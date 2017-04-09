package es.osw.cafelatte.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.osw.cafelatte.presentation.ui.BaseFragment
import es.osw.cafelatte.presentation.ui.extensions.baseActivity
import es.osw.cafelatte.presentation.ui.main.HomeActivity
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    @Inject lateinit var viewModel: HomeFragmentViewModel

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseActivity.getComponent(HomeActivity.HomeComponent::class)!!.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
