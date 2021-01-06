**Week08 作业题目（周四）：**

**1.（选做）**分析前面作业设计的表，是否可以做垂直拆分。

**2.（必做）**设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。
https://github.com/cutieagain/JAVA-000/tree/main/Week_08
配置文件和sql在作业目录下
代码： https://github.com/cutieagain/JAVA-000/tree/main/Week_08/frame/mybatis-druid-demo

**3.（选做）**模拟 1000 万的订单单表数据，迁移到上面作业 2 的分库分表中。

**4.（选做）**重新搭建一套 4 个库各 64 个表的分库分表，将作业 2 中的数据迁移到新分库。

**Week08 作业题目（周六）：**

**1.（选做）**列举常见的分布式事务，简单分析其使用场景和优缺点。

**2.（必做）**基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。


**3.（选做）**基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。

**4.（选做）**基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。

**5.（选做☆）**设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。

**6.（选做☆）**设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。

**7.（选做☆）**设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。

**作业提交规范**：
1\. 作业不要打包 ；
2\. 同学们写在 md 文件里，而不要发 Word, Excel , PDF 等 ；
3\. 代码类作业需提交完整 Java 代码，不能是片段；
4\. 作业按课时分目录，仅上传作业相关，笔记分开记录；
5\. 画图类作业提交可直接打开的图片或 md，手画的图手机拍照上传后太大，难以查看，推荐画图（推荐 PPT、Keynote）；
6\. 提交记录最好要标明明确的含义（比如第几题作业）。

以上作业，要求 2 道必做题目提交到 Github 上面，Week08 作业提交地址：
[https://github.com/JAVA-000/JAVA-000/issues/141](https://github.com/JAVA-000/JAVA-000/issues/141)

请务必按照示例格式进行提交，不要复制其他同学的格式，以免格式错误无法抓取作业。
作业提交截止时间 12 月 9 日（下周三）23:59 前。

**作业参考示例地址，由秦老师和助教共建，每周同步更新：**[ https://github.com/JavaCourse00/JavaCourseCodes](https://github.com/JavaCourse00/JavaCourseCodes)

Github 使用教程：[ https://u.geekbang.org/lesson/51?article=294701](https://u.geekbang.org/lesson/51?article=294701)

学号查询方式：PC 端登录 time.geekbang.org, 点击右上角头像进入我的教室，左侧头像下方 G 开头的为学号
