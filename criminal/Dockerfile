FROM docker.io/eclipse-temurin:8-jdk
LABEL org.opencontainers.image.authors="cnj.jus.br"

ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

RUN apt-get update \
    && apt-get install -y --no-install-recommends fontconfig fonts-dejavu \
    && rm -rf /var/lib/apt/lists/*

EXPOSE 8480 8481

COPY /target/*.jar criminal.jar

ENTRYPOINT ["java", "-jar", "/criminal.jar"]
