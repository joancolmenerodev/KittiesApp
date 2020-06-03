package com.joancolmenerodev.login.data.mapper

import com.joancolmenerodev.login.data.models.Data
import com.joancolmenerodev.login.data.models.LoginResponse
import com.joancolmenerodev.login.data.models.User
import com.joancolmenerodev.login.domain.models.UserLogged
import org.junit.Assert.assertEquals
import org.junit.Test


class LoginResponseMapperKtTest {

    @Test
    fun `Login response to UserLogged mapper`() {
        //ASSIGN
        val expectedResult = UserLogged(1, "Joan Colmenero", "joancolmenero@gmail.com", "1234Token")

        //ACT
        val result = loginResponse.map()

        //ASSERT
        assertEquals(expectedResult, result)
    }

    companion object {
        private val loginResponse = LoginResponse(
            data = Data(
                token = "1234Token",
                user = User(
                    email = "joancolmenero@gmail.com",
                    id = 1,
                    name = "Joan Colmenero"
                )
            ),
            message = "Login successfully",
            success = true
        )
    }
}