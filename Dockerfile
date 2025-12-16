# Build stage
# ===============================
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

RUN mv target/oems-*.jar target/oems.jar


# ===============================
# Runtime stage
# ===============================
FROM amazoncorretto:17-alpine

ARG GIT_SHA
ENV GIT_SHA=$GIT_SHA
ENV TZ=Asia/Dhaka

RUN apk add --no-cache curl \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

ENV TINI_VERSION=v0.19.0
RUN curl -sSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini \
    --output /usr/sbin/tini \
    && chmod +x /usr/sbin/tini

WORKDIR /app
COPY --from=build /app/target/oems.jar oems.jar

EXPOSE 8080

ENTRYPOINT ["/usr/sbin/tini", "--"]
CMD ["java", "-jar", "oems.jar", "--spring.config=application.properties"]
