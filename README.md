JPA1-REST_HelloWorld
====================

This is a bare project that can be used to get a Maven WAR artifact that is deployable on JBoss AS 5.1, with JPA and REST

git clone 
mvn install
cp target/jpa-rest-helloWorld-1.0.0-SNAPSHOT.war to /deploy-folder of JBoss AS 5.1
try url http://localhost:8080/jpa-rest-helloWorld-1.0.0-SNAPSHOT/rest/userListing/persons/all
