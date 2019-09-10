package hr.ferit.automatedparkingpaymentapp.ui.fragments.registeredcars

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.automatedparkingpaymentapp.R
import hr.ferit.automatedparkingpaymentapp.common.EXTRA_CAR_ID
import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.presentation.RegisteredCarsPresenter
import hr.ferit.automatedparkingpaymentapp.ui.MenuMethodes
import hr.ferit.automatedparkingpaymentapp.ui.activities.main.MainActivity
import hr.ferit.automatedparkingpaymentapp.ui.activities.maps.MapsActivity
import hr.ferit.automatedparkingpaymentapp.ui.adapters.RegistrationAdapter
import hr.ferit.automatedparkingpaymentapp.ui.fragments.addcar.AddCarDialogFragment
import hr.ferit.automatedparkingpaymentapp.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_registered_cars.*

class RegisteredCarsFragment : BaseFragment(), AddCarDialogFragment.CarAddedListener, RegisteredCarsContract.View,
    MenuMethodes.RegistrationMethods {

    private val adapter by lazy { RegistrationAdapter({ onItemSelected(it) }, { deleteItem(it) }) }
    private val presenter: RegisteredCarsContract.Presenter by lazy {
        RegisteredCarsPresenter(CarRoomRepository())
    }

    override fun getLayoutResourceId() = R.layout.fragment_registered_cars

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initListeners()
    }

    override fun onPause() {
        super.onPause()
        refreshCars()
    }

    override fun onResume() {
        super.onResume()
        this.setHasOptionsMenu(true)
        (activity as MainActivity).setListeners(this)
        refreshCars()
    }

    private fun initUi() {
        progress.visibility
        noData.isCursorVisible
        carsRecyclerView.layoutManager = LinearLayoutManager(context)
        carsRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        addCar.setOnClickListener { addCar() }
    }

    private fun deleteItem(car: Car) {
        val dialogBuilder = AlertDialog.Builder(context!!)

        dialogBuilder.setMessage("Do you want to delete this registration?")
            .setCancelable(false)
            .setPositiveButton("Delete") { _, _ ->
                presenter.deleteCar(car)
                refreshCars()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Delete alert dialog")
        alert.show()

        refreshCars()
    }


    private fun refreshCars() {
        progress.visibility = View.GONE
        val data = presenter.onGetAllCars()
        if (data.isNotEmpty()) {
            noData.visibility = View.GONE
        } else {
            noData.visibility = View.VISIBLE
        }
        adapter.setData(presenter.onGetAllCars())
    }

    override fun onCarAdded(car: Car) {
        refreshCars()
    }

    private fun addCar() {
        val dialog = AddCarDialogFragment.newInstance()
        dialog.setCarAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun clearAllCars() {
        presenter.deleteAllCars()
        refreshCars()
    }


    private fun onItemSelected(car: Car) {
        val mapsIntent = Intent(context, MapsActivity::class.java).apply {
            putExtra(EXTRA_CAR_ID, car.id)
        }
        startActivity(mapsIntent)
    }

    override fun onCarListRecieved(cars: MutableList<Car>) {

    }

    override fun onGetCarsFailed() {
        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun newInstance(): Fragment {
            return RegisteredCarsFragment()
        }
    }
}