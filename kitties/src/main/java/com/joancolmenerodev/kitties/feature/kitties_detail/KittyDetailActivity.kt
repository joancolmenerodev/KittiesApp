package com.joancolmenerodev.kitties.feature.kitties_detail

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.joancolmenerodev.kitties.R
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import kotlinx.android.synthetic.main.activity_kitty_detail.*

class KittyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitty_detail)
        retrieveAndDisplayData()
    }

    private fun retrieveAndDisplayData() {
        val kittie: Kittie? = intent?.getParcelableExtra(KITTIE_KEY)
        kittie?.let {
            Glide.with(this).load(kittie.image).into(iv_kittie_detail_image)
            tv_kitty_detail_name.text = it.name
            tv_kitty_detail_description.text = it.description
            tv_kitty_detail_temperament.text = it.temperament
            val drawable = getDrawableByName(it.countryCode)
            drawable?.let { image ->
                iv_detail_country_code.setImageDrawable(image)
            }
            tv_kitty_detail_website.text = it.wikipediaLink
        }
    }

    private fun getDrawableByName(name: String): Drawable? {
        val resID =
            this.resources.getIdentifier("flag_${name.toLowerCase()}", "drawable", this.packageName)
        return try {
            ActivityCompat.getDrawable(this, resID)
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val KITTIE_KEY = "kittie_key"

        fun getIntent(
            context: Context,
            kittie: Kittie
        ): Intent {
            return Intent(context, KittyDetailActivity::class.java).also {
                it.putExtra(KITTIE_KEY, kittie)
            }
        }
    }
}
