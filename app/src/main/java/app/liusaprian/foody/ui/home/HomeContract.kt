package app.liusaprian.foody.ui.home

import app.liusaprian.foody.base.BasePresenter
import app.liusaprian.foody.model.response.home.FoodResponse

interface HomeContract {

    interface View {
        fun onFetchFoodDataSuccess(foodResponse: FoodResponse)
        fun onFetchFoodDataFailed(message: String)
    }

    interface Presenter: HomeContract, BasePresenter {
        fun getHomeFoodData()
    }
}