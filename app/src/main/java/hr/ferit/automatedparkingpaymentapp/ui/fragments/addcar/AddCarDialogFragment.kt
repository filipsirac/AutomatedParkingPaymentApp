package hr.ferit.automatedparkingpaymentapp.ui.fragments.addcar

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hr.ferit.automatedparkingpaymentapp.R
import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.presentation.AddCarDialogPresenter
import kotlinx.android.synthetic.main.fragment_dialog_new_car.*

class AddCarDialogFragment : DialogFragment(), AddCarDialogContract.View {

    private var carAddedListener: CarAddedListener? = null
    private val presenter: AddCarDialogContract.Presenter by lazy {
        AddCarDialogPresenter(CarRoomRepository())
    }

    interface CarAddedListener {
        fun onCarAdded(car: Car)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_car, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun initListeners() {
        saveCarRegistration.setOnClickListener{ saveCar()}
    }

    private fun saveCar() {
        if (isEmpty(newCarTitleInput.text) || isEmpty(newCarRegistrationInput.text)) {
            Toast.makeText(context,"Have to fill both fields.", Toast.LENGTH_SHORT).show()
            return
        }

        val name = newCarTitleInput.text.toString()
        val registration = newCarRegistrationInput.text.toString()
        val car = Car(name = name, registration = registration)
        presenter.addCar(car)
        onCarAdded(car)
        clearUi()

    }

    private fun clearUi() {
        newCarTitleInput.text.clear()
        newCarRegistrationInput.text.clear()
    }

    fun setCarAddedListener(listener: CarAddedListener){
        carAddedListener = listener
    }

    override fun onCarAdded(car: Car) {
        carAddedListener?.onCarAdded(car)
        dismiss()
    }

    override fun onCarAddFailed() {
        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()

    }

    companion object {
        fun newInstance(): AddCarDialogFragment {
            return AddCarDialogFragment()
        }
    }
}