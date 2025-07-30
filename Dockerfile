FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy source code and libraries
COPY src/sql/ExampleSql.java ./src/
COPY lib/ ./lib/

RUN mkdir -p bin

# Compilar todos los archivos fuente de Java
# -d indica dónde guardar los archivos .class compilados
# -cp indica el classpath (donde están las clases necesarias para ejecutar el main)
# https://dev.java/learn/jvm/tools/core/javac/
# RUN javac -d bin -cp "src:lib/postgresql-42.7.4.jar" src/*.java src/co/edu/udea/talentotech/programacion/intermedio/figuras/*.java src/co/edu/udea/talentotech/programacion/intermedio/interfaces/*.java
RUN javac -d bin -cp "src:lib/postgresql-42.7.4.jar" src/*.java 

ENV CLASSPATH=/app/bin:/app/lib/postgresql-42.7.4.jar

# Con entryoint siempre se ejecuta el mismo comando
#ENTRYPOINT ["java", "-cp", "/app/bin:/app/lib/postgresql-42.7.4.jar", "MainFiguras"]
# Con CMD el comando puede cambiarse al elejutar el contenedor
# CMD ["java", "-cp", "/app/bin:/app/lib/postgresql-42.7.4.jar", "MainFiguras"]
CMD ["java", "-cp", "/app/bin:/app/lib/postgresql-42.7.4.jar", "ExampleSql"]
