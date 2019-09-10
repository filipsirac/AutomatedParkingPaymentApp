package hr.ferit.automatedparkingpaymentapp.ui.activities.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.ferit.automatedparkingpaymentapp.R
import hr.ferit.automatedparkingpaymentapp.common.showFragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())
        setUpUi()
    }

    protected fun showFragment(fragment: androidx.fragment.app.Fragment) {
        showFragment(R.id.fragmentContainer, fragment)
    }


    abstract fun getLayoutResourceId(): Int
    abstract fun setUpUi()


}