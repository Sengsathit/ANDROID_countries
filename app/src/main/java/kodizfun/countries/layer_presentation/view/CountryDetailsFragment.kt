package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kodizfun.countries.R
import kodizfun.countries.layer_presentation.di.ViewModelFactory
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*
import javax.inject.Inject

/**
 * Fragment that displays a country details
 */
class CountryDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CountriesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the sub component graph from MainActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initiates the ViewModel
        viewModel = ViewModelProvider(this, this.viewModelFactory).get(CountriesViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        countryNameTextView.text = viewModel.selectedCountry?.name
    }

}
