package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodizfun.countries.R
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_presentation.view.adapter.CountriesAdapter
import kodizfun.countries.layer_presentation.view.listener.CountrySelectionListener
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject

class CountriesFragment : Fragment(), CountrySelectionListener, Toolbar.OnMenuItemClickListener  {

    //@Inject lateinit var countriesAdapter: CountriesAdapter

    private lateinit var viewModel: CountriesViewModel

    private lateinit var toolBar: Toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        observeViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init the toolbar menu items
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Sets countries RecyclerView
        //countriesAdapter.setCountrySelectionListener(this)
        //countriesRecyclerView.adapter = countriesAdapter

        // Sets toolbar
        //toolBar = activity!!.toolbar
    }

    override fun onResume() {
        super.onResume()
        toolBar.title ="Countries"
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        toolBar.inflateMenu(R.menu.menu_countries)
        toolBar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.itemRefresh -> {
                //countriesAdapter.updateCountries(ArrayList())
                viewModel.getCountries()
            }
            else -> println("Default")
        }
        return super.onOptionsItemSelected(item!!)
    }
    //endregion

    /**
     * Observes LiveData values from the ViewModel
     */
    private fun observeViewModel(lifeCycleOwner: LifecycleOwner) {

        // Observes countries value
        viewModel.countries.observe(lifeCycleOwner, Observer {
            it?.let { countries ->
                //countriesAdapter.updateCountries(countries)
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
