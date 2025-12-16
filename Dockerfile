# Build stage
# ===============================
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# ===============================
# Runtime stage
# ===============================
FROM amazoncorretto:17-alpine

ARG GIT_SHA
ENV GIT_SHA=$GIT_SHA
ENV TZ=Asia/Dhaka

# Install tini properly via Alpine package manager
RUN apk add --no-cache tini \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

WORKDIR /app
COPY --from=build /app/target/oems.jar oems.jar

EXPOSE 8080

# Use the Alpine-installed tini
ENTRYPOINT ["/sbin/tini", "--"]
CMD ["java", "-jar", "oems.jar", "--spring.config=application.properties"]
