package cn.dingshichen.ksbt.entity

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SelectBeforeUpdate
import javax.persistence.*

@Table(name = "user")
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Entity
data class User(

    /**
     * pk
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    /**
     * 角色id
     */
    @Column(name = "role_id")
    var roleId: Int? = null,

    /**
     * 姓名
     */
    @Column
    var name: String? = null,

    /**
     * 账户名
     */
    @Column
    var account: String? = null,

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    var phoneNumber: String? = null,

    /**
     * 登陆密码
     */
    @Column
    var password: String? = null,

    /**
     * 状态：0：正常、1：锁定
     */
    @Column
    var status: Byte? = null,

)
