package hr.ferit.automatedparkingpaymentapp.presentation

import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.ui.fragments.registeredcars.RegisteredCarsContract

class RegisteredCarsPresenter(private val repository: CarRoomRepository): RegisteredCarsContract.Presenter {

    private lateinit var view: RegisteredCarsContract.View

    override fun setView(view: RegisteredCarsContract.View) {
        this.view = view
    }

    override fun onGetAllCars(): MutableList<Car> {
        return repository.getAllCars()
    }

    override fun deleteCar(car: Car) {
        repository.deleteCar(car)
    }

    override fun deleteAllCars() {
        repository.deleteAllCars()
    }
}