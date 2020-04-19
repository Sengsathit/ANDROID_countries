package kodizfun.countries.layer_data.mapper

import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry
import kodizfun.countries.layer_data.datasource.remote.model.RemoteDataCountry
import kodizfun.countries.layer_domain.entity.Country


/**
 * DATA layer -> DOMAIN layer
 * RemoteDataCountry to Country
 */
fun RemoteDataCountry.mapToDomain(): Country = Country(code = alpha3Code, name = name, flag = flagPNG)

fun List<RemoteDataCountry>.mapToDomain(): ArrayList<Country> = ArrayList(map { it.mapToDomain() })

fun LocalDataCountry.mapToDomain(): Country = Country(code = code, name = name, flag = flag)

/**
 * DOMAIN layer -> DATA layer
 * Country to LocalDataCountry
 */
//TODO essayer de ne pas utiliser le force unwrap
fun Country.mapToLocalData(): LocalDataCountry = LocalDataCountry(code = code!!, name = name, flag = flag)