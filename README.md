# salute-mall是一个springcloud-Alibaba为骨架的微服务项目，为了验证实际生产过程的一些问题与想法，仅供学习参考。



salute-mall模块：

服务名称 | 端口 | 描述
---|---|---
salute-common| | 微服务通用模块
salute-common-core| | 通用工具类相关
salute-common-doc-starter| | 封装knife4J实现的接口文档starter
salute-common-datasource-starter| | 封装mybatis-plus实现的接口文档starter
salute-common-redis-starter| | 封装mybatis-plus实现的接口文档starter
salute-common-security-starter| | 封装jwt+redis+filter+threadLocal实现的认证授权starter
salute-gateway | 8000| 微服务网关
salute-auth | 8001 | 认证授权中心
salute-product | 8002 | 商品中心
salute-search | 8003 | 搜索服务
salute-marketing | 8004 | 营销服务
salute-order | 8005 | 订单服务
salute-pay | 8006 | 支付服务
salute-user | 8007 | 后台用户相关服务
salute-member | 8008 | 会员服务
salute-cms | 8009 | 内容管理服务
salute-ability-center | 8010 | 通用能力中心、类似导出、oss存储

第三方应用：

服务名称 | 端口 | 描述
---|---|---
Nacos | 8001 | 注册中心，配置中心 
MySql| 3306 | MySQL数据库 
Redis | 6379 | K-V缓存数据库 
Elasticsearch | 9200 | 日志存储、搜索
Logstash | 4560 | 日志收集
Kibana | 5601 |日志展示
minio | 5601 | oss文件存储
rocketMq | 5601 | 消息队列
xxl-job | 5601 | 分布式调度



项目技术应用：

服务名称  | 描述
---|---
lombok| 自动生成getset
mapstruct| 实体转换
Redis|  缓存
knife4J| api在线文档
mybatis-plus| 数据库查询工具
Elasticsearch| 搜索
elk|日志收集
redission|分布式锁
