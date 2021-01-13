kafka安装三节点集群【docker】 [https://blog.csdn.net/noaman_wgs/article/details/103757791](https://blog.csdn.net/noaman_wgs/article/details/103757791)

Kafka【第一篇】Kafka集群搭建   [https://www.cnblogs.com/luotianshuai/p/5206662.html](https://www.cnblogs.com/luotianshuai/p/5206662.html)

Kafka集群部署指南 [https://ken.io/note/kafka-cluster-deploy-guide](https://ken.io/note/kafka-cluster-deploy-guide)

broker0配置文件

```bash
log.dirs=logs/kafka-logs
zookeeper.connect=localhost:2181
broker.id=0
listeners=PLAINTEXT://localhost:9092

```

broker1配置文件

```bash
log.dirs=logs/kafka-logs
zookeeper.connect=localhost:2181
broker.id=1
listeners=PLAINTEXT://localhost:9093

```

broker2配置文件

```bash
log.dirs=logs/kafka-logs
zookeeper.connect=localhost:2181
broker.id=2
listeners=PLAINTEXT://localhost:9094

```

启动

```bash
#启动
bin/kafka-server-start.sh config/server.properties &
```

创建topic

```bash
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic cutie
Created topic cutie.

```

关闭kafka

```bash
bin/kafka-server-stop.sh
```

查看topic

```bash
cutiedeMacBook-Pro:kafka_2.13-2.7.0_9092 cutie$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092
cutie
```

发送消息

在brokerId为0的上面发送

```bash
bin/kafka-console-producer.sh --broker-list  localhost:9092  --topic cutie
```

消费消息

在brokerId为1和2的上面消费消息

```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 --topic cutie --from-beginning
```

如果我们启动Consumer指定Consumer Group Id就可以作为一个消费组协同工，1个消息同时只会被一个Consumer消费到

```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 --topic cutie --from-beginning --group cutiegroup

bin/kafka-console-consumer.sh --bootstrap-server localhost:9094 --topic cutie --from-beginning --group cutiegroup
```

### **1、Kafka常用配置项说明**

[Untitled](https://www.notion.so/225894de77bc4e04b5965708252ac8f0)