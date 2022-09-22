package cn.dingshichen.ksbt.control

import cn.dingshichen.ksbt.WebTestContext
import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.isFail
import cn.dingshichen.ksbt.dto.isSuccess
import cn.dingshichen.ksbt.entity.User
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UserControllerTest : WebTestContext() {

    @Test
    fun getUser() {
        val result = mockGet<R<User>>(object : TypeToken<R<User>>() {}.type, "/user/1")
        assertTrue { result.isSuccess() }
        assertNotNull(result.data)
        assertEquals("超管", result.data!!.name)
    }

    @Test
    fun getUser2() {
        val params = mapOf(
            "name" to listOf("丁时辰"),
            "roleId" to listOf("2")
        )
        val result = mockGet<R<List<User>>>(object : TypeToken<R<List<User>>>() {}.type, "/user/find", params)
        assertTrue { result.isSuccess() }
        assertFalse { result.data.isNullOrEmpty() }
        assertEquals(4, result.data!!.size)
    }


}