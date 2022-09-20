package cn.dingshichen.ksbt.dto

data class R<T>(
    val code: Int,
    val message: String,
    val data: T? = null,
)

fun <T> success(data: T? = null) = R(0, "success", data)

fun <T> success(function: () -> T): R<T> = success(function())

fun fail(message: String) = R<Any>(-1, message)

fun R<*>.isSuccess() = code == 0

fun R<*>.isFail() = !isSuccess()