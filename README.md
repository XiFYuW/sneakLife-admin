# sneak-life（偷偷的生活）

## 愿景

*活到现在我很庆幸，世上多磨难，生活多珍惜。我希望能做一个能记录过去的系统，昔日的种种如同电影片段，暮年时能回顾自己的一生。*

---

## [前台地址](https://github.com/XiFYuW/sneakLife)

## 项目结构

> **sneakLifeAPP（启动模块）**
> 
> 1. 启动配置文件、启动类
> 
> 2. 数据库备份文件：**sneakLifeAPP\web\sneakLifeDB.sql**

> **sneakLifeCF（配置模块）**
> 
> 1. 本地缓存（com\sneaklife\config）
> 
> 2. 系统配置参数（com\sneaklife\config\dao\pkv）
> 
> 3. redis配置（com\sneaklife\config\dao\redis）
>    
>    。。。。。。。。。。

> **sneakLifePMS（权限管理模块）**
> 
> 1. ServiceController：前台抽象访问类，负责具体请求的跳转。
>    
>    。。。。。。。。。。

> **sneakLifeUT（工具模块）**
> 
> 1. 字段规则检测（com\sneaklife\u\check）
> 
> 2. 加密解密（com\sneaklife\u\check\keyless）
> 
> 3. 请求响应（com\sneaklife\u\check\keyless\iws）
>    
>    。。。。。。。。。。

---

## 功能

- 权限管理
  
  1. 已实现访问验证、功能动态获取
  
  2. 后台管理标签页动态化，已实现基本标签页内容的动态配置
  
  3. 已实现接口加解密方式 
     
     ```
     rsaData = {
      aesData: 'AES加密具体请求数据'
      token: 'RSA公钥加密AES秘钥'
     }
     // 整体rsaData以公钥加密字符串发送给后台，防止token泄漏
     ```
  
  4. 已实现权限授予，具体到页面展示字段、功能按钮、输入字段、模糊查询字段。
  
  5. 已实现逻辑日志记录于mongoDB中，其包括：方法全名称、入参、出参、异常信息，以及逻辑日志，出入日志页面展示。
  
  6. 已实现输入字段规则配置
  
  7. 实现sql监控（druid）
  
  8. 实现任务调度监控（xxl-job）
