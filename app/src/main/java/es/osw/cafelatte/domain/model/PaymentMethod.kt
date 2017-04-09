package es.osw.cafelatte.domain.model

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

@PaperParcel
data class PaymentMethod(
        val cardNumber: String = "",
        val expirationMonth: String = "",
        val expirationYear: String = "",
        val cvv: String = "") : Parcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelPaymentMethod.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelPaymentMethod.writeToParcel(this, dest, flags)
    }
}
