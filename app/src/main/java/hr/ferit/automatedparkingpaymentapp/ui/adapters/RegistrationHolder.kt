package hr.ferit.automatedparkingpaymentapp.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.automatedparkingpaymentapp.common.OnSwipeTouchListener
import hr.ferit.automatedparkingpaymentapp.model.Car
import kotlinx.android.synthetic.main.item_registration.view.*


class RegistrationHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindData(car: Car, onItemSelected: (Car) -> Unit,onItemSwiped: (Car) -> Unit
    ) {
        view.setOnClickListener { onItemSelected(car) }
        view.setOnTouchListener(object : OnSwipeTouchListener(view.context) {

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                onItemSwiped(car)
            }

            override fun onClick() {
                super.onClick()
                onItemSelected(car)
            }

        })
        view.name.text = car.name
        view.registration.text = car.registration
    }


}