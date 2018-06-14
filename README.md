# springcloud-eureka

## Eureka
- Eureka是一个用于服务注册和发现的组件，由Netflix开源，功能上类似于Consul/Zookeeper/Etcdd等等。
- Eureka结构：
  - Resgister Service: 服务注册与发现中心，是一个Eureka Server;
  - Provider Service:  服务提供者，是一个Eureka Client;
  - Consumer Service:  服务消费者，是一个Eureka Client;

## 示例
> 示例采用maven module结构
- 准备工作
创建Maven主工程，主工程pom文件如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.zc</groupId>
    <artifactId>sc-eureka</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.3.RELEASE</version> //此处使用2.0.4版本会报错，暂时先使用1.5.3版本
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <spring-cloud.version>Dalston.SR1</spring-cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```
### 示例一
1. 编写Eureka Server
a. 新建一个springboot工程module，继承主工程pom文件，pom文件如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.zc</groupId>
    <artifactId>eureka-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>eureka-server</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>com.zc</groupId>
        <artifactId>sc-eureka</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```
b. 配置application.yml，其中register-with-eureka和fetch-registery两个属性是为了防止eureka server向自身注册，默认是开的。
```
server:
  port: 8761
eureka:
  instance:
    hostname: localhost
  client:
    serviveUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
    register-with-eureka: false
    fetch-registry: false
```
c.在工程启动类EurekaServerApplication加上注解@EnableEurekaServer，开启Eureka Server功能
