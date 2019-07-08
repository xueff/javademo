
## java 字符串占位符
```java
System.out.println(Parser.parse0("我的名字是${},结果是${}，可信度是%${}", "雷锋", true, 100));
System.out.println(Parser.parse1("我的名字是{},结果是{}，可信度是%{}", "雷锋", true, 100));

```