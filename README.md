# Blackjack Game

A command-line blackjack game implementation in Java.

## Regler
1. To spillere, du og Magnus, skal spille mot hverandre 
2. Hver spiller tar to kort hver fra toppen av en tilfeldig stokket kortstokk 
3. Du tar de to første kortene, Magnus tar de to neste 
4. Regn ut den samlede poengsummen til hver spiller 
   * Nummererte kort har poeng som angitt på kortet
   * Knekt (J), Dronning (Q) og Konge (K) teller som 10 poeng 
   * Ess (A) teller som 11 poeng
5. Regn ut om en av spillerene har 21 poeng - Blackjack - med deres initielle kort, og dermed vinner runden
6. Hvis ingen har 21 poeng, skal spillerne trekke kort fra toppen av kortstokken 
   * Du skal stoppe å trekke kort når poengsummen blir 17 eller høyere 
   * Du taper spillet hvis poengsummen er høyere enn 21 
   * Når du har stoppet å trekke kort, begynner Magnus å trekke kort 
   * Magnus slutter å trekke kort når poengsummen hans er høyere enn din poengsum 
   * Magnus taper spillet dersom poengsummen er høyere enn 21
7. Skriv ut hvem som vinner spillet
8. Skriv ut poengsum og kortene til hver spiller ved spillets slutt. Et kort angis ved å slå sammen suit og value:

9. Suit:
   ["H","D","S","C"]
   Value
   ["2","3","4","5","6","7","8","9","10","J","Q","K","A"]
   **Eksempel på utskrift**:
   Vinner: Truls

Magnus | 27 | S7,S10,CJ
Truls  | 19 | D2,H2,C6,H9
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



