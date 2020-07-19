####项目使用到的技术：
* mybatis
mysql
elastic-search
rocket-mq
swagger
spring cloud
redis
多线程


elasticsearch 启动：

解决方案：cmd-> cd C:\tools\elasticsearch\elasticsearch-7.2.1\bin
elasticsearch.bat +enter
访问地址：http://127.0.0.1:9200/  

elasticsearch-head 启动：

解决方案：cmd-> cd C:\tools\es-head\elasticsearch-head-master
npm run start +enter
访问地址：http://127.0.0.1:9100/

rocketmq 启动：解决方案：
启动NameServer: cmd -> cd C:\tools\rocketmq\rocketmq-all-4.7.0-bin-release\bin
start mqnamesrv.cmd
启动broker：cmd -> cd  C:\tools\rocketmq\rocketmq-all-4.7.0-bin-release\bin   
start mqbroker.cmd  autoCreateTopicEnable=true 
启动rocketmq 控制台：cd D:\tools\rocket-console\rocketmq-externals\rocketmq-console\target
java -jar rocketmq-console-ng-1.0.1.jar 