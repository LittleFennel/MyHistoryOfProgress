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
	        String methodName = joinPoint.getSinature().getName();
	        String args = Arrays.toString(joinPoint.getArgs());
	    }
	    
	}
	```

	