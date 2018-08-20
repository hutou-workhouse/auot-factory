# auot-factory
自动生成代码的工厂

# 使用
基于spring boot的一个web应用
基于swagger进行应用的使用
代码生成基于freemarker模板
访问：http://localhost:8080/swagger-ui.html

## 具体说明
/ 		全局配置信息的展示
/g 		指定某一个插件名称，进行代码生成
/p 		指定插件名称，查看插件的配置信息

## 配置说明
全局配置信息：auto-config\config.yml，这里可以配置用户自定义信息，可以配置插件
插件配置信息：每个插件需要一个自定义的配置信息

## 模板规则
模板中使用的全局信息以 system 开头
模板中使用的插件信息以 local 开头

## 自定义插件
用户可以自定义插件
1. 需要唯一的插件名称
2. 需要一个插件接口，实现自me.helllp.tools.afactory.core.FactoryPlugin
3. 需要一个数据Bean，接收插件的配置信息，继承自me.helllp.tools.afactory.core.BasePluginBean
4. 指定插件配置文件的位置
5. 书写插件配置文件的内容

