package es.osw.cafelatte.presentation.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.Subcomponent
import es.osw.cafelatte.R
import es.osw.cafelatte.databinding.ActivityProfileBinding
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.di.base.component.ActivityComponent
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.extensions.setContentBinding
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject

class ProfileActivity : BaseActivity() {
    @PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class)) interface ProfileComponent :
            ActivityComponent {
        fun inject(activity: ProfileActivity)
    }

    val profileComponent by lazy {
        applicationComponent.profileComponent(ActivityModule(this))
    }

    @Inject lateinit var viewModel: ProfileViewModel

    companion object {
        fun getCallingIntent(context: Activity) = Intent(context, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileComponent.inject(this)

        val binding: ActivityProfileBinding = setContentBinding(R.layout.activity_profile)

        binding.viewModel = viewModel

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.load()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
            when (item?.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }

                R.id.log_out -> {
                    viewModel.logout()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }

}
