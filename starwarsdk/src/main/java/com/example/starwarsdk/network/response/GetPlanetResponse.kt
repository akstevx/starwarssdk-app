package com.example.starwarsdk.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GetPlanetResponse(
    val climate: @RawValue String? = null,
    val created: @RawValue String? = null,
    val diameter: @RawValue String? = null,
    val edited: @RawValue String? = null,
    val films: @RawValue List<String>? = null,
    val gravity: @RawValue String? = null,
    val name: @RawValue String? = null,
    val orbital_period: @RawValue String? = null,
    val population: @RawValue String? = null,
    val residents: @RawValue List<String>? = null,
    val rotation_period: @RawValue String? = null,
    val surface_water: @RawValue String? = null,
    val terrain: @RawValue String? = null,
    val url: @RawValue String? = null,
): Parcelable {
    fun isSuccessful():Boolean{
        return created == null
    }

}
