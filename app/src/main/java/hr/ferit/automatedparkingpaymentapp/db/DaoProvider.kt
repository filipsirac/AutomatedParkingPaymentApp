package hr.ferit.automatedparkingpaymentapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.ferit.automatedparkingpaymentapp.model.Car

@Database(entities = [Car::class], version = 1)
abstract class DaoProvider: RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        private var instance: DaoProvider? = null

        fun getInstance(context: Context):DaoProvider{

            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoProvider::class.java,
                    "CarDb"
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }

    }
}