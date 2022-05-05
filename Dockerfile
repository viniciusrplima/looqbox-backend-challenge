FROM openjdk

EXPOSE 3000

COPY ./build/libs/pokeapi-0.0.1-SNAPSHOT.jar ./pokeapi.jar

ENTRYPOINT java -jar pokeapi.jar
