package hr.ferit.automatedparkingpaymentapp.presentation

import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.ui.fragments.addcar.AddCarDialogContract

class AddCarDialogPresenter(private val repository: CarRoomRepository) : AddCarDialogContract.Presenter {

    private lateinit var view: AddCarDialogContract.View

    override fun setView(view: AddCarDialogContract.View) {
        this.view = view
    }

    override fun addCar(car: Car) {
        repository.addCar(car)
    }

}