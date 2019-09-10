package hr.ferit.automatedparkingpaymentapp.db

import androidx.room.*
import hr.ferit.automatedparkingpaymentapp.model.Car

@Dao
interface CarDao {
    @Query("SELECT * FROM Car")
    fun getAllCars(): MutableList<Car>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCar(car: Car)

    @Query("SELECT * FROM Car WHERE id= :id")
    fun get(id: Int): Car

    @Delete
    fun deleteCar(car: Car)

    @Query("DELETE from Car")
    fun deleteAllCars()
}