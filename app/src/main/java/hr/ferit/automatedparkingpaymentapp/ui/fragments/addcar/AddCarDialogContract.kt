package hr.ferit.automatedparkingpaymentapp.ui.fragments.addcar

import hr.ferit.automatedparkingpaymentapp.model.Car

interface AddCarDialogContract {
    interface View{

        fun onCarAdded(car: Car)

        fun onCarAddFailed()

    }

    interface Presenter{

        fun setView(view: View)

        fun addCar(car: Car)
    }
}