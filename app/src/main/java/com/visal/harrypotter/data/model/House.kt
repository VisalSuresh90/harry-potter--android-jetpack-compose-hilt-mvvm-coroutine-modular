package com.visal.harrypotter.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class House(val id:String,val name:String,val image:String): Parcelable
