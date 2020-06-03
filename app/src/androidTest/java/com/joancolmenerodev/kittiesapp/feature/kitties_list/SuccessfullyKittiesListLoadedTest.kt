package com.joancolmenerodev.kittiesapp.feature.kitties_list

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.joancolmenerodev.kitties.feature.kitties_list.presentation.KittiesListActivity
import com.joancolmenerodev.kittiesapp.base.BaseTest
import com.joancolmenerodev.kittiesapp.feature.kitties_list.page.KittiesListPage
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection

class SuccessfullyKittiesListLoadedTest : BaseTest<KittiesListActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(KittiesListActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(null)
    }

    private val kittiesListPage = KittiesListPage(context)


    @Test
    fun kittiesListLoaded() {
        enqueueCalls()

        at(kittiesListPage)
        Thread.sleep(100) //Just to let the view loads, and fill the recyclerView
        kittiesListPage.isRecyclerViewFilled()
    }

    private fun enqueueCalls() {
        enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockedKittiesListObjectResponses.mockBreedResponse(context))
        )
        enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockedKittiesListObjectResponses.mockImageByBreedResponse1(context))
        )
        enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockedKittiesListObjectResponses.mockImageByBreedResponse2(context))
        )
    }

    companion object {
        const val RESPONSE_BREED_FILE_NAME = "firstBreedResponse.json"
        const val RESPONSE_IMAGE_BY_BREED_FILE_NAME = "firstBreedImageResponse.json"
        const val RESPONSE_IMAGE_BY_BREED_FILE_NAME2 = "secondBreedImageResponse.json"

    }


}

