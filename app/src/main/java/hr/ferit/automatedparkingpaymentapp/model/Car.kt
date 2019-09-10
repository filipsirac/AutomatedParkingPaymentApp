package hr.ferit.automatedparkingpaymentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val registration: String,
    val name: String
)