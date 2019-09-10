package hr.ferit.automatedparkingpaymentapp.ui.fragments.payment

import hr.ferit.automatedparkingpaymentapp.model.Car

interface PaymentDialogContract {

    interface View{

        fun onSuccessful(car: Car)

        fun onFailed()

    }

    interface Presenter{

        fun setView(view: View)

        fun getRegistration(carID: Int): Car
    }
}