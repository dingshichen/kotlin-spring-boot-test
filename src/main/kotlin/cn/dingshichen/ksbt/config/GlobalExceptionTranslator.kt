package cn.dingshichen.ksbt.config

import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.fail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionTranslator {

    @ExceptionHandler(Throwable::class)
    fun handleError(e: Throwable): R<*> {
        return fail(e.localizedMessage)
    }

}