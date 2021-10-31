package com.example.tictactoe.ui.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.tictactoe.R
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.LocalDataState
import com.example.tictactoe.ui.fragments.auth.LoadingFragment
import com.example.tictactoe.ui.fragments.auth.LoginFragment
import com.example.tictactoe.ui.fragments.auth.LoginViewModel
import com.example.tictactoe.ui.fragments.auth.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var loadingDialog: LoadingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setClickableUI()
        setDataLoadingUI()
    }

    private fun setDataLoadingUI(){
        viewModel.isRemoteDataLoaded.observe(this, {
            when(it){
                is NetworkResult.Success->{
                    loadingDialog.dismiss()
                    finish()
                }
                is NetworkResult.Loading->{
                    loadingDialog= LoadingFragment()
                    loadingDialog.show(supportFragmentManager, "Loading")
                }
                is NetworkResult.Error->{
                    loadingDialog.dismiss()
                    loginErrorTV.visibility = View.VISIBLE
                    loginErrorTV.text="${it.errorMessage}"
                }

            }
        })
    }

    private fun setClickableUI() {
        //arrowHome
        viewModel.isDataLoaded.observeForever {
            if (it != null) {
                if (it is LocalDataState.ERROR || it is LocalDataState.LOADING) {
                    arrowHome.isEnabled = false
                }
            }
        }
    }

}