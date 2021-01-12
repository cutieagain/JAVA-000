## 分片主要配置信息

## NETWORK 网络

指定Redis监听端口，默认端口为6379，如果设置为0，则不会监听TCP【重要】

port 6379

## GENERAL 通用

是否以守护线程的方式运行 【重要】

`daemonize:yes`:redis采用的是单进程多线程的模式。当redis.conf中选项daemonize设置成yes时，代表开启守护进程模式。在该模式下，redis会在后台运行，并将进程pid号写入至redis.conf选项pidfile设置的文件中，此时redis将一直运行，除非手动kill该进程。

`daemonize:no`: 当daemonize选项设置成no时，当前界面将进入redis的命令行界面，exit强制退出或者关闭连接工具(putty,xshell等)都会导致redis进程退出。

daemonize no 

## REPLICATION 分片

设置当本机为副本服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步【重要】从slaveof修改为replicaof，5.0.0版本开始

replicaof <masterip> <masterport>

## REDIS CLUSTER redis集群

是否开启集群模式【重要】

cluster-enabled yes

通信超时时间，毫秒

cluster-node-timeout 15000

部分数据丢失集群无法启动

cluster-require-full-coverage yes

## APPEND ONLY MODE 只增模式

指定是否在每次更新操作后进行日志记录，Redis在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis本身同步数据文件是按上面save条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为no【重要】

appendonly no

## SNAPSHOTTING  快照保存机制

工作目录，数据库会写到这个目录下，文件名就是上面的“dbfilename”，累加的文件也放这里，注意这里指定的是文件夹路径【重要】

dir ./ 

## redis集群配置

集群配置至少需要三个master节点，我们这边配置一个三主三从的分片

分片1 主配置文件

```bash
daemonize yes #开启后台运行
port 6379 #工作端口
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6379.pid #pid file所在目录
logfile log/6379.log
```

分片1 从配置文件

```bash
daemonize yes #开启后台运行
port 6380 #工作端口
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6380.pid #pid file所在目录
logfile log/6380.log
replicaof 127.0.0.1 6379
```

分片2 主配置文件

```bash
daemonize yes #开启后台运行
port 6389 #工作端口
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6389.pid #pid file所在目录
logfile log/6389.log
```

分片2 从配置文件

```bash
daemonize yes #开启后台运行
port 6390 #工作端口
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6390.pid #pid file所在目录
logfile log/6390.log
replicaof 127.0.0.1 6389
```

分片3 主配置文件

```bash
daemonize yes #开启后台运行
port 6399 #工作端口
protected-mode no
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6399.pid #pid file所在目录
logfile log/6399.log
```

分片3 从配置文件

```bash
daemonize yes #开启后台运行
port 6400 #工作端口
protected-mode no
dir run/ #指定工作目录，rdb,aof持久化文件将会放在该目录下，不同实例一定要配置不同的工作目录
cluster-enabled yes #启用集群模式
cluster-node-timeout 5000 #节点宕机发现时间，可以理解为主节点宕机后从节点升级为主节点时间
appendonly yes #开启AOF模式
pidfile run/redis-cluster-6400.pid #pid file所在目录
logfile log/6400.log
replicaof 127.0.0.1 6399
```

集群分片配置

分别启动6台redis服务

```bash
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-server conf/redis-6379.conf
cutiedeMacBook-Pro:redis-6.0.9-6380 cutie$ bin/redis-server conf/redis-6380.conf
cutiedeMacBook-Pro:redis-6.0.9-6381 cutie$ bin/redis-server conf/redis-6381.conf
cutiedeMacBook-Pro:redis-6.0.9-6382 cutie$ bin/redis-server conf/redis-6382.conf
cutiedeMacBook-Pro:redis-6.0.9-6383 cutie$ bin/redis-server conf/redis-6383.conf
cutiedeMacBook-Pro:redis-6.0.9-6384 cutie$ bin/redis-server conf/redis-6384.conf
```

分片以及副本配置

```bash
# 输入配置分片以及副本的命令
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 127.0.0.1:6383 to 127.0.0.1:6379
Adding replica 127.0.0.1:6384 to 127.0.0.1:6380
Adding replica 127.0.0.1:6382 to 127.0.0.1:6381
>>> Trying to optimize slaves allocation for anti-affinity
[WARNING] Some slaves are in the same host as their master
M: 8b2824066f48411482156e64b9db7892432bc1f6 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
M: 801597efbf99cea466eb2a47b1a5908607511f90 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
M: 57772076755bdd1669ea2fca77a1e38e4e63dd28 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
S: f70692d04777fdae5bfbef7d2ad56cf97f2a0ccb 127.0.0.1:6382
   replicates 801597efbf99cea466eb2a47b1a5908607511f90
S: 8b23f40cb2d57209c2efa9319e1d0579c98ae61f 127.0.0.1:6383
   replicates 57772076755bdd1669ea2fca77a1e38e4e63dd28
S: c714db0a9889c8453010b0e95cbbe85debf5cb1b 127.0.0.1:6384
   replicates 8b2824066f48411482156e64b9db7892432bc1f6

#这里会让你看一下配置，是否确认，输入yes确认后就按照这个进行处理了
Can I set the above configuration? (type 'yes' to accept): yes

>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
.
>>> Performing Cluster Check (using node 127.0.0.1:6379)
M: 8b2824066f48411482156e64b9db7892432bc1f6 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
M: 57772076755bdd1669ea2fca77a1e38e4e63dd28 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
S: f70692d04777fdae5bfbef7d2ad56cf97f2a0ccb 127.0.0.1:6382
   slots: (0 slots) slave
   replicates 801597efbf99cea466eb2a47b1a5908607511f90
S: 8b23f40cb2d57209c2efa9319e1d0579c98ae61f 127.0.0.1:6383
   slots: (0 slots) slave
   replicates 57772076755bdd1669ea2fca77a1e38e4e63dd28
M: 801597efbf99cea466eb2a47b1a5908607511f90 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
S: c714db0a9889c8453010b0e95cbbe85debf5cb1b 127.0.0.1:6384
   slots: (0 slots) slave
   replicates 8b2824066f48411482156e64b9db7892432bc1f6
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```

测试

```bash
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli -p 6379
127.0.0.1:6379> set a 1
(error) MOVED 15495 127.0.0.1:6381
127.0.0.1:6379> exit
# 必须以集群的方式连接，不然会报错
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli -p 6379 -c
127.0.0.1:6379> set a 1
-> Redirected to slot [15495] located at 127.0.0.1:6381
OK
127.0.0.1:6381> get a
"1"
```