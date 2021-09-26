package kodizfun.countries.layer_presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kodizfun.countries.App
import kodizfun.countries.R
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

    }


    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}
