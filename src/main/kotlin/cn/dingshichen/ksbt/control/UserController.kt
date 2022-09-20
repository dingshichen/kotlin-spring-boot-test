package cn.dingshichen.ksbt.control

import cn.dingshichen.ksbt.dto.R
import cn.dingshichen.ksbt.dto.success
import cn.dingshichen.ksbt.entity.User
import cn.dingshichen.ksbt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    val userService: UserService
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): R<User> {
        return success {
            userService.getById(id)
        }
    }

    @GetMapping("/find")
    fun getUser(@RequestParam roleId: Int?, @RequestParam name: String?): R<List<User>> {
        return success {
            userService.listByRoleIdOrName(roleId, name)
        }
    }
}