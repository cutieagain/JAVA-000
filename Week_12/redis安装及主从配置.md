## redis下载路径

[https://redis.io/download](https://redis.io/download)

## redis安装

```bash
# 解压
tar -zxvf redis-6.0.9.tar.gz
# 拷贝的local目录下
sudo cp -rf redis-6.0.9 /usr/local/
# 进入相应目录下
cd /usr/local/redis-6.0.9
# 编译 - 时间有点长，可能要等几分钟
sudo make test
# 安装
sudo make install
# 建立相应目录
sudo mkdir bin etc db
# 拷贝启动文件
sudo cp src/mkreleasehdr.sh src/redis-benchmark src/redis-check-rdb src/redis-cli src/redis-server bin/
```

注意：如果在编译过程中出现错误。命令清理再编译一下： sudo make distclean && sudo make && sudo make test

## 配置主从，有3种方式

- 配置文件

在从服务器的配置文件中加入：replicaof <masterip> <masterport>

- 启动命令

redis-server启动命令后加入 --replicaof <masterip> <masterport>

- 客户端命令

Redis服务器启动后，直接通过客户端执行命令：replicaof <masterip> <masterport>，则该Redis实例成为从节点。

## 主配置文件修改内容

分别在根目录下创建run和log文件夹 mkdir run & mkdir log

```bash
bind 127.0.0.1
port 6379
daemonize yes
pidfile run/redis_6379.pid 
logfile log/redis_6379.log
dir run/ 
```

## 从配置文件修改的内容

```bash
bind 127.0.0.1
port 6380
daemonize yes
pidfile run/redis_6380.pid 
logfile log/redis_6380.log
dir run/
#一处不同
replicaof 127.0.0.1 6379
```

## 分别启动主从

```bash
# 启动主 redis-server
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-server ./conf/redis6379.conf

# 启动主client redis-cli
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-cli -h 127.0.0.1 -p 6379

# 启动从 redis-server
cutiedeMacBook-Pro:redis-6.0.9-6380 cutie$ bin/redis-server ./conf/redis6380.conf

# 启动从client redis-cli
cutiedeMacBook-Pro:redis-6.0.9-6380 cutie$ bin/redis-cli -h 127.0.0.1 -p 6380
```

## 测试主从是否成功

```bash
# 主库client
127.0.0.1:6379> set a 1
OK
# 从库client
127.0.0.1:6380> get a
"1"
```

# 主从配置涉及到的修改项

## 基础

启动redis-server

./redis-server /path/to/redis.conf

## NETWORK 网络

网络连接

绑定的主机地址，redis默认绑定127.0.0.1，即只有本机能够访问，如果要监听所有的ip，注释下面一行即可，正式环境不建议这么做【重要】

bind 127.0.0.1

是否开启保护模式，当保护模式开启时，如果服务器没有显式地绑定到一组地址或者没有设置密码，服务器只接受来自客户端的连接，IPv4和IPv6 127.0.0.1 ::1。默认启用，如果你确定你想要其他主机的客户端连接到Redis，没有配置身份验证，也没有配置一组特定的接口，需要使用bind去绑定ip。【重要】

protected-mode yes

指定Redis监听端口，默认端口为6379，如果设置为0，则不会监听TCP【重要】

port 6379

## general通用

是否以守护线程的方式运行 【重要】

`daemonize:yes`:redis采用的是单进程多线程的模式。当redis.conf中选项daemonize设置成yes时，代表开启守护进程模式。在该模式下，redis会在后台运行，并将进程pid号写入至redis.conf选项pidfile设置的文件中，此时redis将一直运行，除非手动kill该进程。

`daemonize:no`: 当daemonize选项设置成no时，当前界面将进入redis的命令行界面，exit强制退出或者关闭连接工具(putty,xshell等)都会导致redis进程退出。

daemonize no 

当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定【重要】

pidfile /var/run/redis_6379.pid 

日志文件保存路径，如果是守护线程的运行模式，日志目录默认在/dev/null

logfile "" 【重要】

## SNAPSHOTTING  快照保存机制

工作目录，数据库会写到这个目录下，文件名就是上面的“dbfilename”，累加的文件也放这里，注意这里指定的是文件夹路径【重要】

dir ./ 

## REPLICATION 分片

设置当本机为副本服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步【重要】从slaveof修改为replicaof，5.0.0版本开始

replicaof <masterip> <masterport>

当master服务设置了密码保护时，replica服务连接master的密码【重要】

masterauth <master-password>

masteruser <username>

## SECURITY 安全

设置Redis连接密码，如果配置了连接密码，客户端在连接Redis时需要通过AUTH <password>命令提供密码，默认关闭【重要】

requirepass foobared