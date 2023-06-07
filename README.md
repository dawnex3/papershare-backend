# papershare-backend
试题分享平台-后端。本项目由吴锜娜，袁若容，项盈盈，项黎明编写。

## 项目简介
本项目是试题分享平台的后端部分。前端部分为https://github.com/dawnex3/papershare-frontend/。  
后端使用Spring MVC架构，数据库为Mongo DB。鉴权采用Spring security + JWT。  
开发和运行环境为java 17.  

## 项目结构
```
papershare
│
│  pom.xml                            # maven dependence
│
├─src
│   ├─main
│   │   ├─java/com/itheima            
│   │   │   │ Application.java        # 应用入口
│   │   │   │
│   │   │   ├─model                   # 数据模型层
│   │   │   │
│   │   │   ├─mapper                  # 数据访问层
│   │   │   │
│   │   │   ├─service                 # 业务层
│   │   │   │
│   │   │   ├─controller              # 控制层
│   │   │   │
│   │   │   ├─token                   # 鉴权
│   │   │   │
│   │   │   └─exception               # 异常类
│   │   │
│   │   └─resources
│   │      └─application.properties   # 应用配置与数据库配置
│   │
│   └─test
│   
└─DB                                  # mongo DB bson格式文件

```

