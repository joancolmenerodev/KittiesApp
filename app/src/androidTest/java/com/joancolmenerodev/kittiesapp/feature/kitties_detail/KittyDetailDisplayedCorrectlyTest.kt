package com.joancolmenerodev.kittiesapp.feature.kitties_detail

import android.content.Intent
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.joancolmenerodev.kitties.feature.kitties_detail.KittyDetailActivity
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kittiesapp.base.BaseTest
import org.hamcrest.Matchers
import org.junit.Test

class KittyDetailDisplayedCorrectlyTest : BaseTest<KittyDetailActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(KittyDetailActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(KittyDetailActivity.getIntent(context, kitty))
    }

    private val kittyDetailPage = KittyDetailPage(context)


    @Test
    fun kittiesListLoaded() {

        at(kittyDetailPage)

        kittyDetailPage.withName(kitty.name)
        kittyDetailPage.withDescription(kitty.description)
        kittyDetailPage.withTemperament(kitty.temperament)
        kittyDetailPage.withWebsite(kitty.wikipediaLink ?: "")

        kittyDetailPage.openWebsite()
        intended(wikiIntended)
    }

    companion object {
        private val kitty = Kittie(
            id = "1",
            name = "CuttieKat",
            description = "Espresso descriptions are the best, because it can be whatever you can imagine, so this is what you can read.",
            image = "https://cdn2.thecatapi.com/images/ks5wRxZmP.jpg",
            countryCode = "eu",
            temperament = "Fast cute play magic",
            wikipediaLink = "https://en.wikipedia.org/wiki/Aegean_cat"
        )

        private val wikiIntended =
            Matchers.allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW), IntentMatchers.hasData(
                    kitty.wikipediaLink
                )
            )
    }

}

