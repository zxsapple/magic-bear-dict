# magic-bear-dict-parent

### 介绍
magic-bear-dict-client-starter 通过springMvc拦截 + cglib 将字典类型自动注入到返回参数中

### 软件架构
基于spring-boot-web的开发

### 客户端使用
##### 核心代码在client里 使用了springMvc拦截+cglib的动态代理自动新增待翻译的字段name
#### 使用步骤(demo见 magic-bear-dict-web-demo)
##### 1 在启动类加上@EnableDictTransfer (默认扫描启动类路径下的所有类)
##### 2 在实体类上加上@OpenDict标记为可用待转类
##### 3 在实体类里待翻译字段加上@DictConvert 标记为待转字段 (默认key为字段名,默认新增的字段为原字段名+Str)
##### 4 实现 AddDictMapTemplate模板 未来有默认从远程server获取字典集的实现

#### 注意点
##### mvc拦截器里的逻辑递归寻找可转类 & 字段，java类库目前支持java.lang.Object & java.util.List (后期其他基础类型需要,可以再添加)
### TODO
##### 1 magic-bear-dict-server 字典获取服务 & 字典管理控制台
##### 2 stater获取字典集 支持服务获取或自定义

#### 目标
##### 最终目标是 用户引入client即可完成转换,字典通过控制台crud,client通过app_id调用微服务获取对应的字典集 整体流程类似apollo配置中心
