<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${local.userDefines.groupId}</groupId>
    <artifactId>${local.userDefines.artifactId}</artifactId>
    <name>commonService</name>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>

        <!-- 版本 -->
        <springfox-swagger.version>2.8.0</springfox-swagger.version>
        <lombok.version>1.16.20</lombok.version>
        <fastjson.version>1.2.47</fastjson.version>
    </properties>

    <dependencies>
        <!-- Swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${r'${springfox-swagger.version}'}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${r'${springfox-swagger.version}'}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${r'${lombok.version}'}</version>
            <scope>provided</scope>
        </dependency>
         <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${r'${fastjson.version}'}</version>
        </dependency>
    </dependencies>
</project>