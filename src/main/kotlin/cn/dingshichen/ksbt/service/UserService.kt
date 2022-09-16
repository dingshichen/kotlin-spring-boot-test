package cn.dingshichen.ksbt.service

import cn.dingshichen.ksbt.entity.User
import cn.dingshichen.ksbt.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun getById(id: Int): User = userRepository.findById(id).orElseThrow()

}