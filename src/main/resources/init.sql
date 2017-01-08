/* **** 用户表 **** */
DROP TABLE IF EXISTS tb_user;
CREATE TABLE `tb_user` (
  `number` BIGINT(13) DEFAULT '0' COMMENT '学工号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `limit` int(2) NOT NULL COMMENT '用户权限，1为登录权限，2为系统管理员权限，4为教师用户权限，8为学生用户权限，3为1+2的权限',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `loginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `status` int(1) DEFAULT 1 COMMENT '状态位，1为正常，0为已删除',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_user`(number,password,`limit`,createTime) VALUES ('123456', '123456', 3, NOW());
INSERT INTO `tb_user`(number,password,`limit`,createTime) VALUES ('20061657', '123456', 5, NOW());
INSERT INTO `tb_user`(number,password,`limit`,createTime) VALUES ('2013333502028', '123456', 9 , NOW());

/* **** 教师信息表 **** */
DROP TABLE IF EXISTS tb_teacher_info;
CREATE TABLE tb_teacher_info
(
  `number` BIGINT(13) DEFAULT '0' COMMENT '学工号',
  `name` VARCHAR(128) NOT NULL COMMENT '名字',
  `positionId` INT COMMENT '职位id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY(number)
)Engine=INNODB DEFAULT charset=utf8;

/* **** 学生信息表 **** */
DROP TABLE IF EXISTS tb_student_info;
CREATE TABLE tb_student_info
(
  number BIGINT(13) DEFAULT '0' COMMENT '学工号',
  name VARCHAR(128) COMMENT '姓名',
  telephone CHAR(11) COMMENT '手机号（长号）',
  species INTEGER DEFAULT 0 COMMENT '所属大类',
  originalClass VARCHAR(128) COMMENT '原班级',
  presentClass INT COMMENT '现班级',
  sex INT COMMENT '男1/女2',
  dorm VARCHAR(128) COMMENT '寝室',
  note VARCHAR(128) COMMENT '备注',
  GPA DOUBLE COMMENT '平均学分绩点',
  realGPA DOUBLE COMMENT '平均学分绩点*70%',
  stuFrom VARCHAR(64) COMMENT '生源地',
  division INT COMMENT '文1/理2',
  entranceScore INT COMMENT '高考成绩',
  admissionScore INT COMMENT '生源省高考录取线',
  gradeOne DOUBLE COMMENT '高考成绩/生源省高考录取线',
  gradeTwo DOUBLE COMMENT '30%*高考成绩/生源省高考录取线',
  totalGrade DOUBLE COMMENT '总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线',
  rank INT COMMENT '排名',
  PRIMARY KEY(number)
)Engine=INNODB DEFAULT charset=utf8;

/* **** 职位表 **** */
DROP TABLE IF EXISTS tb_positions;
CREATE TABLE tb_positions
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  name VARCHAR(20) NOT NULL COMMENT '职位名',
  createTime datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY(id),
  UNIQUE KEY description(name)
)ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 专业大类表 **** */
DROP TABLE IF EXISTS tb_species;
CREATE TABLE tb_species
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '大类id',
  name VARCHAR(50) NOT NULL COMMENT '大类名',
  stuAmount INT COMMENT '学生人数',
  createTime DATETIME DEFAULT NULL COMMENT '创建时间',
  status TINYINT DEFAULT 0 COMMENT '状态',
  PRIMARY KEY(id),
  UNIQUE KEY species_name(name)
)Engine=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 专业表 **** */
DROP TABLE IF EXISTS tb_major;
CREATE TABLE tb_major
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '专业id',
  name VARCHAR(50) NOT NULL COMMENT '专业名',
  createTime DATETIME DEFAULT NULL COMMENT '创建时间',
  status TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY(id),
  UNIQUE KEY major_name(name)
)ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 计划专业表 **** */
DROP TABLE IF EXISTS tb_plan_major;
CREATE TABLE tb_plan_major
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  planId INT NOT NULL COMMENT '计划id',
  majorId INT NOT NULL COMMENT '专业id',
  stuAmount INT NOT NULL COMMENT '学生人数',
  classAmount INT NOT NULL COMMENT '班级数',
  createTime DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY(id)
)Engine=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 志愿填报表 **** */
DROP TABLE IF EXISTS tb_intent_fill;
CREATE TABLE tb_intent_fill
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  number CHAR(13) NOT NULL COMMENT '学号',
  majors VARCHAR(256) NOT NULL COMMENT '志愿json字符串',
  createTime DATETIME DEFAULT NULL COMMENT '创建时间',
  updateTime DATETIME DEFAULT NULL COMMENT '更新时间',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1保存、2确认',
  PRIMARY KEY(id),
  UNIQUE KEY number(number)
)Engine=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 年级表 **** */
DROP TABLE IF EXISTS tb_grade;
CREATE TABLE tb_grade
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  grade INT(4) NOT NULL COMMENT '学期',
  PRIMARY KEY(id),
  UNIQUE KEY grade(grade)
)Engine=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 分流计划表 **** */
DROP TABLE IF EXISTS tb_plan;
CREATE TABLE tb_plan
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  grade INT(4) COMMENT '年级',
  species INT(3) COMMENT '大类（id）',
  studentAmount INT COMMENT '人数',
  majorAmount INT COMMENT '专业数',
  createTime DATETIME NOT NULL COMMENT '操作时间',
  status TINYINT COMMENt '状态',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/* **** 系统日志表 **** */
DROP TABLE IF EXISTS tb_system_log;
CREATE TABLE tb_system_log
(
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  operTime DATETIME NOT NULL COMMENT '操作时间',
  user BIGINT(13) NOT NULL COMMENT '操作用户',
  description VARCHAR(50) COMMENT '操作描述',
  status TINYINT COMMENT '状态',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


