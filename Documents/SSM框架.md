# 基于注解的AOP之准备工作

1. 添加依赖

	```xml
	<!-- spring-aspects 会帮我们传递过来aspectjweaver -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-aspects</artifactId>
	    <version>5.3.1</version>
	</dependency>
	```

2. 准备被代理的目标资源

	接口

	```java
	public interface Calculator {
	    int add(int i, int j);
	    int sub(int i, int j);
	    int mul(int i, int j);
	    int div(int i, int j);
	}
	```

	实现类

	```java
	@Component
	public class CalculatorPureImpl implements Calculator {
	    @Override
	    public int add(int i, int j) {
	        int res = i + j;
	        System.out.println("方法内部 result = " + result);
	        return res;
	    }
	    
	    @Override
	    public int sub(int i, int j) {
	        int res = i - j;
	        System.out.println("方法内部 result = " + result);
	        return res;
	    }
	    
	    @Override
	    public int mul(int i, int j) {
	        int res = i * j;
	        System.out.println("方法内部 result = " + result);
	        return res;
	    }
	    
	    @Override
	    public int div(int i, int j) {
	        int res = i / j;
	        System.out.println("方法内部 result = " + result);
	        return res;
	    }
	}
	```

3. 创建切面并配置

	```java
	// Aspect 表示这个类是一个切面类
	@Aspect
	// @Component 注解保证这个切面类能够放进IOC容器
	@Component
	public class LogAspect {
	    // 复用切入点表达式
	    @Pointcut("execution(* com.hayashisama.aop.annotation.CalculatorPureImpl.*(..))")
	    public void pointCut(){}
	    
	    @Before("pointCut()")
	    public void beforeMethod(JoinPoint joinPoint) {
	        String methodName = joinPoint.getSinature().getName(); // 获取方法名
	        String args = Arrays.toString(joinPoint.getArgs()); // 获取连接点对应方法的参数
	        System.out.println("Logger --> 前置方法， 方法名：" + methodName + "，参数：" + args);
	    }
	    
	    @After("pointCut()")
	    public void afterMethod(JoinPoint joinPoint) {
	        Signature signature = jointPoint.getSignature();
	        System.out.println("LoggerAspect, 方法：" + signature.getName() + ",执行完毕");
	    }
	    
	    /**
	    * 在返回通知中若要获取目标对象方法的返回值
	    * 只需要通过@AfterRunning注解的returning属性姐可以将通知方法的某个参数指定为接收目标对象方法的返回值的参数
	    **/
	    @AfterRunning(value = "pointCut()", returning = "result")
	    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
	        Signature signature = jointPoint.getSignature();
	        System.out.println("LoggerAspect, 方法：" + signature.getName() + ",结果：" + result);
	    }
	    
	    @AfterThrowing(value = "pointCut()", throwing = "exception")
	    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable exception) {
	        Signature signature = jointPoint.getSignature();
	        System.out.println("LoggerAspect, 方法：" + signature.getName() + ", 异常:" + exception);
	    }
	    
	    @Around("pointCut()")
	    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) {
	        Object result = null;
	        try {
	            System.out.println("环绕通知 --> 前置通知");
	            result = joinPoint.proceed; // 表示目标对象方法的执行
	            System.out.println("环绕通知 --> 返回通知");
	        } catch (Throwable throwable) {
	            throwable.printStackTree();
	            System.out.println("环绕通知 --> 异常通知");
	        } finally {
	            System.out.println("环绕通知 --> 后置通知");
	        }
	    }
	}
	```
	

# 基于注解的声明式事务

1. 加入依赖

	```xml
	<dependencies>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-context</artifactId>
	            <version>5.3.1</version>
	        </dependency>
	
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-orm</artifactId>
	            <version>5.3.1</version>
	        </dependency>
	
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-test</artifactId>
	            <version>5.3.1</version>
	        </dependency>
	
	        <dependency>
	            <groupId>junit</groupId>
	            <artifactId>junit</artifactId>
	            <version>4.12</version>
	            <scope>test</scope>
	        </dependency>
	
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>8.0.16</version>
	        </dependency>
	
	        <dependency>
	            <groupId>com.alibaba</groupId>
	            <artifactId>druid</artifactId>
	            <version>1.0.31</version>
	        </dependency>
	    </dependencies>
	```

