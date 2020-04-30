package com.nebiyu.nutrition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nut_detail.*

class NutDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_detail)

        val intent = intent
        val name = intent.getStringExtra("Name")
        val energy = intent.getStringExtra("Energy")
        val protein = intent.getStringExtra("Protein")
        val carbohydrates = intent.getStringExtra("Carbohydrates")
        val carbohydratesSugar = intent.getStringExtra("Carbohydrates_sugar")
        val fat = intent.getStringExtra("Fat")
        val fatSaturated = intent.getStringExtra("Fat_saturated")
        val fibres = intent.getStringExtra("Fibres")
        val sodium = intent.getStringExtra("Sodium")


        name_view.text = name
        energy_view.text = energy
        protein_view.text = protein
        carbohydrates_view.text = carbohydrates
        carbohydrates_sugar_view.text = carbohydratesSugar
        fat_view.text = fat
        fat_saturated_view.text = fatSaturated
        fibres_view.text = fibres
        sodium_view.text = sodium
    }
}
