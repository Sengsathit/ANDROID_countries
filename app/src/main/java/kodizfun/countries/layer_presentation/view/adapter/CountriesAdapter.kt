package kodizfun.countries.layer_presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kodizfun.countries.databinding.ItemCountryBinding
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_presentation.loadImage
import kodizfun.countries.layer_presentation.view.listener.CountrySelectionListener
import javax.inject.Inject

/**
 * Adapter for countries RecyclerView
 */
class CountriesAdapter @Inject constructor() : RecyclerView.Adapter<CountryViewHolder>() {

    private lateinit var countrySelectionListener: CountrySelectionListener

    private var countries = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, countrySelectionListener)
    }

    override fun getItemCount(): Int {
        return countries.count()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindCountry(countries[position])
    }

    fun updateCountries(countries: ArrayList<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    fun setCountrySelectionListener(countrySelectionListener: CountrySelectionListener) {
        this.countrySelectionListener = countrySelectionListener
    }

}

/**
 * View holder for country
 */
class CountryViewHolder(binding: ItemCountryBinding, listener: CountrySelectionListener) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    private var binding = binding
    private var country: Country? = null
    private val countrySelectionListener = listener

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        country?.let {
            countrySelectionListener.onCountrySelected(it)
            countrySelectionListener.navigateToCountryDetails()
        }
    }

    fun bindCountry(country: Country) {
        this.country = country
        binding.countryFlagImageView.loadImage(this.country?.flag)
        binding.countryNameTextView.text = this.country?.name
    }

    companion object {
        private val TAG = CountryViewHolder.javaClass.name
    }

}
