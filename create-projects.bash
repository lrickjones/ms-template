#!/usr/bin/env bash

mkdir mvn-microservices
cd mvn-microservices

spring init \
--boot-version=2.3.0.RELEASE \
--build=maven \
--java-version=1.11 \
--packaging=jar \
--name=product-service \
--package-name=im.backoffice.microservices.core.product \
--groupId=im.backoffice.microservices.core.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-service

spring init \
--boot-version=2.3.0.RELEASE \
--build=maven \
--java-version=1.11 \
--packaging=jar \
--name=review-service \
--package-name=im.backoffice.microservices.core.review \
--groupId=im.backoffice.microservices.core.review \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
review-service

spring init \
--boot-version=2.3.0.RELEASE \
--build=maven \
--java-version=1.11 \
--packaging=jar \
--name=recommendation-service \
--package-name=im.backoffice.microservices.core.recommendation \
--groupId=im.backoffice.microservices.core.recommendation \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
recommendation-service

spring init \
--boot-version=2.3.0.RELEASE \
--build=maven \
--java-version=1.11 \
--packaging=jar \
--name=product-composite-service \
--package-name=im.backoffice.microservices.composite.product \
--groupId=im.backoffice.microservices.composite.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-composite-service

cd ..
