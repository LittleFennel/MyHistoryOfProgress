# 安装

1. 在官网下载压缩包。然后用 XFTP 工具将安装包传到 user/local/src 目录下，使用 `tar -zxvf redis-6.2.6-zip`命令解压
2. 使用`yum -y install gcc c++`在系统下载 gcc，用来编译 redis
3. 在 redis 安装目录下使用`make && make install `命令编译redis
4. 修改 redis.conf 配置文件
	- 将端口改为0.0.0.0
	- 将守护进程改为yes用来实现后台启动
	- 修改密码
	- 修改日志文件输出

# 启动redis服务，进入redis客户端

```shell
# 后台启动 redis
redis-server redis.conf

# 查看 redis 是否启动
ps -ef | grep redis

# 出现这个就是启动成功
root       1107      1  0 01:51 ?        00:00:01 /usr/local/bin/redis-server 0.0.0.0:6379
root       2823   2669  0 02:02 pts/0    00:00:00 grep --color=auto redis

# 输入密码，进入 redis 命令行界面客户端（含中文）
redis-cli -a 887602 --raw
# 成功进入
127.0.0.1:6379> 
```

# Redis 十大数据类型

- Redis 字符串：String
- Redis 列表：List
- Redis 哈希表：Hash
- Redis 集合： Set
- Redis 有序集合：ZSet
- Redis 地理空间：GEO
- Redis 基数统计：HyperLogLog
- Redis 位图：bitmap
- Redis 位域：bitfield
- Redis 流：Stream

## String

String 是 Redis 最基本的类型，一个 key 对应 一个 value

String 类型是二进制安全的，意思是 Redis 的 String 可以包含任何数据，包括 jpg 图片或者序列化的对象

一个 Redis 中字符串 value 最多可以是 512M

## List

Redis 列表是最简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部或者尾部

底层是一个双端链表，最多可以包含 2 ^ 32 - 1 个元素

## Hash

Hash 是一个 String 类型的 field 和 value 的映射表，Hash 特别适合用于存储对象

Redis 的每个 Hash 可以存储 2 ^ 32 - 1 个键值对

## Set

Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据，集合对象的编码可以是 intset 或者 Hashtable

Redis 中 Set 集合是通过哈希表实现的，所以添加、删除、查找的复杂度都是 O(1)

集合中最大的成员数为 2 ^ 32 - 1

## ZSet

sorted set：有序集合

ZSet 和 Set 一样也是 String 类型元素的集合，且不允许重复的成员

不同的是每个元素都会关联一个 double 类型的分数， Redis 正是通过分数来为集合中的成员进行从小到大的排序

ZSet 的成员是唯一的，但分数可以重复

ZSet 的实现是通过哈希表实现的，所以添加、删除、查找的复杂度都是 O(1)，集合中最大的成员数为 2 ^ 32 - 1

## GEO

GEO 主要用来存储地理信息位置，并对存储的信息进行操作。包括

- 添加地理位置的坐标
- 获取地理位置的坐标
- 计算两个坐标之间的距离
- 根据用户给定的经纬度坐标来获取指定范围内的地理位置集合

## HyperLogLog

HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或体积非常大时，计算基数所需要的空间总是固定且是很小的

在 Redis 里面，每个 HyperLogLog 键只需要花费 12kb 内存，就可以计算接近 2 ^ 64 个不同元素的基数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比，但是 HyperLogLog 只会根据输入的元素来计算基数是，而不会存储输入元素本身，所以 HyperLogLog 不能像集群那样，返回输入的各个元素

## bitmap

由 0 和 1 状态表现的二进制的 bit 数组

## bitfield

通过 bitfield 命令可以一次操作多个 bit 位域（指的是连续的多个比特位），他会执行一系列操作并返回一个响应数组，这个数组中的元素对应参数列表中的相应操作的执行结果

## Stream

Stream 是 Redis 5.0 版本新增的数据结构

主要用于消息队列，Redis 本身有一个 Redis发布订阅来实现消息队列的功能，但他有个缺点就是消息无法持久化，如果出现网络断开、Redis 宕机等，消息就回被丢弃

Stream 提供了消息的持久化和主备复制功能，可以让任何客户端访问任何时候的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失

# Redis 常见数据类型操作命令

官方网站（英文）：https://redis.io/commands/

中文：http://www.redis.cn/commands.html

# Springboot 集成 RedisTemplate

1. 导入依赖

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
	<dependency>
	    <groupId>org.apache.commons</groupId>
	    <artifactId>commons-pool2</artifactId>
	</dependency>
	```

2. yml配置文件

	```yml
	redis:
	    port: 6379
	    host: 192.168.93.100
	    password: 887602
	    lettuce:
	      pool:
	        max-wait: -1ms
	        max-active: 8
	        max-idle: 8
	        min-idle: 0
	```

3. 编写配置类来解决 RedisTemplate 序列化问题

	```java
	import com.fasterxml.jackson.annotation.JsonAutoDetect;
	import com.fasterxml.jackson.annotation.JsonInclude;
	import com.fasterxml.jackson.annotation.JsonTypeInfo;
	import com.fasterxml.jackson.annotation.PropertyAccessor;
	import com.fasterxml.jackson.databind.DeserializationFeature;
	import com.fasterxml.jackson.databind.MapperFeature;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.databind.SerializationFeature;
	import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.data.redis.connection.RedisConnectionFactory;
	import org.springframework.data.redis.core.RedisTemplate;
	import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
	import org.springframework.data.redis.serializer.StringRedisSerializer;
	import javax.annotation.Resource;
	import java.text.SimpleDateFormat;
	import java.util.TimeZone;
	
	@Configuration
	public class MyRedisConfig{
	
	    @Resource
	    private RedisConnectionFactory factory;
	
	    @Bean
	    public RedisTemplate redisTemplate() {
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(factory);
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	
	        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(Object.class);
	        redisTemplate.setValueSerializer(serializer);
	
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	        om.setTimeZone(TimeZone.getDefault());
	        om.configure(MapperFeature.USE_ANNOTATIONS, false);
	        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
	        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        serializer.setObjectMapper(om);
	        return redisTemplate;
	    }
	}
	```

	