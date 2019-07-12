package com.example.moneypal.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Switch
import android.widget.Toast
import com.example.moneypal.Model.Transaction
import com.example.moneypal.Model.TransactionType
import com.example.moneypal.Model.typeTransaction
import com.example.moneypal.util.FirestoreUtil
import com.example.moneypal.util.OrangeMoneyUtil.creerTransaction
import org.jetbrains.anko.db.IntParser
import java.util.*

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val extras = intent.extras

        if (extras != null){
            val sms = extras.get("pdus") as Array<Any>

            for (i in sms.indices){
                val format = extras.getString("format")

                var smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    SmsMessage.createFromPdu(sms[i] as ByteArray, format)
                }else{
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }

                val phoneNumber = smsMessage.originatingAddress
                val messageText = smsMessage.messageBody.toString()
                val date = smsMessage.timestampMillis

                if (Regex(pattern = "Transaction reussi").containsMatchIn(input = messageText)){
                    creerTransaction(typeTransaction.TRANFERT_RECU, messageText, Calendar.getInstance().time)
                }else if(Regex(pattern = "Rechargement reussi").containsMatchIn(input = messageText)){
                    creerTransaction(typeTransaction.ACHAT_CREDIT, messageText, Calendar.getInstance().time)
                }else if (Regex(pattern = "Facture").containsMatchIn(input = messageText)){
                    creerTransaction(typeTransaction.FACTURE, messageText, Calendar.getInstance().time)
                }

                Toast.makeText(context, "phoneNumber: $phoneNumber\n" + "messageText: $messageText", Toast.LENGTH_LONG).show()
            }
        }
    }
}