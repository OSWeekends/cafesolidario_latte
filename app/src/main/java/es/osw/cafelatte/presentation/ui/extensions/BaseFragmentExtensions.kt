package es.osw.cafelatte.presentation.ui.extensions

import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.BaseFragment


val BaseFragment.baseActivity: BaseActivity
    get() = activity as BaseActivity
