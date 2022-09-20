package cn.dingshichen.ksbt.repository

import cn.dingshichen.ksbt.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<User, Int>, JpaSpecificationExecutor<User> {

    fun findByRoleIdOrName(roleId: Int?, name: String?): List<User>

}