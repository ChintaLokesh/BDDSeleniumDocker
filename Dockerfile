FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

WORKDIR usr/lokesh

ADD target/selenium-docker.jar            selenium-docker.jar
ADD target/selenium-docker-tests.jar      selenium-docker-tests.jar
ADD target/libs                           libs
ADD healthcheck.sh                        healthcheck.sh

ENTRYPOINT sh healthcheck.sh