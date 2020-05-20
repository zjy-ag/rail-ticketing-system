[![license](https://img.shields.io/hexpm/l/plug.svg?style=flat-square)](https://github.com/2227324689/ToBeBetter/blob/master/LICENSE) [![issues](https://img.shields.io/bitbucket/issues-raw/2227324689/ToBeBetter.svg?style=flat-square)](https://github.com/blankjee/rail-ticketing-system/issues) [![author](https://img.shields.io/badge/author-blankjee-blue.svg?style=flat-square)](#) [![Gpmall](https://img.shields.io/badge/tech-springboot-red.svg?style=flat-square)](#)

# 高铁售票系统

高铁售票系统是一个简单的Spring Boot实战项目，提供铁路运行信息查询以及购票出行服务（模拟）。

（项目地址：[rail-ticketing-system](https://github.com/blankjee/rail-ticketing-system)）

## 应用功能结构

![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520171318.png)

## 总体数据流图

![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520171428.png)

## 系统ER图

![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520171725.png)

## 项目用到的技术

项目采用前后端分离开发。 

* SpringBoot2.1.6
* Mybatis
* Mysql
* Redis
* druid
* mybatis generator
* HTML
* JQuery
* Bootstrap

## 应用截图

### 前台模块

1. 登录界面

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172239.png)

2. 注册界面

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172257.png)

3. 首页

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172316.png)

4. 查询结果页

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172334.png)

5. 信息确认页

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172357.png)

6. 订单支付页

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172428.png)

7. 订单列表页

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172446.png)

8. 好友分享选择页面

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172511.png)

9. 邮箱界面

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172529.png)

### 后台模块

1. 车次管理

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520172546.png)

2.  ... （其他管理界面都类似）

## 部署项目

### 1.还原数据库

运行Mysql数据库，利用Navicat等可视化数据库软件连接，创建数据库`rail-ticketing-system`，导入项目中根目录下sql文件下的数据库还原文件`rail-ticketing-system.sql`。

### 2.导入项目

打开IDEA，点击OPEN...选择rail-ticketing-system项目根目录下的pom.xml文件，open as project。

### 3.加载maven

等待加载Maven，IDEA自带Maven一般不需要配置。但是用的是官方源可能会比较慢，课百度`maven 换阿里源`解决。若此步不行，可手动安装maven。

### 4.配置项目

配置属性文件路径：\src\main\resources\project.properties
注：只需配置和修改sql主机地址，数据库名，用户名，密码， 项目访问路径，这几个属性，项目即可正常运行访问。

```
#数据库连接配置
#数据库主机地址
jdbc.host=127.0.0.1
#数据库名
jdbc.database=online-course
#数据库用户名
jdbc.username=填写你的数据库用户名
#数据库密码
jdbc.password=填写你的数据库密码
```

### 5.运行项目

![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520182609.png)

### 6.项目访问

1. 前台进入方式

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520191120.png)

   测试账号：1 密码：1

2. 后台进入方式

   ![](https://gitee.com/blankjee/pic-bed/raw/master/images/20200520191145.png)

   admin 123456

   *注意：如果没有安装Chrome浏览器可以选择最右边的Edge浏览器，点击代码任意处会出现选择浏览器的栏目*



