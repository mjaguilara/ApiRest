FROM adoptopenjdk/openjdk11:ubi
CMD [ "./gradlew", "clean" ,"bootJar"]
COPY "./build/libs/*.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]




#docker build -t demo .
#docker images
#docker run -d -p 8080:8080 demo
#https://hub.docker.com/r/adoptopenjdk/openjdk11
