package cn.dingshichen.ksbt

import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.isSuccess
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.context.WebApplicationContext
import java.lang.reflect.Type

/**
 * Junit 5 不需要指定 @RunWith
 */
@SpringBootTest(classes = [KotlinSpringBootTestApplication::class])
class BaseTestContext


//@AutoConfigureMockMvc     // 如果不需要自主构建 MockMvc 的话就使用此注解来构建 mockMvc
class WebTestContext : BaseTestContext() {

    @Autowired
    private lateinit var context: WebApplicationContext

    private val mvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(context)
            .alwaysExpect<DefaultMockMvcBuilder> { status().isOk }
            .alwaysExpect<DefaultMockMvcBuilder> { content().contentType(MediaType.APPLICATION_JSON) }
            .build()
    }

    /**
     * GET 请求 URL 参数
     */
    fun <T> mockGet(typeOfT: Type, url: String): T {
        return mvc.perform(get(url))
            .andReturn()
            .response
            .contentAsString
            .fromJsonToTypeObject(typeOfT)
    }

    /**
     * GET 请求 request param 参数
     */
    fun <T> mockGet(typeOfT: Type, url: String, params: Map<String, List<String>>): T {
        return mvc.perform(get(url).params(LinkedMultiValueMap(params)))
            .andReturn()
            .response
            .contentAsString
            .fromJsonToTypeObject(typeOfT)
    }

    /**
     * POST 请求 body json 参数
     */
    fun mockPost(url: String, cmd: Any) {
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(cmd.fromObjectToJsonString()))
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

    /**
     * POST 请求 body json 参数（带返回值）
     */
    fun <T> mockPost(typeOfT: Type, url: String, cmd: Any): T {
        return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(cmd.fromObjectToJsonString()))
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