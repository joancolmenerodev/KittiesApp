package com.joancolmenerodev.kittiesapp.feature.kitties_list

import android.content.Context
import com.joancolmenerodev.kittiesapp.base.JsonFileToString

object MockedKittiesListObjectResponses {

    fun mockBreedResponse(context: Context) =
        JsonFileToString.loadJSONFromAssets(
            context,
            SuccessfullyKittiesListLoadedTest.RESPONSE_BREED_FILE_NAME
        )

    fun mockImageByBreedResponse1(context: Context) =
        JsonFileToString.loadJSONFromAssets(
            context,
            SuccessfullyKittiesListLoadedTest.RESPONSE_IMAGE_BY_BREED_FILE_NAME
        )

    fun mockImageByBreedResponse2(context: Context) =
        JsonFileToString.loadJSONFromAssets(
            context,
            SuccessfullyKittiesListLoadedTest.RESPONSE_IMAGE_BY_BREED_FILE_NAME2
        )
}