2. 创建jdbc.properties

	```properties
	jdbc.driver=com.mysql.cj.jdbc.Driver
	jdbc.url=jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC
	jdbc.username=root
	jdbc.password=887602
	```

3. 创建Spring的配置文件

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	                           http://www.springframework.org/schema/beans/spring-beans.xsd
	                           http://www.springframework.org/schema/context
	                           http://www.springframework.org/schema/context/spring-context.xsd">
	
	    <!-- 引入jdbc.properties -->
	    <context:property-placeholder location="classpath:jdbc.properties"/>
	
	    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
	        <property name="driverClassName" value="${jdbc.driver}"></property>
	        <property name="url" value="${jdbc.url}"></property>
	        <property name="username" value="${jdbc.username}"></property>
	        <property name="password" value="${jdbc.password}"></property>
	    </bean>
	
	    <bean class="org.springframework.jdbc.core.JdbcTemplate">
	        <property name="dataSource" ref="dataSource"></property>
	    </bean>
	</beans>
	```

4. 创建表

	```sql
	-- 创建书籍表
	create table ssm.t_book
	(
	    book_id   int auto_increment comment '主键'
	        primary key,
	    book_name varchar(20) null comment '图书名称',
	    price     int         null comment '价格',
	    stock     int(10)     null comment '库存'
	);
	-- 创建用户表
	create table ssm.t_user
	(
	    user_id  int auto_increment comment '主键'
	        primary key,
	    username varchar(20)  null comment '用户名',
	    balance  int unsigned null comment '余额'
	);
	```

5. 创建组件

	- 创建BookController

		```java
		package com.hayashisama.spring.controller;
		
		import com.hayashisama.spring.service.BookService;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Controller;
		
		@Controller
		public class BookController {
		
		    @Autowired
		    private BookService bookService;
		
		    public void buyBook(Integer userId, Integer bookId) {
		        bookService.buyBook(userId, bookId);
		    }
		}
		```

	- 创建BookDao

		```java
		package com.hayashisama.spring.dao;
		
		public interface BookDao {
		    // 根据图书ID查询图书价格
		    Integer getPriceByBookId(Integer bookId);
		
		    // 更新图书库存
		    void updateStock(Integer bookId);
		
		    // 更新用户余额
		    void updateBalance(Integer userId, Integer price);
		}
		```

	- 创建BookDaoImpl

		```java
		package com.hayashisama.spring.dao.impl;
		
		import com.hayashisama.spring.dao.BookDao;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.jdbc.core.JdbcTemplate;
		import org.springframework.stereotype.Repository;
		
		/**
		 * @program: MyHistoryOfProgress
		 * @ClassName BookDaoImpl
		 * @Description:
		 * @Author: HayashiSama
		 * @Create: 2023-03-08 17:47
		 * @Version 1.0
		 **/
		@Repository
		public class BookDaoImpl implements BookDao {
		
		    @Autowired
		    private JdbcTemplate jdbcTemplate;
		
		    @Override
		    public Integer getPriceByBookId(Integer bookId) {
		        String sql = "select price from t_book where book_id = ?";
		        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
		    }
		
		    @Override
		    public void updateStock(Integer bookId) {
		        String sql = "update t_book set stock = stock - 1 where book_id = ?";
		        jdbcTemplate.update(sql, bookId);
		    }
		
		    @Override
		    public void updateBalance(Integer userId, Integer price) {
		        String sql = "update t_user set balance = balance - ? where user_id = ?";
		        jdbcTemplate.update(sql, price, userId);
		    }
		}
		```

	- 创建BookService

		```java
		package com.hayashisama.spring.service;
		
		public interface BookService {
		    void buyBook(Integer userId, Integer bookId);
		}
		```

	- 创建BookServiceImpl

		```java
		package com.hayashisama.spring.service.impl;
		
		import com.hayashisama.spring.dao.BookDao;
		import com.hayashisama.spring.service.BookService;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Service;
		import org.springframework.transaction.annotation.Transactional;
		
		/**
		 * @program: MyHistoryOfProgress
		 * @ClassName BookServiceImpl
		 * @Description:
		 * @Suthor: HayashiSama
		 * @Create: 2023-03-08 17:45
		 * @Version 1.0
		 **/
		@Service
		public class BookServiceImpl implements BookService {
		
		    @Autowired
		    private BookDao bookDao;
		    @Override
		    @Transactional(
		            // readOnly = true
		            // timeout = 3
		            // noRollbackFor = ArithmeticException.class
		            noRollbackForClassName = "java.lang.ArithmeticException"
		    )
		    public void buyBook(Integer userId, Integer bookId) {
		
		        /*try {
		            TimeUnit.SECONDS.sleep(5);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }*/
		
		        // 查询图书的价格
		        Integer price =  bookDao.getPriceByBookId(bookId);
		
		        // 更新图书的库存
		        bookDao.updateStock(bookId);
		
		        // 更新用户的余额
		        bookDao.updateBalance(userId, price);
		
		        System.out.println(1 / 0);
		    }
		}
		```

# 事务的隔离级别

隔离级别一共有四种

- 读未提交

	允许Transaction01读取Transaction02未提交的修改

- 读已提交

	要求Transaction01只能读取Transaction02已提交的修改

- 可重复读

	确保允许Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其他事务对这个字段进行更新

- 串行化：Serializable

	确保Transaction01可以多次从一个表读取到相同的行，在Transaction01执行期间，禁止其他事务对这个表进行添加、更新、删除曹祖。可以避免任何并发问题，但性能十分低下

各个隔离级别解决并发问题的能力见下表

| 隔离级别             | 脏读 | 不可重复读 | 幻读 |
| -------------------- | ---- | ---------- | ---- |
| 读未提交             | 有   | 有         | 有   |
| 读已提交             | 无   | 有         | 有   |
| 可重复读             | 无   | 无         | 有   |
| 串行化：Serializable | 无   | 无         | 无   |

使用方式

```java
@Transaction(isolation = Isolation.DEFAULT) // 使用数据库默认的隔离级别
@Transaction(isolation = Isolation.READ_UNCOMMITED) // 读未提交
@Transaction(isolation = Isolation.READ_COMMITED) // 读已提交
@Transaction(isolation = Isolation.REPEATABLE_READ) // 可重复读
@Transaction(isolation = Isolation.SERIALIZABLE) // 串行化
```

# SpringMVC入门案例

## 创建maven工程

1. 添加web模块

2. 打包方式为war

3. 引入依赖

	```xml
	<dependencies>
	    <!-- SpringMVC -->
		<dependency>
	    	<groupId>org.springframework</groupId>
	        <artifactId>spring-webmvc</artifactId>
	        <version>5.3.1</version>
	    </dependency>
	    
	    <!-- 日志 -->
	    <dependency>
	    	<groupId>ch.qos.logback</groupId>
	        <artifactId>logback-classic</artifactId>
	        <version>1.2.3</version>
	    </dependency>
	    
	    <!-- ServletAPI-->
	    <dependency>
	    	<groupId>javax.servlet</groupId>
	        <artifactId>javax.servlet-api</artifactId>
	        <version>3.1.0</version>
	        <scope>provided</scope>
	    </dependency>
	    
	    <!-- Spring和Thyleaf整合包 -->
	    <dependency>
	    	<groupId>org.thymeleaf</groupId>
	        <artifactId>thymeleaf-Spring5</artifactId>
	        <version>3.1.0</version>
	        <scope>provided</scope>
	    </dependency>
	</dependencies>
	```

	