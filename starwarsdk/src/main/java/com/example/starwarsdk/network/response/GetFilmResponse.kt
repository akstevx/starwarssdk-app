package com.example.starwarsdk.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GetFilmResponse(
    val characters: @RawValue List<String>?= null,
    val created: @RawValue String?=null,
    val director: @RawValue String?=null,
    val edited: @RawValue String?=null,
    val episode_id: @RawValue String?=null,
    val opening_crawl: @RawValue String?=null,
    val planets: @RawValue List<String>?=null,
    val producer: @RawValue String?=null,
    val release_date: @RawValue String?=null,
    val species:  @RawValue List<String>?=null,
    val starships: @RawValue List<String>?=null,
    val title: @RawValue String?=null,
    val url: @RawValue String?=null,
    val vehicles: @RawValue List<String>?=null,
): Parcelable {
    fun isSuccessful():Boolean{
        return created == null
    }

}
