package cn.dingshichen.ksbt.service

import org.junit.jupiter.api.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServiceDefaultTest {

    /**
     * 在每个 test 方法前都会运行一次
     */
    @BeforeEach
    fun beforeEach() {
        log.info("beforeEach 方法正在执行")
    }

    /**
     * 在每个 test 方法后都会运行一次
     */
    @AfterEach
    fun afterEach() {
        log.info("afterEach 方法正在执行")
    }

    @Test
    fun test1() {
        log.info("test1 方法正在执行")
    }

    @Test
    fun test2() {
        log.info("test2 方法正在执行")
    }

    companion object {

        private val log: Logger = LoggerFactory.getLogger(ServiceDefaultTest::class.java)

        /**
         * 在所有 test 方法运行前运行一次
         */
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            log.info("beforeAll 方法正在执行")
        }

        /**
         * 在所有 test 方法运行后运行一次
         */
        @AfterAll
        @JvmStatic
        fun afterAll() {
            log.info("afterAll 方法正在执行")
        }
    }

}