package hr.ferit.automatedparkingpaymentapp.presentation

import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.ui.fragments.payment.PaymentDialogContract

class PaymentDialogPresenter(private val repository: CarRoomRepository): PaymentDialogContract.Presenter {

    private lateinit var view: PaymentDialogContract.View

    override fun setView(view: PaymentDialogContract.View) {
        this.view = view
    }

    override fun getRegistration(carID: Int): Car {
        return repository.get(carID)
    }


}