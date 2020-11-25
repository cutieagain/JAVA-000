学习笔记

Week06 作业题目（周四）：

1.（选做）尝试使用 Lambda/Stream/Guava 优化之前作业的代码。

2.（选做）尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。

3.（选做）根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。

4.（选做）根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。

Week06 作业题目（周六）：

1.（选做）基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化

2.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。
```
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(12) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `realname` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `is_freeze` tinyint(1) DEFAULT '0' COMMENT '是否冻结 1是 0 否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6656 DEFAULT CHARSET=utf8mb4 COMMENT='电商app用户';

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `receiver_address_id` bigint(20) DEFAULT NULL COMMENT '收货人地址id',
  `receiver_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货用户姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货用户手机号',
  `province_id` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '省id',
  `province` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '省',
  `city_id` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '市id',
  `city` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '市',
  `district_id` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '区id',
  `district` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '区',
  `detail_address` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '详细地址',
  `buyer_user_id` bigint(20) DEFAULT NULL COMMENT '购买用户id',
  `buyer_user_name` varchar(30) DEFAULT NULL COMMENT '购买用户姓名',
  `shop_user_id` bigint(20) DEFAULT NULL COMMENT '商铺用户id',
  `shop_user_name` varchar(30) DEFAULT NULL COMMENT '商铺用户姓名',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商铺id',
  `merchant_name` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT '商铺名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '订单类型',
  `status` tinyint(2) DEFAULT NULL COMMENT '订单状态（待付款1、待发货2、已发货（待收货3）、已完成4(待评价)，已评价5；退款中6（货未发出，货已发出，货已收到），已关闭7（已退款））',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 是1 否0',
  `buyer_user_remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '客户备注',
  `merchant_remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '商家备注',
  `order_no` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单编号',
  `transaction_no` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '账户流水号',
  `payment_transaction_no` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '支付的流水号',
  `place_order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `place_order_cancel_time` datetime DEFAULT NULL COMMENT '下单后未支付取消时间',
  `pay_order_time` datetime DEFAULT NULL COMMENT '付款时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `send_user_id` bigint(20) DEFAULT NULL COMMENT '发货用户id',
  `send_user_name` varchar(32) DEFAULT NULL COMMENT '发货用户姓名',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `freight_amount` decimal(10,2) DEFAULT '0.00' COMMENT '运费金额',
  `commodity_amount` decimal(10,2) DEFAULT '0.00' COMMENT '商品金额（商品默认金额想加）',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '总金额（实际需要支付金额）',
  `paid_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实际支付金额（实际已支付金额）',
  `discount_amount` decimal(10,2) DEFAULT '0.00' COMMENT '商品折扣后金额（折扣后的金额）',
  `waybill_no` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '物流单号',
  `express_id` bigint(20) DEFAULT NULL COMMENT '物流公司id',
  `express_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '物流公司名称',
  `return_waybill_no` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '退货物流单号',
  `return_express_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '退货物流公司',
  `pay_way` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '支付类型（余额，支付宝，微信）',
  `commodity_count` int(4) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3597 DEFAULT CHARSET=utf8mb4 COMMENT='订单';

CREATE TABLE `commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `commodity_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
  `category_name` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商家id',
  `merchant_name` varchar(32) DEFAULT NULL COMMENT '商家名称',
  `merchant_user_id` bigint(20) DEFAULT NULL COMMENT '商家用户id',
  `merchant_user_name` varchar(32) DEFAULT NULL COMMENT '商家用户名称',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(32) DEFAULT NULL COMMENT '品牌名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '商品状态 0下架 1上架',
  `sold_count` int(6) DEFAULT '0' COMMENT '销售实际数量',
  `fake_add_sold_count` int(6) DEFAULT '0' COMMENT '销售添加数量',
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递公司id',
  `express_name` varchar(32) DEFAULT NULL COMMENT '快递公司名称',
  `place_of_origin_id` bigint(20) DEFAULT NULL COMMENT '产地id',
  `place_of_origin_name` varchar(32) DEFAULT NULL COMMENT '产地名称',
  `brokerage_group_chief` float(5,2) DEFAULT NULL COMMENT '团长佣金百分比',
  `brokerage_group_vice_chief` float(5,2) DEFAULT NULL COMMENT '副团长佣金百分比',
  `commodity_service_id` bigint(20) DEFAULT NULL COMMENT '商品服务id',
  `commodity_service_name` varchar(32) DEFAULT NULL COMMENT '商品服务名称',
  `detail_desc` longtext COMMENT '商品详情介绍',
  `commodity_params_display` varchar(1024) DEFAULT NULL COMMENT '商品参数展示 json',
  `union_tag` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品标签联合（用于搜索）',
  `stock` int(6) DEFAULT '0' COMMENT '库存',
  `lowest_default_price` decimal(10,2) DEFAULT '0.00' COMMENT '最低原价',
  `highest_default_price` decimal(10,2) DEFAULT '0.00' COMMENT '最高原价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=649 DEFAULT CHARSET=utf8mb4 COMMENT='商品主';
```

3.（选做）尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

4.（选做）基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。

5.（选做）尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。

6.（选做）模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。

7.（选做）对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。

作业提交规范：
1. 作业不要打包 ；
2. 同学们写在 md 文件里，而不要发 Word, Excel , PDF 等 ；
3. 代码类作业需提交完整 Java 代码，不能是片段；
4. 作业按课时分目录，仅上传作业相关，笔记分开记录；
5. 画图类作业提交可直接打开的图片或 md，手画的图手机拍照上传后太大，难以查看，推荐画图（推荐 PPT、Keynote）；
6. 提交记录最好要标明明确的含义（比如第几题作业）。

以上作业，要求 1 道必做题目提交到 Github 上面，Week06 作业提交地址：
https://github.com/JAVA-000/JAVA-000/issues/134

请务必按照示例格式进行提交，不要复制其他同学的格式，以免格式错误无法抓取作业。
作业提交截止时间 11 月 25 日（周三）23:59 前。

作业参考示例地址，由秦老师和助教共建，每周同步更新： https://github.com/JavaCourse00/JavaCourseCodes

Github 使用教程： https://u.geekbang.org/lesson/51?article=294701

学号查询方式：PC 端登录 time.geekbang.org, 点击右上角头像进入我的教室，左侧头像下方 G 开头的为学号