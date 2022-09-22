package cn.dingshichen.ksbt.service

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 指定测试方法的运行顺序策略
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class FunctionOrderTest {

    private val log: Logger = LoggerFactory.getLogger(FunctionOrderTest::class.java)

    @Test
    @Order(3)
    fun testOrder1() {
        log.info("第 3 个方法执行完成")
    }

    @Test
    @Order(1)
    fun testOrder2() {
        log.info("第 1 个方法执行完成")
    }

    @Test
    @Order(2)
    fun testOrder3() {
        log.info("第 2 个方法执行完成")
    }
}