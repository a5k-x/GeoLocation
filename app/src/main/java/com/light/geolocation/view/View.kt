package com.light.geolocation.view

interface View {
    fun showLoadingScreen()
    fun showData(data: String)
    fun showError(error: String)

}