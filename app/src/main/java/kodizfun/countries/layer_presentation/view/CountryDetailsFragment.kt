package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kodizfun.countries.R
import kodizfun.countries.layer_presentation.di.ViewModelFactory
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_country_details.*
import javax.inject.Inject

/**
 * Fragment that displays a country details
 */
class CountryDetailsFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CountriesViewModel

    private lateinit var toolBar: Toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the sub component graph from MainActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).mainComponent.inject(this)
        // Initiates the ViewModel
        viewModel =
            ViewModelProvider(this, this.viewModelFactory).get(CountriesViewModel::class.java)
        // Observes all values from the ViewModel
        observeViewModel(this)
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

        countryNameTextView.text = viewModel.selectedCountry?.name

        // Sets toolbar
        toolBar = activity!!.toolbar
    }

    override fun onResume() {
        super.onResume()
        toolBar.title = "Country details"
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        toolBar.inflateMenu(R.menu.menu_country_details)
        toolBar.setOnMenuItemClickListener(this)

        viewModel.initSelectedCountry()
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
        // Observes isFavorite value
        viewModel.isSelectedCountryFavorite.observe(lifeCycleOwner, Observer {
            it?.let { isFavorite ->
                updateFavoriteIcon(isFavorite)
            }
        })
    }

    private fun updateFavoriteIcon(isFavorite: Boolean?) {
        isFavorite?.let {
            toolBar.menu.findItem(R.id.itemFavorite).icon =
                if (isFavorite) activity!!.getDrawable(R.drawable.icon_heart)
                else activity!!.getDrawable(R.drawable.icon_heart_empty)
        }
    }

}
