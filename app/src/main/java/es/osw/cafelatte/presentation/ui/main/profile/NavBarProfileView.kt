package es.osw.cafelatte.presentation.ui.main.profile

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import es.osw.cafelatte.R
import es.osw.cafelatte.databinding.ViewHomeMenuProfileBinding
import es.osw.cafelatte.presentation.ui.extensions.inflate
import es.osw.cafelatte.presentation.ui.extensions.inflateBinding
import es.osw.cafelatte.presentation.ui.main.HomeActivity
import javax.inject.Inject

class NavBarProfileView : FrameLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @Inject lateinit var viewModel: NavBarProfileViewModel

    init {
        if (isInEditMode) {
            inflate(R.layout.view_home_menu_profile, true)
        } else {
            val binding = inflateBinding<ViewHomeMenuProfileBinding>(R.layout.view_home_menu_profile, true)
            (context as HomeActivity).homeComponent.inject(this)
            binding.viewModel = viewModel
        }
    }
}
