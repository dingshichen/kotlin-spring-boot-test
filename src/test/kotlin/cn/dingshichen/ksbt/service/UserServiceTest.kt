package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.BaseTestContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class UserServiceTest: BaseTestContext() {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun getById() {
        val user = userService.getById(1)
        println(user)
    }
}