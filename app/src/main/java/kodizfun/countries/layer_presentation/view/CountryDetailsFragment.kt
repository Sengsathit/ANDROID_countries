package kodizfun.countries.layer_presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import kodizfun.countries.R
import kodizfun.countries.databinding.FragmentCountryDetailsBinding
import kodizfun.countries.layer_presentation.loadImage
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel

/**
 * Fragment that displays a country details
 */
@AndroidEntryPoint
class CountryDetailsFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var toolBar: Toolbar
    private val viewModel: CountriesViewModel by activityViewModels()
    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init the toolbar menu items
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets toolbar
        toolBar = (requireActivity() as MainActivity).binding.toolbar
    }

    //region Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        toolBar.inflateMenu(R.menu.menu_country_details)
        toolBar.setOnMenuItemClickListener(this)

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
        viewModel.selectedCountry.observe(lifeCycleOwner, {
            it?.let { country ->
                updateUI(country.isFavorite)
            }
        })
    }

    private fun updateUI(isFavorite: Boolean?) {

        binding.countryFlagImageView.loadImage(viewModel.selectedCountry.value?.flag)
        binding.countryNameTextView.text = viewModel.selectedCountry.value?.name
        toolBar.title = viewModel.selectedCountry.value?.name

        isFavorite?.let {
            toolBar.menu.findItem(R.id.itemFavorite).icon =
                if (isFavorite) (requireActivity() as MainActivity).getDrawable(R.drawable.icon_heart)
                else (requireActivity() as MainActivity).getDrawable(R.drawable.icon_heart_empty)
        }

    }

}
