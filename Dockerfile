FROM maven:3.5.2-jdk-8 AS build
WORKDIR /app
COPY src src
COPY pom.xml pom.xml
RUN mvn -f pom.xml clean package
RUN mv target/oems-*.jar target/oems.jar

FROM amazoncorretto:8
ARG GIT_SHA
ENV GIT_SHA=$GIT_SHA
ENV TZ=Asia/Dhaka
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV TINI_VERSION v0.19.0
WORKDIR /app
RUN curl -sSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini --output /usr/sbin/tini \
        && chmod +x /usr/sbin/tini
COPY --from=build /app/target/oems.jar oems.jar
EXPOSE 8080
ENTRYPOINT ["/usr/sbin/tini", "--"]
CMD ["java", "-jar", "oems.jar", "--spring.config=application.properties"]
