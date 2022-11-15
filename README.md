# ms-template
This is a microservices model project used to showcase application of technologies that are used, or can be used in Intermedia microservice applications.
This is intended to be a live project where technologies can be vetted in a minimalist project.
Each implementation should demonstrate best practices for:
* Code standards
* Comments
* Log messages
* Implementation of technologies
* Unit Testing
* Deployment models
* Integration Testing

The ms-template is broken up into 6 subprojects:
* api -> Shared api definitions
* util -> Shared utilities
* microservices/product-composite-service -> User API
* microservices/product-service -> API to product service data
* microservices/recommendation-service -> API to recommendation service data
* microservices/review-service -> API to review-service data

Product-Composite-Services uses a messaging service to communicate with the database APIs. The default message service is RabbitMQ, but it supports using run time dependency injection to replace the message service with Kafka.

Product-Service and Recommendation-Service use MongoDB for data persistence. They give examples of using embedded MongoDB for unit testing.

Review-Service uses MySQL for data persistence and an in memory H2 db for unit testing.

All DB services give jpa model and object to data mapping examples 

Use: mvn clean package to build the project

Use: docker-compose up to start development eco-system

Use: test-em-all.bash to perform integration tests on the full eco-system

ms-template implements the Spring Boot actuator to provide a /actuator/health end point to monitor service health. It implements health indicator aggregation to allow a single end point for monitoring the health of all the dependent services.

The docker-compose.yml gives examples of how to expose the actuator/health endpoint to docker.

# Dependencies
* docker
* docker-compose
* maven
* libssl-dev (ubuntu)
* im.vbo poms must be in the build path
* docker must have a path to docker-hub repository (for docker-compose)
* im.vbo poms files (In the pom files root directory mvn clean install to add poms to the local cache)

# Quick Start Guide
After cloning project, from project root:

cd pom-files

mvn clean install

cd ..

mvn clean package // This will build and perform unit tests 

docker-compose up -d

./test-em-all.bash // may require chmod +x ./test-em-all.bash first

# Known Issues

* test-em-all.bash fails and gets in a restart loop the first time it is run, ctl-c and restart and it completes

# To Do

* Add examples for db's required by LHC
* Add outbox example for kafka
* Deploy to a kubernetes cluster, and add associated yamls
* Break out pom-files to a separate project
