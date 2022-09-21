package cn.dingshichen.ksbt.service

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DisabledTest {

    private val log: Logger = LoggerFactory.getLogger(DisabledTest::class.java)

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
}