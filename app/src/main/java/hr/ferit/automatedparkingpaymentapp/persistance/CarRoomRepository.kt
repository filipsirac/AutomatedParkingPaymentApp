package hr.ferit.automatedparkingpaymentapp.persistance

import hr.ferit.automatedparkingpaymentapp.AppParking
import hr.ferit.automatedparkingpaymentapp.db.DaoProvider
import hr.ferit.automatedparkingpaymentapp.model.Car

class CarRoomRepository: CarRepository {

    private var db = DaoProvider.getInstance(AppParking.getAppContext())
    private var carDao = db.carDao()

    override fun getAllCars(): MutableList<Car> {
        return carDao.getAllCars()
    }

    override fun addCar(car: Car) {
        carDao.insertCar(car)
    }

    override fun get(id: Int): Car {
        return carDao.get(id)
    }

    override fun deleteCar(car: Car) {
        carDao.deleteCar(car)
    }

    override fun deleteAllCars() {
        carDao.deleteAllCars()
    }

}