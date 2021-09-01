package app.liusaprian.foody.ui.order

import app.liusaprian.foody.base.BasePresenter
import app.liusaprian.foody.model.response.order.TransactionResponse

interface OrderContract {

    interface View {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter: OrderContract, BasePresenter {
        fun getTransaction()
    }
}