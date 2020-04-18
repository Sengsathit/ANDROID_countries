package kodizfun.countries.layer_presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kodizfun.countries.App
import kodizfun.countries.R
import kodizfun.countries.layer_presentation.di.MainComponent
import kodizfun.countries.layer_presentation.di.ViewModelFactory
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    // Stores an instance of PresentationComponent so that its Fragments can access it
    lateinit var mainComponent: MainComponent

    private lateinit var viewModel: CountriesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        // Creation of the sub graph using the application graph
        mainComponent = (application as App).appComponent.mainComponentFactory().create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel =
            ViewModelProvider(this, this.viewModelFactory).get(CountriesViewModel::class.java)

    }


    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}
