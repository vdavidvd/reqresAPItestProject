Hi there!

This is RestAssured Test project in Java. This project is testing the https://reqres.in/ and its endpoints and HTTP methods.

I selected couple of endpoints:

- GET /users?page=2 (Retrive List of users)
- GET /users/2 (Retrive Single user)
- GET /users/23 (Retrive Single user which is not exists)
- POST /users (Create new user)
- POST /login (Login successfull)
- POST /login (Create unsuccessfull)
- POST /register (Register successfull)
- POST /register (Register unsuccessfull)
- PUT /users/+{idOfTheUser} (Update an user)
- PATCH /users/+{idOfTheUser} (Partial Update an user)
- DELETE /users/+{idOfTheUser} (Remove an user)

To start this project you need to have Java version 11 or higher, Maven installed in your machine and some Java Editior if you want to start there or 
if you want to start this project in command prompt then you need to position yourself in the project directory where pom.xml is presented and open there 
command promp and run command mvn test. After that project will start and when all tests are finished you will get Extent Report in HTML located in 
ExtentReports folder under project directory.

![reqResHTMLReport](https://user-images.githubusercontent.com/97592838/212359348-2a238f91-e40f-4cd0-9024-aa11bb37a7ef.PNG)
