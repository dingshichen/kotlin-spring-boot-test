package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.BaseTestContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.util.ReflectionTestUtils

class UserServiceTest: BaseTestContext() {

    @Autowired
    lateinit var userService: UserService

    /**
     * @DisplayName 注解标识这个测试方法的展示名称，可以展示在 ide 或者测试报告中
     */
    @DisplayName("第一个用户查询")
    @Test
    fun getById() {
        val user = userService.getById(1)
        // junit5 提供的 kotlin 函数
        assertAll("user get",
            { assertNotNull(user) },
            { assertEquals("admin", user.account) }
        )
    }

    /**
     * 测试私有方法
     */
    @Test
    fun encryptPassword() {
        // 使用了 spring 的反射测试工具
        val value = ReflectionTestUtils.invokeMethod<String>(userService, "encryptPassword", "123456")
        assertAll("encrypt passoword",
            { assertNotNull(value) },
            { assertEquals(value, "49ba59abbe56e057") }
        )
    }

    /**
     * 带参测试，有多少参数，就执行多少次
     */
    @ParameterizedTest
    @ValueSource(strings = ["丁时辰", "王一博"])
    fun listByRoleIdOrName(name: String) {
        log.info("listByRoleIdOrName 测试方法正在使用 param = $name 运行测试")
        val users = userService.listByRoleIdOrName(name = name)
        assertTrue { users.isNotEmpty() }
    }

    /**
     * 带多个参数测试
     */
    @ParameterizedTest
    @CsvSource(value = [
        "1, 丁时辰",
        "2, 王一博",
    ])
    fun listByRoleIdOrName2(roleId: Int, name: String) {
        log.info("listByRoleIdOrName2 测试方法正在使用 roleId = $roleId, name = $name 运行测试")
        val users = userService.listByRoleIdOrName(roleId, name)
        assertTrue { users.isNotEmpty() }
    }

    /**
     * 重复测试
     */
    @RepeatedTest(2)
    fun getSaveCache() {
        log.info("getSaveCache 运行")
        val user = userService.getSaveCache(5)
        assertAll("user getSaveCache",
            { assertNotNull(user) },
            { assertEquals("17070700077", user.phoneNumber) }
        )
    }

}