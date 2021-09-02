package com.example.tictactoe.ui.activity.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorStateListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.tictactoe.R
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUI()
    }

    private fun setUI() {
        //setOnClickListeners
        setOnClickListeners()
        setOnFocusListeners()

        //setClickableStatus
        setClickableUI()
    }

    private fun setOnFocusListeners() {
        usernameET.setOnFocusChangeListener { view, isFocused ->
            if (!isFocused) {
                validateInputs((view as EditText).text.toString(), passwordET)

            }

        }
        passwordET.setOnFocusChangeListener { view, isFocused ->
            if (!isFocused) {
                validateInputs((view as EditText).text.toString(), usernameET)

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateInputs(text: String, view: EditText) {
        if (text.isEmpty() || !view.isFocused) {
            submitLogin.isEnabled = false
            loginErrorTV.visibility = View.VISIBLE
            loginErrorTV.text = "All fields should be filled"
        }

        if (passwordET.text.toString().isNotEmpty() && usernameET.text.toString().isNotEmpty()) {
            submitLogin.isEnabled = true
            loginErrorTV.visibility = View.GONE
        }
    }

    private fun setOnClickListeners() {
        arrowHome.setOnClickListener {
            finish()
        }
        submitLogin.setOnClickListener {
            viewModel.login(usernameET.text.toString(), passwordET.text.toString())
        }
    }

    private fun setClickableUI() {
        //arrowHome
        viewModel.isDataLoaded.observeForever {
            if (it != null) {
                if (it == LocalDataState.ERROR || it == LocalDataState.LOADING) {
                    arrowHome.isEnabled = false
                }
            }
        }
    }
}