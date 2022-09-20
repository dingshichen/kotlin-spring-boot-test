package cn.dingshichen.ksbt.control

import cn.dingshichen.ksbt.WebTestContext
import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.isSuccess
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UserControllerTest : WebTestContext() {

    @Test
    fun getUser() {
        val result = mockGet<R<String>>(object : TypeToken<R<String>>() {}.type, "/user/{id}", 1)
        assertTrue { result.isSuccess() }
    }
}