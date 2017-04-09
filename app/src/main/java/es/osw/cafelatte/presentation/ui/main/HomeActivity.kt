package es.osw.cafelatte.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import dagger.Subcomponent
import es.osw.cafelatte.R
import es.osw.cafelatte.databinding.ActivityHomeBinding
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.main.home.HomeFragment
import es.osw.cafelatte.presentation.ui.main.payment.PaymentMethodsFragment
import es.osw.cafelatte.presentation.ui.main.profile.NavBarProfileView
import kotlin.reflect.KClass


class HomeActivity : BaseActivity() {
    @PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class)) interface HomeComponent {
        fun inject(fragment: PaymentMethodsFragment)

        fun inject(fragment: HomeFragment)

        fun inject(view: NavBarProfileView)
    }

    val homeComponent: HomeComponent by lazy {
        applicationComponent.homeComponent(ActivityModule(this))
    }

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)
        configureDrawer()

        val onNavigationItemSelectedListener = createNavigationItemSelectedListener()
        binding.navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener)
        onNavigationItemSelectedListener.onNavigationItemSelected(binding.navigationView.menu
                .findItem(R.id.home))
    }

    private fun createNavigationItemSelectedListener(): NavigationView.OnNavigationItemSelectedListener {
        val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
            it.isChecked = !it.isChecked
            binding.drawerLayout.closeDrawers()

            when (it.itemId) {
                R.id.home -> {
                    replaceContent(HomeFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.payment_methods -> {
                    replaceContent(PaymentMethodsFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }
        return onNavigationItemSelectedListener
    }

    private fun replaceContent(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    fun configureDrawer() {
        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_drawer,
                R.string.close_drawer) {

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
            }
        }

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getComponent(clazz: KClass<T>): T? {
        when (clazz) {
            HomeComponent::class -> {
                return homeComponent as T
            }
        }

        return null
    }
}
