package kodizfun.countries.layer_presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
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
class CountryDetailsFragment : Fragment(),  Toolbar.OnMenuItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CountriesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the sub component graph from MainActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).mainComponent.inject(this)
        // Initiates the ViewModel
        viewModel = ViewModelProvider(this, this.viewModelFactory).get(CountriesViewModel::class.java)
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
    }

    override fun onResume() {
        super.onResume()
        // Sets toolbar
        activity?.toolbar?.title ="Country details"
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.toolbar?.inflateMenu(R.menu.menu_country_details)
        activity?.toolbar?.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.itemFavorite -> println("Click sur favoris")
            else -> println("Default")
        }
        return super.onOptionsItemSelected(item!!)
    }
    //endregion

}
