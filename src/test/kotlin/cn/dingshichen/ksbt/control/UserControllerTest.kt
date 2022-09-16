package cn.dingshichen.ksbt.control

import cn.dingshichen.ksbt.WebTestContext
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class UserControllerTest : WebTestContext() {

    @Test
    fun getUser() {
        val responseText = mvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
            .andReturn()
            .response
            .contentAsString
        println(responseText)
    }
}