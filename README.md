# salute-mall是一个springcloud-Alibaba为骨架的微服务项目，为了验证实际生产过程的一些问题与想法，仅供学习参考。



salute-mall模块：

服务名称 | 端口 | 描述
---|---|---
salute-gatewat| 8101| 微服务网关
salute-search| 8201 | 微服务子系统，基于elasticsearch实现的搜索服务



第三方应用：

服务名称 | 端口 | 描述
---|---|---
Nacos| 8001 |注册中心，配置中心 
MySQL| 3306 |MySQL 数据库 
Redis| 6379 | K-V 缓存数据库 
Elasticsearch|9200 | 日志存储、搜索
Logstash|4560|日志收集
Kibana|5601|日志展示


项目技术应用：

服务名称  | 描述
---|---
lombok| 自动生成getset
mapstruct| 实体转换
Redis|  缓存
Elasticsearch| 搜索
elk|日志收集
redission|分布式锁
