package cn.dingshichen.ksbt

import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.fail
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@SpringBootApplication
class KotlinSpringBootTestApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootTestApplication>(*args)
}

@RestControllerAdvice
class GlobalExceptionTranslator {

    @ExceptionHandler(Throwable::class)
    fun handleError(e: Throwable): R<*> {
        return fail(e.localizedMessage)
    }
}