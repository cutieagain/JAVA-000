## redis下载路径

https://redis.io/download

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

在从服务器的配置文件中加入：slaveof <masterip> <masterport>

- 启动命令

redis-server启动命令后加入 --slaveof <masterip> <masterport>

- 客户端命令

Redis服务器启动后，直接通过客户端执行命令：slaveof <masterip> <masterport>，则该Redis实例成为从节点。

```bash
# 启动 redis-server
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ bin/redis-server ./conf/redis6379.conf

# 启动 redis-cli
cutiedeMacBook-Pro:redis-6.0.9-6379 cutie$ ./bin/redis-cli
```