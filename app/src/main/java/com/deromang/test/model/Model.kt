package com.deromang.test.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.annotation.Keep

@Keep
@Parcelize
data class CharactersResponseModel(
    val info: Info = Info(),
    val results: List<Result> = listOf()
) : Parcelable

@Keep
@Parcelize
data class Info(
    val page: Int = 0,
    val results: Int = 0,
    val seed: String = "",
    val version: String = ""
) : Parcelable

@Keep
@Parcelize
data class Result(
    val cell: String = "",
    val dob: Dob = Dob(),
    val email: String = "",
    val gender: String = "",
    val id: Id = Id(),
    val location: Location = Location(),
    val login: Login = Login(),
    val name: Name = Name(),
    val nat: String = "",
    val phone: String = "",
    val picture: Picture = Picture(),
    val registered: Registered = Registered()
) : Parcelable

@Keep
@Parcelize
data class Dob(
    val age: Int = 0,
    val date: String = ""
) : Parcelable

@Keep
@Parcelize
data class Id(
    val name: String = "",
    val value: String = ""
) : Parcelable

@Keep
@Parcelize
data class Location(
    val city: String = "",
    val coordinates: Coordinates = Coordinates(),
    val country: String = "",
    val postcode: String = "",
    val state: String = "",
    val street: Street = Street(),
    val timezone: Timezone = Timezone()
) : Parcelable

@Keep
@Parcelize
data class Login(
    val md5: String = "",
    val password: String = "",
    val salt: String = "",
    val sha1: String = "",
    val sha256: String = "",
    val username: String = "",
    val uuid: String = ""
) : Parcelable

@Keep
@Parcelize
data class Name(
    val first: String = "",
    val last: String = "",
    val title: String = ""
) : Parcelable

@Keep
@Parcelize
data class Picture(
    val large: String = "",
    val medium: String = "",
    val thumbnail: String = ""
) : Parcelable

@Keep
@Parcelize
data class Registered(
    val age: Int = 0,
    val date: String = ""
) : Parcelable

@Keep
@Parcelize
data class Coordinates(
    val latitude: String = "",
    val longitude: String = ""
) : Parcelable

@Keep
@Parcelize
data class Street(
    val name: String = "",
    val number: Int = 0
) : Parcelable

@Keep
@Parcelize
data class Timezone(
    val description: String = "",
    val offset: String = ""
) : Parcelable