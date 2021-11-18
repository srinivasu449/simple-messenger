# Simple-Messenger-API Using Spring Boot

Backend api that would enable a web app to build a simple messenger application.

This is a Spring Boot application which exposes two API's for a web application to build a messenger app.
Used simple embbeded H2 in-memory database for data storage.

Pre requisites:
1. Make sure Java 8 or higher is installed on our local system.
2. Install maven on our system.

Steps to Run the Application:
1. Clone or download the git repository named simple-messenger to your local.
2. Open your command line and change your directory to simple-messenger.
3. Run 'mvn clean install' to build the application.
4. Then go to '/target' directory where you will find 'simple-messenger-0.0.1-SNAPSHOT.jar' file and then run 'java -jar simple-messenger-0.0.1-SNAPSHOT.jar' (by default the application will start on 8089, if you want to change the port then add the following tag '--server.port=<portNumber>' while running the jar file, else traverse to src\main\resources\application.properties and change 'server.port' value to your own port).
5. On the application start a in memory DB schema will be created. With auto populated users data in users table. Find the schema at path '\src\main\resources\schema.sql' and users table data at '\src\main\resources\data.sql'.

Note: As we are not using a persistense database the data inserted will be cleared after each restart of the server. also access Get Users API to find the existing users id, so that we can use the 'userId' field value (i.e userId value is used as a senderId or recipientId in messenger API's) to send messages from one user to another, and to get messages.
  
6. Now you are all set to use the messenger API's to send and get messages.
7. Find the swagger API documentation at path '\generated\swagger-ui\simple-messenger.json'.
8. We can access API's using swagger.io in any browser by importing the 'simple-messenger.json' file.
Go to -> swagger.io -> Tools -> Swager Editor -> Live Demo -> File -> Import File 

![](images/swagger.JPG)

9. Access API's using postman.

Firstly, Access Get Users API to find the users id, so that we can use the 'userId' field value to send messages from one user to another. and also to get messages.

GET: http://localhost:8089/users/list 

![](images/get-users.PNG)

Post API to send a message

POST: http://localhost:8089/message

Request Body :
{
	"senderId" : 2,
	"recipientId": 3,
	"message": "test message 21"
}

Note: senderId and recipientId were the user id that we got from get users API.

![](images/post-message.PNG)

Get API to access messages from sender

GET: http://localhost:8089/message/list/{recipientId}?senderId=1&limit=true

query parameters were optional, if we provide 'senderId' then we get messages from that particular sender else fetches messages from all senders and if we provide 'limit=true' then we limit the reponse to 100 else fetches messages from last 30 days.

![](images/get-messages.PNG)


OPTIONAL:  Accessing H2 Database:

H2 Console Access:

Access 'http://localhost:8089/h2-console' and the console looks like this.

![](images/h2-login-page.JPG)

Login Details:
1. Driver class: org.h2.Driver
2. JDBC Url: jdbc:h2:file:./data/simple-messenger-db
3. username: sa
4. password: password

make sure you use the above details and connect to DB. The console should look like the below image.

![](images/h2-console.JPG)





References:

https://www.baeldung.com/spring-boot-h2-database followed this to create a h2 database.
