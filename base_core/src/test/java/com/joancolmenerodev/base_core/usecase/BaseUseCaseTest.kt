package com.joancolmenerodev.base_core.usecase

import android.accounts.NetworkErrorException
import com.joancolmenerodev.library_base.Either
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class BaseUseCaseTest {

    private var baseUseCase: BaseUseCase = mockk()

    @Test
    fun `given we pass a String in the catchTyped it will return a string on the Right of the Either`() {

        //Assign
        val word = "Flawless"

        //Act
        val result = baseUseCase.toEither<IOException, String> { word }

        //Assert
        assertEquals(Either.Right(word), result)
    }

    @Test(expected = RuntimeException::class)
    fun `given we pass a function that does crash but it's not the same as the reified then it will return that exception`() {

        //Assign
        val exception = RuntimeException()
        val crash: () -> Int = { throw exception }

        //Act
        baseUseCase.toEither<NetworkErrorException, Unit> { crash() }

    }

    @Test
    fun `given we pass an exception in the catchTyped it will return Either left with that exception`() {
        //Assign

        val exception = NetworkErrorException()

        //Act
        val result = baseUseCase.toEither<NetworkErrorException, String> { throw exception }

        //Assert
        assertEquals(Either.Left(exception), result)
    }

}