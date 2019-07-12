package com.example.moneypal.Model

import java.util.*

class Objectif (val adminId:String,
                val titreObjectif: String,
                val montantObjectif: Int,
                val dateCreation: Date,
                val membres: MutableList<String>?) {

    constructor() :this("", "",0, Date(0), null)

}