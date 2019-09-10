package hr.ferit.automatedparkingpaymentapp.ui.fragments.payment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hr.ferit.automatedparkingpaymentapp.common.EXTRA_CAR_ID
import hr.ferit.automatedparkingpaymentapp.common.ZONE1
import hr.ferit.automatedparkingpaymentapp.common.ZONE2
import hr.ferit.automatedparkingpaymentapp.common.zoneTag
import hr.ferit.automatedparkingpaymentapp.model.Car
import hr.ferit.automatedparkingpaymentapp.persistance.CarRoomRepository
import hr.ferit.automatedparkingpaymentapp.presentation.PaymentDialogPresenter
import kotlinx.android.synthetic.main.fragment_dialog_payment.*


class PaymentDialogFragment : DialogFragment(), PaymentDialogContract.View {

    private var carID: Int = NO_CAR
    private lateinit var currentRegistration: String

    private val presenter by lazy { PaymentDialogPresenter(CarRoomRepository()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(hr.ferit.automatedparkingpaymentapp.R.layout.fragment_dialog_payment, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_CAR_ID)?.let { carID = it }
        displayPaymentInfo(carID)
        initListeners()
    }

    private fun initListeners() {
        prepareSMS.setOnClickListener { goToSMS() }
    }

    private fun goToSMS() {
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.type = "vnd.android-dir/mms-sms"
        smsIntent.putExtra("address", zonePaymentNumber.text.toString())
        smsIntent.putExtra("sms_body", currentRegistration)
        startActivity(smsIntent)
    }

    private fun displayPaymentInfo(carId: Int) {
        currentRegistration = presenter.getRegistration(carId).registration
        registration.setText(currentRegistration)
        when (zoneTag) {
            0 -> zonePaymentNumber.setText("")
            1 -> zonePaymentNumber.setText(ZONE1.toString())
            2 -> zonePaymentNumber.setText(ZONE2.toString())
        }
    }

    override fun onSuccessful(car: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailed() {
        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val NO_CAR: Int = -1

        fun newInstance(carId: Int): PaymentDialogFragment {
            val bundle = Bundle().apply { putInt(EXTRA_CAR_ID, carId) }
            return PaymentDialogFragment().apply { arguments = bundle }
        }
    }
}