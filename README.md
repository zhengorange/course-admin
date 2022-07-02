# 教务管理系统

## 项目介绍
这是一个作业演示，包括数据库，后端代码和前端代码。教务系统有三个角色：学生、任课老师和教学秘书。教学秘书维护班级信息和培养方案信息，任课老师维护自己的课程和课程下学生的成绩信息，学生则可以查看自己课表、选课以及查看自己成绩。

## 系统各模块组成

+ 数据库
使用数据库软件版本：mysql 5.7.35
mysql目录下有mysqldump导出的备份文件，可使用mysqldump命令一键恢复数据库。

+ SpringBoot
Java版本：17.0.2
SpringBoot版本：2.7.0
SpringBoot初始化注意事项：完成数据库的恢复后，请将\springboot\eduadmin\src\main\resources\application-dev.yml中spring.datasource.url、username、password替换成可使用的数据库环境。

+ 前端Vue
Vue版本：2.x
前端启动注意事项，在启动项目前请安装node环境。
启动命令：npm run serve
