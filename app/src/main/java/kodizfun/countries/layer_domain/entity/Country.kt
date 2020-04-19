package kodizfun.countries.layer_domain.entity

data class Country(
    var code: String? = null,
    var name: String? = null,
    var flag: String? = null,
    var isFavorite: Boolean = false
)