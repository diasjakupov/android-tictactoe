package com.example.tictactoe.ui.fragments.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.view.*

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val viewModel:LoginViewModel by activityViewModels()
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_register, container, false)
        setUI()
        return rootView
    }

    private fun setUI(){
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        rootView.loginLink.setOnClickListener {
            val action=RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        rootView.submitRegister.setOnClickListener {
            register()
            Log.e("TAG", "register")
        }
    }

    private fun register(){
        val username=rootView.usernameET.text.toString()
        val password=rootView.passwordET.text.toString()
        val re_password=rootView.re_passwordET.text.toString()
        if(re_password==password && username.length>=2){
            Log.e("TAG", "under validation")
            viewModel.register(username, password)
        }
    }

}