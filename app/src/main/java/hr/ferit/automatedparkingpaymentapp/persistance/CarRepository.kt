package hr.ferit.automatedparkingpaymentapp.persistance

import hr.ferit.automatedparkingpaymentapp.model.Car

interface CarRepository {
    fun getAllCars(): MutableList<Car>
    fun addCar(car: Car)
    fun get(id: Int): Car
    fun deleteCar(car: Car)
    fun deleteAllCars()
}