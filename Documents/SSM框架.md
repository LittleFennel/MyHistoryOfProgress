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
	    	<artifactId>spring-context</artifactId>
	    	<version>5.3.1</version>
	    </dependency>
	</dependencies>
	```

	