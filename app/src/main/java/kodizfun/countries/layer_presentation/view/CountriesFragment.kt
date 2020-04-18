package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodizfun.countries.R
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_presentation.di.ViewModelFactory
import kodizfun.countries.layer_presentation.view.adapter.CountriesAdapter
import kodizfun.countries.layer_presentation.view.listener.CountrySelectionListener
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject

class CountriesFragment : Fragment(), CountrySelectionListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

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
        viewModel =
            ViewModelProvider(this, this.viewModelFactory).get(CountriesViewModel::class.java)

        // Observes all values from the ViewModel
        observeViewModel(this)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Sets countries RecyclerView
        countriesAdapter.setCountrySelectionListener(this)
        countriesRecyclerView.adapter = countriesAdapter

        // Sets click listener
        countriesButton.setOnClickListener {
            countriesAdapter.updateCountries(ArrayList())
            viewModel.getCountries()
        }
    }

    private fun observeViewModel(lifeCycleOwner: LifecycleOwner) {

        // Observes countries value
        viewModel.countries.observe(lifeCycleOwner, Observer {
            it?.let { countries ->
                countriesAdapter.updateCountries(countries)
            }
        })

        // Observes data loading value
        viewModel.loading.observe(lifeCycleOwner, Observer {
            it?.let { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })

        // Observes error value
        viewModel.error.observe(lifeCycleOwner, Observer {
            it?.let { errorMessage ->
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    errorMessage,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

    companion object {
        private val TAG = CountriesFragment::class.java.simpleName
    }

    override fun onCountrySelected(country: Country) {
        viewModel.setSelectedCountry(country)
    }

    override fun navigateToCountryDetails() {
        findNavController().navigate(R.id.action_countriesFragment_to_countryDetailsFragment)
    }
}
