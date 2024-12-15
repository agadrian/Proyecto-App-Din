package com.es.interfazproyectoapp.utils

import android.content.Context
import android.widget.Toast

fun customToast(context: Context, msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}