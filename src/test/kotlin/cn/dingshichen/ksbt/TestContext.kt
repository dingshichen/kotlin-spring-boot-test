package cn.dingshichen.ksbt

import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(classes = [KotlinSpringBootTestApplication::class])
class BaseTestContext


@AutoConfigureMockMvc
class WebTestContext : BaseTestContext() {

    @Autowired
    lateinit var context: WebApplicationContext

    protected val mvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(context)
            .alwaysExpect<DefaultMockMvcBuilder> { status().isOk }
            .alwaysExpect<DefaultMockMvcBuilder> { content().contentType(MediaType.APPLICATION_JSON) }
            .build()
    }

}