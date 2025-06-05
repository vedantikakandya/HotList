package com.vedantika.hotlist
import java.io.Serializable

data class taskitem(var task:String, var ischecked:Boolean=false): Serializable{
}