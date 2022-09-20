package cn.dingshichen.ksbt

import org.junit.jupiter.api.Assertions.*

import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.isSuccess
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.lang.reflect.Type

@SpringBootTest(classes = [KotlinSpringBootTestApplication::class])
class BaseTestContext


@AutoConfigureMockMvc
class WebTestContext : BaseTestContext() {

    @Autowired
    lateinit var context: WebApplicationContext

    private val mvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(context)
            .alwaysExpect<DefaultMockMvcBuilder> { status().isOk }
            .alwaysExpect<DefaultMockMvcBuilder> { content().contentType(MediaType.APPLICATION_JSON) }
            .build()
    }

    fun <T> mockGet(typeOfT: Type, urlTemplate: String, vararg uriVariables: Any): T {
        return mvc.perform(MockMvcRequestBuilders.get(urlTemplate, uriVariables))
            .andReturn()
            .response
            .contentAsString
            .fromJsonToTypeObject(typeOfT)
    }

    fun mockPost(url: String, cmd: Any) {
        mvc.perform(MockMvcRequestBuilders.post(url).content(cmd.fromObjectToJsonString()))
            .andReturn()
            .response
            .contentAsString
            .fromJsonToTypeObject<R<*>>(object : TypeToken<R<*>>() {}.type)
            .apply {
                assertTrue {
                    isSuccess()
                }
            }
    }

    fun <T> mockPost(typeOfT: Type, url: String, cmd: Any): T {
        return mvc.perform(MockMvcRequestBuilders.post(url).content(cmd.fromObjectToJsonString()))
            .andReturn()
            .response
            .contentAsString
            .fromJsonToTypeObject(typeOfT)
    }

}

fun Any.fromObjectToJsonString(): String {
    return Gson().run {
        toJson(this@fromObjectToJsonString)
    }
}

fun <T> String.fromJsonToTypeObject(typeOfT: Type): T {
    return Gson().run {
        fromJson(this@fromJsonToTypeObject, typeOfT)
    }
}