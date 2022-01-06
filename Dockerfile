FROM maven:3-jdk-11-slim

ENV HOME=/opt/juanServer
WORKDIR $HOME

COPY settings.xml $HOME
COPY pom.xml $HOME
RUN mvn -q -s settings.xml dependency:resolve

COPY . $HOME
RUN mvn -q -s settings.xml package -DskipTests

CMD java -Xms4g -Xmx16g -jar target/server-0.0.1-SNAPSHOT.jar
