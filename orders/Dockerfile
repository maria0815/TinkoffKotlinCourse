#base image
FROM openjdk:15
#copy jar from target to image
ADD build/libs/orders-1.0.jar app.jar
#port expose
EXPOSE 8080
#start jar inside container
ENTRYPOINT ["java", "-jar", "app.jar"]