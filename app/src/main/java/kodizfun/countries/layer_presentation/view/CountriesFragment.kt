package kodizfun.countries.layer_presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kodizfun.countries.R
import kodizfun.countries.databinding.FragmentCountriesBinding
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_presentation.view.adapter.CountriesAdapter
import kodizfun.countries.layer_presentation.view.listener.CountrySelectionListener
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CountriesFragment : Fragment(), CountrySelectionListener, Toolbar.OnMenuItemClickListener {

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    private lateinit var binding: FragmentCountriesBinding
    private lateinit var toolBar: Toolbar
    private val viewModel: CountriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init the toolbar menu items
        setHasOptionsMenu(true)

        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Sets countries RecyclerView
        countriesAdapter.setCountrySelectionListener(this)
        binding.countriesRecyclerView.adapter = countriesAdapter

        // Sets toolbar
        toolBar = (requireActivity() as MainActivity).binding.toolbar

    }

    override fun onResume() {
        super.onResume()
        toolBar.title = "Countries"
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        toolBar.inflateMenu(R.menu.menu_countries)
        toolBar.setOnMenuItemClickListener(this)

        observeViewModel(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.itemRefresh -> {
                countriesAdapter.updateCountries(ArrayList())
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
        viewModel.countries.observe(lifeCycleOwner, {
            it?.let { countries ->
                countriesAdapter.updateCountries(countries)
            }
        })

        // Observes data loading value
        viewModel.loading.observe(lifeCycleOwner, {
            it?.let { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })

        // Observes error value
        viewModel.error.observe(lifeCycleOwner, {
            it?.let { errorMessage ->
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    errorMessage,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onCountrySelected(country: Country) {
        viewModel.setSelectedCountry(country)
    }

    override fun navigateToCountryDetails() {
        findNavController().navigate(R.id.action_countriesFragment_to_countryDetailsFragment)
    }
}
