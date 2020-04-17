package kodizfun.countries.layer_data.datasource.local.model

import androidx.room.*

@Entity(
    tableName = "countries"
)
data class LocalDataCountry(
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "flag")
    var flag: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
