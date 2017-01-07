DROP TABLE IF EXISTS tb_user;
CREATE TABLE `tb_user` (
  `number` BIGINT(13) DEFAULT '0' COMMENT '学工号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `limit` int(2) NOT NULL COMMENT '用户权限，1为登录权限，2为系统管理员权限，4为教师用户权限，8为学生用户权限，3为1+2的权限',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `loginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `status` int(1) DEFAULT 1 COMMENT '状态位，1为正常，0为已删除',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `tb_user`(number,password,`limit`,createTime) VALUES ('123456', '123456', 3, NOW());
INSERT INTO `tb_user`(number,password,`limit`,createTime) VALUES ('20061657', '123456', 5, NOW());
INSERT INTO `tb_user`(number,password,`limit`,createTime)VALUES ('2013333502028', '123456', 9 , NOW());

