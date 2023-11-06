package com.visal.harrypotter.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//typealias CharacterListResponse = ArrayList<CharacterListResponseElement>
@Parcelize
data class HpCharacter (
    val id: String,
    val name: String,
    val alternateNames: List<String>?,
    val species: String?,
    val gender: String?,
    val house: String?,
    val dateOfBirth: String?,
    val yearOfBirth: Long?,
    val wizard: Boolean?,
    val ancestry: String?,
    val eyeColour: String?,
    val hairColour: String?,
    val wand: Wand?,
    val patronus: String?,
    val hogwartsStudent: Boolean?,
    val hogwartsStaff: Boolean?,
    val actor: String?,
    val alternateActors: List<String>?,
    val alive: Boolean?,
    val image: String?
):Parcelable

@Parcelize
data class Wand (
    val wood: String?,
    val core: String?,
    val length: Double?
):Parcelable


