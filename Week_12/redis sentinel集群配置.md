# 配置sentinel需要用到的配置解析

## IMPORTANT 重要

sentinel运行的端口号【重要】

port 26379

是否以守护线程的方式运行，即后台运行【重要】

daemonize no

守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定【重要】

pidfile /var/run/redis-sentinel.pid

日志文件保存的地方【重要】

logfile ""

Sentinel 工作的目录【重要】

dir /tmp

格式：sentinel <option_name> <master_name> <option_value>；这一行代表sentinel监控的master的名字叫做mymaster,地址为127.0.0.1:6379，行尾最后的一个2代表什么意思呢？我们知道，网络是不可靠的，有时候一个sentinel会因为网络堵塞而误以为一个master redis已经死掉了，当sentinel集群式，解决这个问题的方法就变得很简单，只需要多个sentinel互相沟通来确认某个master是否真的死了，这个2代表，当集群中有2个sentinel认为master死了时，才能真正认为该master已经不可用了。

【重要】

dentinel monitor mymaster 127.0.0.1 6379 2

首先配置好 [redis安装及主从配置](https://github.com/cutieagain/JAVA-000/blob/main/Week_12/redis安装及主从配置.md) 看之前的

## 配置sentinel集群

配置sentinel.conf

```bash
port 26379
daemonize yes
dentinel monitor mymaster 127.0.0.1 6379 2
logfile log/sentinel-26379.log
dir run/
```

启动sentinel

```bash
bin/redis-sentinel conf/sentinel-26379.conf
```

查看sentinel是否生效

```bash
cutiedeMacBook-Pro:redis-6.0.9-26379 cutie$ bin/redis-cli -h 127.0.0.1 -p 26379 info sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=127.0.0.1:6379,slaves=2,sentinels=1
```

查看sentinel配置文件，多了下面几条

```bash
protected-mode no
user default on nopass ~* +@all
sentinel known-replica mymaster 127.0.0.1 6380
sentinel known-replica mymaster 127.0.0.1 6381
sentinel current-epoch 0
```

启动多个sentinel，发现sentinel集群配置没生效，原因：复制相同的配置文件导致myid相同，删除后重启即可

重启后可以看到有3个sentinel

```bash
cutiedeMacBook-Pro:redis-6.0.9-26380 cutie$ bin/redis-cli -h 127.0.0.1 -p 26380 info sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=127.0.0.1:6379,slaves=2,sentinels=3
```

关闭主redis

```bash
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli -h 127.0.0.1 -p 6379 shutdown
```

查看sentinel状态，切换成6380是主了，但是slaves显示的还是2【配置上改成replica了但是查状态展示的还是slave..】

```bash
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli -h 127.0.0.1 -p 26379 info sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=127.0.0.1:6380,slaves=2,sentinels=3
```