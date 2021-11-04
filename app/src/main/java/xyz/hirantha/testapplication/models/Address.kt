package xyz.hirantha.testapplication.models


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import xyz.hirantha.testapplication.models.Geo

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    @Embedded(prefix = "geo_")
    val geo: Geo,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)