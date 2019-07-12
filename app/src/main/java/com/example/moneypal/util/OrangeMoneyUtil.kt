package com.example.moneypal.util

import com.example.moneypal.Model.Transaction
import java.util.*

object OrangeMoneyUtil {

    fun creerTransaction(typeTransaction: String, message: String, date: Date){

        val montant = Regex(pattern = """ \d+""").find(input = message)
        val transaction = Transaction(typeTransaction, date, Integer.parseInt(montant?.value))
        FirestoreUtil.addTransaction(transaction)
    }

}