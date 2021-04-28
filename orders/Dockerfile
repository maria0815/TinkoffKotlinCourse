#base image
FROM openjdk:15
#copy jar from target to image
ADD build/libs/orders-1.0.jar app.jar
#port expose
EXPOSE 8081
#start jar inside container
ENTRYPOINT ["java", "-jar", "-Dspring.datasource.url=${JDBC_URL}", "-Dspring.datasource.username=${JDBC_USER}", "-Dspring.datasource.password=${JDBC_PASS}",  "app.jar"]