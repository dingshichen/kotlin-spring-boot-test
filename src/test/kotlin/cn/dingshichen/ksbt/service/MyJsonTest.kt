package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.entity.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.GsonTester
import org.springframework.boot.test.json.JacksonTester

/**
 * 测试 JSON 序列化是否符合预期
 */
@JsonTest
class MyJsonTest(
    @Autowired
    val json: JacksonTester<User>,
    @Autowired
    val gson: GsonTester<User>
) {

    @Test
    fun serialize() {
        val user = User(name = "ding.shichen", status = 0)
        println(json.write(user).json)
    }

    @Test
    fun deserialize() {
        val content = "{\"name\": \"ding.shichen\"}"
        println(json.parseObject(content))
    }

    @Test
    fun gson() {
        val user = User(name = "ding.shichen", status = 0)
        println(gson.write(user).json)

        val content = "{\"name\": \"ding.shichen\"}"
        println(gson.parseObject(content))
    }
}