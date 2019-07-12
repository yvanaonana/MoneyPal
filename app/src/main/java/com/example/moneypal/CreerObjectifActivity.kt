package com.example.moneypal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.moneypal.Model.Objectif
import com.example.moneypal.util.FirestoreUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_creer_objectif.*
import java.util.*

class CreerObjectifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_objectif)

        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

        btn_save_objectif.setOnClickListener {
            val titre = edit_text_titre_objectif.text.toString()
            val montant = edit_text_montant_objectif.text.toString()
            val objectif = Objectif(currentUserId, titre, Integer.parseInt(montant), Calendar.getInstance().time, null)
            FirestoreUtil.addObjectif(objectif)
        }
    }
}
