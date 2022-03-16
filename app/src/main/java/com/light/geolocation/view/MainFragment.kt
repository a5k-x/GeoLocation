package com.light.geolocation.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.light.geolocation.databinding.FragmentMainBinding
import com.light.geolocation.presenter.MainPresenter


class MainFragment : Fragment(), com.light.geolocation.view.View {
   private val permLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        it -> val granted = it.entries.all {
            it.value == true
    }
        if (granted){
            getLocation()
        }
    }

    private var vb: FragmentMainBinding? = null

    private val presenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMainBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                showDialog()
            } else {
                requestPermission()
            }
        }
    }
//Уточнить для чего нужны данные
   private fun showDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle("Permission")
            .setMessage("Разрешение только для определения вашего местоположения :-)")
            .setPositiveButton("OK"
            ) { _, _ -> requestPermission() }
            .setNegativeButton("Cancel"){dialog,_ -> dialog.cancel() }
            .create()
            .show()
}
  //Запросить разрешение на полученные данных
  private fun requestPermission(){
      permLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
       Manifest.permission.ACCESS_COARSE_LOCATION))
  }

    private fun getLocation() {
        Log.i("AAA","getLocation")
    }

    override fun showLoadingScreen() {
        TODO("Not yet implemented")
    }

    override fun showData(data: String) {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}