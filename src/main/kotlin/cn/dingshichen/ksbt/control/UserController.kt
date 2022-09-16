package cn.dingshichen.ksbt.control

import cn.dingshichen.ksbt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    val userService: UserService
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int) = userService.getById(id)


}