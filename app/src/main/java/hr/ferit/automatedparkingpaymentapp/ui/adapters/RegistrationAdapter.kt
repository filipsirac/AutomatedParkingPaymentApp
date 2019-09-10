package hr.ferit.automatedparkingpaymentapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.automatedparkingpaymentapp.R
import hr.ferit.automatedparkingpaymentapp.model.Car

class RegistrationAdapter(private val onItemSelected: (Car) -> Unit, private val onSwipeToDelete: (Car) -> Unit) : RecyclerView.Adapter<RegistrationHolder>() {

    private val data: MutableList<Car> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_registration, parent, false)
        return RegistrationHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegistrationHolder, position: Int) {
        holder.bindData(data[position], onItemSelected, onSwipeToDelete)
    }

    fun setData(data: List<Car>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
