package cn.dingshichen.ksbt.service

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class DisabledAndTimeoutTest {

    private val log: Logger = LoggerFactory.getLogger(DisabledAndTimeoutTest::class.java)

    /**
     * @Disabled 等同于 junit4 中的 @Ignore
     */
    @Disabled
    @Test
    fun disabled() {
        log.info("被忽略的测试方法")
    }

    @Test
    fun test1() {
        log.info("可以正常执行的方法")
    }

    /**
     * 测试超时，超过设定时间则报错
     */
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @Test
    fun testTimeout() {
        Thread.sleep(3000L)
    }
}