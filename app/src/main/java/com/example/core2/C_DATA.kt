package com.example.core2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize

data class C_DATA (var cname:String, var cdate:String, var cfoodtype:String, var cratingbar:Float, var pictureclicked:Int):Parcelable {
}