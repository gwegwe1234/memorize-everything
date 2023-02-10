## Build stage: Build the Spring Boot application using Gradle
#FROM gradle:7.6.0-jdk11 AS build
#WORKDIR /app
#COPY build.gradle.kts /app
#COPY src /app/src
#RUN gradle build --exclude-task test
#
## Package stage: Copy the JAR file from the build stage and run the application
#FROM adoptopenjdk/openjdk11
#WORKDIR /app
#COPY --from=build /app/build/libs/memorize-everything-0.0.1-SNAPSHOT.jar /app/app.jar
#ENV JAVA_OPTS=""
#CMD ["java", "-jar", "app.jar", "--spring.profiles.active=local"]

### build stage ###
FROM openjdk:11 AS builder

# set arg
ARG WORKSPACE=/home/spring-docker
ARG BUILD_TARGET=${WORKSPACE}/build/libs
WORKDIR ${WORKSPACE}

# copy code & build
COPY . .
RUN ./gradlew clean bootJar

# unpack jar
WORKDIR ${BUILD_TARGET}
RUN jar -xf *.jar


### create image stage ###
FROM openjdk:11

ARG WORKSPACE=/home/spring-docker
ARG BUILD_TARGET=${WORKSPACE}/build/libs
ARG DEPLOY_PATH=${WORKSPACE}/deploy

# copy from build stage
COPY --from=builder ${BUILD_TARGET}/org ${DEPLOY_PATH}/org
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/lib ${DEPLOY_PATH}/BOOT-INF/lib
COPY --from=builder ${BUILD_TARGET}/META-INF ${DEPLOY_PATH}/META-INF
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/classes ${DEPLOY_PATH}/BOOT-INF/classes

WORKDIR ${DEPLOY_PATH}

EXPOSE 8080/tcp
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]