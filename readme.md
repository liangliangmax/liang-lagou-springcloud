### 项目总结

本项目是一个基于springcloud的简单的注册登录系统。可以注册，登录，发送注册验证码和ip防刷。



#### 项目结构

|                     |            |
| ------------------- | ---------- |
| eureka-server       | 注册中心   |
| config-server       | 配置中心   |
| gateway-server      | 网关       |
| lagou-page          | 页面       |
| lagou-user-service  | 用户模块   |
| lagou-code-service  | 验证码模块 |
| lagou-email-service | 邮件模块   |

 启动顺序

1. eureka-server
2. config-server 启动之前，可以修改resource/lagou_config/dev下面的配置文件
3. 其他的不分顺序，挨个启动即可



数据库脚本

```sql
CREATE TABLE `lagou_auth_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '⾃自增主键',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `expiretime` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

```sql
CREATE TABLE `lagou_token`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '⾃自增主键',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱地址',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '令牌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

```
CREATE TABLE `lagou_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
```



配置中心：

配置中心采用本地配置文件的方式统一管理配置文件，方便本地修改调试，如果上线可以调整为git管理配置文件。只需要修改spring.profiles.active 从native改成git即可。



采用本地管理的配置文件修改完成之后不能进行bus的方式进行统一刷新，得需要重启config模块才能使用bus刷新。如果是git方式管理的配置文件可以不重启config直接bus刷新。



数据库的配置在 lagou-common-database.yml，启动项目之前需要修改数据库地址

feign的配置在lagou-common-feign.yml

mq的配置在lagou-common-rabbitmq.yml，启动项目之前需要修改mq的地址

config自己的bootstrap配置文件中也需要修改mq的地址



页面：

本项目也不需要前面假设nginx，启动完lagou-page后，直接在浏览器访问http://localhost:8090/login ，即可打开登录页面，这个页面项目里面指明了网关的位置。后面网关也配置了跨域的设置，不会出现跨域的情况。



在注册页面，如果同一个ip在一段时间内或者一定次数内重复提交注册信息，就会被拦截。



登录成功后，后台会返回给页面一个uuid，如果携带uuid访问welcome页面可直接进入，如果没有uuid，则拦截跳转到登录页面。













