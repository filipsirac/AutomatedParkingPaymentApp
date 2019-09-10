package hr.ferit.automatedparkingpaymentapp.ui.fragments.registeredcars

import hr.ferit.automatedparkingpaymentapp.model.Car

interface RegisteredCarsContract {

    interface View{

        fun onCarListRecieved(cars: MutableList<Car>)

        fun onGetCarsFailed()

    }

    interface Presenter{

        fun setView(view: RegisteredCarsContract.View)

        fun onGetAllCars(): MutableList<Car>

        fun deleteCar(car: Car)

        fun deleteAllCars()


    }
}