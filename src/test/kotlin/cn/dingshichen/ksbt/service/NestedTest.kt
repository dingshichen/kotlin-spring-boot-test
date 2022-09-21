package cn.dingshichen.ksbt.service

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.util.*

/**
 * 内部类测试官方示例
 */
class NestedTest {

    lateinit var stack: Stack<Any>

    @Test
    @DisplayName("is instantiated with new Stack()")
    fun isInstantiatedWithNew() {
        Stack<Any>()
    }

    /**
     * @Nested 嵌套测试，展示出测试类之间的关系
     */
    @Nested
    @DisplayName("when new")
    inner class WhenNew {

        @BeforeEach
        fun createNewStack() {
            stack = Stack<Any>()
        }

        @Test
        @DisplayName("is empty")
        fun isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        fun throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException::class.java, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        fun throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException::class.java, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        inner class AfterPushing {

            private val anElement = "an element"

            @BeforeEach
            fun pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            fun isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            fun returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty())
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            fun returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek())
                assertFalse(stack.isEmpty())
            }
        }
    }
}