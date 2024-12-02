# Blackjack Game

A command-line blackjack game implementation in Java.

## Prerequisites

- Java 23
- Maven
- Docker (optional)

## Build Instructions

### Using Maven

Build the application locally:
```bash
mvn clean package
```

## Run the Application
```bash
java -jar target/blackjack-game-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Using Docker

### Build Docker image:
```bash
docker build -t blackjack .
```

### Run the Docker container:
```bash
docker run -it blackjack
```

## Release
### Create a new release:
```bash
git tag v1.0.0
git push origin v1.0.0
```

## CI/CD
The project uses GitHub Actions for:
* Continuous Integration 
* Automated Testing 
* Docker Image Building 
* Release Management

### Docker images are available at: 

```bash
docker pull rpkr/blackjack:latest
```



