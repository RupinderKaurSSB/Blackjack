# Blackjack Game

A command-line blackjack game implementation in Java.

## Prerequisites

- Java 23
- Maven
- Docker (optional)

## Build Instructions

### Using Maven

Build the application:

mvn clean package

## Run the Application
java -jar target/blackjack-game-1.0-SNAPSHOT-jar-with-dependencies.jar

## Using Docker

### Build Docker image:
docker build -t blackjack .

### Run the Docker container:
docker run -it blackjack

## Release
### Create a new release:

git tag v1.0.0
git push origin v1.0.0

## CI/CD

The project uses GitHub Actions for:

Continuous Integration
Automated Testing
Docker Image Building
Release Management

### Docker images are available at: 

docker pull <your-dockerhub-username>/blackjack:latest



