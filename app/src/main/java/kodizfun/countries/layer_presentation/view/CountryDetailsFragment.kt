package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kodizfun.countries.R
import kodizfun.countries.layer_presentation.loadImage
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_country_details.*
import javax.inject.Inject

/**
 * Fragment that displays a country details
 */
@AndroidEntryPoint
class CountryDetailsFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var viewModel: CountriesViewModel

    private lateinit var toolBar: Toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init the toolbar menu items
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Sets toolbar
        //toolBar = activity!!.toolbar
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        toolBar.inflateMenu(R.menu.menu_country_details)
        toolBar.setOnMenuItemClickListener(this)

        // Observes all values from the ViewModel here
        // in order to waiting for all inflating process (layout, menu, etc...)
        observeViewModel(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.itemFavorite -> viewModel.toggleCountryFavorite()
            else -> println("Default")
        }
        return super.onOptionsItemSelected(item!!)
    }
    //endregion

    /**
     * Observes LiveData values from the ViewModel
     */
    private fun observeViewModel(lifeCycleOwner: LifecycleOwner) {
        // Observes selected country value
        viewModel.selectedCountry.observe(lifeCycleOwner, Observer {
            it?.let { country ->
                updateUI(country.isFavorite)
            }
        })
    }

    private fun updateUI(isFavorite: Boolean?) {

        countryFlagImageView.loadImage(viewModel.selectedCountry.value?.flag)
        countryNameTextView.text = viewModel.selectedCountry.value?.name
        toolBar.title = viewModel.selectedCountry.value?.name

        isFavorite?.let {
            toolBar.menu.findItem(R.id.itemFavorite).icon =
                if (isFavorite) activity!!.getDrawable(R.drawable.icon_heart)
                else activity!!.getDrawable(R.drawable.icon_heart_empty)
        }
    }

}
