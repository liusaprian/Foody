package app.liusaprian.foody.model

import app.liusaprian.foody.model.response.home.FoodItem

data class Order(
    var food: FoodItem,
    var quantity: Int
)