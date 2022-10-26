# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Development

To start your application in the dev profile, run:

```
./gradlew
```

## Building for Development

### Packaging as jar

To build the final jar and optimize the institute application for production, run:

```
./gradlew -Pdev clean bootJar
```

To ensure everything worked, run:

```
java -jar build/libs/*.jar
```

## Swagger

http://localhost:8080/swagger-ui/index.html
