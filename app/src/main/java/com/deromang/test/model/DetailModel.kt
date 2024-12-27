package com.deromang.test.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DetailResponseModel(
    val adid: Int?,
    val country: String?,
    val energyCertification: EnergyCertification?,
    val extendedPropertyType: String?,
    val homeType: String?,
    val moreCharacteristics: MoreCharacteristics?,
    val multimedia: Multimedia?,
    val operation: String?,
    val price: Double?,
    val priceInfo: PriceInfo?,
    val propertyComment: String?,
    val propertyType: String?,
    val state: String?,
    val ubication: Ubication?
) : Parcelable

@Keep
@Parcelize
data class EnergyCertification(
    val emissions: Emissions?,
    val energyConsumption: EnergyConsumption?,
    val title: String?
) : Parcelable

@Keep
@Parcelize
data class MoreCharacteristics(
    val agencyIsABank: Boolean?,
    val bathNumber: Int?,
    val boxroom: Boolean?,
    val communityCosts: Double?,
    val constructedArea: Int?,
    val energyCertificationType: String?,
    val exterior: Boolean?,
    val flatLocation: String?,
    val floor: String?,
    val housingFurnitures: String?,
    val isDuplex: Boolean?,
    val lift: Boolean?,
    val modificationDate: Long?,
    val roomNumber: Int?,
    val status: String?
) : Parcelable

@Keep
@Parcelize
data class MultimediaModel(
    val images: List<Image?>?
) : Parcelable

@Keep
@Parcelize
data class PriceInfo(
    val amount: Double?,
    val currencySuffix: String?
) : Parcelable

@Keep
@Parcelize
data class Ubication(
    val latitude: Double?,
    val longitude: Double?
) : Parcelable

@Keep
@Parcelize
data class Emissions(
    val type: String?
) : Parcelable

@Keep
@Parcelize
data class EnergyConsumption(
    val type: String?
) : Parcelable

@Keep
@Parcelize
data class Image(
    val localizedName: String?,
    val multimediaId: Int?,
    val tag: String?,
    val url: String?
) : Parcelable