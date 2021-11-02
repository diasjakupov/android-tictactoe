package com.example.tictactoe.ui.fragments.auth

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.view.*

import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView=inflater.inflate(R.layout.fragment_login, container, false)
        setUI()
        return rootView
    }

    private fun setUI() {
        //setOnClickListeners
        setOnClickListeners()
    }

    private fun setOnFocusListeners() {
        rootView.usernameET.setOnFocusChangeListener { view, isFocused ->
            if (!isFocused) {
                validateInputs((view as EditText).text.toString(), rootView.passwordET)
            }

        }
        rootView.passwordET.setOnFocusChangeListener { view, isFocused ->
            if (!isFocused) {
                validateInputs((view as EditText).text.toString(), rootView.usernameET)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateInputs(text: String, view: EditText) {
        if (text.isEmpty() || !view.isFocused) {
            rootView.submitLogin.isEnabled = false
            rootView.loginErrorTV.visibility = View.VISIBLE
            rootView.loginErrorTV.text = "All fields should be filled"
        }

        if (rootView.passwordET.text.toString().isNotEmpty() && rootView.usernameET.text.toString().isNotEmpty()) {
            rootView.submitLogin.isEnabled = true
            rootView.loginErrorTV.visibility = View.GONE
        }
    }

    private fun setOnClickListeners() {
        rootView.submitLogin.setOnClickListener {
            viewModel.login(rootView.usernameET.text.toString(), rootView.passwordET.text.toString())
        }

        rootView.signUpLink.setOnClickListener {
            val action=LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }



}