CREATE DATABASE ksbt;
USE ksbt;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'pk',
                        `role_id` int(11) NOT NULL COMMENT '角色id',
                        `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
                        `account` varchar(32) NOT NULL DEFAULT '' COMMENT '账户名',
                        `phone_number` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号码',
                        `password` varchar(32) NOT NULL DEFAULT '' COMMENT '登陆密码',
                        `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态：0：正常、1：锁定',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_account_uindex` (`account`),
                        UNIQUE KEY `user_phone_number_uindex` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'pk',
                        `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
                        `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态，0：正常、1：锁定',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `role_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

insert into role (id, name)
VALUES (1, '超级管理员'), (2, '管理员');

insert into user (id, role_id, name, account, phone_number, password, status)
values (1, 1, '超管', 'admin', '100000', '49ba59abbe56e057', 0),
       (2, 2, '丁时辰', 'ding.shichen', '17705505050', '49ba59abbe56e057', 0),
       (3, 2, '王一博', 'wang.yibo', '18080080808', '49ba59abbe56e057', 0),
       (4, 2, '易烊千玺', 'yi.yangqianxi', '13033000033', '49ba59abbe56e057', 0),
       (5, 2, '朱一龙', 'zhu.yilong', '17070700077', '49ba59abbe56e057', 0);