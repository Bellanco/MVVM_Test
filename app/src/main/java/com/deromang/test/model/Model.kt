package com.deromang.test.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ListResponseModel(
    val address: String?,
    val bathrooms: Int?,
    val country: String?,
    val description: String?,
    val district: String?,
    val exterior: Boolean?,
    val features: Features?,
    val floor: String?,
    val latitude: Double?,
    val longitude: Double?,
    val multimedia: Multimedia?,
    val municipality: String?,
    val neighborhood: String?,
    val operation: String?,
    val parkingSpace: ParkingSpace?,
    val price: Double?,
    val priceInfo: PriceInfoModel?,
    val propertyCode: String?,
    val propertyType: String?,
    val province: String?,
    val rooms: Int?,
    val size: Double?,
    val thumbnail: String?,
    var isFavorite: Boolean = false
) : Parcelable

@Keep
@Parcelize
data class Features(
    val hasAirConditioning: Boolean?,
    val hasBoxRoom: Boolean?,
    val hasGarden: Boolean?,
    val hasSwimmingPool: Boolean?,
    val hasTerrace: Boolean?
) : Parcelable

@Keep
@Parcelize
data class Multimedia(
    val images: List<ImageModel?>?
) : Parcelable

@Keep
@Parcelize
data class ParkingSpace(
    val hasParkingSpace: Boolean?,
    val isParkingSpaceIncludedInPrice: Boolean?
) : Parcelable

@Keep
@Parcelize
data class PriceInfoModel(
    val price: Price?
) : Parcelable

@Keep
@Parcelize
data class ImageModel(
    val tag: String?,
    val url: String?
) : Parcelable

@Keep
@Parcelize
data class Price(
    val amount: Double?,
    val currencySuffix: String?
) : Parcelable







