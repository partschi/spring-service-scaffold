# Spring Service Scaffold

A scaffold for a web service operating with a [Spring Framework](https://spring.io/) backend, [reactjs](https://reactjs.org/) 
as frontend and a continuous testing and building pipeline, built on [gradle](https://gradle.org/), [junit5](https://junit.org/junit5/) 
and [GitHub actions](https://github.com/features/actions).

## Components

### Backend

This repository builds a Java 17 application serving a webapp and web service using the [Spring Framework on Reactive Stack](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html).
See [ApiController class](./src/main/java/org/example/ApiController.java) for the implementation of a controller.

### Frontend

A reactjs application created with the [create-react-app script](https://reactjs.org/docs/create-a-new-react-app.html).

### Continuous Building/Testing

Besides JUnit 5, this scaffold employs the following tools for building and testing:

1. [Gradle plugin for the OWASP dependency check, to spot vulnerabilities in the JARs dependencies.](https://plugins.gradle.org/plugin/org.owasp.dependencycheck)
2. [Gradle plugin for Checkstyle to verify code formatting.](https://docs.gradle.org/current/userguide/checkstyle_plugin.html)
3. [Gradle JIB plugin for building a docker image out of the JAR.](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin)
4. [Dependabot to update outdated gradle dependencies.](https://github.com/dependabot)
5. [Spring Boot Integration for Sentry.](https://docs.sentry.io/platforms/java/guides/spring-boot/)

## TODOs

After forking the repository to build your new web services, complete the following TODOs:
- [ ] Rename the project in the following locations:
  - [ ] [settings.gradle.tks](./settings.gradle.kts), property `rootProject.name`
  - [ ] [application.yml](./src/main/resources/application.yml), property `spring.application.name`
  - [ ] [package.json](./src/main/webapp/package.json), property `name`
- [ ] Change the version of the project in:
  - [ ] [build.gradle.tks](./build.gradle.kts), property `version`
  - [ ] [package.json](./src/main/webapp/package.json), property `version`
- [ ] Rename the package from `org.example` to your organisation/application name scheme.
- [ ] Rename the group name in [build.gradle.tks](./build.gradle.kts) to the package name.
- [ ] Set the sentry DSN of your project in [application.yml](./src/main/resources/application.yml)