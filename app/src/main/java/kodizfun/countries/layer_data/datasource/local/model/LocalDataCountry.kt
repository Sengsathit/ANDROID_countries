package kodizfun.countries.layer_data.datasource.local.model

import androidx.room.*

@Entity(
    tableName = "favorite"
)
data class LocalDataCountry(

    @PrimaryKey
    @ColumnInfo(name = "code")
    var code: String,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "flag")
    var flag: String? = null
)
