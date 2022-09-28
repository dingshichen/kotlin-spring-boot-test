package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.BaseTestContext
import cn.dingshichen.ksbt.entity.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean

/**
 * @MockBean 会使得 bean 被完全代理，所有方法都会被 mock 并返回默认值
 */
class MockBeanTest : BaseTestContext() {

    @MockBean
    lateinit var userService: UserService

    @Test
    fun getById() {
        val user = userService.getById(1)
        assertNull(user)
    }

    @Test
    fun isBlocked() {
        val blocked = userService.isBlocked(1)
        assertFalse(blocked)
    }

    /**
     * 设置 mockbean 的行为
     */
    @Test
    fun getById2() {
        doReturn(User(name = "wang.yibo"))
            .`when`(userService)
            .getById(1)

        val user = userService.getById(1)
        assertEquals("wang.yibo", user.name)

        val user2 = userService.getById(2)
        assertNull(user2)
    }

    @Test
    fun isBlocked2() {
        `when`(userService.isBlocked(anyInt()))
            .thenReturn(true)

        val blocked = userService.isBlocked(99)
        assertTrue(blocked)
    }

    @Test
    fun listByRoleIdOrName() {
        doThrow(RuntimeException("mock exception"))
            .`when`(userService)
            .listByRoleIdOrName(1, "haha")

        assertThrows(RuntimeException::class.java) {
            userService.listByRoleIdOrName(1, "haha")
        }

        // 失败
        assertThrows(RuntimeException::class.java) {
            userService.listByRoleIdOrName(2, "haha")
        }
    }

    @Test
    fun listByRoleIdOrName2() {
        doCallRealMethod()
            .`when`(userService)
            .listByRoleIdOrName(anyInt(), anyString())

        // 因为 mockbean 里没有注入的 userRepository，所以会报错
        assertThrows(NullPointerException::class.java) {
            userService.listByRoleIdOrName(1, "超管")
        }
    }

    /**
     * answer 可以灵活的设置 mock 方法实现
     */
    @Test
    fun listByRoleIdOrName3() {
        `when`(userService.listByRoleIdOrName(anyInt(), anyString()))
            .thenAnswer {
                val roleId: Int = it.getArgument(0)
                return@thenAnswer if (roleId == 1) listOf() else listOf(User(name = "超管"))
            }

        val users = userService.listByRoleIdOrName(2, "")
        assertEquals(1, users.size)

        val users2 = userService.listByRoleIdOrName(1, "")
        assertEquals(0, users2.size)
    }
}

/**
 * SpyBean 只会在设置了行为的方法会被 mock，其他还是默认执行原本的方法
 */
class SpyBeanTest : BaseTestContext() {

    @SpyBean
    lateinit var userService: UserService

    @Test
    fun getById() {
        val user = userService.getById(1)
        assertNotNull(user)
    }

    @Test
    fun getById2() {
        doReturn(User(name = "wang.yibo"))
            .`when`(userService)
            .getById(1)

        // 设置过的方法，就会使用 mock
        val user = userService.getById(1)
        assertEquals("wang.yibo", user.name)

        // 没有设置过，就执行原本的方法
        val user2 = userService.getById(2)
        assertNotNull(user2)
    }
}