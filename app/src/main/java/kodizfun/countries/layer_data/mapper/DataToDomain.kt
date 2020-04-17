package kodizfun.countries.layer_data.mapper

import kodizfun.countries.layer_data.datasource.remote.model.RemoteDataCountry
import kodizfun.countries.layer_domain.entity.Country


/**
 * RemoteDataCountry to Country
 */
fun RemoteDataCountry.mapToDomain(): Country = Country(name, flagPNG)
fun List<RemoteDataCountry>.mapToDomain(): List<Country> = map { it.mapToDomain() }