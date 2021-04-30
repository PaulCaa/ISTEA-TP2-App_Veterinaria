package ar.com.pablocaamano.veterinaria.model

import java.io.Serializable

data class Animal (var name: String, var type:String, var raze: String, var age: Number, var cause: String, var diagnostic: String) : Serializable;