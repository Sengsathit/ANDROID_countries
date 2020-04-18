package kodizfun.countries.layer_presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kodizfun.countries.R
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_presentation.loadImage
import kodizfun.countries.layer_presentation.view.listener.CountrySelectionListener
import kotlinx.android.synthetic.main.item_country.view.*
import javax.inject.Inject

/**
 * Adapter for countries RecyclerView
 */
class CountriesAdapter @Inject constructor() : RecyclerView.Adapter<CountryViewHolder>() {

    private lateinit var countrySelectionListener: CountrySelectionListener

    private var countries = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(inflatedView, countrySelectionListener)
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
class CountryViewHolder(v: View, listener: CountrySelectionListener) : RecyclerView.ViewHolder(v),
    View.OnClickListener {

    private var view = v
    private var country: Country? = null
    private val countrySelectionListener = listener

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        country?.let {
            countrySelectionListener.onCountrySelected(it)
            countrySelectionListener.navigateToCountryDetails()
        }
    }

    fun bindCountry(country: Country) {
        this.country = country
        view.countryFlagImageView.loadImage(this.country?.flag)
        view.countryNameTextView.text = this.country?.name
    }

    companion object {
        private val TAG = CountryViewHolder.javaClass.name
    }

}
