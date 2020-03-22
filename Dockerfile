FROM openjdk:8-jre
RUN mkdir /wait-for-db
ADD docker/wait-for-db/wait-for-it.sh /wait-for-db
RUN chmod +x /wait-for-db/wait-for-it.sh
ADD ./docker/start.sh start.sh
RUN chmod +x /start.sh
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["./start.sh"]