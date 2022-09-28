package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.entity.User
import cn.dingshichen.ksbt.repository.UserRepository
import cn.hutool.crypto.SecureUtil
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun getById(id: Int): User = userRepository.findById(id).orElseThrow()

    private fun encryptPassword(password: String) = SecureUtil.md5().digestHex16(password)

    fun listByRoleIdOrName(roleId: Int? = null, name: String? = null) = userRepository.findByRoleIdOrName(roleId, name)

    // 假设有一个缓存
    private var cacheUser: User? = null

    fun getSaveCache(id: Int): User {
        return cacheUser ?: userRepository.findById(id)
            .orElseThrow()
            .also { cacheUser = it }
    }

    fun isBlocked(id: Int) = getById(id).status!!.toInt() == 1
}