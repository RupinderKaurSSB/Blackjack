FROM maven:3.9-amazoncorretto-23 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package assembly:single
RUN ls -l target/

FROM amazoncorretto:23
WORKDIR /app
COPY --from=build /app/target/*jar-with-dependencies.jar /app/blackjack.jar
CMD java -jar blackjack.jar CMD java -jar app.jarCMD java -jar blackjack-0.0.1-SNAPSHOT.jar ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD java -jar /app/app.jarCMD java -jar /app/app.jarCMD java -jar /app/app.jarCMD ["java", "-jar", "/app/app.jar"]COPY --from=build /app/target/*-with-dependencies.jar app.jarCMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]COPY --from=build /app/target/*-with-dependencies.jar app.jarCMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]CMD ["java", "-jar", "app.jar"]