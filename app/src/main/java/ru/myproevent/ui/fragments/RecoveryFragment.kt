package ru.myproevent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.myproevent.ProEventApp
import ru.myproevent.databinding.FragmentRecoveryBinding
import ru.myproevent.ui.BackButtonListener
import ru.myproevent.ui.presenters.recovery.RecoveryPresenter
import ru.myproevent.ui.presenters.recovery.RecoveryView

class RecoveryFragment : MvpAppCompatFragment(), RecoveryView, BackButtonListener {
    private var _view: FragmentRecoveryBinding? = null
    private val view get() = _view!!

    private val presenter by moxyPresenter {
        RecoveryPresenter().apply {
            ProEventApp.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun newInstance() = RecoveryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = FragmentRecoveryBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _view = null
    }
}