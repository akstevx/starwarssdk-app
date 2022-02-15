package com.example.starwarsdk.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GetPersonResponse(
    val birth_year: @RawValue String? = null,
    val created: @RawValue String? = null,
    val edited: @RawValue String? = null,
    val eye_color: @RawValue String? = null,
    val films: List<String>?,
    val gender: @RawValue String? = null,
    val hair_color: @RawValue String? = null,
    val height: @RawValue String? = null,
    val homeworld: @RawValue String? = null,
    val mass: @RawValue String? = null,
    val name: @RawValue String? = null,
    val skin_color: @RawValue String? = null,
    val species: @RawValue List<Any>?= null,
    val starships: @RawValue List<Any>?= null,
    val url: @RawValue String? = null,
    val vehicles: @RawValue List<String>?= null,
): Parcelable {
    fun isSuccessful():Boolean{
        return created == null
    }

}