package cn.dingshichen.ksbt.dto

data class R<T>(
    val code: Int,
    val message: String,
    val data: T? = null,
)

fun success() = R<Any>(0, "success")

fun fail(message: String) = R<Any>(-1, message)