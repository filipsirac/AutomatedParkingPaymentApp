package hr.ferit.automatedparkingpaymentapp.ui.activities.main

import android.view.Menu
import android.view.MenuItem
import hr.ferit.automatedparkingpaymentapp.R
import hr.ferit.automatedparkingpaymentapp.ui.MenuMethodes
import hr.ferit.automatedparkingpaymentapp.ui.activities.base.BaseActivity
import hr.ferit.automatedparkingpaymentapp.ui.fragments.registeredcars.RegisteredCarsFragment

class MainActivity : BaseActivity() {

    private var listener: MenuMethodes.RegistrationMethods? = null
    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(RegisteredCarsFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_registered_cars, menu)
        return true
    }

    fun setListeners(fragment: RegisteredCarsFragment){
        listener = fragment
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.clearAllCars) listener!!.clearAllCars()
        return super.onOptionsItemSelected(item)

    }


}
